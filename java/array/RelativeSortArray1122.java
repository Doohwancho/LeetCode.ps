/*
	Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.

	Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.  Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.
	
	 
	
	Example 1:
	
	Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
	Output: [2,2,2,1,4,3,3,9,6,7,19]
	
	
	<문제>
	
	arr1과 arr2가 다음과 같이 주어진다.
	
	arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
	
	arr1을 재정렬 하는데, 재정렬의 기준은 arr2의 나온 숫자들의 순서이다.
	
	위를 예시로들면, arr2에서 2가 먼저 나왔고 arr1에서 2가 총 3번 등장하므로 [2,2,2]를 먼저 넣고, 다음에 1이 1번 나왔으므로 [2,2,2,1] ...
	
	이런식으로 정렬하다 arr2에 나오지 않은 숫자가 arr1에 나왔으면, 해당 숫자들 끼리만 오름차순 정렬해서 넣어준다.
	
	
	
	<문제풀이 - trial01>
	
	2중 for문으로 arr2를 for문으로 돌면서 arr1에도 나왔으면 rst라는 변수에 넣고 해당값을 -1으로 바꿔준 다음, 맨 마지막에 -1이 아닌값들만 Intstream.of().sorted()로 오름차순 정렬 후, 순차적으로 넣으려고 했으나
	
	Intstream.of()를 사용하면 int[]에서 Intstream type으로 바뀌는데, 여기서 for문을 쓰려면 .foreach()를 써야함
	
	그런데 .foreach()에서 막힘.
	
	.forEach(i -> if(i != -1) rst[idx++] = i);
	
	인터넷 예제따라 요롷게 해봤는데 안됌.. ㅜㅜ
	
 */


/*
package Array;

import java.util.*;
import java.util.stream.IntStream; 


public class RelativeSortArray1122 {
	
	public static int[] relativeSortArray(int[] arr1, int[] arr2) 
	{
        int[] rst = new int[arr1.length];
        int idx = 0;
        
        for(int i = 0; i < arr2.length; i++)
        {
        	for(int j = 0; j < arr1.length; j++)
        	{
        		if(arr2[i] == arr1[j])
        		{
        			rst[idx] = arr2[i];
        			arr1[j] = -1; 
        			idx++;
        		}
        	}
        }
        
        //forEach()에서 실패..
        //IntStream.of(arr1).sorted().forEach(i -> if(i != -1) rst[idx++] = i);

        for(int p = 0; p < arr1.length; p++)
        {
        	if(arr1[p] != -1)
        	{
        		rst[idx] = arr1[p];
        		idx++;
        	}
        }
        return rst;		
    }
	
	public static void main(String[] args) {
		//int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19}, arr2 = {2,1,4,3,9,6};
		int[] arr1 = {28,6,22,8,44,17}, arr2 = {22,28,8,6};
		System.out.println(relativeSortArray(arr1, arr2));
	}
}
*/


/*
	<문제풀이 by Rock>
	
	arr1에 값이 각각 몇번 나왔는지 count[]를 만들어 빈도수를 센다.
	
	다음 arr2에 나온값들을 순서대로 rst에 넣는데, 이때 arr1에 카운트값한 값들을 하나씩 빼줘가며 넣는다
	
	그러면 남은 숫자들은 자연스럽게 arr2에 나오지 않은 값이 된다.
	
	그 값들도 방금처럼 for문으로 순서대로 돌면서 count를 하나씩 차감하며 rst에 넣어준다.
	
	Runtime: 0 ms, faster than 100.00% of Java online submissions for Relative Sort Array.
	Memory Usage: 35.9 MB, less than 100.00% of Java online submissions for Relative Sort Array.
 */

package Array;

public class RelativeSortArray1122 {
	
	public static int[] relativeSortArray(int[] arr1, int[] arr2) {
		int count[] = new int[1001];
		int rst[] = new int[arr1.length];
		int idx = 0;
		
		for(int i : arr1)
		{
			count[i]++;
		}
		for(int j : arr2)
		{
			while(count[j]-- > 0)
			{
				rst[idx++] = j;
			} 
		}
		for(int k = 0; k < 1001; k++)
		{
			while(count[k]-- > 0)
			{
				rst[idx++] = count[k];
			} 
		}
		return rst;
	}
	
	
	
	public static void main(String[] args) {
		int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19}, arr2 = {2,1,4,3,9,6};
		System.out.println(relativeSortArray(arr1, arr2));
	}
}


