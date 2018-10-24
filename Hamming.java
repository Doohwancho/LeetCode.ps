/*The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.

Note:
0 ≤ x, y < 231.

Example:

Input: x = 1, y = 4

Output: 2

Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑
 * 
 * The above arrows point to positions where the corresponding bits are different.
 */


package LeetCode01;

public class Hamming {

	public static int hammingDistance(int x, int y) {
       
        char[] charsX = Integer.toBinaryString(x).toCharArray();
        char[] charsY = Integer.toBinaryString(y).toCharArray();
        int num = 0;
        int c = 0;
        for (char c ; charsX.length ; c++) { //?왜 array.length가 안돼는거지?
            if (charsX[c] != charsY[c]) {
                num++;
            }
        }
        return num;
    }
	public static void main(String[] args) {
		System.out.println(hammingDistance(4,5));
	}
}
