package juneChallenge;

public class CoinChange2 {
	
	
	/*
	//<Trial01 - time limit exceeded>
	
	//까비 아깝소잉
	
	//recursive로 간지나게 풀었는데
	 
	//모든 경우의 수를 다 도는 brute-force라
	
	//성능은 간지가 안났음
	
    public int helper(int amount, int[] coins, int i) { 
        if(amount < 0 || i < 0) return 0; 
        else if(amount == 0) return 1;
        return helper(amount-coins[i], coins, i) + helper(amount, coins, i-1);  //(when i is part of "amount" or amount % i == 0 or amount == i), (when "amount" don't need i  or amount < i)
    }
    
    public int change(int amount, int[] coins) {
        if(coins.length == 0){
            if(amount == 0) return 1; //
            return 0;
        }
        return helper(amount, coins, coins.length-1);
    }
    */
    
    
    //<문제풀이1 by anubhavjindal>
    
    //int amount = 9;
	//int[] coins = {2,7};
	
	//1 0 1 0 0 0 0 0 0 0 
	//
	//1 0 1 0 0 0 0 0 0 0 
	//
	//1 0 1 0 1 0 0 0 0 0 
	//
	//1 0 1 0 1 0 0 0 0 0 
	//
	//1 0 1 0 1 0 1 0 0 0 
	//
	//1 0 1 0 1 0 1 0 0 0 
	//
	//1 0 1 0 1 0 1 0 1 0 
	//
	//1 0 1 0 1 0 1 0 1 0 
	//
	//1 0 1 0 1 0 1 1 1 0 
	//
	//1 0 1 0 1 0 1 1 1 0 
	//
	//1 0 1 0 1 0 1 1 1 1 
	//
	//1    
	
//	Runtime: 2 ms
//	Memory Usage: 36.7 MB
	
    public static int change(int amount, int[] coins) {
    	int dp[] = new int[amount+1];
    	dp[0] = 1;
    	for(int c : coins) {
    		for(int i = c; i <= amount; i++) { //끝까지 +1씩 더해주니까 이전까지 나온 다른동전들과 조합의 가짓수가 누적으로 더해짐 
    			dp[i] += dp[i-c];
    		}
    	}
    	return dp[amount];
    }
    
    public static void main(String[] args) {
//		int amount = 5;
//		int[] coins = {1,2,5};
		
		int amount = 9;
		int[] coins = {2,7};
		System.out.println(change(amount, coins));
	}
}
