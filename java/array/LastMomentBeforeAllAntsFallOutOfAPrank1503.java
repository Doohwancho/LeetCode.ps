package array;

public class LastMomentBeforeAllAntsFallOutOfAPrank1503 {
	
	//<문제풀이1>
	
	//미친
	
	//모든 ant가 같아서 구분 안되니까, 부딫혀서 다른방향으로 간다는걸 무시하고
	
	//한방향으로만 단다고 생각하면, 종점으로부터 젤 멀리있는 애의 거리만 알면 되지.
	
	//내가 쥰나 복잡하게 생각했었던게 
	
	//[left, x, x, left, x, right]
	
	//같이 나오면, 두번째 left가 right에 한번 
	
	//팅!
	
	//하고 팅겨서 오다가 첫번째 left의 또
	
	//팅!
	
	//하면, game of life처럼 매 state를 int[]a, int[]b 에 왔다갔다하면서 표시해줘야 하나 고민했었는데
	
	//안그대로 되는게
	
	//[left, x, x, left, x, right]  = 1
	
	//[x, left, x, left, 팅!, right] = 2
	
	//[x, left, 팅!, left, x, x] = 3,4 (이때 왼쪽 left가 오른쪽 left로 옮겨갔다고 생각하셈)
	
	//[left, x, x, x, left, x] = 5
	
	//[x, x, x, x, x, left] = 6
	
	//iterate = 6
	
	//이 팅겨져 나가는거 무시하고 일직선 간다고 생각해도 똑같음
	
	//https://leetcode.com/problems/last-moment-before-all-ants-fall-out-of-a-plank/discuss/720313/C%2B%2B-Python-Java-Beautiful-Visual-Explanation
	
	//여기 그림보면 이해가 잘 됨
	
	
	//time O(n)
	//space O(1)

	//Runtime: 1 ms, faster than 100.00% of Java online submissions for Last Moment Before All Ants Fall Out of a Plank.
	//Memory Usage: 40.2 MB, less than 100.00% of Java online submissions for Last Moment Before All Ants Fall Out of a Plank.
    public int getLastMoment(int n, int[] left, int[] right) {
        int res = 0;
        for (int i: left)
            res = Math.max(res, i);
        for (int i: right)
            res = Math.max(res, n - i);
        return res;
    }
}
