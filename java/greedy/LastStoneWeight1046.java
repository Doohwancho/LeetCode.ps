/*
	We have a collection of rocks, each rock has a positive integer weight.
	
	Each turn, we choose the two heaviest rocks and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:
	
	If x == y, both stones are totally destroyed;
	If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
	At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)
	
	 
	
	Example 1:
	
	Input: [2,7,4,1,8,1]
	Output: 1
	Explanation: 
	We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
	we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
	we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
	we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of last stone.
	 
	
	Note:
	
	1 <= stones.length <= 30
	1 <= stones[i] <= 1000
	
	
	
	
	<문제>
	
	어레이가 다음과 같이 주어진다. [2,7,4,1,8,1]
	
	매 loop마다 가장 큰 숫자와 그다음으로 큰 숫자를 비교한다.
	
	비교할 때, 두 수가 같으면 둘을 없애고,
	
	어느 한 쪽이 다흔 한 수보다 더 크면, 두 수의 차이만 원레 어레이에 남기고 두 수를 남긴다. 마지막 수가 나올 때 까지.
	
	예를들어, [2,7,4,1,8,1]를 예시로 들자면,
	
	첫번째 loop) 가장 큰 두 수 7이랑 8은 서로 다른 수이므로, 그 차인 1을 남기고 두 수를 없앤다. 그러면 [2,4,1,1,1]이 된다.
	
	두번째 loop) 새로운 어레이에서 가장 큰 수 2이랑 4는 서로 다른 수 이므로, 그 차인 2를 남기고 두 수를 없앤다. 그러면 [2,1,1,1]이 된다.
	
	세번째 loop) 새로운 어레이에서 가장 큰 수는 2와 1고 서로 다른 수 이므로, 그 차인 1를 남기고 두 수를 없앤다. 그러면 [1,1,1]이 된다.
	
	네번째 loop) 새로운 어레이에서 가장 큰 수는 1와 1이고, 서로 같은 수 이므로, 둘은 없애면 [1]이 남는다. 마지막에 남은 수인 1을 반환한다.
 */

package Greedy;

import java.util.PriorityQueue;

public class LastStoneWeight1046 {
	
	
	/*
	//<Trial01>
	
	//먼저 Arrays.sort()로 오름차순 정렬 시켜놓고, 
	
	//맨뒤에 제일 큰 숫자부터 그 다음 숫자와 하나씩 비교하여, 같으면 두 숫자 모두 없어져야 하니까 i와 i-1번째를 0으로 리셋하고,
	
	//i번째가 i-1번째 수보다 더 크면 그 차이만큼 빼주어 i-1번째에 넣어주는 방법을 int[]의 가장 앞 index 0의 숫자만 빼고 나머지가 0일때까지 반복하면 되지 않을까?라는 생각을 해봤는데,
	
	//밑에 예시를 보면 두번째 loop에서 잘 보면 재정렬해서 [1,1,2]니까,
	
	//2-1을 먼저해서 [1,1]가 남아 가장 작은숫자 1을 반환해야 정상인데,
	
	//순서상 2가 index 0에 위치해 있고, 1 두개가 뒤에 있으니까, 1 두개가 숫자크기상 2보다 작지만 index위치가 뒤에있어서 
	
	//1이 먼저 처리되 둘다 없어져 버려 마지막 남은 2를 반환함.
	
	//문제 해결방법은, 매 loop이 끝났을 때, 다시 재정렬 해주어야 할 것 같음.
	
	//그런데 매 loop마다 재정렬 하면 메모리도 메모리대로 잡아먹고 너무 느려져 time limit exceeded 뜰 것으로 예상.
	 
	//수정) 매 loop이 끝날때 마다 재정렬을 한다고 해도, 하나의 loop안에 i-(i-1)을 한 값이 또 계산되어야 하는 경우를 처리를 못해 에러.
	
	//그렇다는건 i에서 i-1을 할때마다 arrays.sort()를 하라는 말인데 이 방법은 매우 비효율적이므로 다른 방법이 분명 있을 것임.
	
	
	//	1   3   4   5   7   8   10   10   
	//	*******************************
	//
	//	1   3   4   5   7   8   0   0   
	//	1   3   4   5   1   0   0   0   
	//	1   3   4   5   1   0   0   0   
	//	1   3   1   0   1   0   0   0   
	//	1   3   1   0   1   0   0   0   
	//	2   0   1   0   1   0   0   0  
	//	1번 for문이 돌았습니다.
	
	//	2   0   1   0   1   0   0   0   
	//	2   0   1   0   1   0   0   0   
	//	2   0   1   0   1   0   0   0   
	//	2   0   1   1   0   0   0   0   
	//	2   0   0   0   0   0   0   0   
	//	2   0   0   0   0   0   0   0   
	//	2번 for문이 돌았습니다.
	
	//	2   0   0   0   0   0   0   0   
	//	2   0   0   0   0   0   0   0   
	//	2   0   0   0   0   0   0   0   
	//	2   0   0   0   0   0   0   0   
	//	2   0   0   0   0   0   0   0   
	//	2   0   0   0   0   0   0   0   
	//	2   0   0   0   0   0   0   0   
	//	3번 for문이 돌았습니다.
	
	//	2(원래 답은 1이 나와야 함)
	
	public static int lastStoneWeight(int[] stones) {
		if(stones.length == 1) return stones[0];
		Arrays.sort(stones);
		
		boolean flag = true;
		
		while(flag) {
			int cnt = 0;
			
			for(int i = stones.length-1; i > 0; i--) {
				if(stones[i] == 0) {
					cnt++;
				}
				else if(stones[i]==stones[i-1]) {
					stones[i] = 0;
					stones[i-1] = 0;
					i--;
				}
				else if(stones[i] > stones[i-1]) {
					stones[i-1] = stones[i] - stones[i-1];
					stones[i] = 0;
				}
				else if(i == 1) {
					stones[0] = stones[0]-stones[i];
					stones[i] = 0;
				}
				
				if(i == 1 && cnt == stones.length-1) {
					flag = false;
				}
			}
		}
		return stones[0];
    }
	*/
	
	/*
	
	//<문제풀이0 by vluu>
	
	//trial01에서 생각한 "매우 비 효율적인 방법"이 정답이었음.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Last Stone Weight.
	//Memory Usage: 34.5 MB, less than 100.00% of Java online submissions for Last Stone Weight.
	
    public static int lastStoneWeight(int[] stones) {
        Arrays.sort(stones);
        for (int i = stones.length-1; i > 0; i--){
            stones[i-1] = stones[i]-stones[i-1];
            Arrays.sort(stones);   
        }
        return stones[0];
    }
	*/
    
	
	//<문제풀이1 by lee215>
	
	//위의 풀이보다 성능은 구린데 풀이방식이 약간 간지나서 추가함.
	
	//Runtime: 32 ms, faster than 29.32% of Java online submissions for Last Stone Weight.
	//Memory Usage: 34.3 MB, less than 100.00% of Java online submissions for Last Stone Weight.

    public static int lastStoneWeight(int[] A) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)-> b - a); 
        //PriorityQueue가 숫자가 낮을수록 우선순위가 높게 잡혀서 순서상 앞쪽에 배치가 됨.
        
        //.offer()로 값을 집어넣고, .poll()로 우선순위가 가장 높은 가장 첫번째 값을 뺄 수 있음.
        
        //(a, b)-> b - a는 자바의 람다식으로, 원래는 숫자가 낮을수록 우선순위가 높게 잡히지만, 이 식으로 인해 숫자가 높을수록 우선순위가 높게 잡히게 됨.
        
        //우선순위큐(PriorityQueue)는 우선순위를 비교하는 compare(T o1, T o2)를 오버라이드 해줄 필요가 있음.
        
        //기본 비교 메서드는 첫번째 인수가 두번째 인수보다 작거나 같거나 큰 경우 음수, 0 또는 양의 정수를 반환함.
        
        //아래는 기본 비교 메서드 예제임.
        
//    	static class PQsort implements Comparator<Integer> {
//    		 
//        	@override
//    		public int compare(Integer a, Integer b) {
//    			return a - b;
//    		}
//    	}
        
        //그런데 우선순위를 따질 때, 가장 작은 숫자가 가장 중요하다는게 기본인데, 가장 큰 숫자가 가장 중요하게끔 반대로 바꾸려니까,
        
        //원래 (a, b) -> a - b 인것을 (a,b)->b-a 로 바꿔준 것 뿐임.
        
        
        for (int a : A)  //가장 숫자가 큰것부터 재정렬해주고
            pq.offer(a);
        
        for (int i = 0; i < A.length - 1; ++i) //맨 첫번째(가장 큰 숫자)부터 그 다음으로 큰 숫자를 차근차근 비교하여, 둘이 같으면 상쇄, 다르면 그 차이를 다시 우선순위큐에 넣음
            pq.offer(pq.poll() - pq.poll());
        
        return pq.poll();
    }
	
	public static void main(String[] args) {
		int[] stones = {2,7,4,1,8,1};
//		int[] stones = {3,7,8,10};
//		int[] stones = {10,5,4,10,3,1,7,8};
//		int[] stones = {239,198,980,404,413,804,912,546,849,506,917,837,210,837,917,6,723,929,506,438,267,225,533,312,568,596,82,685,138,276};
		System.out.println(lastStoneWeight(stones));
	}
}




