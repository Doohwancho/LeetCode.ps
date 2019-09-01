/*
	In a array A of size 2N, there are N+1 unique elements, and exactly one of these elements is repeated N times.
	
	Return the element repeated N times.
	
	 
	
	Example 1:
	
	Input: [1,2,3,3]
	Output: 3
	Example 2:
	
	Input: [2,1,2,5,3,2]
	Output: 2
	Example 3:
	
	Input: [5,1,5,2,5,3,5,4]
	Output: 5
	
	
	
	<문제>
	
	주어진 어레이에선, 어레이의 길이/2만큼 반복되는 숫자가 존재한다. 그 숫자를 반환하라.
	
	[1,2,3,3]에선 Arr의 길이가 4니까, 4/2 = 2번 반복되는 숫자인 3을 반환하면 된다.
	
	Arr안의 등장하는 숫자는 중복되서 나타나는 숫자 외에는 모든수가 단 한번만 등장하고,
	
	숫자의 범위는 1~10000까지다.

 */

package HashTable;

import java.util.HashMap;
import java.util.Map;

public class NRepeatedElementInSize2NArr961 {
	
	/*
	//<문제풀이>
	
	//dictionary(java에서는 HashMap)을 활용해서, for문돌면서 각 element를 key로, 빈도수를 value로 저장.
	
	//map.put(element, map.getOrDefault(element, 0)+1);에서 .getOrDefault()+1을 한 이유는
	
	//.getOrDefault(?,0)해놓으면 해당 값을 찾았을때, 있으면 value를, 없으면 0을 반환하라는 이야기
	
	//거기에 한번 등장했으니 +1해준것
	
	//메모리는 잘썼는데 좀 느림
	
	//int[]만 이용해도 될것같음. 훨씬 더 빠르게.
	
	//Runtime: 22 ms, faster than 14.14% of Java online submissions for N-Repeated Element in Size 2N Array.
	//Memory Usage: 39.1 MB, less than 100.00% of Java online submissions for N-Repeated Element in Size 2N Array.
	
	public static int repeatedNTimes(int[] A) {
        int repeated = A.length/2;
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int element : A)
        {
        	map.put(element, map.getOrDefault(element, 0)+1);
        	if(map.get(element) == repeated) return element;
        }
        
        return 0;
    }
    */
	
	/*
	//<문제풀이>
	
	//int[]로 대신해봤다.
	
	//문제에서 0 <= A[i] < 10000라길래 int[]의 크기를 10000으로 정해서 메모리를 겁나 먹을 줄 알았는데, 그래도 100%떴다.
	
	//20ms 빨라졌긴 했는데, 더 빨리할 수 있는 방법이 있긴하나?
	
	//Runtime: 2 ms, faster than 42.83% of Java online submissions for N-Repeated Element in Size 2N Array.
	//Memory Usage: 38.3 MB, less than 100.00% of Java online submissions for N-Repeated Element in Size 2N Array.
	
	public static int repeatedNTimes(int[] A) {
		int len = A.length/2;
		int[] container = new int[10000];
		
		for(int element : A)
		{
			container[element]++;
			if(container[element] == len) return element;
		}

		return 0;
	}
	*/
	
	//<문제풀이 by lee215>
	
	//놀랍게도 더 빠른 방법이 존재한다.
	
	//중복되서 등장하는 숫자 말고, 다른숫자는 distinct(한번씩만 등장)한다고 했으니, 같은숫자가 2번째 등장할때 return해주면 된다.
	
	//그러면 번거롭게 len을 구할 필요도 없고, 딕셔너리 쓸 필요도 없다.
	
	//Runtime: 1 ms, faster than 68.94% of Java online submissions for N-Repeated Element in Size 2N Array.
	//Memory Usage: 38 MB, less than 100.00% of Java online submissions for N-Repeated Element in Size 2N Array.
	
	public static int repeatedNTimes(int[] A) {
        
        int[] count = new int[10000];
        for (int a : A)
            if (count[a]++ == 1)
                return a;
        return -1;
    
    }


	public static void main(String[] args) {
		int[] A = {5,1,5,2,5,3,5,4};
		System.out.println(repeatedNTimes(A));
	}
}
