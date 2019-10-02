/*
	Given two positive integers x and y, an integer is powerful if it is equal to x^i + y^j for some integers i >= 0 and j >= 0.
	
	Return a list of all powerful integers that have value less than or equal to bound.
	
	You may return the answer in any order.  In your answer, each value should occur at most once.
	
	 
	
	Example 1:
	
	Input: x = 2, y = 3, bound = 10
	Output: [2,3,4,5,7,9,10]
	Explanation: 
	2 = 2^0 + 3^0
	3 = 2^1 + 3^0
	4 = 2^0 + 3^1
	5 = 2^1 + 3^1
	7 = 2^2 + 3^1
	9 = 2^3 + 3^0
	10 = 2^0 + 3^2
	Example 2:
	
	Input: x = 3, y = 5, bound = 15
	Output: [2,4,6,8,10,14]
	
	
	
 */

package HashTable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PowerfulIntegers970 {
	
	//<문제풀이1>
	
	//a**x = b에서 x를 구하려면, log(b) / log(a)를 하면 된다.
	
	//이 점을 이용해서, 2중 loop로 x,y의 거듭제곱들의 합이 bound보다 작은 것을 반환하면 된다.
	
	//속도는 빠른데 메모리가 많이 잡아먹음. set을 써서 그런 것 같음.
	
	//Runtime: 1 ms, faster than 99.74% of Java online submissions for Powerful Integers.
	//Memory Usage: 33.6 MB, less than 12.50% of Java online submissions for Powerful Integers.
	
	
	public static List<Integer> powerfulIntegers(int x, int y, int bound) {
		
		Set<Integer> set = new HashSet<>();
				
		int x_log = x != 1 ? (int) (Math.log(bound) / Math.log(x))+1 : 1;
		int y_log = y != 1 ? (int) (Math.log(bound) / Math.log(y))+1 : 1;		
		
		for(int i = 0; i < x_log; i++) {
			for(int j = 0; j < y_log; j++) {
					int sum = (int)(Math.pow(x, i) + Math.pow(y, j));
					if(sum <= bound) set.add(sum);
			}
		}
		
		return new ArrayList<>(set);
    }
	
	public static void main(String[] args) {
		//int x = 3, y = 5, bound = 15;
		int x = 2, y = 1, bound = 10;
		
		System.out.println(powerfulIntegers(x,y,bound));
	}
}



