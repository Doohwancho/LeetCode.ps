package TwoPointers;




public class PalindromeLinkedList234 {
	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
	
	/*
	//<Trial01>
	
	//만약 [1,2,2,1]처럼 짝수개가 좌우대칭이면, [1,2]까지는 stack쌓은 다음 [2,1]은 stack.pop()해서 값이 같을때마다 지워주면 되는데,
	
	//[1,0,1]처럼 홀수개면 맨 가운데 0을 처리할 방법이 없어서 에러.

	public boolean isPalindrome(ListNode head) {
		Stack<Integer> st = new Stack<>();
		int size = 0;
		while (head != null) {
			if (!st.isEmpty() && st.peek() == head.val) {
				st.pop();
			} else {
				st.add(head.val);
			}
			head = head.next;
			size++;
		}
		return size == 1 ? true : st.isEmpty();
	}
	*/
	
	/*
	//<문제풀이1>
	
	//deque는 stack과 queue의 장점을 합친것으로, 앞,뒤 모두에서 뽑을 수 있음.
	
	//deque에 값을 넣고, 앞,뒤 양쪽에서 뽑아 값을 비교하면, 좌우대칭인지 알 수 있음.
	
	//Runtime: 3 ms, faster than 24.84% of Java online submissions for Palindrome Linked List.
	//Memory Usage: 40.5 MB, less than 98.78% of Java online submissions for Palindrome Linked List.
    public boolean isPalindrome(ListNode head) {
        Deque<Integer> dq = new ArrayDeque<>();
        while(head != null){
            dq.add(head.val); //Deque선언시 ArrayDeque와 LinkedList중 .add가 더 빠른 ArrayDeque사용
            head = head.next;
        }
        while(dq.size() > 1){ //[1,0,1] 에서 가운데 0이 무슨 숫자든 상관없이 좌우대칭이기 때문에 >1을 넣어줌.
            if((int)dq.pollFirst() != (int)dq.pollLast()) return false;
        }
        return true;
    }
    */
	
	//<문제풀이2>
	
	//설명 : https://leetcode.com/problems/palindrome-linked-list/discuss/64501/Java-easy-to-understand
	
	//reverse부분 모르겠다. 머리 터질듯.
	
    
    //[-16557,-8725,-29125,28873,-21702,15483,-28441,-17845,-4317,-10914,
    // -10914,-4317,-17845,-28441,15483,-21702,28873,-29125,-8725,-16557]

//    before reverse1  -10914
//    before reverse2  -4317
//    before reverse3  -17845
    
//    prev: null next: -4317 prev: -10914(head) head: -4317(head.next) head.next: -17845(head.next.next)
//    next: -17845 head.next: -10914 prev: -4317 head: -17845 head.next: -28441
//    next: -28441 head.next: -4317 prev: -17845 head: -28441 head.next: 15483
//    next: 15483 head.next: -17845 prev: -28441 head: 15483 head.next: -21702
//    next: -21702 head.next: -28441 prev: 15483 head: -21702 head.next: 28873
//    next: 28873 head.next: 15483 prev: -21702 head: 28873 head.next: -29125
//    next: -29125 head.next: -21702 prev: 28873 head: -29125 head.next: -8725
//    next: -8725 head.next: 28873 prev: -29125 head: -8725 head.next: -16557
//    next: -16557 head.next: -29125 prev: -8725 head: -16557
//     head.next: -8725 prev: -16557
    
//    after reverse1   -16557
//    after reverse2   -8725
//    after reverse3  -29125
	
	//Runtime: 1 ms, faster than 99.49% of Java online submissions for Palindrome Linked List.
	//Memory Usage: 41 MB, less than 65.85% of Java online submissions for Palindrome Linked List.
	
	
    public static boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast != null) { // odd nodes: let right half smaller
            slow = slow.next;
        }
        System.out.println("before reverse->  slow.val: "+ slow.val+"  slow.next.val: "+slow.next.val);
        slow = reverse(slow);
        System.out.println("After reverse->   slow.val:  "+ slow.val+"  slow.next.val:  "+slow.next.val);
        fast = head;
        
        while (slow != null) {
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }
    
    public static ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next; 
            head.next = prev; 
            prev = head; 
            head = next;
            if(prev != null) System.out.print(" prev: "+prev.val); 
            if(head != null) {
            	System.out.print(" head: "+head.val);
            	
            	if(head.next != null) {
            		System.out.print(" head.next: "+head.next.val);
            	}
            }
            if(next != null) System.out.print(" next: "+next.val);
            System.out.println();
        }
        
        return prev; 
    }
    
    public static void main(String[] args) {
    	ListNode head1 = new ListNode(1);
    	ListNode head2 = new ListNode(2);
    	ListNode head3 = new ListNode(3);
    	ListNode head4 = new ListNode(4);
    	ListNode head5 = new ListNode(5);
    	ListNode head6 = new ListNode(6);
//    	ListNode head7 = new ListNode(7);
    	
    	head1.next = head2;
    	head2.next = head3;
    	head3.next = head4;
    	head4.next = head5;
    	head5.next = head6;
//    	head6.next = head7;
    	
		
    	isPalindrome(head1);
	}
}

