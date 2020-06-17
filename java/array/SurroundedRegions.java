package juneChallenge;

public class SurroundedRegions {
	
	//<문제풀이1>
	
	//board를 for문으로 돌면서 O를 만나면, O가 가장자리까지 쭉 이어지나 보고, 가장자리까지 붙어있으면, 안건들고,
	
	//가장자리에 안붙어있으면, 4면이 모두 'X'로 둘러쌓여있다는 말이니까, 'O'에서 'X'로 바꿔줘야 겠지?
	
	//근데 board에서 돌다가 'O'가 나와서 붙어있는애들 다 'X'로 바꿔주고 있었는데, 
	
	//'O'중 하나가 가장자리에 붙어있는걸 확인했다면?(== 4면이 'X'로 둘러쌓여있지 않은걸 확인했다면?)
	
	//그럼 다시 'X'->'O' 할꺼야? 무슨기준으로? arraylist에 'O'->'X'바꿀때마다 좌표를 int[i][j]식으로 담아놓으려고?
	
	//이러지말고, board를 복사한 copy를 for문으로 돈다음에, copy에서 'O' 4면이 'X'둘러쌓인 섬인지 보는거야.
	
	//볼때 한번 다녀간 곳은'O'를 'U'로 바꿔주고. 다시 돌 필요 없게.
	
	//그리고 가장자리에 'O'가 있는지 확인해주는거지. 조건이 충족되면 그제서야 board를 바꿔주는거고.
	
	//Runtime: 3 ms
	//Memory Usage: 45.4 MB
	
	//붙어있는 'O'들 중, 가장자리에 하나라도 있으면 true를 반환하는 함수
	private static boolean check(char[][] board, int i, int j){
    	if(i < 0 || j < 0 || i == board.length || j == board[0].length || board[i][j] == 'X') return false; //범위 유효성 검사
        boolean flag = false;
		board[i][j] = 'U'; //한번 돈 곳은 'U'로 마크해서 다음번 iterate시엔 먼저 확인한 곳을 또 확인 안하도록 한다.
		if(i == 0 || j == 0 || i == board.length-1 || j == board[0].length-1) return true; //가장자리인데 'O'라면, return true
		
        //up
        if(i > 0 && board[i-1][j] == 'O') flag |= check(board, i-1, j);
        //right
        if(j < board[0].length-1 && board[i][j+1] == 'O') flag |= check(board, i, j+1);
        //down
        if(i < board.length-1 && board[i+1][j] == 'O') flag |= check(board, i+1, j);
        //left
        if(j > 0 && board[i][j-1] == 'O') flag |= check(board, i, j-1);
        
        return flag;
    }
    
    private static void dfs(char[][] board, int i, int j){
        board[i][j] = 'X';
        //up
        if(i > 0 && board[i-1][j] == 'O') dfs(board, i-1, j);
        //right
        if(j < board[0].length-1 && board[i][j+1] == 'O') dfs(board, i, j+1);
        //down
        if(i < board.length-1 && board[i+1][j] == 'O') dfs(board, i+1, j);
        //left
        if(j > 0 && board[i][j-1] == 'O') dfs(board, i, j-1);
        
    }
    
    public static void solve(char[][] board) {
    	if(board.length == 0) return;
    	
        char[][] copy = new char[board.length][board[0].length];
        for(int p = 0; p < board.length; p++){
            for(int q = 0; q < board[0].length; q++){
                copy[p][q] = board[p][q];
            }
        }
        for(int i = 1; i < board.length-1; i++){
            for(int j = 1; j < board[0].length-1; j++){
                if(copy[i][j] == 'O'){ 
                	if(!check(copy, i, j)) { //가장자리에 하나도 'O'가 붙어있지 않다면, 4면이 'X'로 둘러쌓였다는 말이니까, 
                        dfs(board, i, j); //board에서 'O'를 'X'로 바꿔준다.
                    }
                }
            }
        }
    }
}
