package array;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumIncrementToMakeArrayUnique945 {
	
	//in-process...
	
	public int minIncrementForUnique(int[] A) {
        int[] container = new int[40000];
        int rst = 0;
        int maxNum = 0;
        
        for(int element : A){
            container[element]++;
            maxNum = Math.max(maxNum, element);
        }
        
        //[3,2,1,2,1,7] == [1,1,2,2,3,7]

        //[0,2,2,1,0,0,0,1]

        //[위치 인덱스, 몇개]
        //[1, 1], [2, 1] (Queue)
        //rst += (4-1)
        //rst += (5-2)

        //[1, 3], [2, 1]
        
        
        Queue<Integer> q = new LinkedList<>();
        
        boolean flag = false;
        for(int i = 0, idx = 0; i < maxNum+1; i++){
            System.out.println(i);
            if(container[i] > 1){
                System.out.println("if");
                flag = true;
                if(container[i] > 1){
                    System.out.println("if-if");
                    q.add(i);
                }
            } else if(flag && container[i] == 0){
                System.out.println("else if");
                if(idx == 0 && q.size() > 0){
                    System.out.println("else if - if");
                    idx = q.poll();
                } else {
                    System.out.println("else if - else");
                    continue;
                }
                
                if(container[idx] == 2){
                    System.out.println("else if - 2nd if");
                    rst += (i - container[idx]);
                    idx = 0;
                } else {
                    System.out.println("else if - 2nd else");
                    container[idx]--;
                }
            }
        }
        return rst + (q.poll()-1);
    }
}
