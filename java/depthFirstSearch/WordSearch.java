package julyChallenge;

public class WordSearch {
	
	//<Trial01>
	
	//"adjacent cells"
	
	//문제좀 읽자 친구야
	
    public boolean exist(char[][] board, String word) {
        int[] alphabet = new int[58];
        for(char[] row : board){
            for(char c : row){
                alphabet[c-65]++;
            }
        }
        
        for(char c : word.toCharArray()){
            if(--alphabet[c-65] < 0){
                return false;
            }
        }
        return true;
    }
    
    
    //<Time Limit Exceeded>
    
    //dfs로 안되면 뭐 어쩌라는거지
    
    private boolean helper(char[][] board, int[][] visited, String word, int i, int j, int idx){
        if(visited[i][j] == 1 || idx >= word.length() || board[i][j] != word.charAt(idx)) return false;
        else if(idx == word.length()-1 && board[i][j] == word.charAt(idx)) return true;
        
        boolean flag = false;
        visited[i][j] = 1;
        
        if(i < board.length-1) flag |= helper(board,visited,word,i+1,j,idx+1);
        if(j < board[0].length-1) flag |= helper(board,visited,word,i,j+1,idx+1);
        if(i > 0) flag |= helper(board,visited,word,i-1,j,idx+1);
        if(j > 0) flag |= helper(board,visited,word,i,j-1,idx+1);
        
        visited[i][j] = 0;
        
        return flag;
    }
    
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == word.charAt(0)){
                    if(helper(board, new int[m][n], word, i, j, 0)){
                        return true;
                    };
                }
            }
        }
        return false;
    }
    
    
    
    //<문제풀이 1by harish-v>
    
    //flag변수 안쓰니까 통과되네
    
    //Runtime: 12 ms
    //Memory Usage: 41.8 MB
    
    private boolean helper(char[][] board, int[][] visited, String word, int i, int j, int idx){
        if(idx == word.length()) return true;
        
        if(i >= board.length || i < 0 || j >= board[i].length || j < 0 || visited[i][j] == 1 || board[i][j] != word.charAt(idx)) return false;
        
        visited[i][j] = 1;
        
        if(helper(board,visited,word,i+1,j,idx+1) 
            || helper(board,visited,word,i,j+1,idx+1)
            || helper(board,visited,word,i-1,j,idx+1)
            || helper(board,visited,word,i,j-1,idx+1)){
            return true;
        }
        
        visited[i][j] = 0;
        
        return false;
    }
    
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == word.charAt(0)){
                    if(helper(board, new int[m][n], word, i, j, 0)){
                        return true;
                    };
                }
            }
        }
        return false;
    }
    
    
    
	public static void main(String[] args) {
		//'a' == 97
		System.out.println('a'-'a'+32);//0~25
		System.out.println('z'-'a'+32);
		System.out.println('A'-'a'+32); //-32 ~ -7
		System.out.println('Z'-'a'+32);
		
		System.out.println(-97+32);
	}
}
