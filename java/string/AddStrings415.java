/*
Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */

/*
 * 문제
 * 
 * 두 숫자 num1과 num2가 String 형태로 주어지면, 이 두 숫자를 더한 후, String 형태로 반환하라.
 * 예를들어, num1 = '8';, num2 = '9'; 이면, String 형태의 "17"를 반환한다.
 * 
 * 
 * 
 * 
 * 문제풀이
 * 
 * 형변환을 이용하면 쉽게 풀 수 있다.
 * <형변환>
		 
   1. int to String
	 1-1. String str = Integer.toString(t);
	 1-2. String str = "" + i;
		 
   2. String to int
	 2-1. int i = Integer.parseInt(str);
	 2-2. int i = Integer.valueOf(Str).intValue();
		 
   int intNum1 = Integer.parseInt(num1);
   int intNum2 = Integer.parseInt(num2);
		
   return Integer.toString(intNum1 + intNum2);
   
      하지만 문제에서 형변환을 사용하지 말고 풀라고 했기 때문에, 아래 방법을 쓴다.
      
   num1과 num2의 일의자리 수부터 두 수를 더한다. 
   1의자리를 명시하는 법은 각 숫자의 length(int m, int n)에서 -1을 해 준 값이 해당값의 마지막 인덱스가 되기 때문에 1의자리를 뜻한다.
      두 수를 더했을 때, 10 이상이면(e.g 8+9 = 17) 1은 그 다음 십의자리 수를 계산할때 더해줘야 하기 때문에, carry라는 변수에 넣어서 다음 for문 차례 때, sum에 미리 더해준다.
      그리고 17의 나머지 7(17%10)은 1의자리 숫자니까 stringbuilder에 더해준다.
   stringbuilder에 append 시킬 때, 오른쪽에 붙여진다. 예를들어, 123+4567을 하면 4690을 반환해야 하는데, 
      일의자리부터 한자리 한자리를 sb에 더할때 왼쪽에 붙여주기 때문에, 0964를 반환한다. 따라서 return시 .reverse()를 사용해서 뒤집어 준 후 반환한다.
   
*/


package String;

public class AddStrings415 {
	public static String addStrings(String num1, String num2)
	{
		StringBuilder sb = new StringBuilder();
		int m = num1.length();
		int n = num2.length();
		int carry = 0;
		
		for(int i = 1; i <= Math.max(m, n) || carry > 0; i++)
		{
			int sum = carry;
			if(i <= m) sum += num1.charAt(m - i) - '0';
			if(i <= n) sum += num2.charAt(n - i) - '0';
			sb.append(sum % 10);
			carry = sum / 10;
			
			//123    4567
			if(i <= m) System.out.println(num1.charAt(m - i) - '0');
			if(i <= n) System.out.println(num2.charAt(n - i) - '0');
			System.out.println("sum = "+sum);
			System.out.println("sum % 10 = "+(sum % 10));
			System.out.println("sum / 10 = "+(sum / 10));
			System.out.println("sb = "+sb);
			System.out.println("carry = "+carry);
			System.out.println("======================");
		}
		return sb.reverse().toString();
	}
	
	public static void main(String[] args) 
	{
		String num1 = "123";
		String num2 = "4567";
		
		System.out.println(addStrings(num1, num2));
	}

}
