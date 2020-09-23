package september;

public class GasStation {
	
	//<문제풀이1>
	
	//brute-force
	
	//Runtime: 23 ms
	//Memory Usage: 39.7 MB

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        
        int[] gas_ = new int[len*2];
        int[] cost_ = new int[len*2];
        
        for(int i = 0; i < len; i++){
            gas_[i] = gas_[i+len] = gas[i];
            cost_[i] = cost_[i+len] = cost[i];
        }
        
        for(int i = 0; i < len; i++){
            int start = gas[i];
            for(int j = i; j < len+i; j++){
                start -= cost_[j];
                if(start < 0){
                    break;
                } else if(j == len+i-1){
                    return i;
                }
                start += gas_[j+1];
            }
        }
        return -1;
    }
    
    
    //<문제풀이2 by Cheng_Zhang>
    
    //수학적으로 접근한 방법.
    
    //gas[i]-cost[i]를 누적한게 마이너스면, 우리가 찾는 인덱스는 0~i까지 절대 있을 수 없음.
    
    //왜냐면, i이전에서 시작해서 오른쪽으로 가면 무조건 누적합이 마이너스 찍힌다는 말이잖어.
    
    //tank가 마이너스일때마다 idx를 i+1로 세팅함. greedy방식.
    
    //total은 gas[i]-cost[i]의 전체 차이를 나타냄.
    
    //어짜피 한바퀴 다 찍으면, gas의 총 합 - cost의 총합 해서 0이랑 같거나 크면 우리가 찾는 idx가 있는거고
    
    //마이너스면 어딜돌아도 없는거니 return -1이니까,
    
    //return total < 0 ? -1 : idx;
    
    //똑똑하구만
    
    //Runtime: 0 ms
    //Memory Usage: 39.8 MB
    
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int idx = 0, tank = 0, total = 0, len = gas.length;
        for(int i = 0; i < len; i++){
            int diff = gas[i] - cost[i];
            tank += diff;
            if(tank < 0){
                idx = i+1;
                tank = 0;
            }
            total += diff;
        }
        return total < 0 ? -1 : idx;
    }
}
