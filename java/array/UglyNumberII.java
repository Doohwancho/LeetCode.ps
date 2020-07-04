package julyChallenge;

public class UglyNumberII {
	
	
	//<문제풀이1 by syftalent>
	
	//똑똑허네
	
	//하나하나 다 보는 brute-force말고
	
	//2,3,... 부터 차근차근 ugly number만 기록하는 방법.
	
	//차근 차근 곱해주면서 가장 작은 애만 쏙뽑아서 i에 넣어버림
	
	//만약 6같이 2랑 3 겹치는게 나왔다고 해도, else-if문이 아니라, 둘다 index+1해버리기 때문에 문제가 안생김	
	
	//섹시 그 자체구마이
	
	//O(n)
	
	//Runtime: 3 ms
	//Memory Usage: 37.7 MB
	
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;
        
        for(int i = 1; i < n; i++) {
        	int min = Math.min(Math.min(factor2,factor3),factor5); //셋중에 젤 작은거
        	ugly[i] = min;
        	
            if(factor2 == min)
                factor2 = 2*ugly[++index2];
            if(factor3 == min)
                factor3 = 3*ugly[++index3];
            if(factor5 == min)
                factor5 = 5*ugly[++index5];
        }
        return ugly[n-1];
    }
}
