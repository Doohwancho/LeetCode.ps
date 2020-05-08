package mayChallenge;

public class CheckIfStraightLine {

	/*
	//<Trial01>
	
	//점이 [[-3,-2],[-1,-2],[2,-2],[-2,-2],[0,-2]] 요딴식으로 섞여나오면 에러남
	
	//근데 그렇다고 x값을 두고 오름차순 정렬해서 돌리자니, 시간도 오래걸리고, x는 고정인데 y값만 바뀌어있으면
	
	//또 y값두고 오름차순 정렬해서 다시 돌려야 하니까, 이 방법은 안됌.
	
    public boolean checkStraightLine(int[][] coordinates) {
        int xDiff = coordinates[1][0] - coordinates[0][0];
        int yDiff = coordinates[1][1] - coordinates[0][1];
        
        for(int i = 1; i < coordinates.length; i++){
            if(coordinates[i][0]-coordinates[i-1][0] != xDiff ||
               coordinates[i][1]-coordinates[i-1][1] != yDiff){
                return false;
            }
        }
        return true;
    }
    
    //<Trial02>
    
    //전거랑 후꺼의 x,y의 차들이 그들 중에 최솟값의 차이로 나누어 지나?
    
    //응 아니야~
    
    //[[-3,-2],[-1,-2],[2,-2],[-2,-2],[0,-2]]
    public boolean checkStraightLine(int[][] coordinates) {
        Set<Integer> xList = new HashSet<>();
        Set<Integer> yList = new HashSet<>();
        int xMin = coordinates[1][0] - coordinates[0][0];
        int yMin = coordinates[1][1] - coordinates[0][1];
        xList.add(xMin);
        yList.add(yMin);
        
        for(int i = 1; i < coordinates.length; i++){
            int xDiff = coordinates[i][0]-coordinates[i-1][0];
            int yDiff = coordinates[i][1]-coordinates[i-1][1];
            xMin = Math.min(xMin, xDiff);
            yMin = Math.min(yMin, yDiff);
            xList.add(xDiff);
            yList.add(yDiff);
            for(int p : xList){
                if(p % xMin != 0){
                    return false;
                }
            }
            for(int q : yList){
                if(q % yMin != 0){
                    return false;
                }
            }
        }
        return true;
    }
    */
	
	//<문제풀이1>
	
	//기울기를 써라
	
	//생각좀 하자 친구야
	
	//int 나누기 int했을 때, 3/5하면 0.6이 아니라 0이 나와서 검색해 봤더니,
	
	//소숫점까지 나오게 하려면 분자랑 분모중 하나가 float이여야 하고, 받는 숫자의 타입이 double이면 된다네
	
	//66 / 66 test cases passed.
	//Status: Accepted
	//Runtime: 0 ms
	//Memory Usage: 39.4 MB
    public boolean checkStraightLine(int[][] coordinates) {
    	double slant = (coordinates[1][1] - coordinates[0][1]) / (float)(coordinates[1][0] - coordinates[0][0]);
        
        for(int i = 1; i < coordinates.length; i++){
            float x_ = (float)(coordinates[i][0]-coordinates[i-1][0]);
            int y_ = coordinates[i][1]-coordinates[i-1][1];
            if(y_/x_ != slant) return false;
        }
        return true;
    }
    
    
    
    //<문제풀이2 by 
    
    //기울기 공식
    
    //(y - y1) / (x - x1) = (y1 - y0) / (x1 - x0) 
    
    //이게
    
    //(x1 - x0) * (y - y1) = (x - x1) * (y1 - y0)
    
    //이거랑 같고 이게
    
    //dx * (y - y1) = dy * (x - x1), where dx = x1 - x0 and dy = y1 - y0
    
    //이거랑 같으니까, 나눗셈할때 타입을 double로 할지 float할지 고민하지 말고 걍 곱셈만 쓰면 됌
    
    //이 방식이 문제풀이1보다 더 나은게, division by zero에러를 피할 수 있음

	//66 / 66 test cases passed.
	//Status: Accepted
	//Runtime: 0 ms
	//Memory Usage: 38.9 MB
    
    public boolean checkStraightLine(int[][] coordinates) {
        int x0 = coordinates[0][0], y0 = coordinates[0][1], 
            x1 = coordinates[1][0], y1 = coordinates[1][1];
        int dx = x1 - x0, dy = y1 - y0;
        for (int[] co : coordinates) {
            int x = co[0], y = co[1];
            if (dx * (y - y1) != dy * (x - x1))
                return false;
        }
        return true;
    }
}
