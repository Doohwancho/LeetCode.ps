package julyChallenge;

public class BestTimeToBuyAndSellStockWithCooldown {
	
	//<Trial01>
	
	//어씨 거의 다 왔는데
	
    int rst = 0;
    
    private void dfs(int[] prices, int i, int acc, boolean sold, boolean buy){
        if(i == prices.length){
            rst = Math.max(rst, acc);
            return;
        }
        if(sold){
            dfs(prices, i+1, acc, false, false); //cooldown
        } else {
            dfs(prices, i+1, acc + (buy ? prices[i]-prices[i-1] : 0), true, true); //sell
            dfs(prices, i+1, acc + (buy ? prices[i]-prices[i-1] : 0), false, true); //buy
            dfs(prices, i+1, acc, false, true); //pass
        }
    };
    
    public int maxProfit(int[] prices) {
        if(prices.length == 0) return 0;
        dfs(prices, 1, 0, false, true);
        return rst;
    }
    
    //<Trial02 by naveen_kothamasu >
    
    //거의 다온게 아니었네?
    
    //경우의 수가 쥰내 많았네?
    
    //그리고 이방식으로 해도 time limit exceeded 뜨네?
    
    //0 - rest, 1 - buy, 2 - sell
    int max = 0;
    public int maxProfit(int[] p) {
        helper(p, 0, 0, false, -1);
        return max;
    }
    private void helper(int[] p, int i, int profit, boolean isSellOK, int prev){
        if(i == p.length) {
            max = Math.max(max, profit);
            return;
        }
        //first only
        if(prev == -1){
            //rest
            helper(p, i+1, profit, isSellOK, 0);
            //buy
            helper(p, i+1, profit-p[i], true, 1);
        //if sell
        }if(prev == 2){
            //rest
            helper(p, i+1, profit, isSellOK, 0);
        //if buy
        }else if(prev == 1){
            //sell
            helper(p, i+1, profit+p[i], false, 2);
            //rest
            helper(p, i+1, profit, isSellOK, 0);
        //if do-nothing
        }else{
        	//rest
            helper(p, i+1, profit, isSellOK, 0);
            //buy
            helper(p, i+1, profit-p[i], true, 1);
            if(isSellOK)
                //sell
                helper(p, i+1, profit+p[i], false, 2);
        }
    }
    
    
    //<문제풀이1 by yavinci>
    
    //이건 큰 틀은 알겠는데 디테일을 모르겠네
    
    //buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);   
    //sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
    
    //b0 = Math.max(b1, s2 - prices[i]);
    //s0 = Math.max(s1, b1 + prices[i]);
    
    //Runtime: 1 ms
    //Memory Usage: 39.3 MB
    
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1) return 0;
      
        int b0 = -prices[0], b1 = b0;
        int s0 = 0, s1 = 0, s2 = 0;
     
        for(int i = 1; i < prices.length; i++) {
        	b0 = Math.max(b1, s2 - prices[i]); //i번째 사는 선택. 한턴 쉬는 선택(b1) vs i-2에서 팔고 i-1에서 cooldown하고 i에서 사는 선택(s2-prices[i]) 
        	s0 = Math.max(s1, b1 + prices[i]); //i번째 파는 선택. 한턴 쉬는선택(s1) vs i-1과 i에서의 차익
        	b1 = b0; s2 = s1; s1 = s0; 
        }
        return s0;
    }
}
