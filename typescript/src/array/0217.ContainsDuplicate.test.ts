//Question

//typescript에서는, set.add()해서 hash 충돌나면 false반환 안하네?

//difficulty: easy
//data structure: set
//time complexity: O(N)
//space complexity: O(N)


function containsDuplicate(nums: number[]): boolean {
	let set = new Set<number>();
	for (let i = 0; i < nums.length; i++) {
		if(set.has(nums[i])) return true;
        set.add(nums[i]);
	}
	return false;
};

describe("", () => {
  it("should pass", () => {
    const cases = [
      { input: {nums: [1,2,3,1]}, output: true },
      { input: {nums: [1,2,3,4]}, output: false },
    ];

    cases.forEach(({ input, output }) => {
      expect(containsDuplicate(input.nums)).toEqual(output);
    });
  });
});
