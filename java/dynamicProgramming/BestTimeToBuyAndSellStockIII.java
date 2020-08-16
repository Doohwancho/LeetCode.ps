package augustChallenge;

public class BestTimeToBuyAndSellStockIII {

	//<Trial01>
	
	//어우 헤깔려
	
	//아니 다시 이전 transaction이 +면 무조건 묻고 더블로 가고, 이전 transaction이 -인데 여태껏 누적으로 벌던 돈이랑 합쳤을때도 -면 무조건 손절해야 해서
	
	//이까진 쉽게 가는데,
	
	//만약 i번째 transaction이 -인데, 여태껏 누적으로 벌던거랑 합했을땐 +면,
	
	//ex)[1,5,2, ...]
	
	//2에서 손절하는 경우랑 큰그림 그리면서 2를 안고가는 경우 2개를 else if에 넣었는데
	
	//어디선가 꼬임.
	
	//다시 생각해보니까 최대 2번만 샀다 팔았다 할 수 있는데 그건 포함을 안시켰네 제기랄?
	
    private int dfs(int[] prices, int idx, int first, int second, int curr, int prev){
        
        for(int i = idx, tmp = 0; i < prices.length; i++){
            tmp = prices[i];
            prices[i] -= prev;
            
            if(prices[i] >= 0){
                curr += prices[i];
            } else if(prices[i] + curr > 0){
                if(curr >= second){
                    if(curr >= first){
                        second = first;
                        first = curr;
                    } else if(curr > second){
                        second = curr;
                    }
                    curr = 0;
                }
                return Math.max(dfs(prices, i+1, first, second, prices[i]+curr, prev), dfs(prices, i+1, first, second, curr, prev));
            } else {
                if(curr >= first){
                    second = first;
                    first = curr;
                } else if(curr > second){
                    second = curr;
                }
                curr = 0;
            }
            prev = tmp;
            
        }
        if(curr != 0){
            if(curr >= first){
                second = first;
                first = curr;
            } else if(curr > second){
                second = curr;
            }
            curr = 0;
        }
        
        return first+second;
    }
    
    public int maxProfit(int[] prices) {
        return dfs(prices, 1, 0, 0, 0, prices[0]);
    }
    
    
    //<문제풀이1 by jeantimex>
    
    //와씨 큰그림 설계 오지네
    
    //왼쪽부터 오른쪽까지 최대 profit누적
    
    //오른쪽부터 왼쪽까지 최대 profit누적
    
    //for문으로 각 인덱스 돌면서 해당 시점에 left최대 profit + right 최대 profit중 최댓값 구함
    
    //left에서 transaction 한번, right에서 transaction한번 했다고 생각.
    
    //Runtime: 3 ms
    //Memory Usage: 39.6 MB
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        
        int n = prices.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int min = prices[0];
        int max = prices[n-1];
        int rst = 0;
        
        for(int i = 1; i < n; i++){
            left[i] = Math.max(left[i-1], prices[i]-min);
            min = Math.min(min, prices[i]);
        }
        min = prices[n-1];
        for(int j = n-2; j >= 0; j--){
            right[j] = Math.max(right[j+1], max-prices[j]);
            max = Math.max(max, prices[j]);
            
            rst = Math.max(rst, left[j]+right[j]);
        }
        return rst;
    }
    
    //<문제풀이2 by yogi_bear>
    
    //dfs
    
    //optimize하려면 dp써도 되는데, 안써도 무방
    
    //Runtime: 4 ms
    //Memory Usage: 44.1 MB
    
//    Integer[][] dp;
    int[] prices;
    int k = 2;
    public int maxProfit(int[] prices) {
//        dp = new Integer[prices.length][k];
        this.prices = prices;
        return compute(0, k-1);        
    }
    private int compute(int st, int k) {
        if (k < 0) return 0;
        if (st >= prices.length) return 0;
//        if (dp[st][k] != null) return dp[st][k];
        
        int i = st + 1;
        int maxProfit = 0;
        int profit = 0;
        for (; i < prices.length; i++) {
            profit += prices[i] - prices[i-1];
            if (profit <= 0) break;
            maxProfit = Math.max(maxProfit, profit + compute(i + 1, k - 1)); //i번째는 -인데, 누적 profit이랑 더했을땐 아직 +인 경우, Math.max(i번째에 파는 선택, i번째에 손절하는 선택)
        }
        maxProfit = Math.max(maxProfit, compute(i, k));//완전 profit이 아작난 경우, 여때껏 maxProfit이랑 여태껏은 버리고 앞으로 남은 array의 최대 profit비교해서 maxProfit갱신
//        dp[st][k] = maxProfit;
        return maxProfit;
    }
}
