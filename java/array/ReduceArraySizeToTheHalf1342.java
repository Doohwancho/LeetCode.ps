/*
	Given an array arr.  You can choose a set of integers and remove all the occurrences of these integers in the array.
	
	Return the minimum size of the set so that at least half of the integers of the array are removed.
	
	 
	
	Example 1:
	
	Input: arr = [3,3,3,3,5,5,5,2,2,7]
	Output: 2
	Explanation: Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the size of the old array).
	Possible sets of size 2 are {3,5},{3,2},{5,2}.
	Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5] which has size greater than half of the size of the old array.
	Example 2:
	
	Input: arr = [7,7,7,7,7,7]
	Output: 1
	Explanation: The only possible set you can choose is {7}. This will make the new array empty.
	Example 3:
	
	Input: arr = [1,9]
	Output: 1
	Example 4:
	
	Input: arr = [1000,1000,3,7]
	Output: 1
	Example 5:
	
	Input: arr = [1,2,3,4,5,6,7,8,9,10]
	Output: 5
	 
	
	Constraints:
	
	1 <= arr.length <= 10^5
	arr.length is even.
	1 <= arr[i] <= 10^5
	
	
	
	
	<문제>
	
	arr = [3,3,3,3,5,5,5,2,2,7]
	
	여기서 특정 숫자를 선택해 전체를 지울 수 있다(ex. 3을 선택하면 [5,5,5,2,2,7], 3과 7을 선택하면 [5,5,5,2,2]),
	
	특정 숫자(or 숫자들)를 선택하여 최소 원래 arr사이즈의 절반 이상을 지워야 한다면, 지워야 하는 숫자의 최소갯수를 반환하라. 
 */
package array;

import java.util.Arrays;

public class ReduceArraySizeToTheHalf1342 {
	
	//<문제풀이1>
	
	//	Constraints:
	//1 <= arr.length <= 10^5
	//arr.length is even.
	//1 <= arr[i] <= 10^5
	
	//여기서 arr의 범위가 1부터 10^5까지라는 것을 이용해 사이즈 100001짜리 리스트를 만든다.
	
	//arr를 for문돌면서 숫자의 빈도수를 사이즈 100001짜리 리스트에 기록하고
	
	//Arrays.sort()를 하여, 오름차순정렬해서 가장 빈도수가 큰 수가 맨 뒤로 가도록 설정.
	
	//가장 빈도수가 큰 숫자부터 하나씩 더해가면서 arr.size/2보다 같거나 큰지 확인.
	
	//arr.size/2보다 같거나 큰게 지워야하는 최소숫자므로, 100001-i를 반환.
	
	//Runtime: 24 ms, faster than 100.00% of Java online submissions for Reduce Array Size to The Half.
	//Memory Usage: 56.5 MB, less than 100.00% of Java online submissions for Reduce Array Size to The Half.
	
    public int minSetSize(int[] arr) {
        int minSize = arr.length/2;
        int[] container = new int[100001];
        for(int element : arr){
            container[element]++;
        }
        Arrays.sort(container);
        
        for(int i = 100001-1, rst = 0; i >= 0; i--){
            rst += container[i];
            if(rst >= minSize){
                return 100001-i;
            }
        }
        return minSize;
    }

}
