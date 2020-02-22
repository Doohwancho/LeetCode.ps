package array;

public class UniquePath62 {
	
	//<문제풀이1>
	
	//잘 생각해보면 일정패턴이 있음.
	
	//m x n (where m = 3, n = 7)
    
    //xxxxxxx
    //xxxxxxx
    //xxxxxxx
    
    //1 1 1  1  1  1  1
    //1 2 3  4  5  6  7
    //1 3 6 10 15 21 28
	
	//찾게 된 계기 : 역순으로 생각함.
	
	//m x n (where m = 2, n = 2)
	
	//xx
	//xx
	
	//??
	//?2
	
	//가장 단순한 모델을 생각하면 결과값이 2임.
	
	//2 x 3모델을 보면
	
	//???
	//?23
	
	//결과값이 3임. 이건 3x2로 보고 위에 2x3이랑 합치면
	
	//???
	//?23
	//?3
	
	//이렇게 나옴. 다음은 3x3을 보면
	
	//???
	//?23
	//?36
	
	//직접 세보면 이런 결과가 나옴. 나머지 ?는 모두 1이란걸 알수있음. 원점에서 그곳으로 가는방법이 직진 단 하나뿐이기 때문.
	
	//그러면
	
	//111
	//123
	//136
	
	//이런식으로 되는데, 여기서 패턴을 발견할 수 있음. 
	
	//1)row index 0과 column index0은 모두 1로 채워졌구나. 
	
	//2)(1,1)부터 위엣거 왼쪽거 합한게 해당 셀의 값이 되는구나... 라고. 
	
	//크.. 이거지
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
	//Memory Usage: 36.3 MB, less than 5.10% of Java online submissions for Unique Paths.
    public int uniquePaths(int m, int n) {
        int[][] grid = new int[m][n];
        
        for(int i = 0; i < m; i++){
            grid[i][0] = 1;
        }
        
        for(int j = 0; j < n; j++){
            grid[0][j] = 1;
        }
        
        for(int p = 1; p < m; p++){
            for(int q = 1; q < n; q++){
                grid[p][q] = grid[p-1][q]+grid[p][q-1];
            }
        }
        
        return grid[m-1][n-1];
    }

}
