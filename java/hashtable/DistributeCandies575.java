/*
	Given an integer array with even length, where different numbers in this array represent different kinds of candies. Each number means one candy of the corresponding kind. You need to distribute these candies equally in number to brother and sister. Return the maximum number of kinds of candies the sister could gain.
	Example 1:
	Input: candies = [1,1,2,2,3,3]
	Output: 3
	Explanation:
	There are three different kinds of candies (1, 2 and 3), and two candies for each kind.
	Optimal distribution: The sister has candies [1,2,3] and the brother has candies [1,2,3], too. 
	The sister has three different kinds of candies. 
	Example 2:
	Input: candies = [1,1,2,3]
	Output: 2
	Explanation: For example, the sister has candies [2,3] and the brother has candies [1,1]. 
	The sister has two different kinds of candies, the brother has only one kind of candies. 
	
	
	
	<문제>
	
	길이가 짝수인 어레이가 주어진다. 어레이의 각 원소는 사탕을 나타낸다. 다른 숫자는 다른 종류의 사탕을 나타낸다.
	
	사탕을 2명에게 똑같은 갯수로 나눠주는데, 이 때, 최대로 다양하게 사탕을 받을 수 있는 경우를 반환하라.
	
	예를들어, [1,1,2,3]을 두명에게 [1,1] [2,3] 이렇게 나눠주면, [2,3]을 받은 사람은 두종류의 사탕을 받게되고, [1,1]는 한종류의 사탕을 받게된다.
	
	여기서 최대로 다양하게 사탕을 받는 경우는 2이므로, 2를 반환한다.

 */
package HashTable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DistributeCandies575 {
	
	/*
	//<문제풀이1>
	
	//set에 넣고, set의 size를 구하면 사탕의 종류가 최대 몇개인지 구해짐
	//사탕의 종류 숫자가 array길이의 반보다 크지 않으면서, 반과 가장 가까운 숫자를 구해 반환하면 됨.
	
	//느리고 메모리도 많이 먹음 ㅜㅜ
	
	//Runtime: 46 ms, faster than 31.68% of Java online submissions for Distribute Candies.
	//Memory Usage: 44.6 MB, less than 47.37% of Java online submissions for Distribute Candies.
	public static int distributeCandies(int[] candies) {
		Set<Integer> set = new HashSet<>();
		int len = candies.length;
		for(int i = 0; i < len; i++) set.add(candies[i]);
		int kinds = set.size();
		return kinds > len/2 ? len/2 : kinds;
    }
	*/
	/*
	//<문제풀이2>
	
	//set말고 오름차순 정렬해서 사탕의 종류를 구하는 방법
	
	//성능상 큰 차이는 없음
	//Runtime: 46 ms, faster than 31.68% of Java online submissions for Distribute Candies.
	//Memory Usage: 45.3 MB, less than 47.37% of Java online submissions for Distribute Candies.
	
	public static int distributeCandies(int[] candies) {
		Arrays.sort(candies);
		int kinds = 1;
		int len = candies.length;
		for(int i = 1; i < len; i++) if(candies[i] != candies[i-1]) kinds++;
		return kinds > len/2 ? len/2 : kinds;
    }
    */
	
	/*
	//<문제풀이3>
	
	//for문 다도는게 아니라, loop중간에 사탕 종류가 array길이의 반이면 반환하게끔 바꿨다.
	
	//input의 양이 얼마 없어서 성능상 별차이없다고 나오지만 아마 큰 데이터 처리하면 있을것이다.
	
	//Runtime: 49 ms, faster than 25.00% of Java online submissions for Distribute Candies.
	//Memory Usage: 45 MB, less than 47.37% of Java online submissions for Distribute Candies.
	
	public static int distributeCandies(int[] candies) {
		Arrays.sort(candies);
		int kinds = 1;
		int len = candies.length;
		for(int i = 1; i < len; i++)
		{
			if(candies[i] != candies[i-1])
            {
                kinds++;
                if(kinds > len/2) return len/2;
            }
		}
		return kinds;
    }
	*/
	
	//<문제풀이 by andyreadall>
	
	//1번에다 3번을 섞은 답안
	
	//3번은 kinds > len/2라고 했는데, kinds == len/2이 한 step줄여줘서 더 빠름
	
	//for(int element : candies)를 써서 조금 더 빠름
	
	//Runtime: 29 ms, faster than 92.25% of Java online submissions for Distribute Candies.
	//Memory Usage: 40.1 MB, less than 100.00% of Java online submissions for Distribute Candies.
	
	public static int distributeCandies(int[] candies) {
		Set<Integer> set = new HashSet<>();
		int cap = candies.length/2;
		for(int candy : candies)
		{
			set.add(candy);
			if(set.size() == cap) return cap;
		}
		return set.size();
    }


	public static void main(String[] args) {
		int[] candies = {1,1,2,3};
		System.out.println(distributeCandies(candies));
	}
}