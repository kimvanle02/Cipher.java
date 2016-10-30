import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Cipher {

	/**
	 * Returns plaintext encrypted by the rotation cipher with a shift of
	 * movement.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param plain
	 *            the plain text to be encrypted.
	 * @param shift
	 *            the number of characters in ALPHABET to shift by.
	 * @return returns the encrypted plainText.
	 */
	public static String rotationCipherEncrypt(String plain, int shift, String alphabet) {
		int newIndex = 0;
		String newPlainText = "";
		for (int j = 0; j < plain.length(); j++){
			String eachLetterInPlainText = plain.substring(j, j+1);
			int indexOfPlainTextLetterInAlphabet = alphabet.indexOf(eachLetterInPlainText); 
			newIndex = ((indexOfPlainTextLetterInAlphabet + shift) % alphabet.length());
			eachLetterInPlainText = alphabet.substring(newIndex, newIndex+1);
			newPlainText = newPlainText + eachLetterInPlainText;
		}
		return newPlainText;
	}


	/**
	 * Returns a the result of decrypting cipherText by shiftAmount using the
	 * rotation cipher.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param cipher
	 *            the encrypted text.
	 * @param shift
	 *            the key to decrypt the cipher.
	 * @return returns the decrypted cipherText.
	 */
	public static String rotationCipherDecrypt(String cipher, int shift, String alphabet) {
		String decipheredText = "";
		for (int j = 0; j < cipher.length(); j++){
			String eachLetterInCipherText = cipher.substring(j, j+1);
			int indexOfCipherTextLetterInAlphabet = alphabet.indexOf(eachLetterInCipherText) - shift; 
			while (indexOfCipherTextLetterInAlphabet < 0) {
				 indexOfCipherTextLetterInAlphabet += alphabet.length();
			}
			eachLetterInCipherText = alphabet.substring(indexOfCipherTextLetterInAlphabet, indexOfCipherTextLetterInAlphabet+1);
			decipheredText = decipheredText + eachLetterInCipherText;
		}
		return decipheredText;
	}


	/**
	 * Returns plaintext encrypted by the vigenere cipher encoded with the
	 * String code
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param plain
	 *            the plain text to be encrypted.
	 * @param password
	 *            the code to use as the encryption key.
	 * @return returns the encrypted plainText.
	 */
	public static String vigenereCipherEncrypt(String plain, String password, String alphabet) {
		String newText = "";
		for (int i = 0; i < plain.length(); i++){
			String letterInPlainText = plain.substring(i, i+1);
			int indexOfLetterInPlainText = alphabet.indexOf(letterInPlainText);
			int codePos = i % password.length();
			int indexOfCode = alphabet.indexOf(password.substring(codePos, codePos+1));
			String newLetter = alphabet.substring(indexOfCode + indexOfLetterInPlainText, indexOfCode + indexOfLetterInPlainText + 1);
			newText += newLetter;
		}
		return newText;
	}


	/**
	 * Returns the result of decrypting cipherText with the key code.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param cipher
	 *            the encrypted text.
	 * @param password
	 *            the decryption key
	 * @return returns the decrypted cipherText.
	 */
	public static String vigenereCipherDecrypt(String cipher, String password, String alphabet) {
		String newText = "";
		for (int i = 0; i < cipher.length(); i++){
			String letterInCipherText = cipher.substring(i, i+1);
			int indexOfLetterInCipherText = alphabet.indexOf(letterInCipherText);
			int codePos = i % password.length();
			int indexOfCode = alphabet.indexOf(password.substring(codePos, codePos+1));
			String newLetter = alphabet.substring(indexOfLetterInCipherText - indexOfCode, (indexOfLetterInCipherText - indexOfCode) + 1);
			newText += newLetter;
		}
		return newText;
	}


	public static String rotationCipherCrack(String cipher, String alphabet){
		String deCipheredText = "";
		for (int i = 0; i < alphabet.length(); i++) {
			deCipheredText = CipherOriginal.rotationCipherDecrypt(cipher, i);
			if (CipherOriginal.isEnglish(deCipheredText)) return deCipheredText;
		} 
		return "";
	}
	
	
	public static String vigenereCipherCrackThreeLetter(String cipher, String alphabet){
		String code = "";
		for (int i = 0; i < 3; i++){
			String letterGroup = CipherCracker.getLettersOut(cipher, i, 3);
			code = code + CipherCracker.findCodeLetter(letterGroup, alphabet);
		}
		return (CipherOriginal.vigenereCipherDecrypt(cipher, code, alphabet));
	}
	

	
	public static String vigenereCipherCrack(String cipher, int passwordLength, String alphabet){
		String code = "";
		for (int i = 0; i < passwordLength; i++){
			String letterGroup = CipherCracker.getLettersOut(cipher, i, passwordLength);
			code = code + CipherCracker.findCodeLetter(letterGroup, alphabet);
		}
		return (CipherOriginal.vigenereCipherDecrypt(cipher, code, alphabet));
	}
	
}