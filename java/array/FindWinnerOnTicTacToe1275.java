package Array;

public class FindWinnerOnTicTacToe1275 {
	
	/*
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
	*/
	
	//<문제풀이1 by kylewzk>
	
	//똑똑허이
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Winner on a Tic Tac Toe Game.
	//Memory Usage: 34.4 MB, less than 100.00% of Java online submissions for Find Winner on a Tic Tac Toe Game.
	
    public String tictactoe(int[][] m) {
        if(check(m, 0)) return "A";
        if(check(m, 1)) return "B";
        if(m.length == 9) return "Draw";
        return "Pending";
    }
    
    boolean check(int[][] m, int s) {
        int[] r = new int[3], c = new int[3];
        int d1 = 0, d2 = 0;
        
        while(s < m.length) {
            if(++r[m[s][0]] == 3) return true; //가로로 3번 등장하면 빙고
            if(++c[m[s][1]] == 3) return true; //세로로 3번 등장하면 빙고
            if(m[s][0] == m[s][1] && ++d1 == 3) return true; //좌상 우하 직선 빙고 
            if(m[s][0] + m[s][1] == 2 && ++d2 == 3) return true; //우상 좌하 직선 빙고
            s += 2; //A는 A만, B는 B만 봐야 되기 때문에 하나씩 건너 뜀.
        }
        return false;
    }
}
