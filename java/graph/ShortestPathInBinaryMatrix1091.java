package graph;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMatrix1091 {
	
	//<문제풀이1>
	
	//bfs

	//야레야레... 
	
	//시.시.하.구.만
	
	//다음엔 더 강해져서 돌아오라구
	
	//BOY~
	
	
	
	//이 bfs는 한칸 가면 이미 간 곳을 마크해줘야해. 안그러면 [a,b]에서 a->b가고 b->a가고 a->b가고 무한 반복하거든.
	
	//이미 간곳을 마크하는 방법은 두가지가 있어.
	
	//하나는 boolean[m][n]을 써서 한칸 이동할때마다 boolean[i][j] = true를 하는 방법이고,
	
	//다른 하나는 앞으로 갈곳(0)들을 Integer.MAX_VALUE를 박아놓은 다음,
	
	//if(matrix[next] <= matrix[prev]+1) continue를 해서 이미 갔었던 곳을 건너뛰는 방법이 있어.
	
	//2번째 방법을 선택한 이유는, boolean[m][n]은 메모리 소모가 크기도 하고,  
	
	//visited[m][n]방법을 써도 grid에 몇번에 step에 걸쳐 갔는지 표시해줘야 되서, 이 두개를 합친 첫번째 방법을 썼어.
	
	//첫번째 방법에서 이미 간곳을 건너 뛰는 부분은
	
	//if(matrix[next] <= matrix[prev]+1) continue; 야
	
	//한번도 안가본 0은 Integer.MAX_VALUE이기 때문에, matrix[next]가 matrix[prev]+1 보다 무조건 클 수 밖에 없고,
	
	//matrix[next]에 값을 넣을 때, Math.min(matrix[next], matrix[prev]+1); 를 해야 하잖아?
	
	//왜냐면 A,B길 둘다 목표지점에 도착하는데, A가 최단거리로 목표지점에 먼저 도착해도,
	
	//B가 삥 돌아서 나중에 목표지점에 도착해서 A를 덮어씌워 버리면 안되니까, Math.min()을 해줘야 한단 말이지.
	
	//Math.min()이랑 궁합이 잘 맞는 Integer.MAX_VALUE를 쓴거야 그래서.
	
	//Runtime: 12 ms, faster than 77.71% of Java online submissions for Shortest Path in Binary Matrix.
	//Memory Usage: 40.7 MB, less than 6.18% of Java online submissions for Shortest Path in Binary Matrix.
    
    public static int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if(grid[0][0] == 1 || grid[m-1][n-1] == 1) return -1;
        
        int[][] dir = new int[][]{{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1}};
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 0){
                    grid[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        grid[0][0] = 1;
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {0, 0});
        
        while(!q.isEmpty()){
            int size = q.size();
            
            while(size-- > 0){
                int[] coord = q.poll();
                int x = coord[0];
                int y = coord[1];
                
                for(int[] d : dir){
                    int dx = x+d[0];
                    int dy = y+d[1];
                    if(dx < 0 || dy < 0 || dx == grid.length || dy == grid[0].length || grid[dx][dy] <= grid[x][y]+1){
                        continue;
                    }
                    grid[dx][dy] = Math.min(grid[dx][dy], grid[x][y]+1);
                    q.add(new int[] {dx, dy});
                }
                
            }
        }
        
        return grid[m-1][n-1] == Integer.MAX_VALUE ? -1 : grid[m-1][n-1];
    }
	
	public static void main(String[] args) {
//		int[][] grid = new int[][] {{0, 0, 1},
//								    {1, 1, 0},
//								    {1, 1, 0}};
		int[][] grid = new int[][] {{0, 0, 0, 0, 1},
								    {1, 0, 0, 0, 0},
								    {0, 1, 0, 1, 0},
								    {0, 0, 0, 1, 1},
								    {0, 0, 0, 1, 0}};
		
		System.out.println(shortestPathBinaryMatrix(grid));
	}
}
