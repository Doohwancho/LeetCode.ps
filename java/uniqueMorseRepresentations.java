package String;

import java.util.*;

public class uniqueMorseRepresentations {
	public static void main(String[] args) {		
		String[] words = {"gin", "zen", "gig", "msg"};
		
		//morse code 입력
        String[] morse = {
        		".-","-...","-.-.","-..",".","..-.",
        		"--.","....","..",".---","-.-",".-..",
        		"--","-.","---",".--.","--.-",".-.","...",
        		"-","..-","...-",".--","-..-","-.--","--.."
		};
        
        //words의 각 element의 morsecode를 담을 list 생성
        List result = new ArrayList();
		
        //for문으로 words 리스트의 각 element를 돈다.
		for(String word:words)
        {
			//각 element의 morse code를 붙일 변수 생성
			String concatenatedMorse = "";

            for(int i = 0; i < word.length(); i++) 
            {
            	//컴퓨터는 인간이 쓰는 알파벳을 못알아들으므로 숫자로 인식을 하는데, 'a'은 97이고 'b'은 98 ... 'z'은 122다.
            	//word.charAt()으로 각 알파벳을 돌면서 -'a'를 해주면, 해당 알파벳이 몇번째인지 알게 된다.
            	//예를들어 (int)'c'는 99이고 (int)'a'는 97이라, c-a를하면 2가 나오고, morse code에 두번째 인덱스에 위치하는 것을 알 수 있다.
            	concatenatedMorse += morse[word.charAt(i)-'a'];
			}
            //각 element의 for문이 끝날때 마다, morse code를 만든것을 result list에 넣어준다.
            result.add(concatenatedMorse);
	    }
		
		//이렇게 각 단어들의 morse code를 만드는 것을 성공했다. 하지만 중복인 것을 제거해야 한다.
		System.out.println(result);
		
		//리스트 안 중복 제거를 위해 hashset을 사용한다.
		List<String> withDuplicates = result;
	    List<String> withoutDuplicates = new ArrayList<>(
	      new HashSet<>(withDuplicates));
	 
	    //중복을 제거 후 남은 리스트의 길이를 구한다.
	    System.out.println(withoutDuplicates.size());
    }

	}

