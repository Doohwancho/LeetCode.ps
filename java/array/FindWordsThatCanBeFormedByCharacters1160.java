package Array;

import java.util.Set;
import java.util.HashSet;

public class FindWordsThatCanBeFormedByCharacters1160 {
	
	/*
	//<trial01 - time limit exceeded>
	
	//단순무식방법
	
	//step01 - put chars in a hashset
	
	//step02 - loop words[]
	
	//step03 - deconstruct each word into letters using another for-loop
	
	//step04 - check if all letters are in the hashset. if so, add the length of the word
	
	public static int countCharacters(String[] words, String chars) {
        
		
		Set<Character> container = new HashSet<>();
		
		int rst = 0;
		
		for(int i = 0; i < chars.length(); i++) 
		{ 
			container.add(chars.charAt(i));
		}
		
		for(int j = 0; j < words.length; j++)
		{
			int cnt = 0;
			
			for(int k = 0; k < words[j].length(); k++)
			{
				if(container.contains(words[j].charAt(k)))
				{
					cnt++;
				}
				else
				{
					break;
				}
				if(cnt == words[j].length())
				{
					rst += cnt;
				}
			}
		}
		
		return rst;
    }
	
	*/
	
	/*
	//problem - word가 존나 길면?
	
	//set을 두개만들어서
	
	//char을 set에 넣은것처럼 word도 set에 넣고, 그 둘을 비교해서 다른 원소가 있으면 다음으로 넘어가고, 
	
	//원소 모두 같다면 해당 글자의 길이를 rst에 더해줌
	
	//근데 이것도 time limit exceeded 뜨네?
	
	public static int countCharacters(String[] words, String chars) {
		
		int rst = 0;
		Set<Character> charsContainer = new HashSet<>();
		
		for(int i = 0; i < chars.length(); i++) { charsContainer.add(chars.charAt(i)); }
		
		for(String s : words)
		{
			Set wordsContainer = new HashSet<>();
			for(int j = 0; j < s.length(); j++) { wordsContainer.add(s.charAt(j)); }
			if(charsContainer.containsAll(wordsContainer)) { rst += s.length(); }
		}
		return rst;
		
	}
	*/
	
	//<Solution by GoGoogle2020>
	
	//Set대신 int[]를 이용하면 더 빠르다..
	
//	Runtime: 4 ms, faster than 92.53% of Java online submissions for Find Words That Can Be Formed by Characters.
//	Memory Usage: 37.9 MB, less than 100.00% of Java online submissions for Find Words That Can Be Formed by Characters.
	
	private static boolean identifier(String word, int[] copiedArr)
	{
		for(char c : word.toCharArray())
		{
			if(--copiedArr[c-'a'] < 0)
			{
				return false;
			}
		}
		return true;
	}
	
	public static int countCharacters(String[] words, String chars) {
		int rst = 0;
		
		int[] container = new int[26];
		
		for(char c: chars.toCharArray()) { container[c-'a']++; }
		
		for(String word : words)
		{
			int[] copiedArr = container.clone();
			if(identifier(word, copiedArr)) { rst += word.length(); }
		}
		
		return rst;
	}
	
	
	public static void main(String[] args) {
//		String[] words = {"cat","bt","hat","tree"};
//		String chars = "atach";
		
		String[] words = {"hello","world","leetcode"};
		String chars = "welldonehoneyr";
		
		System.out.println(countCharacters(words, chars));
	}
}
