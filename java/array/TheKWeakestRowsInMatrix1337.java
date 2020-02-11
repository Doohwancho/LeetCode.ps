package array;

public class TheKWeakestRowsInMatrix1337 {
	
	//<문제풀이1>
	
	//조아따
	
	//Runtime: 1 ms, faster than 99.74% of Java online submissions for The K Weakest Rows in a Matrix.
	//Memory Usage: 41.7 MB, less than 100.00% of Java online submissions for The K Weakest Rows in a Matrix.
	public int[] kWeakestRows(int[][] mat, int k) {
        int[] rst = new int[k];
        int[] container = new int[mat.length]; //이미 rst에 들어간 row의 index를 체크하기 위한 리스트
        int idx = 0;
        int originalK = k;
        
        //1(soldier)가 무조건 0보다 앞에 위치 하니까, for문 돌때 각 row에 가장 첫번째 수만 체크, 각 row에 두번째 수 체크, 세번째 수 체크.. 순으로 감.
        for(int i = 0; i < mat[0].length; i++){
            for(int j = 0; j < mat.length; j++){
                if(container[j] == 0 && mat[j][i] == 0){ //만약 0(civilian)이 나타났고, 이미 rst에 넣지도 않았다면, 
                    rst[idx++] = j; //rst에 값을 넣고
                    container[j]++; //rst에 넣은 row의 인덱스를 체크해줌
                    k--;
                }
                if(k == 0){     //만약 k를 모두 소진했으면 rst를 바로 반환해줌.
                    return rst;
                }
            }
        }
        
        if(k == originalK){ //mat을 2중 for문으로 다 돌았는데도, 0(civilian)이 한명도 없었기 때문에 k값에 변화가 없었다면, 빈 리스트인 k사이즈의 [0]을 반환.
            return rst; 
        }

        for(int q = 0; q < container.length && k > 0; q++){ //만약 k가 원래 4이고, 3개의 row만 0(civilian)이 있어서 1이 남은 상황이면, 비록 1(soldier)밖에 없지만 해당 row를 마저 더해줌
            if(container[q] == 0){
                rst[idx++] = q;
                k--;
            }
        }
        
        return rst;
    }
}
