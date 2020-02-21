package array;

public class SpiralMatrixII59 {
	
	/*
	//<Trial01>
	
	//dfs
	
	//잘가다가 11에서 우회전해서 망함
	
	//1 2 3 4 
	//0 0 0 5 
	//11 12 0 6 
	//10 9 8 7 
	
	public int[][] generateMatrix(int n) {
        int[][] rst = new int[n][n];
        int[] coord = new int[2];
        
        for(int i = 1; i < n*n+1; i++){
            
            rst[coord[0]][coord[1]] = i;
            if(coord[1] < n-1 && rst[coord[0]][coord[1]+1] == 0){
                coord[1]++;
            }
            else if(coord[0] < n-1 && rst[coord[0]+1][coord[1]] == 0){
                coord[0]++;
            }
            else if(coord[1] > 0 && rst[coord[0]][coord[1]-1] == 0){
                coord[1]--;
            }
            else if(coord[0] > 0 && rst[coord[0]-1][coord[1]] == 0){
                coord[0]--;
            }
        }
        return rst;
    }
    */
	
	
	//<문제풀이1>
	
	//dfs
	
	//특이점은 오른쪽으로 가는 부분의 조건절 (i < n || (coord[0] > 0 && rst[coord[0]-1][coord[1]] != 0))
	
	//자꾸 11에서 12넘어갈때 막히길래
	
	//1 2 3 4 
	//0 0 0 5 
	//11 12 0 6 
	//10 9 8 7
	
	//해당 좌표의 바로 위의 수가 0이 아닌 수로 채워지지 않았다면, 오른쪽으로 방향을 바꾸지 말라는게 이부분 (coord[0] > 0 && rst[coord[0]-1][coord[1]] != 0)
	
	//i < n 이부분은 맨 첫줄부터 (coord[0] > 0 && rst[coord[0]-1][coord[1]] != 0)
	
	//이게 터져서 진행이 안되서 맨 첫줄만 오른쪽으로 좀 가라라고 설정한 것
	
	//이거지
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Spiral Matrix II.
	//Memory Usage: 37.3 MB, less than 8.33% of Java online submissions for Spiral Matrix II.
	
	public int[][] generateMatrix(int n) {
		int[][] rst = new int[n][n];
        int[] coord = new int[2];

        for(int i = 1; i < n*n+1; i++){
            rst[coord[0]][coord[1]] = i; 
            
            if((i < n || (coord[0] > 0 && rst[coord[0]-1][coord[1]] != 0)) && coord[1] < n-1 && rst[coord[0]][coord[1]+1] == 0){
                coord[1]++;
            }
            else if(coord[0] < n-1 && rst[coord[0]+1][coord[1]] == 0){
                coord[0]++;
            }
            else if(coord[1] > 0 && rst[coord[0]][coord[1]-1] == 0){
                coord[1]--;
            }
            else if(coord[0] > 0 && rst[coord[0]-1][coord[1]] == 0){
                coord[0]--;
            }
        }
        return rst;
    }
}
