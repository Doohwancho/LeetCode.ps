package dynamicProgramming;

import java.util.Arrays;

public class StoneGame877 {
	
	/*
	//<Trial01>
	
	//알렉스랑 리랑 게임하는데,
	
	//알렉스만 최적으로 플레이하는건  만들 수 있겠는데,
	
	//알렉스랑 리가 동시에 최적으로 플레이하는건 머리아프네
	
	
	
	//[1,0,100,2] 처럼, 매 선택마다 양쪽 끝단에 가장 큰수를 고르는게 답이 아닐 수도 있음.
	
	//그래서 memoization과 함께 모든 경우의 수를 다 봐야 함

    int[] piles;
    int[] alex;
    int[] lee;
    
    private int dp(int start, int end, int choose, boolean flag, boolean identifier){
        if(start > end) return 0;
        
        if(flag){
            if(!identifier) return 0;
            if(alex[choose] != -1) return alex[choose];
            alex[choose] = 0;
            alex[choose] = Math.max(alex[choose], piles[choose] + Math.max(dp(start+1, end, start+1, !flag, false), dp(start, end-1, end-1, !flag, false)));
            return alex[choose];
        } else {
            if(identifier) return 0;
            if(lee[choose] != -1) return lee[choose];
            lee[choose] = 0;
            lee[choose] = Math.max(lee[choose], piles[choose] + Math.max(dp(start+1, end, start+1, !flag, true), dp(start, end-1, end-1, !flag, true)));
            return lee[choose];
        }
    }
    
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        alex = new int[n];
        lee = new int[n];
        for(int i = 0; i < n; i++){
            alex[i] = -1;
            lee[i] = -1;
        }
        this.piles = piles;
        Math.max(dp(0, n-1, true, true), dp(0, n-1, n-1, true, true));
        
        return true;
    }
    
    
    //<Trial02>
    
    int[] piles;
    int[] alex;
    int[] lee;
    
    private int dp(int start, int end, boolean flag){
        if(start >= end) return 0;
        
        if(flag){
            alex[start] = 0;
            alex[start] = piles[start] + dp(start+1, end, !flag);
            alex[end] = 0;
            alex[end] = piles[end] + dp(start, end-1, !flag);
            return Math.max(alex[start], alex[end]);
        } else {
            lee[start] = 0;
            lee[start] = piles[start] + dp(start+1, end, !flag);
            
            lee[start] = 0;
            lee[end] = 0;
            lee[end] = piles[end] + dp(start, end-1, !flag);
            return Math.max(lee[start], lee[end]);
        }
    }
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        alex = new int[n];
        lee = new int[n];
        for(int i = 0; i < n; i++){
            alex[i] = -1;
            lee[i] = -1;
        }
        this.piles = piles;
        dp(0, n-1,true);
        
        //scan alex and lee && compare + && -
        
        return true;
    }
    
    
    //<문제풀이1 by lee215>
    
    //씨발
    
    //알렉스는 언제나 선턴시작이니까, 홀수번째 숫자나 짝수번째 숫자만 골라뽑을 수 있음.
    
    //array의 총 합은 언제나 홀수니까, 홀수번째 숫자의 총합 > 짝수번째 숫자의 총합이라면, 홀수번째 숫자만 뽑으면 무조건 이김.
    
    //반대의 경우면 짝수번째 숫자만 뽑으면 무조건 이김
    
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Stone Game.
    //Memory Usage: 36.6 MB, less than 89.51% of Java online submissions for Stone Game.
    
    public boolean stoneGame(int[] piles) {
    	return true;
    }
    
    
    */
    
    //<문제풀이2 by aakarshmadhavan>
    
    //minimax + dp
    
    //minimax : 알렉스가 리를 이긴다는 말은 알렉스의 점수 > 리의 점수이고, 이걸 차원축소해서 하나의 변수로 만들면 알렉스의 점수 - 리의 점수 > 0은 알렉스가 이기는 것이 됨.
    
    //trial02를 3차원 배열로 풀 생각을 했었어야 했음. 
    
    //에고..
    
    
    
	//This is a Minimax problem. Each player plays optimally to win, but you can't greedily choose the optimal strategy so you have to try all strategies, this is DP now.
	//
	//What does it mean for Alex to win? Alex will win if score(Alex) >= score(Lee), but this also means score(Alex) - score(Lee) >= 0, so here you have a common parameter which is a score variable. The score parameter really means score = score(Alex) - score(Lee).
	//
	//Now, if each player is suppoed to play optimally, how do you decide this with one variable?
	//
	//Well since Alex is playing optimally, he wants to maximize the score variable because remember, Alex only wins if score = score(Alex) - score(Lee) >= 0 Alex should add to the score because he wants to maximize it.
	//Since Lee is also playing optimally, he wants to minimize the score variable, since if the score variable becomes negative, Lee has more individual score than Alex. But since we have only one variable, Lee should damage the score (or in other words, subtract from the score).
	//
	//Now, let's think of the brute force solution. You are at the current state, say this is Alex's turn. Alex can choose either left or right, but he can't greedily pick so you try both of them, this is O(2^n) for runtime.
	//
	//But realize the state you are in. You can easily memoize the this, the varying parameters are l, r, ID, where ID is the player ID (1 for Alex, 0 for Lee), hence you can make a DP/Cache table and return answer if you have discovered the state.
	//
	//Finally, in your main function you call this helper function and check if you were able to get a score >= 0.
    
    
	
	//{1,0,100,2} 넣으면 (0 = Lee, 1 = Alex)
	
	//-1 0 | 0 1 | -99 0 | 0 99 | 
	//0 0 | 0 0 | 0 100 | 98 0 | 
	//0 0 | 0 0 | -100 0 | 0 98 | 
	//0 0 | 0 0 | 0 0 | -2 0 | 
	
	//이렇게 됨.
	
	//https://www.youtube.com/watch?v=WxpIHvsu1RI&feature=emb_logo&ab_channel=TusharRoy-CodingMadeSimple
    
    //Runtime: 52 ms, faster than 6.85% of Java online submissions for Stone Game.
    //Memory Usage: 48.9 MB, less than 10.65% of Java online submissions for Stone Game.
    
    static int[] p;
    static int[][][] mem;
    
    private static int dp(int l, int r, int id){
        if(l > r) return 0;
        if(mem[l][r][id] != 0) return mem[l][r][id];
        int next = id ^ 1;
        if(id == 1){
            mem[l][r][id] = Math.max(p[l] + dp(l+1,r,next), p[r] + dp(l,r-1,next));
        } else{
            mem[l][r][id] = Math.min(-p[l] + dp(l+1,r,next), -p[r]+dp(l,r-1,next));
        }
        return mem[l][r][id];
    }
    
    public static boolean stoneGame(int[] piles) {
    	int n = piles.length;
    	p = piles;
        mem = new int[n][n][2];
        return dp(0, n-1,1) > 0;
    }
    
    
    //<문제풀이3 by lee215>
    
	//5 2 0 0 
	//0 3 0 0 
	//0 0 4 0 
	//0 0 0 5 
	
	//5 2 0 0 
	//0 3 1 0 
	//0 0 4 0 
	//0 0 0 5 
	
	//5 2 0 0 
	//0 3 1 0 
	//0 0 4 1 
	//0 0 0 5 
	
	//5 2 4 0 
	//0 3 1 0 
	//0 0 4 1 
	//0 0 0 5 
	
	//5 2 4 0 
	//0 3 1 4 
	//0 0 4 1 
	//0 0 0 5 
	
	//5 2 4 1 
	//0 3 1 4 
	//0 0 4 1 
	//0 0 0 5 
    
    //얘도 문제풀이2 처럼 알렉스, 리 따로구하는게 아니라 차원축소해서 알렉스 최적 스코어 - 리 최적 스코어가 양수인지 판별하는 법.
    
//    If you pick piles[i], your result will be piles[i] - dp[i + 1][j]
//    If you pick piles[j], your result will be piles[j] - dp[i][j - 1]
    
    //아 드디어 이해했네
    
    //이해력 레게노
    
    //int a = p[i];
	//int b = dp[i + 1][i + d];
	//int c = p[i + d];
	//int d = dp[i][i + d - 1];
    
    //이렇다고 치면, dp[i][j] = max(a-b, c-d)잖아?
    
    //그럼 여기서 앞애오는 애 a(c)는 뭐고 뒤에오는 애 b(d)는 뭔가~ 이 말이다.
    
    //일단 맨 처음애를 보자.
    
    //[5,3,4,5]
    
	//5 0 0 0 
	//0 3 0 0 
	//0 0 4 0 
	//0 0 0 5 
    
    //대각선에 이렇게 채워지는 이유는, 다음과 같다.
    
    //[5] 얘만 있어. 알렉스랑 리랑 게임해. 그러면 알렉스가 선턴이니까, 5 집고, 리는 아무것도 안남았으니까, 0이겠지? 이걸 [3],[4],[5]에 반복하는 것임
    
    //그럼 위에 매트릭스터럼 됨.
    
    //그럼 그 다음에 오는게
    
	//a: 5 b: 3 c: 3 d: 5
	//5 2 0 0 
	//0 3 0 0 
	//0 0 4 0 
	//0 0 0 5 
    
    //얘인데, 여기서 2는 대체 왜 오는거냐?
    
    //두번째 대각선은 한개씩 묶는게 아니라 두개씩 묶음. [5,3]
    
    //max(a-b, c-d)에서, 첫번째 부분 a-b는 제일 왼쪽을 집는것 부터 시작하는 경우, (== 5부터 집고 시작하는 경우) 이고
    
    //c-d는 [5,3]중에 제일 오른쪽 애를 집는것 부터 시작하는 경우(==3부터 집고 시작하는 경우)야.
    
    //a-b에서, 왼쪽부터 집고 시작한다고 했으니까, 알렉스는 5를 집으면, 리는 남은게 [3]밖에 없으니까 3집고 땡이겠네?
    
    //근데 위에 차원축소방식으로 푼다그랬으니까, 알렉스 점수 - 리의점수 > 0인지 보려면, 5-3 = 2 구해지네?
    
    //c-d는, 알렉스가 선턴인데 [5,3]중에 젤 오른쪽애 잡고 시작하니까 3, 리는 남은에가 [5]밖에 없으니 5 하면,
    
    //c(3) - d(5) 하면 -2.
    
    //max(2, -2) = 2. 해서 dp[i][j]의 최적해는 2가 나옴.
    
    //원리는 이거임. 이걸 반복해나감.
    
	//a: 5 b: 1 c: 4 d: 2
	//5 2 4 0 
	//0 3 1 0 
	//0 0 4 1 
	//0 0 0 5 
    
    //여기서 4가 왜 나왔는지 보자.
    
    //i~j까지니까 [5,3,4]네? 그러면 a-b는 알렉스가 젤 왼쪽애 잡고 시작하는거니까, 5를 집으면 alex +5.
    
    //리의 차례엔 [3,4]가 남네?
    
    //근데 여기서 잠깐!
    
    //[3,4]에 대한 최적해는 전에 이미 구해놨잖아. 밑에있는 1. 그걸 가져다 쓰면 되제.
    
    //그래서 a-b는 알렉스가 선턴에 집은 제일 왼쪽애 5 - [3,4]의 최적해인 1 해서 4가 나오는거지.
    
    //c-d도 같은 원리로 풀어서 max(a-b, c-d)하면 되고.
    
    //Runtime: 5 ms, faster than 59.14% of Java online submissions for Stone Game.
    //Memory Usage: 39.8 MB, less than 29.70% of Java online submissions for Stone Game.
    
    public boolean stoneGame(int[] p) {
        int n = p.length;
        int[][] dp  = new int[n][n];
        for (int i = 0; i < n; i++) dp[i][i] = p[i];
        for (int d = 1; d < n; d++){
            for (int i = 0; i < n - d; i++){
                dp[i][i + d] = Math.max(p[i] - dp[i + 1][i + d], p[i + d] - dp[i][i + d - 1]);
            }
        }
        return dp[0][n - 1] > 0;
    }
    
    //<문제풀이4 by lee215>
    
    //문제풀이 3에서 dp가 2차원 배열이었는데, 2차원 배열로 차원축소한 것
    
	//a: 5 b: 3 c: 3 d: 5
	//a: 3 b: 4 c: 4 d: 3
	//a: 4 b: 5 c: 5 d: 4
	//a: 5 b: 1 c: 4 d: 2
	//a: 3 b: 1 c: 5 d: 1
	//a: 5 b: 4 c: 5 d: 4
    
    //문제풀이3과 완벽히 동일함. 다만, 2차원 배열에서 불필요한 부분 자르고 쓰는 부분만 뽑아 1차원 배열로 압축한 것
    
    //Runtime: 7 ms, faster than 40.87% of Java online submissions for Stone Game.
    //Memory Usage: 38.8 MB, less than 60.86% of Java online submissions for Stone Game.
    
    public boolean stoneGame(int[] p) {
        int n = p.length;
        int[] dp = Arrays.copyOf(p, n);
        
        for (int d = 1; d < n; d++){
            for (int i = 0; i < n - d; i++){
                int a = p[i];
                int b = dp[i + 1];
                int c = p[i + d];
                int d_ = dp[i];
                
                dp[i] = Math.max(a-b, c-d_);
            }
        }
        return dp[0] > 0;
    }
    
    
    
    public static void main(String[] args) {
		int[] pile = {1,0,100,2};
		System.out.println(stoneGame(pile));
//        for(int[][] mat : mem){
//            for(int[] row : mat){
//                for(int ele : row) {
//                	System.out.print(ele+" ");
//                }
//                System.out.print("| ");
//            }
//            System.out.println();
//        }
//        System.out.println("============================");
	}
}
