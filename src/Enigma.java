import java.util.Arrays;

public class Enigma {
	//private final char[] defaultMap = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private final char[][] rotorList = 
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
	private final char[][] turnoverList =
		{
				"Q".toCharArray(),
				"E".toCharArray(),
				"V".toCharArray(),
				"J".toCharArray(),
				"Z".toCharArray(),
				"ZM".toCharArray(),
				"ZM".toCharArray(),
				"ZM".toCharArray()
		};
	private final char[][] rotorTList = 
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
	private int[] 	rotorId;
	private int[][] rotor;
	private int[][] turnover;
	private int[]   rotorT;
	private int[]   reflector;
	private int[] 	posRot;
	private int 	posRotThin;
	private int 	posRef;
	private int[] 	plugboard;
	
	Enigma()
	{
		rotor = new int[3][26];
		rotorT = new int[26];
		reflector = new int[26];
		turnover = new int[3][];
		plugboard = new int[26];
		posRot=new int[3];
		rotorId=new int[3];
		selectRot(0,1,2,-1);
		for(int i=0;i<26;i++)
		{
			plugboard[i]=i;
			reflector[i] = reflectorList[0][i]-'A';
		}
	}
	
	public void selectRot(int rotorL, int rotorM, int rotorR, int rotorThin)
	{
		//may need to throw exception here
		rotorId[0]=rotorL;
		rotorId[1]=rotorM;
		rotorId[2]=rotorR;
		for(int i=0;i<26;i++)
		{
			rotor[0][i]=rotorList[rotorL][i]-'A';
			rotor[1][i]=rotorList[rotorM][i]-'A';
			rotor[2][i]=rotorList[rotorR][i]-'A';
			if(rotorThin<0)
				rotorT[i] = i;
			else
				rotorT[i] = rotorTList[rotorThin][i]-'A';
		}
		for(int i=0;i<3;i++)
		{
			turnover[i]=new int[turnoverList[i].length];
			for(int i1=0;i1<turnoverList[i].length;i1++)
				turnover[i][i1]=turnoverList[i][i1]-'A';
		}
	}
	public void setRotPos(int rotorLoc, int pos)
	{
		int temp[] = new int[26];
		pos=pos%26;
		for(int i=0;i<26;i++)
			temp[i]=rotorList[rotorId[rotorLoc]][(i+pos)%26]-'A';//may optimize this with Linked Ring.
		posRot[rotorLoc] = pos;
		rotor[rotorLoc] = temp;
	}
	public char getChar(char c)
	{
		int temp = c-'A';
		temp = plugboard[temp];
		
		rotateOnce();
		
		temp=rotor[2][temp];
		temp=rotor[1][(26+temp-posRot[2])%26];
		temp=rotor[0][(26+temp-posRot[1])%26];

		temp = reflector[temp];

		for(int i=0;i<26;i++)
			if(rotor[0][i]==temp)
			{
				temp=i;
				break;
			}
		for(int i=0;i<26;i++)
			if(rotor[1][i]==(temp+posRot[1])%26)
			{
				temp=(i)%26;
				break;
			}
		for(int i=0;i<26;i++)
			if(rotor[2][i]==(temp+posRot[2])%26)
			{
				temp=(i)%26;
				break;
			}
		
		return (char)(temp+'A');
	}
	private void rotateOnce()
	{
		for(int i=0;i<turnover[1].length;i++)
			if(turnover[1][i]==posRot[1])//Double step of the middle rotor.
			{
				setRotPos(0,posRot[0]+1);
				setRotPos(1,posRot[1]+1);
				setRotPos(2,posRot[2]+1);
				return;
			}
		for(int i=0;i<turnover[2].length;i++)
			if(turnover[2][i]==posRot[2])//Normal
			{
				setRotPos(1,posRot[1]+1);
				break;
			}
		setRotPos(2,posRot[2]+1);
	}
	public static void main(String args[])
	{
		Enigma enigma = new Enigma();
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		System.out.print(enigma.getChar('A'));
		
	}
}