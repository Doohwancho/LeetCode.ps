package september;

public class LengthOfLastWord {
	
	//<문제풀이1>
	
	//trim은 양끝단에 불필요한 공백 없애주는 애
	
	//"a " 이렇게 나왔을 경우 대비
	
	//Runtime: 1 ms
	//Memory Usage: 39.5 MB
	
    public int lengthOfLastWord(String s) {
        s = s.trim();
        return s.length() - s.lastIndexOf(" ") - 1;
    }
}
