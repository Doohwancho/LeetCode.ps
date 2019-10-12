package Stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ImplementStackUsingQueue225 {
	
	
	//<문제풀이 by StefanPochmann>
	
	//Runtime: 42 ms, faster than 80.92% of Java online submissions for Implement Stack using Queues.
	//Memory Usage: 33.9 MB, less than 91.67% of Java online submissions for Implement Stack using Queues.
	
	class MyStack {

	    //poll, peek, add, remove
	    /** Initialize your data structure here. */
	    public MyStack() {
	    }
	   
	    Queue<Integer> queue = new LinkedList<>(); //Stack은 선언시, Stack<type> stack = new Stack<>();으로 선언했는데,
	    										   //queue는 LinkedList로 선언하는 것이 차이점.
	    
	    /** Push element x onto stack. */
	    public void push(int x) {
	        queue.add(x);
	        for (int i=1; i<queue.size(); i++) //queue.remove()하면 가장 첫번째 값이 나오면서 지워짐. 역순으로 다시 담는 것.
	            queue.add(queue.remove());
	    }
	    
	    /** Removes the element on top of the stack and returns that element. */
	    public int pop() {
	        return queue.remove(); //push()에서 역순으로 이미 담았기 때문에, .remove()로 가장 앞에 값을 빼는게 가장 뒤에것을 빼는거와 같음.
	    }
	    
	    /** Get the top element. */
	    public int top() {
	        return queue.peek();  //pop()와 마찬가지로, push()에서 이미 역순으로 담아서, .peek()로 가장 앞에 원소를 보는게 가장 뒤에 원소를 보는 것과 동일
	    }
	    
	    /** Returns whether the stack is empty. */
	    public boolean empty() {
	        return queue.isEmpty();
	    }
	}

}
