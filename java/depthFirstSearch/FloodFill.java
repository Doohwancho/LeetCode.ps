package mayChallenge;

public class FloodFill {
	
	//<문제풀이1>
	
	//dfs
	
	//맨 처음 지정된 곳 newColor로 마크 해주고,
	
	//동서남북으로 한칸씩 가면서 newColor로 마크해줌
	
	//org변수를 뺄까 싶었는데 마크된곳(1이든 다른 어떤 양수든)이랑 0이랑 구분해줄 필요가 있겠다 싶어서 넣음.
	
	//Runtime: 2 ms
	//Memory Usage: 45.3 MB
	
    private int[][] dfs(int[][] image, int sr, int sc, int newColor, int org){
        image[sr][sc] = newColor;
        if(sr > 0 && image[sr-1][sc] == org) dfs(image, sr-1, sc, newColor, org);
        if(sr < image.length-1 && image[sr+1][sc] == org) dfs(image, sr+1, sc, newColor, org);
        if(sc > 0 && image[sr][sc-1] == org) dfs(image, sr, sc-1, newColor, org);
        if(sc < image[0].length-1 && image[sr][sc+1] == org) dfs(image, sr, sc+1, newColor, org);
        return image;
    }
    
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
         if(image[sr][sc] == newColor) return image;
         return dfs(image, sr, sc, newColor, image[sr][sc]);
    }
    
    //<문제풀이2 by shawngao>
    
    //문제풀이1과 같은방식인데, 이게 보기에 더 깔끔함. 맨앞 if문으로 다 빼놔서.
    
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) return image;
        fill(image, sr, sc, image[sr][sc], newColor);
        return image;
    }
    
    private void fill(int[][] image, int sr, int sc, int color, int newColor) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != color) return;
        image[sr][sc] = newColor;
        fill(image, sr + 1, sc, color, newColor);
        fill(image, sr - 1, sc, color, newColor);
        fill(image, sr, sc + 1, color, newColor);
        fill(image, sr, sc - 1, color, newColor);
    }
    
    
    //<문제풀이3 by fishercoder>
    
    //bfs
    
    //이것도 한칸씩 이동해서 newColor로 마킹한다는 원리는 같은데
    
    //int[] directions = new int[]{0, 1, 0, -1, 0};
    
    //요롷게 해놓고
    
	//for (int i = 0; i < directions.length - 1; i++) {
	//    int nextR = curr[0] + directions[i];
	//    int nextC = curr[1] + directions[i + 1];
    //}
    
    //이렇게 (0,1),(1,0),(0,-1),(-1,0) 이렇게 도는건 좀 참신하네

	//Runtime: 2 ms
	//Memory Usage: 40.2 MB
    
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int[] directions = new int[]{0, 1, 0, -1, 0};
        int m = image.length;
        int n = image[0].length;
        int originalValue = image[sr][sc];
        image[sr][sc] = newColor;

        boolean[][] visited = new boolean[m][n];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            visited[curr[0]][curr[1]] = true;
            for (int i = 0; i < directions.length - 1; i++) {
                int nextR = curr[0] + directions[i];
                int nextC = curr[1] + directions[i + 1];
                if (nextR < 0 || nextC < 0 || nextR >= m || nextC >= n || image[nextR][nextC] != originalValue || visited[nextR][nextC]) {
                    continue;
                }
                image[nextR][nextC] = newColor;
                queue.offer(new int[]{nextR, nextC});
            }
        }
        return image;
    }
}
