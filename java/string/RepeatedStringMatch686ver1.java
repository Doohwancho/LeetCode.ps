/*
	Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.
	
	For example, with A = "abcd" and B = "cdabcdab".
	
	Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").
	
	
	
	
	<문제>
	
	문자열 A와 B가 주어지면, A를 몇번 반복해서 붙여야 B가 A의 substring이 되는지 구하라.
	
	
	<trial01>
	A = "abcd", B = "abcdb" 에서 막힘.

 */


package String;

public class RepeatedStringMatch686ver1 {
	public static int repeatedStringMatch(String A, String B)
	{
		if((A+A).indexOf(B) != -1 || B.indexOf(A) != -1)
		{
			int rst = 1;
			String tmp = A;
			
			while(true)
			{
				if(tmp.indexOf(B) > -1)
				{
					return rst;
				}
				tmp=tmp+A;
				rst++;	
			}
		}
		else
		{
			return -1;
		}
		
	}
	
	public static void main(String[] args) 
	{
		String A = "abcd", B = "cdabcdab";
		//String A = "abcd", B = "bc";
		//String A = "abxd", B = "bc";
		//String A = "aaaaaaaaaaaaaaaaaaaaaab", B = "ba";
		//String A = "baaabbbaba", B = "baaabbbababaaabbbababaaabbbababaaabbbababaaabbbababaaabbbababaaabbbababaaabbbababaaabbbababaaabbbaba";
		//String A = "abcd", B = "abcdb"; // 여기서 막힘. for문 이전에 filter에서 걸러야 하는데 어떻게 거르는지 모르겠음.
		
		System.out.println(repeatedStringMatch(A,B));
	}
}
