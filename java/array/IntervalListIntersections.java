package mayChallenge;

import java.util.ArrayList;
import java.util.List;

public class IntervalListIntersections {
	
	
	//<문제풀이1 bychipbk10>
	
	//intersect 메소드로 a,b의 교차점을 구하되,
	
	//A = [[0,2],[5,10],[13,23],[24,25]], 
	//B = [[1,5],[8,12],[15,24],[25,26]]
	
	//에서, 첫빠따 [0,2]랑 [1,5]는 [1,2]로 잘 드감
	
	//다음빠따 선정할때 if (a[1] <= c[1]) i++; 이걸 쓰는데,
	
	//[1,2]에서 2보다 [0,2]의 2가 같거나 작으면, 이미 포함한다는 말이니까, 다음빠따로 넘어가야되서 i++;
	
	//마찬가지로 [1,2]를 넣었는데, B[0]의 [1,5]랑 비교시 B[0][1]이랑 [1,2]의 2랑 비교함.
	
	//근데 얘는 5네? 2보다 크네? 아직 커버 안쳐졌네?
	
	//그럼 j는 걍 냅둠
	
	//그럼 다음 intersect에 들어갈 파라미터는 [5,10]이랑 [1,5]가 되는겨.
	
	//[5,5]가 들어가면, 10이랑 5가 [5,5]의 5보다 작거나 같으니까(커버되니까), i++,j++해서 다음빠따로 넘어가는겨
	
	//Runtime: 2 ms
	//Memory Usage: 40.3 MB
	
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int m = A.length, n = B.length, i = 0, j = 0;
        List<int[]> res = new ArrayList<>();
        
        while (i < m && j < n) {
            int[] a = A[i], b = B[j], c = intersect(a, b);            
            if (c[1] >= c[0]) res.add(c);
            if (a[1] <= c[1]) i++;
            if (b[1] <= c[1]) j++;
        }
        
        return res.toArray(new int[res.size()][2]);
    }
    
    private int[] intersect(int[] a, int[] b) {
        return new int[] {Math.max(a[0], b[0]), Math.min(a[1], b[1])};
    }
    
}
