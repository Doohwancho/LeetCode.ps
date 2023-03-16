//Question


// Constraints:

// 2 <= nums.length <= 104
// -109 <= nums[i] <= 109
// -109 <= target <= 109
// Only one valid answer exists.


//difficulty: easy
//data structure: array
//algorithm: two-pointer
//time complexity: O(nlogn)
//space complexity: O(n)


function twoSum(nums: number[], target: number): number[] {
	const arrayWithIndices = nums.map((value, index) => ({ value, index }));

	arrayWithIndices.sort((a, b) => a.value - b.value);

	let left = 0;
	let right = nums.length - 1;

	while (left < right) {
		const sum = arrayWithIndices[left].value + arrayWithIndices[right].value;

		if (sum === target) {
			return [arrayWithIndices[left].index, arrayWithIndices[right].index];
		} else if (sum < target) {
			left++;
		} else {
			right--;
		}
	}
};

describe("", () => {
  it("should pass", () => {
    const cases = [
      { input: {nums: [2,7,11,15], target: 9} , output: [0,1] },
      { input: {nums: [3,2,4], target: 6}, output: [1,2] },
      { input: {nums: [3,3], target: 6}, output: [0,1] },
    ];

    cases.forEach(({ input, output }) => {
      expect(twoSum(input.nums, input.target)).toEqual(output);
    });
  });
});
