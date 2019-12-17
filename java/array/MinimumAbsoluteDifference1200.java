/*
	Given an array of distinct integers arr, find all pairs of elements with the minimum absolute difference of any two elements. 
	
	Return a list of pairs in ascending order(with respect to pairs), each pair [a, b] follows
	
	a, b are from arr
	a < b
	b - a equals to the minimum absolute difference of any two elements in arr
	 
	
	Example 1:
	
	Input: arr = [4,2,1,3]
	Output: [[1,2],[2,3],[3,4]]
	Explanation: The minimum absolute difference is 1. List all pairs with difference equal to 1 in ascending order.
	Example 2:
	
	Input: arr = [1,3,6,10,15]
	Output: [[1,3]]
	Example 3:
	
	Input: arr = [3,8,-10,23,19,-4,-14,27]
	Output: [[-14,-10],[19,23],[23,27]]
	 
	
	Constraints:
	
	2 <= arr.length <= 10^5
	-10^6 <= arr[i] <= 10^6
	
	
	
	<문제>
	
	리스트가 다음과 같이 주어진다. [4,2,1,3,6]
	
	이 리스트의 값들의 차이 중, 최솟값을 먼저 구한다. 1이다.(2-1이든, 3-2든, 4-3이든)
	
	그 후, 어레이 리스트에 두 값의 차이가 방금 구한 최솟값과 같으면 다음과 같이 리스트에 담아 반환한다.
	
	[[1,2],[2,3],[3,4]]
	
	여기에 [4,6]은 안들어가는데, 둘의 차이가 2이기 때문.
 */
package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumAbsoluteDifference1200 {
	/*
	//<문제풀이1>
	
	//먼저 Arrays.sort()로 오름차순 정렬 후, 두 값의 차이의 최솟값을 Math.min()으로 구함.
	
	//이 후, for문 돌면서 i번째 값과 i-1번째 값이 최솟값과 같다면, 리스트에 담고 그 리스트를 rst리스트에 또 담아 반환.
	
	//Runtime: 21 ms, faster than 62.03% of Java online submissions for Minimum Absolute Difference.
	//Memory Usage: 51.5 MB, less than 100.00% of Java online submissions for Minimum Absolute Difference.
    
	public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;
        for(int i = 1; i < arr.length; i++){
            minDiff = Math.min(minDiff, arr[i]-arr[i-1]);
        }
        List<List<Integer>> rst = new ArrayList<>();
        for(int i = 1; i < arr.length; i++){
            if(arr[i]-arr[i-1] == minDiff){
                List<Integer> list = new ArrayList<>();
                list.add(arr[i-1]);
                list.add(arr[i]);
                rst.add(list);
            }
        }
        return rst;
    }
	*/
	
	
	//<문제풀이2 by naveenkothamasu>
	
	//문제풀이1과 큰 틀은 같으나, 두값 차이의 최솟값을 구하기 위해 for문을 한번 더 돌지 말고, 하나로 합친 것.
	
	//Runtime: 19 ms, faster than 99.53% of Java online submissions for Minimum Absolute Difference.
	//Memory Usage: 54.8 MB, less than 100.00% of Java online submissions for Minimum Absolute Difference.
	
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;

        List<List<Integer>> rst = new ArrayList<>();
        for(int i = 1; i < arr.length; i++){
            int diff = arr[i]-arr[i-1];
            if(diff <= minDiff){
                if(diff < minDiff){
                    rst.clear();    
                }
                minDiff = diff;
                List<Integer> list = new ArrayList<>();
                list.add(arr[i-1]);
                list.add(arr[i]);
                rst.add(list);
            }
        }
        return rst;
    }
}
