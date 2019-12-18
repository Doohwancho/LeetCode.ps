/*
	Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time.
	
	Return that integer.
	
	 
	
	Example 1:
	
	Input: arr = [1,2,2,6,6,6,6,7,10]
	Output: 6
	 
	
	Constraints:
	
	1 <= arr.length <= 10^4
	0 <= arr[i] <= 10^5
	
	
	
	
	<문제>
	
	오름차순 정렬된 어레이가 다음과 같이 주어진다.
	
	arr = [1,2,2,6,6,6,6,7,10]
	
	이중 , 전체어레이의 1/4보다 더 많이 등장하는 숫자가 유일하게 한 개있는데, 그 숫자를 반환하라.
 */

package Array;

public class ElementAppearingMoreThanQuarterInSortedArray1287 {
	/*
	//<문제풀이1>
	
	//Runtime: 1 ms, faster than 52.26% of Java online submissions for Element Appearing More Than 25% In Sorted Array.
	//Memory Usage: 38.9 MB, less than 100.00% of Java online submissions for Element Appearing More Than 25% In Sorted Array.
    public int findSpecialInteger(int[] arr) {
        int[] container = new int [100000];
        int req = arr.length/4;
        for(int i : arr){
            if(++container[i]>req){
                return i;
            }
        }
        return -1;
    }
    */
	
	//<문제풀이2 by Poorvank>
	
	//어레이의 1/4 지점에서 시작하여 해당 숫자가 어레이에서 언제 첫번째로 등장하고, 언제 마지막으로 등장하는지 binary search로구해서
	
	//마지막 등장한 인덱스 - 처음 등장한 인덱스+1이 어레이의 길이의 1/4이 넘어가는지 확인하는 방법.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Element Appearing More Than 25% In Sorted Array.
	//Memory Usage: 39 MB, less than 100.00% of Java online submissions for Element Appearing More Than 25% In Sorted Array
    
    public int findSpecialInteger(int[] a) {
        int len = a.length/4;
        
        for(int idx = Math.max(0, len-1); idx < a.length; idx += len) {
            if(getCount(a, a[idx]) > len) return a[idx];
        }
        return -1;
    }
    
    int getCount(int[] a, int v) {
        return uppperIdx(a, v) - lowerIdx(a, v) + 1;
    }
    
    int uppperIdx(int[] a, int v) {
        int l = 0, h = a.length-1;
        while(l < h) {
            int m = l + (h-l+1)/2;
            if(a[m] > v) {
                h = m-1;
            } else {
                l = m;
            }
        }
        return l;
    }
    
    int lowerIdx(int[] a, int v) {
        int l = 0, h = a.length-1;
        while(l < h) {
            int m = l + (h-l)/2;
            if(a[m] < v) {
                l = m+1;
            } else {
                h = m;
            }
        }
        return l;        
    }
}
