/*
 * <문제풀이 by lee215>
 * 
 * A와 B의 합의 차이는 홀수일 수 없다. 홀수이면 swap후 합이 같을 수 없기 때문(A=3,B=6을 예로 들어 생각해 보자)
 * 
 * 따라서 A합과 B합의 차이를 먼저 구한 후, 2로 나눠서 diff를 구한다.
 * 
 * 마지막에 값을 (a,b)로 반환할 때, a는 b+diff가 된다.
 * 
 * A의 원소를 확인하면서, B의 원소에 diff를 더한값이 있나 확인한 후, (b+diff,b)를 반환해준다.
 * 
 * 
 * Runtime: 52 ms, faster than 31.75% of Java online submissions for Fair Candy Swap.
   Memory Usage: 40.8 MB, less than 93.87% of Java online submissions for Fair Candy Swap.
 * 
 */


package Array;

import java.util.HashSet;
import java.util.stream.IntStream;

public class FairCandySwap888ver2 {
	public int[] fairCandySwap(int[] A, int[] B) 
	{
        int diff = (IntStream.of(A).sum() - IntStream.of(B).sum())/2;
        HashSet<Integer> S = new HashSet<>();
        for(int a : A) S.add(a);
        for(int b : B) if(S.contains(b+diff)) { return new int[] {b+diff, b};}
        return null;
    }

}
