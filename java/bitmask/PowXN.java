package julyChallenge;

public class PowXN {

	//<문제풀이1>
	
	//이걸 내장함수를 안쓰는 이유가 있나?
	
	//bitmask라도 integer이 아니라 double이면 꽤나 복잡할텐데?
	
	//Runtime: 1 ms
	//Memory Usage: 38.6 MB
	
    public double myPow(double x, int n) {
        return Math.pow(x, n);
    }
    
    
    
    //<문제풀이2 by dietpepsi>
    
    //bitmask
    
    //Runtime: 0 ms
    //Memory Usage: 36.8 MB
    
    public double myPow(double x, int n) {
        long m = n > 0 ? n : -(long)n;
        double ans = 1.0;
        while (m != 0) { //지수가 0이될때까지 계속 곱연산. 그리고 n이 -일수도 있으니 n_>0이 아니라 n_ != 0
            if ((m & 1) == 1) //bit가 켜질때만 ans에 x를 곱해줌.
                ans *= x;
            x *= x; //x를 계속 제곱해줌. m >> 1이니까 2의 제곱으로 움직이기 때문. 
            m >>= 1;
        }
        return n >= 0 ? ans : 1 / ans;
    }
}
