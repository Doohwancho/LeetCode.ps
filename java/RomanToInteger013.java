/*
 * 
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9. 
X can be placed before L (50) and C (100) to make 40 and 90. 
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

Example 1:

Input: "III"
Output: 3
Example 2:

Input: "IV"
Output: 4
Example 3:

Input: "IX"
Output: 9
Example 4:

Input: "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.
Example 5:

Input: "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

 */

/*
 * 문제
 * 로마자가 주어지면 숫자로 변환해주는 코드를 짜라.
 * 각 로마문자가 의미하는 숫자는 아래와 같다.
	I             1
	V             5
	X             10
	L             50
	C             100
	D             500
	M             1000
 * 로마자를 변환할 때, 특수한 규칙이 있다.
 * 예시)
 * I가 V나 X뒤에 오면 4, 9를 의미한다.
 * X가 L나 C뒤에 오면 40, 90을 의미한다.
 * C가 D나 M뒤에 오면 400, 900을 의미한다.
 * 
 * 
 * 
 * 
 * 문제풀이
 * 
 * LVIII과 같이, 앞 로마자가 뒤 로마자보다 크다면 문제될것이 없이 해당 로마자를 더하면 된다.
 * 50+10+1+1+1 = 63
 * 
 * MCMXCIV과 같이 앞 로마자가 뒤 로마자보다 작으면 이하의 규칙을 적용한다.
 * 뒤 로마자에서 앞 로마자를 뺀 후 result에 더한 후, 뒤 로마자는 이미 계산이 끝났으므로 뒤 로마자를 한칸 건너 뛴다.
 * 예를들어, IV는 앞 로마자(I == 1)이 뒷 로마자(V == 5)보다 작으므로, 
 * 뒷 로마자에서 앞 로마자를 뺀 후<4(5-1)> result에 더해준다.
 * 그리고 V는 이미 계산에 쓰였으므로, V의 다음 인덱스로 넘어간다.
 * 
 * 
 * 해당 로마자를 넣으면 값을 꺼내오기 위해 hash-table이라는 기능을 사용하였다. 
 * hash-table은 key:value형태로 데이터를 저장해서, key를 입력하면 value를 꺼내올 수 있는 기능이다.
 * Map<Character, Integer> m = new HashMap<>();
 * 위처럼 객체를 만드는데, key는 Character형태로, value는 Integer형태로 선언한다.
 * 그리고 값을 넣을 때는 m.put('I', 1); 처럼 넣고, 값을 찾을때는 m.get("X");처럼 찾는다.
 * 
 */


package String;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger013 { //by jackthesuper
	public static int romanToInt(String s) 
	{
		Map<Character, Integer> m = new HashMap<>();
		m.put('I', 1);
		m.put('V', 5);
		m.put('X', 10);
		m.put('L', 50);
		m.put('C', 100);
		m.put('D', 500);
		m.put('M', 1000);
		
		int rst = 0;
		for(int i=0; i<s.length(); i++)
		{
			char c = s.charAt(i);
			if(i != s.length()-1 && m.get(c) < m.get(s.charAt(i+1)))  
			{
				rst += m.get(s.charAt(i+1))-m.get(c);
				i++;
			}
			else
			{
				rst += m.get(c);
			}
		}
		return rst;
	}
	
	public static void main(String[] args) {
		String s = "MCMXCIV";
		System.out.println(romanToInt(s));
		
	}
}
