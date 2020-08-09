package augustChallenge;

public class RottingOranges {
	
	//<문제풀이1 by votrubac>
	
	//bfs
	
	//첫트엔 1->3
	//2트엔 1->4 ...
	//를 d로 표현한게 똑똑했음.
	
	//그냥 무-난
	
	//근데 죄다 bfs네
	
	//정수론 쓴거 없나 가능할거같은데
	
	//Runtime: 2 ms
	//Memory Usage: 39.4 MB
	
	private int rot(int[][] g, int i, int j, int d) {
	  if (i < 0 || j < 0 || i >= g.length || j >= g[i].length || g[i][j] != 1) return 0;
	  g[i][j] = d + 3;
	  return 1;
	}
	public int orangesRotting(int[][] g) {
	  int fresh = 0, d = 0;
	  for (int i = 0; i < g.length; ++i)
	    for (int j = 0; j < g[i].length; ++j)
	      if (g[i][j] == 1) ++fresh;
	  for (int old_fresh = fresh; fresh > 0; ++d, old_fresh = fresh) {
	    for (int i = 0; i < g.length; ++i)
	      for (int j = 0; j < g[i].length; ++j)
	        if (g[i][j] == d + 2)
	          fresh -= rot(g, i + 1, j, d) + rot(g, i - 1, j, d) + rot(g, i, j + 1, d) + rot(g, i, j - 1, d);
	    if (fresh == old_fresh) return -1;
	  }
	  return d;
	}
	
	//<문제풀이2 by ambuj_kumar>
	
	//bfs + queue + hash
	
	//정수론 찾고있었는데 뜻밖에 hash가 나와서 놀랐네 ㅋㅋ
	
	//쥰내 신기하네? x,y좌표 해시방법ㅋㅋ
	
	//Runtime: 2 ms
	//Memory Usage: 38.7 MB
	

    public int orangesRotting(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int R = grid.length;
        int C = grid[0].length;
        int x, y, hash;
        int clean = 0, rotted = 0, time = 0, size = 0, added = 0;
        Queue<Integer> q = new LinkedList<>();
        
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(grid[i][j] == 1){
                    clean++;
                } else if(grid[i][j] == 2){
                    q.offer(hash(i,j,C));
                }
            }
        }
        
        while(clean != 0 && !q.isEmpty()){
            size = q.size();
            added = 0;
            
            for(int i = 0; i < size; i++){
                hash = q.poll();
                x = hash/C;
                y = hash - (x * C);

                if(x > 0 && grid[x-1][y] == 1){
                    clean--;
                    added++;
                    q.offer(hash(x-1,y,C));
                    grid[x-1][y] = 2;
                }
                if(x < R-1 && grid[x+1][y] == 1){
                    clean--;
                    added++;
                    q.offer(hash(x+1,y,C));
                    grid[x+1][y] = 2;
                }
                if(y > 0 && grid[x][y-1] == 1){
                    clean--;
                    added++;
                    q.offer(hash(x,y-1,C));
                    grid[x][y-1] = 2;
                }
                if(y < C-1 && grid[x][y+1] == 1){
                    clean--;
                    added++;
                    q.offer(hash(x,y+1,C));
                    grid[x][y+1] = 2;
                }
            }
            if(added > 0){
                time++;
            }
        }
        if(clean == 0){
            return time;
        } else {
            return -1;
        }
    }
    
    private int hash(int x, int y, int C){
        return x * C + y;
    }
}
