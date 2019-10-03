package HashTable;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings205 {
	
	/*
	//<문제풀이1>
	
	//key:value가 char형태인 맵을 만들어서, 매 loop마다 s의 알파벳 : t의 알파벳을 key:value 형태로 map에 넣는다.
	
	//index 1번부터 s와 t가 isomorphic인가 판단하는데,
	
	//만약 s의 알파벳이 이전에 나왔다면(map.keys에 있다면) 이전에 key와 매칭되는 value값이 t의 알파벳과 동일한지 비교해 주면 된다.
	
	//else문은 s의 알파벳이 처음 나온다는 말이니까, s = "aa" t = "ab"에서 처럼, a:a 매칭된 상황에서, index가 1일때, t의 알파벳 b가 기존 a:a에 맞는지만 보면 됨.
	
	//짱느림. 메모리도 최악. 헐.
	
	//Runtime: 1357 ms, faster than 5.22% of Java online submissions for Isomorphic Strings.
	//Memory Usage: 39.6 MB, less than 6.14% of Java online submissions for Isomorphic Strings.
	
	public static boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
		
		int sLen = s.length();
		int tLen = t.length();
		
		if(sLen != tLen) return false;
		
		for(int i = 0; i < sLen; i++) {
			if(i != 0) {
				if(map.containsKey(s.charAt(i))) {
					if(map.get(s.charAt(i)) != t.charAt(i))
						return false;
				}
				else if(map.containsValue(t.charAt(i))) {
					return false;	
				}
			}
			map.put(s.charAt(i), t.charAt(i));
			System.out.println(map);
		}
		
		return true;
    }
    */
	
	
	/*
	//<Trial01>
	
	//int[]를 활용해서 풀어봄
	
	//s에서 나오는 알파벳을 keys라는 int[]에 저장하고, t에서 나오는 알파벳을 values int[]에 저장하고, s+t 둘이 합한 값을 match라는 int[]에 저장함.
	
	//매 loop마다, s,m,match의 값을 +1해줌
	
	//그러면서 key가 이미 나왔는데, s+t가 match에 이미 없거나, value에 t의 값이 미리 심어져 있지 않았을 경우 false반환.
	
	//key가 이미 나와있지 않은 경우라면, t의 값이 처음 나왔기 때문에, value에 없어야 한다. 이 조건이 충족 안되면 false.
	
	//	s = "egg", t = "add";
		
	//	keys   : 0  0  0  0  1  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  
	//	values : 1  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  
	//	match  : 0  0  0  0  1  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  
	//
	//	key    : 0  0  0  0  1  0  1  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  
	//	values : 1  0  0  1  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  
	//	match  : 0  0  0  0  1  0  0  0  0  1  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  
	//
	//	key    : 0  0  0  0  1  0  2  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  
	//	values : 1  0  0  2  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  
	//	match  : 0  0  0  0  1  0  0  0  0  2  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  

	//true
	
	
	//문제가 좀 더럽다. 
	
	//처음엔 알파벳만 나올것 처럼 보여서 알파벳버전만 만들었더니,
	
	//갑분싸 숫자가 나오길래, 숫자 버전도 만들었더니,
	
	//이젠 특수문자도 나온다.
	
	//숫자+알파벳+특수문자를 모두 커버 가능한 int[]를 겁나 크게 만들어서 하면 될것같은데,
	
	//지쳤다. 2시간째다.
	
	
	public static boolean isIsomorphic(String s, String t) {
		if(s.length() != t.length()) return false;
        if(s.length() == 0 && t.length() == 0) return true;
        
        
		if(Character.isAlphabetic(s.charAt(0))) {
			int[] match = new int[51]; //245
			int[] keys = new int[26];
			int[] values = new int[26];
			
			for(int i = 0; i < s.length(); i++) {
				if(keys[s.charAt(i)-'a'] > 0) {
					if(match[s.charAt(i)-'a' + t.charAt(i)-'a'] == 0 || values[t.charAt(i)-'a'] == 0) return false;
				}
				else if(values[t.charAt(i)-'a'] > 0) return false;
				
				keys[s.charAt(i)-'a']++;
				values[t.charAt(i)-'a']++;
				match[s.charAt(i)-'a'+t.charAt(i)-'a']++;
			}
		}
		else {
			int[] match = new int[19]; 
			int[] keys = new int[10];
			int[] values = new int[10];
						
			for(int i = 0; i < s.length(); i++) {
				if(keys[s.charAt(i)-'0'] > 0) {
					if(match[s.charAt(i)-'0' + t.charAt(i)-'0'] == 0 || values[t.charAt(i)-'0'] == 0) return false;
				}
				else if(values[t.charAt(i)-'0'] > 0) return false;
				
				keys[s.charAt(i)-'0']++;
				values[t.charAt(i)-'0']++;
				match[s.charAt(i)-'0'+t.charAt(i)-'0']++;
			}
		}
		return true;
	}
	*/
	/*
	
	//<문제풀이2 by StefanPochmann>
	
	//loop돌면서, key를 s와 t의 알파벳, value를 해당 알파벳 인덱스로 넣어서, 해당 인덱스가 다르면 false를 반환하는 방법.
	
	//좀 특이한 점은, m.put(s.charAt(i), i)을 print 하면, 처음 값을 넣을 땐, null이 나오는데, 중복된 값을 넣을 땐, 이전에 넣었던 알파벳의 index가 나온다.
	
	//2번째 나오는 알파벳들의 index를 비교하여 다르면 false를 반환하는 구문이다.
	
	//또한, t의 값을 넣을 땐, +""로 String화 시켜서 넣었기 때문에, s,t모두 같은 알파벳이 있었더라도, 다른 형태로 다른 key값으로 들어간다.
	
	//이러한 이유때문에 Map선언시 타입선언을 안해준 것이다.
	
	
	//Runtime: 22 ms, faster than 9.51% of Java online submissions for Isomorphic Strings.
	//Memory Usage: 38.2 MB, less than 49.12% of Java online submissions for Isomorphic Strings.
	
	
	public static boolean isIsomorphic(String s, String t) {
        Map m = new HashMap();
        for (Integer i=0; i<s.length(); ++i) {
            if (m.put(s.charAt(i), i) != m.put(t.charAt(i)+"", i))
                return false;
        }
        return true;
	}
	*/
    
    
	//<문제풀이3 by xcv58>
	
	//Trial01에서 모든 숫자,특수문자까지 포함하려면, 큰 int[]을 선언하여 거기에 넣는다고 하였는데, 이를 실제로 실행한 사람.
	
	//물론 가장 빠르고 메모리도 적게 썼다.
	
	//int[]를 두개 만들어서, s꺼의 int[]에다가는 index : s의 알파벳, value : 해당 s알파벳과 매칭되는 t알파벳을 넣고,
	
	//두번째 int[]에다는 같은 방법으로 t것도 만듬.
	
	//s = "aa", t = "ab"나 s = "ab", t = "aa"에서, s->t는 매칭되지만 t->s는 매칭 안되고, 그 반대로도 안되는 경우만 뽑아 false를 반환해줌.
	
	//Runtime: 4 ms, faster than 87.46% of Java online submissions for Isomorphic Strings.
	//Memory Usage: 37.2 MB, less than 100.00% of Java online submissions for Isomorphic Strings.
	
	
	public static boolean isIsomorphic(String s, String t) {
        char[] map1 = new char[256], map2 = new char[256];
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);
            if (!map(a, b, map1) || !map(b, a, map2)) { return false; }
        }
        return true;
    }

    private static boolean map(char a, char b, char[] map) {
        if (map[a] == 0) { map[a] = b; }
        return map[a] == b;
    }
    
	
	public static void main(String[] args) {		
		String s = "egg"; 
		String t = "add"; 
		
		//String s = "ab"; 
		//String t = "aa"; 
		
		//String s = "13"; 
		//String t = "42";
		
		//String s = "";
		//String t = "";
		
//		String s = "abab";
//		String t = "baba";
		
		
		System.out.println(isIsomorphic(s,t));
	}
}
