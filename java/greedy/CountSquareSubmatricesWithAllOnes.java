package mayChallenge;

public class CountSquareSubmatricesWithAllOnes {

	
	//<문제풀이1 by lee215>
	
	//그리드 방식이네. 그 칸에 도착했을때 바로 필요한 칸만큼만 더해주는 방식
	
	//예를들어 
	
	//[1,1],
	//[1,1]
	
	//를 더한다고 치면,
	
	//(x,y)가 (1,1)이전까지는 1씩 더해주다가,
	
	//(1,1)이 도착해서 2x2상자가 만들어 지면,
	
	//(1,1)좌표에 있는 한칸짜리 박스 한개랑, 2x2 박스를 만들었을때 하나 해서 2개를 더해주는 방식
	
	//[1,1,1],
	//[1,1,1],
	//[1,1,1]
	
	//을 봐도,
	
	//처음 4개째 까지는 1씩 더하다가, x,y좌표가 (1,1)부터는, 한칸짜리 박스 +1이랑 2x2짜리박스 +1씩 총 2씩 더해담.
	
	//그러다가 x,y좌표가 (3,3)일땐, 한칸짜리 박스 하나, 두칸짜리 박스 귀퉁이꺼 하나, 세칸짜리 박스 하나 해서 +3을 더해줌
	
    // [0,1,1,1],
    // [1,1,1,1],
    // [0,1,1,1]
	
    // [0,1,1,1],
    // [1,1,2,2],
    // [0,1,2,3]
	
	//4+11(=1+2+2+1+2+3)
	
	//Runtime: 7 ms
	//Memory Usage: 48.6 MB
    
    public int countSquares(int[][] matrix) {
        int m = matrix.length-1;
        int n = matrix[0].length-1;
        int rst = 0;
        
        for(int i = m; i >= 0; i--){
            for(int j = n; j >= 0; j--){
                if(matrix[i][j] == 1){
                    rst += check(matrix, i, j, i, j);
                }
            }
        }
        return rst;
    }
}
