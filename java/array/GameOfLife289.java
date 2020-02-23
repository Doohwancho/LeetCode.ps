package array;

public class GameOfLife289 {
	
	//<문제풀이1 by yavinci>
	
	//또라이네 이거
	
	//[2nd bit, 1st bit] = [next state, current state]
	//
	//- 00  dead (next) <- dead (current)  No need to care
	//- 01  dead (next) <- live (current)  -> ! if (board[i][j] == 1 && lives >= 2 && lives <= 3) -> 그대로 01(dead when next staged) 
	//- 10  live (next) <- dead (current)  -> if (board[i][j] == 0 && lives == 3)
	//- 11  live (next) <- live (current)  -> if (board[i][j] == 1 && lives >= 2 && lives <= 3) 
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Game of Life.
	//Memory Usage: 37.9 MB, less than 7.69% of Java online submissions for Game of Life.
	
	public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = liveNeighbors(board, m, n, i, j); //이부분까지는 brute-force로 예상가능

                if (board[i][j] == 1 && lives >= 2 && lives <= 3) {  
                    board[i][j] = 3; // Make the 2nd bit 1: 01 ---> 11
                }
                if (board[i][j] == 0 && lives == 3) {
                    board[i][j] = 2; // Make the 2nd bit 1: 00 ---> 10
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;  // Get the 2nd state. - 미쳤다. 도랐네
            }
        }
    }

    public int liveNeighbors(int[][] board, int m, int n, int i, int j) {
        int lives = 0;
        for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) { //while로 어떻게 할까 고민했는데 Math.max(i-1,0)올 해버리네. 똑똑허이
            for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
                lives += board[x][y] & 1; //if(board[x][y] == 1, lives++; bitmask로 표현하여 더 빠르게 한 것.. 이 아니라 board[i][j] = 3으로 한것도 캐치하기 위해서 한 것이네.
            }
        }
        lives -= board[i][j] & 1; //본인은 빼야지
        return lives;
    }
}
