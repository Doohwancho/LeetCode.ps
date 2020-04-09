package thirtyDayChallenge;

public class MiddleOfTheLinkedList {

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	//<문제풀이1>
	
	//1칸씩 다음 노드로 이동하는 slow와 2칸씩 다다음 노드로 이동하는 fast를 만들고,
	
	//토끼와 거북이처럼 경주 돌리는데
	
	//토끼가 골인지점에 왔을땐, 거북이는 토끼의 속도의 반이니까
	
	//linkedlist에 반이지 않을까?
	
	//15 / 15 test cases passed.
	//Status: Accepted
	//Runtime: 0 ms
	//Memory Usage: 37.4 MB
	
	public ListNode middleNode(ListNode head) {
		ListNode slow = head;
		ListNode fast = head.next;

		while (fast != null) {
			slow = slow.next;
			if (fast.next != null) {
				fast = fast.next.next;
			} else {
				return slow;
			}
		}
		return slow;
	}
	
	/*
	
	//같은 로직인데 이게 더 깔끔하다.
	
	public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;    
        }
        return slow;
    }
	 */
}
