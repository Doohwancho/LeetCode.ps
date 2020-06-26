package array;

public class XOROperationInAnArray1486 {

	//<문제풀이1>
	
	//문제에서 어떻게 풀라고 친절하게 알려줘서 딱히 생각할게 없네
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for XOR Operation in an Array.
	//Memory Usage: 38.3 MB, less than 100.00% of Java online submissions for XOR Operation in an Array.
    public int xorOperation(int n, int start) {
        int rst = 0;
        for(int i = 0; i < n; i++){
            rst ^= start + 2 * i;
        }
        return rst;
    }
}
