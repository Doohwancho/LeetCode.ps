package thirtyDayChallenge;

public class MaximalSquare {
	

	/*
	
	//<문제풀이 1>

	
//	[["0","0","0","1"],
//	 ["1","1","0","1"],
//	 ["1","1","1","1"],
//	 ["0","1","1","1"],
//	 ["0","1","1","1"]]
			 
	// 0 0 0 1 
	// 1 1 0 1 
	// 1 2 1 1 
	// 0 1 1 2 
	// 0 1 2 2 
	
	//정사각형의 한 변의 길이가 1->2->3 순차적으로 늘어나니까, 한변의 최대길이를 구하고, 맨 마지막에 답 리턴할때 변의길이 * 변의길이를 해주면 된다.
	
	//한 변의 길이를 확인하려면, [i-1][j-1]가 0보다 큰지 확인한 다음, 0보다 크다면, 정사각형이 되는 조건중에 하나인 일단 왼쪽 위 꼭짓점과 오른쪽 아래 꼭짓점은 있다는 얘기니까, 
	
	//가로세로가 비어있는지 확인해준다.
	
	//만약 밑변과 오른쪽 세로변이 다 채워져 있다면, matrix[i][j] = matrix[i-1][j-1]을 해준다.
	
	//이렇게 해주면, 원래 아래처럼 생긴 matrix가
	
	//111
	//111
	//111
	
	//아래처럼 변한다.
	
	//111
	//122
	//123
	
	//그럼 가장 큰 변의 길이인 3*3을 리턴해 주면 된다.
	
	//그런데 문제는, 
	 
	// 1 1 0 0 
	// 1 1 1 1 
	// 0 1 1 1 
	// 0 1 1 1
	
	//이거다.
	
	// 1 1 0 0 
	// 1 2 1 1 
	// 0 1 1 1 
	// 0 1 1 1
	
	//요기까지는 그려려니 하는데, 
	
	// 1 1 0 0 
	// 1 2 1 1 
	// 0 1 ? 1 
	// 0 1 1 1
	
	//?표 부분에서 밑변과 세로변이 모두 1로 채워지지 않았기 때문에 문제가 생기는데, 이러한 경우를 대비해
	
	//밑변을 t1, 세로변을 t2로 처리해 밑변과 세로변이 [i-1][j-1]번 만큼 다 채워지지 않았더라도, 최대한 채워진 만큼을 [i][j]에 업데이트 시켜준다.
	
	//69 / 69 test cases passed.
	//Status: Accepted
	//Runtime: 3 ms
	//Memory Usage: 43 MB
	
	
    public int maximalSquare(char[][] matrix) {
        if(matrix.length==0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int rst = 0;
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j]-48 == 1){
                    rst = Math.max(rst, 1);
                    if(i>0 && j > 0 && matrix[i-1][j-1]-48 > 0){
                        int limit = matrix[i-1][j-1]-48;
                        int t1 = 0;
                        int t2 = 0;
                        boolean flag = false;
                        for(int p = i-1; p >= i-limit; p--){
                            if(matrix[p][j]-48==0){
                                flag = true;
                                break;
                            }else {
                            	t1++;
                            }
                        }
                        for(int q = j-1; q >= j-limit; q--){
                            if(matrix[i][q]-48==0){
                                flag = true;
                                break;
                            } else {
                            	t2++;
                            }
                        }
                        if(flag) {
                        	if(Math.min(t1, t2) > 0) {
                        		matrix[i][j] = Character.forDigit(Math.min(t1, t2)+1,10);
                        	}
                        } else {
                        	matrix[i][j] = (char)(matrix[i-1][j-1]+1);
                        }
                        
                        rst = Math.max(rst, matrix[i][j]-48);
                    }
                }
            }
        }
        
        return rst * rst;
    }
    */
	
	//<문제풀이2 by earlme>
	
	//아 개쉬운거였네
	
	//[i][j]가 정사각형이 되는 1이면, 위, 옆,대각선인 [i-1][j-1],[i-1][j],[i][j-1] 셋들중에 젤 작은애+1씩 시켜주면 되잖어.
	
	//111
	//111
	//111
	
	//도, 
	
	//0000
	//0111
	//0111
	//0111
	
	//로 세팅해 준 다음에 저 로직을 돌리면
	
	//0000
	//0111
	//0122
	//0123 
	
	//이 되잖어. 문제풀이1에서 고생했던 요 testcase도, 이 로직대로 하면,
	
	// 1 1 0 0 
	// 1 1 1 1 
	// 0 1 1 1 
	// 0 1 1 1
	
	//요게 이렇게 변함
	
	// 1 1 0 0 
	// 1 2 1 1 
	// 0 1 '2' 2 
	// 0 1 2 3
	
	//'2'요 부분이 핵심인데, 밑변과 세로변이 꽉 안차있다는걸 이미 [i-1][j]랑 [i][j-1]돌때 파악해놨자너~
	
	public static int maximalSquare(char[][] a) {
	    if(a.length == 0) return 0;
	    int m = a.length, n = a[0].length, result = 0;
	    int[][] b = new int[m+1][n+1];
	    for (int i = 1 ; i <= m; i++) {
	        for (int j = 1; j <= n; j++) {
	            if(a[i-1][j-1] == '1') {
	                b[i][j] = Math.min(Math.min(b[i][j-1] , b[i-1][j-1]), b[i-1][j]) + 1;
	                result = Math.max(b[i][j], result); // update result
	            }
	        }
	    }
	    return result*result;
	}
    
    
	public static void main(String[] args) {
//		char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
//		char[][] matrix = {{'1','1','1','1'},{'1','1','1','1'},{'1','1','1','1'}};	
		char[][] matrix = {{'0','0','0','1'},{'1','1','0','1'},{'1','1','1','1'},{'0','1','1','1'},{'0','1','1','1'}};
				
		System.out.println(maximalSquare(matrix));
	}
}
