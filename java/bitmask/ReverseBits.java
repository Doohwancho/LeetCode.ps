package julyChallenge;

public class ReverseBits {
	
	//<문제풀이1>
	
	//아 그냥 만들어진 함수가 있으면 좀 쓰자 쫌!!
	
	//Runtime: 2 ms
	//Memory Usage: 40.2 MB
	
    public int reverseBits(int n) {
        return Integer.reverse(n);
    }
    
    //<문제풀이2 by PIP_Guy>
    
    //n에 오른쪽 맨 끝자리를 &1로 end에 담은다음, rst에 넣고 왼쪽으로 한칸씩 미는 방법.
    
    //Runtime: 2 ms
    //Memory Usage: 39.9 MB
    
    public int reverseBits(int n) {
        int rst = 0;
        for(int i = 0; i < 32; i++){
            int end = n & 1;
            n >>= 1;
            rst <<= 1;
            rst |= end;
        }
        return rst;
    }
    
    //<문제풀이3 by dugu9sword>
    
    //merge sort
    
    //미친새낀가? 
    
	//Take 12345678 as an example.
	//First step, interchange 1234 with 5678 -> 56781234
	//Second step, interchange 56~~12~~ with ~~78~~34-> 78563412
	//Last step, interchange 7~5~3~1~ with ~8~6~4~2 ->87654321
    
    //0xFF00FF00 = 1111 1111 0000 0000 1111 1111 0000 0000
    //0x00FF00FF = 0000 0000 1111 1111 0000 0000 1111 1111
    
    //0xF0F0F0F0 = 1111 0000 1111 0000 1111 0000 1111 0000
    //0x0F0F0F0F = 0000 1111 0000 1111 0000 1111 0000 1111
    
    //0xCCCCCCCC = 1100 1100 1100 1100 1100 1100 1100 1100
    //0x33333333 = 0011 0011 0011 0011 0011 0011 0011 0011
    
    //0xaaaaaaaa : 1010 1010 1010 1010 1010 1010 1010 1010
    //0x55555555 : 0101 0101 0101 0101 0101 0101 0101 0101
    
    //Runtime: 2 ms
    //Memory Usage: 40 MB
    
    public int reverseBits(int n) {
        int ret=n;
        ret = ret >>> 16 | ret<<16;
        ret = (ret & 0xff00ff00) >>> 8 | (ret & 0x00ff00ff) << 8;
        ret = (ret & 0xf0f0f0f0) >>> 4 | (ret & 0x0f0f0f0f) << 4;
        ret = (ret & 0xcccccccc) >>> 2 | (ret & 0x33333333) << 2;
        ret = (ret & 0xaaaaaaaa) >>> 1 | (ret & 0x55555555) << 1; 
        return ret;
    }
}
