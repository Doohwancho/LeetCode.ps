package augustChallenge;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GoatLatin {
	
	//<문제풀이1>
	
	//self-explanatory
	
	//improvement point 1)
	//set.add()넣는거 10개 한줄로 줄이기 가능.
	//for(char c : "aeiouAEIOU".toCharArray()) set.add(c);
	//이렇게. 10줄->1줄. 개2득
	
    //set선언이랑 아에이오우 넣는걸 한줄로 줄일 수 있긴 함.
    //Set<Character> set = "aeiouAEIOU".chars().mapToObj(e -> (char)e).collect(Collectors.toSet()); 
    //근데 12ms나와서 성능 떨어짐.
	//개꾸져
	//으 극혐
	
	
	//improvement point 2)
	//step1이랑 2가 10줄이 넘어가는데, 한줄로 압축가능
	//sb.append(set.contains(s.charAt(0)) ? s+"ma" : s.substring(1)+s.charAt(0)+"ma");
	//근데 string+string이라 성능이 구려짐.
	//stringbuilder쓰는걸 권고
	
	//Runtime: 4 ms
	//Memory Usage: 38.2 MB
	
	public String toGoatLatin(String str) {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');
        
        
        
        StringBuilder sb = new StringBuilder();
        int cnt = 1;
        for(String s : str.split("\\s")) {
        	
            //1 - vowel : add "ma" at the end
            if(set.contains(s.charAt(0))){
                sb.append(s);
                sb.append("ma");
            } 
            //2 - consonant : 1st letter to last + add "ma" at the end
            else {
                for(int i = 1; i < s.length(); i++){
                    sb.append(s.charAt(i));
                }
                sb.append(s.charAt(0));
                sb.append("ma");
            }
            
            //3 - add "a" 'cnt' times
            for(int i = 0; i < cnt; i++){
                sb.append("a");
            }
            sb.append(" ");
            cnt++;
		}
        return sb.toString().trim();
    }

}
