package september;

public class CarPooling {
	
	//<문제풀이1>
	
	//brute-force
	
	//1) 최대 거리 측정
	
	//2) i번째에 몇명이 필요한지 표시
	
	//3) i번째에 필요한 손님이 capacity보다 더 크다면, return false
	
	//Runtime: 3 ms
	//Memory Usage: 39.3 MB
	
	
    public boolean carPooling(int[][] trips, int capacity) {
        
    	//1)
    	int len = 0;
        for(int[] trip : trips){
            len = Math.max(len, trip[2]);
        }
        int[] a = new int[len];
        
        //2)
        for(int[] trip : trips){
            for(int i = trip[1]-1; i < trip[2]-1; i++){
                a[i] += trip[0];
            }
        }
        
        //3)
        for(int i : a){
            if(i > capacity){
                return false;
            }
        }
        return true;
    }
    
    
    //<문제풀이1 by lee215>
    
    //크.. 지렸다
    
    //hashmap아니고 treemap이야!
    
    //순서대로 넣어야 되서 그래.
    
    //출발할때 trip[0]만큼 까고 도착했을때 trip[0]만큼 다시 채워주는 방식
    
    //아트구만 아트야
    
    //Runtime: 5 ms
    //Memory Usage: 39.1 MB
    
    public boolean carPooling(int[][] trips, int capacity) {
        Map<Integer, Integer> map = new TreeMap<>();
        for(int[] trip : trips){
            map.put(trip[1], map.getOrDefault(trip[1], 0) + trip[0]);
            map.put(trip[2], map.getOrDefault(trip[2], 0) - trip[0]);
        }
        
        for(int i : map.values()){
            capacity -= i;
            if(capacity < 0){
                return false;
            }
        }
        return true;
    }
    
    //<문제풀이2 by votrubac>
    
    //문제풀이1과 같은 개념.
    
    //Runtime: 1 ms
    //Memory Usage: 39.5 MB
    
    //Runtime: O(n), where n is the number of trips.
    //Memory: O(m), where m is the number of stops.
    
    public boolean carPooling(int[][] trips, int capacity) {
        int[] a = new int[1001];
        for(int[] t : trips){
            a[t[1]]+=t[0];
            a[t[2]]-=t[0];
        }
        for(int i = 0; i < 1001 && capacity >= 0; i++) capacity -= a[i];
        return capacity >= 0;
    }
}
