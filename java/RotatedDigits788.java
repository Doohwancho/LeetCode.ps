/*
X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from X.  Each digit must be rotated - we cannot choose to leave it alone.

A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves; 2 and 5 rotate to each other; 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number and become invalid.

Now given a positive number N, how many numbers X from 1 to N are good?

Example:
Input: 10
Output: 4
Explanation: 
There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
 */

/*
 * 문제
 * 숫자 x가 주어지면, x를 180도 돌렸을 때, 본인이 아닌 다른 숫자가 나오면 valid, 아니면 invalid 하다.
 * 예를들어, 0,1,8은 180도 회전시 본인숫자가 나오므로 invalid하다.
 * 2는 180도 회전시 5가 되고, 5도 180도 회전시 2가 되므로 valid하다.
 * 6은 180도 회전시 9가 되고, 9도 180도 회전시 6이 되므로 valid하다.
 * 아머지 숫자들(3,4,7)은 180도 회전시 아무 숫자가 되지 않으므로 invalid하다.
 * 
 * 숫자 x가 주어지면, 1~x의 숫자중 valid한 숫자의 갯수를 반환하라.
 * 
 * 
 * ???
 * 10 is not a good number, since it remains unchanged after rotating.
 * 10을 뒤집으면 1이 되는데 
 * 
 * 문제풀이
 * 
 * valid 조건1: 숫자에 2,5,6,9가 무조건 들어가야 한다.
 * valid 조건2: 나머지 숫자들 중 3,4,7은 있으면 안된다.
 * 
 * 2중 for문으로, 첫번째 for문은 1부터 N까지 돌면서, 두번째 for문은 해당번째의 숫자를 toCharArray()로 쪼개서 각 자리숫자마다 for문을 돈다(e.g 104이면, 1->0->4 순으로 for문을 돈다)
 * valid 조건1을 만족시켜 주기위한 identifier 변수를 false로 선언한 후, 해당 숫자가 2,5,6,9를 포함하고 있으면 true로 만들어 준다.
 * 2번째 for문을 돌면서, 3,4,7이 포함되어 있으면 break를 통해 해당 숫자를 취소하고, 만약 3,4,7이 포함되지 않으면서 마지막 인덱스에 도달했는데 identifier도 true면 ans+1을 해준다.
 * 
 */

package String;

public class RotatedDigits788 {
	public static int rotatedDigits(int N)
	{
		int ans = 0;
		
		for(int i = 1; i < N+1 ; i++)
		{
			char[] word = Integer.toString(i).toCharArray();
			boolean identifier = false;

			for(int j = 0; j < word.length; j++)
			{
				if(word[j] == '2' | word[j] == '5' | word[j] == '6' | word[j] == '9')
				{
					identifier = true;
				}
				else if(word[j] == '3' | word[j] == '4' | word[j] == '7')
				{
					break;
				}
				
				if(j == word.length-1 && identifier)
				{
					ans++;
				}
			}
		}
		return ans;
	}
	
	public static void main(String[] args) {
		int n = 10;
		System.out.println(rotatedDigits(n));
	}
}



