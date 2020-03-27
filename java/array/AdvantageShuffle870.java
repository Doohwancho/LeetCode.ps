package array;

import java.util.Arrays;

public class AdvantageShuffle870 {

	/*
	//<Trial01>
	
	//문제는 A[i] > B[i]여야 한다는데,
	
	//tr.ceiling은 A[i] >= B[i]인걸 뽑아내서 실패
	
	//또한 TreeSet은 중복된 값을 처리 못해서 실패.. set이니까..
	
	public static int[] advantageCount(int[] A, int[] B) {
        TreeSet<Integer> tr = new TreeSet<>();
        for(int a : A) tr.add(a);
        
        int[] rst = new int[A.length];
        int i = 0;
        
        for(int b : B){
        	int num = tr.ceiling(b) != null ? tr.ceiling(b) : tr.pollFirst();
            rst[i++] = num;
            tr.remove(num);
        }
        return rst;
    }
    */
	
	/*
	//<문제풀이1 by lee215>
	
	//treeset대신 treemap쓰네
	
	//treemap엔 treeset에 메소드 .ceiling()과는 다르게
	
	//greater만 되는 메소드인 .higherKey가 있구나
	
	//Runtime: 94 ms, faster than 13.23% of Java online submissions for Advantage Shuffle.
	//Memory Usage: 42.5 MB, less than 83.33% of Java online submissions for Advantage Shuffle.
    public static int[] advantageCount(int[] A, int[] B) {
        TreeMap<Integer, Integer> m = new TreeMap<>();
        for (int i : A) m.put(i, m.getOrDefault(i, 0) + 1);
        int[] res = new int[A.length];
        for (int i = 0; i < A.length; ++i) {
            Integer x = m.higherKey(B[i]);
            if (x == null) x = m.firstKey();
            m.put(x, m.get(x) - 1);
            if (m.get(x) == 0) m.remove(x);
            res[i] = x;
        }
        return res;
    }
    */
	
	//<문제풀이2 by WHJ425>
	
	//explaination for (a, b) -> (B[a] - B[b]) ?
	
	//same as compare();
	
	//new Comparator<Integer>() {
	//    @Override
	//    public int compare(int a, int b) {
	//        return B[a] - B[b];
	//    }
	//}
	
	//Runtime: 28 ms, faster than 89.23% of Java online submissions for Advantage Shuffle.
	//Memory Usage: 41.9 MB, less than 100.00% of Java online submissions for Advantage Shuffle.
	public static int[] advantageCount(int[] A, int[] B) {
        Arrays.sort(A);
        
        Integer[] bIdxArr = new Integer[B.length];
        for (int i = 0; i < bIdxArr.length; i++) {
            bIdxArr[i] = i;
        }
        Arrays.sort(bIdxArr, (a, b) -> (B[a] - B[b])); //B를 오름차순 순서대로 인덱스값 표기

        int[] res = new int[A.length];
        
        for (int idxA = 0, idxB = 0, r = res.length - 1; idxA < A.length; idxA++) {
            if (A[idxA] > B[bIdxArr[idxB]]) {
                res[bIdxArr[idxB]] = A[idxA]; //res를 순서대로 0부터 차곡차곡 쌓는게 아니라 B의 오름차순 인덱스메 맞게 A를 저장
                idxB++;
            } else {
                res[bIdxArr[r]] = A[idxA]; //만약 B보다 더 큰 A가 없으면 맨 뒤부터 쑤셔넣음. 어짜피 순서 노상관해도 괜찮다고 했으니까.
                r--;
            }
        }
        return res;
    }
	
	
	public static void main(String[] args) {
		int[] A = {12,24,8,32};
		int[] B = {13,25,32,11};
		System.out.println(advantageCount(A,B));
	}
}
