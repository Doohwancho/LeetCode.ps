package array;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumIncrementToMakeArrayUnique945 {
	
	/*
	//<Trial01>
	
	//개추하게 이악물고 했는데도 안되네 쓰바
	
	public int minIncrementForUnique(int[] A) {
        int[] container = new int[40000];
        Queue<Integer> q = new LinkedList<>();
        
        int rst = 0;
        int maxNum = 0;
        boolean flag = false;
        
        for(int element : A){
            container[element]++;
            maxNum = Math.max(maxNum, element);
        }

        for(int i = 0, idx = -1; i < maxNum+1; i++){
            if(container[i] > 1){
                flag = true;
                if(container[i] > 1){
                    q.add(i);
                }
            } else if(flag && container[i] == 0){
                if(idx == -1 && q.size() > 0){
                    idx = q.poll();
                } else {
                    continue;
                }
                
                if(container[idx] == 2){
                    rst += (i - idx);
                    idx = -1;
                } else {
                    rst += (i - idx);
                    container[idx]--;
                }
            }
        }
        if(q.size()>0){
            int lastIdx = q.poll();
            int additionals = container[lastIdx]-1;
            if(additionals % 2 == 0){
                return rst + (additionals+1) * (additionals/2) + (maxNum - lastIdx) * additionals;
            } else {
                return rst + additionals * ((additionals-1)/2) + additionals + (maxNum - lastIdx) * additionals;
            }
        } else {
            return rst;
        }
    }
    */
	
	/*
	//<문제풀이1>
	
	//오오ㅗ오오오오오오옹오옹ㅇ오오오오오
	
	//이거거등~
	
	//Runtime: 6 ms, faster than 92.45% of Java online submissions for Minimum Increment to Make Array Unique.
	//Memory Usage: 42.6 MB, less than 100.00% of Java online submissions for Minimum Increment to Make Array Unique.
	
    public int minIncrementForUnique(int[] A) {
    	//0 <= A.length <= 40000, 0 <= A[i] < 40000 인데 맨 마지막에 밀려서 몇천개 더 넣을수 있어서 넉넉히 조정한 것
    	//container에는 각 숫자별 빈도수를 넣을 것임
        int[] container = new int[50000]; 
        int rst = 0;
        int maxNum = 0;  

        for(int element : A){
            container[element]++; //빈도수 
            maxNum = Math.max(maxNum, element); 
            //가장 큰 숫자를 구하는 이유는, 나온숫자중 젤큰수가 3이면, [x, x, x, x]같이 index 3까지 빈도수를 차곡차곡 넣을거라서. 물론 밀린건 index3 이상으로 넘어가겠지만.
            //기냥 container.length 5만개 무식하게 다돌리면 6ms느려짐
            //Runtime: 12 ms, faster than 81.25% of Java online submissions for Minimum Increment to Make Array Unique.
        }

        //[1,2,0,1,0] <- 가 원래 주어진 A라고 치면
        //[0,0,1,1,2] <- 이걸 순서대로 정렬한게 이거잖어?
        //[2,2,1]  //그걸 container에 빈도수에 맞게 쌓은게 이거고
        //그럼 이 경우엔 index0에서 남는게 한개고 이걸 처리하려면 index3에 하나 쌓아야 하네?
        //그럼 인덱스끼리 빼줘서 3-0하면 3만큼 움직여야 하니까, result에 +3해줌/
        //근데 만약에 [3,2,1]이라서 0이 세개 있었는데 하나 index3으로 옮긴거라면?
        //그걸 배열로 보면 [0,0,0,1,1,2] -> [0,0,1,1,2,3]이 된거네? result는 3이고
        //근데 0 하나 더옮겨야 되잖어? 어디루? 새롭게 0이 나타나는 index지점으로.
        //그럼 0이 새롭게 튀어나오면, 새롭게 나온 index - 0의 index만큼(이 경우 4-0=4) result에 더해주면 되겠지?
        //그럼 result는 7이되고 다음에 처리해야할 1로 넘어감.
        //근데 2개 이상씩 빈도수가 있으면 순서대로 쌓아서 처리해야 하잖아? 그걸
        //밑에 queue로 차곡차곡 쌓고, 맨 처음것부터 하나하나뺌
  
        Queue<Integer> q = new LinkedList<>();

        int i = 0;
        int anchor = -1;

        while(i < maxNum+1 || q.size() > 0 || anchor != -1){ //맨 마지막 시점에 아무것도 안쌓였다면 나온 최대숫자(maxNum)이상 볼 필요 없으니까 i < maxNum+1. 만약 맨 끝에 쌓인게 좀 남았으면 q.size() > 0 발동, 혹은 쌓인건 queue엔 없는데 맨 마지막게 anchor에 있으면 anchor != -1 발동.
            if(container[i] > 1){ //쌓인게 있으면 
                q.add(i);         //queue에 차곡차곡 쌓는다 
            } 
            else if(container[i] == 0){ //만약 빈도수가 0인데
                if(anchor == -1 && q.size() > 0) anchor = q.poll(); //anchor가 -1이고 q크기가 1이상이면, i index이전 스택이 쌓여 위에 if문이 발동됬다는 의미이므로, anchor를 세팅해 준다
                if(anchor != -1){ //만약 anchor가 세팅이 된 상태면
                    rst += (i-anchor); //인덱스의 차이만큼 rst에 더해주고
                    container[anchor]--; //하나를 이동시켰으니 빈도수를 하나 줄여주고
                    if(container[anchor] == 1) anchor = -1;  //빈도수가 다 줄어서 1이되면 다음 anchor로 넘어가기 위해 anchor를 -1으로 초기화 해준다.
                }
                else{
                    i++;  //continue하면 밑에 i++;가 무시당해서 while이 평생돔.
                    continue; 
                }
            }
            i++;
        }
        return rst;
    }
    */
	
	//약간 추하지만 정리된거 한번 더올릴꺼야
    
    public int minIncrementForUnique(int[] A) {
		Queue<Integer> q = new LinkedList<>();
        int[] container = new int[50000]; 
        int rst = 0, maxNum = 0, i = 0, anchor = -1;

        for(int element : A){
            container[element]++; 
            maxNum = Math.max(maxNum, element); 
        }
		
        while(i < maxNum+1 || q.size() > 0 || anchor != -1){ 
            if(container[i] > 1) q.add(i);
            else if(container[i] == 0){ 
                if(anchor == -1 && q.size() > 0) anchor = q.poll();
                if(anchor != -1){ 
                    rst += (i-anchor); 
                    container[anchor]--; 
                    if(container[anchor] == 1) anchor = -1;  
                }
            }
            i++;
        }
        return rst;
    }
}
