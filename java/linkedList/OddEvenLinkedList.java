package mayChallenge;

public class OddEvenLinkedList {

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
	
	//이거지요~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	//head에 1,3,5,... 홀수 넣고
	
	//even에 2,4,6,... 짝수 넣고
	
	//나중에 head.next = even으로 둘이 이을라니까
	
	//even의 포인터가 2가 아니고 6에가있네?
	
	//그럼 even이 2부터 시작하니까, 처음 시작하는곳을 가르키는 또다른 변수 evenStart를 만듬.
	
	//그래서 head.next = evenStart으로 해서 submit하려니까
	
	//head가 1부터 시작하는게 아니라 5(홀수의 끝)부터 시작하네?
	
	//그래서 1을 가르키는 headStart을 만들고, head.next = evenStart한 후, 가장 처음인 headStart를 리턴함
	
	//근데 맨 마지막에 null로 안끝나면 에러떠서, even.next = null로 마무리 치고 이어줌.

	// Runtime: 0 ms
	// Memory Usage: 39.1 MB

	public ListNode oddEvenList(ListNode head) {
		if(head == null || head.next == null || head.next.next == null) return head;
        
		ListNode headStart = head;
        ListNode even = null;
        ListNode evenStart = null;
        
        while(head.next != null && head.next.next != null){
            if(even == null){
                even = head.next;
                evenStart = even;
                 
            } else {
                even.next = head.next;    
                even = even.next;
            }
            if(head.next.next != null){
                head.next = head.next.next;
                head = head.next;
            } 
        }
        
        if(head.next != null){
            even.next = head.next;
            even.next.next = null;
        } else{
            even.next = null;    
        }
        
        head.next = evenStart;
        return headStart;
	}
	
	
	//<문제풀이2 by caikehe>
	
	//같은 방식인데 훨씬 깔끔함.
	
	public ListNode oddEvenList(ListNode head) {
	    if (head == null || head.next == null) {
	        return head;
	    }
	    ListNode p1 = head, p2 = head.next, pre = p2;
	    while (p2 != null && p2.next != null) {
	        p1.next = p2.next;
	        p1 = p1.next;
	        p2.next = p1.next;
	        p2 = p2.next;
	    }
	    p1.next = pre;
	    return head;
	}
	
	//<문제풀이3 by StefanPochmann>
	
	//짜잘한 곳에 xor쓰네
	
	//이게 바로 고인물이제
	
	//방식은 같음
	
	public ListNode oddEvenList(ListNode head) {
	    ListNode odd = new ListNode(0), even = new ListNode(0), tail[] = {odd, even};
	    for (int i=0; head != null; i^=1) {
	        tail[i] = tail[i].next = head;
	        head = head.next;
	    }
	    tail[0].next = even.next;
	    tail[1].next = null;
	    return odd.next;
	}
}
