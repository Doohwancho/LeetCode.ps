package dynamicProgramming;

import java.util.Arrays;

public class MaximumSubarray53 {

	//<문제풀이1>
	
	//brute-force
	
	//O(N^2)
	
	//Runtime: 299 ms, faster than 5.09% of Java online submissions for Maximum Subarray.
	//Memory Usage: 41.3 MB, less than 5.48% of Java online submissions for Maximum Subarray.
	
    public int maxSubArray(int[] nums) {
    	int rst = Integer.MIN_VALUE;
    	int n = nums.length;
        int[] mem = new int[n];
        Arrays.fill(mem, Integer.MIN_VALUE);
        
        for(int i = 0; i < nums.length; i++){
            int sum = nums[i];
            mem[i] = nums[i] = sum;
            for(int j = i-1; j >= 0; j--){
                sum += nums[j];
                mem[i] = Math.max(mem[i], sum);
            }   
            rst = Math.max(rst, mem[i]);
        }
        
        return rst;
    }
    
    
    //<문제풀이2>
    
    //dp
    
    //O(N)
    
    //Runtime: 1 ms, faster than 46.10% of Java online submissions for Maximum Subarray.
    //Memory Usage: 39.1 MB, less than 42.80% of Java online submissions for Maximum Subarray.
    
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] acc = new int[n+1];
        int[] mem = new int[n+1];
        int rst = Integer.MIN_VALUE;
        
        for(int i = 1; i <= n; i++){
            acc[i] = acc[i-1] + nums[i-1];
            mem[i] = Math.max(mem[i-1]+nums[i-1], Math.max(acc[i] - mem[i-1], nums[i-1]));
            rst = Math.max(rst, mem[i]);
        }
        
        return rst;
    }
    

    //<문제풀이3>
    
    //O(N)
    
    //문제풀이2에서 바보짓함.
    
    //어 이게 왜 됨????????????????????? 
    
    //졸라 고민했는데 사실 필요없던거였음...
    
    //Runtime: 1 ms, faster than 46.37% of Java online submissions for Maximum Subarray.
    //Memory Usage: 38.8 MB, less than 84.92% of Java online submissions for Maximum Subarray.
    
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] mem = new int[n+1];
        int rst = Integer.MIN_VALUE;
        
        for(int i = 1; i <= n; i++){
            mem[i] = Math.max(mem[i-1]+nums[i-1], nums[i-1]);
            rst = Math.max(rst, mem[i]);
        }
        
        return rst;
    }
    
    
    //<문제풀이4 by jianchao-li>
    
    //Divide and Conquer
    
    //크... 똑똑하구마잉
    
    //반으로 쪼갤때마다 쪼갠곳을 중심으로 양옆에 최대 합의 subarray를 구함
    
    //O(NlogN)
    
    //Runtime: 1 ms, faster than 46.37% of Java online submissions for Maximum Subarray.
    //Memory Usage: 38.6 MB, less than 97.81% of Java online submissions for Maximum Subarray.
    
    static int[] nums;

    private int divideAndConquer(int l, int r){
        if(l > r) return Integer.MIN_VALUE;
        int m = (l+r)/2;
        int lmax = divideAndConquer(l, m-1);
        int rmax = divideAndConquer(m+1, r);
        
        int lt = 0, rt = 0;
        for(int i = m-1, sum = 0; i >= l; i--){
            sum += nums[i];
            lt = Math.max(lt, sum);
        }
        for(int i = m+1, sum = 0; i <= r; i++){
            sum += nums[i];
            rt = Math.max(rt, sum);
        }
        
        return Math.max(Math.max(lmax, rmax), lt+rt+nums[m]);
    }
    
    public int maxSubArray(int[] nums) {
        this.nums = nums;
        return divideAndConquer(0, nums.length-1);
    }
    
    
    
    
    //<문제풀이5 by joezie>
    
    //Divide and Conquer
    
    //O(N)
    
    //leftSums[3] + rightSums[0] 얘랑 rightSums[3] + leftSums[1] 얘 부분에서
    
    //서로 영역 침범아녀? 이해하기 힘드네
    
    //Runtime: 1 ms, faster than 46.37% of Java online submissions for Maximum Subarray.
    //Memory Usage: 38.9 MB, less than 57.78% of Java online submissions for Maximum Subarray.
    
    public int maxSubArray(int[] nums) {
        // use divide and conquer: store current max sum of left-attached
        // subarray, max sum of right-attached subarray, max sum, and sum
        // Time: O(n) because f(n) = 2 * f(n/2) + O(1) => f(n)=O(n)
        // Space: O(lgn) because the runtime stack is of maximum depth O(lgn)
    
        int n = nums.length;
        if (n == 0) return 0;
        return helper(0, n - 1, nums)[2];

    }

    private int[] helper(int left, int right, int[] nums) {
        // return values: leftMax, rightMax, max, sum

        if (left == right)
            return new int[]{nums[left], nums[left], nums[left], nums[left]};

        int mid = (left + right) / 2;
        int[] leftSums = helper(left, mid, nums),
              rightSums = helper(mid + 1, right, nums);

        return new int[]{Math.max(leftSums[0], leftSums[3] + rightSums[0]),
                         Math.max(rightSums[1], rightSums[3] + leftSums[1]),
                         Math.max(leftSums[1] + rightSums[0], 
                                  Math.max(leftSums[2], rightSums[2])),
                         leftSums[3] + rightSums[3]};

    }
}

