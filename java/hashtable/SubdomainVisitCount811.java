/*
	A website domain like "discuss.leetcode.com" consists of various subdomains. At the top level, we have "com", at the next level, we have "leetcode.com", and at the lowest level, "discuss.leetcode.com". When we visit a domain like "discuss.leetcode.com", we will also visit the parent domains "leetcode.com" and "com" implicitly.
	
	Now, call a "count-paired domain" to be a count (representing the number of visits this domain received), followed by a space, followed by the address. An example of a count-paired domain might be "9001 discuss.leetcode.com".
	
	We are given a list cpdomains of count-paired domains. We would like a list of count-paired domains, (in the same format as the input, and in any order), that explicitly counts the number of visits to each subdomain.
	
	Example 1:
	Input: 
	["9001 discuss.leetcode.com"]
	Output: 
	["9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"]
	Explanation: 
	We only have one website domain: "discuss.leetcode.com". As discussed above, the subdomain "leetcode.com" and "com" will also be visited. So they will all be visited 9001 times.
	
	Example 2:
	Input: 
	["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
	Output: 
	["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
	Explanation: 
	We will visit "google.mail.com" 900 times, "yahoo.com" 50 times, "intel.mail.com" once and "wiki.org" 5 times. For the subdomains, we will visit "mail.com" 900 + 1 = 901 times, "com" 900 + 50 + 1 = 951 times, and "org" 5 times.
 
 
 	
 	
 	<문제>
 	
 	google.mail.com 이렇게 메일이 있으면, google은 가장 상위 도메인, mail은 중간 도메인, com은 하위 도메인이다.
 	
 	가장 상위 도메인을 가기 위해선 가장 하위도메인을 통과해야 한다.
 	
 	예를들어, 10 google.mail.com이 있고, 5 yahoo.mail.com이 있으면, (숫자는 방문횟수를 뜻함)
 	
 	google.mail.com에 10번 방문,
 	
 	yahoo.mail.com에 5번 방문,
 	
 	mail.com에 15번 방문(10+5),
 	
 	com에 15번 방문(10+5)한 것이다.
 	
 	이런 방식으로, 리스트의 여러 주소와 방문횟수가 주어지면, 모든 도메인의 각각 몇번씩 방문했는지 구하라.

 */

package HashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SubdomainVisitCount811 {
	
	/*
	//<문제풀이>
	
	//먼저 for문으로 String[]을 String으로 쪼갠다.
	
	//String의 각 주소+빈도수가 "900 google.mail.com"라고 가정하면, 
	
	//둘을 정규표현식으로 .split("스페이스")로 쪼갠 후,
	
	//빈도수를 Integer.parseInt()로 형변환 해서 넣어주고, 주소도 따로 나누어 변수에 담는다.
	
	//그 후, 주소는 또다시 .을 기준으로 쪼갠 후, 뒤에서부터 딕셔너리(map)에 .을 붙이며 넣어준다(이때 value는 방금 구한 빈도수).
	
	//Runtime: 25 ms, faster than 31.98% of Java online submissions for Subdomain Visit Count.
	//Memory Usage: 38 MB, less than 96.97% of Java online submissions for Subdomain Visit Count.
	public static List<String> subdomainVisits(String[] cpdomains) {
        
		List<String> rst = new ArrayList();
        Map<String, Integer> map = new HashMap<>();
        
        for(String str : cpdomains)
        {
        	String[] element = str.split("\\s");
        	int freq = Integer.parseInt(element[0]);
        	String address = element[1];
        	
        	String[] parse = address.split("\\.");
        	String pieces = "";
        	
        	for(int i = parse.length-1; i>=0 ; i--)
        	{
        		pieces = parse[i] + pieces;
        		map.put(pieces, map.getOrDefault(pieces, 0)+freq);
        		pieces = "." + pieces;
        	}
        }
        
        Iterator<String> keys = map.keySet().iterator();
        while(keys.hasNext())
        {
        	String key = keys.next();
        	rst.add(map.get(key)+" "+key);
        }
        
        return rst;
    }
	*/
	
	//<문제풀이 by lee215>
	
	//위와 방식은 같은데 정규표현식을 쓰지않고, space의 인덱스를 기준으로 .substring()이용.
	
	//이 방식이 16ms더 빠르다.
	
	//Runtime: 9 ms, faster than 81.66% of Java online submissions for Subdomain Visit Count.
	//Memory Usage: 37 MB, less than 96.97% of Java online submissions for Subdomain Visit Count.
	
	public static List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap();
        for (String cd : cpdomains) {
            int i = cd.indexOf(' ');
            int n = Integer.valueOf(cd.substring(0, i));
            String s = cd.substring(i + 1);
            for (i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == '.') {
                    String d = s.substring(i + 1);
                    map.put(d, map.getOrDefault(d, 0) + n);
                }
            }
            map.put(s, map.getOrDefault(s, 0) + n);
        }

        List<String> res = new ArrayList();
        for (String d : map.keySet()) res.add(map.get(d) + " " + d);
        return res;
    }
    

	public static void main(String[] args) {
		String[] cpdomains = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
		
		System.out.println(subdomainVisits(cpdomains));
	}
}
