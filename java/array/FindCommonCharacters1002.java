/*
	Given an array A of strings made only from lowercase letters, return a list of all characters that show up in all strings within the list (including duplicates).  For example, if a character occurs 3 times in all strings but not 4 times, you need to include that character three times in the final answer.
	
	You may return the answer in any order.
	
	 
	
	Example 1:
	
	Input: ["bella","label","roller"]
	Output: ["e","l","l"]
	Example 2:
	
	Input: ["cool","lock","cook"]
	Output: ["c","o"]
	
	
	
	<문제>
	
	리스트 안에 String형태의 단어가 주어지면, 모든 단어에 공통적으로 등장하는 알파벳들을 리스트에 담아 리턴하라
	
	
	
	<문제 풀이>
	
	step01) 각 단어에 따른 알파벳을 저장시킬 map이라는 2차원 배열과, 모든 단어에 공통적으로 들어가는 알파벳이 들어갈 res라는 String형태의 리스트를 선언. map의 크기가 26인 이유는 알파벳의 총 갯수가 26개이기 때문.
	
	step02) 각 단어의 알파벳들을 .toCharArray()를 통해서 쪼갠 후, map에 해당 단어의 자리의 알파벳의 갯수만큼 +1씩 해줌.
	
	step03) Integer.MAX_VALUE을 통해 무한대의 min이라는 변수를 먼저 선언. 26개의 알파벳을 for문으로 돌면서, 해당 알파벳이 각 단어마다 총 몇번 등장했는지 map을통해 확인. 각 단어마다 등장횟수의 min값을 적용하여, 등장횟수만큼 res변수에 해당 단어를 넣어줌.
			예를들어, {"abcaa","axxxa","azzzzzaa"}인 경우, 첫번째 단어는 a가 3번, 두번째 단어는 a가 2번, 세번째 단어는 a가 3번 들어감. for문으로 돌면서 'a'의 등장횟수에  min()값을 적용하면 모든 단어에서 'a'가 총 두번 나오는 것을 확인할 수 있음.
 */

package Array;

import java.util.List;
import java.util.ArrayList;

public class FindCommonCharacters1002 {
	
	public static List<String> commonChars(String[] A)
	{
		//step01
		int[][] map = new int[A.length][26];
		List<String> res = new ArrayList();
		
		//step02
		for(int i = 0; i < A.length; i++)
		{
			for(char c : A[i].toCharArray())
			{
				map[i][c-'a']++;
			}
		}
		
		//step03
		for(int k = 0; k < 26; k++)
		{
			int min = Integer.MAX_VALUE;
			for(int j = 0; j < A.length; j++)
			{
				min = Math.min(min, map[j][k]);
			}
			for(int q = 0; q < min; q++)
			{
				res.add(Character.toString((char)('a'+ k)));
			}
		}
		return res;
	}
	public static void main(String[] args) {
		String[] A = {"bella","label","roller"};
		System.out.println(commonChars(A));
	}
}
