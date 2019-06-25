/*
 * 문제 설명
 * 
 * 문자열 S,T가 주어진다. S는 T문자열을 n번 합한 문자열이다.(n >= 1) (e.g S = T + T + ... + T) 
 * S와 T가 공통적으로 겹치는 문자열 중, 가장 긴 문자열을 반환하라
 * 
 */
/*
 * Trial01 
 * 
 * step01) S와 T의 공통으로 겹치는 문자열을 구한다
 * step02) 해당 문자열에 반복되는 문자중, 가장 긴 문자를 반환한다
 * 
 * 문제점) String str1 = "TAUXXTAUXXTAUXXTAUXXTAUXX";
		 String str2 = "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX";
		 의 경우, 위 방법으로 하면 "TAUXXTAUXX"를 반환하는데, str1는 "TAUXXTAUXX"로 나누면 나누어 떨어지지 않는다. "TAUXX"가 남기 때문이다.
 * 
 */

package String;

public class GreatestCommonDivisor1071_ver1 {
	public static String gcdOfString(String str1, String str2)
	{
		char[] S = str1.toCharArray();
		char[] T = str2.toCharArray();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < str1.length() && i < str2.length(); i++)
		{
			if(S[i]==T[i]) sb.append(S[i]);
		}

		String common = sb.toString();
		System.out.println(common);
		if(common.isEmpty()) 
		{
			return "";
		}
		else
		{
			for(int j = common.length()/2; j >0; j--)
			{
				//왜 common.substring(0, j) == common.substring(j, 2*j) false? 
				//https://tworab.tistory.com/16
				//왜냐면 문자열 비교엔 1. == 2. equals()이 있는데
				//==은 두 문자열의 원본내용을 비교하는게 아닌, 참조값을 비교하기 때문이다.
				//equals()는 두 문자열의 원본내용을 비교한다.
				System.out.println(j);
				System.out.println(common.substring(0, j));
				System.out.println(common.substring(j, 2*j));
				
				if(common.substring(0, j).equals(common.substring(j, 2*j)))
				{
					System.out.println("triggered");
					return common.substring(0, j);
				}
				else
				{
					continue;
				}
			}return common;
		}
	}
	
	public static void main(String[] args) 
	{
//		String str1 = "ABABABAB";
//		String str2 = "ABABAB";
//		String str1 = "ABCABC";
//		String str2 = "ABC";
		String str1 = "TAUXXTAUXXTAUXXTAUXXTAUXX";
		String str2 = "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX";
		
		System.out.println(gcdOfString(str1, str2));
	}
}
