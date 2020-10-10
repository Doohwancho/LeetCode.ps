package october;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumNumberOfArrowsToBurstBalloons {

	//<Trial01>
	
	//[[1,2],[3,4],[5,6],[7,8]]
	
	//얘네들이 걸쳐있는게 아니라 따로있는 애들이니까 1이 아니라 4가 나와야 하지 참
	
	//날먹안되네 ㅎㅎ.....ㅈㅅ......ㅋㅋ!!!
	
    public int findMinArrowShots(int[][] points) {
        int[] c = new int[10001];
        
        for(int[] point : points){
            for(int i = point[0]; i <= point[1]; i++){
                c[i]++;
            }
        }
        
        int rst = 0;
        boolean flag = false;
        for(int i = 0; i < 10001; ){
            while(i < 10001 && c[i] == 0){ i++; flag = false;}
            while(i < 10001 && c[i] > 0) { i++; flag = true; }
            if(flag) { rst++; }
        }
        return rst;
    }
    
    
    
    //<Trial02>
    
    //priority queue로 point[0]인 애들 기준 오름차순 정렬해 놓고,
    
    //for문으로 pq를 하나하나 돌면서
    
    //뒤따라 오는 애가 앞에 애랑 안겹치면, +1씩 해주는 방법
    
    //먹힐거라고 생각했는데 
    
    //[[10,16],[2,8],[1,6],[7,12]] 여서 막혔거든
    
    //왜 막혔냐면, pq에 넣으면 
    
    //[1,6],[2,8],[7,12],[10,16]
    
    //이래 정렬되잖아? point[0]기주누 오름차순으로
    
    //근데 얘네는 다 애매~하게 겹친 애들잉거든?
    
    //6에 한방, 10에 한방 총 2방이 필요한데, 아래 함수 돌리면, 일단 다 겹쳐서 이어져 있긴 하니까, 하나의 큰 덩어리로봐서 1을 반환함.
    
    //그래서 망함 아이고
    
    
    public int findMinArrowShots(int[][] points) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        for(int[] point : points){
            pq.offer(point);
        }
        
        int rst = 1;
        int[] prev = pq.poll();
        
        while(pq.size() > 0){
            int[] curr = pq.poll();
            if(prev[1] < curr[0]){
                rst++;
            } 
            prev = curr;
        }
        
        return rst;
    }
    
    
    //<Trial03>
    
    //오씨 거의 다온거같아
    
    //[[10,16],[2,8],[1,6],[7,12]] 얘를 pq에 넣으면
    
    //[1,6],[2,8],[7,12],[10,16] 이렇게 정렬되잖아?
    
    //prev = [1,6], 그 다음빠따 curr = [2,8] 이라고 생각하자
    
    //얘네 둘을 겹치지? 겹치는지 여부는 prev[1] < curr[0]로 알 수 있음. 2부터 6까지.
    
    //겹치면, 겹치는 범위중에 가장 오른쪽 애를 살려야 할거아냐?
    
    //2~6에서 가장 오른쪽 애인 6을 살려야, 그다음 [7,12]랑 또 겹치는지 봐서,
    
    //겹치면 rst+1안하고 걍 넘어가고, 안겹치면 새로운 화살이 필요하단 말이니까 rst+1해주면 되잖아.
    
    //오씨 난 천잰가?
    
    //[[-2147483646,-2147483645],[2147483646,2147483647]]
    
    //근데 이런 막장 범위는 뭐냐
    
    public int findMinArrowShots(int[][] points) {
        if(points.length == 0) return 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        for(int[] point : points){
            pq.offer(point);
        }
        
        int rst = 1;
        int prev = pq.poll()[1];
        
        while(pq.size() > 0){
            int[] curr = pq.poll();
            if(prev < curr[0]){
                rst++;
                prev = curr[1];
            } 
        }
        
        return rst;
    }
    
    
    //<Trial04>
    
    
    
    //int[] -> long[]으로 바꿔서
    
    //[[-2147483646,-2147483645],[2147483646,2147483647]] 을 해결함
    
    //priority queue는 int아니면 안돌아가나벼~
    
    //그래서 compare()을 @Override해서 long도 되게끔 함
    
    //아 근데
    
    //[[3,9],[7,12],[3,8],[6,8],[9,10],[2,9],[0,9],[3,9],[0,6],[2,8]]
    
    //여기서 또 터지네
    
    //이번엔 뭐가 문제냐면, 위에 애를 정렬시키면,
    
    //prev: 0 9
	//curr: 0 6
	//curr: 2 8
	//curr: 2 9
	//curr: 3 9
	//curr: 3 9
	//curr: 3 8
	//curr: 6 8
	//curr: 7 12
	//curr: 9 10
    
    //이렇게 되거든?
    
    //point[0]기준으로 정렬되있었기 때문에, point[1]는 순서가 안맞단 말야. 이게 문젠데.
    
    //[0,9] -> [0,6]할때 if(prev < curr[0]) 요기에 안걸려서 그냥 지나친단 말야?
    
    //그럼 걍 지나쳐야 하는게 아니라, 둘중 겹치는의 가장 오른쪽인 6으로 바꾸고 넘어가야겠네
    
    
    public int findMinArrowShots(int[][] points) {
        if(points.length == 0) return 0;
        
        PriorityQueue<long[]> pq = new PriorityQueue(points.length, 
            new Comparator<long[]>(){
                public int compare(long[] a, long[] b) {
                    return Long.compare(a[0], b[0]);
                }
            }
        );
        
        for(int[] point : points){
            long[] tmp = new long[2];
            tmp[0] = (long)point[0];
            tmp[1] = (long)point[1];
            pq.offer(tmp);
        }
        
        int rst = 1;
        long prev = pq.poll()[1];
        
        while(pq.size() > 0){
            long[] curr = pq.poll();
            if(prev < curr[0]){
                rst++;
                prev = curr[1];
            } 
        }
        
        return rst;
    }
    
    
    //<문제풀이1>
    
    //와 시바 가이가 울고가것네
    
    //priority queue에서 사용된 (new Comparator<>(){ public int compare(){} })은 
    
    //Arrays.sort()에서도 똑같이 쓰일 수 있다.
    
	//Arrays.sort(points, new Comparator<int[]>() {
	//	public int compare(int[] a, int[] b) {
	//		if(a[0]==b[0]) return a[1]-b[1];
	//		else return a[0]-b[0];
	//	}
	//});
    
    //이런식으로.
    
    //저걸보면 굳이 long[]으로 바꿀 필요가 있었나 싶네. Integer.MIN_VALUE나오길래  냅다 바꿨는데.
    
    //Runtime: 23 ms
    //Memory Usage: 43.6 MB
    
    public int findMinArrowShots(int[][] points) {
        if(points.length == 0) return 0;
        
        PriorityQueue<long[]> pq = new PriorityQueue(points.length, 
            new Comparator<long[]>(){
                public int compare(long[] a, long[] b) {
                    return Long.compare(a[0], b[0]);
                }
            }
        );
        
        for(int[] point : points){
            long[] tmp = new long[2];
            tmp[0] = (long)point[0];
            tmp[1] = (long)point[1];
            pq.offer(tmp);
        }
        
        int rst = 1;
        long prev = pq.poll()[1];
        
        while(pq.size() > 0){
            long[] curr = pq.poll();
            if(prev < curr[0]){
                rst++;
                prev = curr[1];
            } else {
                prev = Math.min(prev, curr[1]);
            }
        }
        
        return rst;
    }
}
