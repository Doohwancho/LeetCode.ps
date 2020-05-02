package mayChallenge;

import java.util.HashSet;
import java.util.Set;

public class JewelsAndStones {
	
	//<문제풀이1>
	
	//hint가
	
	//for each stone, check if it is a jewel
	
	//인거 실화냐?
	
	//이거 보고 처음 든 생각
	
	//그래서 뭘 어쩌라는거지? 약올리는건가?
	
	//strings J representing the types of stones that are jewels, and S representing the stones you have. 
	
	//String J가 "aA"잖어? 그럼 수많은 원석 a~Z중에 'a'와 'A'만 보석이고 나머지는 그냥 원석이라는거잖아?
	
	//근데 내가 가진 String S = "aAAbbbb"는 원석들의 집합이고.
	
	//일단 원석들을 하나씩 떼서 봐야겠지?
	
	//그럴때 필요한게 .toCharArray()
	
	//String형태의 "aAAbbbb"를 char[]타입의 {'a','A','A','b','b','b','b'}로 만들어줘
	
	//그걸 for문으로 돌면서 하나씩 내가 가진 원석이 보석'a'나 'A'인지 확인하는거야
	
	//물론 2중 for문을 사용해서, 
	
//	for(char s : S.toCharArray()){
//        for(char j : J.toCharArray()) {
//        	if(s==j) {
//        		rst++;
//        		break;
//        	}
//        }
//    }
//	return rst;
	
	//해줘도 되는데, 그러면 간지가 조금 안나니까
	
	//뭔가 아는척을 하기위해 Set을 쓴다.
	
	//set을 왜쓰냐면 .contains()라는 메서드가 있는데,
	
	//.contains(alphabet)하면, alphabet이 set안에 포함되어있는지 알려주는거임.
	
	//이 경우엔, 내가 가진 원석중에 보석이 있는지 알아보는 거겠지.
	
	//그래서 set에 보석들을 넣고, .contains()메서드로 내가 가진 원석들을 for문으로 loop돌때마다 set에 있는 보석리스트에 있는지 확인하는 방법이지.
	
	//254 / 254 test cases passed.
	//Status: Accepted
	//Runtime: 2 ms
	//Memory Usage: 39.8 MB
	
	public int numJewelsInStones(String J, String S) {
        Set<Character> s = new HashSet<>();
        int rst = 0;
        for(char c : J.toCharArray()) {
        	s.add(c);
        }
        for(char c : S.toCharArray()){
            if(s.contains(c)){
                rst++;
            }
        }
        return rst;
    }
}
