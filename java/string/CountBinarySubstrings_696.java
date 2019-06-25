package String;

/*
 * Give a string s, count the number of non-empty (contiguous) substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.

Substrings that occur multiple times are counted the number of times they occur.

Example 1:
Input: "00110011"
Output: 6
Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".

Notice that some of these substrings repeat and are counted the number of times they occur.

Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
Example 2:
Input: "10101"
Output: 4
Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.
 */

/*
 * 문제
 * binary숫자가 "00110011"처럼 주어지면, 연속된 substring 중 0과 1의 갯수가 같은 substring의 갯수를 구하라
 * 예를들어, "0011"이면, "0011"과"01"이 연속되면서도 0과 1의 숫자가 같기 때문에, 답은 2이다.
 * 
 * 문제 풀이(by lee215)
 * "0001111"의 경우 0이 3개가 나온 후 1이 몇개가 나오든, "000111","0011","01"만 카운트 되기 때문에, 0의 갯수와 1의 갯수중 더 낮은 값을 구하면 된다.
 * "0110001111"는 각 숫자가 [1,2,3,4]개 나온다. 위의 논리를 적용하여, min(1,2)+min(2,3)+min(3,4)를 해주면 된다.
 */
public class CountBinarySubstrings_696 {
	public static void main(String[] args) {
		String s = "00110011";
		
		int cur = 1, pre = 0, res = 0;
		
		for(int i = 1; i < s.length(); i++)         //index 1부터 시작(이전 인덱스의 숫자와 비교해야 하는데 index 0부터 시작하면 첫번째에 이전이 없으므로 에러나기 때문)
		{
			if(s.charAt(i) == s.charAt(i-1)) cur++; //이전 숫자와 비교해서 같으면 cur +1
			else {
				res += Math.min(cur,pre);           //숫자가 0에서 1로, 1에서 0으로 바뀌면 min(기존 숫자 갯수,현재 숫자 갯수)해주고 res에 더해줌
				pre = cur;                          //숫자가 0에서 1로, 1에서 0으로 바뀌면 카운트하던 cur을 pre로 옮기고 다시 cur을 1개로 맞춤
				cur = 1;
			}
		}
		System.out.println(res + Math.min(cur, pre));

	}
}
