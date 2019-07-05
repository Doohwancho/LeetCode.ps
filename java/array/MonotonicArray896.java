/*
	An array is monotonic if it is either monotone increasing or monotone decreasing.
	
	An array A is monotone increasing if for all i <= j, A[i] <= A[j].  An array A is monotone decreasing if for all i <= j, A[i] >= A[j].
	
	Return true if and only if the given array A is monotonic.
	
	 
	
	Example 1:
	
	Input: [1,2,2,3]
	Output: true
	Example 2:
	
	Input: [6,5,4,4]
	Output: true
	Example 3:
	
	Input: [1,3,2]
	Output: false
	Example 4:
	
	Input: [1,2,4,5]
	Output: true
	Example 5:
	
	Input: [1,1,1]
	Output: true
	
	
	<문제>
	
	리스트가 주어지면, 리스트 안의 원소들이 순차적으로 늘어나거나 순차적으로 줄어들면 true, 아니면 false를 반환한다.
	
	단, [1,2,2,3]나 [6,5,4,4]와 같이, 크거나 같은것/작거나 같은것도 인정된다.
	
	
	
	<문제풀이>
	
	변수 up, down을 boolean형태로 선언한다.
	
	for문을 돌면서, 이전원소보다 커지면 up에 true를, 작아지면 down에 true를 넣고, 같은숫자가 [1,1,1]처럼 반복되었다면 continue로 넘어간다.
	
	값을 반환시, 커지고 작아진 것 두개 모두가 나타난 경우만 false를 반환하고, 나머지는 true를 반환해준다.
	
 */
package Array;

public class MonotonicArray896 {
	public static boolean isMonotonic(int[] A) 
	{
        if(A.length < 1) return false;

        boolean up = false;
        boolean down = false;
        
        for(int i = 1; i < A.length; i++)
        {
        	if(A[i]>A[i-1])
        	{
        		up = true;
        	}
        	else if(A[i]<A[i-1])
        	{
        		down = true;
        	}
        	else
        	{
        		continue;
        	}
        }
		return (up && down) ? false : true;
    }
	
	public static void main(String[] args) {
		//int[] A = {1,2,2,3};
		int[] A = {1,2,1,3};
		//int[] A = {2,2,2,2};
		System.out.println(isMonotonic(A));
	}
}
