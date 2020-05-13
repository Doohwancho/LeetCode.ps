package mayChallenge;

import java.util.Stack;

public class RemoveKDigits {
	
	/*
	//<Trial01>
	
	//아ㅏㅏㅏㅏㅏ
	
	//String이 말도안되게 긴 숫자가 나왔을때 Integer.parseInt()가 안먹힘. Integer.MAX_VALUE를 넘어가서
	
	//아이고
	
    public String removeKdigits(String num, int k) {
        if(num.length() <= k) return "0";
        char[] n = num.toCharArray();
        
        while(k > 0){
            Character p = null; 
            for(int i = 0; i < n.length; i++){
                if(p == null){
                    p = n[i];
                } else{
                    if(p < n[i]){
                        StringBuilder sb = new StringBuilder();
                        for(int j = 0; j < n.length; j++){
                            if(j!=i){
                                sb.append(n[j]);
                            }
                        }
                        n = Integer.valueOf(sb.toString()).toString().toCharArray();
                        break;
                    } else if(p > n[i]){
                        if(n[i]=='0' && i == 1){
                            n = String.valueOf(n).substring(2).toCharArray();
                            break;
                        }
                        n = String.valueOf(n).substring(1).toCharArray();
                        break;
                    } else if(i == n.length-1){
                        return String.valueOf(n).substring(n.length-1-k).toString();
                    }

                }
            }
            k--;
        }
        return n.length == 0 ? "0" : String.valueOf(n);
    }
    */
    
    //<문제풀이1 by my3m>
    
    //Stack
    
	//Runtime: 5 ms
	//Memory Usage: 39.9 MB
    
    public static String removeKdigits(String num, int k) {
        Stack<Character> chars = new Stack<Character>();
        for(char c : num.toCharArray()) {
            while(chars.size() > 0 && k > 0 && chars.peek() > c) { //i > i+1 이면, i를 빼준다.
                k--;
                chars.pop();
            }
            chars.add(c);
        }
        while(k > 0 && chars.size() > 0) { //2223, k=2 처럼 i <= i+1이라 끝까지 가도 k가 남아있는 경우, 가장 큰 수인 마지막부터 k만큼 뺴준다.
            chars.pop();
            k--;
        }
        int j = 0;
        while(j < chars.size() && chars.elementAt(j) == '0') //0의 갯수 파악
            j++;
        if(j == chars.size()) //만약 남은게 다 0이면 "0"을 반환
            return "0";
        System.out.println(chars);
        System.out.println(j);
        
        StringBuilder sb = new StringBuilder(); 
        while(j < chars.size()) { //num = "10101", k = 2이면, 0앞에있는 1 두개를 빼고 "001"이 남고 j = 2. j는 앞에있는 0의 갯수를 뜻하니, index j이후에 숫자만 모아서 반환해주면 됨 
            sb.append(chars.elementAt(j));
            j++;
        }
        return sb.toString(); 
    }
    
    public static void main(String[] args) {
    	String num = "10101";
    	int k = 2;
    	System.out.println(removeKdigits(num, k));
	}
}
