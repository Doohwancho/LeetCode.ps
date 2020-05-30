package mayChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class KClosestPointsToOrigin {
	
	
	//<문제풀이1>
	
	//treemap은 넣을때 key를 자동으로 오름차순 정렬해준다는걸 이용한 방법
	
	//Runtime: 43 ms
	//Memory Usage: 49 MB
	
    public static int[][] kClosest(int[][] points, int K) {
        int[][] rst = new int[K][2];
		TreeMap<Integer, List<int[]>> tm = new TreeMap<>();
		
        for(int[] p : points){
        	int key = p[0]*p[0] + p[1]*p[1];
        	if(tm.containsKey(key)) {
        		List<int[]> tmp = tm.get(key);
        		tmp.add(p);
        		tm.put(key, tmp);
        	} else {
        		List<int[]> tmp = new ArrayList<>();
        		tmp.add(p);
        		tm.put(p[0]*p[0] + p[1]*p[1], tmp);
        	}
        }
        
        int i = 0;
        while(i < K) {
        	List<int[]> tmp = tm.pollFirstEntry().getValue();
        	int j = 0;
        	while(i < K && j < tmp.size()) {
        		rst[i] = tmp.get(j);
        		i++;
        		j++;
        	}
        }
        
        return rst;
        
    }
    
    
	
    //<문제풀이2 by lee215>
    
    //Arrays, Comparator의 내부 메서드랑 람다를 잘 활용할 줄 알면, 같은것도 이렇게 짧게 단축이 가능하네.
    
    //Runtime: 39 ms
    //Memory Usage: 47.8 MB
    
    public static int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, Comparator.comparing(p -> p[0] * p[0] + p[1] * p[1]));
        return Arrays.copyOfRange(points, 0, K);
    }
    
	
	//<문제풀이3 by asurana28>
	
	//priority queue사용. 
	
	//넣을때 오름차순으로 넣고, 사이즈가 K이상일 필요는 없으니, 사이즈가 K를 초과할때마다 가장 큰거 하나씩 빼는 방법.
	
	//문제풀이2처럼 마지막에 새로운 array안만들어도 되서 빠른덧
	
	//좀 신기한점은 .toArray()시, 들어가는 인가자 이상하다. new int[0][0]을 넣네? 왜?
	
	//1. List를 toArray 메서드에 파라메터로 넘어가는 배열 객체의 size만큼의 배열로 전환한다.
	//2. 단, 해당 List size가 인자로 넘어가는 배열 객체의 size보다 클때, 해당 List의 size로 배열이 만들어진다.
	//3. 반대로 해당 List size가 인자로 넘어가는 배열객체의 size보다 작을때는, 인자로 넘어가는 배열객체의 size로 배열이 만들어진다.
	
	//int[][] points = {{3,3},{5,-1},{-2,4}}; 이걸 넣고 K가 2면, pq의 사이즈가 2겠지?
	
	//그럼 인자로 넣은 int[0][0]의 사이즈보다 더 크니까, 이 경우는 원래 pq의 사이즈를 씀.
	
	//근데 만약에 인풋이 [[1,3],[-2,2]], 1인데, return pq.toArray(new int[2][]); 을 했다?
	
	//그럼 원래는 [[-2,2]] 만 나와야 되는데, int[2][]의 인자 2가 [[-2,2]]의 사이즈 int[1][]의 1보다 더 크니까
	
	//[[-2,2],null]을 반환해 버림. 그래서 에러남.
	
	//.toArray()의 인자 new int[n][]은, priority queue -> list변환시 사이즈를 결정해주는 요소다.
	
	//Runtime: 18 ms
	//Memory Usage: 47.9 MB
    public static int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> (b[0]*b[0]+b[1]*b[1]) - (a[0]*a[0]+a[1]*a[1]));

        for(int[] po : points) {
        	pq.add(po);
        	if(pq.size()>K) pq.poll();
        }
        return pq.toArray(new int[0][0]);
    }
	
	public static void main(String[] args) {
		int[][] points = {{3,3},{5,-1},{-2,4}};
		int K = 2;
		
//		int[][] points = {{0,1},{1,0}};
//		int K = 2;
		
		System.out.println(kClosest(points, K));
	}
}
