
public class Enigma {
	private final char[][] roterList = 
		{
				"EKMFLGDQVZNTOWYHXUSPAIBRCJ".toCharArray(),
				"AJDKSIRUXBLHWTMCQGZNPYFVOE".toCharArray(),
				"BDFHJLCPRTXVZNYEIWGAKMUSQO".toCharArray(),
				"ESOVPZJAYQUIRHXLNFTGKDCMWB".toCharArray(),
				"VZBRGITYUPSDNHLXAWMJQOFECK".toCharArray(),
				"JPGVOUMFYQBENHZRDKASXLICTW".toCharArray(),
				"NZJHGRCXMYSWBOUFAIVLPEKQDT".toCharArray(),
				"FKQHTLXOCBJSPDZRAMEWNIUYGV".toCharArray(),
		};
	private final char[][] roterTList = 
		{
				"LEYJVCNIXWPBQMDRTAKZGFUHOS".toCharArray(),
				"LEYJVCNIXWPBQMDRTAKZGFUHOS".toCharArray(),
		};
	private final char[][] reflectorList =
		{
				"EJMZALYXVBWFCRQUONTSPIKHGD".toCharArray(),
				"YRUHQSLDPXNGOKMIEBFZCWVJAT".toCharArray(),
				"FVPJIAOYEDRZXWGCTKUQSBNMHL".toCharArray(),
		};
	private final char[][] reflectorThinList =
		{
				"ENKQAUYWJICOPBLMDXZVFTHRGS".toCharArray(),
				"RDOBJNTKVEHMLFCWZAXGYIPSUQ".toCharArray(),
		};
	private final char[][] roter;
	private final char[][] roterT;
	private final char[][] reflector;
	private int[] posRot;
	private int posRef;
	private int[] plugboard;
	Enigma(int roterL, int roterM, int roterR, int reflector)
	{
		roter[R]
	}
}
