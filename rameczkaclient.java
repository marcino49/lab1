package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Cezar.cezar;
import DiffieHellman.DiffieHellman;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class rameczkaclient extends JFrame {

	private static JPanel contentPane;
	private static JTextArea msg;
	
	private static Socket socket;
	private static DataInputStream dataInput;
	private static DataOutputStream dataOutput;

	
	private static DiffieHellman dhClient = new DiffieHellman();
	private static cezar cr = new cezar();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rameczkaclient frame = new rameczkaclient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		rameczkaclient.connectToServer("127.0.0.1", 1099);
		
		String messageFromServer = "";
		while(true){
			try {
				messageFromServer = dataInput.readUTF();
				msg.setText(msg.getText().trim()+"\nServer:\t"+messageFromServer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
	}

	/**
	 * Create the frame.
	 */
	public rameczkaclient() {
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		msg = new JTextArea();
		msg.setBounds(10, 11, 312, 368);
		contentPane.add(msg);
		
		JButton btnZamknij = new JButton("zamknij");
		btnZamknij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					socket.close();
					System.exit(0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnZamknij.setBounds(397, 53, 97, 25);
		contentPane.add(btnZamknij);
	}
	
	public static void connectToServer(String ip,int port){
		try{
			socket = new Socket(ip,port);
			
			dataInput = new DataInputStream(socket.getInputStream());
			dataOutput = new DataOutputStream(socket.getOutputStream());
			
			String clientm = "";
				
			dhClient.setP(dataInput.readInt());
			clientm += "Client P:\t\t" + dhClient.getP()+"\n\n";
		
			
			dhClient.setG(dataInput.readInt());
			clientm += "Client G:\t\t" + dhClient.getG()+"\n\n";
			
			
			dhClient.setSecretB(15);
			clientm += "Client secretB:\t\t" + dhClient.getSecretB()+"\n\n";
			
			
			dhClient.setB(dhClient.calculateA(dhClient.getP(),dhClient.getG(), dhClient.getSecretB()));
			clientm += "Client calculate B:\t" + dhClient.getB()+"\n\n";
			
			
			dhClient.setAFromServer(dataInput.readInt());
			clientm += "Client A from Server:\t" + dhClient.getAFromServer()+"\n\n";
			
	
			dataOutput.writeInt(dhClient.getA());
		
			dhClient.setClientS(dhClient.calculateSClient(dhClient.getAFromServer(),dhClient.getSecretB() ,dhClient.getP()));
			clientm += "Client S:\t\t" + dhClient.getClientS()+"\n\n";
			
			msg.setText(clientm);
			
			msg.setText(msg.getText().trim()+"\nJa:\t"+"test");
			dataOutput.writeUTF(cr.encrypt(dhClient.getClientS(), "test"));
			Thread.sleep(250);
			
				
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}

}
