/*
	Given a list of dominoes, dominoes[i] = [a, b] is equivalent to dominoes[j] = [c, d] if and only if either (a==c and b==d), or (a==d and b==c) - that is, one domino can be rotated to be equal to another domino.
	
	Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length, and dominoes[i] is equivalent to dominoes[j].
	
	 
	
	Example 1:
	
	Input: dominoes = [[1,2],[2,1],[3,4],[5,6]]
	Output: 1
 */

package Array;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

import java.util.Map;
import java.util.HashMap;


public class NumberOfEquivalentDominoPairs1128 {
	
	/*
	//<문제풀이 - trial01>
	//
	//Time Limit Exceeded
	//이중 for문으로 모든 경우의 수를 돌면서 i와 j와 같은지, 혹은 i를 swap했을 때 j와 같은지 비교해 주는 단순 무식한 방법.

	
	public static boolean compare(int[] A, int[] B)
	{
		return A[0] == B[0] && A[1] == B[1];
	}
	
	public static int[] swap(int[] A)
	{
		int tmp = 0;
		tmp = A[0];
		A[0] = A[1];
		A[1] = tmp;
		
		return A;
	}
	
	public static int numEquivDominoPairs(int[][] dominoes) {
        
		int cnt = 0;
		
		for(int i = 0; i < dominoes.length; i++)
		{
			for(int j = i+1 ; j < dominoes.length; j++)
			{
				if(compare(dominoes[i],dominoes[j]) || compare(swap(dominoes[i]),dominoes[j]))
				{
					cnt++;
				}
			}
		}
		
		return cnt;
    }
    */
	
	/*
	//<문제풀이 - trial02>
	
	//lee215의 방법 : {1,2},{2,1} 이 두 리스트가 페어인지 알려면 먼저 정렬 필요.
	
	//정렬 방법은 작은수 * 10 + 큰수 하면 작은수가 십의자리가 되고 큰수가 일의자리가 되어 정렬이 됨
	
	//위의 예시의 경우 12, 12로 정렬이 됨.
	
	//여기서 추가한 방법은 Set에는 중복되는 값이 안들어 간다는 점을 활용하여, set에 안들어 갈 때마다 count를 세고
	
	//count한 숫자는 pair가 되는 여분의 수를 의미하니까,
	
	//그 여분의 수 만큼 고려하면 pair 경우의 수가 몇개가 되는지 반환하는 방법...
	
	//이 될거라고 생각했으나
	
	//만약 input이 [[1,1],[2,2],[1,1],[1,2],[1,2],[1,1]] 처럼
	
	//pair가 되는게 [1,1]도 있고 [1,2]도 있고 둘이 따로 노는 경우
	
	//만약 [1,1]의 여분 pair가 2이고, [1,2]의 여분 pair가 1일때
	
	//[1,1] -> 1+2 = 3
	//[1,2] -> 1   = 1
	
	//도합 4를 도출해야 하는데,
	
	//밑에 구현된건 페어가 달라도 짬뽕해 버리기 때문에
	
	//3 -> 1+2+3 = 6을 반환하여  틀린 답이 구해짐
	
	public static int numEquivDominoPairs(int[][] dominoes) {
		
		Set<Integer> container = new HashSet<>();
		
		int cnt = 0;
		int rst = 0;
		
		for(int i = 0; i < dominoes.length; i++)
		{
			if(!container.add(Math.min(dominoes[i][0], dominoes[i][1]) * 10 + Math.max(dominoes[i][0], dominoes[i][1])))
			{
				cnt++;
			}
		}

        for(int j = 1; j <= cnt; j++)
        {
            rst += j;
        }
		
		return rst;

	}
	*/
	
	// <문제 풀이 by lee215>
	
	//trial02와 방식은 비슷하다. 단, set에 넣지 말고 map에 각 페어마다 몇개씩 있는지 넣고,
	
	//(n * (n-1))/2를 이용하여 combination이 몇개있는지 계산하고 rst에 더해준 숫자를 반환한다.
	
	//Runtime: 8 ms, faster than 82.03% of Java online submissions for Number of Equivalent Domino Pairs.
	//Memory Usage: 55.7 MB, less than 100.00% of Java online submissions for Number of Equivalent Domino Pairs.
	
	public static int numEquivDominoPairs(int[][] dominoes) {
		Map<Integer,Integer> container = new HashMap<>();
		
		int rst = 0;
		
		for(int i = 0; i < dominoes.length; i++)
		{
			int k = Math.min(dominoes[i][0], dominoes[i][1]) * 10 + Math.max(dominoes[i][0], dominoes[i][1]);
			container.put(k, container.getOrDefault(k, 0)+1);
		}
		for(int j : container.values())
		{
			rst += (j * (j-1)) / 2;
		}
		return rst;
		
	}
	
	public static void main(String[] args) {
		int[][] dominoes = {{1,2},{2,1},{3,4},{5,6},{1,2}};
		System.out.println(numEquivDominoPairs(dominoes));
		
	}
}
