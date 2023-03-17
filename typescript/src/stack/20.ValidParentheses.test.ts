//Question

// Constraints:

// 1 <= s.length <= 104
// s consists of parentheses only '()[]{}'.

//difficulty: easy
//data structure: stack, map
//time complexity: O(n)
//space complexity: O(n)

interface IStack<T> {
  push(item: T): void;
  pop(): T | undefined;
  peek(): T | undefined;
  size(): number;
  isEmpty(): boolean;
}

class Stack<T> implements IStack<T> {
  private storage: T[] = [];

  constructor(private capacity: number = Infinity) {}

  push(item: T): void {
    if (this.size() === this.capacity) {
      throw Error("Stack has reached max capacity, you cannot add more items");
    }
    this.storage.push(item);
  }

  pop(): T | undefined {
    return this.storage.pop();
  }

  peek(): T | undefined {
    return this.storage[this.size() - 1];
  }

  size(): number {
    return this.storage.length;
  }

  isEmpty(): boolean {
	return this.size() === 0;
  }

}


function isValid(s: string): boolean {
	//write stack
	let stack = new Stack<String>();

	//write map
	let map = new Map();
	map.set("(", ")");
	map.set("{", "}");
	map.set("[", "]");

	for(let i = 0; i < s.length; i++){
		if(s.charAt(i) === "(" || s.charAt(i) === "{" || s.charAt(i) === "["){
			stack.push(s.charAt(i));
		} else {
			if(stack.isEmpty()) return false;
			if(map.get(stack.pop()) != s.charAt(i)) return false;
		}
	}
	return stack.isEmpty();
};


describe("", () => {
  it("should pass", () => {
    const cases = [
      { input: {s:"()"}, output: true },
      { input: {s:"()[]{}"} , output: true },
      { input: {s:"(]"} , output: false },
    ];

    cases.forEach(({ input, output }) => {
      expect(isValid(input.s)).toEqual(output);
    });
  });
});
