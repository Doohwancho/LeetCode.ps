package dynamicProgramming;

import java.util.Arrays;

public class Triangle {
	
	//Q. 아래랑 오른쪽만 갈 수 있음. (0,0)에서 (4,4)까지 갔을 때, 무수한 경로 중, 최대합을 반환하라.
	
	static int[][] triangle = {
		{6,0,0,0,0},
		{1,2,0,0,0},
		{3,7,4,0,0},
		{9,4,1,7,0},
		{2,7,5,9,4}
	};
	static int cnt = 0;
	
	
	//1. Backtracking
	
	//ans:48
	//loops:363
	
//	private static int search(int i, int j) {
//		if(i == triangle.length || j == triangle[0].length) return 0;
//		if(i == triangle.length-1 && j == triangle[0].length-1) return triangle[i][j];
//		return triangle[i][j] + Math.max(search(i+1,j), search(i, j+1));
//	}
	
	
	//2. Backtracking + Memoization
	
	//ans:48
	//loops:49
	
//	static int[][] visited = new int[5][5];
//	
//	private static int search(int i, int j) {
//		if(i == triangle.length || j == triangle[0].length) return 0;
//		if(visited[i][j] != 0) return visited[i][j];
//		if(i == triangle.length-1 && j == triangle[0].length-1) return triangle[i][j];
//		visited[i][j] = triangle[i][j] + Math.max(search(i+1,j), search(i, j+1));
//		return visited[i][j];
//	}
	
	
	
	//3. Backtracking + Memoization + Optimization
	
	//ans:48
	//loops:29
	
//	static int[][] visited = new int[5][5];
//	
//	private static int search(int i, int j) {
//		if(i == triangle.length || j > i) return 0;
//		if(visited[i][j] != 0) return visited[i][j];
//		if(i == triangle.length-1 && j == triangle[0].length-1) return triangle[i][j];
//		visited[i][j] = triangle[i][j] + Math.max(search(i+1,j), search(i, j+1));
//		return visited[i][j];
//	}
	
	
	
	
	
	//Q. 아래랑 오른쪽아래만 갈 수 있으면?
	
	//1. 위에서 차근차근 내려오다가, 도착지 직으면 다시 재귀로 처음으로 돌아와 반환하는 방법
	
	//전제조건: mem[0][0] = triangle[0][0];
	
	//ans:28
	//loop:31
	
	
//	static int[][] mem = new int[5][5];
//	
//	private static int search(int y, int x) {
//		if(y == triangle.length-1) return mem[y][x];
//		mem[y+1][x] = Math.max(mem[y+1][x], mem[y][x] + triangle[y+1][x]);
//		mem[y+1][x+1] = Math.max(mem[y+1][x+1], mem[y][x] + triangle[y+1][x+1]);
//		return Math.max(search(y+1, x), search(y+1, x+1));
//	}
	
	
	//2. Memoization + Optimization
	
	//ans:28
	//loop:21
	
	//왼쪽 아래부터 삼각형으로 서서히 퍼져나가는 식
	
	//28 0 0 0 0 
	//20 22 0 0 0 
	//19 18 20 0 0 
	//16 11 10 16 0 
	//0 0 0 0 0
	
	//여기서 보면, 겹치는 구간이, 아래부터 16,11,10,19,18,20 총 6군데.
	
	//그래서 최소로 돌아야 하는 곳 15(1+2+3+4+5)에 겹치는 부분 6군데 해서 총 21군데.
	
	//1번과는 다르게, Math.max()로 매번 겹치는 부분 비교해야 하는게 빠짐.
	
	static int[][] mem = new int[5][5];
	
	private static int search(int y, int x) {
		if(y == triangle.length-1) return triangle[y][x];
		if(mem[y][x] != 0) return mem[y][x];
		return mem[y][x] = Math.max(search(y+1, x), search(y+1,x+1)) + triangle[y][x];
	}
	
	
	public static void main(String[] args) {
		//메모이제이션은 원래 -1로 초기화 해줘야 한다.
//		for(int[] row : visited) {
//			Arrays.fill(row, -1);
//		}
//		
		System.out.println(search(0, 0)); 
		System.out.println(cnt);
	}
}
