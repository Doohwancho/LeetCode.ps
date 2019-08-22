/*
	In a deck of cards, each card has an integer written on it.
	
	Return true if and only if you can choose X >= 2 such that it is possible to split the entire deck into 1 or more groups of cards, where:
	
	Each group has exactly X cards.
	All the cards in each group have the same integer.
	 
	
	Example 1:
	
	Input: [1,2,3,4,4,3,2,1]
	Output: true
	Explanation: Possible partition [1,1],[2,2],[3,3],[4,4]
	Example 2:
	
	Input: [1,1,1,2,2,2,3,3]
	Output: false
	Explanation: No possible partition.
	Example 3:
	
	Input: [1]
	Output: false
	Explanation: No possible partition.
	Example 4:
	
	Input: [1,1]
	Output: true
	Explanation: Possible partition [1,1]
	Example 5:
	
	Input: [1,1,2,2,2,2]
	Output: true
	Explanation: Possible partition [1,1],[2,2],[2,2]
	
	
	
	
	<문제>
	
	리스트가 [1,2,3,4,4,3,2,1] 이렇게 주어졌을 때, 원소를 최소 2개 이상씩 그룹화 할수 있으면 true를 반환한다.
	
	단, 그룹안 모든 원소는 동일해야 한다.
	
	예를들어, 위의 리스트의 경우, 2개씩 그룹화 하면, [1,1],[2,2],[3,3],[4,4]가 되어 true를 반환한다.
	
	다른 예시를 들자면, [1]의 경우, 2개 이상씩 그룹핑 할 수 없으므로 false를 반환한다.
	
	[1,1]은 한쌍의 그룹이 나오므로 true를 반환하고, 
	
	[1,1,2,2,2,2]은 그룹화 하면 [1,1],[2,2],[2,2]이 되는데, [2,2]가 두번 나왔더라도, 그룹 개개인들의 모든 원소가 동일하고, 그룹 멤버수가 같으므로 true를 반환한다.
 */

package Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



public class XofaKindInDeckOfCards914 {
	
	/*
	//<문제풀이>

	//step01 - 유효성 검사(list길이가 2보다 작으면, 최소 2개 이상 그룹핑 할 수 없으므로 false 반환)

	//step02 - 각 원소들이 빈도수 체크

	//step03 - 각 빈도수의 공약수 존재여부 체크

	//step04 - 공약수가 1이면, 1개로밖에 그룹핑 못한다는 뜻이므로 false반환. 2 이상이 하나라도 나오면, n개씩 그룹핑 가능하다는 말이므로 true반환.

	//Runtime: 8 ms, faster than 66.82% of Java online submissions for X of a Kind in a Deck of Cards.
	//Memory Usage: 38.2 MB, less than 100.00% of Java online submissions for X of a Kind in a Deck of Cards.
	
	private static boolean GCD(Map<Integer, Integer> map) //Greatest Common Denominator - 최대공약수
    {
		int smallest = Integer.MAX_VALUE;
		Iterator<Integer> values = map.values().iterator();
		
		while(values.hasNext())
		{
			int value = values.next();
			if(value < smallest) smallest = value;
		}
		for(int i = 2; i < smallest+1; i++)
		{
			Iterator<Integer> values2 = map.values().iterator();
			while(values2.hasNext())
			{
				int value = values2.next();
				if(value % i == 0 && !values2.hasNext()) return true;
				else if(value % i == 0) continue;
				break;
			}
		}
		return false;
	}
	*/
	
	
	//done - if(value % i == 0 && !values2.hasNext()) -> if(!values2.hasNext() && value % i == 0)
	//done - smallest 찾는걸 Collections class활용
	//done - smallest loop 효율 개선
	//? - GCD 효율성 개선여지 있음
	
	//메모리 사용량 개선됨
	
	//Runtime: 9 ms, faster than 57.87% of Java online submissions for X of a Kind in a Deck of Cards.
	//Memory Usage: 38.8 MB, less than 100.00% of Java online submissions for X of a Kind in a Deck of Cards.
	
	
	
	private static boolean GCD(Map<Integer, Integer> map) //Greatest Common Denominator - 최대공약수
    {

		int smallest = Collections.min(map.values());
		
		boolean divides  = false;
		for (int i = 2; i <= smallest; i++)
		{
			divides = true;
			for (int key : map.keySet())
			{
				int val = map.get(key);
			    if (val % i != 0)
			    {
			    	divides = false;
		            break;
			    }
			}
			 if (divides) break;
		}      
	    return divides;
	}
    
	
    public static boolean hasGroupsSizeX(int[] deck) {
        if(deck == null || deck.length < 2) return false;
        Map<Integer, Integer> map = new HashMap<>();
		for(int i : deck) map.put(i, map.getOrDefault(i, 0)+1);
		return GCD(map);
    }
    
	
	/*
	//<문제풀이 by lee215>
	
	//성능상 차이는 거의 없지만 코드 가독성이 훨씬 좋음
	//gcd를 재귀로 해서 느린듯
	
	//위에 코드와 차이는 gcd시 위에껀 조건충족만 되면 true반환하고 끝나나, 밑에껀 모든 gcd를 계산한다는 점이 다름. 가독성은 높지만 비효율적.
	
	//Runtime: 9 ms, faster than 57.87% of Java online submissions for X of a Kind in a Deck of Cards.
	//Memory Usage: 39.5 MB, less than 57.14% of Java online submissions for X of a Kind in a Deck of Cards.
	
	public static boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> count = new HashMap<>();
        int res = 0;
        for (int i : deck) count.put(i, count.getOrDefault(i, 0) + 1);
        for (int i : count.values()) res = gcd(i, res);
        return res > 1;
    }

    public static int gcd(int a, int b) {
        return b > 0 ? gcd(b, a % b) : a;
    }
    */
	
  
	
	public static void main(String[] args) {
		int[] deck = {1,1,1,1,2,2,2,2,2,2};
		System.out.println(hasGroupsSizeX(deck));
	}
}
