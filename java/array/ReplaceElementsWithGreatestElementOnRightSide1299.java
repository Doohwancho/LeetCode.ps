/*
	Given an array arr, replace every element in that array with the greatest element among the elements to its right, and replace the last element with -1.
	
	After doing so, return the array.
	
	 
	
	Example 1:
	
	Input: arr = [17,18,5,4,6,1]
	Output: [18,6,6,6,1,-1]
	
	
	
	
	<문제>
	
	Input: arr = [17,18,5,4,6,1]
	Output: [18,6,6,6,1,-1]
	
	주어진 arr를 for문 돌면서, i번째 이후 숫자중 가장 큰 숫자를 i번째에 넣어라.
	
	그리고 맨 마지막은 무조건 -1으로 넣고 arr를 반환하라.
 */

package Array;

public class ReplaceElementsWithGreatestElementOnRightSide1299 {
	
	/*
	//<문제풀이1>
	
	//brute-force
	
	//Runtime: 131 ms, faster than 6.52% of Java online submissions for Replace Elements with Greatest Element on Right Side.
	//Memory Usage: 40.1 MB, less than 100.00% of Java online submissions for Replace Elements with Greatest Element on Right Side.
    public int[] replaceElements(int[] arr) {
        for(int i = 0; i < arr.length-1; i++){
            int maxNum = 0;
            for(int j = i+1; j < arr.length; j++){
                maxNum = Math.max(maxNum, arr[j]);
            }
            arr[i] = maxNum;
        }
        arr[arr.length-1] = -1;
        return arr;
    }
    */
	
    //<문제풀이2>
    
    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Replace Elements with Greatest Element on Right Side.
    //Memory Usage: 40.3 MB, less than 100.00% of Java online submissions for Replace Elements with Greatest Element on Right Side.
    
    public int[] replaceElements(int[] arr) {
        int[] rst = new int[arr.length];
        int maxNum = 0;
        for(int i = arr.length-1; i > 0; i--){ //역순 for문
            maxNum = Math.max(maxNum, arr[i]); //여태까지 최대 숫자를 구해서
            rst[i-1] = maxNum; //rst에 넣고
        }
        rst[rst.length-1] = -1; //마지막에 -1을 넣어줌.
        return rst;
    }
}
