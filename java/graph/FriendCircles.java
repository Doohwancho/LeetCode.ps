package graph;

import java.util.ArrayList;
import java.util.List;

public class FriendCircles {
	
	//<Trial01>
	
	//일단 M에 몇명의 사람이 있는지 리스트에 담아.
	
	//무한으로 재귀되는 circle을 찾아야 되잖아?
	
	//그러려면 처음 시작점이 어딘지 파악한 다음(orig)
	
	//계속 돌면서 리스트에 원소 하나씩 추가하다가
	
	//처음 시작점(orig)에 돌아왔으면, 리스트 안에 추가된 원소들은 circle이라는 말이니까, 
	
	//처음에 담은 총 사람 리스트에서 circle안 사람 빼고 rst+1하는 식으로 풀었음.
	
	
	//input:  [[1,0,0,1],[0,1,1,0],[0,1,1,1],[1,0,1,1]]
	
	//output: 2
	
	//expected: 1
	
	public int findCircleNum(int[][] M) {
        List<Integer> list = new ArrayList<>();
        int rst = 0;
        
        //add candidates
        for(int i = 0; i < M.length; i++){
            list.add(i);
        }
        
        while(!list.isEmpty()){
            int orig = list.remove(0);
            
            List<Integer> tmp = new ArrayList<>();
            tmp.add(orig);
            
            int next = orig;
            do {
                for(int j = 0; j < M[0].length; j++){
                    if(j != next && M[next][j] == 1){
                        next = j;
                        tmp.add(next);
                        break;
                    }
                }
                if(next == orig){
                    rst++;
                    if(tmp.size() > 1){
                        list.removeAll(tmp);
                    }
                    break;
                }
            } while(orig != next);
        }
        return rst;
    }
	
	
	//<Trial02 - Memory Limit Exceeded>
	
	//input: [[1,1,1,1,1],[1,1,1,1,1],[1,1,1,1,1],[1,1,1,1,1],[1,1,1,1,1]]
	
	//방향은 맞았는데 성능이 매우 구리네
	
    public int findCircleNum(int[][] M) {
        List<Integer> list = new ArrayList<>();
        int rst = 0;
        
        //add candidates
        for(int i = 0; i < M.length; i++){
            list.add(i);
        }
        
        while(!list.isEmpty()){
            int orig = list.remove(0);
            
            List<Integer> tmp = new ArrayList<>();
            tmp.add(orig);
            
            int next = orig;
            do {
                int prev = next;
                
                for(int j = 0; j < M[0].length; j++){
                    if(j != next && M[next][j] == 1){
                        M[j][next] = 0; //왔던데 지워주기**
                        next = j;
                        tmp.add(next);
                        break;
                    }
                }
                if(next == orig || prev == next){ //제자리로 왔거나(next == orig), 본인 외에 친구가 아무도 없거나 ㅜㅜ(next == orig), 막다른 길에 막혔거나(prev == next)
                    rst++;
                    if(tmp.size() > 1){
                        list.removeAll(tmp);
                    }
                    break;
                }
            } while(orig != next);
        }
        return rst;
    }
    
    
    //<문제풀이1 by fang2018>

    //union circles
    
    //https://www.youtube.com/watch?v=n_t0a_8H8VY&ab_channel=TusharRoy-CodingMadeSimple
    
    //처음엔 각자 스스로 친구니까, 3명이 있다고 하면 총 3명의 친구가 있다고 하자(union.count = 3)
    
    //2중 for문 돌면서 자기 스스로가 이어졌으면 return(skip)하고, 다른 애랑 이어졌으면,
    
    //i = i 에서 i = j로 바꾼다. 
    
	//[[1,1,0],
	// [1,1,1],
	// [0,1,1]]
    
    //을 돌리면 (0,1)에서 path[]가 [0,1,2]였던게 [1,1,2]가 됨. 이어졌다는 뜻. 그리고 0하고 1이 이어졌으니까,
    
    //다 따로 생각했던 3명에서 이어진 한쌍 + 2번째 애 해서 총 2가 된다.
    
    //마찬가지로 (1,2)에서 1번과 2번이 이어져서 path는 [1,2,2]가 되고, 한번 더 이어졌으니 기존 count-1해서 1이 된다.
    
    
    
    //전체적 프로세스 : 처음엔 다 혼자 친구라고 생각. 3명이면 count = 3.
    
    //한쌍씩 이어질때마다 count-1씩 해줌.
    
    
    //Runtime: 1 ms, faster than 75.85% of Java online submissions for Friend Circles.
    //Memory Usage: 39.7 MB, less than 14.65% of Java online submissions for Friend Circles.
    
    public int findCircleNum(int[][] M) {
        int n = M.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (M[i][j] == 1) {
                    uf.union(i, j);
                } 
            }
        }
        return uf.count();
    }
    class UnionFind {
        int[] path; 
        int count;
        public UnionFind(int n) {
            path = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                path[i] = i;
            }
        }
        public int find(int i) {
            while (i != path[i]) i = path[i];
            return i;
        }
        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI == rootJ) return;
            path[rootI] = rootJ; 
            count--;
        }
        public int count() {
            return count;
        }
    }
	
	
	
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		
		List<Integer> list2 = new ArrayList<>();
		list2.add(1);
		list2.add(2);
		
		
//		System.out.println(list.remove(0));
		System.out.println(list);
		
		list.removeAll(list2);
		
		System.out.println(list);
	}
}
