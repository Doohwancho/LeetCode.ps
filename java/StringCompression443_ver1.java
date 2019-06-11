/*
	<문제>
	
	char[]가 이런식으로 주어지면(e.g ["a","a","b","b","c","c","c"]), 각 알파벳과 그 빈도수 순으로 다시 char[]을 만든다.
	
	예를들어, 위의 리스트에 적용하면, ["a","2","b","2","c","3"]가 된다. 적용한 리스트의 길이를 구하라.(위 경우, 6 반환)
	
	단, 만약 b가 12번 등장했다고 가정하면, ["b", "12"]가 아닌, ["b","1","2"]가 된다.
	
	
	
	
	
	Given an array of characters, compress it in-place.
	
	The length after compression must always be smaller than or equal to the original array.
	
	Every element of the array should be a character (not int) of length 1.
	
	After you are done modifying the input array in-place, return the new length of the array.
	
	 
	Follow up:
	Could you solve it using only O(1) extra space?
	
	 
	Example 1:
	
	Input:
	["a","a","b","b","c","c","c"]
	
	Output:
	Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
	
	Explanation:
	"aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
	 
	
	Example 2:
	
	Input:
	["a"]
	
	Output:
	Return 1, and the first 1 characters of the input array should be: ["a"]
	
	Explanation:
	Nothing is replaced.
	 
	
	Example 3:
	
	Input:
	["a","b","b","b","b","b","b","b","b","b","b","b","b"]
	
	Output:
	Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
	
	Explanation:
	Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
	Notice each digit has it's own entry in the array.
	
	
	<문제풀이 - Trial01(특수문자 고려X)>
	
	step01) int[]에  빈도수를 셀 알파벳의 갯수만큼(소문자 26+대문자26 = 52) 선언
	step02) 주어진 char[]에 for문을 돌면서, 해당 [char - 'a']++; 을 이용하여 각 알파벳의 갯수 파악
	step03) 빈도수가 1~9이면 알파벳+1 해서 2, 빈도수가 10~99이면 알파벳+2해서 3 등으로 반환
	
	
	<문제점>
	1. 알파벳이 소문자 26개, 대문자 26개해서 int[]의 크기를 52로 선언했으나, input중 특수문자도 포함되어 있음을 확인 -> 특수문자까지 고려해서 int[]를 만들면, 메모리를 너무 많이 잡아먹음
	2. 알파벳 대문자나 특수문자는 alphabetCount[chars[i]-'a']++; 에서 -'a'가 안됌
	3. 알파벳의 빈도수가 1의자리면 2개, 10의자리면 3개, 100의자리면 4개 등.. 이 되어야 하는데, 더하는 수를 자동적으로 설정하지 않고, 수동으로 기입

 * 
 * 
 */

package String;

public class StringCompression443_ver1 {
	public static int compress(char[] chars)
	{
		int[] alphabetCount = new int[52]; //26
		int rst = 0;
		
		System.out.println(1);
		
		for(int i = 0; i < chars.length; i++)
		{
			System.out.println(i);
			alphabetCount[chars[i]-'a']++;
		}
		System.out.println(2);
		
		for(int freq: alphabetCount)
		{
			if(freq >= 10) { rst += 3; } // 예외처리 -- 100개 이상이면 +4, 1000개 이상이면 +5를 해주어야 함
			else if(freq > 1){ rst += 2;}
			else if(freq == 1) { rst += 1; }
			else { continue; }
		}
		return rst;
	}
	
	public static void main(String[] args)
	{
		//char[] chars = {'a','a','b','b','c','c','c'};
		//char[] chars = {'a'};
		char[] chars = {'b','0','l','A',']','+','O','5','j','4'};
		System.out.println(compress(chars));
	}

}
