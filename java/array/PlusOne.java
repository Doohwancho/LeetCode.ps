package julyChallenge;

public class PlusOne {

	//<문제풀이1>
	
	//걍 맨뒤에서부터 1씩 더해줘
	
	//근데 1더했는데 10되면 그 다음 숫자로 넘어가
	
	//그렇게 쭉쭉쭉 가.
	
	//[9,9,9]
	
	//근데 이거 1000되야되는데 끝까지 가면 [0,0,0]되잖아
	
	//그래서 int[4]만들고 앞에 1붙여줘.
	
	//time: O(n)
	//space: O(n)
	
	//Runtime: 1 ms
	//Memory Usage: 39.3 MB
	
    public int[] plusOne(int[] digits) {
        for(int i = digits.length-1; i >= 0; i--){
            if(digits[i]+1 > 9){
                digits[i] = 0;
                if(i == 0){
                    int[] rst = new int[digits.length+1];
                    rst[0] = 1;
                    for(int p = 1; p < rst.length; p++){
                        rst[p] = digits[p-1];
                    }
                    return rst;
                }
            } else {
                digits[i]++;
                return digits;
            }
        }
        return digits;
    }
    
    
    //<문제풀이2 by caikehe>
    
    //생각해보니까
    
    //[1,0,0,0]은 
    
    //int[] rst = new int[4]
    //rst[0] = 1 
    //이랑 같잖아?
    
    //ㅋㅋ 참신하네
    
    public int[] plusOne(int[] digits) {
        int carry = 1;
        for (int i = digits.length-1; i>= 0; i--) {
            digits[i] += carry;
            if (digits[i] <= 9) // early return 
                return digits;
            digits[i] = 0;
        }
        int[] ret = new int[digits.length+1];
        ret[0] = 1;
        return ret;
    }
}
