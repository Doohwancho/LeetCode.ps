/*
	Find the minimum length word from a given dictionary words, which has all the letters from the string licensePlate. Such a word is said to complete the given string licensePlate
	
	Here, for letters we ignore case. For example, "P" on the licensePlate still matches "p" on the word.
	
	It is guaranteed an answer exists. If there are multiple answers, return the one that occurs first in the array.
	
	The license plate might have the same letter occurring multiple times. For example, given a licensePlate of "PP", the word "pair" does not complete the licensePlate, but the word "supper" does.
	
	Example 1:
	Input: licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
	Output: "steps"
	Explanation: The smallest length word that contains the letters "S", "P", "S", and "T".
	Note that the answer is not "step", because the letter "s" must occur in the word twice.
	Also note that we ignored case for the purposes of comparing whether a letter exists in the word.
	Example 2:
	Input: licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]
	Output: "pest"
	Explanation: There are 3 smallest length words that contains the letters "s".
	We return the one that occurred first.
	
	
	
	
	
	<문제>
	
	lincensePlate와 words가 다음과 같이 주어진다.
	
	licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]
	
	licensePlate에 숫자는 제외하고, 문자는 대소문자 구별없이 취급한다.
	
	words의 단어들 중, 그 길이가 가장 짧으면서 licensePlate에 나오는 알파벳을 모두 포함하는 가장 첫번째 단어를 반환하라.
 */

package HashTable;

import java.util.Arrays;

public class ShortestCompletingWord748 {
	
	
	/*
	//<Trial01>
	
	//licensePlate에 나오는 알파벳을 포함하는 횟수가 만약 3개 단어가 모두 같다면, 그중 순서상 가장 먼저 등장하는 것을 반환해야 하나,
	
	//밑의 코드는 그와중에 가장 짧은 것을 반환하여 실패.
	
	//lincensePlate = "Ah71752"
	//words = ["suggest","letter","of","husband","easy","education","drug","prevent","writer","old"]
	
	//에서 husband를 반환해햐아하, license에 나오는 모든 알파벳을 포함하는 단어가 없기 때문에, 마지막 단어인 old를 반환함.
			
			
	 public static String shortestCompletingWord(String licensePlate, String[] words) {
		 StringBuilder sb = new StringBuilder();
		 for(char c : licensePlate.toCharArray()){
			 if(Character.isAlphabetic(c)) {
				 String alphabet = (c+"").toLowerCase();
				 sb.append(alphabet);
			 }
		 }
		 String rst = "";
		 
		 for(String str : words){
			 StringBuilder copiedSb = sb;
			 for(char c : str.toCharArray()){
				 int idx = copiedSb.indexOf(c+"");
				 if(idx >= 0) copiedSb.deleteCharAt(idx);
			 }
			 if(copiedSb.length() == 0 && (str.length() < rst.length() || rst.length() == 0)) rst = str;
		 }
	     
		 return rst;
	    }
	 */
	/*
	//<문제풀이1>
	
	//문제점 찾음. 
	
	//StringBuilder copiedSb = sb;
	
	//하고, copiedSb.deleteCharAt(idx)을 해도, 원래 sb에 것이 지워짐.
	
	//왜냐하면, StringBuilder copiedSb = sb;는 sb의 주솟값만 복사해 오기 때문.
	
	//따라서 완전 .clone()뜨려면, StringBuilder copiedSb = new StringBuilder(sb); 해줘야함.
	
	//Runtime: 13 ms, faster than 25.45% of Java online submissions for Shortest Completing Word.
	//Memory Usage: 38.5 MB, less than 100.00% of Java online submissions for Shortest Completing Word.
	
	 public static String shortestCompletingWord(String licensePlate, String[] words) {
		 StringBuilder sb = new StringBuilder();
		 for(char c : licensePlate.toCharArray()){
			 if(Character.isAlphabetic(c)) {
				 String alphabet = (c+"").toLowerCase();
				 sb.append(alphabet);
			 }
		 }
		 String rst = "";
		 
		 for(String str : words){
			 StringBuilder copiedSb = new StringBuilder(sb);			 
			 for(char c : str.toCharArray()){
				 int idx = copiedSb.indexOf(c+"");
				 if(idx >= 0) copiedSb.deleteCharAt(idx);
			 }
			 if(copiedSb.length() == 0 && (str.length() < rst.length() || rst.length() == 0)) rst = str;
		 }
	     
		 return rst;
	    }
	
	 */
	 
	/*
	
	//<문제풀이 2 by alexander>
	
	//Stringbuilder대신, int[26]사용. 26은 26개의 알파벳을 뜻함.
	
	//7ms 단축. 역시 왠만하면 int[]가 빠르다.
	
	//Runtime: 6 ms, faster than 48.57% of Java online submissions for Shortest Completing Word.
	//Memory Usage: 38.8 MB, less than 100.00% of Java online submissions for Shortest Completing Word.
	
	public static String shortestCompletingWord(String s, String[] words) {
        int[] counts = new int[26]; // count for each chars in licensePlate
        int total = 0; // total number of chars licensePlate
        for (char c : s.toLowerCase().toCharArray()) {
            if ('a' <= c && c <= 'z') {
                counts[c - 'a']++;
                total++;
            }
        }

        String res = null;
        for (String w : words) {
            int n = total;
            int[] cnts = counts.clone();
            for (char c : w.toCharArray())
                if (cnts[c - 'a']-- > 0) n--;
            if (n == 0 && (res == null || w.length() < res.length()))
                res = w;
        }
        return res;
    }
	*/
	
	//<문제풀이3 by caraxin>
	
	//문제풀이2에서 몇가지 개선한 솔루션.
	
	//개선점1) if ('a' <= c && c <= 'z')을 if (Character.isLowerCase(c))로,
	
	//개선점2) ok함수로 total변수를 안써도 됨
	
	//개선점3) 문제풀이 1,2는 각 단어마다, 무조건 int[26]을 loop돌면서 알파벳을 빼줘야 했는데, 문제풀이3에선 조건문에 가장 마지막에 둬서, 몇몇 word는 스킵가능.
	
	//Runtime: 2 ms, faster than 99.48% of Java online submissions for Shortest Completing Word.
	//Memory Usage: 38.2 MB, less than 100.00% of Java online submissions for Shortest Completing Word.
	
	public static String shortestCompletingWord(String licensePlate, String[] words) {
        int[] freq= new int[26];
        for (char c: licensePlate.toLowerCase().toCharArray()) if (Character.isLowerCase(c)) freq[c-'a']++;
        String res="";
        for (String s: words) if ((res.length()==0 || s.length()<res.length()) && ok(Arrays.copyOf(freq, 26), s)) res=s;
        return res;
    }
    public static boolean ok(int[] freq, String s){
        for (char c: s.toCharArray()) freq[c-'a']--;
        for (int f: freq) if (f>0) return false;
        return true;
    }
	
	public static void main(String[] args) {
		//String licensePlate = "1s3 PSt";
		//String[] words = {"step", "steps", "stripe", "stepple"};
		
		String licensePlate = "Ah71752";
		String[] words = {"suggest","letter","of","husband","easy","education","drug","prevent","writer","old"};
				
		System.out.println(shortestCompletingWord(licensePlate, words));
	}
}
