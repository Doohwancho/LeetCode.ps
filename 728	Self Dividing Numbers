/*A self-dividing number is a number that is divisible by every digit it contains.

For example, 128 is a self-dividing number because 128 % 1 == 0, 128 % 2 == 0, and 128 % 8 == 0.

Also, a self-dividing number is not allowed to contain the digit zero.

Given a lower and upper number bound, output a list of every possible self dividing number, including the bounds if possible.

Example 1:
Input: 
left = 1, right = 22
Output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
Note:

The boundaries of each input argument are 1 <= left <= right <= 10000.
 */

package LeetCode07;

import java.util.ArrayList;
import java.util.List;

public class SelfDivide {
	public static List<Integer> selfDividingNumbers(int left, int right) {
		String num = "";
		ArrayList<Integer> rst = new ArrayList<Integer>();
		int cnt = 0;
		for (int i = left; i <= right; i++) {
			num = i + "";
			for (int j = 0; j < num.length(); j++) {
				if ((int) Integer.parseInt("" + num.toCharArray()[j]) != 0) {
					if (i % (int) Integer.parseInt("" + num.toCharArray()[j]) == 0)
						cnt++;
				}
			}
			if (num.length() == cnt) {
				rst.add(Integer.parseInt(num));
			}
			cnt=0;
		}
		return rst;
	}

	public static void main(String[] args) {
		System.out.println(selfDividingNumbers(1, 22));

	}
}
