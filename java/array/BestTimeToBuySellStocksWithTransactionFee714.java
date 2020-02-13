package array;

public class BestTimeToBuySellStocksWithTransactionFee714 {
	
	/*
	//<Trial01>
	
	//아 머리야
	
	//Your input : [1,3,2,8,4,9], 2
	
	//stdout
	//0 1 0 0
	//1 3 2 2
	//2 2 -1 2
	//3 8 6 7
	//4 4 -4 7
	//5 9 5 8
	
	//Output: 0
	
	public int maxProfit(int[] prices, int fee) {
        int[] container = new int[prices.length];
        int[] containerAcc = new int[prices.length];
        int rst = 0;
        int accumulate = 0;
        
        for(int i = 1, buy = 0, neg = 0; i < prices.length; i++){
            int diff = prices[i] - prices[i-1];
            container[i] = diff;
            
            if(container[i] < 0){
                accumulate = containerAcc[i-1];
                neg += container[i];
            } else {
                accumulate = containerAcc[i-1]+container[i]+neg;
                neg = 0;
            }
            
            containerAcc[i] = accumulate;
         
        }
        return rst;
    }
    */
	
	/*
	//121. Best Time to Buy and Sell Stock I
	//<What-if 1>
	//제한사항1 : 한번만 사고 한번만 팔수있다면 최대 차익은?
	//제한사항2 : fee가 없었다면?	
    public int maxProfit(int[] prices) {
        if(prices.length == 0) return 0;
        
        int buyPrice = prices[0];
        int max = 0;
        
        for(int i = 0; i < prices.length; i++)
        {
            if(prices[i] < buyPrice)
            {
                buyPrice = prices[i];
            }
            else
            {
                int profit = prices[i] - buyPrice;
                max = Math.max(max, profit);
            }
        }
        return max;
    }
    */
    /*
	//<What-if2>
    //122. Best Time to Buy and Sell Stock II
	//조건: 여러번 사고 팔수있다면 최대 차익은?
	//fee가 없으니 [1, 3, 2, 8, 4, 9]를 적용하면, +2,+6,+5 해서 +13이 맞네
	
    public int maxProfit(int[] prices) {
        int profit = 0;
        
        for (int i = 1; i < prices.length; i++) 
            profit += Math.max(0, prices[i] - prices[i - 1]);
        
        return profit;
    }
    */
	
	/*
	//<문제풀이1 by alexander>
	
	//쉬운버전
	
	//Runtime: 10 ms, faster than 14.61% of Java online submissions for Best Time to Buy and Sell Stock with Transaction Fee.
	//Memory Usage: 53.7 MB, less than 36.36% of Java online submissions for Best Time to Buy and Sell Stock with Transaction Fee.
	
	public int maxProfit(int[] p, int fee) {
        int profit = 0;
        Integer lo = null, hi = null, n = p.length;
        for (int i = 0; i < n; i++) {
            if (lo != null && hi == null && p[i] - lo > fee) hi = p[i]; // buy in
            if (hi != null && p[i] > hi) hi = p[i]; // update highest
            if (hi != null && (hi - p[i] > fee || i == n - 1)) { // sale out
                profit += hi - lo - fee;
                hi = null;
                lo = null;
            }

            lo = lo != null ? Math.min(lo, p[i]) : p[i]; // update lowest
        }
        return profit;      
    }
    */
	
	//<문제풀이2 by alexander>
	
	//어려운버전. 아 개헤깔리네
	
	//Definition:
	//hold[i] - the maximum profit you can earn if you have to hold at day[i]
	//sold[i] - the maximum profit you can earn if you have to sold at day[i]
	//
	//Formula:
	//hold[i] = max(hold[i - 1], sold[i - 1] - p[i])       // if hold at [i-1], no op; if sold at [i-1], buy at [i] with cost of p[i];
	//sold[i] = max(sold[i - 1], hold[i - 1] + p[i] - fee) // if sold at [i-1], no op; if hold at [i-1], sell at [i] with gain of p[i] - fee;
	//
	//Initialization:
	//hold[0] = 0 - price[0];  // buy shares with cost of p[0];
	//sold[0] = 0;             // no op no cost;
	
	
	//prices = [1,3,2,8,4,9], fee = 2
	
	//i: 0 prices[i]: 1 hold: -1 sold: 0
	//i: 1 prices[i]: 3 hold: -1 sold: 0
	//i: 2 prices[i]: 2 hold: -1 sold: 0
	//i: 3 prices[i]: 8 hold: -1 sold: 5
	//i: 4 prices[i]: 4 hold: 1 sold: 5
	//i: 5 prices[i]: 9 hold: 1 sold: 8
	
	//Runtime: 4 ms, faster than 86.29% of Java online submissions for Best Time to Buy and Sell Stock with Transaction Fee.
	//Memory Usage: 55.8 MB, less than 9.09% of Java online submissions for Best Time to Buy and Sell Stock with Transaction Fee.
	public int maxProfit(int[] p, int fee) {
        int n = p.length;
        if (n < 2) return 0;
        int[] hold = new int[n], sold = new int[n];
        hold[0] = -p[0];
        for (int i = 1; i < n; ++i) {
            hold[i] = Math.max(hold[i - 1], sold[i - 1] - p[i]);
            sold[i] = Math.max(sold[i - 1], hold[i - 1] + p[i] - fee);
        }

        return sold[n - 1];
    }
	
}
