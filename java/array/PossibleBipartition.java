package mayChallenge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PossibleBipartition {
	
	/*
	//<Trial01>
	
	//N = 4, dislikes = [[1,2],[1,3],[2,4]]
	
	//[1,4],[2,3]
	
	//2,3 1,4
	//1   2
	
	//x,0,1,2,3,4
	//0   
	//1   x x,x o
	//2   x x o x
	//3   x   x
	//4		x   x
	
	//dislikes = [[1,2],[1,3],[2,3]]
	
	//x,0,1,2,3
	//0   
	//1   x x,x
	//2   x x x
	//3   x x x
	
	
	//dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
	
	//1,2,3,4,5
	
	//[1,3],[2,4]
	
	//x,0,1,2,3,4,5
	//0   
	//1   x x     x
	//2   x x x  
	//3     x x x
	//4       x x x
	//5   x     x x
	
	//dislikes = [[1,2],[2,3],[3,4],[4,5]]
	
	//[1,3,5],[2,4]
	
	//x,0,1,2,3,4,5
	//0   
	//1   x x     
	//2   x x x  
	//3     x x x
	//4       x x x
	//5         x x
	
	//쓰바
	
    
    //<Trial02>
	
	//쓰ㅡㅡㅡ바
    
    public boolean possibleBipartition(int N, int[][] dislikes) {
        int[][] check = new int[N+1][N+1];
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        
        for(int i = 1; i < N+1; i++){
            a.add(i);
        }

        for(int[] g : dislikes){
            if(a.contains(g[0]) && a.contains(g[1])){
                int transfer = a.remove(a.indexOf(g[1]));
                b.add(transfer);
                check[transfer][g[0]]++;
            }
        }
        
        for(int i = b.size()-1; i >= 0; i--){
            for(int j = 1; j < N+1; j++){
                if(check[b.get(i)][j] > 0 && !a.contains(j)){
                    a.add(b.remove(b.indexOf(b.get(i))));
                }
            }
        }
        
        for(int[] g : dislikes){
            if(b.contains(g[0]) && b.contains(g[1])){
                return false;
            }
        }
        return true;
    }
    */
	
    //<문제풀이1 by trustno1>
    
    //Runtime: 12 ms
	//Memory Usage: 47.7 MB
    
    public boolean possibleBipartition(int N, int[][] dislikes) {
        int[] color = new int[N + 1]; //바구니. a에 담으면 1, b에담으면 2로 표시. 아직 안담았으면 0으로 표시.
        List<List<Integer>> adj = new ArrayList<>(N + 1);
        for(int i = 0; i <= N; i++) adj.add(new ArrayList<Integer>()); //dislikes[i][0]이 싫어하는 사람 dislikes[i][1]를 담고, 반대도 담음
        for(int[] d : dislikes) {
            adj.get(d[0]).add(d[1]);
            adj.get(d[1]).add(d[0]);
        }
        
        for(int i = 1; i <= N; i++) { //dislikes를 피해서 color에 담는다.(1에 담든, 2에 담든.)
            if(color[i] == 0) { //아직 안담겨져 있으면
                color[i] = 1; //일단 1에 담아놔.
                Queue<Integer> q = new LinkedList<>(); //
                q.add(i);
                while(!q.isEmpty()) {
                    int cur = q.poll();
                    for(int hate : adj.get(cur)) { //i가 싫어하는 애들이 hate인데,
                        if(color[hate] == 0) { //hate가 아직 안담겨있다면,
                            color[hate] = color[cur] == 1 ? 2 : 1; //i가 1에 담겨져 있으면, nb는 2에 담고, 아니면 i의 반대인 2에 담아.
                            q.add(hate); //그리고 hate도 싫어하는애랑 같이 담겨있을 수 있으니, 다시 q에 담아 확인한다.
                        } else {
                            if(color[hate] == color[cur]) return false; //만약 i가 싫어하는 nb가 이미 한번 거쳐가서 1이나 2든 담겨져 있는데, 둘이 같이 담겨져 있으면, return false
                        }
                    }
                }
            }
        }
        return true;
    }
    
}
