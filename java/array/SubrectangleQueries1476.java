package array;

public class SubrectangleQueries1476 {

	//<문제풀이1>
	
	//무-난
	
	//Runtime: 46 ms, faster than 55.32% of Java online submissions for Subrectangle Queries.
	//Memory Usage: 52.1 MB, less than 50.00% of Java online submissions for Subrectangle Queries.
	class SubrectangleQueries {
	    int[][] r;
	    
	    public SubrectangleQueries(int[][] rectangle) {
	        r = rectangle;    
	    }
	    
	    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
	        for(int i = row1; i <= row2; i++){
	            for(int j = col1; j <= col2; j++){
	                r[i][j] = newValue;
	            }
	        }
	    }
	    
	    public int getValue(int row, int col) {
	        return r[row][col];
	    }
	}
	
	//<문제풀이2 by rock>
	
	//좀 신박한 방법
	
	//update시 r에 박는게 아니라 linkedlist에 박아놓고
	
	//get시 linkedlist에서 업데이트 한애들중에 있나 먼저 보고, 없다면 한번도 안건든 쌩 오리지널 애라는 말이니까, r[row][col]반환함
	
	//update가 몇번 일어나냐에 따라 다르겠지만, update사이즈가 클수록 이 방법이 더 빠를덧
	
	//Runtime: 35 ms, faster than 75.88% of Java online submissions for Subrectangle Queries.
	//Memory Usage: 52.9 MB, less than 50.00% of Java online submissions for Subrectangle Queries.
	
	class SubrectangleQueries {
	    private final int[][] r;
	    private final LinkedList<int[]> histories = new LinkedList<>();
	    
	    public SubrectangleQueries(int[][] rectangle) {
	        r = rectangle;
	    }
	    
	    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
	        histories.push(new int[]{row1, col1, row2, col2, newValue});
	    }
	    
	    public int getValue(int row, int col) {
	        for (int[] his: histories) {
	            if (his[0] <= row && row <= his[2] && his[1] <= col && col <= his[3]) {
	                return his[4];
	            }
	        }
	        return r[row][col];
	    }
	}
}
