/*
	Let's define a function f(s) over a non-empty string s, which calculates the frequency of the smallest character in s. For example, if s = "dcce" then f(s) = 2 because the smallest character is "c" and its frequency is 2.
	
	Now, given string arrays queries and words, return an integer array answer, where each answer[i] is the number of words such that f(queries[i]) < f(W), where W is a word in words.
	
	 
	
	Example 1:
	
	Input: queries = ["cbd"], words = ["zaaaz"]
	Output: [1]
	Explanation: On the first query we have f("cbd") = 1, f("zaaaz") = 3 so f("cbd") < f("zaaaz").
	Example 2:
	
	Input: queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
	Output: [1,2]
	Explanation: On the first query only f("bbb") < f("aaaa"). On the second query both f("aaa") and f("aaaa") are both > f("cc").


	
	
	
	<문제>
	
	queries와 words가 queries = ["zbbbd","fckc"], words = ["a","aa","aaa","aaaa"] 이렇게 주어진다.
	
	queries의 각 단어들을보면 알파벳들이 섞여있는데, 그 중, 사전에서 가장 먼저나오는 알파벳의 빈도수를 먼저 구하라.
	
	queries[0]의 경우 b가 d나 z보다 먼저 나오니까 b의 빈도수인 3을 구하면 되고, queries[1]인 "fckc"는 c가 k나 f보다 먼저 나오니까 c의 빈도수인 2를 구하면 된다.
	
	이와 같은 방식을 words의 단어에게도 적용했을 때, words에서 나온 빈도수가 queries에서 나온 빈도수보다 더 큰 갯수만큼 카운팅 하여, 리스트에 담아 반환하라.
	
	예를들어, "zbbbd"는 3인데, words의 원소들을 각각 위의 방식으로 카운팅 했을 때, 1,2,3,4가 나오고, "aaaa"만이 3보다 더 크므로, +1을 해주는 방식이다.
	
	"fckc"의 경우는 2인데, words에서 2보다 큰게 "aaa","aaaa" 2개이므로, [1,2]를 반환해주면 된다.
	
	
 */

package Array;

import java.util.Arrays;

public class CompareStringsByFreqOfSmallestChar1170 {
	
	
	/*
	//<문제풀이1>
	
	//String을 넣으면, 사전에서 가장 앞에 나오는 알파벳의 빈도수를 뽑아주는 메소드 생성
	
	//2중 for문 돌면서 queries와 words를 위의 메소드로 돌린 값 비교
	
	//메모리는 잘 썼는데 겁나느림..
	
	//Runtime: 518 ms, faster than 6.25% of Java online submissions for Compare Strings by Frequency of the Smallest Character.
	//Memory Usage: 39.1 MB, less than 100.00% of Java online submissions for Compare Strings by Frequency of the Smallest Character.
	
	private static int freqCount(String str)
	{
		int[] alphabet = new int[26];
		
		for(char c : str.toCharArray()) 
		{
			alphabet[c-'a']++;
		}
		
		for(int j = 0; j < 26; j++) 
		{ 
			if(alphabet[j] > 0) 
			{
				return alphabet[j];
			}
		}
		
		return -1;
	}
	
	public static int[] numSmallerByFrequency(String[] queries, String[] words) {
		int[] rst = new int[queries.length];		
		
		for(int i = 0; i < queries.length; i++)
		{
			int compare1 = freqCount(queries[i]);
			
			for(int j = 0; j < words.length; j++)
			{
				int compare2 = freqCount(words[j]);
				if(compare1 < compare2)
				{
					rst[i]++; 
				}
			}
		}
		
		return rst;
    }
	*/
	
	
	/*
	//<문제풀이2>
	
	//너무 느리길래 중복되는 부분 제거
	
	//원래는 2중 for문으로 매 queries 돌때마다 words를 위의 메소드로 돌렸는데, 맨 처음에 words메소드를 아싸리 돌려서 리스트에 값을 넣어놓고,
	
	//그 리스트를 활용하여, 불필요하게 2중for문을 쓰지않음
	
	//510ms 빨라짐
	
	//Runtime: 8 ms, faster than 65.34% of Java online submissions for Compare Strings by Frequency of the Smallest Character.
	//Memory Usage: 39.2 MB, less than 100.00% of Java online submissions for Compare Strings by Frequency of the Smallest Character.
	
	
	private static int freqCount(String str)
	{
		int[] alphabet = new int[26];
		
		for(char c : str.toCharArray()) 
		{
			alphabet[c-'a']++;
		}
		
		for(int j = 0; j < 26; j++)
		{ 
			if(alphabet[j] > 0) 
			{
				return alphabet[j];
			}
		}
		
		return -1;
	}
	
	private static int search(int[] freq, int bar)
	{
		int cnt = 0;
		
		for(int k : freq)
		{
			if(k > bar) cnt++;
		}
		
		return cnt;
	}
	
	
	
	
	public static int[] numSmallerByFrequency(String[] queries, String[] words) {
		int[] rst = new int[queries.length];
		int[] freq = new int[words.length];
		
		for(int i = 0; i < words.length; i++)
		{
			freq[i] = freqCount(words[i]);
		}
	
		
		for(int j = 0; j < queries.length; j++)
		{
			rst[j] = search(freq, freqCount(queries[j]));
		}
		
		return rst;
    }
    */
	
	
	//<문제풀이 by hpj0408>
	
	//binary search를 활용
	
	//binary search를 했을 때, left는 해당 기준에 부합하지 않는 수들
	
	//따라서 해당 기준에 부합하는 수를 찾기위해, w.length - l을 함
	
	//Runtime: 4 ms, faster than 84.32% of Java online submissions for Compare Strings by Frequency of the Smallest Character.
	//Memory Usage: 37.7 MB, less than 100.00% of Java online submissions for Compare Strings by Frequency of the Smallest Character.
	
	private static int freqCount(String str)
	{
		int[] alphabet = new int[26];
		
		for(char c : str.toCharArray()) 
		{
			alphabet[c-'a']++;
		}
		
		for(int j = 0; j < 26; j++)
		{ 
			if(alphabet[j] > 0) 
			{
				return alphabet[j];
			}
		}
		
		return -1;
	}
	
	public static int[] numSmallerByFrequency(String[] queries, String[] words) {
		int[] q = new int[queries.length];
		int[] w = new int[words.length];
		int[] rst = new int[queries.length];
		
		for(int i = 0; i < queries.length; i++)
		{
			q[i] = freqCount(queries[i]);
		}
		
		for(int j = 0; j < queries.length; j++)
		{
			w[j] = freqCount(words[j]);
		}
		Arrays.sort(w);
		
		for(int k = 0; k < queries.length; k++)
		{
			int l = 0, r = w.length-1;
			while(l <= r)
			{
				int mid = (l+r)/2;
				if(w[mid] <= q[k])
				{
					l = mid+1;
				}
				else
				{
					r = mid-1;
				}
			}
			rst[k] = w.length-l;
		}
		return rst;
	}
	


	public static void main(String[] args) {
//		String[] queries = {"cbd"};
//		String[] words = {"zaaaz"};
		
		String[] queries = {"bbb","cc"};
		String[] words = {"a","aa","aaa","aaaa"};
		
		System.out.println(numSmallerByFrequency(queries, words));
	}
}
