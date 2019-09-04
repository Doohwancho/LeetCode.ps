/*
	Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.
	
	 
	Example:
	
	Input: ["Hello", "Alaska", "Dad", "Peace"]
	Output: ["Alaska", "Dad"]
	
	
	
	
	
	<문제>
	
	키보드에서 가로 한줄만 써서 만들 수 있는 단어를 리스트에 넣어 반환하라.
	
	예를들어, "Dad"의 경우, 키보드 caps lock에 있는 줄의 있는 알파벳으로만 완성시킬 수 있다.
	
	"qwerty"도 그렇다.
	
	"Mom"은 아니다. Tab줄과 Shift줄 두개를 쓰기 때문이다.
 */


package HashTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KeyboardRow500 {
	
	/*
	//<문제풀이>

	//brute-force
	
	//각 줄의 알파벳을 넣은 set 3개를 만들고, 그 set을 for문 돌릴꺼니까 리스트에 담는다.
	
	//loop 돌면서 .contains()로 해당 알파벳이 각 set안에 있는지 확인한다.
	
	//느리고 메모리도 많이 잡아먹었다.
	
	//Runtime: 6 ms, faster than 8.54% of Java online submissions for Keyboard Row.
	//Memory Usage: 36.3 MB, less than 7.69% of Java online submissions for Keyboard Row.
	
	
	
//	<수정>
	
//	boolean flag = true;
//	for(int k = 0; k < len; k++)
//	{
//		if(container.get(i).contains(word[k]))
//		{
//			continue;
//		}
//		else
//		{
//			flag = false;
//			break;
//		}
//	}
//	if(flag) rst.add(words[j]);
	
	
	//불필요하게 if 여러번 쓰지말고 boolean 이용 한번만 적용하면 (words의 총 알파벳 수 - 3) * 3번만큼 if를 안써도 된다.
	
	//1ms 단축
	
	
	//Runtime: 5 ms, faster than 9.02% of Java online submissions for Keyboard Row.
	//Memory Usage: 36.2 MB, less than 7.69% of Java online submissions for Keyboard Row.
	
	public static String[] findWords(String[] words) {
		List<String> rst = new ArrayList<>();
		List<Set> container = new ArrayList<>();
		
		Set row1 = new HashSet<>();
		Set row2 = new HashSet<>();
		Set row3 = new HashSet<>();

		String[] row1Arr = {"q","w","e","r","t","y","u","i","o","p","Q","W","E","R","T","Y","U","I","O","P"};
		String[] row2Arr = {"a","s","d","f","g","h","j","k","l","A","S","D","F","G","H","J","K","L"};
		String[] row3Arr = {"z","x","c","v","b","n","m","Z","X","C","V","B","N","M"};
		
		Collections.addAll(row1, row1Arr);
		Collections.addAll(row2, row2Arr);
		Collections.addAll(row3, row3Arr);
		
		container.add(row1);
		container.add(row2);
		container.add(row3);
		
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < words.length; j++)
			{
				String[] word = words[j].split("");
				int len = word.length;
				boolean flag = true;
				
				for(int k = 0; k < len; k++)
				{
					if(container.get(i).contains(word[k]))
					{
						continue;
					}
					else
					{
						flag = false;
						break;
					}
				}
				if(flag) rst.add(words[j]);
			}
		}		
		return rst.toArray(new String[0]);
    }
	*/
	
	
	//<문제풀이 by shawngao>
	
	//개선점1 : 불필요하게 set에 다때려박고 어레이에 set들을 또 넣은게 아니라, String[]에 소문자만 넣어놓고 .tolowercase()을 활용. String도 .contains()있기 때문.
	
	//개선점2 : 문자의 첫 글자가 어느 row에 들어있는지 먼저 확인 후, 그 row만 loop돌림.
	
	//똑똑하네 인정
	
	//Runtime: 1 ms, faster than 74.58% of Java online submissions for Keyboard Row.
	//Memory Usage: 34.5 MB, less than 100.00% of Java online submissions for Keyboard Row.
    private static String[] strs = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
    
    public static String[] findWords(String[] words) {
        List<String> res = new ArrayList<>();
        
        for (String word : words) {
            if (word.length() == 0) continue;
            
            int row = 0;
            for (; row < 3; row++) {
                if (inRow(word.charAt(0), row)) break;
            }
            if (row >= 3) continue;
            
            boolean flag = true;
            for (int i = 1; i < word.length(); i++) {
                if (!inRow(word.charAt(i), row)) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) {
                res.add(word);
            }
        }
        
        return res.toArray(new String[res.size()]);
    }
    
    private static boolean inRow(char c, int row) {
        if (strs[row].contains(Character.toLowerCase(c)+"")) return true;
        else return false;
    }
	
	
	public static void main(String[] args) {
		String[] words = {"Hello", "Alaska", "Dad", "Peace"};
		System.out.println(findWords(words));
	}
}
