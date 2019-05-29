/*
 * 
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.

 */


/*
 * 문제
 * 
 * 문자열이 주어지면, 중복이 아닌 알파벳 중 가장 앞에 위치한 알파벳을 반환하라
 * 
 * 
 * 
 * 문제풀이
 * 
 * step01) counter이라는 int 배열을 선언한다. 알파벳이 총 26개이므로 크기는 26으로 선언한다.
 * step02) 문자열 s를 toCharArray()로 char형태로 쪼개서 for문을 돌면서, 해당 알파벳과 그 갯수를 저장시켜 준다. 이 때 c-'a'을 하는 이유는, 알파벳 -'a'을 하면, a,b,c,d, ... 가 0,1,2,3, ...으로 저장되기 때문이다.
 * step03) count한 갯수가 1이면 해당 문자열의 인덱스를 반환하고, 모두 2이상이면(모두 중복이면) -1을 반환한다.
 * 
 */


package String;


public class FirstUniqueChar387 {
	
	public static int firstUniqChar(String s) {
		int[] counter = new int[26];
		for(char c:s.toCharArray()) 
			{
			counter[c-'a']++;
			}
		for(char c:s.toCharArray())
		{
			if(counter[c-'a']==1) return s.indexOf(c);
		}
		return -1;
	}
	
	public static void main(String[] args) {
		String s = "loveleetcode";
		System.out.println(firstUniqChar(s));
	}
}
