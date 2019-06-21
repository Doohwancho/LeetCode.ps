package String;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;


public class BuddyStrings859ver2 {
	public static boolean buddyStrings(String A, String B) {
        if (A.length() != B.length())   return false;
        
        if (A.equals(B)) {                                //A = "aa", B = "aa"; 와 A = "ab", B = "ab"; 구분
            Set<Character> set = new HashSet();
            for (char ch : A.toCharArray()) set.add(ch);
            return set.size() < A.length();
        }
        
        List<Integer> diff = new ArrayList();
        
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) != B.charAt(i)) {
                diff.add(i);
                if (diff.size() > 2)    return false;      //다른 알파벳을 비교하여, 다른것이 2개보다 더 많이 있다면, swap해도 같지 않다는 뜻이므로 false 반환
            }
        }
        
        return diff.size() == 2 && 
            A.charAt(diff.get(0)) == B.charAt(diff.get(1)) &&  //그냥 return diff.size() == 2에서 끝내지 않는 이유는, A = "abcaa", B = "abcbb"; 때문
            A.charAt(diff.get(1)) == B.charAt(diff.get(0));
    }
	
	public static void main(String[] args) {
		String A = "abcaa", B = "abcbb";
		System.out.println(buddyStrings(A, B));
	}
	

}
