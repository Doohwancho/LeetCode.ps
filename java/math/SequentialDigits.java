package september;

import java.util.ArrayList;
import java.util.List;

public class SequentialDigits {
	
	//<문제풀이1>
	
	//1) low가 몇자리 수인지 찾기. 해당 자릿수의 최소세팅하기(if digit = 10000, first = 1234, ones = 1111)
	
	//2) 만약 low가 8511이라, first가 9천몇이 나왔을 경우, 6789보다 더 크므로, 자릿수를 한자리 위로 땡김
	
	//3) first에 ones를 더해가며 rst에 값을 더해줌.
	
	//성능은 좋은데 지저분하네;;
	
	//Runtime: 0 ms
	//Memory Usage: 36.6 MB
	
    private int max(int digit){
        int rst = 9;
        int a = 8;
        int b = 10;
        while(rst < digit/10){
            rst = a*b + rst;
            a--;
            b *= 10;
        }
        return rst;
    }
    
    private int fillSeq(int digit){
        int rst = digit;
        digit /= 10;
        int cnt = 2;
        while(digit != 0){
            rst = rst + cnt * digit;
            digit /= 10;
            cnt++;
        }
        return rst;
    }
    
    private int fillOnes(int digit){
        int rst = digit;
        digit /= 10;
        while(digit != 0){
            rst = rst + digit;
            digit /= 10;
        }
        return rst;
    }
    
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> rst = new ArrayList<>();
        int digit = 1;
        int ones = 1;
        int first = 1;
        int cnt = 2;
        
        //1)
        while(low / digit != 0){
            digit *= 10;
            ones = ones * 10 + 1;
            first = first*10+cnt++;
        }
        ones /= 10;
        first /= 10;
        
        while(first < low){
            first += ones;
        }
        
        //2)
        int max = max(digit);
        if(first > max){
            first = fillSeq(digit);
            ones = fillOnes(digit);
            digit *= 10;
        }
        
        //3)
        while(first < high){
            rst.add(first);
            first += ones;
            
            if(first % 10 == 0){
                first = fillSeq(digit);
                ones = fillOnes(digit);
                digit *= 10;
            }
        }
        
        return rst;
    }
}
