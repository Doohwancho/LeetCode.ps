package bitmask;

public class HammingDistance461 {
	
	//<문제풀이1>
	
	//x랑 y를 2진수로 만들어서 bit의 자릿수가 몇개나 다른지 세라는거아녀

	//x랑 y의 범위가 0 ≤ x, y < 2^31 니까, bit자리가 총 32갠데,
	
	//음수는 안세니까 맨 앞에 bit는 셀 필요 없겠지? 그럼 31번만 for문 돌리면서
	
	//자릿수 체크해주면 되겠네.
	
	//자릿수 체크는 1 << i로 하고.
	
	//1 << i는 맨 오른쪽부터 i번 땡긴 후에 1을 켜준다는 말이여.
	
	//거기에 x & 를 하면, i번째 땡긴곳에 x도 bit가 켜져있으면 해당자리의 숫자를 돌려줘
	
	//예를들어, 9의 bit는 1001이지?
	
	//9 & (1<<3) 하면 1000이 켜지고, 1001 & 1000하면 1000이니까, 8을 반환해주는 식이지.
	
	//이렇게 x랑 y랑 bit자리 31개 비교해보면 됨
	
	// Runtime: 0 ms, faster than 100.00% of Java online submissions for Hamming Distance.
	// Memory Usage: 36.1 MB, less than 78.77% of Java online submissions for Hamming Distance.
    public int hammingDistance(int x, int y) {
        int rst = 0;
        for(int i = 0; i < 32; i++){
            if ((x & (1 << i)) != (y & (1 << i))){
                rst++;
            }
        }
        return rst;
    }
    
    
    //<문제풀이2 by shawngao>
    
    //머리좀 썼네 이친구
    
    //x랑 y를 xor하면, 원래 같은 bit에 있는건 꺼지고, 다른 비트에 있는건 켜질거아녀?
    
    //그럼 .bitCount() 내장함수로 다른 bit가 몇개있는지 보면 되자너
    
//    public int hammingDistance(int x, int y) {
//        return Integer.bitCount(x ^ y);
//    }
	    
}
