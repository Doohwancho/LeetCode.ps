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
	
	
//  m            sqrtM                     l     r
	
//	1073741823   1.15292150245936333E18    0    1073741823
//	536870911   2.8823037507796992E17    0    536870911
//	268435455   7.2057593501057024E16    0    268435455
//	134217727   1.8014398241046528E16    0    134217727
//	67108863   4.503599493152769E15    0    67108863
//	33554431   1.125899839733761E15    0    33554431
//	16777215   2.81474943156225E14    0    16777215
//	8388607   7.0368727400449E13    0    8388607
//	4194303   1.7592177655809E13    0    4194303
//	2097151   4.398042316801E12    0    2097151
//	1048575   1.099509530625E12    0    1048575
//	524287   2.74876858369E11    0    524287
//	262143   6.8718952449E10    0    262143
//	131071   1.7179607041E10    0    131071
//	65535   4.294836225E9    0    65535
//	32767   1.073676289E9    32768    65535
//	49151   2.415820801E9    32768    49151
//	40959   1.677639681E9    40960    49151
//	45055   2.029953025E9    45056    49151
//	47103   2.218692609E9    45056    47103
//	46079   2.123274241E9    46080    47103
//	46591   2.170721281E9    46080    46591
//	46335   2.146932225E9    46336    46591
//	46463   2.158810369E9    46336    46463
//	46399   2.152867201E9    46336    46399
//	46367   2.149898689E9    46336    46367
//	46351   2.148415201E9    46336    46351
//	46343   2.147673649E9    46336    46343
//	46339   2.147302921E9    46340    46343
//	46341   2.147488281E9    46340    46341
//	46340   2.1473956E9    46341    46341
	
	//2147483647을 돌리면 매 loop마다 다음과 같은 값이 나온다.
	
	//보면 6만대까지 꾸준히 반으로 줄어들다가, 3만찍고 4만대에서 adjust함.
	
	//마지막 3개를 보면, m이 46341,46340과 일때 l값과 r값의 차이가 좁아지더니, 둘이 같아지게 될 때 while문이 끝난다.
	
	//m을 제곱한 sqrtM을 보면 2.1473956E9인데, 2147483647과 비교를 해 보면, 3957..부분이 다른것을 볼 수 있다.
	
	//신기하네
	
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
    		System.out.println(m+"   "+sqrtM+"    "+l+"    "+r);
    	}
    	return false;
    }
	
	public static void main(String[] args) {
		//int num = 16;
		int num = 2147483647;
		System.out.println(isPerfectSquare(num));
	}
}
