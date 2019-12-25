package Array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RevealCardsInIncreasingOrder950 {
	
	/*
	//<문제풀이1>
	
	//패턴발견.
	
//	Input: [17,13,11,2,3,5,7]
//	Output: [2,13,3,11,5,17,7]
//	Explanation: 
//	We get the deck in the order [17,13,11,2,3,5,7] (this order doesn't matter), and reorder it.
//	After reordering, the deck starts as [2,13,3,11,5,17,7], where 2 is the top of the deck.
//	We reveal 2, and move 13 to the bottom.  The deck is now [3,11,5,17,7,13].
//	We reveal 3, and move 11 to the bottom.  The deck is now [5,17,7,13,11].
//	We reveal 5, and move 17 to the bottom.  The deck is now [7,13,11,17].
//	We reveal 7, and move 13 to the bottom.  The deck is now [11,17,13].
//	We reveal 11, and move 17 to the bottom.  The deck is now [13,17].
//	We reveal 13, and move 17 to the bottom.  The deck is now [17].
//	We reveal 17.
//	Since all the cards revealed are in increasing order, the answer is correct.
	
	//deck에서 가장 큰 숫자부터 점차 작은 숫자대로 for문 돌면서
	
	//처음 두번(가장 큰 값 17, 13)은 deck에 push로 밀어넣고
	
	//나머지는 가장 뒤에 있는 숫자를 앞으로 옮긴 후, 새로운 숫자를 push해주는 패턴.
	
	//double ended queue를 쓴 이유는, push와 poll둘 다 필요했기 때문.
	
	//Runtime: 2 ms, faster than 95.56% of Java online submissions for Reveal Cards In Increasing Order.
	//Memory Usage: 39.2 MB, less than 14.29% of Java online submissions for Reveal Cards In Increasing Order.
	
    public int[] deckRevealedIncreasing(int[] deck) {
        if(deck.length < 2) return deck;
        Arrays.sort(deck);
        Deque<Integer> dq = new ArrayDeque<>();       
        for(int i = deck.length-1; i >= 0; i--){
            if(i > deck.length-3){
                dq.push(deck[i]);
            } else {
                int tmp = dq.pollLast();
        	    dq.push(tmp);
        	    dq.push(deck[i]);    
            }
        }
        int[] rst = new int[deck.length];
        for (int i = 0; i < deck.length; i++) {
            rst[i] = dq.poll();
        }
        return rst;
    }
    */
    
	//<문제풀이2 by lee215>
	
	//17-13-11-7-5-3-2
	
//	[17]
//	[17, 13]
//	[13, 17, 11]
//	[17, 11, 13, 7]
//	[11, 13, 7, 17, 5]
//	[13, 7, 17, 5, 11, 3]
//	[7, 17, 5, 11, 3, 13, 2]
	
	//역순으로 배열에 넣기
	
	//Runtime: 2 ms, faster than 95.56% of Java online submissions for Reveal Cards In Increasing Order.
	//Memory Usage: 38.7 MB, less than 14.29% of Java online submissions for Reveal Cards In Increasing Order.
    
    public int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        Arrays.sort(deck);
        Queue<Integer> q = new LinkedList<>();
        for (int i = n - 1; i >= 0; --i) {
            if (q.size() > 0) q.add(q.poll());
            q.add(deck[i]);
        }
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            res[i] = q.poll();
        }
        return res;
    }
    
}
