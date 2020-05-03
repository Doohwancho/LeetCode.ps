package mayChallenge;

import java.util.HashMap;
import java.util.Map;

public class RansomNote {
	
	/*
	
	//파라미터로 ransomeNote가 "aa", magazine이 "abca"이렇게 주어졌을 때,
	
	//ransomNote에 있는 알파벳 하나하나들이 모두 magazine에 포함되어 있으면 true, 아니면 false를 반환하라는 거잖아?
	
	//이걸 푸는 방법은 크게 두가지겠지
	
	//하나는 ransomNote의 알파벳들의 갯수를 파악해 둔 다음에, magazine을 loop돌면서 ransomNote의 알파벳들이 모두 등장하는지 확인하는 방법이고
	
	//두번째 방법은 magazine의 알파벳의 갯수를 파악해 둔 다음에, ransomNote를 loop 돌면서 ransomNote에 3번 나온게 magazine에 2번밖에 안나왔 다던가, 아예 없다면 return false하고, 
	
	//loop이 끝나도 아무 이상 없다면 return true하는 방법이지.
	
	//두번째 방법이 discuss란에 가장 조회수가 많아. 두번째 방법은 이렇게 생겼어.
	 
	//<문제풀이1>
	 */
	
	public boolean canConstruct(String ransomNote, String magazine) {
		Map<Character, Integer> magM = new HashMap<>();
		for (char c:magazine.toCharArray()){
		    int newCount = magM.getOrDefault(c, 0)+1;
		    magM.put(c, newCount);
		}
		for (char c:ransomNote.toCharArray()){
		    int newCount = magM.getOrDefault(c,0)-1;
		    if (newCount<0)
		        return false;
		    magM.put(c, newCount);
		}
		return true;
	}
	
	//근데 돌려보면 성능이 Runtime: 11 ms Memory Usage: 40 MB 나와.
	
	//근데 첫번째 방법으로 돌리면 성능이 Runtime: 3 ms, Memory Usage: 40 MB 이렇게 나와.
	
	//8ms더 빠른거지.
	
	//왜일까?
	
	//집합개념으로 생각하면, ransomNote가 magazine안에 에 포함되잖아?
	
	//ransomNote의 알파벳 갯수가 magazine보다 더 작을 수 밖에 없어.
	
	//더 크다면, 맨 처음 유효성 검사에서 return false해주어야 해. 
	
	//ransomNote가 magazine보다 사이즈가 더 작다면, map에 넣어서 빈도수 카운트 할 때, magazine보다 사이즈가 더 작은 ransomNote를 넣는게 유리해.
	
	//map에 넣는게 시간이 오래걸리니까, 사이즈가 더 작은걸 넣는거야.
	
	//그리고나서 magazine을 loop돌때 조건이 충족되면 바로 결과값을 뽑도록 하는게 8ms더 빠르네.
	
	
	//<문제풀이2>
	  
	
	//126 / 126 test cases passed.
	//Status: Accepted
	//Runtime: 3 ms
	//Memory Usage: 39.6 MB
	
    public boolean canConstruct(String ransomNote, String magazine) {
        if(ransomNote.length() == 0) return true;
        Map<Character, Integer> map = new HashMap<>();
        int cnt = 0;
        for(char c: ransomNote.toCharArray()){
            map.put(c, map.getOrDefault(c, 0)+1);
            cnt++;
            }
        for(char c : magazine.toCharArray()){
            if(map.getOrDefault(c, 0)>0){
                map.put(c, map.get(c)-1);
                cnt--;
                if(cnt == 0){
                    return true;
                    }
                }
        
            }
        return false;
    }
    
    //<문제풀이3 by yubad2000>
    
    //이 방법은 map에 넣는게 오래걸리니까, 더 빠른 int[]에 넣는거야.
    
    //String을 .toCharArray()로 쪼개면 char, 그러니까 Character type이 되는데 어떻게 int[]에 넣냐고?
    
    //'a', 'b', 이런건 다 고유 번호가 있어.
      
    //예를들어, 'a'는 97이고, 'b'는 98이고, ... 'z'는 122야.
    
    //대분자도 같은 방식이야. 'A'은 65고 'B'는 66이고, ... 'Z'은 90이야.
     
	//System.out.println('a'-97);  //result : 0
	//System.out.println('z'-122); //result : 0
	//System.out.println('A'-65);  //result : 0
	//System.out.println('Z'-90);  //result : 0
     
    //알파벳 -숫자로 나오는 숫자를 인덱스처럼 이용해서, int[]에 넣는거지. 
    
    //HashMap보다 넣고 꺼내기 훨씬 빨라서 성능이 좋아. 봐봐.
    
    //두번쨰 방법보다 1ms더 빨라잖아.
    
    //Runtime: 2 ms
    //Memory Usage: 40.1 MB
    
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote==null || ransomNote.length() == 0) return true;
        if (magazine==null || magazine.length() == 0) return false;
        int[] counts = new int[256];
        for (char c: magazine.toCharArray()) {
            counts[c]++;
        }
        for (char c: ransomNote.toCharArray()) {
            if (counts[c]==0) return false;
            counts[c]--;
        }
        return true;
    }
    */
	
	//<문제풀이4 by UpTheHell>
	
	//문제풀이3에서 int[256]이라고 나와있어서 의아했지?
	
	//왜냐하면 'a'는 97이고, 'b'는 98이고, ... 'z'는 122이라면, 알파벳 소문자가 총 26개밖에 없으니까
    
    //굳이 256개의 메모리공간이 필요없잖아?
	
	//그래서 int[]를 만들때, 소문자 알파벳 갯수인 26개만 만들고,
	
	//magazine.toCharArray()로 loop돌때, 매 알파벳마다 alphabet-'a'(==97)을 해줘서
	
	//int[]에 넣는거지.
	
	//근데 결과값을 보면 이렇게 나와. 
	
	//Runtime: 2 ms, Memory Usage: 39.7 MB
	
	//문제풀이3과 비교했을 때, 메모리 사용량은 0.4MB 아꼈는데, runtime은 똑같이 2ms야.
	
	//상식적으로 int[]에서 값을 넣고 빼올떄, 256개를 뒤져서 빼오는것보다 26개만 뒤져서 빼오는게 더 빠를텐데 왜 그럴까?
	
	//왜냐하면, 문제풀이4에서는 매 loop마다 alphabet-'a'연산을 하기 때문에, 더 느려져서 그런거야.
	
	//int[]의 사이즈가 작아져서 찾는데 시간이 단축되도, 막상 매 loop마다 -'a'연산 때문에 시간이 연장되서 +- 제로인 셈이지.
	
	//근데 magazine의 알파벳 갯수가 많을수록, 문제풀이3이 문제풀이 4보다 더 빠를거야. 메모리는 조금 더 잡아먹어도 -'a'연산을 안하잖아.
	
	public boolean canConstruct(String ransomNote, String magazine) {
        int[] cnt = new int[26];
        for(char c: magazine.toCharArray()){
            cnt[c-'a']++;
        }
        
        for(char c: ransomNote.toCharArray()){
            if(--cnt[c-'a']<0)
                return false;
        }
        return true;
    }
	
    public static void main(String[] args) {
		System.out.println('a'-97);
		System.out.println('z'-122);
		System.out.println('A'-65);
		System.out.println('Z'-90);
	}
    
}
