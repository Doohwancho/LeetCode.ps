package array;

public class BulbSwitcherIII1375 {
	
	/*
	//<문제풀이1>
	
	//크...크윽....
	
	//"지식"은 곧..."power"다...크...크윽...
	
	//"시시"하구만 기래...크...크윽
	
	//Runtime: 1 ms, faster than 100.00% of Java online submissions for Bulb Switcher III.
	//Memory Usage: 51.6 MB, less than 100.00% of Java online submissions for Bulb Switcher III.
	
	public static int numTimesAllBlue(int[] light) {
        //light = [3,2,4,1,5]
        //00100
        //01100
        //01110
        //22220
        //22222
		
        int highest = 0;
        int cnt = 0; //3이면 3보다 작은 수 1,2가 나올때마다 까면서 0일때 rst+1해줌
        int rst = 0;
        
        for(int ele : light){
            if(ele > highest){
                int prev_highest = highest;
                highest = ele;
                if(cnt == 0 && ele == (prev_highest+1)){ //[3,2,1,4,7, ...]에서, 1에서 4로 넘어갈때 원래 스택 위에 그냥 +1된거니, cnt를 0으로 설정하여 cnt증가 없이 rst+1하게 해줌
                    cnt = 0;
                } 
                else { 
                	//prev_highest == 0은 가장 처음시작할 때. 이땐 cnt를 ele-1해주는데, 이유는 ele가 3이면 2(3-1)을 해야, 1이랑 2나왔을때 cnt를 두번까고 rst가 +1되기 때문.
                	//만약 index0이 아니라면, 예를들어 [3,2,1,4,7, ...]에서 4->7로 넘어갈땐, 앞으로 5,6 두번만 더 나오면 되니까, 7-4-1해서 2를 얻음. 
                	//prev_highest가 0이 아닌 상황에서, 예를들어 [3,1,5,..]에서 1->5로 넘어갈 때, 2가 아직 안나왔기 때문에, 이전 cnt(1)+ 앞으로 필요한 cnt(5-3-1 == 1) 해서 2를 계산
                    cnt = prev_highest == 0 ? ele-1: cnt + (ele - prev_highest - 1);
                }
            }
            else{
                cnt--; //만약에 highest보다 작은숫자가 나왔으면 cnt를 하나 까준다.
            }
            if(cnt == 0){ //cnt가 0이면 순서가 어떻게 됬든, 해당 숫자 왼쪽으로 모두 불이 켜졌다는 뜻이므로 rst+1
                rst++;
            }
        }
        return rst;
	}
	*/
	
	//<문제풀이2 by lee215>
	
	//ㅏㅏ.. 뛰는놈위에 나는놈있구나
	
	//Runtime: 1 ms, faster than 100.00% of Java online submissions for Bulb Switcher III.
	//Memory Usage: 50.7 MB, less than 100.00% of Java online submissions for Bulb Switcher III.
	
    public static int numTimesAllBlue(int[] A) {
        int right = 0, res = 0, n = A.length;
        for (int i = 0; i < n; ++i) {
            right = Math.max(right, A[i]);
            if (right == i + 1) ++res; //i+1까지 왔을때까지 나왔던 수들 중에 가장 큰수(right)가 i+1와 같다면, 순서가 어떻게 되었든 해당 인덱스부터 왼쪽의 모든 전구가 불이 들어온 것이다.
        }
        return res;
    }
	
	public static void main(String[] args) {
		int[] test = new int[] {3,2,4,1,5};
		System.out.println(numTimesAllBlue(test));
	}
}
