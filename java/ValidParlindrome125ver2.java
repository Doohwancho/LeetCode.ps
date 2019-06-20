/*
 * 
 * <문제풀이 by caikehe>
 * 
 * **two pointer**
 * 
 * 왼쪽 맨 끝 첫번째 인덱스 l과, 오른쪽 맨 끝 마지막 인덱스 r을 만든 후, 알파벳과 숫자가 아닌 부분은 while()문으로 건너뛰면서, l과 r번째를 비교하고, l++와 r--을 해준다.
 * 
 */


package String;

public class ValidParlindrome125ver2 {
	
	public static boolean isPalindrome(String s)
	{
		int l = 0, r = s.length()-1;
		while(l < r)
		{
			while(l <= r && !Character.isLetterOrDigit(s.charAt(l))) 
			{
				l++;
			}
			while(r >= 0 && !Character.isLetterOrDigit(s.charAt(r)))
			{
				r--;
			}
			if(l < r && Character.toLowerCase(s.charAt(l++)) != Character.toLowerCase(s.charAt(r--))) //.charAt(l)과 .charAt(l++)은 같은 인덱스 l로 동작하나, 동작이 끝난 후 .charAt(l++)만 l을 +1해준다. 
			{
				return false;
			}
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		//String s = "A man, a plan, a canal: Panama";
		String s = "!Am@ore, Roma.";
		System.out.println(isPalindrome(s));
	}
	
	
}
