package augustChallenge;

public class PowerOfFour {
	
	
	//<문제풀이1>
	
	//아 성능도 좋고 잘 풀긴 했는데 뭔가 2% 부족한 느낌
	
	//뭔가 약간 덜 섹시해
	
	//Runtime: 1 ms
	//Memory Usage: 36.9 MB
	
    public boolean isPowerOfFour(int num) {
        if(num <= 0) return false;
        else if(num == 1) return true;
        
        for(int i = 0, cnt = 0; i < 32; i++){
            if((num & (1<<i)) > 0){
                if(i % 2 == 0 && i != 0){ 
                    cnt++;
                    if(cnt == 2) return false;
                    continue;
                }
                return false;
            }
        }
        return true;
    }
    
    //<문제풀이2>
    
    //cnt를 Integer.bitCount(num) == 1로 대체해 보았다.
    
    //아 그래도 뭔가 2% 부족한데
    
    //Runtime: 1 ms
    //Memory Usage: 36.8 MB
    
    public boolean isPowerOfFour(int num) {
        if(num <= 0) return false;
        else if(num == 1) return true;
        
        for(int i = 0; i < 32; i++){
            if((num & (1<<i)) > 0){
                if(i % 2 == 0 && i != 0 && Integer.bitCount(num) == 1){ 
                    return true;
                }
                return false;
            }
        }
        return false;
    }
    
    //<Trial01>
    
    //아 2의 제곱수랑 4의 제곱수를 구분하기만 하면 되는데 이걸 모르겠네
    
    public boolean isPowerOfFour(int num) {
        if(num <= 0 || Integer.bitCount(num) != 1 || (Integer.lowestOneBit(num) % 4 != 0 && num != 1)) return false;
        return true;
    }
    
    
    //<문제풀이3 by aiscong>
    
    //그래 바로 이거지
    
    //이게 쿨하고 펀하고 섹시한 답이지
    
    //0x55555555 = 0x55 = 01010101
    
    //이걸로 4의 제곱수를 구분함. 
    
    //num & 01010101.... 하면 무조건 4의 제곱수만 나와야 하기 때문.
    
    //Runtime: 1 ms
    //Memory Usage: 37.1 MB
    
    public boolean isPowerOfFour(int num) {
        return num > 0 && (num&(num-1)) == 0 && (num & 0x55555555) != 0;
    }
    
    
    //<문제풀이4 by JudyH15>
    
    //log
    
    //log(숫자)/log(몇진수인지) 나누면, 4의 제곱수들은 0,1,2,3,4, 1씩 증가하도록 나옴
    
    //다른 숫자들은 소숫점이 껴서 나오고.
    
    //이걸 %1하면 4의 제곱수들은 소숫점이 원래 없어서 0이되고, 나머지 숫자는 소숫점을 가진 숫자가 됨.
    
    //이걸 ==0해서 0으로 떨어지는지 보는 것임.
    
    
    //Runtime: 1 ms
    //Memory Usage: 36.6 MB
    
    public boolean isPowerOfFour(int num) {
       return Math.log(num)/Math.log(4)%1 == 0;
    }
    
}
