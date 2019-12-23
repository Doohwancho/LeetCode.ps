package Array;

public class FindNumbersWithEvenNumberOfDigits1295 {
	
	/*
	//<문제풀이1>
	
	//int, int[]선에서 해결하는게 잴 빠름. 괜히 string만들어서 .length()메소드 쓰고 지지고 볶는 것 보단.
	
	//Runtime: 2 ms, faster than 35.30% of Java online submissions for Find Numbers with Even Number of Digits.
	//Memory Usage: 37.8 MB, less than 100.00% of Java online submissions for Find Numbers with Even Number of Digits.
	
    public int findNumbers(int[] nums) {
        int cnt = 0;
        for(int i : nums){
            if((i+"").length()%2 == 0) cnt++;
        }
        return cnt;
    }
    */
    
    //<문제풀이2>
	
	//Constraints
	
	//1 <= nums[i] <= 10^5
	
	//를 이용한 것.
    
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Numbers with Even Number of Digits.
    //Memory Usage: 38.6 MB, less than 100.00% of Java online submissions for Find Numbers with Even Number of Digits.
    
    public int findNumbers(int[] nums) {
        int cnt = 0;
        for(int i : nums){
            if(10 <= i && i < 100 || 1000 <= i && i < 10000 || i == 100000) cnt++;
        }
        return cnt;
    }
}
