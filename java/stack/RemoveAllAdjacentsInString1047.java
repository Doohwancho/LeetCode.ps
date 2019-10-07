/*
Share
Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.

We repeatedly make duplicate removals on S until we no longer can.

Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.

 

Example 1:

Input: "abbaca"
Output: "ca"
Explanation: 
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
 

Note:

1 <= S.length <= 20000
S consists only of English lowercase letters.

 */


package Stack;

import java.util.Stack;

class RemoveAllAdjacentsInString1047 {
	
	/*
	//<Trial01 - time limit exceeded>
	
	//"aaaaaaaa" 인풋 시, TLX뜸
	
	//방법은 중복된 알파벳이 두개 연달아 나오면, 해당 인덱스를 remove 함수에 넘겨서, 그부분만 빼고 stringbuilder에 다시 만들어 재귀하는 함수
	
	private static String remove(String S, int idx) {
		StringBuilder sb = new StringBuilder();
		for(int j = 0; j < S.length(); j++) {
			if(j == idx-1 || j == idx) continue;
			else sb.append(S.charAt(j));
		}
        return sb.toString();
	}
	
	
	public static String removeDuplicates(String S) {
		boolean flag = true;
		char prev = 0;
        
		while(flag) {
			for(int i = 0; i < S.length(); i++) {
				if(S.charAt(i) == prev) {
	        		S = remove(S, i);
	        		break;
	        	}
	        	prev = S.charAt(i);
	        	if(i == S.length()-1) flag = false;
			}
		}
        return S;
    }
	
	*/
	/*
	
	//<문제풀이1>
	
	//어짜피 중복되는 단어는 "abbaca" -> "aaca" -> "ca"로 하나,
	
	//밑의 stack로직을 써서, "a" -> "ab" -> "a" -> "" -> "c" -> "ca"로 하나
	
	//밑에께 재귀가 아닌 for문이므로 훨씬 더 빠름
	
	//Runtime: 30 ms, faster than 60.73% of Java online submissions for Remove All Adjacent Duplicates In String.
	//Memory Usage: 38.5 MB, less than 100.00% of Java online submissions for Remove All Adjacent Duplicates In String.
	
	public static String removeDuplicates(String S) {
		Stack<Character> stack = new Stack<>();
		for(char c : S.toCharArray()) {
			if(!stack.empty() && stack.lastElement() == c) stack.pop();
			else stack.push(c);
		}		
		StringBuilder sb = new StringBuilder();
		for(char c : stack) sb.append(c);
		return sb.toString();
	}
	*/
	
	
	
	
	//<문제풀이2 by lee215>
	
	//2 pointer로 품
	
	//j는 단순히 S를 loop할 때 쓰고, i는 중복되는 알파벳이 연속적으로 나왔을 때, 인덱스를 2칸 앞으로 땡겨서 중복된 알파벳을 덮어쓰는 역할을 함.
	
	//마지막에 String type으로 반환 시, new String(char[], start index, last idex)를 사용.
	
	//Runtime: 3 ms, faster than 99.86% of Java online submissions for Remove All Adjacent Duplicates In String.
	//Memory Usage: 38.3 MB, less than 100.00% of Java online submissions for Remove All Adjacent Duplicates In String.
	
	public static String removeDuplicates(String s) {
        int i = 0, n = s.length();
        char[] res = s.toCharArray();
        for (int j = 0; j < n; ++j, ++i) {
            res[i] = res[j];
            if (i > 0 && res[i - 1] == res[i]) // count = 2
                i -= 2;
        }
        return new String(res, 0, i);
    }
	
	
	public static void main(String[] args) {
		String S = "abbaca";
		System.out.println(removeDuplicates(S));
	}
}
