package juneChallenge;

public class PowerOfTwo {
	
	//<문제풀이1>
	
	//n이 2의 제곱수인지 파악하라는 건데
	
	//일단 n이 마이너스면, 2의 제곱 자체가 될 수가 없겠지?
	
	//if(n < 0) return false; 그래서 이걸로 예외처리 해주고
	
	//2의 제곱수(1,2,4,8,16,32,64,...)의 특징이
	
	//2진수로 변환하면 bit가 딱 한개 켜진다라는걸 이용해서
	
	//bit의 수가 1개이면 true를 반환하면 되지.
	
	//오늘것도 무난무난하구만
	
	//Runtime: 2 ms
	//Memory Usage: 37 MB
	
    public boolean isPowerOfTwo(int n) {
        if(n < 0) return false;
        return Integer.bitCount(n) == 1;
    }
}
