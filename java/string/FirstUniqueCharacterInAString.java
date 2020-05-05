package mayChallenge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FirstUniqueCharacterInAString {
	
	//<문제풀이1 by ZachC>
	
	//가장 무난한 답안.
	
	//loop 두번만 돌면 되잖어. 근데 우린 힙스터니까 다른 방법을 찾자.
	
    public int firstUniqChar(String s) {
        int freq [] = new int[26];
        for(int i = 0; i < s.length(); i ++)
            freq [s.charAt(i) - 'a'] ++;
        for(int i = 0; i < s.length(); i ++)
            if(freq [s.charAt(i) - 'a'] == 1)
                return i;
        return -1;
    }
	
	
	//<문제풀이2>
	
	//104 / 104 test cases passed.
	//Status: Accepted
	//Runtime: 13 ms
	//Memory Usage: 39.8 MB
	
    public int firstUniqChar(String s) {
		char[] s_ = s.toCharArray();
        StringBuilder sb = new StringBuilder(s);
        Set<Character> set = new HashSet<>();
		
		for(int i = 0; i < s.length(); i++) {
			if(!set.contains(s_[i]) && sb.indexOf(s_[i]+"", i+1) < 0) { //처음 등장하면서, i+1이후에도 등장하지 않는다면, 해당 알파벳의 인덱스 반환.
				return i;
			}
            set.add(s_[i]); //한번이라도 등장했으면 set에 포함시킨다.
		}
        return -1;
    }
    
	
    
    //<문제풀이3>
    
    //불필요한 형변환을 해줘서 느리게 되네. 에고 이건 별루다
    
	//104 / 104 test cases passed.
	//Status: Accepted
	//Runtime: 21 ms
	//Memory Usage: 39.8 MB
    
    public int firstUniqChar(String s) {
        if(s.length()==0) return -1;
        List<Integer> list = new ArrayList<>();
        Set<Character> set = new HashSet<>();
        
        for(int i = 0; i < s.length(); i++) {
        	if(set.add(s.charAt(i))) {
        		list.add(s.charAt(i)-97);
        	} else {
                list.remove((Integer)(s.charAt(i)-97));
        	}
        }
    	return list.isEmpty() ? -1: s.indexOf((char)(list.get(0)+97));
    }
    
	
	//<문제풀이4 by tsuvmxwu>
	
	//문제풀이3에 알파벳의 인덱스를 어떻게 표시할까 고민하다가 꼬여서 저런 괴물이 됬는데, 
	
	//얘는 int[]에 인덱스를 저장할 때, 한번도 안나온건 0, 중복되서 나온건 1, 한번만 나온건 ~index로 넣는다.
	
	//~n을 하면, 앞에 마이너스를 달아준다. (비트연산자임. 틸트연산자라고 함.) 
	
	//s를 "leetcode"로 집어넣었을 때, indexes가 아래와 같이 나온다.
	
	//0 0 -5 -7 1 0 0 0 0 0 0 -1 0 0 -6 0 0 0 0 -4 0 0 0 0 0 0
	
	//a,b,c,d,...,x,y,z 쭈루룩이고
	
	//원래 c의 인덱스가 5인데, ~i를 해줘서 -5가 저장된걸 볼 수 있다.
	
	//두번째 for문에서는 저장된 값중에 0과 1이 아닌 것들(한번만 나온 것들) 중 가장 작은 수를 뽑는다.
	
	//가장 작은수가 가장 먼저 위치해 있기 때문.
    
    //요게 가장 성능이 좋으면서 힙스터 스럽다. 만-족

	//104 / 104 test cases passed.
	//Status: Accepted
	//Runtime: 7 ms
	//Memory Usage: 39.4 MB
	
    public int firstUniqChar(String s) {
        int[] indexes = new int[26];
        for(int i=0; i<s.length(); i++){
            int ch = s.charAt(i)-'a';
            indexes[ch] = indexes[ch]==0 ? ~i : 1;
        }
        int min = Integer.MAX_VALUE;
        for(int index : indexes) {
        	if(index<0) {
        		min=Math.min(min, ~index);
        	}
        }
        return min==Integer.MAX_VALUE ? -1 : min;
    }
    
    public static void main(String[] args) {

	}
}
