/*
	Say you have an array for which the ith element is the price of a given stock on day i.

	Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
	
	Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
	
	Example 1:
	
	Input: [7,1,5,3,6,4]
	Output: 7
	Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
	             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
	Example 2:
	
	Input: [1,2,3,4,5]
	Output: 4
	Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
	             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
	             engaging multiple transactions at the same time. You must sell before buying again.
	Example 3:
	
	Input: [7,6,4,3,1]
	Output: 0
	Explanation: In this case, no transaction is done, i.e. max profit = 0.



	<문제>
	
	리스트 [7,1,5,3,4,6]에서 각 값들은 주식의 가격을 의미하고, 인덱스는 날짜를 의미한다.
	
	하루에 한번만 사거나 파는게 가능하다고 할 때, 극대화된 이익의 량은 얼마인가?
	
	예를들어, [7,1,5,3,4,6]의 경우, 1에사서 5에팔고(+4) 3에사서 6에팔면(+3) 총 이익은 7이다.
	
	

	<문제 풀이 by jeantimex>
	
	전날 가격의 차가 0보다 크다면(양의정수이면) profit에 더하면 된다.
	
	예를들어, [7,1,5,3,4,6]은 +4+1+2이다. 이 리스트의 순서가 약간 변형이 되어서 [7,1,5,3,6,4]가 된다고 하더라도, +4+3으로 결과값에는 변화가 없다.
	
	그냥 +3이 +1+2로 쪼개진 것일 뿐이다.
	
	
	Runtime: 1 ms, faster than 94.84% of Java online submissions for Best Time to Buy and Sell Stock II.
	Memory Usage: 37.4 MB, less than 99.98% of Java online submissions for Best Time to Buy and Sell Stock II.
 */




package Array;

public class BuySellStockII122 {
	
	public static int maxProfit(int[] prices) 
	{
        int profit = 0;
        
        for(int i = 1; i < prices.length; i++)
        {
        	profit += Math.max(0, prices[i]-prices[i-1]);
        }
		
		return profit;
    }
	
	public static void main(String[] args) 
	{
		int[] prices = {7,1,5,3,4,6};
		System.out.println(maxProfit(prices));
	}

}
