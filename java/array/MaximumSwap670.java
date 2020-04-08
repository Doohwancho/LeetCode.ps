package array;

public class MaximumSwap670 {
	
	
	
	//<문제풀이1>
	
	//이맛이지
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Swap.
	//Memory Usage: 36.1 MB, less than 25.00% of Java online submissions for Maximum Swap.
	public static int maximumSwap(int num) {
    	int[] n = new int[8];
        int originalNum = num;
    	int idx = 7;
    	boolean flag = false;
    	
        while(num!=0) {
        	int num_ = num%10;
        	num /= 10;
        	n[idx--] = num_;
        }
        int originalIdx = idx+1;
        int idx_ = 6-idx;

        
        for(int i = idx+1; i < 7; i++) {
            int maxVal = 0;
            int j_ = i+1;
            boolean flag2 = false;
            
        	for(int j = i+1; j < 8; j++) {
        		if(n[j] >= maxVal && n[j] > n[i]) { //135같은 경우에, 3을 마주쳤을때 1보다 크다고 바로 바꿔버리면 315를 반환하잖니. 근데 swap시 가장 큰수는 531이잔니. 끝까지 봐야 하잔니. 그래서 maxVal로 뒤에꺼중에 젤 큰수 뽑는거잔니.
                    maxVal = n[j];
                    j_ = j;
                    flag2 = true;
        		}
        	}
            if(flag2){
                swap(n, i, j_);
                int rst = 0;
                while(idx_ >= 0) {
                    rst += n[originalIdx++] * (int)Math.pow(10, idx_--);
                }
                return rst;
            }
        }
        return originalNum;
    }
    
    private static void swap(int[] n, int i, int j) {
    	int tmp1 = n[i];
    	int tmp2 = n[j];
    	n[i] = tmp2;
    	n[j] = tmp1;
    }

}
