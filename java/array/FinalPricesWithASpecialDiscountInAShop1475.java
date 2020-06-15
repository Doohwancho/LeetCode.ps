package array;

public class FinalPricesWithASpecialDiscountInAShop1475 {

	
	//<문제풀이1>
	
	//걍 2중for문 써서 i>=j가 나오자마자 i-j해주고 다음껄로 넘어가면 되제
	
	//Runtime: 1 ms, faster than 99.84% of Java online submissions for Final Prices With a Special Discount in a Shop.
	//Memory Usage: 39.9 MB, less than 100.00% of Java online submissions for Final Prices With a Special Discount in a Shop.
	
    public int[] finalPrices(int[] prices) {
        for(int i = 0; i < prices.length; i++){
            for(int j = i+1; j < prices.length; j++){
                if(prices[i] >= prices[j]){
                    prices[i] -= prices[j];
                    break;
                }
            }
        }
        return prices;
    }
    
}
