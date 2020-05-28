package mayChallenge;

public class CountingBits {
	
	
	//<문제풀이1>
	
	//반복되는 패턴을 발견..!
	
	//2진수에 도착할때마다, 1+ 해당 2진수 도착 전까지 나왔던 숫자패턴을 더해주는걸 반복
    
    //0: 0
    //1: 1
    
    //2: 1 (base1(2의 2진수는 0010이니까, 하나 솟아있는 1) 더하기 2 이전에 패턴들(0,1)을 차례대로 더하기)
    //3: 2
    
    //4: 1 (base1(4의 2진수는 0100이니까, 하나 솟아있는 1) 더하기 4 이전에 패턴들(0,1,1,2)을 차례대로 더하기)
    //5: 2
    //6: 2
    //7: 3
    
    //8: 1 (base1(8의 2진수는 1000이니까, 하나 솟아있는 1) 더하기 8 이전에 패턴들(0,1,1,2,1,2,2,3)을 차례대로 더하기)
    //9: 2
    //10:2
    //11:3
    
    //12:2
    //13:3
    //14:3
    //15:4
	
	//Runtime: 2 ms
	//Memory Usage: 43.4 MB
	
    public static int[] countBits(int num) {
    	int[] rst = new int[num+1];
    	for(int i = 1, lastBin = 0; i <= num; i++) {
    		if(isPowerOfTwo(i)) lastBin = i;
    		rst[i] = 1 + rst[i-lastBin];
    	}
    	return rst;
    }
    
    public static boolean isPowerOfTwo(int x) { //x가 2진수라면, 0001 0000처럼 bit이 1인게 딱 한개뿐이여야 함. 그러려면, x-1의 2진수 0000 1111와 &연산자 했을때 아무것도 겹치지 않아야 2진수임. 6의 경우, 0110인데, 6-1인 0101와 &연산자 하면 0100으로, 0이 아님. 고로 2진수가 아님.
        return (x != 0) && ((x & (x - 1)) == 0);
    }
}
