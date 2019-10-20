/*
	Given a positive integer num, write a function which returns True if num is a perfect square else False.
	
	Note: Do not use any built-in library function such as sqrt.
	
	Example 1:
	
	Input: 16
	Output: true
	Example 2:
	
	Input: 14
	Output: false
	
	
	
	<문제>
	
	Math.sqrt()를 쓰지않고, 주어진 숫자 num이 자연수의 제곱인 수인지 확인하라.
 */

package BinarySearch;

public class ValidPerfectSquare367 {
	
	//<문제풀이1>
	
	//Math.sqrt()를 쓰지 말라고 했으니까, 1부터 num까지의 숫자들 중, 제곱해서 num이 있는지 찾아봐야됨.
	
	//근데 for-loop해서 일일이 다 확인하면 시간이 너무 오래 걸리므로, binary-search를 이용해 해당 조건이 맞는 숫자 몇개만 확인함
	
	//일반적인 binary-serach를 쓰면, 에러뜸. 왜냐면 leetcode에서 input을 짖궂게도 int의 max인 2147483647을 넣어버리기 때문.
	
	//따라서 middle을 구할때 원래 식인 middle = (left+right)/2해서 구하는게 아니라,
	
	//값은 같지만 약간 변형한 left + (right-left) / 2을 해서 구함
	
	//왜냐면, 2*left/2 + (right-left)/2 == (2*left + right - left)/2 == (left+right)/2이기 때문.
	
	//그런데도 또 막힘. 왜냐면, 예를들어 2147483647의 약 3/4정도 되는 숫자를 제곱했다고 치면, int의 최대치인 2147483647를 넘어가기 때문에 에러남.
	
	//따라서 Math.pow(m,2)한것을 int로 받는게 아니라 최대한도가 더 큰 double로 받음.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Valid Perfect Square.
	//Memory Usage: 32.8 MB, less than 6.12% of Java online submissions for Valid Perfect Square.
	
    public static boolean isPerfectSquare(int num) {
    	if(num == 1) return true;
    	int l = 0, r = num, m = 0;
    	while(l < r) {
    		m = l+(r-l)/2;
    		double sqrtM = Math.pow(m, 2);
    		if(sqrtM == num) return true;
    		else if(sqrtM < num) l = m + 1;
    		else r = m;
    	}
    	return  false;
    }
	
	public static void main(String[] args) {
		//int num = 16;
		int num = 2147483647;
		System.out.println(isPerfectSquare(num));
	}
}
