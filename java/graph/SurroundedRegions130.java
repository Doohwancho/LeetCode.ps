package graph;

public class SurroundedRegions130 {

	//<문제풀이1>
	
	
	//가장자리에 'O'랑 이어진 애들 일단 'A'로 바꿈.
	
	//그러면 나머지 'O'들은 가장자리랑 안 이어진 애들이란 말이니까, 얘네들을 'X'로 바꾸고
	
	//아까 가장자리랑 이어진 'A'들을 다시 'O'로 바꿈.
	
	//Runtime: 1 ms, faster than 99.84% of Java online submissions for Surrounded Regions.
	//Memory Usage: 41.1 MB, less than 6.35% of Java online submissions for Surrounded Regions.
	
	private void edge(char[][] board, int i , int j, Character c){
        if(board[i][j] == 'X' || board[i][j] == c) return;
        
        board[i][j] = c;
        
        if(i > 0) edge(board, i-1, j, c);
        if(j > 0) edge(board, i, j-1, c);
        if(i < board.length-1) edge(board, i+1, j, c);
        if(j < board[0].length-1) edge(board, i, j+1, c);
    }
    
    public void solve(char[][] board) {
        if(board.length == 0) return;
        
        int m = board.length;
        int n = board[0].length;
        int rst = 0;
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 || j == 0 || i == m-1 || j == n-1){
                    edge(board, i, j, 'A');    
                }
            }
        }
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j]== 'O'){ 
                    board[i][j] = 'X';
                }
            }
        }
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j]== 'A'){
                    board[i][j] = 'O';
                }
            }
        }
    }
}
