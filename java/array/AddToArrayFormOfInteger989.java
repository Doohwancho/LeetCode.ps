/*
	For a non-negative integer X, the array-form of X is an array of its digits in left to right order.  For example, if X = 1231, then the array form is [1,2,3,1].
	
	Given the array-form A of a non-negative integer X, return the array-form of the integer X+K.
	
	 
	
	Example 1:
	
	Input: A = [1,2,0,0], K = 34
	Output: [1,2,3,4]
	Explanation: 1200 + 34 = 1234
	Example 2:
	
	Input: A = [2,7,4], K = 181
	Output: [4,5,5]
	Explanation: 274 + 181 = 455
	Example 3:
	
	Input: A = [2,1,5], K = 806
	Output: [1,0,2,1]
	Explanation: 215 + 806 = 1021
	Example 4:
	
	Input: A = [9,9,9,9,9,9,9,9,9,9], K = 1
	Output: [1,0,0,0,0,0,0,0,0,0,0]
	Explanation: 9999999999 + 1 = 10000000000
	
	
	
	<문제>
	
	어레이와 숫자가 다음과 같이 주어진다. A = [2,7,4], K = 181
	
	어레이에 담긴 숫자는 그냥 순서 그대로 읽어서 274이 된다. 이를 K와 합하면 455가 되는데, 455의 각 숫자들을 순서대로 리스트에 담아
	
	[4,5,5]를 반환하면 된다.
	
	
 */

package Array;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class AddToArrayFormOfInteger989 {
	/*
//	<문제풀이 - trial01>
	
//	Wrong Answer
//	
//	Input
//	[9,9,9,9,9,9,9,9,9,9]
//	1
//	Output
//	[-2147483648]
//	Expected
//	[1,0,0,0,0,0,0,0,0,0,0]
	
//	input이 특정 숫자를 초과해 버리면 에러난다.
//	
//	방법은 단순히 A를 for문돌면서 숫자로 바꾸고, K와 합친다음, 자릿수 순서대로 리스트에 집어넣는 방법.
	
	
	
	
	public static List<Integer> addToArrayForm(int[] A, int K) {

		int ciphers = A.length-1;
		int Anum = 0;
		int AK = 0;
		
		//A -> number
		for(int i = 0; i < A.length; i++)
		{
			Anum += A[i] * (Math.pow(10, ciphers));
			ciphers--;
		}
		
		//A + K
		AK = Anum + K;
		
		//(A + K) to List
		List<Integer> rst = new ArrayList<Integer>();
		int ciphers2 = (int)(Math.log10(AK));
		
		while(ciphers2 >= 0)
		{
			rst.add((int)(AK / Math.pow(10, ciphers2)));
			AK -= ((int)(AK / Math.pow(10, ciphers2))) * Math.pow(10, ciphers2) ;
			ciphers2--;
		}
		 
		
		//뒷자리 부터 넣어서 문제에서 요구하는 답이 아님
//		while(AK > 0)
//		{
//			rst.add(AK % 10);
//			AK /= 10;
//		}
		return rst;
    }
    */
//	<문제풀이 by lee215>
	
//	A에 가장 마지막 숫자부터 K를 더해가면서 1의자리 숫자를 리스트에 넣어줌.
//	
//	2자리와 2자리를 더해서 3자리가 된 경우, 십의자리까지는 리스트에 넣을 수 있지만 백에자리는 넣을 수 없음
//	
//	이 때문에 while문으로 한번 더 돌면서 남은 수를 넣어줌.
	
//  단순히 rst.add(0, K)를 안하는 이유는 K가 0이면 더해줄 필요가 없기 때문.
	
//	ArrayList대신 LinkedList를 사용하는 이유는 속도차이 때문.
//	
//	똑같은 코드도 ArrayList는 다음과 같이 걸림
	
	//Runtime: 43 ms, faster than 23.76% of Java online submissions for Add to Array-Form of Integer.
	//Memory Usage: 41 MB, less than 95.86% of Java online submissions for Add to Array-Form of Integer.
	
//	LinkedList는 다음과 같이 걸림
//	
//	Runtime: 5 ms, faster than 88.53% of Java online submissions for Add to Array-Form of Integer.
//	Memory Usage: 40.5 MB, less than 97.93% of Java online submissions for Add to Array-Form of Integer.
	
//	LinkedList는 ArrayList와 비교하여 여러가지 장점을 지니고 있습니다. 
//	LinkedList는 몇 개의 참조자만 바꿈으로써 새로운 자료의 삽입이나 기존 자료의 삭제를 위치에 관계없이 빠른 시간안에 수행 할 수 있습니다. 
//	ArrayList 같은 경우는 O(N)만큼의 연산 속도가 걸리기 때문에 자료의 최대 개수에 영향을 받지만, LinkedList는 그런 제약을 받지 않습니다. 
//	또한 LinkedList는 무한 개수의 자료를 삽입할 수 있는 반면 (메모리의 용량이 무한정하다고 가정할 때), ArrayList는 크기가 한정되어 있기 때문에 결국 포화 상태에 이르게 됩니다. 
//	ArrayList의 크기를 재조정하는 연산을 수행하여 크기를 늘릴 수도 있지만, 상당한 연산량이 요구됩니다.
//	http://www.nextree.co.kr/p6506/
	
//	.add(0, value)를 하면서 값을 삽입할 때 효율성이 LinkedList >>> ArrayList라 그렇다.
	
	
	

	
	
	
	public static List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> rst = new LinkedList<>();
        for(int i = A.length-1 ; i >= 0 ; i--)
        {
        	rst.add(0, (A[i]+K)%10);
        	K = (K + A[i]) / 10;
        }

        while(K > 0)
        {
        	rst.add(0, K % 10);
        	K /= 10;
        }
        
        return rst;
    }
	
	public static void main(String[] args) {
//		int[] A = {2,7,4};
//		int K = 181;
		
		int[] A = {2,1,5};
		int K = 806;
				
		System.out.println(addToArrayForm(A, K));
	}
}
