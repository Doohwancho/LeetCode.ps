package september;

import java.util.HashMap;
import java.util.Map;

public class WordPattern {

	
	//<문제풀이1>
	
	//Runtime: 2 ms
	//Memory Usage: 37.3 MB
	
    public boolean wordPattern(String pattern, String str) {
        String[] strList = str.split("\\s");
        if(pattern.length() != strList.length) return false;
        Map<Character, String> map = new HashMap<>();
        int i = 0;
        for(String s : strList){
            char key = pattern.charAt(i);
            
            if(map.containsKey(key)){
                if(!map.get(key).equals(s)) return false;
                i++;
                continue;
            } 
            else if(map.containsValue(s)){
                for(Map.Entry<Character, String> elem : map.entrySet() ){
                    if(elem.getValue().equals(s)){
                        if(elem.getKey() != key) return false;
                        i++;
                        continue;
                    }
                }
                i++;
                continue;
            }
            map.put(key, s);
            i++;
        }
        return true;
    }
    
    
    //<문제풀이2 by StefanPochmann>
    
    //"abba"
//    "dog dog dog dog"

//    null   null
//    null   0
//    1   1
//    0   2
    
    //map.put(key, value)
    
    //원래 key가 map에 있다면, 예전 인덱스를 반환해 줌. 처음넣는거면 null반환해주고.
    
    //만약 넣었는데 한쪽만 숫자가 나왔다? 그럼 예전에 이미 넣었던거임. 근데 
    
    //예전에 넣은게 key:value가 완전 똑같으면, 양쪽 숫자가 다 같아야 됨. 
    
    //만약 양쪽이 같지 않으면 key:value 둘 중 하나가 예전에 넣은거랑 다르다는 소리.
    
    //Runtime: 0 ms
    //Memory Usage: 37.1 MB
    
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;
        Map index = new HashMap();
        for (Integer i=0; i<words.length; ++i)
            if (index.put(pattern.charAt(i), i) != index.put(words[i], i))
                return false;
        return true;
    }
}
