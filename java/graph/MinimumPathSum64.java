package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinimumPathSum64 {

	//<Time Limit Exceeded>
	
	//Dijkstra

	//27 / 61 test cases passed.
	
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dirs = new int[][]{{1,0},{0, 1}};
        Queue<int[]> pq = new PriorityQueue<>((a,b) -> a[0]-b[0]);
        pq.offer(new int[] {grid[0][0], 0, 0});
        
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int acc = curr[0];
            int x = curr[1];
            int y = curr[2];
            
            if(x == m-1 && y == n-1) return acc;
            
            for(int[] dir : dirs){
                int newX = x+dir[0];
                int newY = y+dir[1];
                if(newX >= m || newY >= n) continue;
                pq.offer(new int[] {acc+grid[newX][newY], newX, newY});
            }
        }
        
        return 0;
    }
    
    //<문제풀이1>
    
    //Dijkstra
    
    //Runtime: 439 ms, faster than 5.26% of Java online submissions for Minimum Path Sum.
    //Memory Usage: 52.3 MB, less than 5.03% of Java online submissions for Minimum Path Sum.
    
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] matrix = new int[m][n];
        for(int[] mat : matrix){
            Arrays.fill(mat, Integer.MAX_VALUE);
        }
        matrix[0][0] = grid[0][0];
        int[][] dirs = new int[][]{{1,0},{0, 1}};
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {grid[0][0], 0, 0});
        
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int acc = curr[0];
            int x = curr[1];
            int y = curr[2];
            
            for(int[] dir : dirs){
                int newX = x+dir[0];
                int newY = y+dir[1];
                if(newX >= m || newY >= n) continue;
                if(acc+grid[newX][newY] < matrix[newX][newY]){
                    q.offer(new int[] {acc+grid[newX][newY], newX, newY});
                    matrix[newX][newY] = acc+grid[newX][newY];
                }
            }
        }
        
        return matrix[m-1][n-1] == Integer.MAX_VALUE ? 0 : matrix[m-1][n-1];
    }
    
    
    
    //<Trial02>
    
    //recursive - dfs
    
    //25 / 61 test cases passed.
    //Status: Time Limit Exceeded
    
    //최적화 하려면 minimum cost인 애 위주로 돌아야 하는데, 만약 그렇게 짜면,
    
    //A 길이 처음엔 minimum cost다가 후반가서 cost가 엄청 높아지는데,
    
    //B 길이 처음엔 cost가 높다가 후반가서 cost가 낮아져서 B길을 택해야 하는 경우 막힐텐데..
    
    public int recursive(int[][] grid, int[][] matrix, int m, int n, int prev){
        if(m < 0 || n < 0) return Integer.MAX_VALUE;
        matrix[m][n] = Math.min(matrix[m][n], grid[m][n] + prev); 
        if(m == 0 && n == 0) return matrix[m][n];
        return Math.min(recursive(grid, matrix, m-1, n, matrix[m][n]), recursive(grid, matrix, m, n-1, matrix[m][n]));
    }
    
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] matrix = new int[m][n];
        for(int[] row : matrix){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        return recursive(grid, matrix, m-1, n-1, 0);
    }
    
    
    //<문제풀이2 by JayOMG>
    
    //dp
    
    //일단 coord(0,0)까지 간 다음, m,n이 0일땐 세로 가로 1자로 챱챱챱 값을 넘겨주고
    
    //m,n이 0이 아닐 땐, 해당 좌표에서 위에 한칸과 왼쪽 한칸애 애 중 더 작은 애들을 골라 챱챱챱 넘겨줌
    
    //자세히 보면, Math.min(gridSum(grids, m-1, n), gridSum(grids, m, n-1)); 에서
    
    //gridSum(grids, m-1, n)먼저 recursive로 계~속 감.
    
    //쭉쭉 위로 올라감.
    
    //그러다가 m==0이 되면, if(m==0)에 걸려, 쭉쭉 왼쪽으로 감.
    
    //coord(0,0)부터 오른쪽으로 순서대로 채우고,
    
    //한칸 내려와서 그 다음줄 왼쪽 상단부터 오른쪽으로 순서대로 채우고, 이러는 식.
    
	//0 0 0 
	//0 0 0 
	//0 0 0 
	
	//0 0 0 
	//0 0 0 
	//0 0 0 
	
	//0 4 0 
	//0 0 0 
	//0 0 0 
	
	//0 4 5 
	//0 0 0 
	//0 0 0 
	
	//0 4 5 
	//0 0 0 
	//0 0 0 
	
	//0 4 5 
	//2 0 0 
	//0 0 0 
	
	//0 4 5 
	//2 7 6 
	//0 0 0 
	
	//0 4 5 
	//2 7 6 
	//6 0 0 
    
    //매 iterate마다 Trial02처럼 if(m < 0 || n < 0) 과 if(m == 0 && n == 0)해줄 필요 없기 때문에, 속도가 빠름.
    
    //trial02는, coord(x,y)가 matrix에 중앙부에 있어서 왼쪽이랑 위 둘다 가야되는지, 아니면 왼쪽 끝에 붙어있어서 위에만 가야하는지, 아니면 위쪽 끝에 붙어있어서 왼쪽에만 가야하는지, 아니면
    
    //x,y중 하나의 값이 -1이라 가면 안되는지, 아니면 x,y가 0,0이라 값을 반환해야하는지 모든 경우의수를 매 iterate마다 판단해야 했기 때문에 성능이 구렸는데,
    
    //얘 같은 경우엔, 일단 원점 찍고, 윗칸 확정인 애들 순서대로 채운 다음, 그 다음줄에 애들 채울 때 grids[m][n] + gridSum(grids, m, n-1); 에서 
    
    //gridSum(grids, m, n-1); 시, trial02처럼 경우의 수가 많지 않고, 딱 하나기 때문에
    
    //if (mem[m][n] != 0) return mem[m][n]; 이걸로 결정됨.
    
    //그래서 성능이 좋음.
    
    //Runtime: 1 ms, faster than 98.72% of Java online submissions for Minimum Path Sum.
    //Memory Usage: 41.7 MB, less than 49.50% of Java online submissions for Minimum Path Sum.
    
    private int[][] mem;
    public int minPathSum(int[][] grids) {
        int m = grids.length, n = grids[0].length;
        mem = new int[m][n];
        return gridSum(grids, m-1, n-1);
    }
    
    private int gridSum(int[][] grids, int m, int n) {
        if (m == 0 && n == 0) return grids[m][n];
        if (mem[m][n] != 0) return mem[m][n];
        if (m == 0) {
            mem[m][n] = grids[m][n] + gridSum(grids, m, n-1);
            return mem[m][n];
        }
        if (n == 0){
            mem[m][n] = grids[m][n] + gridSum(grids, m-1, n);
            return mem[m][n];
        }
        mem[m][n] = grids[m][n] + Math.min(gridSum(grids, m-1, n), gridSum(grids, m, n-1));
        return mem[m][n];
    }
}
