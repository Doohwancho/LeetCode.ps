package HashTable;

import java.util.HashSet;
import java.util.Set;

public class UniqueNumberOfOccurences1207 {
/*
	Given an array of integers arr, write a function that returns true if and only if the number of occurrences of each value in the array is unique.
	
	 
	
	Example 1:
	
	Input: arr = [1,2,2,1,1,3]
	Output: true
	Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.
	Example 2:
	
	Input: arr = [1,2]
	Output: false
	Example 3:
	
	Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
	Output: true
	
	
	
	
	<문제>
	
	어레이가 다음과 같이 주어지면,  Input: arr = [1,2,2,1,1,3]
	
	어레이에 있는 값들의 빈도수들이 서로 다르면 true, 아니면 false를 반환하라.
	
	위의 예시의 경우, 1은 3번, 2는 2번, 3은 1번 나온다.
	
	3,2,1 빈도수가 서로 다르기 때문에 true를 반환한다.
	
	만약 arr = [1,2]라면,
	
	1은 1번, 2도 1번이기 때문에, 빈도수가 같아서 false를 반환한다.
	
	단, arr의 최대 길이는 1000이고, arr안에 나올수 있는 숫자의 범위는 -1000부터 1000이다.
 */

	//<문제풀이>
	
	//숫자의 빈도수를 체크해줄 int[]를 만든다. 크기는 2001개로.
	
	//숫자가 -1000부터 1000까지 등장하니까, 0까지 세면 2001개의 숫자가 등장 가능하다.
	
	//for문돌면서 빈도수를 체크해 주는데, 마이너스인 수는 1000-음수 하면 -1부터 1001번째 인덱스에 얌전하게 들어간다.
	
	//그리고 마지막에 빈도수가 겹치는지 알아보기위해 set을 이용하여, 이전에 나왔던 빈도수가 또 나오면 false를 반환해준다.
	
	//역시 int[]가 젤 빠르다.
	
	//Runtime: 1 ms, faster than 99.91% of Java online submissions for Unique Number of Occurrences.
	//Memory Usage: 35.5 MB, less than 100.00% of Java online submissions for Unique Number of Occurrences.
	
    public static boolean uniqueOccurrences(int[] arr) {
        Set<Integer> set = new HashSet<>();
    	int[] container = new int[2001];
        for(int i : arr) {
        	if(i < 0) container[1000-i]++;
        	else container[i]++;
        }
        for(int k : container) if(k != 0 && !set.add(k)) return false;
    	return true;
    }

	
	public static void main(String[] args) {
		int[] arr = {1,2,2,1,1,3};
		//int[] arr = {-1,0,1};
		
		System.out.println(uniqueOccurrences(arr));
	}
}
