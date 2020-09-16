package september;

import java.util.HashSet;
import java.util.Set;

public class MaximumXORofTwoNumbersInArr {

	//<문제풀이1 by laodasb>
	
	//brute-force
	
	//Runtime: 182 ms
	//Memory Usage: 41.9 MB
	
    public int findMaximumXOR(int[] nums) {
        int res=0;
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                res=Math.max(res,nums[i]^nums[j]);
            }
        }
        return res;
    }
    
    //<문제풀이2 by tangx668>
    
    //아씨 헤깔려 
	
	//Mask   = 00000
	//Result = 00000
	
	//For position 4,
	//Mask = 10000
	
	//Integers    Mask      Modified Integer
	//--------------------------------------
	// 3 = 00011 & 10000 =  00000    --check
	//10 = 01010 & 10000 =  00000
	// 5 = 00101 & 10000 =  00000
	//25 = 11001 & 10000 =  10000    --check
	// 2 = 00010 & 10000 =  00000
	// 8 = 01000 & 10000 =  00000
	//--------------------------------------
	//DesiredResult = Result | (1 << 4) = 10000
	//DesiredResult ^ 00000 = 10000 <- which is available in array. stop!
	//Result = DesiredResult = 10000
	
	//For position 3,
	//Mask   = 11000
	
	//Integers    Mask      Modified Integer
	//--------------------------------------
	// 3 = 00011 & 11000 =  00000    --check
	//10 = 01010 & 11000 =  01000
	// 5 = 00101 & 11000 =  00000
	//25 = 11001 & 11000 =  11000    --check
	// 2 = 00010 & 11000 =  00000
	// 8 = 01000 & 11000 =  01000
	//--------------------------------------
	//DesiredResult = Result | (1 << 3) = 11000
	//DesiredResult ^ 00000 = 11000 <- which is available in array. stop!
	//Result = DesiredResult = 11000
	
	//For position 2,
	//Mask   = 11100
	
	//Integers    Mask      Modified Integer
	//--------------------------------------
	// 3 = 00011 & 11100 =  00000
	//10 = 01010 & 11100 =  01000
	// 5 = 00101 & 11100 =  00100    --check
	//25 = 11001 & 11100 =  11000    --check
	// 2 = 00010 & 11100 =  00000
	// 8 = 01000 & 11100 =  01000
	//--------------------------------------
	//DesiredResult = Result | (1 << 2) = 11100
	//DesiredResult ^ 00000 = 11100
	//DesiredResult ^ 01000 = 10100
	//DesiredResult ^ 00100 = 11000 <- which is available in array. stop!
	//Result = DesiredResult = 11100
	
	//For position 1,
	//Mask   = 11110
	
	//Integers    Mask      Modified Integer
	//--------------------------------------
	// 3 = 00011 & 11110 =  00010
	//10 = 01010 & 11110 =  01010
	// 5 = 00101 & 11110 =  00100
	//25 = 11001 & 11110 =  11000
	// 2 = 00010 & 11110 =  00010
	// 8 = 01000 & 11110 =  01000
	//--------------------------------------
	//DesiredResult = Result | (1 << 1) = 11110
	//DesiredResult ^ 00010 = 11100
	//DesiredResult ^ 01010 = 10100
	//DesiredResult ^ 00100 = 11010
	//DesiredResult ^ 00010 = 11100
	//DesiredResult ^ 00100 = 11010
	//This time Result isn't updated
	
	//For position 0,
	//Mask   = 11111
	
	//Integers    Mask      Modified Integer
	//--------------------------------------
	// 3 = 00011 & 11111 =  00011
	//10 = 01010 & 11111 =  01010
	// 5 = 00101 & 11111 =  00101
	//25 = 11001 & 11111 =  11001
	// 2 = 00010 & 11111 =  00010
	// 8 = 01000 & 11111 =  01000
	//--------------------------------------
	//DesiredResult = Result | (1 << 0) = 11101
	//DesiredResult ^ 00011 = 11110
	//DesiredResult ^ 01010 = 10110
	//DesiredResult ^ 00101 = 11000
	//DesiredResult ^ 11001 = 00100
	//DesiredResult ^ 00010 = 11111
	//DesiredResult ^ 01000 = 10101
	//This time Result isn't updated
	
	//Finally, Result = 11100 = 28
	
    
    //Runtime: 37 ms
    //Memory Usage: 41.8 MB
    
    public static int findMaximumXOR(int[] nums) {
        int max = 0, mask = 0;
        for(int i = 31; i >= 0; i--){
            mask = mask | (1 << i); 
            Set<Integer> set = new HashSet<>();
            for(int num : nums){
                set.add(num & mask); 
            }
            
            int tmp = max | (1 << i);
            System.out.println(tmp);
            for (int prefix : set) {  
                if (set.contains(tmp ^ prefix)) {
                    max = tmp; 
                    break; 
                }
            }
        }
        return max;
    }
    
    public static void main(String[] args) {
		System.out.println(findMaximumXOR(new int[] {3,10,5,25,2,8}));
	}
}
