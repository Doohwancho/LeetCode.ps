
//Question

//Given a binary array nums, return the maximum number of consecutive 1's in the array.


//constraints
//1 <= nums.length <= 105
//nums[i] is either 0 or 1.


//Solution01

//difficulty: easy
//datastructure: array
//algorithm: none
//Time complexity: O(n)
//Space complexity: O(n)

// 42 / 42 test cases passed.
// Status: Accepted
// Runtime: 72 ms
// Memory Usage: 44.7 MB


function findMaxConsecutiveOnes(nums: number[]): number {

	let max = 0;
	let consecutive = 0;

	for(let i = 0; i < nums.length; i++){
		consecutive++;
		if(nums[i]==0) consecutive = 0;
		max = Math.max(max, consecutive);
	}

	return max;
};

describe("", () => {
  it("should pass", () => {
    const cases = [
      { input: [1,1,0,1,1,1], output: 3 },
      { input: [1,0,1,1,0,1], output: 2 },
      { input: [0,0,0,0,0,0], output: 0 },
      { input: [1,1,1,1,1,1], output: 6 },
    ];

    cases.forEach(({ input, output }) => {
      expect(findMaxConsecutiveOnes(input)).toEqual(output);
    });
  });
});
