package september;

public class MaximumProductSubarray {
	
	//<Trial01 - Memory Limit Exceeded>
	
	//2차원 배열을 1차원으로 줄이면 되겠네?
	
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[][] matrix = new int[n][n];
        int rst = Integer.MIN_VALUE;
        
        for(int p = 0; p < n; p++){
            matrix[p][p] = nums[p];
            rst = Math.max(rst, nums[p]);
        }
        
        for(int i = 0; i < n-1; i++){
            for(int j = i+1; j < n; j++){
                matrix[i][j] = matrix[i][j-1] * nums[j];
                rst = Math.max(rst, matrix[i][j]);
            }
        }
        return rst;
    }
    
    
    //<문제풀이1>
    
    //brute-force
    
    //O((N^2)/2)
    
    //Runtime: 230 ms
    //Memory Usage: 39.2 MB
    
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] matrix = new int[n];
        int rst = matrix[0] = nums[0];
        
        for(int i = 0; i < n-1; i++){
            for(int j = i+1; j < n; j++){
                matrix[j] = matrix[j-1] * nums[j];
                rst = Math.max(rst, matrix[j]);
            }
            if(i+1 < n){
                matrix[i+1] = nums[i+1];
                rst = Math.max(rst, matrix[i+1]);
            }
        }
        return rst;
    }
    
    
    //<문제풀이2 by lee215>
    
    //압도적인 성능차이네 ㅋㅋㅋ
    
    //일단 A에 숫자가 모두 양수면, 다 곱한값을 반환하면 됨.
    
    //만약 iterate하다가 0이 나오면, 0이나온쪽 리셋하고 다음거부터 다시 누적으로 곱해줌
    
    //Q. 만약 [-1,2,2,2, ...]면, l이 마이너스부터 곱하니까 안되는거 아냐?
    
    //그러면 r이 오른쪽에서부터 다 곱하면서 들어와서 괜찮음.
    
    //Q. 그럼 [-1,2,2,2,-1]은 어쩔껀데? l도 마이너스부터 곱하고 r도 마이너스부터 곱하잖아?
    
    //마이너스가 두개면 플러스 되니까 괜찮음
    
    //Q. 그럼 [-1,2,2,2,-1,2,2,-1]은?
    
    //양끝단에 마이너스값 말고, 중간에 마이너스가 홀수개 나온다면,  홀수개 기준으로 왼쪽에 젤 큰 누적값이 l이고, 오른쪽 젤 큰 누적값이 r이니까,
    
    //Math.max(l,r)하면 됨.
    
    //양끝안에 마이너스값 말고, 중간의 마이너스가 짝수개 나온다면, 마이너스를 짝수번 곱하면 어짜피 플러스니까 다 곱해주면 됨.
    
    //머리 개좋네 와 씨 ㅋㅋㅋㅋ
    
    //Runtime: 1 ms
    //Memory Usage: 39.3 MB
    
    public int maxProduct(int[] A) {
        int n = A.length, res = A[0], l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            l =  (l == 0 ? 1 : l) * A[i];
            r =  (r == 0 ? 1 : r) * A[n - 1 - i];
            res = Math.max(res, Math.max(l, r));
        }
        return res;
    }
}
