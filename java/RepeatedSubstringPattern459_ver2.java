/*	
	<문제풀이 by fabrizio3>
	
	똑같은 substring을 여러번 합쳐서 주어진 문자열을 만들 수 있다면, substring은, (주어진 문자열의 길이 /substring의 길이)만큼 반복해서 합쳐진다.
	예를들어, "abcabcabc"가 주어졌다고 가정하면, 문자열의 길이는 9이고 "abc"이 길이는 3인데, "abc"아 3번(9/3) 반복해서 붙여져야 해당 문자열이 된다.
	위 조건을 의미하는 코드가 if(l%i==0) 이 부분이다. 
	
	if 안의 코드는, substring을 반복해서 넣었을 때, 해당 문자열을 만들 수 있는가를 의미한다.

 */


package String;

public class RepeatedSubstringPattern459_ver2 {
	public static boolean repeatedSubstringPattern(String str)
	{
		int l = str.length();
		for(int i=l/2;i>=1;i--) {
			if(l%i==0) {
				int m = l/i;
				String subS = str.substring(0,i);
				StringBuilder sb = new StringBuilder();
				for(int j=0;j<m;j++) {
					sb.append(subS);
				}
				if(sb.toString().equals(str)) return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		String s = "abcabcabc";
		System.out.println(repeatedSubstringPattern(s));
	}
}
