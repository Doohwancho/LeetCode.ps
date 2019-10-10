package Stack;

import java.util.Stack;

public class BackspaceStringCompare844 {
	
	/*
	//<문제풀이1>
	
	//stack을 활용한 풀이
	
	//S와 T를 각각 다른 stack에 담는데, #이 있으면 이전스택을 지워주는 방식으로 담는다.
	
	//이 후, 이 둘을 비교하여 같으면 true, 아니면 false를 반환한다.
	
	//성능은 그냥 무난함.
	
	//Runtime: 2 ms, faster than 49.22% of Java online submissions for Backspace String Compare.
	//Memory Usage: 34.4 MB, less than 100.00% of Java online submissions for Backspace String Compare.
	
	public static boolean backspaceCompare(String S, String T) {
		Stack<Character> stack_S = new Stack<>();
		Stack<Character> stack_T = new Stack<>();
		
		for(char c : S.toCharArray()){
			if(c == '#' && !stack_S.isEmpty()) stack_S.pop();
			else if(c == '#' && stack_S.isEmpty()) continue;
			else stack_S.push(c);
		}
		for(char c : T.toCharArray()){
			if(c == '#' && !stack_T.isEmpty()) stack_T.pop();
			else if(c == '#' && stack_T.isEmpty()) continue;
			else stack_T.push(c);
		}
		if(stack_S.size() != stack_T.size()) return false;
		
		for(int i = 0; i < stack_S.size(); i++) {
			if(stack_S.get(i) != stack_T.get(i)) return false;
		}
		
		return true;
    }
    
	*/
	/*
	//<Trial01>
	
	//two-pointer로 풀려고했는데 헷깔리네
	
	
	public static boolean backspaceCompare(String S, String T) {
		
		for(int i = S.length()-1, j = T.length()-1; i >= 0 && j >= 0; ) {
			
			if(S.charAt(i) != '#' && S.charAt(i) == T.charAt(j)) {
				i--;
				j--;
			}
			else if(S.charAt(i) == '#' && S.charAt(i) == T.charAt(j)) {
				while(i >= 0 && S.charAt(i) == '#') i--; //#의 갯수만큼 세서 빼줘야하는데, 그냥 #이 나온만큼만 움직였음
				while(j >= 0 && T.charAt(j) == '#') j--;
				i--;
				j--;
			}
			else if(S.charAt(i) == '#'){
				while(i >= 0 && S.charAt(i) == '#') i--;
				i--;
			}
			else if(T.charAt(j) == '#') {
				while(j >= 0 && T.charAt(j) == '#') j--;
				j--;
			}
			else if(S.charAt(i) != T.charAt(j)) return false; // || )
						
			if((i < 0 && j >= 0) || (i >= 0 && j < 0)) return false;

		}
		return true;
	}
	*/
	
	//<문제풀이 by lee215>
	
	//#이 있을때마다 b(=back)을 +1해줌. #이 있을땐 당연히 건너뛰고, 알파벳이 나왔어도 #때문에 쌓여있는 b이 있으면 그만큼 더 건너뜀. 물론 건너뛸때 b를 하나 깜.
	
	//이짓을 b가 0이 될때까지 함.
	
	//T도 똑같이 한다음, 둘을 비교해서 같다면 i--,j--한 다음 위에 방법 반복, 다르면 당연히 false반환하고, 
	
	//다른데도 i랑 j중 하나만 -1이라던가 하면, 둘다 -1인지 확인.
	
	//둘다 -1이란 말은 S,T의 첫번째 인덱스가 같은 알파벳이란 뜻이므로 이상 없음.
	
	//근데 둘중 하나만 -1이란 말은 삔또가 안맞았다는말.
	
	//O(N) time and O(1) space
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Backspace String Compare.
	//Memory Usage: 34.2 MB, less than 100.00% of Java online submissions for Backspace String Compare.
	
	public static boolean backspaceCompare(String S, String T) {
        for (int i = S.length() - 1, j = T.length() - 1;; i--, j--) {
            for (int b = 0; i >= 0 && (b > 0 || S.charAt(i) == '#'); --i)
                b += S.charAt(i) == '#' ? 1 : -1;
            for (int b = 0; j >= 0 && (b > 0 || T.charAt(j) == '#'); --j)
                b += T.charAt(j) == '#' ? 1 : -1;
            if (i < 0 || j < 0 || S.charAt(i) != T.charAt(j)) return i == -1 && j == -1;
        }   
    }
	
	
	public static void main(String[] args) {
		//String S = "a##c";
		//String T = "#a#c";
		
		//String S = "baz#b#c";
		//String T = "ad#c";
		
		String S = "zab##";
		String T = "c#d#";
		
		System.out.println(backspaceCompare(S, T));
	}
}




















