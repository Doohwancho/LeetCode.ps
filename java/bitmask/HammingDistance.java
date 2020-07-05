package julyChallenge;

public class HammingDistance {

	
	//<문제풀이1>
	
	//항상 일정한 시간이 걸리니까 O(1)아닌가?
	
	//x랑 y가 뭐가 들어가든 loop31번 돌아야 될거아냐?
	
	//Runtime: 0 ms
	//Memory Usage: 38.7 MB
	
    public int hammingDistance(int x, int y) {
        int n = x ^ y;
        int rst = 0;
        
        for(int i = 0; i < 32; i++){
            if((n & (1<<i)) > 0){
                rst++;
            };
        }
        
        return rst;
    }
    
    //<문제풀이2 by shawngao>
    
    //bit가 몇개 켜져있는지 확인하는 함수인 Integer.bitCount()를 이용한 방법
    
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
    
    //<문제풀이3 by tankztc>
    
    //1010 0000 이 xor-1하면
    
    //1001 1111 이 되고,
    
    //그걸 xor &=하면 1000 0000이 되고 cnt+1
    
    //1000 0000을 xor-1하면
    
    //0111 1111이 되고
    
    //이걸 cnt+1한 후 xor와 &=하면
    
    //0000 0000이 되네
    
    //그러니까 가장 오른쪽에 있는 bit을 -1하고 &먹이면 없어지니까
    
    //가장 오른쪽에 있는 bit부터 하나씩 까고 cnt+1하는 방식이구나!
    
    //sexy하구만
    
    //Runtime: 0 ms
    //Memory Usage: 38.2 MB
    
    public int hammingDistance(int x, int y) {
        int xor = x ^ y, count = 0;
        
        while (xor != 0) {
            xor &= (xor - 1);
            count++;
        }
        return count;
    }
}
