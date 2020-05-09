package mayChallenge;

public class ValidPerfectSquare {
	
	//<문제풀이1>
	
	//binary search
	
	//1. n의 제곱이 m이라고 하자. 그렇다면, n <= m/2이다. 어떤수를 반으로 나눈것보다 같거나 작아야 한다. n = 2, m = 4일때만 유일하게 n == m/2인 경우고,
	
	//m이 4보다 더 커지면, 무조건 n < m/2가 되기 때문.
	
	//그럼 1부터 m/2까지 숫자중에, 제곱해서 m이되는 수를 구하는 방법으로 binary search를 이용한다.
	
	//2. m*m할때, int의 max범위 벗어나면 에러나서 long으로 선언했는데,
	
	//int로 쓰고싶다면, m = (l+r)/2공식 대신에, m = l-(r-l)/2 공식 쓰면 됨.
	
	//왜냐하면, m = l-(r-l)/2 = l - 2l/2 + (l+r)/2 = l - (r-l)/2 
	
	//이기 때문.
	
	//68 / 68 test cases passed.
	//Status: Accepted
	//Runtime: 0 ms
	//Memory Usage: 35.7 MB

    public boolean isPerfectSquare(int num) {
        long l = 0;
        long r = num/2;
        while(l <= r){
            long m = (l+r)/2;
            if(m*m == num) return true;
            else if(m*m > num) r = m-1;
            else l = m+1;
        }
        return l*l == num;
    }
    
    //<문제풀이2>
    
    //Newton-Raphson method
    
    //뭔가 외워서 푸는 느낌이라 별로다.
    
    public boolean isPerfectSquare(int num) {
	    long r = num;
	    while (r*r > num)
	        r = (r + num/r) / 2;
	    return r*r == num;
    }
}
