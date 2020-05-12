package mayChallenge;

public class SingleElementInASortedArray {
	
	
	//<문제풀이1>
	
	//bitmask에서 xor이용하는 방법
	
	//https://doohwancho.tistory.com/867
	
	//같은문제 몇번째 우려먹는거냐 리트코드야 지겹다
	
	//Runtime: 0 ms
	//Memory Usage: 39.4 MB
    public int singleNonDuplicate(int[] nums) {
        int rst = 0;
        for(int n : nums) rst ^= n;
        return rst;
    }
}
