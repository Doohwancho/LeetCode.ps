package october;

import java.util.Arrays;
import java.util.TreeSet;

public class RemoveDuplicateLetters {

	
	//<Trial01>
	
	//알파벳 순서 살리라는게 주어진 s를 최대한 살리면서 살리라는 말이었구나
	
	//날먹 안되겠네
	
	//ㅎㅎ...ㅈㅅ...ㅋㅋ!!
	
	//아 잠깐만
	
	//You must make sure your result is the smallest in lexicographical order among all possible results.
	
	//Input: s = "cbacdcbc"
	//Output: "acdb"
	
	//이거 왜 "bacd"가 아냐?
	
	//왜 b만 똑떼서 맨 뒤에 붙이는거지?
	
	//"bcad","acdb","adcb","adbc" 이중에 사전적으로 젤 앞에있는게 "acdb"라는 말이구나
	
    public String removeDuplicateLetters(String s) {
        int[] alphabet = new int[26];
        for(char c : s.toCharArray()){
            alphabet[c-'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 26; i++){
            if(alphabet[i] > 0){
                sb.append((char)('a'+i));
            }
        }
        return sb.toString();
    }
    
    
    //<Trial02>
    
    //yet
    
    private String helper(StringBuilder sb, int len, StringBuilder s, int idx, StringBuilder accum){
        StringBuilder curr = s.substring(s.indexOf(sb.indexOf(idx))+1);
        if(curr.length == s.length) return null;
        if(len == 0) return accum.toString();
        
        StringBuilder tmp = new StringBuilder(curr);
        
        while(idx < s.length){
            while(tmp.length != curr.length){
                accum.append(sb.indexOf(idx));
                String rst = helper(sb, len-1, tmp, idx+1, accum);
                if(rst != null) return rst;

                accum.deleteCharAt();
                curr = new StringBuilder(tmp);
                tmp = s.substring(tmp.indexOf(sb.indexOf(idx))+1);
            } 
            idx++;
        }
    }
    
    public String removeDuplicateLetters(String s) {
        StringBuilder sb = new StringBuilder();
        char[] sChar = s.toCharArray();
        Arrays.sort(sChar);
        for(char c :sChar){
            if(sb.indexOf(c) < 0){
                sb.append(c);
            }
        }
        int len = sChar.length;
        
        return helper(sb, len, new StringBuilder(s), 0, new StringBuilder());
    }
    
    
    
    //<Trial03 - Time Limit Exceeded>
    
    //input: "pblspykdpqfhcfcirkrhbbfbnqagshfqrrkcjpsuaytjfwyhjpubttxkkpswuvoiicsnkxiyhsyqrqecsiabhvjfodpkdgcgdceobyfonnurqxvstxkgsagnosvfjgsnylyhbjcrkgaylgxxxmghfbpfqwpplntrrogtkapbpkkwkdxgrfmikdlcftuyywrsnfasxgiw"
    
    //윽 너무 가혹한 인풋이야
    
    //test input:"cbacdcbc"
    
	//accum: 
	//sorted.toString(): abcd
	//s: cbacdcbc
	//--------------
    
	//accum: a
	//sorted.toString(): bcd
	//s: cdcbc
	//--------------
    
	//accum: ab
	//sorted.toString(): cd
	//s: c
	//--------------
    
	//accum: abc
	//sorted.toString(): d
	//s: 
	//s.length() == 0, return null
	//--------------
    
	//accum: ac
	//sorted.toString(): bd
	//s: dcbc
	//--------------
	//accum: acb
	//sorted.toString(): d
	//s: c
	//--------------
    
	//accum: acd
	//sorted.toString(): b
	//s: cbc
	//--------------
    
	//accum: acdb
	//sorted.toString(): 
	//s: c
	//accum.length() == len && sorted.length() == 0 acdb
	//rst != null -> acdb
	//rst != null -> acdb
	//rst != null -> acdb
	//rst != null -> acdb
    
    private String helper(String s, StringBuilder sorted, StringBuilder accum, int len){
        if(accum.length() == len && sorted.length() == 0){
            return accum.toString();
        }
        if(s.length() == 0){
            return null;    
        } 
        
        for(int i = 0; i < sorted.length(); i++){
            StringBuilder filteredSorted = new StringBuilder(sorted);
            filteredSorted.deleteCharAt(i);
            accum.append(sorted.charAt(i));
            
            int exist = s.indexOf(sorted.charAt(i)+"");
            
            if(exist >= 0){
                String rst = helper(s.substring(exist+1), filteredSorted, new StringBuilder(accum), len);
                if(rst != null){
                    return rst;
                }
            } 
            accum.deleteCharAt(accum.length()-1);
        }
        return null;
    }
    
    public String smallestSubsequence(String s) {
        StringBuilder sorted = new StringBuilder();
        char[] sChar = s.toCharArray();
        Arrays.sort(sChar);
        for(char c :sChar){
            if(sorted.indexOf(c+"") < 0){
                sorted.append(c);
            }
        }
        int len = sorted.length();
        return helper(s, sorted, new StringBuilder(), len);
    }
    
	
	//<Trial04>
	
	//String -> Stringbuilder로 최적화
	
	//...하였으나 여전히 TLE
	
	private String helper(StringBuilder s, StringBuilder sorted, StringBuilder accum, int len){

        if(accum.length() == len && sorted.length() == 0) {
            return accum.toString();
        }
        if(s.length() == 0){
            return null;    
        } 
        
        for(int i = 0; i < sorted.length(); i++){
            StringBuilder filteredSorted = new StringBuilder(sorted);
            filteredSorted.deleteCharAt(i);
            accum.append(sorted.charAt(i));
            
            int exist = s.indexOf(sorted.charAt(i)+"");
            
            if(exist >= 0){
                StringBuilder tmp = new StringBuilder(s);
                tmp = tmp.delete(0, exist+1);
                String rst = helper(tmp, filteredSorted, new StringBuilder(accum), len);
                if(rst != null){
                    return rst;
                }
            } 
            accum.deleteCharAt(accum.length()-1);
        }
        return null;
    }
    
    public String smallestSubsequence(String s) {
        StringBuilder sorted = new StringBuilder();
        char[] sChar = s.toCharArray();
        Arrays.sort(sChar);
        for(char c :sChar){
            if(sorted.indexOf(c+"") < 0){
                sorted.append(c);
            }
        }
        return helper(new StringBuilder(s), sorted, new StringBuilder(), sorted.length());
    }
    
    
    
    //<Trial05>
    
    //s -> char[] -> Arrays.sort() -> sorted.indexOf() 해서 StringBuilder에 순서대로 append
    
    //대신, TreeSet로 바꿈
    
    //그러나 treeset을 기존에 char[] sorted처럼 combination 방법을 못찾음.
    
    //넣을땐 쉽게 오름차순 정렬되로 넣어졌으나, 이걸 순차적으로 iterate해서 combination하는 방법을 갈구 못함.
    
    //윽
    
    /*
    StringBuilder sorted = new StringBuilder();
    char[] sChar = s.toCharArray();
    Arrays.sort(sChar);
    for(char c :sChar){
        if(sorted.indexOf(c+"") < 0){
            sorted.append(c);
        }
    }
    
    //to
    
    TreeSet<Character> tr = new TreeSet<>();
    for(char c : s.toCharArray()) {
		tr.add(c);
	}
	*/
        
    //<문제풀이1 Roka>
    
    
    //step01) text에 가장 뒤에서부터 iterate하면서, 주어진 text에서 한번밖에 안나온(distinct)한 애들의 총합과 같은 부분을 true표시해 주면, 
    //		  index 0부터 어디까지 첫 스타트가 유효한지 파악 가능
    
    //step02) i번째가 유효한 애들중에서, 가장 사전적으로 앞에 오는 애의 인덱스를 minIndex에 담음
    
    //step03) 최초 스타트를 담음
    
    //step04) 최초 스타트 이후를 .substring()으로 똑 때서 재귀돌리며 붙임. 붙였을 때, currentResult가 딸려오니 .replace()로 제거.
    
    //어렵긴 했네. dfs가 아니었어. 헛다리를 짚었으면 다른걸 생각해야 하는데 rabbit hole에 매몰됬었던 듯.
    
    //Runtime: 16 ms, faster than 5.03% of Java online submissions for Smallest Subsequence of Distinct Characters.
    //Memory Usage: 39.3 MB, less than 6.52% of Java online submissions for Smallest Subsequence of Distinct Characters.
    
    public String smallestSubsequence(String text) {
        if (text.length() <= 1) {
            return text;
        }
        //step01)
        boolean[] containsAllCharacters = calculateValidSuffixes(text);
        int minIndex = 0;
        
        //step02)
        for (int i = 1; i < text.length(); i++) {
            if (containsAllCharacters[i] && text.charAt(i) < text.charAt(minIndex)) {
                minIndex = i;
            }
        }
        
        //step03)
        String currentResult = Character.toString(text.charAt(minIndex));
        
        //step04) 
        String rest = smallestSubsequence(text.substring(minIndex + 1).replaceAll(currentResult, ""));
        return currentResult + rest;
    }
    
    private boolean[] calculateValidSuffixes(String text) {
        int n = text.length();
        boolean[] containsAllCharacters = new boolean[n];
        
        int charactersCount = (int) text.chars().distinct().count();
        Set<Character> used = new HashSet<>();
        
        for (int i = n - 1; i >= 0; i--) {
            used.add(text.charAt(i));
            containsAllCharacters[i] = used.size() == charactersCount;
        }
        
        return containsAllCharacters;
    }
    
 
    //<문제풀이2 by lee215>
    
    //greedy
    
    //Runtime: 2 ms, faster than 88.73% of Java online submissions for Smallest Subsequence of Distinct Characters.
    //Memory Usage: 37.2 MB, less than 6.52% of Java online submissions for Smallest Subsequence of Distinct Characters.
    public String smallestSubsequence(String S) {
    	//Use a stack to keep the characters for result.
        Stack<Integer> stack = new Stack<>();
        int[] last = new int[26], seen = new int[26];
        
    	//Find the index of last occurrence for each character.
        for (int i = 0; i < S.length(); ++i)
            last[S.charAt(i) - 'a'] = i;
        
    	//Loop on each character in the input string S,
        for (int i = 0; i < S.length(); ++i) {
            int c = S.charAt(i) - 'a';
            if (seen[c]++ > 0) continue;
            
          //if the current character is smaller than the last character in the stack,
          //and the last character exists in the following stream,
            while (!stack.isEmpty() && stack.peek() > c && i < last[stack.peek()])
                seen[stack.pop()] = 0; 	//we can pop the last character to get a smaller result.
            stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        for (int i : stack) sb.append((char)('a' + i));
        return sb.toString();
    }
}
