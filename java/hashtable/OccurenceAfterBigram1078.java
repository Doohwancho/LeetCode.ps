/*
	Given words first and second, consider occurrences in some text of the form "first second third", where second comes immediately after first, and third comes immediately after second.
	
	For each such occurrence, add "third" to the answer, and return the answer.
	
	 
	
	Example 1:
	
	Input: text = "alice is a good girl she is a good student", first = "a", second = "good"
	Output: ["girl","student"]
	Example 2:
	
	Input: text = "we will we will rock you", first = "we", second = "will"
	Output: ["we","rock"]
	
	
	
	
	<문제>
	
	문장이 이렇게 주어진다.
	
	"we will we will rock you", first = "we", second = "will"
	
	그리고 첫번째 단어, 두번째 단어가 이렇게 주어진다.
	
	first = "we", second = "will"
	
	그럼 문장 안에서, 첫번째 단어, 두번째 단어 이후에 나오는 세번째 단어들을 리스트에 담아 반환하라.
	
	위의 경우, we와 rock이, we will 다음에 와서 ["we","rock"]을 반환해 주면 된다.
 */

package HashTable;

import java.util.ArrayList;
import java.util.List;

public class OccurenceAfterBigram1078 {
	
	/*
	//<문제풀이01>
	
	//.split("\\s")을 이용하여, 스페이스바 단위로 단어별로 끊어서 String[]으로 만들어 준 후, 해당 인덱스의 -2, -1번이 first, second이면
	
	//rst라는 문자열에 더해준다.
	
	//이때, index를 2부터 시작하는 것은 i-2,i-1을 보기 위함이고(range out of index error 피함)
	
	//==대신 .equals()을 쓰는 이유는 String을 비교시, ==을쓰면 주솟값을 비교하고 .equals()는 해당 값만 비교하기 때문.
	
	//a = "girl" b = "girl, a == b ??? 
	
	//똑같은 girl이여도, a와  b의 주솟값이 다르기 때문에 false뜸.
	
	//그러나 .equals()를 쓰면 값이 같아서 true뜸
	
	//맨 마지막에 .substring(1)을 쓰는 이유는, 맨 처음에 빈 스페이스가 하나 들어감(container= container+" "+temp[i];)
	
	//이 space 이후부터 쪼갠다는 뜻.
	
	//3ms뜨는데 정규표현식 말고 space의 인덱스를 찾아서 .substring으로 대체하면 더 빨라질 듯 싶다.
	
	//Runtime: 3 ms, faster than 18.88% of Java online submissions for Occurrences After Bigram.
	//Memory Usage: 35.1 MB, less than 100.00% of Java online submissions for Occurrences After Bigram.
	
	public static String[] findOcurrences(String text, String first, String second) {
		String[] temp = text.split("\\s");		
        String container = "";
        
        for(int i = 2; i < temp.length; i++)
        {
        	if(temp[i-2].equals(first)&&temp[i-1].equals(second))
    		{
    			container= container+" "+temp[i];
    		}
        }
        
        return (container.length() > 0) ? container.substring(1).split("\\s") : new String[0];
    }
	*/
	
	/*
	
	//<문제 풀이02>
	
	//정규표현식 쓰는게 느려서 index찾고 substring써서 단어별로 쪼개는것으로 바꿔봤다.
	
	//1ms단축시켰다.
	
	//아싸
	
	//Runtime: 2 ms, faster than 21.46% of Java online submissions for Occurrences After Bigram.
	//Memory Usage: 34.8 MB, less than 100.00% of Java online submissions for Occurrences After Bigram.
	
	public static String[] findOcurrences(String text, String first, String second) {
		List<Integer> indexList = new ArrayList<>();
		int startIndex = 0;
		String firstWord = "";
		String secondWord = "";
		String rst = "";
		
		for(int i = 0; i < text.length(); i++)
		{
			if(text.charAt(i) == ' ')
			{
				indexList.add(i);
			}
		}
		indexList.add(text.length());
		
		for(int j = 0; j < indexList.size(); j++)
		{
            int endIndex = indexList.get(j);
            String currWord = text.substring(startIndex, endIndex);
			if(j > 1 && firstWord.equals(first) && secondWord.equals(second))
			{
				rst = rst + " " + currWord;
				firstWord = secondWord;
				secondWord = currWord;
			}
			else if(j == 1)
			{
				secondWord = currWord;
			}
			else if(j == 0)
			{
				firstWord = currWord;
			}
			else
			{
				firstWord = secondWord;
				secondWord = currWord;
			}
				
			startIndex = endIndex+1;
		}
		
		
		return (rst.length() > 0) ? rst.substring(1).split("\\s") : new String[0];
	}
	*/
	
	//<문제풀이 02.5>
	
	//else if문이 매 loop마다 반복되는게 비효율적이라 앞으로 뺐다.
	
	//근데 그래도 걸리는 시간은 2ms.변하지 않았다.
	
	//Runtime: 2 ms, faster than 21.46% of Java online submissions for Occurrences After Bigram.
	//Memory Usage: 34.8 MB, less than 100.00% of Java online submissions for Occurrences After Bigram.
	
	public static String[] findOcurrences(String text, String first, String second) {
		List<Integer> indexList = new ArrayList<>();
		
		for(int i = 0; i < text.length(); i++)
		{
			if(text.charAt(i) == ' ')
			{
				indexList.add(i);
			}
		}
		indexList.add(text.length());
		
		String firstWord = text.substring(0, indexList.get(0));
		String secondWord = text.substring(indexList.get(0)+1, indexList.get(1));
		String rst = "";
        int startIndex = indexList.get(1)+1;
		
		for(int j = 2; j < indexList.size(); j++)
		{
            int endIndex = indexList.get(j);
            String currWord = text.substring(startIndex, endIndex);
			if(j > 1 && firstWord.equals(first) && secondWord.equals(second))
			{
				rst = rst + " " + currWord;
				firstWord = secondWord;
				secondWord = currWord;
			}
			else
			{
				firstWord = secondWord;
				secondWord = currWord;
			}
			startIndex = endIndex+1;
		}
		
		return (rst.length() > 0) ? rst.substring(1).split("\\s") : new String[0];
    }
	
	
	
	/*
	
	//<문제풀이 by rock>
	
	//그냥 text를 .split으로 단어별로 쪼개고
	
	//for문 돌면서 i-2,i-1 비교, 맞으면 만들어놓은 리스트 rst에 더하고
	
	//리턴시 리스트를 스트링 타입의 어레이로 변환.
	
	//Runtime: 1 ms, faster than 95.69% of Java online submissions for Occurrences After Bigram.
	//Memory Usage: 34.8 MB, less than 100.00% of Java online submissions for Occurrences After Bigram.
	
	public static String[] findOcurrences(String text, String first, String second) {
		String[] words = text.split(" ");
		List<String> rst = new ArrayList<>();
		
		for(int i = 2; i < words.length; i++)
		{
			if(words[i-2].equals(first) && words[i-1].equals(second))
			{
				rst.add(words[i]);
			}
		}
		return rst.toArray(new String[0]);
	}
	
	*/
	
	public static void main(String[] args) {
		String text = "alice is a good girl she is a good student";
		String first = "a";
		String second = "good";
		
//		String text = 
//				"obo jvezipre obo jnvavldde jvezipre jvezipre jnvavldde jvezipre jvezipre jvezipre y jnvavldde jnvavldde obo jnvavldde jnvavldde obo jnvavldde jnvavldde jvezipre";
//		String first = "jnvavldde";
//		String second = "y";
		
		System.out.println(findOcurrences(text, first, second));
	}
}
