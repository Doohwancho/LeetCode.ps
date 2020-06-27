package juneChallenge;

public class PerfectSquares {
	
	//<Trial01>
	
	//greedy가 안먹히는 문제네?
	
	public int numSquares(int n) {
        int rst = 0;
        while(n > 0){
            int sqrt = (int)Math.sqrt(n);
            n -= sqrt * sqrt;
            rst++;
        }
        return rst;
    }
	
	
	//<문제풀이1>
	
	//씨발 난 천재야
	
	//아 근데 성능 개구리네
	
	//Runtime: 1070 ms
	//Memory Usage: 38.6 MB
	
    //  o     o         o                   o
    //0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18
    //1,1,2,3,1,2,3,4,2,1,2 ,3 ,3 ,2 ,3 ,4 ,1 ,2  ,2
    public int numSquares(int n) {
        int[] a = new int[n+1];
        
        for(int i = 1; i <=n; i++){
            if(Math.sqrt(i) == (int)Math.sqrt(i)){ //제곱수는 바로 1박아줌
                a[i] = 1;
            } else {
                int smallest = Integer.MAX_VALUE;
                for(int j = 1; j < i; j++){
                    smallest = Math.min(smallest, a[i-j] +a[j]); // 6이 걸렸으면 (1,5),(2,4),(3,3) 조합중 가장 작은거 smallest에 넣어서 a[i]에 갖다 박음
                }
                a[i] = smallest;
            }
        }
        return a[n];
    }
    
    
    //<문제풀이2 by czonzhu> 
    
    //dp
    
    //Runtime: 25 ms
    //Memory Usage: 38.6 MB
    
    //  o     o         o                   o
    //0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18
    //1,1,2,3,1,2,3,4,2,1,2 ,3 ,3 ,2 ,3 ,4 ,1 ,2  ,2
    
    public int numSquares(int n) { 
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0; i <= n; i++){
            for(int j = 1; i + j * j <= n; j++){
                dp[i  + j * j] = Math.min(dp[i + j * j], dp[i] + 1); 
                //맨처음 i가 0일때 제곱수들 dp[i](==0)+1로 다 1박아줘.
                
                //dp[i] + 1같은 경우에는 dp[i]가 i의 제곱수들의 최소니까, i에 j*j제곱수를 더한건, +1만 해주면 되지. i에 제곱수 하나 띡 더하는거잖어
                //예를들어 13의 경우엔, i가 4일때, 4는 2의 제곱수니까 dp[i]는 1인상태에서, i(4)+j의 제곱(3*3 = 9) = 해서 13잖어.
                
                //그리고 12의 경우에, dp[i]+1부분 써서
                //i가 3일때 3(3)+9(1)해서 4로 채워졌다가
                //i가 8일때 8(1)+4(1)해서 다시 2로 Math.min()해서 업데이트 되는거지
             }
        }
        return dp[n];
     }
	
}
