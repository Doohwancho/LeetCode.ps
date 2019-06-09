/*
	<문제>
	
	문자열이 주어지면, 문자열 안에 substring으로만 반복해서 해당 문자열을 만들 수 있으면 true, 아니면 false를 반환하라.
	
	예를들어, "abcabcabcabc"의 경우, 안에 substring "abc"를 4번 사용하여 해당 문자열을 만들 수 있으므로, true를 반환한다.
	
	만약 "ababababac"의 경우, 어떠한 substring을 반복적으로 사용해도 해당 문자열을 만들 수 없으므로, false를 반환한다.
	
	

	Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

 

	Example 1:
	
	Input: "abab"
	Output: True
	Explanation: It's the substring "ab" twice.
	Example 2:
	
	Input: "aba"
	Output: False
	Example 3:
	
	Input: "abcabcabcabc"
	Output: True
	Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
	
	
	
	
	
	<문제풀이 - Trial01(Time Limit Exceeded)>
	
	미친듯한 input을 넣었을 경우, time limit이 걸리지만, 방법론은 이러하다.
	
	step01) for문을 돌면서, index 0부터 해당 for문의 index까지의 substring과 그 길이만큼의 다음 substring을 비교하여 같은지 확인. 단, substring은 최소 2개가 같아야 하므로, for문은 주어진 문자열의 반틈까지만 돌면 됨.
	step02) 같다면, 해당 substring의 길이만큼 while문으로 돌면서 나머지 substring과 일치하는지 확인
	
 */


package String;

public class RepeatedSubstringPattern459_ver1 {
	public static boolean repeatedSubstringPattern(String s)
	{
		int length = s.length();
		
		for(int i = 1; 2*i < length+1; i++)
		{
			if(s.substring(0,i).equals(s.substring(i,2*i)))
			{
				int index = i;
				while(index+i <= length)
				{
					if(s.substring(index-i,index).equals(s.substring(index,index+i)))
					{
						index+=i;
					}
					else
					{
						break;
					}
					if(index == length) { return true; }
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		String s = "ababababjk";
		System.out.println(repeatedSubstringPattern(s));
	}
}
