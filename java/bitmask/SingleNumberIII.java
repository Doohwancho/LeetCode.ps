package julyChallenge;

public class SingleNumberIII {
	
	//<문제풀이 by zhiqing_xiao>
	
	//똑똑하네 레알루다가
	
	//Runtime: 1 ms
	//Memory Usage: 42.1 MB
	
    public int[] singleNumber(int[] nums) { 
        if (nums == null || nums.length < 2) return new int[0];
        int t = 0; 
        for (int n: nums) t ^= n; //t = a^b
        t &= -t;  //find the least significant different bit between a and b.
        
//      System.out.println(7&-7); //1
//		System.out.println(Integer.toBinaryString(7)); //111
//		System.out.println(Integer.toBinaryString(-7)); //11111111111111111111111111111001
//		System.out.println(Integer.toBinaryString(1)); //1
//		
//		System.out.println(10&-10); //2
//		System.out.println(Integer.toBinaryString(10)); //1010
//		System.out.println(Integer.toBinaryString(-10)); //11111111111111111111111111110110
//		System.out.println(Integer.toBinaryString(2)); //10
//		
//		System.out.println(8&-8); //8
//		System.out.println(Integer.toBinaryString(8)); //1000
//		System.out.println(Integer.toBinaryString(-8)); //11111111111111111111111111111000
//		System.out.println(Integer.toBinaryString(8)); //1000
        
        //일단 a^b는 a랑 b의 bitwise 차이야. 만약 t(==a^b)가 0이면, 둘의 차이가 없다는 말이니까, a랑 b는 같은 숫자가 되겠지.
        
        //t는 a^b의 가장 작은 bit이잖아?. t는 a랑 b의 차이중에 가장 작은 비트야
        
        //그러면 t가 a랑 b의 차이중에 가장 작은 비트라고 했으니까, 
        
        //a^t하고 b^t했을때 둘중 하나만 0보다 크고, 나머지는 0이 나오겠지?
        
        //그럼 a^num[i]이랑 b^num[i]는 어떻게 설명하느냐.
        
        //num[i]가 두 그룹으로 나뉘는게, 굳이 반반 똑 나눠서 갈릴 필요가 없어
        
        //만약 t&n > 0이면, 주구장창 다 if에 들어갈 수도 있지.
        
        //그래도 상관없어. 몇그룹이 한쪽에 쏠리든 어짜피 페어들은 xor하면 0되니까.
      
        int[] ret = new int[2];
        for (int n: nums) {
            if ((t & n) > 0) ret[0] ^= n;
            else ret[1] ^= n;
        }
        return ret;
    }
	
	
}
