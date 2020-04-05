package thirtyDayChallenge;

public class BestTimeToBuyAndSellStockII {
	
	/*
	//<문제풀이1>
	
	//greedy
	
	//201 / 201 test cases passed.
	//Status: Accepted
	//Runtime: 1 ms
	//Memory Usage: 39.3 MB
	
    public int maxProfit(int[] prices) {
        int rst = 0;
        for(int i = 1; i < prices.length; i++){
            if(prices[i] > prices[i-1]){
                rst += prices[i] - prices[i-1];
            }
        }
        return rst;
    }
    */
	
    //<문제풀이2 by shpolsky>
    
    //greedy가 아닌 방식
    
    //먼저 가장 낮은 점을 찾아내고, 그 다음 가장 높은점을 찾아낸 후, 그 차이를 더하는 방식
    
    public int maxProfit(int[] prices) {
        int profit = 0, i = 0;
        while (i < prices.length) {
            // find next local minimum
            while (i < prices.length-1 && prices[i+1] <= prices[i]) i++;
            int min = prices[i++]; // need increment to avoid infinite loop for "[1]"
            // find next local maximum
            while (i < prices.length-1 && prices[i+1] >= prices[i]) i++;
            profit += i < prices.length ? prices[i++] - min : 0;
        }
        return profit;
    }
}
