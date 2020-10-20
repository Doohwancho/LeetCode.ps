package graph;

public class NumberOfOperationsToMakeNetworkConnected1319 {

	
	//<문제풀이1>
	
	
	//union circle
	
	//union circle에 uc.union에서, i_==j_ 둘이 같다면 이미 이어진 애라는 말이니까, 잉여 커넥션+1
	
	//un.union으로 애들 다 잇고, uc.standAlone()으로 혼자인 애들 숫자 뽑음.
	
	//이때 주의할게 그룹 안에 맨 마지막에도 스스로를 가리키고 있기 때문에, 
	
	//만약 그룹A, 그룹B, 혼자인애 C 이렇게 있으면 uc.standAlone()실행시 3이 나옴. 3개 다른 그룹이니까.
	
	//얘네들을 최소갯수로 이으려면 2번만 이으면 되니까, loner-1을 리턴하는 것.
	
	//Runtime: 25 ms, faster than 31.90% of Java online submissions for Number of Operations to Make Network Connected.
	//Memory Usage: 53.7 MB, less than 5.10% of Java online submissions for Number of Operations to Make Network Connected.
	
	class UnionCircle{
        int[] connected;
        int leftOver = 0;
        
        UnionCircle(int len){
            connected = new int[len];
            
            for(int i = 0; i < len; i++){
                connected[i] = i;
            }
        }
        
        private int find(int x){
            while(connected[x] != x) x = connected[x];
            return x;
        }
        
        public void union(int i, int j){
            int i_ = find(i);
            int j_ = find(j);
            if(i_ == j_){
                leftOver++;
                return;
            }
            connected[i_] = j_;
        }
        
        public int standAlone(){
            int cnt = 0;
            for(int i = 0; i < connected.length; i++){
                if(i == connected[i]) cnt++;
            }
            return cnt;
        }
        
        public int getLeftOver(){
            return this.leftOver;
        }
    }
    
    public int makeConnected(int n, int[][] connections) {
        UnionCircle uc = new UnionCircle(n);
        for(int[] connection : connections){
            uc.union(connection[0], connection[1]);    
        }
        int loner = uc.standAlone();
        int lefty = uc.getLeftOver();
        
        return lefty >= (loner-1) ? loner-1 : -1;
    }
}
