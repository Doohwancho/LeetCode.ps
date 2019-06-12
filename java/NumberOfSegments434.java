/*
 * Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.

Please note that the string does not contain any non-printable characters.

Example:

Input: "Hello, my name is John"
Output: 5


<문제>

문장이 주어지면, 스페이스바를 기준으로 나누어진 단어의 총 갯수를 구하라.
예를들어, s = "Hello, my name is John" 인 경우, "Hello," , "my" , "name" , "is" , "John" 이므로, 5를 반환한다.


<문제풀이>

.split()은 괄호 안의 정규표현식을 기준으로 문장을 잘라주는 역할을 한다. 우리는 스페이스를 기준으로 문장을 잘라줄 것이므로, 스페이스바 \\s와 1~무한대 갯수를 의미하는 +를 붙여서
단어와 단어사이, 스페이스가 1개~무한개가 있으면, 그 스페이스바를 기준으로 단어를 쪼개준다.
단, .split()은 단어와 단어 사이를 쪼개주는 함수이기 때문에,  "    foo    bar"같은 경우, foo 1칸앞에 스페이스는 자르고 그 앞에 스페이스바들을 단어로 인식하므로,
split()을 적용하기전, 문장의 양 끝쪽에 스페이스를 없애주는 .trim()을 사용한다.
반환할때는 ? 연산자를 이용하여, 괄호안의 조건이 참이면 ? 뒤의 명령어를 실행하고, 거짓이면 0을 반환하게 설정해 놓았다.  

 */

package String;

public class NumberOfSegments434 {
	public static int countSegments(String s)
	{
		String[] splited = s.trim().split("\\s+");
		return (s.trim().length() > 0) ? splited.length : 0;
	}
	
	public static void main(String[] args) {
		//String s = "Hello, my name is John";
		String s = "    foo    bar";
		System.out.println(countSegments(s));
	}

}
