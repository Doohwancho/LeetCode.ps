package array;

import java.util.LinkedList;
import java.util.List;

public class CreateTargetArrayInTheGivenOrder1389 {
	
	
	//<문제풀이1>
	
	//Runtime: 1 ms, faster than 63.17% of Java online submissions for Create Target Array in the Given Order.
	//Memory Usage: 38.3 MB, less than 100.00% of Java online submissions for Create Target Array in the Given Order.
	
    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> container = new LinkedList<>(); //중간에 삽입하는게 있어서 LinkedList썼더니 ArrayList가 더 빠르네.  linkedlist is always O(1) whereas arraylist is O(n). find the position to add is O(N), and then add is O(1)
        
        for(int i = 0; i < nums.length; i++){
            container.add(index[i], nums[i]);
        }
        
        //그냥 return container.stream().mapToInt(i->i).toArray(); 와 같지만, 이것보다 1ms더 빠르다.
        
        //.stream()라이브러리랑 .mapToInt() 찾아서 여는데 1ms정도 걸리나 보다.
        //i->i는 원래는 아래처럼 씀
        
        //list.stream().mapToInt(num -> Integer.parseInt(num)) 
        //	.filter(num -> num % 3 == 0) 
        //	.forEach(System.out::println); 
       
        //근데 이 경우엔 .toArray()하려면 Integer에서 int타입으로 wrapper class -> primitive class 형변환 시켜줘야 하는데,
        
        //거기서 각각의 element를 for문 돌때 쓰려고 .stream을 하고
        //각 element의 형식을 int로바꿔주려고 .mapToInt(i->i)쓴 듯. 
        
        //물론 성능 떨어지는 힙스터 전용답안이라 비추.
        
        int[] rst = new int[nums.length];
        
        for(int j = 0; j < nums.length; j++){
            rst[j] = container.get(j);
        }
        
        return rst;
    }
}
