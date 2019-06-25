/*
 * 
Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true

 */

/*
 * 문제
 * 
 * 문자열 ransomNote와 magazine이 주어지면, magazine의 알파벳으로 ransomeNote를 만들 수 있다면 ture, 아니면 false를 반환하라.
 * 
 * 
 * 
 * 
 * 문제풀이
 * 
 * magazine의 각 알파벳의 숫자를 카운팅하고, ransomeNote의 각 알파벳을 for문으로 돌면서, 해당 알파벳이 나올때 마다 아까 카운팅 한 숫자를 하나씩 지워나간다.
 * 만약 for문을 도는 중, 해당 알파벳이 magazine을 카운팅한 리스트에 존재하지 않으면 false를 반환한다.
 * 
 * 
 * 
 */

package String;


public class RansomNote383 {
	
	public static boolean canConstruct(String ransomNote, String magazine)
	{
		int[] counter = new int[26];
		char[] charRansomeNote = magazine.toCharArray();
		
		for(int i = 0; i < magazine.length(); i++)
		{
			counter[charRansomeNote[i]-'a']++;
		}
		
		for(char k: ransomNote.toCharArray())
		{
			if(counter[k-'a'] > 0)
			{
				counter[k-'a']--;
			}
			else
			{
				return false;
			}
		}return true;
	}
	public static void main(String[] args) {
		String a = "aa";
		String b = "aab";
		
		System.out.println(canConstruct(a,b));
	}

}
