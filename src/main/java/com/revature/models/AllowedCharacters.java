package com.revature.models;

import java.util.Arrays;
import java.util.HashSet;

public final class AllowedCharacters {
	
	private static final Character[] alphabetUsername = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
			'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
			,'0','1','2','3','4','5','6','7','8','9',
			'!','@','#','$','%','^','&','*','(',')','-','_','=','+','[','{',']','}',';',':',',',',','<','.','>','/','?','`','~','\'','\\','\"','|'};
	public static final HashSet<Character> allowedCharactersUsername = new HashSet<Character>(Arrays.asList(alphabetUsername));
	
	
	private static final Character[] alphabetPassword = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
			'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
			,'0','1','2','3','4','5','6','7','8','9',
			'!','@','#','$','%','^','&','*','(',')','-','_','=','+','[','{',']','}',';',':',',',',','<','.','>','/','?','`','~','\'','\\','\"','|'};
	public static final HashSet<Character> allowedCharactersPassword = new HashSet<Character>(Arrays.asList(alphabetPassword));
	
	
	private static final Character[] alphabetFirst = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
			'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
			,' ','-','\''};
	public static final HashSet<Character> allowedCharactersFirst = new HashSet<Character>(Arrays.asList(alphabetFirst));
	
	
	private static final Character[] alphabetLast = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
			'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
			,' ','-','\''};
	public static final HashSet<Character> allowedCharactersLast = new HashSet<Character>(Arrays.asList(alphabetLast));
	
	
	private static final Character[] alphabetEmail = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
			'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
			,'0','1','2','3','4','5','6','7','8','9',
			'_','-','@','.','!','#','$','%','*','+','/','=','?','^','`','{','|','}','~','\''}; 
	public static final HashSet<Character> allowedCharactersEmail = new HashSet<Character>(Arrays.asList(alphabetEmail));
}
