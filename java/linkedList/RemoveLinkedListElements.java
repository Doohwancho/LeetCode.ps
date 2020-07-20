package julyChallenge;

public class RemoveLinkedListElements {

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

	// <Trial01>

	//[1],1같은거나 [1,1,1],1같은거나오면 안됨.
	
	//head.next를 바꾸는게 아니라 head.val == val하면 head를 바꿔야 겠음. 
	
	//prev쓰면 되려나

	public ListNode removeElements(ListNode head, int val) {
		if (head == null)
			return null;
		ListNode start = head;

		while (head != null && head.next != null) {
			if (head.next.val == val) {
				if (head.next != null && head.next.next != null) {
					head.next = head.next.next;
				} else {
					head.next = null;
				}
			}
			head = head.next;
		}

		return start.next == null && start.val == val ? null : start;
	}
	
	
	//<문제풀이1>
	
	//head.next == val, change head.next는, 만약 head의 첫번째부터 remove해야 하는 상황이 오면 안됨.
	
	//그래서 head.next가 아니라 head에서 바로 고쳐야됨.
	
	//근데 이건 doubly linked list가 아니라 singly linked list이기 때문에,
	
	//previous node를 개별 생성해서 관리해 줄 필요가 있음.
	
	//첫번째부터 지랄하는건 if(prev==null)로 따로 처리해줌
	
	//O(n)
	
	//Runtime: 1 ms
	//Memory Usage: 46.6 MB
	
    public ListNode removeElements(ListNode head, int val) {
        ListNode start = head;
        ListNode prev = null;
        
        while(head != null){
            if(head.val == val){
                if(prev == null){//[1,1,1],1
                    head = head.next;
                    start = head; //[1,2],1
                    continue;
                } else {
                    prev.next = head.next;
                    head = prev;
                }
            } 
            prev = head;
            head = head.next;
        }
        
        return start;
    }
    
    
    //<문제풀이2 by mscho147>
    
    //아 얘는 prev안쓰고 애초에 시작점을 head한칸 전부터 했구나
    
    //그래서 cur.next.val == val를 스무스하게 쓸 수 있었음.
    
    //Runtime: 0 ms
    //Memory Usage: 40.2 MB
    
    public ListNode removeElements(ListNode head, int val) {
        ListNode node = new ListNode(-1);
        node.next = head;
        ListNode cur = node;
        while(cur.next != null){
            if(cur.next.val == val){
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return node.next;
    }
}
