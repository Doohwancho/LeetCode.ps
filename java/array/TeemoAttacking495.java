package array;

public class TeemoAttacking495 {
	
	//<문제풀이1>
	
	//Runtime: 1 ms, faster than 100.00% of Java online submissions for Teemo Attacking.
	//Memory Usage: 41.6 MB, less than 11.11% of Java online submissions for Teemo Attacking.
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        //유효성검사
        if(timeSeries.length == 0) return 0;
        
        int posTime = 0;
        for(int i = 1; i < timeSeries.length; i++){
            if((timeSeries[i-1]+duration) <= timeSeries[i]){ //i와 i-1과의 차이가 duration보다 더 클 경우, poison이 다 끝난 후, 다음께 오기 때문에, 그냥 poison이 걸리는 시간을 더해줌
                posTime += duration;
            }
            else{
                posTime += (timeSeries[i]-timeSeries[i-1]);//timeSeries==[1,2], duration=2 인 경우, 1->2에서 넘어갈 땐, 2에서 poison이 새출발 하니까 앞에꺼랑 뒤에거 차이만큼 빼주면 됨
            }
        }
        return posTime+duration; //맨 마지막은 어떠한 경우라도 poison 끝까지 맞아야 하기 때문에, 그 시간을 더해줌
    }

}
