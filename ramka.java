package Server;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Cezar.cezar;
import DiffieHellman.DiffieHellman;
import PrimeNumber.numberr;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ramka extends JFrame {

	private static JPanel contentPane;
	private static JTextArea msg;
	
	private static ServerSocket server_socket;
	private static Socket socket;
	private static DataInputStream dataInput;
	private static DataOutputStream dataOutput;
	
	
	private static DiffieHellman dhServer = new DiffieHellman();
	private static numberr randPrimeNumber = new numberr();
	
	private static cezar cr = new cezar();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ramka frame = new ramka();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		ramka.createServer(1099);
		
		String messageFromClient = "";
		while(true){
			try {
				messageFromClient = dataInput.readUTF();
				msg.setText(msg.getText().trim()+"\nClient:\t"+messageFromClient);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	/**
	 * Create the frame.
	 */
	public ramka() {
		setTitle("Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 643, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		msg = new JTextArea();
		msg.setBounds(10, 11, 333, 337);
		contentPane.add(msg);
		
		JButton btnZamknij = new JButton("Zamknij");
		btnZamknij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					socket.close();
					System.exit(0);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnZamknij.setBounds(426, 83, 97, 25);
		contentPane.add(btnZamknij);
	}
	
	
	public static void createServer(int port){
		try{
			server_socket = new ServerSocket(port);
			socket = server_socket.accept();
			
			dataInput = new DataInputStream(socket.getInputStream());
			dataOutput = new DataOutputStream(socket.getOutputStream());
			
			String serverMsg = "";
			
			dhServer.setP(randPrimeNumber.liczba(32));
			dhServer.setG(randPrimeNumber.liczba(9));
			dhServer.setSecretA(6);
			
			
		
			serverMsg += "Server P:\t\t" + dhServer.getP()+"\n\n";
		
			serverMsg += "Server G:\t\t" + dhServer.getG()+"\n\n";
	
			serverMsg += "Server secretA:\t\t" + dhServer.getSecretA()+"\n\n";

		
			dataOutput.writeInt(dhServer.getP());
			Thread.sleep(250);
		
		
			dataOutput.writeInt(dhServer.getG());
			Thread.sleep(250);
			
		
			dhServer.setA(dhServer.calculateB(dhServer.getP(),dhServer.getG(), dhServer.getSecretA()));
			serverMsg += "Server calculate A:\t" + dhServer.getA()+"\n\n";
			
			dataOutput.writeInt(dhServer.getA());
			Thread.sleep(250);
			

			dhServer.setBFromClient(dataInput.readInt());
			serverMsg += "Server B from Client:\t" + dhServer.getBFromClient()+"\n\n";
			Thread.sleep(250);
			
		
			dhServer.setServerS(dhServer.calculateSServer(dhServer.getBFromClient(),dhServer.getSecretA() ,dhServer.getP()));
			serverMsg += "Server S:\t\t" + dhServer.getServerS()+"\n\n";
		
			msg.setText(serverMsg);
			Thread.sleep(250);
			
			msg.setText(msg.getText().trim()+"\nJa:\t"+"testserver");
			dataOutput.writeUTF(cr.encrypt(dhServer.getServerS(), "testserver"));
			Thread.sleep(250);
			
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
	}
	
}
