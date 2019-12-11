package TwoPointers;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle141 {

	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	
	/*
	//<문제풀이1>
	
	//Runtime: 5 ms, faster than 6.79% of Java online submissions for Linked List Cycle.
	//Memory Usage: 37.9 MB, less than 84.29% of Java online submissions for Linked List Cycle.

    public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null) return false;
        Set set = new HashSet<>();
		while (head != null) {
            if(set.contains(head)) return true; //이전에 등장했던 노드값의 주소값이 재등장하면 이어졌다는 말이므로 true반환
            set.add(head);
			head = head.next;
		}
		return false;
	}
    */
	
	//<문제풀이2 by jeantimex>
	
	//빨리 달리는 토끼와 느리게 달리는 거북이를 만들어서 원형 경기장에 경주를 시키는데,
	
	//만약 원형 경기장이 아니거나 끝이 유한한 길이라면, circular linked list가 아니므로 false를 반환한다.
	
	//만약 원형 경기장이라면, 빨리달리는놈이 언젠가 느리게 달리는놈을 따라잡으므로, 이 경우엔 true를 반환한다.
	
	//문제풀이1에서 고민했던 부분이 어떻게 linked list의 명목상 끝부분을 알까 고민했는데 
	
	//이 똑똑이는 이렇게 푸네
    
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Linked List Cycle.
    //Memory Usage: 37.2 MB, less than 100.00% of Java online submissions for Linked List Cycle.
    
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) { //
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true; 
        }
        return false;
    }
}
