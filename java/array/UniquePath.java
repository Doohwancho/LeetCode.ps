package juneChallenge;

public class UniquePath {
	
	//<문제풀이1>
	
	//dp
	
	//4x4를 예로듬
	
	//각 칸당 최소 몇번 움직여야 올 수 있는지 적기 위해 2차원 배열을 만듬
	
	//0 0 0 0
	//0 0 0 0
	//0 0 0 0
	//0 0 0 0
	
	//첫번째줄 가로세로는 뭔짓을 해도 한번의 move로밖에 갈 수 없으니 1로 채움
	
	//1 1 1 1
	//1 0 0 0
	//1 0 0 0
	//1 0 0 0
	
	//이제 나머지는 어떻게 채울까 인데..
	
	//일일이 손으로 해보면 이런 결과가 나옴
	
    //1 1 1 1
    //1 2 3 4
    //1 3 6 10
    //1 4 10 20
	
	//자세히보면 왼쪽칸+위칸의 합이란걸 알 수 있음
	
	//그걸 dp[i][j] = dp[i-1][j] + dp[i][j-1];
	
	//이렇게 녹임
	
	//Runtime: 0 ms
	//Memory Usage: 35.9 MB
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        
        for(int i = 0; i < m; i++){
            dp[i][0] = 1;
        }
        for(int j = 0; j < n; j++){
            dp[0][j] = 1;
        }
        
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}
