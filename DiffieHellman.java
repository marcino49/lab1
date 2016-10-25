package DiffieHellman;

	public class DiffieHellman {
		
		private int p;
		private int g;
		private int A;
		private int a;
		private int AFromServer;
		private int B;
		private int b;
		private int BFromClient;
		private int ClientS;
		private int ServerS;
		
		public DiffieHellman(){
			
		}
		
		public int calculateA(int p,int g,int secretA){
			
			double tmp = ((Math.pow(g, secretA)) % p);
			A = (int)tmp;
			
			return A;
		}
		
		public int calculateB(int p,int g,int secretB){
			
			double tmp = ((Math.pow(g, secretB)) % p);
			B = (int)tmp;
			
			return B;
		}
		
		public int calculateSServer(int AFromClient,int a,int p){
			double tmp = ((Math.pow(AFromClient, a)) % p);
			ServerS = (int) tmp;
			return ServerS;
		}
		
		public int calculateSClient(int BFromServer,int b,int p){
			double tmp = ((Math.pow(BFromServer, b)) % p);
			ClientS = (int) tmp;
			return ClientS;
		}
		
		public int getP(){
			return p;
		}
		public void setP(int p){
			this.p = p;
		}
		
		public int getG(){
			return g;
		}
		public void setG(int g){
			this.g = g;
		}
		
		public int getA(){
			return A;
		}
		public void setA(int A){
			this.A = A;
		}
		
		public int getSecretA(){
			return a;
		}
		public void setSecretA(int secretA){
			this.a = secretA;
		}
		
		public int getB(){
			return B;
		}
		public void setB(int B){
			this.B = B;
		}
		
		public int getSecretB(){
			return b;
		}
		public void setSecretB(int secretB){
			this.b = secretB;
		}
		
		public int getClientS(){
			return ClientS;
		}
		public void setClientS(int ClientS){
			this.ClientS = ClientS;
		}
		
		public int getServerS(){
			return ServerS;
		}
		public void setServerS(int ServerS){
			this.ServerS = ServerS;
		}
		
		public int getAFromServer(){
			return AFromServer;
		}
		public void setAFromServer(int AFromServer){
			this.AFromServer = AFromServer;
		}
		public int getBFromClient(){
			return BFromClient;
		}
		public void setBFromClient(int BFromClient){
			this.BFromClient = BFromClient;
		}
		

	}

