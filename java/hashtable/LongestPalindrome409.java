/*
	Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
	
	This is case sensitive, for example "Aa" is not considered a palindrome here.
	
	Note:
	Assume the length of given string will not exceed 1,010.
	
	Example:
	
	Input:
	"abccccdd"
	
	Output:
	7
	
	Explanation:
	One longest palindrome that can be built is "dccaccd", whose length is 7.
 
 
 
 	<문제>
 	
 	문자열이 다음과 같이 주어지면(ex. "abccccdd")이 알파벳을 조합하여 가장 긴 palindrome을 만들 수 있는 알파벳의 갯수를 반환하라.
 	
 	parlindrome이란, 뒤집어도 원래 문자열과 같은것이다.
 	
 	위의 예시를 들면, 주어진 문자열 "abccccdd"에서, palindrome을 만들 수 있는 가장 긴 단어는 "dccaccd"이므로, 이 문자열의 길이인 7을 반환한다.

 */

package HashTable;

import java.util.HashSet;
import java.util.Set;

public class LongestPalindrome409 {
	
	/*
	//<Trial01>
	
	//방법
	
	//map(딕셔너리)로 각 알파벳의 빈도수를 카운트 한 후, 짝수갯수의 알파벳은 그대로 더하고, 홀수갯수의 알파벳은 그중 가장 큰것을 더한다.
	
	//map을 굳이 안쓰고 int[52] (소문자 26개+대문자26개) 하면 더 빠르다.
	
	//이론상 완벽한줄 알았는데, 생각해보니 "aaabbbcc"같은 경우엔, a에서 2개, c에서 2개, b3개 써서 "acbbbca" 이런식으로 만들어야하는데,
	
	//밑의 모델은 aaa와 bbb가 둘다 홀수개니까, 그중에 가장큰 홀수개만 쓰자 라는 식이여서 잘못동작하는 것임.
	
	
	
	public static int longestPalindrome(String s) {
		
		int[] lowercase = new int[26];
		int[] capitalLetter = new int[26];

		int rst = 0;
		int maxOdd = 0;
		
		for(char c : s.toCharArray()) {
			if(Character.isLowerCase(c)) lowercase[c-'a']++;
			else capitalLetter[c-'A']++;
		}
			
		for(int i : lowercase) {
			if(i % 2 == 0) rst += i;
			else maxOdd = Math.max(maxOdd, i);
		}
		
		for(int i : capitalLetter) {
			if(i % 2 == 0) rst += i;
			else maxOdd = Math.max(maxOdd, i);
		}
		
		return rst + maxOdd;
    }
    */
	
	/*
	//<문제풀이 1 by fabrizio3>
	
	//set을 만들어서, set에 한개만 들어간다는 성질을 이용하여, 페어가 될때마다 그 알파벳을 빼주며 카운터를 한 후, 전체문자가 홀수개있으면 맨 마지막에 +1해주는 식.

	//메모리 잘썼고 속도도 나쁘지 않은편.
	
	//Runtime: 3 ms, faster than 53.00% of Java online submissions for Longest Palindrome.
	//Memory Usage: 35.7 MB, less than 100.00% of Java online submissions for Longest Palindrome.
	
	public static int longestPalindrome(String s) {
		if(s==null || s.length()==0) return 0;
		Set<Character> set = new HashSet<>();
		int count = 0;
		char[] chars = s.toCharArray();
		for(char c: chars) {
			if(set.remove(c)) {
				count++;
			} else {
				set.add(c);
			}
		}
		return set.isEmpty() ? count*2 : count*2+1;
	}
	*/
	
	
	//<문제풀이2 by yuxiangmusic>
	
	//크.. 군더더기 없이 깔끔하고 완벽하다.
	//Runtime: 1 ms, faster than 100.00% of Java online submissions for Longest Palindrome.
	//Memory Usage: 35.8 MB, less than 100.00% of Java online submissions for Longest Palindrome.
	
	//알파벳 소문자는 97~112, 대문자는 65~90까지니까, 
	//그만큼의 숫자를 포용해 줄 int[]를 만듬. 이때, 1<<7은 1을 이진법으로 변환 후, 왼쪽으로 7칸 옮기라는 말인데, 이러면 1000 0000이 되고, 이를 10진수로 전환하면 2**7이니까 128. 위의 알파벳의 범위를 커버함.
	//for (int i : arr) l += i >> 1 << 1;의 뜻은, 2진수에서 오른쪽으로 한칸 이동시킨 후, 다시 왼쪽으로 한칸 이동시킨다는 의민데, 
	//이걸 왜 하냐면 오른쪽으로 한칸 이동할때 이진수 맨 오른쪽수가 날라가고, 다시 왼쪽으로 한칸 당길 때, 없어졌던 칸의 2진수가 0으로 채워지기 때문.
	//결국, 3이던 7이건 111이던 2001이던, 맨 끝에 1을 제거하겠다는 의미.
	//맨 마지막은 홀수가 있으면(주어진 문자열의 길이가 홀수면 알파벳중 하나는 무조건 홀수여야 하므로..) 1을 더해줌.
	//지렸다.
	
	public static int longestPalindrome(String s) {
		int arr[] = new int[1 << 7], l = 0;
        for (char c : s.toCharArray()) arr[c]++;
        for (int i : arr) l += i >> 1 << 1;
        return l == s.length() ? l : l + 1;
    }

	
	
	public static void main(String[] args) {
		//String s = "abccccddAA";
		String s = "azAZ";
		System.out.println(longestPalindrome(s));
	}
}
