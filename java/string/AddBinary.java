package julyChallenge;

import java.util.LinkedList;
import java.util.List;

public class AddBinary {
	
	//<Trial01>
	
	//input : 
	
	//"10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101"
	//"1101 0100 1011 1011 ? 1000 1111 1001 1000 ? 1010 1000 0110 1011 ? 1010 1000 0011 0110 ? 
	//1100 1011 1011 1100 ? 1100 0000 1101 1110 ? 011"
	
	//너무 긴데?
	
    public String addBinary(String a, String b) {
        return Integer.toBinaryString(Integer.valueOf(a, 2)+Integer.valueOf(b, 2));
    }
    
    //<Trial02>
    
    //걍 더하면 될줄알았는데, 1이랑 0이 96에서 겹쳐버리네? 
    
    //못쓰겠네?
    
    //a = "10";
    //b = "10";
    
	//96~99
//	System.out.println(a.charAt(0) + b.charAt(0) + 1); //1,1 + 1 = 99 (1)
//	System.out.println(a.charAt(0) + b.charAt(0) + 0); //1,1 + 0 = 98 (0)
//	
//	System.out.println(a.charAt(1) + b.charAt(1) + 1); //0,0 + 1 = 97 (1)
//	System.out.println(a.charAt(1) + b.charAt(1) + 0); //0,0 + 0 = 96 (0)
//	
//	System.out.println(a.charAt(0) + b.charAt(1) + 1); //1,0 + 1 = 98 (0)
//	System.out.println(a.charAt(1) + b.charAt(1) + 0); //0,1 + 0 = 96 (1)
	
	//1:96,97,99
	//0:96,98
	
    //얼라 근데 xor하면 되네?
    
	//String a = "01";
	//String b = "01";
	//
	//System.out.println(a.charAt(0) ^ b.charAt(0) ^ 1); //1 
	//System.out.println(a.charAt(0) ^ b.charAt(0) ^ 0); //0
	//
	//System.out.println(a.charAt(1) ^ b.charAt(1) ^ 1); //1
	//System.out.println(a.charAt(1) ^ b.charAt(1) ^ 0); //0
	//
	//System.out.println(a.charAt(0) ^ b.charAt(1) ^ 1); //0
	//System.out.println(a.charAt(1) ^ b.charAt(1) ^ 0); //0
    
    //형변환에서 막힘. ㅏ...
    
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder(a);
        for(int i = a.length()-1, j = b.length()-1, adder = 0; j >= 0; i--, j--){
            int n = a.charAt(i) ^ b.charAt(j) ^ adder;
            sb.setCharAt(j, (char)n); //
            adder = a.charAt(i) + b.charAt(j) + 1 > 97 ? 1 : 0;
        }
        return sb.toString();
    }
    
    
    
    
    
    //<문제풀이1 by jeantimex>
    
    //얜 형변환할때 -'0'을 해준채로 xor처리할거 하고 다시+'0'한 후 (char)했구나
    
    //c = (a + b + c) >> 1; 로 두개 더했을때 올라가는거 처리한게 쥰나 똑똑함
    
    //Runtime: 8 ms
    //Memory Usage: 39.3 MB
    
    public String addBinary(String s1, String s2) {
        int i = s1.length() - 1, j = s2.length() - 1, c = 0;
        String s = "";
        
        while (i >= 0 || j >= 0 || c == 1) {
            int a = (i < 0) ? 0 : s1.charAt(i--) - '0';
            int b = (j < 0) ? 0 : s2.charAt(j--) - '0';
            
            s = (char)('0' + a ^ b ^ c) + s; //
            c = (a + b + c) >> 1;
        }
        
        return s;
    }
    
}
