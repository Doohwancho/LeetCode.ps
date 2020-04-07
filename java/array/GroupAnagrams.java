package thirtyDayChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {
	
	/*
	//<Trial01 - Time Limit Exceeded>
	
	//윾
	
	public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> rst = new ArrayList<>();
        HashMap<String, int[]> container = new HashMap<>();
        
        for(String str : strs){
        	if(container.size()>0) {
        		char[] chars = str.toCharArray();
        		boolean flag = false;
        		for(Map.Entry<String, int[]> entry : container.entrySet()) {
        			String key = entry.getKey();
        			int[] val = entry.getValue();
        			
        			int[] strTmp = new int[26];
        			for(int i = 0; i < chars.length; i++) {
        				strTmp[chars[i]-97]++;
        			}
        			for(int i = 0; i < 26; i++) {
        				if(val[i] != strTmp[i]) {
        					break;
        				}
        				if(i == 25) {
        					for(List<String> temp : rst) {
        						if(temp.get(0).equals(key)) {
        							temp.add(str);
        							flag = true;
        							break;
        						}
        					}
        				}
        			}
        			if(flag) {
        				break;
        			}
        		}
        		if(flag) {
        			continue;
        		}
        	} 
        	
            char[] chars = str.toCharArray();
            int[] alphabet = new int[26];
            
            for(int i = 0; i < chars.length; i++) {
            	alphabet[chars[i]-97]++;
            }
            container.put(str, alphabet);
            List<String> temp = new ArrayList<>();
            temp.add(str);
            rst.add(temp);
        }
        return rst;
    }
    */
	
	//<문제풀이1 by legendaryengineer>
	
	//map을 활용하는데, key값을 Arrays.sort(str)으로 넣는 방법
	
	//101 / 101 test cases passed.
	//Status: Accepted
	//Runtime: 20 ms
	//Memory Usage: 54.4 MB
    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for(String str : strs){
            char[] ch = str.toCharArray();
            Arrays.sort(ch);
            /*
             	Arrays.sort(ch)를 아래와 같이 바꿀수도 있다.
             	
	            char[] ca = new char[26];
	            for (char c : s.toCharArray()) ca[c - 'a']++;
	            String keyStr = String.valueOf(ca);
             */
            String sortedStr = String.valueOf(ch);
            if(!map.containsKey(sortedStr)) map.put(sortedStr, new ArrayList<>());
            map.get(sortedStr).add(str);
        }
        return new ArrayList<>(map.values());
    }
	
	
	public static void main(String[] args) {
		String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
		System.out.println(groupAnagrams(strs));
		
		System.out.println("abc".contains("b"));
	}
}
