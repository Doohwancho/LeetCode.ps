package augustChallenge;

public class ValidPalindrome {
	
	//<Trial01>
	
	//어씨 숫자도 나오네?
	
	//어씨 ".,"나오면 while문 끝났을때 i랑 j가 범위를 넘어버려서 index not found에러뜨네?
	
	public static boolean isPalindrome(String s) {
		char[] s_ = s.toCharArray();
		for(int i = 0, j = s_.length-1; i < j; i++, j--) {
			while(!Character.isAlphabetic(s_[i])) i++;
			while(!Character.isAlphabetic(s_[j])) j--;
			if(Character.toLowerCase(s_[i]) != Character.toLowerCase(s_[j])) return false;
		}
		return true;
    }
	
	
	//<문제풀이1>
	
	//이거거등~
	
	//Runtime: 2 ms
	//Memory Usage: 39.4 MB
	
    public boolean isPalindrome(String s) {
        char[] s_ = s.toCharArray();
        int len = s_.length;
        
		for(int i = 0, j = s_.length-1; i < j; i++, j--) {
			while(i < len-1 && !Character.isLetterOrDigit(s_[i])) i++;
			while(j > 0 && !Character.isLetterOrDigit(s_[j])) j--;
			if(i < j && Character.toLowerCase(s_[i]) != Character.toLowerCase(s_[j])) return false;
		}
		return true;
    }
}
