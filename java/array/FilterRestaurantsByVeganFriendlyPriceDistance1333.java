package array;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class FilterRestaurantsByVeganFriendlyPriceDistance1333 {
	
	/*
	//<Trial01>
	
	//Return the array of restaurant IDs after filtering, ordered by rating from highest to lowest. For restaurants with the same rating, order them by id from highest to lowest.
	
	//여기서 막힘. container[rating] = id 할때, rating이 같으면 id가 덮어써지는 문제 때문.
	
	//이 문제의 코어는 정렬이네
	
	//Input
	//[[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,1,15,1]], 0, 50, 10
	//Output : [4,3,1,5]
	//Expected : [4,3,2,1,5]
	
	public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<Integer> rst = new ArrayList<>();
        int[] container = new int[100000];
        
        if(veganFriendly == 1){
            for(int[] restaurant : restaurants){
                if(restaurant[2] == veganFriendly && restaurant[3] <= maxPrice && restaurant[4] <= maxDistance){
                    container[restaurant[1]] = restaurant[0];
                }
            }
        } else {
            for(int[] restaurant : restaurants){
                if(restaurant[3] <= maxPrice && restaurant[4] <= maxDistance){
                    container[restaurant[1]] = restaurant[0];
                }
            }
        }
        
        for(int i = container.length-1; i >= 0; i--){
            if(container[i] > 0){
                rst.add(container[i]);
            }
        }

        return rst;
    }
    */
	
	/*
	//<Trial02>
	
	//이것도 같은 input에서 막힘
	
	//[[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,1,15,1]]
	
	//여기서 [2,8,0,50,5] 이거 넣고 [3,8,1,30,4] 이거 넣을때, 8이 겹쳐서 저장되기 때문에 앞에꺼 덮어써서 그런듯.
	
	public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<Integer> rst = new LinkedList<>();
        Map<Integer, Integer> treeMap = new TreeMap<>(); //넣을때부터 자동으로 key가 오름차순정렬되서 들어감
        
        if(veganFriendly == 1){
            for(int[] restaurant : restaurants){
                if(restaurant[2] == veganFriendly && restaurant[3] <= maxPrice && restaurant[4] <= maxDistance){
                    treeMap.put(restaurant[1], restaurant[0]);
                }
            }
        } else {
            for(int[] restaurant : restaurants){
                if(restaurant[3] <= maxPrice && restaurant[4] <= maxDistance){
                    treeMap.put(restaurant[1], restaurant[0]);
                }
            }
        }
        
        Iterator<Integer> iteratorKey = treeMap.keySet( ).iterator( );

        while(iteratorKey.hasNext()) {
        Integer key = iteratorKey.next();
            rst.add(0, treeMap.get(key));
        }
    
        return rst;
    }
    */
	
	/*
	//<문제풀이1>
	
	//if-else문에서 조건사항(vegan,price,distance)에 해당하는 것만 treeMap에 적재한다.
	
	//hashMap안쓰고 treeMap쓴 이유는, treeMap은 넣을때 key값이 자동으로 오름차순 정렬되서 들어가기 때문에,
	
	//중요도(restaurant[1])을 key값으로 넣고, id를 value값으로 넣으면,
	
	//중요도 순으로 treeMap에 이쁘게 오름차순 정렬되서 들어가기 때문.
	
	//문제에서 한번 더 꼬은게, 중요도가 같은 n개의 레스토랑이 있으면,
	
	//레스토랑 아이디를 큰것부터 반환하라고 함.
	
	//trial01이나 trial02가 나가리 인게, 중요도가 같은 레스토랑이 중복해서 나왔을 경우, 이전 레스토랑을 덮어쓰는게 문제였는데,
	
	//이걸 value값을 new ArrayList<>();로 해놓고 중복된 레스토랑 있을때마다 list.add(duplicated new restaurant)해줘서 해결.
	
	//Runtime: 6 ms, faster than 73.76% of Java online submissions for Filter Restaurants by Vegan-Friendly, Price and Distance.
	//Memory Usage: 50 MB, less than 100.00% of Java online submissions for Filter Restaurants by Vegan-Friendly, Price and Distance.
	
	public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<Integer> rst = new LinkedList<>();
        Map<Integer, List<Integer>> treeMap = new TreeMap<>(); //넣을때부터 자동으로 key가 오름차순정렬되서 들어감
        
        if(veganFriendly == 1){
            for(int[] restaurant : restaurants){
                if(restaurant[2] == veganFriendly && restaurant[3] <= maxPrice && restaurant[4] <= maxDistance){
                    if(treeMap.get(restaurant[1]) != null){
                        treeMap.get(restaurant[1]).add(restaurant[0]);
                    } else {
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(restaurant[0]);
                        treeMap.put(restaurant[1], tmp);
                    }
                }
            }
        } else {
            for(int[] restaurant : restaurants){
                if(restaurant[3] <= maxPrice && restaurant[4] <= maxDistance){
                    if(treeMap.get(restaurant[1]) != null){
                        treeMap.get(restaurant[1]).add(restaurant[0]);
                    } else {
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(restaurant[0]);
                        treeMap.put(restaurant[1], tmp);
                    }
                }
            }
        }
        
        Iterator<Integer> iteratorKey = treeMap.keySet( ).iterator( );

        while(iteratorKey.hasNext()) {
            Integer key = iteratorKey.next();
            List<Integer> tmp = treeMap.get(key);

            if(tmp.size() > 1){
                Collections.sort(tmp);
                
                for(int i = 0; i < tmp.size(); i++){
                    rst.add(0, tmp.get(i));
                }
                
            } else if(tmp.size() == 1) {
                rst.add(0, tmp.get(0));
            }
            
        }
    
        return rst;
    }
    */
	
	//<문제풀이 by xiaoxiang615>
	
	//if input : [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,1,15,1]], 1, 50, 10
	
	//for문 다 돌면, maxHeap에 있는 것들은
	
	//[3 8 1 30 4] 
	//[1 4 1 40 10]
	//[5 1 1 15 1]
	
	
	//Queue는 들어온 순서에 따라 차례대로 데이터 적재.
	
	//priorityQueue란, 들어온 순서에 상관 없이 우선순위가 높은 데이터가 먼저 적재.
	
	//Queue<int[]> maxHeap = new PriorityQueue<>((a, b)->a[1] != b[1] ? b[1] - a[1] : b[0] - a[0]);
	
	//여기서, (a, b)->a[1] != b[1] ? b[1] - a[1] : b[0] - a[0] 이게 람다식인데
	
	//기존것(a)보다 나중에 들어온 것(b)의 rating(index[1])이 다른 경우,
	
	//b[1]-a[1]하고 값이 +면, 중요도가 높다는 뜻이니, 중요도가 높은것을 먼저 정재하는 PriorityQueue에 룰에 따라 a보다 b가 순서상 먼저 적재됨.
	
	//만약 b[1]-a[1]하고 값이 -면(같을 순 없음. a[1] != b[1] 조건에 맞아야 이게 실행되기 때문), 중요도가 낮다는 뜻이니, b가 a보다 순서상 뒤에 적재됨.
	
	//만약 a의 중요도(a[1])와 b의 중요도(b[1])가 같다면, b[0] - a[0]을 하여, id가 높은게 우선순위로 적재됨.
	
	//Runtime: 4 ms, faster than 99.48% of Java online submissions for Filter Restaurants by Vegan-Friendly, Price and Distance.
	//Memory Usage: 50.8 MB, less than 100.00% of Java online submissions for Filter Restaurants by Vegan-Friendly, Price and Distance.
	
	public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
	    Queue<int[]> maxHeap = new PriorityQueue<>((a, b)->a[1] != b[1] ? b[1] - a[1] : b[0] - a[0]); 
	    for(int[] r : restaurants){
	        if(r[2] >= veganFriendly && r[3] <= maxPrice && r[4] <= maxDistance){ //if-else 안쓰고 >=하나로 vegan문제 해결. 
	            maxHeap.offer(r);
	        }
	    }
	    List<Integer> res = new ArrayList<>();
	    while(!maxHeap.isEmpty()){
	        res.add(maxHeap.poll()[0]);
	    }
	    return res;
	}
	

}		
