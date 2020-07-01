package julyChallenge;

public class ArrangingCoins {
	
	
	//<Trial01>
	
	//어씨 숫자가 쥰내 커지면 1부터 그 숫자까지 다 더하는데 지붕뚫고 하이킥 하길래
	
	//double로 바꾸고 helper()도 형변환 안하게 하고 살살켔는데
	
	//이젠 큰숫자에선 잘 도는에 작은숫자에서 1씩 안맞네?
	
	//double때문에 그런가?
	
    private double helper(double n){
        return n%2 == 0 ? (n-1) * n/2 + n : n * (n+1)/2;
    }
    
    public int arrangeCoins(int n) {
        if(n < 2) return n;
        
        double l = 0, r = n/2+1;
        
        while(l < r){
            double m = (l+r)/2;
            double t = helper(m);
            if(t >= n){
                r = m;
            } else {
                l = m+1;
            }
        }
        
        return (int)l;
    }
	
    
    //<문제풀이1 by larrywang2014>
    
    //내 풀이랑 다른게 있다면 overflow를 막기위해 long을 썼다는 점과 
    
    //구질구질하게 helper()같은거 안쓰고 깔끔하게 m * (m + 1) <= 2 * nLong 했다는것
    
    //그외 1차이나는거 메꿔주는 미세튜닝.
    
    //똑똑허네 인정한다
    
    //Runtime: 1 ms
    //Memory Usage: 37.2 MB
    
    public int arrangeCoins(int n) {   
        long nLong = (long)n, l= 0, r= nLong;

        while (l <= r){
            long m = l + (r - l) / 2;
            
            if (m * (m + 1) <= 2 * nLong){
                l = m + 1;
            }else{
                r = m - 1;
            }
        }
        
        return (int)(l - 1);
    }
}
