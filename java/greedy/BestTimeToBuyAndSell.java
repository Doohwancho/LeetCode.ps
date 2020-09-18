package september;

public class BestTimeToBuyAndSell {
	
	//<문제풀이1>
	
	//greedy
	
	//min: i번째까지 최솟값
	
	//max: i번째의 값 - 여태껏 나온 최솟값 
	
	//Runtime: 1 ms
	//Memory Usage: 39.3 MB
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for(int price : prices){
            min = Math.min(min, price);
            max = Math.max(max, price-min);
        }
        return max;
    }
}
