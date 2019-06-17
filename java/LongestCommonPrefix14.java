/*
 * 
	Write a function to find the longest common prefix string amongst an array of strings.
	
	If there is no common prefix, return an empty string "".
	
	Example 1:
	
	Input: ["flower","flow","flight"]
	Output: "fl"
	Example 2:
	
	Input: ["dog","racecar","car"]
	Output: ""
	Explanation: There is no common prefix among the input strings.


	<문제>
	["flower","flow","flight"]와 같은 리스트가 주어지면, 각 단어들의 맨 앞 알파벳부터 최대한 중복되는 알파벳을 반환하라.
	예를들어 위의 예시 경우, "fl"까지가 세 단어 "flower","flow","flight"에 가장 앞에 중복된다.
	만약 아무 알파벳도 중복되지 않는다면 ""(빈 스트링)을 반환한다.
	

	<문제 풀이 - Trial>
	주어진 String[]이 ["a"]처럼 하나만 주어졌을때, "a"를 반환해야 하지만 ""를 반환해서 에러.
	단, 위의 에러는 부당한게, 문제 자체가 longest common prefix이기 때문에, common, 즉 2개 이상의 단어들의 prefix를 반환해야 함.
	따라서 단어가 한개가 주어지면, 공통되는 prefix를 구하기가 불가능 하기 때문에 ""를 반환하는 것이 맞다고 생각.
	
	
	1. find the number of elements & the shortest length of strs elements
	2. for문으로 돌면서, if strs[0][i]==strs[1][i]==strs[2][i] ... -> StringBuilder.add()
	3. StrinbBuilder type을 .toString()을 이용하여 String type으로 바꾼 뒤, return
	
	
	<문제풀이 - by jeantimex>
	
	
	위의 step02와 같은데, 여기서는 for문을 돌다가 주어진 strs의 element중 가장 끝에 도달하였을 경우나 해당 인덱스의 알파벳이 다를경우, substring()으로 다르기 전 까지의 substring을 반환
	
	public String longestCommonPrefix(String[] strs) {
	
	  if (strs == null || strs.length == 0) {
	    return "";
	  }
	  
	  for (int i = 0; i < strs[0].length(); i++) {
	    for (int j = 1; j < strs.length; j++) {
	      if (i >= strs[j].length() || strs[j].charAt(i) != strs[0].charAt(i)) {
	        return strs[0].substring(0, i);
	      }
	    }
	  }
	  
	  return strs[0];
	}
	
	
	
	
 */

package String;

public class LongestCommonPrefix14 {
	public static String longestCommonPrefix(String[] strs)
	{
		//step01
		int numberOfElements = strs.length-1;
		int minimumLength = 99999;
		
		for(String str: strs) 
		{
			if(minimumLength > str.length())
			{
				minimumLength = str.length();
			}
		}
		
		//step02
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < minimumLength; i++)
		{
			int idx = 0;
			char identical = strs[0].charAt(i);
			
			while(idx < numberOfElements)
			{
				idx++;
				if(identical != strs[idx].charAt(i))
				{
					break;
				}
				if(idx == numberOfElements)
				{
					sb.append(strs[idx].charAt(i));
				}
			}
		}
		
		//step03
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		String[] strs = {"flower","flow","flight","flo"};
		//String[] strs = {"a"};
		
		System.out.println(longestCommonPrefix(strs));
		
	}
}
