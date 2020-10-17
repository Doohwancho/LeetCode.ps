package graph;

public class RedundantConnection684 {
	
	//<문제풀이1>
	
	//union circle
	
	//undirected-graph에서 일단 n개만큼 separate한 점을 connected에 놓고,
	
	//주어진 int[][] edges를 iterate하면서 하나하나씩 이음.
	
	//이을 때, edge[0]의 끝까지, edge[1]의 끝까지 간 다음, 둘의 end point비교
	
	//만약 둘이 안이어져있으면, endpoint가 다름. 그러면 둘이 이어줘.
	
	//만약 둘이 이미 이어져 있었따면 endpoint가 같겠지? 그러면 undirected graph에 i번째 edge필요 없으니 edge반환해줘
	
	//문제에서 If there are multiple answers, return the answer that occurs last in the given 2D-array. 라고 했고
	
	//input이 이쁘게 나오는거 같으니, uc.connect()돌리기전에 앞에꺼 순서대로 정렬할 필요도 없음 개꾸르
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Redundant Connection.
	//Memory Usage: 39.2 MB, less than 5.17% of Java online submissions for Redundant Connection.
	
    class UnionCircle {
        int[] connected;

        UnionCircle(int n){
            connected = new int[n];

            for(int i = 0; i < n; i++){
                connected[i] = i;
            }
        }

        private int find(int n){
            while(connected[n] != n) n = connected[n];
            return n;
        }

        public int[] connect(int[] i){
            int i_ = find(i[0]-1);
            int j_ = find(i[1]-1);
            if(i_ == j_){
                return i;    
            } 
            connected[i_] = j_;
            return null;
        }
    }
    
    public int[] findRedundantConnection(int[][] edges) {
        
        int n = edges.length;
        UnionCircle uc = new UnionCircle(n);

        for(int[] edge : edges){
            int[] tmp = uc.connect(edge);
            if(tmp != null){
                return tmp;
            }
        }
        return null;
    }
}
