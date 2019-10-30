/*
	We are given an array A of N lowercase letter strings, all of the same length.
	
	Now, we may choose any set of deletion indices, and for each string, we delete all the characters in those indices.
	
	For example, if we have an array A = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3}, then the final array after deletions is ["bef", "vyz"], and the remaining columns of A are ["b","v"], ["e","y"], and ["f","z"].  (Formally, the c-th column is [A[0][c], A[1][c], ..., A[A.length-1][c]].)
	
	Suppose we chose a set of deletion indices D such that after deletions, each remaining column in A is in non-decreasing sorted order.
	
	Return the minimum possible value of D.length.
	
	 
	
	Example 1:
	
	Input: ["cba","daf","ghi"]
	Output: 1
	Explanation: 
	After choosing D = {1}, each column ["c","d","g"] and ["a","f","i"] are in non-decreasing sorted order.
	If we chose D = {}, then a column ["b","a","h"] would not be in non-decreasing sorted order.
	Example 2:
	
	Input: ["a","b"]
	Output: 0
	Explanation: D = {}
	Example 3:
	
	Input: ["zyx","wvu","tsr"]
	Output: 3
	Explanation: D = {0, 1, 2}
	
	
	
	
	
	<문제>
	
	각 문자열의 길이가 같은 문자열들의 어레이가 다음과 같이 주어진다.
	
	["cba","daf","ghi"]
	
	이때, index 0번째부터 문자열의 길이까지의 인덱스를 n번째라고 하면,
	
	각 문자열의 0부터 n번째까지 인덱스만 뽑아서 보면 아래와 같이 된다.
	
	["c","d","g"], ["b","a","h"], ["a","f","i"]
	
	이 때, 예를들어 인덱스 0을 선택한다고 하면, 인덱스 0을 제외한 나머지 문자열 어레이 ["b","a","h"], ["a","f","i"]가 모두 사전적으로 순서에 맞으면 +1을 해준다.
	
	인덱스 0을 골랐을 때, ["b","a","h"]가 사전상 순서에 맞지 않으므로("b"가 "a"보다 사전상 늦게 나오는데 여기선 일찍나왔다), 인덱스 0은 넘어간다.
	
	인덱스 1을 골랐을 때, ["c","d","g"], ["a","f","i"] 모두 사전상 순서가 맞으므로 +1을 해주고,
	
	인덱스 2를 골랐을 때, ["c","d","g"], ["b","a","h"] 에서, 인덱스 0과 마찬사지로 ["b","a","h"]가 사전상 순서에 맞지않아 넘어간다. 
	
	결국 1을 반환해 주면 된다.
	
 */
package Greedy;

public class DeleteColumnstoMakeSorted944 {
	
	/*
	//<문제풀이1>
	
	//n번째 인덱스에 있는 알파벳들을 나열하여, 사전상 순서가 안맞는 경우의 합을 찾아 반환한다.
	
	//그러기 위해서 2중 for문으로 각 문자의 n번째 알파벳만 뽑아서, 앞뒤를 사전적 순서에 맞는지 보는데, 
	
	//보기위해 Character.compare()사용.
	
	//Character.compare("a","b") 은 -1이 나옴. a가 b보다 사전상 앞에 나오기 때문.
	
	//Character.compare("a","a") 은 0이 나옴. a와 a는 사전상 같은 위치에 있기 때문.
	
	//Character.compare("b","a") 은 1이 나옴. b는 a보다 사전상 뒤에 있기 때문.
	
	//Character.compare(뒷 알파벳, 앞 알파벳)을 했을때 0이나 -1이 나오면, 뒤 알파벳이 사전상 같거나 앞에 나온다는 뜻이기 때문에 break.
	
	//Runtime: 12 ms, faster than 48.24% of Java online submissions for Delete Columns to Make Sorted.
	//Memory Usage: 36.7 MB, less than 100.00% of Java online submissions for Delete Columns to Make Sorted.
	
	public static int minDeletionSize(String[] A) {
		int rst = 0;
		for(int i = 0; i < A[0].length(); i++) {
			for(int j = 1; j < A.length; j++) {
				if(Character.compare(A[j].charAt(i), A[j-1].charAt(i)) < 0){
                    rst++;
                    break;
                }
			}
		}
		return rst;
    }
    */
	
	//<문제풀이2 by cheng_coding_attack>
	
	//Character.compare()안쓰고 charAt으로 char에 담아서 단순 대소비교로 한 점 + 2중 for문 안에 for문이 for(String s : A)가 동작속도가 일반 for문보다 조금 빠름.
	
	//또한 눈여겨 볼 점은, char prev='a';한 후, prev--;을 함. 그러면 'a'였던게 '`'가 됨.
	
	//이건 어떤 알파벳과 비교해도 작음(-1)이 나오기 때문에 if(c<prev)의 첫번째 loop은 무조건 false가 나서 넘어감.
	
	//이게 true가 나오려면 뒤에 알파벳이 c보다 큰 경우, 그러니까 뒤의 알파벳이 앞의 알파벳보다 사전적으로 앞에 나오는 경우(우리가 찾기 원하는 경우)
	
	//이기 때문에 이 경우가 나오면 경우의수 +1해줌
	
	//Runtime: 7 ms, faster than 91.89% of Java online submissions for Delete Columns to Make Sorted.
	//Memory Usage: 37.1 MB, less than 100.00% of Java online submissions for Delete Columns to Make Sorted.
	
	public static int minDeletionSize(String[] A) {
        if (A.length == 0) return 0;
        int len = A[0].length();        
        int count = 0;
        for (int i = 0; i < len; i++) {
            char prev = 'a';
            prev--;
            for (String s : A) {
                char c = s.charAt(i);
                if (c < prev) {
                    count++;
                    break;
                }
                prev = c;
            }
        }
        return count;
    }
	
	public static void main(String[] args) {
		String[] A = {"cba","daf","ghi"};
//		String[] A = {"a","b"};
//		String[] A = {"zyx","wvu","tsr"};
//		String[] A = {"rrjk","furt","guzm"};
		System.out.println(minDeletionSize(A));
	}
}
