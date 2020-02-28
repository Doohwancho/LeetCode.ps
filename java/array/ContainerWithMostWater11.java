package array;

public class ContainerWithMostWater11 {

	//<문제풀이1>
	
	//two-pointer
	
	//EZ
	
	//Runtime: 2 ms, faster than 95.53% of Java online submissions for Container With Most Water.
	//Memory Usage: 41.6 MB, less than 5.77% of Java online submissions for Container With Most Water.
	
    public int maxArea(int[] height) {
        int rst = 0;
        for(int i = 0, j = height.length-1; i < j; ){
            rst = Math.max(rst, Math.min(height[i],height[j]) * (j-i));
            if(height[i] > height[j]){
                j--;
            } else {
                i++;
            }
        }
        return rst;
    }
}
