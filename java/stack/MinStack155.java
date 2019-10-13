/*
	Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
	
	push(x) -- Push element x onto stack.
	pop() -- Removes the element on top of the stack.
	top() -- Get the top element.
	getMin() -- Retrieve the minimum element in the stack.
	 
	
	Example:
	
	MinStack minStack = new MinStack();
	minStack.push(-2);
	minStack.push(0);
	minStack.push(-3);
	minStack.getMin();   --> Returns -3.
	minStack.pop();
	minStack.top();      --> Returns 0.
	minStack.getMin();   --> Returns -2.
	
	
	
	<문제>
	
	stack을 이용하여, .push() / .pop() / .top() / .getMin() function을 구현하라.
 */

package Stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MinStack155 {
	/*
	//<문제풀이1>
	
	//무난하게 stack선언해서 하는 방법. getMin을 for문말고 다른방법으로 구하면 훨씬 더 빠른 결과를 얻을 수 있을 것 같음.
	
	//Runtime: 1042 ms, faster than 5.11% of Java online submissions for Min Stack.
	//Memory Usage: 56.5 MB, less than 5.08% of Java online submissions for Min Stack.
	
	class MinStack {

	    public MinStack() {
	        
	    }
	    Stack<Integer> stack = new Stack<>();
	    
	    public void push(int x) {
	        stack.push(x);    
	    }
	    
	    public void pop() {
	        stack.pop();    
	    }
	    
	    public int top() {
	        return stack.lastElement();   
	    }
	    
	    public int getMin() {
	        int minimum = Integer.MAX_VALUE;
	        for(int i : stack) minimum = Math.min(minimum, i);
	        return minimum;
	    }
	}
	*/
	
	//<문제풀이 by 	EdickCoding>
	
	//일반적으로 담는 stack과, min value꺼낼때 필요한 min stack 두개 생성.
	
	//stack에 push할때마다, min stack의 가장 마지막 값보다 x가 더 작으면 min stack에도 push
	
	//따라서 가장 작은 값이 min stack의 가장 마지막에 쌓이니깐, getMin()할때 그냥 min.peek()하면 됨
	
	//pop()은 if (stack.pop().equals(min.peek())) min.pop();
	
	//이렇게 생겨먹었는데, if문 안에 stack.pop()에서 원래 stack에 팝은 실행이 됨.
	
	//근데 stack.pop().equals(min.peek())까지 하는 이유는, min의 가장 마지막 값은 가장 작은 값이라 했는데,
	
	//따라서 min.peek(=가장 작은 값)이 stack.pop()해서 stack의 가장 마지막 값을 지울때 없어지면, min의 값도 없어져야 하기 때문.
	
	//Runtime: 48 ms, faster than 38.44% of Java online submissions for Min Stack.
	//Memory Usage: 41.5 MB, less than 10.15% of Java online submissions for Min Stack.
	
	class MinStack {
	    Stack<Integer> min = new Stack<>();
	    Stack<Integer> stack = new Stack<>();
	    public void push(int x) {
	        stack.push(x);
	        if (min.isEmpty() || min.peek() >= x) {
	            min.push(x);
	        }
	    }

	    public void pop() {
	        if (stack.pop().equals(min.peek())) {
	            min.pop();
	        }
	    }

	    public int top() {
	        return stack.peek();
	    }

	    public int getMin() {
	        return min.peek();
	    }
	}
	
}
