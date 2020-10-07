package october;

public class RotateList {

	public class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	// <문제풀이1>
	
	
	
	//100만번이면 100만번 다 돌필요가 없잖어. k/head.size()만큼 공회전 하는거니까 %해줘서 나머지만 돌아.
	
	//step01) k % head.size() 해서 몇번째 부터 돌아야 하는지 파악
	
	//step02) i번째 이후로 똑 떼서 첫번째에 붙여야 하니까, i번째까지 이동함. i+1번째 애는 똑 띠어서 첫번째 애한테 붙여줄거임.
	
	//step03) i+1번째 애가 null이란 말은, 똑 떼서 첫번째 애한테 붙여줄 애가 없단 말이니까, 바로 head를 반환함.
	
	//step04) i.next = null. 끝을 만들어주고, i+1얘는 끝까지 간 다음, 맨 첫번째 애랑 붙여줌. end.next = first 해줌.
	//		  tail.next.next ... 해서 끝까지 갈 수도 있지만, 아까 head.size()구할때 이미 끝까지 간 head를 이용해서 head.next = first; 해줌

	//Runtime: 0 ms
	//Memory Usage: 38 MB

	public ListNode rotateRight(ListNode head, int k) {
		if (head == null) return null;
		
		//step01)
		int len = 1;
		ListNode first = head;
		ListNode second = head;

		while (head != null && head.next != null) {
			len++;
			head = head.next;
		}
		int idx = len - (k % len) - 1;
		
		//step02)
		while (idx > 0) {
			second = second.next;
			idx--;
		}
		ListNode tail = second.next;
		
		//step03)
		if (tail == null) return first;
		
		//step04)
		second.next = null;
		head.next = first;

		return tail;
	}

}
