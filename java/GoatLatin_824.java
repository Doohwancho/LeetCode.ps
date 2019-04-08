package String;

import java.util.*;

/*
 * A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters only.

We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)

The rules of Goat Latin are as follows:

If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
For example, the word 'apple' becomes 'applema'.
 
If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
For example, the word "goat" becomes "oatgma".
 
Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
Return the final sentence representing the conversion from S to Goat Latin. 

 

Example 1:

Input: "I speak Goat Latin"
Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
Example 2:

Input: "The quick brown fox jumped over the lazy dog"
Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 */

public class GoatLatin_824 {
	public static void main(String[] args) {
		String S = "abgoat hi";		//주어진 문자열
		Set<Character> vowel = new HashSet<>(Arrays.asList('a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U')); //모음 리스트화
        StringBuilder sb = new StringBuilder();		//주어진 문자열을 goat화 시킬때 필요한 것
        String[] words = S.split(" ");		//주어진 문자열이 한개 이상의 단어일때, 스페이스바 기준으로 띄어서 리스트 화
        

        for (int i = 0; i < words.length; i++) {		
            if (i != 0)     sb.append(" ");				//만약 단어가 "goat hello"처럼 2개 이상의 단어로 구성되어 있는경우, 첫번째 이후 단어부터는 앞에 띄어쓰기를 해줌
            
            String word = words[i];						//스페이스바로 나눠진 문자열들을 for문으로 돈다.
            if (vowel.contains(word.charAt(0))) {		//문자열의 첫번째 인덱스에 모음이 있으면, 해당단어에 ma를 붙여서 stringbuilder에 붙임
                sb.append(word).append("ma");
            } else {									//만약 첫번째 알파벳이 모음으로 시작하지 않은다면, 첫번째 이후 인덱스의 문자열+첫번째 알파벳+ma를 붙여서 stringbuilder에 붙임
                sb.append(word.substring(1)).append(word.charAt(0)).append("ma"); 
            }
            
            for (int j = 0; j <= i; j++)     sb.append('a');	//해당 인덱스의 수마다 'a'를 strinbuilder에 붙여줌
        }
        System.out.println(sb.toString());
	    }
	}
