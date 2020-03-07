package array;

import java.util.Arrays;

public class ValidTriangleNumber611 {
	
	/*
	//<문제풀이1>
	
	//brute-force
	
	//원래는 Arrays.sort()로 해서, container[2] < container[0]+container[1]로 했었는데(짧은변 두변 합친게 가장 긴변보다 작거나 같으면 삼각형 성립 안되자너), print log찍어보니 뭔가 이상했음
	
	//다시 생각해보니 sort시 단순 값만 바뀌는게 아니라 값이 저장되있는 주솟값도 재정렬되서 이상해진거임.
	
	//그래서 maxNum과 total변수를 써서, 두개중에 가장 큰 변과, 나머지 변의 합을 도출함. 
	
	//이 부분은 야간 위트 있었으나 성능은 개
	
	//Runtime: 1864 ms, faster than 5.01% of Java online submissions for Valid Triangle Number.
	//Memory Usage: 38.4 MB, less than 50.00% of Java online submissions for Valid Triangle Number.
	
	public static int triangleNumber(int[] nums) {
        int[] container = new int[3];
        int rst = 0;
        for(int i = 0; i < nums.length; i++){
            container[0] = nums[i];
            for(int j = i+1; j < nums.length; j++){
                container[1] = nums[j];
                for(int q = j+1; q < nums.length; q++){
                    container[2] = nums[q];
                    int maxNum = 0;
                    int total = 0;
                    for(int ele : container) {
                    	total += ele;
                    	maxNum = Math.max(maxNum, ele);
                    }
                    if(maxNum < total - maxNum) rst++;
                }
            }
        }
        return rst;
    }
	*/
	
	//<문제풀이2 by compton_scatter>
	
	/*
	 * // here is my solution which is **WRONG**
	    public static int triangleNumber1(int[] nums) {
	        Arrays.sort(nums);
	        int count = 0;
	        int n = nums.length;
	        for (int i = 0; i < n - 2; i++) {
	            int l = i + 1;
	            int r = n - 1;
	            while (l < r) {
	                if (nums[i] + nums[l] > nums[r]) {
	                    count += r - l;
	                    l = i + 1;
	                    r--;
	                } else {
	                    l++;
	                }
	            }
	        }
	        return count;
	    }
	    
	    some analysis and explanation:
		our target is a+b>c, so there are two ways to implement 3 pointers method - suppose the three pointers are i, j and k, where i < j < k:
		we fix the first pointer i and make j = i+1, k = n-1, if a[i]+a[j]>a[k], then there are k-j combinations that satisfy a+b>c.
		however, if a[i]+a[j]<=a[k], based on the condition, we have two options: 1) j++, OR 2) k--
		the problems will happen right here - if we are considering all the combinations - the solution is becoming O(N^3) again. if we just simply move j, we are missing the cases where the third pointer between j and k
	 * 
	 */
	
	//Runtime: 6 ms, faster than 82.65% of Java online submissions for Valid Triangle Number.
	//Memory Usage: 38.4 MB, less than 50.00% of Java online submissions for Valid Triangle Number.
    public int triangleNumber(int[] A) {
        Arrays.sort(A);
        int count = 0, n = A.length;
        for (int i=n-1;i>=2;i--) {
            int l = 0, r = i-1;
            while (l < r) {
                if (A[l] + A[r] > A[i]) {
                    count += r-l;
                    r--;
                }
                else l++;
            }
        }
        return count;
    }
	
	
	
}
