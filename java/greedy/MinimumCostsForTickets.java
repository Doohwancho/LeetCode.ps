package augustChallenge;

public class MinimumCostsForTickets {
	
	
	
	//<Trial01>
	
	//일일로 쪼개서 greedy
	
	//대략적인 방향은 맞았는데 약간 삐꾸남
	
	
    public int mincostTickets(int[] days, int[] costs) {
        int[] d = new int[days[days.length-1]+1];
        for(int i = 1, j = 0; i < d.length; i++){
            if(i == days[j]){
                d[i] = Math.min(costs[0] + d[i-1], Math.min(i > 6 ? d[i-7]+costs[1] : Integer.MAX_VALUE, i > 29 ? d[i-30]+costs[2] : Integer.MAX_VALUE));
                j++;
            } else if(i > 0) {
                d[i] += d[i-1];
            }
        }
        return d[d.length-1];
    }
    
    
    
    //<문제풀이1 by sriharik>
    
    //오늘은 끈기가 부족했네
    
    //i > 6 ? d[i-7]+costs[1] : Integer.MAX_VALUE 말고
    
    //이 사람은 쥰내 똑똑하게 dp[Math.max(0, i-7)] + costs[1]함
    
    //이게 왜 쥰내 똑똑하냐면,
    
    //[100,100,1]같은 경우에, 내가 쓴 방식은 99일까진 1을 안삼.
    
    //근데 이 사람이 쓴 방식을 쓰면 1을 삼.
    
    //Runtime: 1 ms
    //Memory Usage: 39.6 MB
    
    public int mincostTickets(int[] days, int[] costs) {
        int[] dp = new int[days[days.length-1]+1];
        
        for(int i = 1, j = 0; i < dp.length; i++){
            if(i == days[j]){
                int one = dp[i-1] + costs[0];
                int seven = dp[Math.max(0, i-7)] + costs[1];
                int thirty = dp[Math.max(0, i-30)] + costs[2];
                dp[i] = Math.min(one, Math.min(seven, thirty));
                j++;
            } else {
                dp[i] += dp[i-1];
            }
        }
        
        return dp[dp.length-1];
    }
    
    
    
}
