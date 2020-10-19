package graph;

public class MostStonesRemovedWithSameRowOrColumn947 {

	//<문제풀이1 by NorthernMystic>
	
	//union circle
	
	//헤깔렸던 부분: What is the largest possible number of moves we can make?
	
	//뇌에선 당연하게 'least possible moves'인걸로 받아들임
	
	//최적화 하려고 알고리즘 짜는데 왜 가장 비효율적인 방법을 찾으라는건진 모르겠지만, 여하튼.
	
	
	//전체 n개 - (아무애랑도 안 이어진 혼자 동떨어져있는애 + 다른애들이랑 이어진 그룹중에 하나) = 답
	
	//union circle돌리면,
	
	//아무애랑도 안 이어진 애는 connected[i] = i; 그대로. 손댄적 없기 때문.
	
	//다른애들일아 이엊니 그룹중 하나는, 해당 그룹의 pointer(?) 맨 마지막 빠따를 보면 됨.
	
	//새로운 애가 그룹에 들어올때마다 connected[i_] = j_; 이걸 하니까, 하나의 그룹당 한놈은 무조건 connecetd[j_] = j_가 되잖어.
	
	//이친구가 '다른애들이랑 이어진 그룹중의 하나', 다른말로 하면 해당 그룹의 포인터 가 되는겨
	
	//여기서 해당 그룹의 포인터라는 말은, 그 그룹의 어떤 element를 조회해도, union.find()를 돌리면 마지막에 내리는 종착역을 뜻함.
	
	//Runtime: 22 ms, faster than 56.98% of Java online submissions for Most Stones Removed with Same Row or Column.
	//Memory Usage: 39.2 MB, less than 11.23% of Java online submissions for Most Stones Removed with Same Row or Column.
	
    class UnionCircle{
        int[][] matrix;
        int[] connected;
        
        public UnionCircle(int[][] stones, int n){
            matrix = stones;
            connected = new int[n];
            for(int i = 0; i < n; i++){
                connected[i] = i;
            }
        }
        
        private int find(int n){
            while(connected[n] != n) n = connected[n];
            return n;
        }
        
        public void union(int i, int j){
            int i_ = find(i);
            int j_ = find(j);
            if(i_ == j_) return;
            connected[i_] = j_;
        }
        
        public int getLeftOvers(){
            int count = 0;
            for (int i = 0; i < connected.length; i++){
                if (connected[i] == i) count++;
            }
            return count;
        }
    }
    
    public int removeStones(int[][] stones) {
        int len = stones.length;
        UnionCircle uc = new UnionCircle(stones, len);
        for(int i = 0; i < len; i++){
            for(int j = i+1; j < len; j++){
                if(stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]){ //같은열, 같은 행일 경우,
                    uc.union(i, j);        
                }
            }
        }
        return len - uc.getLeftOvers(); 
    }
}
