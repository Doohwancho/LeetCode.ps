/*
Your friend is typing his name into a keyboard.  Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.

You examine the typed characters of the keyboard.  Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.

 

Example 1:

Input: name = "alex", typed = "aaleex"
Output: true
Explanation: 'a' and 'e' in 'alex' were long pressed.
Example 2:

Input: name = "saeed", typed = "ssaaedd"
Output: false
Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.
Example 3:

Input: name = "leelee", typed = "lleeelee"
Output: true
Example 4:

Input: name = "laiden", typed = "laiden"
Output: true
Explanation: It's not necessary to long press any character.
 */


/*
 * 문제설명
 * 
 * name과 typed가 주어진다.
 * typed는 name을 입력할 때, 잘못눌러서 한 글자가 여러번 입력된 문자이다.
 * 예를들어, name이 alex라고 하면, alex를 입력할 때, a와 e를 여러번 입력하면 aaaleeeeex가 된다.
 * 위와 같은 상황을 long pressed word라고 하자.
 * long pressed word면 true, 아니면 false를 반환하라.
 * 
 * 
 * 
 * 
 * 문제풀이
 * 
 * 예시로 name = aalex, typed = aaaalex 라고 하자.
 * 
 * 먼저 name의 인덱스가 되어줄 변수 int j를 선언한다.
 * 
 * j는 name의 인덱스가 됨과 동시에, typed와 name[j]가 얼마나 일치하는지 알려줄 것이다.
 * 
 * typed를 for 문을 돌리면서 typed가 name[j]와 일치하는지 확인한다.
 * 
 * 위의 에시의 경우, index가 0과 1일때 name, typed 모두 'a'로 같다.
 * 
 * index가 2가되면 name[2]는 'l'이고 typed는 'a'이다.
 * 
 * 이 때, else if문으로 넘어가면서, j(name의 인덱스)는 그대로 있고, typed가 다음으로 넘어간다.
 * 
 * 그렇게 typed의 'l' 차례가 돌아오면, 위의 로직대로 비교한다. 
 * 
 * else if문에서, 만약 typed가 name[j-1]와 같다야 하는데(위 경우는 'a') 다르거나, 
 * 
 * 애초에 name과 typed의 첫번째 문자부터 다르다면(else if( j == 0)) false를 반환한다.
 * 
 * 마지막에 return j == n.length;를 하는 이유는 다음과 같다.
 * 
 * 처음에 설명했듯이, j는 name과 typed의 일치한 횟수를 뜻한다.
 * 
 * 따라서 j와 name의 길이가 같다면, name의 모든 character가 일치했다고 볼 수 있다.
 * 
 * 
 */


package String;

public class LongPressedName925{
	public static boolean isLongPressedName(String name, String typed) {
		if(typed.length() < name.length()) return false;
        char[] t = typed.toCharArray();
        char[] n = name.toCharArray();
        int j = 0;
        for (int i = 0; i < t.length; i++) {
            if(j < n.length && t[i] == n[j]) {
                j++; 
            } else if( j == 0 || n[j-1] != t[i]) {
                return false;
            }
        }
        return j == n.length;  
    }
	
	public static void main(String[] args) {
		String name = "aalexz";
		String typed = "aaaaleexz";
		System.out.println(isLongPressedName(name, typed));
	}
}
