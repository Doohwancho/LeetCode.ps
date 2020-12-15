package dynamicProgramming;

public class LongestIncreasingSubsequence300 {
	
	//<문제풀이1>
	
	//dp
	
	//O((N+1)*(N/2))
	
	//Runtime: 55 ms, faster than 28.86% of Java online submissions for Longest Increasing Subsequence.
	//Memory Usage: 39.2 MB, less than 11.69% of Java online submissions for Longest Increasing Subsequence.
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] cnt = new int[len];
        cnt[len-1] = 1;
        for(int i = len-2; i >= 0; i--){
            for(int j = i+1; j < len; j++){
                if(nums[j] > nums[i]){
                    cnt[i] = Math.max(cnt[i], cnt[j]+1);
                }
            }
            if(cnt[i] == 0) cnt[i] = 1;
        }
        int rst = 0;
        for(int ele : cnt){
            rst = Math.max(rst, ele);
        }
        return rst;
    }
    
    
    //<문제풀이2 by HelloWorld123456>
    
    //[4,5,6,7,1,2,3]이라는 예제가 있다고 치자.
    
    //그럼 [4,5,6,7]까지는 무난히 가지다가, 1,2,3부터는 앞에 4,5,6의 자리를 한칸씩 뺏음
    
    //[1,2,3,7]까지옴. 기존에 4,5,6,7 4칸 콤보 이상 오지 않기 때문에, 3까지에서 멈춤.
    
    //그래도 기존에 4,5,6,7 4칸콤보는 살아있음.
    
    //만약 [4,5,6,7,1,2,3,0,1,2,3,4] 이 경우라면,
    
    //0이 다시 첫째칸부터 자리 뺏어서
    
    //[0,2,3,7]
    //[0,1,3,7]
    //[0,1,2,7]
    //[0,1,2,3]
    //[0,1,2,3,4]
    
    //이렇게 되겠지.
    
    //binary search를 쓸 수 있는 이유는, subs가 오름차순으로 정렬되었기 때문.
    
    //Runtime: 2 ms, faster than 84.56% of Java online submissions for Longest Increasing Subsequence.
    //Memory Usage: 38.8 MB, less than 25.00% of Java online submissions for Longest Increasing Subsequence.
    
    private void binarySearchReplace(int[] subs, int length, int val){
        int l = 0, r = length;
        while(l < r){
            int m = (l+r)/2;
            if(subs[m] == val){
                return;
            }
            else if(subs[m] < val){
                l = m+1;
            } else {
                r = m;
            }
        }
        subs[l] = val;
    }
    
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] subs = new int[len];
        int size = 0;
        subs[size++] = nums[0];
        for(int i = 1; i < len; i++){
            if(nums[i] > subs[size-1]){
                subs[size++] = nums[i];
            } else {
                binarySearchReplace(subs, size-1, nums[i]);
            }
        }
        return size;
    }
}
