
public class Enigma {
	private final char[][] roterList = 
		{																	//First introduced
				"EKMFLGDQVZNTOWYHXUSPAIBRCJ".toCharArray(),	//Rotor #I		Enigma I(1930), M3(1934)
				"AJDKSIRUXBLHWTMCQGZNPYFVOE".toCharArray(),	//Rotor #II		Enigma I(1930), M3(1934)
				"BDFHJLCPRTXVZNYEIWGAKMUSQO".toCharArray(),	//Rotor #III	Enigma I(1930), M3(1934)
				"ESOVPZJAYQUIRHXLNFTGKDCMWB".toCharArray(),	//Rotor #IV		M3(1934), Enigma I(1938)
				"VZBRGITYUPSDNHLXAWMJQOFECK".toCharArray(),	//Rotor #V		M3(1934), Enigma I(1938)
				"JPGVOUMFYQBENHZRDKASXLICTW".toCharArray(),	//Rotor #VI		M3(1938)
				"NZJHGRCXMYSWBOUFAIVLPEKQDT".toCharArray(),	//Rotor #VII	M3(1938)
				"FKQHTLXOCBJSPDZRAMEWNIUYGV".toCharArray(),	//Rotor #VIII	M3(1939)
		};
	private final char[][] roterTList = 
		{
				"LEYJVCNIXWPBQMDRTAKZGFUHOS".toCharArray(),	//Rotor Beta
				"FSOKANUERHMBTIYCWLQPZXVGJD".toCharArray(),	//Rotor Gamma
		};
	private final char[][] reflectorList =
		{
				"EJMZALYXVBWFCRQUONTSPIKHGD".toCharArray(),	//Reflector UKW-a, only in Enigma I before WWI
				"YRUHQSLDPXNGOKMIEBFZCWVJAT".toCharArray(),	//Reflector UKW-b
				"FVPJIAOYEDRZXWGCTKUQSBNMHL".toCharArray(),	//Reflector UKW-c
		};
	private final char[][] reflectorThinList =
		{
				"ENKQAUYWJICOPBLMDXZVFTHRGS".toCharArray(),	//
				"RDOBJNTKVEHMLFCWZAXGYIPSUQ".toCharArray(),
		};
	private int[][] roter;
	private int[]   turnover;
	private int[][] roterT;
	private int[]   reflector;
	private int[] posRot;
	private int posRef;
	private int[] plugboard;
	Enigma(int roterL, int roterM, int roterR, int ref)
	{
		roter = new int[3][26];
		reflector= new int[26];
		for(int i=0;i<26;i++)
		{
			roter[0][i]=roterList[roterL][i]-'A';
			roter[1][i]=roterList[roterM][i]-'A';
			roter[2][i]=roterList[roterR][i]-'A';
		}
		posRot=new int[3];
		
	}
	public void selectRot(int roterL, int roterM, int roterR)
	{
		//may need to throw exception here
		for(int i=0;i<26;i++)
		{
			roter[0][i]=roterList[roterL][i]-'A';
			roter[1][i]=roterList[roterM][i]-'A';
			roter[2][i]=roterList[roterR][i]-'A';
		}
		posRot=new int[3];
	}
	void setLocation()
	{
		
	}
}
