/*
	Implement the following operations of a queue using stacks.
	
	push(x) -- Push element x to the back of queue.
	pop() -- Removes the element from in front of queue.
	peek() -- Get the front element.
	empty() -- Return whether the queue is empty.
	Example:
	
	MyQueue queue = new MyQueue();
	
	queue.push(1);
	queue.push(2);  
	queue.peek();  // returns 1
	queue.pop();   // returns 1
	queue.empty(); // returns false
	
	
	
	
	<문제>
	
	stack으로 queue의 .push() / .pop() / .peek() / .empty()함수를 구현하라.
	
 */

package Stack;

import java.util.Stack;

public class ImplementQueueUsingStack232 {
	
	/*
	//<문제풀이1>
	
	//무난하게 Integer type이 들어가는 stack을 만들어서, 
	
	//queue나 stack이나 push하면 맨 마지막에 들어가니까 .push() 똑같이 쓰고
	
	//queue의 pop은 가장 첫번째 원소가 나가야 하니까, stack.firstElement()로 따로 빼주고, .removeElementAt(0)로 첫번째 원소를 지운 후, 아까 빼준 첫번째 원소 반환
	
	//peek()은 가장 첫번째 원소 보여주기만 하는거니까 stack.firstElement()쓰면 되고
	
	//empty도 똑같이 .isEmpty쓰면 된다.
	
	//무-난
	
	//Runtime: 43 ms, faster than 40.59% of Java online submissions for Implement Queue using Stacks.
	//Memory Usage: 34 MB, less than 20.83% of Java online submissions for Implement Queue using Stacks.
	
	class MyQueue {

		Stack<Integer> stack = new Stack<>();
		
	    public MyQueue() {} 
	    
	    public void push(int x) {
	        stack.push(x);    
	    }
	    
	    public int pop() {
	        int first = stack.firstElement();
	        stack.removeElementAt(0);
	        return first;
	    }
	    
	    public int peek() {
	        return stack.firstElement();    
	    }
	    
	    public boolean empty() {
	        return stack.isEmpty();
	    }
	}
	*/
	
	//<문제풀이2 by StefanPochmann>
	
	//stack 2개를 만들어서, push할땐 일반적이게 stack1에 push하고,
	
	//첫번째 원소가 필요한 pop()이나 peek을 한땐, stack1에서 역순으로 stack2한테 push하면,
	
	//원래 1,2,3,4,5로 쌓은게 5,4,3,2,1로 되니까, 여기서 stack에 pop()이나 peek()하면 1을 반환할 수 있음.
	
	//empty는 당연히 stack1과 stack2 둘 다 비어있는지 확인해야 함.

	//성능은 위와 비슷
	
	//Runtime: 44 ms, faster than 12.20% of Java online submissions for Implement Queue using Stacks.
	//Memory Usage: 33.8 MB, less than 20.83% of Java online submissions for Implement Queue using Stacks.
	
	class MyQueue {

	    Stack<Integer> input = new Stack();
	    Stack<Integer> output = new Stack();
	    
	    public void push(int x) {
	        input.push(x);
	    }

	    public int pop() {
	        peek();
	        return output.pop();
	    }

	    public int peek() {
	        if (output.empty()){
	            while(!input.empty()){
	                output.push(input.pop());
	            }
	        }
	        return output.peek();
	    }

	    public boolean empty() {
	        return input.empty() && output.empty();
	    }
	}
}
