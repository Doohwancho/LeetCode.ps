/*
 * Every email consists of a local name and a domain name, separated by the @ sign.

For example, in alice@leetcode.com, alice is the local name, and leetcode.com is the domain name.

Besides lowercase letters, these emails may contain '.'s or '+'s.

If you add periods ('.') between some characters in the local name part of an email address, mail sent there will be forwarded to the same address without dots in the local name.  For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.  (Note that this rule does not apply for domain names.)

If you add a plus ('+') in the local name, everything after the first plus sign will be ignored. This allows certain emails to be filtered, for example m.y+name@email.com will be forwarded to my@email.com.  (Again, this rule does not apply for domain names.)

It is possible to use both of these rules at the same time.

Given a list of emails, we send one email to each address in the list.  How many different addresses actually receive mails? 

 
Example 1:
Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
Output: 2
Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails

*/

package String;

import java.util.*;

public class UniqueEmailAddresses {
	public static void main(String[] args) {
		
		String[] emails = {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
		
		//Q1. Set<String> set = new HashSet<>(); ???
		//HashSet은 순서가 필요없는 데이터를 hash table에 저장한다. Set은 HashSet, TreeSet, LinkedHashSet이 있는데, 이 Set 중에 가장 성능이 좋음. set은 
		Set<String> set = new HashSet<>();
        for (String email : emails) {
        	//StringBuilder를 통해 문자열 합치기
        	//StringBuilder에 관한 설명은 아래 링크 참조
        	//https://m.blog.naver.com/PostView.nhn?blogId=ninace&logNo=80210003143&proxyReferer=https%3A%2F%2Fwww.google.com%2F
            StringBuilder sb = new StringBuilder();
            boolean ignore = false;
            //for문으로 한글자씩 돌면서 stringbuilder에 append시킨다. 만약 .이 나오면 무시하고, +가 나오면 이후 문자를 무시한다. @가 나오면 substring() 함수를 사용하여 domain address를 마저 stringbuilder에 넣는다.
            for (int i = 0; i < email.length(); i++) {
            	//charAt();은 파이썬에서 .index()와 같음
                char c = email.charAt(i);
                if (c == '@') {
                	/*.substring(start,end) == start부터 end 전까지 문자열 발췌 
                	 * 예제
						String str = "ABCDEFG"; //대상 문자열
						A=0 B=1 C=2 D=3 E=4 F=5 G=6의 index를 가진다.
								
						str.substring(3); 
						substring(시작위치) 결과값 = DEFG
						
						str.substring(3, 6); 
						substring(시작위치,끝위치) 결과값 = DEF
                	 */
                    sb.append(email.substring(i, email.length()));
                    break;
                } else if (c == '+') {
                    ignore = true;
                } else if (c != '.' && !ignore) {
                    sb.append(email.charAt(i));
                }
            }
            set.add(sb.toString());
        }
        System.out.println(set.size());
    }	
				
	}

