package juneChallenge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PermutationSequence {

	// <Trial01>

	//앞자리 k/factorial(n-1)로 까서 넣는것까진 됬는데, 나머지가 안됨 제기랄?

	public int factorial(int n) {
		if (n <= 1)
			return n;
		else
			return factorial(n - 1) * n;
	}

	public String getPermutation(int n, int k) {
		StringBuilder rst = new StringBuilder();
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i <= n; i++)
			list.add(i);

		while (n > 0) {
			int fn = factorial(n - 1); // 한 사이클당 몇번이 필요한지?

			if (fn <= 0) {
				rst.append(list.get(0));
				list.remove(0);
			} else if (k % fn > 0) {
				rst.append(list.get(k / fn));
				list.remove(k / fn);
				k %= fn;
			} else {
				rst.append(list.get(k - 1));
				list.remove(k / fn - 1);
				k %= fn;
			}

			n--;
		}
		return rst.toString();
	}

	// <문제풀이1 by mo10>

	// Runtime: 3 ms
	// Memory Usage: 37.2 MB

	public String getPermutation(int n, int k) {

		LinkedList<Integer> notUsed = new LinkedList<Integer>();

		int weight = 1;

		for (int i = 1; i <= n; i++) {
			notUsed.add(i);
			if (i == n)
				break;
			weight = weight * i;
		}

		String res = "";
		k = k - 1;        //이게 위트쩌는 파트임. k/fn했을때 2/2면, 0을 원했는데 계속 1이나와서 어떻게 할지 고민했는데, k-1을 해서 해버리면 0뜸. 만약 k가 원래 4고 fn이 2였다고 해도, 3/2하면 1뜨잖어. 2가 아니라. 어씨 말로 설명하기 어렵네 하여튼 k-1을 함으로써 Trial01에서 if~if else~else문 지랄하는걸 안해도 됨
		while (true) {
			res = res + notUsed.remove(k / weight);
			k = k % weight;
			if (notUsed.isEmpty())
				break;
			weight = weight / notUsed.size();
		}

		return res;
	}
	
	
	//<Trial02>
	
	//문제풀이1보고 심폐소생완료.
	
	//Runtime: 1 ms
	//Memory Usage: 37.1 MB
	
    public int factorial(int n) {
        if (n <= 1) return n;	
		else return factorial(n - 1) * n;
	}

	public String getPermutation(int n, int k) {
		StringBuilder rst = new StringBuilder();
		List<Integer> list = new LinkedList<>();
		for (int i = 1; i <= n; i++) list.add(i);
        k--;
        
		while (n > 0) {
			int fn = factorial(n - 1); 
            if(fn == 0){
                rst.append(list.remove(0));
                if(list.isEmpty()) break;
            } else {
                rst.append(list.remove(k / fn));
                k %= fn;
            }
            n--;
		}
		return rst.toString();
	}
}
