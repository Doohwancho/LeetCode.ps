/*
 * <문제 풀이 by alexander>
 * 
 * b가 a의 substring이기 확인하기 전에, a를 최대로 이어붙여야 하는 횟수를 구해야 한다.
 * 
 * b의 길이를 a로 나눴을때, 정수로 나누어 떨어지면, b의 길이/a의길이 +1만큼 확인해 보면 된다.
 * 
 * 예를들어, a = "bca", b = "abcabc"이면, "bcabcabca"로, 6/3+1번(3번) 까지가 최대로 확인해 볼 수 있는 숫자다.
 * 
 * 하지만 b의 길이를 a로 나눴을때, 정수로 나누어 떨어지지 않는다면(5/3라던지..), b의길이+a의길이 +2만큼 확인해 보면 된다. 남는 1.x을 올림해 주는 개념이라고 이해하면 편하다.
 * 
 * 예를들어, a = "abc", b = "cabcabca"이면, "abcabcabcabc"로, 8/3+2번(4번) 까지가 최대로 확인해 볼 수 있는 숫자다.
 * 
 * a를 최대호 이어붙일 수 있는 숫자를 구했으면, for문으로 돌면서, .indexOf(b)가 -1이 아니라면(존재하지 않는게 아니라면 == 존재한다면), 몇번 이어붙였는지 반환해준다.
 * 
 */


package String;

public class RepeatedStringMatch686ver2 {
	
	public static int repeatedStringMatch(String a, String b) {
        String as = a;
        System.out.println(b.length());
        System.out.println(a.length());
        System.out.println(b.length() / a.length());

        for (int rep = 1; rep <= b.length() / a.length() + 2; rep++, as += a)
            if (as.indexOf(b) != -1) return rep;
        return -1;
    }
	
	public static void main(String[] args) 
	{
		//String A = "abcd", B = "abcdb"; 
		//String A = "abc", B = "abcabc";
		//String A = "cab", B = "abcab";
		String A = "abc", B = "cabcabca";
		
		
		System.out.println(repeatedStringMatch(A,B));
	}
}
