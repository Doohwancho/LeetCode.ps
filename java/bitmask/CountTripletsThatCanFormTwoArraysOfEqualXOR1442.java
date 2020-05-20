package array;

public class CountTripletsThatCanFormTwoArraysOfEqualXOR1442 {

    
    //<문제풀이1 by Admin007>
	
	//i<j까지의 xor이 a이고,
	
	//j<=k까지의 xor이 b일때
	
	//a==b의 갯수를 찾는거잖아?
	
	//근데 a==b라면, a^b == 0이겠지?
	
	//같은숫자를 xor하면 0되잖아. 같은 bitmask의 digit은 0이되니까. 
	
	//예를들어, 3^3 = 0이고, 15243^15243 = 0이니까.
	
	//그럼 xor==0파트는 이해가 감
	
	//근데 j-i파트는? 왜?
	
	//1,3,2 가 있다고 치자.
	
	//그럼 (1),(3,2)랑
	
	//(1,3)(2) 두가지 경우네?
	
	//1이랑 (3,2)를 먼저 보면, 3^2 = 1이여서, 1^1 = 0이지.
	
	//(1,3)이랑 2를 봐도, 1^3은 2고, 2^2=0이지.
	
	//그러니까, i부터 j까지의 숫자들을 xor했을 때, 그 사이를 어떤식으로 가르든지 a==b는 성립되기 때문에, j-i를 더해주는겨.
	
	//a^b^c^d^e = 0이라면,
	
	//얘내를 둘로 쪼갰을때, 
	
	//a = b^c^d^e 이고,
	
	//a^b = c^d^e 이고,
	
	//a^b^c = d^e 이고,
	
	//a^b^c^d = e 야.
	
	//왜냐면, =의 왼쪽애들이랑 =의 오른쪽애들이랑 같아야 둘이 ^했을때 0이될거아냐.
	
	//그러니까 e의 인덱스인 4에서 a의 인덱스인 0를 뺀 4를 더해주는거고. 이게 j-i.
    
    //Runtime: 2 ms, faster than 78.67% of online submissions for Count Triplets That Can Form Two Arrays of Equal XOR.
    //Memory Usage: 38.9 MB, less than 100.00% of online submissions for Count Triplets That Can Form Two Arrays of Equal XOR.
    
    //Give the observation: For all pairs of i and k, where arr[i] ^ arr[i + 1] ^ ... ^ arr[k] = 0, 
    //then any j (i < j <= k) will be good to set as the answer 
    //(hint: proof by contradiction, if you cannot understand this trick immediately). 
    //So you just need to find all segments where XOR equals zero.
    
    public int countTriplets(int[] arr) {
        int ans = 0;
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            int xor = arr[i];
            for (int j = i + 1; j < length; j++) {
                xor ^= arr[j];
                if (xor == 0) {
                    ans += (j - i);
                }
            }
        }
        return ans;
    }
}
