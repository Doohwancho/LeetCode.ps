package String;


public class ReverseWords {
	public static void main(String[] args) {
		String s = "Let's take LeetCode contest";
		//split(정규표현식)으로 스페이스바 기준으로 단어를 잘라서 넣는다.
		String[] words = s.split("\\s");
		//단어를 하나하나 이어붙이기 위해 StringBuffer를 사용한다
		StringBuffer sb = new StringBuffer();
		//split()함수로 자른 단어들ㅇ르 for문으로 돈다.
		for(String each: words) {
			//단어 하나하나를 뽑기위해 array로 만든다
			char[] array = each.toCharArray();
			int len = each.length();
			//각 단어의 알파벳을 거꾸로 뽑으면서 StringBuilder에 하나하나씩 넣는다.
			for(int i = len-1; i >= 0; i--) {
				sb.append(array[i]);
			}
			//각 단어를 거꾸로 넣는게 끝날 때 마다, 공백을 붙여준다.
			sb.append(' ');
		}
		//마지막 단어가 끝나도 공백이 붙으므로, StringBuilder 양 끝에있는 공백을 .trim()을 통해 제거한다.
		System.out.println(sb.toString().trim());
	}
}
