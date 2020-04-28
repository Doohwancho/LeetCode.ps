package thirtyDayChallenge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class FirstUniqueNumber {
	
	/*
	//<Trial01>
	
	//생성자랑 add메서드에 문제있음.
	
	//기존 q에 있으면 지우고 없으면 넣으라고 했는데,
	
	//같은 숫자가 3번 나온다면?
	
	//넣고지우고넣으니까 있다고 나옴. 원랜 2번 이상나와서 없다고 나와야 하는데.
	  
	//**문제는 나중에, 중복 출현시 map의 value값을 null로 만들어 버림으로서 해결함. 문제풀이1참조.
	
	static class FirstUnique {

	    Queue<Integer> q;
	    
	    public FirstUnique(int[] nums) {
	        q = new LinkedList<>();
	        for(int n : nums){
	            if(q.contains(n)){
	                q.remove(n);
	            } else{
	                q.add(n);
	            }
	        }
	    }
	    
	    public int showFirstUnique() {
	    	if(q.isEmpty()) return -1;
	        return q.peek();
	    }
	    
	    public void add(int value) {
	        if(q.contains(value)){
	            q.remove(value);
	        } else {
	            q.add(value);
	        }
	    }
	    
	}
	*/
	
	/*
	//<Trial02 memory limit exceeded>
	
	//아이고 머리를 써라 친구야 야매털생각하지말고
	  
	 
	class FirstUnique {
	    Queue<Integer> q;
	    int[] dup;
	    
	    public FirstUnique(int[] nums) {
	        q = new LinkedList<>();
	        dup = new int[100000000];
	        
	        for(int n : nums){
	            dup[n]++;
	        }
	        for(int n : nums){
	            if(dup[n]==1){
	                q.add(n);
	            }
	        }
	        
	    }
	    
	    public int showFirstUnique() {
	        if(q.isEmpty()) return -1;
	        return q.peek();
	    }
	    
	    public void add(int value) {
	        if(dup[value]==0){
	            dup[value]++;
	            q.add(value);
	        }else if(dup[value]==1){
	            dup[value]++;
	            q.remove(value);
	        } 
	    }
	 }
    */
	
	/*
	//<Trial03 - Time limit exceeded>
	
	//map써도 안돼네
	
	class FirstUnique {

	    Queue<Integer> q;
	    Map<Integer, Integer> m;
			
	    
	    public FirstUnique(int[] nums) {
	        q = new LinkedList<>();
	        m  = new HashMap<>();
	        for(int n : nums){
	            m.put(n, m.getOrDefault(n, 0)+1);
	        }
	        for(int n : nums){
	            if(m.get(n)==1){
	                q.add(n);
	            }
	        }
	    }
	    
	    public int showFirstUnique() {
	        if(q.isEmpty()) return -1;
	        return q.peek();
	    }
	    
	    public void add(int value) {
	        if(m.get(value) != null){
	            q.remove(value);
	        } else {
	            m.put(value, 1);
	            q.add(value);
	        }
	    }
	}
	*/
	
	//<문제풀이1 by Knowledge Center>
	
	//17 / 17 test cases passed.
	//Status: Accepted
	//Runtime: 111 ms
	//Memory Usage: 78.2 MB
	
	class FirstUnique {
	    
	    private class ListNode{
	        ListNode _next;
	        ListNode _prev;
	        int _val;
	        public ListNode(int val){
	            _val = val;
	            _next = null;
	            _prev = null;
	        }
	    }
	    
	    private class MyDLL{ //DLL stands for Doubly Linked List
	        private ListNode _head;
	        private ListNode _tail;
	        
	        public MyDLL(){
	            _head = new ListNode(-1); //head는 시작,
	            _tail = new ListNode(-1); //tail은 끝임. head.next는 첫 노드고, tail.prev는 마지막 노드임.
	            _head._next = _tail;
	            _tail._prev = _head;
	        }
	        
	        public ListNode getFirst(){
	            return _head._next;
	        }
	        
	        public boolean isEmpty(){
	            return _head._next == _tail;
	        }
	        
	        public void remove(ListNode node){
	            ListNode prev = node._prev;   //일단 파라미터로 받은 노드의 앞과 뒤 노드를 안아내
	            ListNode next = node._next;
	            prev._next = next;          //앞 노드의 next를 뒷노드로
	            next._prev = prev;          //뒷 노드의 prev를 앞노드로하면, 사이에 껴있던 기존에 노드가 사라짐. 근데 메모리상에는 안사라지잖아? 어씨 왠지 손해보는기분
	        }
	        
	        public ListNode append(int num){
	            ListNode node = new ListNode(num); //일단 노드끼리 연결시켜주는거니까, 노드를 만들어줘.
	            ListNode prev = _tail._prev;  //tail의 앞에 노드는 맨 뒤에 노드겠지?
	            prev._next = node;         //맨 뒤의 노드의 다음을 파라미터로 받은 노드로 정해줌
	            node._prev = prev;         //그리고 그 노드에 prev를 방금 노드로 정함으로써 서로 이어줌.
	            node._next = _tail;        //마찬가지로 위에한거 또함. 이제 맨 뒤에 노드가 된 애의 next를 tail로 해줌
	            _tail._prev = node;        //그리고 당연히 tail을 맨 뒤에 노드와 붙여줌
	            
	            return node;
	        }
	    }
	    
	    private MyDLL _keys;
	    private Map<Integer, ListNode> _map;
	    
	    public FirstUnique(int[] nums) {
	        _keys = new MyDLL();
	        _map = new HashMap<>();
	        
	        for(int n : nums){
	            add(n);        //아래에 구현된 add메서드로 DLL과 map에 동시에 넣음. ** 생성자에서 메서드 바로 쓸 수 있다...!
	        }
	    }
	    
	    public int showFirstUnique() {
	        if(_keys.isEmpty()){
	            return -1;
	        }
	        return _keys.getFirst()._val;
	    }
	    
	    public void add(int value) {
	        if(_map.containsKey(value)){
	            if(_map.get(value) != null){
	                _keys.remove(_map.get(value));
	                _map.put(value, null);          //2번 이상 나타난 값들의 value는 모두 null값으로 처리한다.
	            }
	        }
	        else{
	            ListNode node = _keys.append(value);
	            _map.put(value, node);
	        }
	    }
	}
    
//	public static void main(String[] args) {
//		int[] test = {2,3,5};
//		FirstUnique firstUnique = new FirstUnique(test);
//		System.out.println(firstUnique.showFirstUnique()); // return 2
//		firstUnique.add(5);            // the queue is now [2,3,5,5]
//		System.out.println(firstUnique.showFirstUnique()); // return 2
//		firstUnique.add(2);            // the queue is now [2,3,5,5,2]
//		System.out.println(firstUnique.showFirstUnique()); // return 3
//		firstUnique.add(3);            // the queue is now [2,3,5,5,2,3]
//		firstUnique.showFirstUnique(); // return -1
//		
//	}

}
