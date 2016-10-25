package PrimeNumber;

import java.util.ArrayList;
import java.util.Random;

public class numberr {
	public static int liczba(int upperLimit) {
		 ArrayList<Integer> primeList = new ArrayList<Integer>();
		 Random rand = new Random();
		 int randomNum;
		        {
		            boolean[] numbersTable = new boolean[upperLimit + 1];
		            for (int i = 2; i * i <= upperLimit; i++) {
		                if (numbersTable[i] == true)
		                    continue;
		                for (int j = 2 * i; j <= upperLimit; j += i)
		                    numbersTable[j] = true;
		            }
		            for (int i = 2; i <= upperLimit; i++)
		                if (numbersTable[i] == false)
		                     primeList.add(i);
		        }
		        randomNum = rand.nextInt(primeList.size()) + 0;   
				return primeList.get(randomNum);
	}
}
