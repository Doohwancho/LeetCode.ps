/*
	On an 8 x 8 chessboard, there is one white rook.  There also may be empty squares, white bishops, and black pawns.  These are given as characters 'R', '.', 'B', and 'p' respectively. Uppercase characters represent white pieces, and lowercase characters represent black pieces.
	
	The rook moves as in the rules of Chess: it chooses one of four cardinal directions (north, east, west, and south), then moves in that direction until it chooses to stop, reaches the edge of the board, or captures an opposite colored pawn by moving to the same square it occupies.  Also, rooks cannot move into the same square as other friendly bishops.
	
	Return the number of pawns the rook can capture in one move.
	
	
	<문제>
	
	룩은 'R', 비숍은 'B', 폰은'p'이다. 룩,비숍,폰은 8x8 체스판 위에 있다.
	
	흰색은 대문자이고, 흑색은 소문자 이다. 고로,룩과 비숍은 대문자 이기에 흰색이고 폰은 소문자 이기에 검은색이다.
	
	룩, 비숍, 폰이 움직이는 방법은 체스의 규칙과 같다.(룩은 동서남북 직선으로밖에 움직일 수 없다.)
	
	룩이 위치한 곳에서 폰을 먹으려고 할 때, 한턴에 먹을 수 있는 최대 갯수의 폰을 반환하면 된다.
	
	단, 폰과 룩 사이에 비숍이나 다른 폰이 있다면 먹을 수 없다.
	
	ex1) p B R 인 경우, 비숍이 막고있기 때문에 룩은 폰을 먹을 수 없다.
	
	ex2) p p R 인 경우, 두번째 폰은 룩이 먹을 수 있지만, 첫번째 폰은 룩이 먹을 수 없다. 두번째 폰이 길을 막고있기 때문이다.
	
	
	
	
	<문제 풀이 by lee215>
	
	step01) 룩의 좌표가 되줄 x0, y0변수와 마지막에 폰을 잡은 횟수를 담을 res변수 선언
	step02) 2중 for문으로 룩의 좌표를 찾아 x0,y0에 담는다
	step03) 룩의 좌표를 기준으로, 왼쪽, 오른쪽, 위쪽, 아랫쪽으로 한칸씩 옮긴 후, 나머지 남은 칸들을 for문으로 돌면서 p가 나오면 res를 +1해줌
	
 * 
 * 
 */

package Array;

public class AvailableCapturesForRook999 {
	public static int numRookCaptures(char[][] board)
	{
		//step01
		int x0 = 0, y0 = 0, res = 0;
		
		//step02
		outerloop:
		for(int rowIdx = 0; rowIdx < 8 ; rowIdx++) //indexOf
		{
			for(int columnIdx = 0; columnIdx < 8; columnIdx++)
			{
				if(board[rowIdx][columnIdx] == 'R')
				{
					x0 = rowIdx;
					y0 = columnIdx;
					break outerloop; //2중 for문 break
				}
			}
		}
		
		//step03
		for(int[] d : new int[][] {{1,0}, {0,1}, {-1,0}, {0,-1}})
		{
			for(int x = x0+d[0], y = y0+d[1]; 0 <= x && x < 8 && 0 <= y && y < 8; x += d[0], y += d[1])
			{
				if(board[x][y] == 'p') res++;
				if(board[x][y] != '.') break;
			}
		}
		
		return res;
	}
	
	
	public static void main(String[] args) 
	{
		char[][] board = {{'.','.','.','.','.','.','.','.'},
						  {'.','.','.','p','.','.','.','.'},
						  {'.','.','.','p','.','.','.','.'},
						  {'p','p','.','R','.','p','B','.'},
						  {'.','.','.','.','.','.','.','.'},
						  {'.','.','.','B','.','.','.','.'},
						  {'.','.','.','p','.','.','.','.'},
						  {'.','.','.','.','.','.','.','.'}};
		
		System.out.println(numRookCaptures(board));
		
	}
}
