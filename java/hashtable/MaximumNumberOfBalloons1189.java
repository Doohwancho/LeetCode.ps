/*
	Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.
	
	You can use each character in text at most once. Return the maximum number of instances that can be formed.
	
	 
	
	Example 1:
	
	
	
	Input: text = "nlaebolko"
	Output: 1
	Example 2:
	
	
	
	Input: text = "loonbalxballpoon"
	Output: 2
	Example 3:
	
	Input: text = "leetcode"
	Output: 0
	
	
	
	
	
	
 
 */


package HashTable;

public class MaximumNumberOfBalloons1189 {
	
	
	//<문제풀이>
	
	//loop돌면서 각 단어가 총 몇개있는지 new int[26]에 저장
	//변수 cnt를 만들고, balloon loop돌면서 한싸이클이 모두 돌때마다 cnt +1, 중간에 끊기면 cnt를 리턴해줌.
	
	//원샷 원킬.
	
	//Runtime: 1 ms, faster than 100.00% of Java online submissions for Maximum Number of Balloons.
	//Memory Usage: 37.8 MB, less than 100.00% of Java online submissions for Maximum Number of Balloons.
	
	public static int maxNumberOfBalloons(String text) {
		int rst = 0;
        int[] container = new int[26];
        for(char c : text.toCharArray()) container[c-'a']++;
        while(true) {
        	for(char c : "balloon".toCharArray()) if(--container[c-'a'] < 0) return rst;
        	rst++;
        }
    }
	
	public static void main(String[] args) {
		String text = "loonbalxballpoon";
		System.out.println(maxNumberOfBalloons(text));
	}
}
