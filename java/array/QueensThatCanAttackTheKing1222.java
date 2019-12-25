/*
	On an 8x8 chessboard, there can be multiple Black Queens and one White King.
	
	Given an array of integer coordinates queens that represents the positions of the Black Queens, and a pair of coordinates king that represent the position of the White King, return the coordinates of all the queens (in any order) that can attack the King.
	
	Input: queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
	Output: [[0,1],[1,0],[3,3]]
	Explanation:  
	The queen at [0,1] can attack the king cause they're in the same row. 
	The queen at [1,0] can attack the king cause they're in the same column. 
	The queen at [3,3] can attack the king cause they're in the same diagnal. 
	The queen at [0,4] can't attack the king cause it's blocked by the queen at [0,1]. 
	The queen at [4,0] can't attack the king cause it's blocked by the queen at [1,0]. 
	The queen at [2,4] can't attack the king cause it's not in the same row/column/diagnal as the king.





	<문제>
	
	queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
	
	체스보드에 킹 하나와 퀸 여러개가 있고, 좌표는 위와같이 주어진다.
	
	이 때, 킹을 공격(체크)할 수 있는 퀸들의 포지션을 어레이리스트에 담아 반환하라.

 */

package Array;

import java.util.ArrayList;
import java.util.List;

public class QueensThatCanAttackTheKing1222 {
	
	//<문제풀이1>
	
	//Runtime: 1 ms, faster than 98.57% of Java online submissions for Queens That Can Attack the King.
	//Memory Usage: 36.5 MB, less than 100.00% of Java online submissions for Queens That Can Attack the King.
	
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        //동서남북 + 좌상,우하,우상,좌하

        //0-동 1-서 2-남 3-북  || 4-좌상 5-우하 6-우상 7-좌하
        int[][] container = new int[8][2]; 
        //mark -1 as unfilled
        for(int[] i : container){
            i[0] = -1;
        }
        
        for(int[] queen : queens){
            //동서(01)
            if(queen[1] == king[1]){
                if(queen[0] < king[0] && (container[1][0] == -1 || queen[0] > container[1][0])){
                    container[1] = queen;
                } else if(queen[0] > king[0] && (container[0][0] == -1 || queen[0] < container[0][0])){
                    container[0] = queen;
                }
            }
            //남북(23)
            else if(queen[0] == king[0]){
                if(queen[1] < king[1] && (container[3][0] == -1 || queen[1] > container[3][1])){
                    container[3] = queen;
                } else if(queen[1] > king[1] && (container[2][0] == -1 || queen[1] < container[2][1])){
                    container[2] = queen;
                }
            }
            //좌상우하(45)
            else if(queen[0]-king[0] == queen[1]-king[1]){
                if(queen[0] < king[0] && (container[4][0] == -1 || queen[0] > container[4][0])){
                    container[4] = queen;
                } else if(queen[0] > king[0] && (container[5][0] == -1 || queen[0] < container[5][0])){
                    container[5] = queen;
                }
            }
            //우상좌하(67)
            else if(queen[0]-king[0] == king[1]-queen[1]){
                if(queen[0] > king[0] && (container[6][0] == -1 || queen[0] < container[6][0])){
                    container[6] = queen;
                } else if(queen[0] < king[0] && (container[7][0] == -1 || queen[0] > container[7][0])){
                    container[7] = queen;
                }
            }
        }
        
        //답 옮겨담기
        List<List<Integer>> rst = new ArrayList<>();
        for(int[] ans : container){
            if(ans[0] != -1){
                List<Integer> tmp = new ArrayList<>();
                tmp.add(ans[0]);
                tmp.add(ans[1]);
                rst.add(tmp);
            }
        }
        return rst;
    }

}
