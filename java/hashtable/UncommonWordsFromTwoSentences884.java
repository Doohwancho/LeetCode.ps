/*
	We are given two sentences A and B.  (A sentence is a string of space separated words.  Each word consists only of lowercase letters.)
	
	A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.
	
	Return a list of all uncommon words. 
	
	You may return the list in any order.
	
	 
	
	Example 1:
	
	Input: A = "this apple is sweet", B = "this apple is sour"
	Output: ["sweet","sour"]
	Example 2:
	
	Input: A = "apple apple", B = "banana"
	Output: ["banana"]
	
	
	
	
	<문제>
	
	두 문장 A,B에 나오는 단어들 중, 단 한번만 나오는 단어들만 리스트에 담아 반환하라.
	
 */

package HashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UncommonWordsFromTwoSentences884 {
	
	/*
	//<문제풀이>
	
	//먼저 A와 B를 +연산자로 합친 후, .split()으로 단어별로 쪼개서 loop돌림. 
	
	//딕셔너리에 key값을 단어, value값을 그 단어의 빈도수로 넣은 후
	
	//빈도수가 1인 유니크 단어만 리스트에 담아 도출
	
	//이때 어레이리스트로 List<String> rst = new ArrayList<>(); 여기에 일단 담고, String[] 타입으로 리턴할 땐,
	
	//rst.toArray(new String[rst.size()]); 로 리턴
	
	//6ms. 좀 느림. 
	
	//개선점1 - String합치는 +연산자가 느리고 이것보다 더 성능좋은게 있을 수 있음
	
	//개선점2 - 정규표현식이 원체 느려서, space의 인덱스를 찾고, 그걸 기준으로 .substring()으로 단어를 쪼개서 쓰면 더 빠름
	
	//Runtime: 6 ms, faster than 23.07% of Java online submissions for Uncommon Words from Two Sentences.
	//Memory Usage: 36.1 MB, less than 100.00% of Java online submissions for Uncommon Words from Two Sentences.
	public static String[] uncommonFromSentences(String A, String B) {
        
		//step01 - A+B
		String StringAdded = A+" "+B;
		
		//step02 - after .split("\\s"), loop and put each word in a dictionary with its frequency on its value
		Map<String, Integer> map = new HashMap<>();
		
		for(String word : StringAdded.split("\\s"))
		{
			map.put(word, map.getOrDefault(word, 0)+1);
		}
		
		//step03 - loop dictionary and return those with freq 1
		List<String> rst = new ArrayList<>();
		Iterator<String> itr = map.keySet().iterator();
		
		while(itr.hasNext())
		{
			String key = itr.next();
			if(map.get(key) == 1)
			{
				rst.add(key);
			}
		}
		
        return rst.toArray(new String[rst.size()]);
    }
    */
	
	/*
	//<문제풀이2>
	
	//A+B합치고, 단어를 쪼개고, 사전적으로 가나다 순으로 재정렬 후, loop돌면서 unique한 단어 도출
	//steps가 많은게 어째 좀 느릴것 같긴하지만 일단 시도
	//역시 훨느림
	
	//Runtime: 17 ms, faster than 9.15% of Java online submissions for Uncommon Words from Two Sentences.
	//Memory Usage: 36.4 MB, less than 100.00% of Java online submissions for Uncommon Words from Two Sentences.
	
	public static String[] uncommonFromSentences(String A, String B) {	
		//step01 - A+B
		
		//문자열 2개를 더할땐, .concat()이 좋고, 여러개를 더할 땐 +가 좋다고 한다.
		//concat은 합친 문자열을 new String으로 생성. 따라서 새롭게 합친 문자열은 다른 주솟값을 가지게 됨.
		//+연산자는 StringBuilder로 변환한 후, append로 문자열을 더해준 후, toString()으로 반환하기 때문에, 많은 양의 문자열을 합칠 때 효율적. 합친 문자열의 주솟값은 기존 문자열의 주솟값과 동일.
		//(new StringBuilder(String.valueOf(str1)).append(str2).toString(); // +연산자 실제 동작방식
		//https://programmers.co.kr/learn/questions/571
		
		String StringAdded = A+" "+B;
		
		//step02 - split the words
		String[] words = StringAdded.split("\\s");
		
		//step03 - lexicographic sort using .compareTo()
		//A.compareTo(B)시, A가 B보다 사전적으로 앞에 나오면 양수, A와 B가 같은 단어면 0, A가 B보다 사전적으로 뒤에 나오면 음수를 반환한다.
		for(int i = 0; i < words.length-1; i++)
		{
			for(int j = i+1; j < words.length; j++)
			{
				if(words[i].compareTo(words[j]) > 0)
				{
					String tmp = words[i];
					words[i] = words[j];
					words[j] = tmp;
				}
			}
		}
		
		//step04 - pick unique word and return
		List<String> rst = new ArrayList<>();
		
		int idx = 1;
		while(idx < words.length)
		{
			if(words[idx].equals(words[idx-1]))
			{
				String comparison = words[idx];
				while(idx < words.length && words[idx].equals(comparison)) {idx++;}
				idx++;
			}
			else
			{
				rst.add(words[idx-1]);
				idx++;
			}
		}
		//마지막 원소는 따로 unique한지 처리해줌
		if(!words[words.length-1].equals(words[words.length-2])) rst.add(words[words.length-1]);
		
		return rst.toArray(new String[rst.size()]);
	}
	*/
	
	//<문제풀이 by lee215>
	
	//어씨 이거 문제풀이1이랑 똑같은데 왜 훨빠른거야
	
	//는 Iterator안써서 그렇다.
	
	//set일때만 쓰면 되는데 굳이 써서 느려짐.

	//Runtime: 2 ms, faster than 99.95% of Java online submissions for Uncommon Words from Two Sentences.
	//Memory Usage: 35.9 MB, less than 100.00% of Java online submissions for Uncommon Words from Two Sentences.
	
    public static String[] uncommonFromSentences(String A, String B) {
        Map<String, Integer> count = new HashMap<>();
        for (String w : (A + " " + B).split(" "))
            count.put(w, count.getOrDefault(w, 0) + 1);
        ArrayList<String> res = new ArrayList<>();
        for (String w : count.keySet())
            if (count.get(w) == 1)
                res.add(w);
        return res.toArray(new String[0]);
    }
	
	public static void main(String[] args) {
//		String A = "this apple is sweet";
//		String B = "this apple is sour";
		
//		String A = "gw pk xy";
//		String B = "gw aje zqd";
		
		String A = "s z z z s";
		String B = "s z ejt";
		
//		String A = "apple apple";
//		String B = "banana";
		
		System.out.println(uncommonFromSentences(A, B));
	}
}
