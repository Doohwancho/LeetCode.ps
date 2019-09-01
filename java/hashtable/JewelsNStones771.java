/*
	You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.
	
	The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".
	
	Example 1:
	
	Input: J = "aA", S = "aAAbbbb"
	Output: 3
	Example 2:
	
	Input: J = "z", S = "ZZ"
	Output: 0
	
	
	
	<문제>
	
	J는 보석이고 S는 원석이다.
	
	원석중에 보석이 몇개 있는지 찾아라. 단, J가 "aA"이렇게 있으면 "a"도 보석이고 "A"도 보석이다. "aA"가 보석이 아니라.
 */

package HashTable;

import java.util.HashSet;

public class JewelsNStones771 {
	
	//<문제풀이>
	
	//HashSet을 이용하여 보석들을 중복되지않게 set에 담아두고, 원석을 for문으로 돌면서, .contains로 보석에 포함되는지 확인.
	
	//Runtime: 1 ms, faster than 94.01% of Java online submissions for Jewels and Stones.
	//Memory Usage: 34.9 MB, less than 97.98% of Java online submissions for Jewels and Stones.
	public static int numJewelsInStones(String J, String S) {
		int cnt = 0;
		HashSet set = new HashSet<>();
		
		for(char c : J.toCharArray())
		{
			set.add(c);
		}
		
		for(char c : S.toCharArray())
		{
			if(set.contains(c)) cnt++;
		}
		
		return cnt;
    }

	public static void main(String[] args) {
		String J = "aA";
		String S = "aAAbbbb";
		
		System.out.println(numJewelsInStones(J, S));
	}
}
