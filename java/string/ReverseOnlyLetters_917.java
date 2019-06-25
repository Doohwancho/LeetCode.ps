package String;

/*
 * Given a string S, return the "reversed" string where all characters that are not a letter stay in the same place, and all letters reverse their positions.
Example 1:
Input: "ab-cd"
Output: "dc-ba"

Example 2:
Input: "a-bC-dEf-ghIj"
Output: "j-Ih-gfE-dCba"

Example 3:
Input: "Test1ng-Leet=code-Q!"
Output: "Qedo1ct-eeLg=ntse-T!"
 */
import java.util.*;

public class ReverseOnlyLetters_917 {
	private static void swap(char[] cs, int i, int j) {
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
    }
	
	public static void main(String[] args) {
		String S = "-1abc-de";
		
		char[] cs = S.toCharArray();
        int i = 0, j = cs.length - 1;
        
        while (i < j) {
            while (i < j && !Character.isLetter(cs[i])) { //if 대신 while로 하는 이유는, 한번이상 연속적으로 알파벳이 아닌게 나왔을 경우, 건너뛰어야 하기 때문. 
                i++;
            }
            while (i < j && !Character.isLetter(cs[j])) {
                j--;
            }
            swap(cs, i++, j--);
        }
        System.out.println(new String(cs));
    }
}