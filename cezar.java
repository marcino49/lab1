package Cezar;

public class cezar {

	public static String encrypt(int key,String str){
		
		String text = "";
		char[] charArray = str.toCharArray();
		key = key % 26;
		for(int i = 0 ; i < charArray.length;i++){
			
			int asciiCode = (int) charArray[i];
			
			if(asciiCode >=65 && asciiCode <=90){
				
				asciiCode += key;
	
			}
			else if(asciiCode >=97 && asciiCode <=122){
				asciiCode += key;
			}
			
			char toChar = (char) asciiCode;
			text += toChar;
		}
		return text;
	}
	
	public static String decrypt(int key,String str){
		
		String text = "";
		char[] charArray = str.toCharArray();
		key = key % 26;
		for(int i = 0 ; i < charArray.length;i++){
			
			int asciiCode = (int) charArray[i];
			
			if(asciiCode >=65 && asciiCode <=90){
				asciiCode -= key;
				
			}
			else if(asciiCode >=97 && asciiCode <=122){
				asciiCode -= key;
			}
			
			char toChar = (char) asciiCode;
			text += toChar;
		}
		return text;
	}
}
