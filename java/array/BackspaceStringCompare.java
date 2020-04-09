package thirtyDayChallenge;

public class BackspaceStringCompare {
	
	//<문제풀이1>
	
	//104 / 104 test cases passed.
	//Status: Accepted
	//Runtime: 0 ms
	//Memory Usage: 37.5 MB
    private static String filter(String str) {
		StringBuilder sb = new StringBuilder(); //stringbuilder로 알파벳 하나하나를 차곡차곡 .append()로 쌓자!
		
        for(int i = 0; i < str.length(); i++) {
        	if(str.charAt(i)-97 >= 0) { //'a'-97은 0이고 'z'-97은 26인가? 25인가 그럴거고 '#'-97은 마이너스값이기 때문. 원래는 .alpha()쓰려고 했는데, String type(wrapper class)를 char type(primitive type)으로 바꾸니, .alpha()함수가 지원이 안되서 이렇게 함.
        		sb.append(str.charAt(i)); //알파벳이면 stringbuilder에 붙이고
        	} else {
        		if(sb.length() > 0) sb.deleteCharAt(sb.length()-1); //알파벳이 아니면(=='#'이면 stringbuilder에서 하나 까. 근데 stringbuilder가 없는데 하나까면 array out of bound 에러뜨니까, stringbuilder가 있을때만 까.
        	}
        }
        return sb.toString(); //stringbuilder를 String type으로 형변환 하는거 잊지 말구
	}
	
    public boolean backspaceCompare(String S, String T) {
    	return filter(S).equals(filter(T)); //어짜피 S나 T나 같은 로직의 loop돌리는건데 메소드 하나로 퉁 치자. 그리고 ==말고 .equals()한 까닭은 ==은 객체의 주소를 비교하는데 .equals()는 내용을 비교하기 때문.
    }
}
