/*
	In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
	
	Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.
	
	 
	
	Example 1:
	
	Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
	Output: true
	Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
	Example 2:
	
	Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
	Output: false
	Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
	Example 3:
	
	Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
	Output: false
	Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 

	
	
	<문제>
	
	영어의 사전적 순서는 abcdefg~~인데, 외계인이 사전적 순서는 문제에서 따로 주어진다. order = "hlabcdefgijkmnopqrstuvwxyz" 이것처럼.
	
	단어들이 다음과 같이 주어졌을 때, words = ["hello","leetcode"],  외계인의 사전적 순서에 맞으면 true, 아니면 false를 반환하라. 
	
 */

package HashTable;

public class VerifyingAnAlienDictionary953 {
	
	//<문제풀이>
	
	//원트 퍼펙트. 오져따리.
	
	//자바 내장함수중에 compareTo()라는게 있는데, 원리는 똑같고, order라는 새로운 사전적 순서를 받아서, indexOf()로 인덱스 차이 반환.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Verifying an Alien Dictionary.
	//Memory Usage: 35.8 MB, less than 100.00% of Java online submissions for Verifying an Alien Dictionary.
	
	//두개의 문자열 str1, str2을 비교하여, str1이 사전상으로 str2보다 더 앞에있으면 -, 뒤에있으면 +, str1과 str2이 동일한 숫자이면 0을 반환하는 함수
	//	System.out.println(compareTo("world", "word")); //8 (앞 단어가 사전상 뒤면 +)
	//	System.out.println(compareTo("word", "world")); //-8 (앞 단어가 사전상 앞이면 -)
	//	System.out.println(compareTo("world", "worlda")); //-1
	//	System.out.println(compareTo("world", "world")); //0 
	
	private static int compareTo(String order, String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int lim = Math.min(len1, len2);
        char v1[] = str1.toCharArray();
        char v2[] = str2.toCharArray();
        int k = 0;
        
        while (k < lim) { //공통된 부분까지만 비교하는 이유는 len1 - len2할 때, 어짜피 str2가 n개 문자만큼 더 있으면 len2>len1이라는 말이므로 -반환하기 때문.
            char c1 = v1[k];
            char c2 = v2[k];
            if (c1 != c2) {
                //return c1 - c2;//"word"와 "world"에서 d와 l비교시, l이 d보다 숫자상으로는 더 크므로, c1-c2이 -값을 가지게 됨
            	return order.indexOf(c1) - order.indexOf(c2);
            }
            k++;
        }
        return len1 - len2; //만약 동일한 숫자라면 0반환
    }
	
	public static boolean isAlienSorted(String[] words, String order) {
		
		for(int i = 1; i < words.length; i++)
		{
			if(compareTo(order, words[i-1], words[i]) > 0) return false;
		}
		return true;
    }
	
	public static void main(String[] args) {
		String[] words = {"word","world","row"};
		String order = "worldabcefghijkmnpqstuvxyz";
		
		System.out.println(isAlienSorted(words, order));
	}
}
