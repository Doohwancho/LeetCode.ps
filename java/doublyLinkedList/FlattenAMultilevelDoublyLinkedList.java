package julyChallenge;

public class FlattenAMultilevelDoublyLinkedList {
	
	class Node {
	    public int val;
	    public Node prev;
	    public Node next;
	    public Node child;
	};
	
	//<Trial01>
	
	//값은 맞게 들어갔는데 doubly linked list가 아니라고 뜬다면? 
	
	//해결법 : **head.child가 있어서 일자로 붙이고 난 후, head.child = null 해주어야 leetcode가 doubly linked list라고 인식함.
	
	//씨발 난 천재야
	
	//Runtime: 3 ms
	//Memory Usage: 39.6 MB
	
    public Node flatten(Node head) {
        Node start = head;
        Node tail;
        Node tmp;
        
        while(head != null){ 
            if(head.child != null){ //child가 있으면
                tail = head.next;  //child가 아닌 원래 다음애들은 tail에 빼두고
                head.next = flatten(head.child); //recursive로 child부터 재시작. head.child가 또다른 child없이 끝까지 갔으면. head.next로 설정해줌.
                head.child = null; //이거 안하면 not a valid doubly linked list 에러 뜸
                while(head.next != null){ //포인터를 child에 맨 끝까지 가게 함. 이 때, 앞노드랑 뒷노드를 이어주면서 감.
                    tmp = head;
                    head = head.next;
                    head.prev = tmp;
                }
                head.next = tail; //맨 끝노드까지 갔으면, 아까 떼어놓은 원래 tail붙여줌
                if(tail != null) tail.prev = head; //tail이랑 head랑도 붙여줌
            }
            head = head.next;
        }
        
        return start;
    }
}
