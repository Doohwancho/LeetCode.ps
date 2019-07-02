/*
	We have an array A of integers, and an array queries of queries.
	
	For the i-th query val = queries[i][0], index = queries[i][1], we add val to A[index].  Then, the answer to the i-th query is the sum of the even values of A.
	
	(Here, the given index = queries[i][1] is a 0-based index, and each query permanently modifies the array A.)
	
	Return the answer to all queries.  Your answer array should have answer[i] as the answer to the i-th query.
	
	 
	
	Example 1:
	
	Input: A = [1,2,3,4], queries = [[1,0],[-3,1],[-4,0],[2,3]]
	Output: [8,6,2,4]
	Explanation: 
	At the beginning, the array is [1,2,3,4].
	After adding 1 to A[0], the array is [2,2,3,4], and the sum of even values is 2 + 2 + 4 = 8.
	After adding -3 to A[1], the array is [2,-1,3,4], and the sum of even values is 2 + 4 = 6.
	After adding -4 to A[0], the array is [-2,-1,3,4], and the sum of even values is -2 + 4 = 2.
	After adding 2 to A[3], the array is [-2,-1,3,6], and the sum of even values is -2 + 6 = 4.
	
	
	
	<문제>
	
	A와 queries가 주어진다. queries는 [[1,0],[-3,1],[-4,0],[2,3]] 이런식으로 주어지는데, [1,0]에서 0번째 값은 value를 뜻하고, 1번째 값은 인덱스를 뜻한다.
	
	A를 보면 [1,2,3,4] 이런식으로 되어있다. queries의 1번째 값, 인덱스는 A의 인덱스 값을 뜻하고, value는 해당 인덱스에다가 value를 더하라는 뜻이다.
	
	맨 첫번째인 [1,0]를 보면, A의 0번째 인덱스에 1을 더하라는 뜻이 된다.
	
	따라서 A는 [2,2,3,4]가 된다. 이 때, 여기서 짝수인 수들의 합을 반환하면 된다. 이경우는 2+2+4=8이다.
	
	두번째인 [-3,1]을 보면, A의 1번째 인덱스에 3을 빼라는 뜻이 된다.
	
	따라서 A는 [2,-1,3,4]가 되고, 짝수의 합은 2+4해서 6이 된다.
	
	이렇게 짝수들의 합이 다 모이면 8,6,2,4가 나오는데, 이를 int[]에 담아 리턴하면 된다.
	
	
	
	
	<문제 풀이>
	
	step01) 짝수들의 합을 담아서 리턴할 A와 같은 길이의 rst변수 생성
	
	step02) queries를 for문으로 돌면서, A의 해당 인덱스(queries의 2번째 값)에 값(queries의 1번째 값)을 더해준다.
	
	step03) 값을 더해준 A를 for문으로 돌면서, 짝수인 경우에만 sum이라는 변수에 더해주고, step01에서 선언해 둔 rst에 값을 넣어준다.
 */

package Array;

import java.util.Arrays;

public class SumOfEvenNumbersAfterQueries985 {
	public static int[] sumEvenAfterQueries(int[] A, int[][] queries)
	{
		//step01
		int[] rst = new int[A.length];
		int idx = 0;
		
		//step02
		for(int[] q : queries)
		{
			A[q[1]]+=q[0];
			int sum = 0;
			
			//step03
			for(int p : A) if(p%2 == 0) { sum+=p; }
			rst[idx] = sum;
			idx++;
		}
		return rst;
	}
	
	public static void main(String[] args) {
		int[] A = {1,2,3,4};
		int[][] queries = {{1,0},{-3,1},{-4,0},{2,3}};
		System.out.println(sumEvenAfterQueries(A, queries));
	}
}
