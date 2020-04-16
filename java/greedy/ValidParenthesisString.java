package thirtyDayChallenge;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class ValidParenthesisString {

	/*
	//<Trial01>
	
	//"(*()" 에서 막히네. 
	
	//"(())" 이런식으로 감싸는 것만 나올 줄 알았는데, ()() 이것도 오케이라면, two pointer쓰면 안되겠네. 
    public boolean checkValidString(String s) {
        char[] c = s.toCharArray();
        for(int i = 0,j = s.length()-1; i < j; i++, j--){
            if(c[i]==')' || c[j]=='(') return false;
        }
        return true;
    }
    */
	
	/*
	//<Trial02>
	
	//"(*()" 맞춤형으로 ()()()같은거 위주로 만들었는데, 이번엔 반대로
	
	//"(*))" 같이 (((())))같은 것에서 막힘. 에고
    
    public boolean checkValidString(String s) {
        Stack<Character> st = new Stack<>();
        for(char c : s.toCharArray()){
            if(st.isEmpty()){
                st.add(c);
            } else{
                if(c=='*' || (c==')' && (st.peek()=='*' || st.peek()=='('))){
                    st.pop();
                } else{
                    return false;
                }
            }
        }
        return true;
    }
    */
	
	/*
	//<Trial03>
	
	//deque를 이용해봤는데
	
	//"((*)****)"처럼 *가 여러개 나오는건 해당이 안되네 ㅠㅠ
    
	public boolean checkValidString(String s) {
        Deque<Character> dq = new ArrayDeque<>();
        
        for(char c : s.toCharArray()){
            if(dq.isEmpty()) {
            	dq.add(c);
            } else {
            	if(dq.getLast() == '(' && c == ')') {
            		dq.pollLast();
            	} else {
            		dq.add(c);
            	}
            }
        }
        
        while(!dq.isEmpty()){
            Character first = dq.pollFirst();
            Character last = null;
            if(dq.isEmpty()) {
            	if(first != '*') return false;
            } else {
            	last = dq.pollLast();
            }
            if(last != null && (first==')' || last=='(')) return false;
        }
        return true;
    }
    */
	
	//<문제풀이 1 by hiepit>

	//58 / 58 test cases passed.
	//Status: Accepted
	//Runtime: 0 ms
	//Memory Usage: 37.3 MB
	
	public static boolean checkValidString(String s) {
        int cmin = 0, cmax = 0; // open parentheses count in range [cmin, cmax]
        for (char c : s.toCharArray()) {
            if (c == '(') {
                cmax++;
                cmin++;
            } else if (c == ')') {
                cmax--;
                cmin--;
            } else if (c == '*') {
                cmax++; // if `*` become `(` then openCount++
                cmin--; // if `*` become `)` then openCount--
                // if `*` become `` then nothing happens
                // So openCount will be in new range [cmin-1, cmax+1]
            }
            if (cmax < 0) return false; // Currently, don't have enough open parentheses to match close parentheses-> Invalid
                                        // For example: (()))(
            cmin = Math.max(cmin, 0);   // It's invalid if open parentheses count < 0 that's why cmin can't be negative
            System.out.println(cmin);
        }
        
        return cmin == 0; // Return true if can found `openCount == 0` in range [cmin, cmax]
    }
	
	public static void main(String[] args) {
		String s = "(**)";
		System.out.println(checkValidString(s));
	}
}
