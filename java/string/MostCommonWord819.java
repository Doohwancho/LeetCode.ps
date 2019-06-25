/*
 * 문제
 * 
	Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.
	
	Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.
	
	 
	
	Example:
	
	Input: 
	paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
	banned = ["hit"]
	Output: "ball"
	Explanation: 
	"hit" occurs 3 times, but it is a banned word.
	"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
	Note that words in the paragraph are not case sensitive,
	that punctuation is ignored (even if adjacent to words, such as "ball,"), 
	and that "hit" isn't the answer even though it occurs more because it is banned.
 * 
 * 
 * paragraph와 banned단어가 주어지면, paragraph에서 banned단어를 제외한 단어들 중, 가장 빈도수가 높은 단어를 반환하라.
 * 
 * 
 * 
 * 
 * 
 * 문제풀이
 * 
 * step01) paragraph를 소문자화
 * step02) 특수문자(",',;,!,? 등) .replace()로 제거 + 중복되는 space는 하나의 space로 치환
 * step03) .split()을 이용하여 스페이스 단위로 단어 쪼개기
 * step04) Map에 banned 단어 추가
 * step05) hashtable에 key:value 형태로 단어:빈도수 입력(입력시 Map에 등록되어있는 banned단어는 제외). 단어 빈도수 카운팅 방법은 아래 stackoverflow 참조
 * https://stackoverflow.com/questions/81346/most-efficient-way-to-increment-a-map-value-in-java
 * step06) 가장 빈도수가 많은 단어 출력
 * 
 */


package String;

import java.util.*;


class MutableInt{
	int value = 1;
	public void increment() { ++value;      }
	public int  get() 		{ return value; }
	}


public class MostCommonWord819 {

	public static String mostCommonWord(String paragraph, String[] banned)
	{
		Map<String, MutableInt> freq = new HashMap<String, MutableInt>();    
		Set<String> bannedset = new HashSet<String>();
		String mostCommon = "";
		int maxNum = 0;
		
		//step01
		String lowerP = paragraph.toLowerCase();
		
		//step02 & step03
		String[] words = lowerP.replace(',', ' ').replace('.', ' ').replace('!', ' ').replace('?', ' ').replace('\'', ' ').replace(';', ' ').replace('\"', ' ').replaceAll("\\s\\s"," ").split("\\s");

		//step04
		for(String bannedWord : banned)
		{
			bannedset.add(bannedWord);
		}
		
		//step05
		for(String wo : words)
		{
			MutableInt count = freq.get(wo);

			if(!bannedset.contains(wo) && count == null)
			{
				freq.put(wo,  new MutableInt());
			}
			else if(!bannedset.contains(wo))
			{
				count.increment();
			}
		}
		
		//step06
		for(String a : freq.keySet())
		{
			if(freq.get(a).get() > maxNum)
			{
				maxNum = freq.get(a).get();
				mostCommon = a;
			}
		}
		return mostCommon;
	}
   
	

	public static void main(String[] args) {
		String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
		String[] banned = {"hit"};
		
		System.out.println(mostCommonWord(paragraph, banned));
		
	}
}
