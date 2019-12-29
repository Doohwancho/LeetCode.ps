/*
	Given an integer n, return any array containing n unique integers such that they add up to 0.
	
	 
	
	Example 1:
	
	Input: n = 5
	Output: [-7,-1,1,3,4]
	Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].
	Example 2:
	
	Input: n = 3
	Output: [-1,0,1]
	Example 3:
	
	Input: n = 1
	Output: [0]
	 
	
	Constraints:
	
	1 <= n <= 1000
	
	
	
	
	
	<문제>
	
	랜덤숫자 n이 주어진다.
	
	그러면 n사이즈의 리스트 int[n]를 리턴하는데, 안의 원소는 아무 숫자라도 상관없으니, 총합이 0만 되면 된다.
	
	예를들어, n = 5 일 때,
	[-7,-1,1,3,4], [-5,-1,1,2,3] , [-3,-1,2,-2,4]
	위의 답 모두 수용. 총 합이 0이기 때문.
 */

package Array;

public class FindNUniqueIntegersSumUpToZero1304 {
	
	//<문제풀이1>
	
	//int[n]을 만들고, 중간값을 0으로 채운 다음, 앞뒤 한칸씩 땡기면서 -1, +1을 해준다.
	
	//근데 n이 짝수면, 맨 앞이 안채워진다.
	
	//그 경우 if문으로 다시한번 inc값을 더하고 빼준다.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Find N Unique Integers Sum up to Zero.
	//Memory Usage: 34.6 MB, less than 100.00% of Java online submissions for Find N Unique Integers Sum up to Zero.
    public int[] sumZero(int n) {
        int[] rst = new int[n];
        int m = n/2;
        int num = 0;
        rst[m] = num;
        int inc = 1;
        while(m-inc>=0 && m+inc < n){
            rst[m-inc] = num-inc;
            rst[m+inc] = num+inc;
            inc++;
        }
        if(n != 1 && rst[0] == 0){ 
            rst[0] -= inc;
            rst[n-1] += inc;
        }
        return rst;
    }
}
