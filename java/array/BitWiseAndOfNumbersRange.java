package thirtyDayChallenge;

import java.util.ArrayList;
import java.util.List;

public class BitWiseAndOfNumbersRange {
	
	/*
	//<Trial01 - Time limit exceeded>
	
	//아니 이건 너무한거 아니냐고~
	
	//이정도면 빠르자너~ 패스해줘~

    public static int rangeBitwiseAnd(int m, int n) {
        char[] mChar = Integer.toBinaryString(m).toCharArray();
		List<Integer> list = new ArrayList<>();
		
		for(int idx = mChar.length-1, i = 0;idx >= 0;idx--, i++) {
			if('1' == mChar[idx]) {
				list.add(i);            //오른쪽으로부터 몇번째에 1이 있는지 체크
			}
		}
		int i = m+1;
		while(list.size()>0 && i <= n) {
            for(int j = list.size()-1; j >=0; j--) {
            	if((i & (1<< list.get(j))) == 0) { //m+1~n의 수들 중, 해당 자리에 1이 없다면, and연산자로 없어지니까 해당 자릿수 제거.
            		list.remove(j);
            	}
            }
			i++;
		}
		
		int rst = 0;
		for(int digit : list) {
			rst = rst | (1 << digit); //남은 자릿수로 1을 넣어 숫자를 만듬
		}
		return rst;
    }
	*/
    
	
	//<문제풀이1 by dietpepsi>
	
	//똑똑허네
	
	//m이랑 n이 이렇다고 하자.
	
	//m = 0abc 0010
	
	//n = 0abc 1000
	
	//m에 0010부분은 1씩 올라가면서 bitmask가 바뀌며 결국엔 n이랑 같은 지점에 나올때까지 다 꺼질거다.
	
	//0abc 0000까지
	
	//8266 / 8266 test cases passed.
	//Status: Accepted
	//Runtime: 4 ms
	//Memory Usage: 38.8 MB
    public int rangeBitwiseAnd(int m, int n) {
        int i = 0;
        for (; m != n; ++i) {
            m >>= 1;
            n >>= 1;
        }
        return n << i;
    }
}
