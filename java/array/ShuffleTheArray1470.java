package array;

public class ShuffleTheArray1470 {

	
	//<문제풀이1>
	
	//nums = [1,2,3,4,4,3,2,1], n = 4
	
	//i를 0부터, j를 n부터 시작하면
	
	//i-> 1,2,3,4
	
	//j-> 4,3,2,1
	
	//이걸 나란히 담아주면 되제 two pointer로
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Shuffle the Array.
	//Memory Usage: 39.5 MB, less than 100.00% of Java online submissions for Shuffle the Array.
	
    public int[] shuffle(int[] nums, int n) {
        int[] rst = new int[n*2];
        for(int i = 0, j = n, p = 0; i < n; i++, j++){
            rst[p++] = nums[i];
            rst[p++] = nums[j];
        }
        return rst;
    }
    
}
