package juneChallenge;

public class DeleteNodeInALinkedList {

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	// <문제풀이1>

	// 어씨 head어따팔아먹었냐

	// 1->2->3->4 인데, node가 2라고 해보자,
	
	//node.val = node.next.val하면
	
	//1->3->3->4 가 되겠지?
	
	//그리고 3번째 node를 버리는거임
	
	//node.next = node.next.next 하면
	
	//1->3->4
	
	//가 되서 2를 없앨 수 있음.
	
	//근데 보통 이런문제는 head를 주는데.. 예제 input에서도 head가 나와있구만
	
	//어이없네
	
	public void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
	}
}
