//Question

//Given an array nums of integers, return how many of them contain an even number of digits.



// Constraints:
// 1 <= nums.length <= 500
// 1 <= nums[i] <= 105

//difficulty: easy
//data structure: array
//algorithm: none
//time complexity: O(n)
//space complexity: O(n)

function findNumbers(nums: number[]): number {
	let count = 0;
	for(let num of nums){
		if(num.toString().length % 2 == 0){
			count++;
		}
	}
	return count;
};

describe("", () => {
  it("should pass", () => {
    const cases = [
		{ input: {nums: [12,345,2,6,7896]} , output: 2 },
		{ input: {nums: [555,901,482,1771]}, output: 1 },
    ];

    cases.forEach(({ input, output }) => {
      expect(findNumbers(input.nums)).toEqual(output);
    });
  });
});
