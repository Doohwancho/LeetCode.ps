package thirtyDayChallenge;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MinStackQuestion {
	
	/*
	//<문제풀이1>
	
	//겁나 느린데 일단 된다.
	
	//18 / 18 test cases passed.
	//Status: Accepted
	//Runtime: 1118 ms
	//Memory Usage: 45.9 MB
	class MinStack {

	    Stack st;
	    List<Integer> list;
	    public MinStack() {
	        st = new Stack();
	        list = new ArrayList<>();
	    }
	    
	    public void push(int x) {
	        st.push(x);
	        list.add(x);
	    }
	    
	    public void pop() {
	        st.pop();
	        list.remove(list.size()-1);
	    }
	    
	    public int top() {
	        return (int)st.peek();
	    }
	    
	    public int getMin() {
	        int minNum = Integer.MAX_VALUE;
	        for(int i : list) minNum = Math.min(minNum, i);
	        return minNum;
	    }
	}
	*/
	
	/*
	//<문제풀이2>
	
	//스택 안써도 될거같네
	
	//근데 이게 답은 아닌갑네. 겁나 느리니까.
	
	//getMin()때문에 느린거같은데

	//18 / 18 test cases passed.
	//Status: Accepted
	//Runtime: 949 ms
	//Memory Usage: 45.6 MB
	
	class MinStack {

	    List<Integer> list;
	    public MinStack() {
	        list = new ArrayList<>();
	    }
	    
	    public void push(int x) {
	        list.add(x);
	    }
	    
	    public void pop() {
	        list.remove(list.size()-1);
	    }
	    
	    public int top() {
	        return list.get(list.size()-1);
	    }
	    
	    public int getMin() {
	        int minNum = Integer.MAX_VALUE;
	        for(int i : list) minNum = Math.min(minNum, i);
	        return minNum;
	    }
	}
	*/

	/*
	//<문제풀이3>
	
	//이거지!!!!!!
	
	//1118ms -> 10ms
	
	//크..지려버렸따
	
	//이 문제의 핵심은 getMin()임. 딴건 스택을 쓰건 걍 arraylist쓰건 만들 수 있는데,
	
	//getMin()때문에 시간 오지게 먹음
	
	//그래서 가장 작은 숫자를 항상 업데이트 시켜줄 플러스 알파가 필요함
	
	//그게 TreeMap임
	
	//TreeSet, TreeMap 둘다 .add, .put으로 숫자 넣을때마다
	
	//오름차순으로 차곡차곡 넣어서, .first()하면 가장 작은 숫자 나옴
	
	//TreeSet말고 TreeMap쓴 이유는, TreeSet해봤는데 중복되는 숫자가 나오면
	
	//TreeSet이 기본적으로 Set이라 중복숫자를 무시해서 결과값이 이상하게 나옴
	
	//0,1,0 넣었는데 set이다 보니 0,1밖에 안들어감..
	
	//18 / 18 test cases passed.
	//Status: Accepted
	//Runtime: 10 ms
	//Memory Usage: 41 MB
	
	class MinStack {

	    List<Integer> list;
	    TreeMap<Integer, Integer> tr;
	    
	    public MinStack() {
	        list = new ArrayList<>();
	        tr = new TreeMap<>();
	    }
	    
	    public void push(int x) {
	        list.add(x);
	        tr.put(x, tr.getOrDefault(x, 0)+1);
	    }
	    
	    public void pop() {
	        int n = list.remove(list.size()-1);
	        if(tr.get(n) > 1){
	            tr.put(n, tr.get(n)-1);
	        }
	        else{
	            tr.remove(n); 
	        }
	        
	    }
	    
	    public int top() {
	        return list.get(list.size()-1);
	    }
	    
	    public int getMin() {
	        return tr.firstKey();
	    }
	}
	*/
	
	//<문제풀이4 by ivtoskov>
	
	//이것이 바로 인터뷰어가 원하는 정석적인 stack 디자인

	//18 / 18 test cases passed.
	//Status: Accepted
	//Runtime: 4 ms
	//Memory Usage: 41.5 MB
	
	class MinStack {

	    class Node{
	        int val;
	        int min;
	        Node next;
	        
	        Node(int val, int min){
	            this.val = val;
	            this.min = min;
	            this.next = null;
	        }
	        
	        Node(int val, int min, Node node){ //새 노드 추가할땐 기존노드를 새노드.next로 붙여
	            this.val = val;
	            this.min = min;
	            this.next = node;
	        }
	    }
	    private Node head;

	    public void push(int x) {
	        if(head == null){
	            head = new Node(x, x);
	        } else {
	            head = new Node(x, Math.min(head.min, x), head); //넣을때마다 min tracking하는거 지렸다
	        }
	    }

	    public void pop() {
	        head = head.next; //리턴값이 없어서 망정이지
	    }

	    public int top() {
	        return head.val;
	    }

	    public int getMin() {
	        return head.min;
	    }
	}
}
