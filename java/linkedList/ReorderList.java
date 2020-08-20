package augustChallenge;

import java.util.Stack;

public class ReorderList {

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

	// stack

	// Runtime: 2 ms
	// Memory Usage: 41.8 MB

	public void reorderList(ListNode head) {
		if (head == null) return;
			
		ListNode rst = head;
		ListNode start = head;

		Stack<ListNode> st = new Stack(); //stack에 순서대로 담은 다음,
		while (head != null) {
			st.add(head);
			head = head.next;
		}

		boolean flag = st.size() % 2 == 1;
		int size = st.size() / 2;
		while (size > 0) {
			ListNode tmp = start.next;
			start.next = st.pop(); //stack.pop()으로 뒤에있는애 띄어서
			start = start.next; //붙여주고
			start.next = tmp; //1->4 다음에 올 2를 붙여주고
			start = start.next; //한칸 땡겨서 1->4->2로 만듬.
			size--;
		}
		if (flag) {
			start.next = st.pop(); //홀수개 있으면 맨 마지막에 남는 꼬다리 처리
			start = start.next;
		}
		start.next = null; //이거 안하면 cycle돈다고 지랄함

		head = rst; //return void이기 때문에 head에 원상복귀
	}
	
	
	//<문제풀이2 by jeantimex>
	
	//토끼와 거북이를 써서 반띵하고,
	
	//뒤에 반을 뒤집고
	
	//첫번째 반이랑 두번째 반이랑 순서대로 merge
	
	//Runtime: 2 ms
	//Memory Usage: 43.4 MB
	
    ListNode secondStart;
    
    public void reorderList(ListNode head) {
        if(head == null) return;
        ListNode start = head;
        secondStart = head;
        
        //split into two parts
        ListNode prev = head; //tail of 1st half
        ListNode slow = head; //head of 2nd half
        ListNode fast = head; //tail of 2nd half
        
        while(fast != null && fast.next != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        
        //reverse the 2nd half
        reverse(slow);
        
        //merge 1st half & 2nd half
        merge(start, secondStart);
    }
    
    //recursive
    private ListNode reverse(ListNode head){
        if(head.next != null){
            ListNode tmp = head;
            ListNode tmp2 = reverse(head.next);
            tmp2.next = tmp;
            tmp2 = tmp2.next;
            tmp2.next = null; //마무리를 null로 확실히 해줘야지 merge에서 fast != null을 사용 가능
            return tmp2;
        } else{
            secondStart = head;
            return head;    
        } 
    }
    
    private void merge(ListNode start, ListNode fast){
        ListNode prev = start;
        while(start != null){
            ListNode tmp = start.next;
            start.next = fast;
            fast = fast.next;
            start = start.next;
            start.next = tmp;
            prev = start;
            start = start.next;    
        }
        //홀수개라면, 가장 가운데꺼가 fast에 남음
        if(fast != null){
            prev.next = fast;
            prev = prev.next;
            prev.next = null;
        }
    }
}
