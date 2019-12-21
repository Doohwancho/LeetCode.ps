package Array;

public class FindWinnerOnTicTacToe1275 {
	
	
	//<Trial01>
	
	//Input : [[0,0],[1,1],[2,0],[1,0],[1,2],[2,1],[0,1],[0,2],[2,2]]
	//Output : "A"
	//Expected : "Draw"
    
	//그냥 단순히 x,y좌표를 매 loop마다 A,B따로 더해서,
	
	//tic-tac-toe에서 이길때 나오는 경우의 수에 해당될 때([3,(0,3,6)] [(0,3,6),3] [3, 3])
	
	//반환하면 된다고 생각했는데, 
	
	//"XXO"
	//"OOX"
	//"XOX"

	//여기서 X가 4번째 턴일때 값을 모두 더하면 [3,3]이 되지만 3개 똑바로 따다당이 안되서 실패.
	
	public String tictactoe(int[][] moves) {        
	        int[] A = new int[2];
	        int[] B = new int[2];
	        int turn = 0;
	        
	        for(int i = 0; i < moves.length; i++){
	            if(i % 2 == 0){
	                A[0]+=moves[i][0];
	                A[1]+=moves[i][1];
	                if(i >= 4){
	                    if(A[0] == 3){
	                        if(A[1] == 0 || A[1] == 3 || A[1] == 6){
	                            return "A";
	                        }
	                    }
	                    else if((A[0] == 0 || A[0] == 6) && A[1] == 3){
	                        return "A";
	                    }
	                }
	            }
	            else if(i % 2 == 1){
	                B[0]+=moves[i][0];
	                B[1]+=moves[i][1];
	                if(i >= 4){
	                    if(B[0] == 3){
	                        if(B[1] == 0 || B[1] == 3 || B[1] == 6){
	                            return "B";
	                        }
	                    }
	                    else if((B[0] == 0 || B[0] == 6) && B[1] == 3){
	                        return "B";
	                    }
	                }
	            }
	            turn++;
	        }
	        return turn == 9 ? "Draw" : "Pending";
	    }
}
