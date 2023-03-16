//Question

//Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.


//Constraints
//1 <= nums.length <= 104
// -104 <= nums[i] <= 104
// nums is sorted in non-decreasing order.


//Solution1

//using built-in sort

//difficulty: easy
//data structure: array
//algorithm: none
//time complexity: O(n) for squaring + O(nlogn) for sort = O(nlogn)

function sortedSquares1(nums: number[]): number[] {
	nums.forEach((num, index) => {
		nums[index] = num * num;
	});

	nums.sort((a,b) => a-b);

	return nums;
};


//Solution2

// two pointers

// 아 더러워

//difficulty: easy
//data structure: array
//algorithm: two-pointers
//time complexity: O(n)

//Runtime 515 ms
//Memory 48.7 MB

function sortedSquares2(nums: number[]): number[] {
	for(let i = 0, j = nums.length-1; j >= 0; j--){
		let sqr_i = nums[i] * nums[i];
		let sqr_j = nums[j] * nums[j];

		if(sqr_i < sqr_j){
			nums[j] = sqr_j;

			let k = j;

			while(k < nums.length-1 && nums[k] > nums[k+1]){
				k++;
				let tmp = nums[k];
				nums[k] = nums[k-1];
				nums[k-1] = tmp;
			}
		} else {
			nums[i] = nums[j];
			nums[j] = sqr_i;
		}
	}
	return nums;
};


//solution3 by wangzi6147

//two pointers

//새 array를 역순으로 채워나가면서, 원본 two pointer 중, 더 큰 값을 채우는 방식.

//time complexity: O(n)
//Runtime 96 ms
//Memory 50 MB


function sortedSquares3(A: number[]): number[] {
	let n:number= A.length;
	let result:number[] = new Array(n);
	let i:number = 0, j:number = n - 1;
	for (let p = n - 1; p >= 0; p--) {
		if (Math.abs(A[i]) > Math.abs(A[j])) {
			result[p] = A[i] * A[i];
			i++;
		} else {
			result[p] = A[j] * A[j];
			j--;
		}
	}
    return result;
}

describe("", () => {
  it("should pass", () => {
    const cases = [
      { input: {nums: [-4,-1,0,3,10]}, output: [0,1,9,16,100] },
      { input: {nums: [-7,-3,2,3,11]}, output: [4,9,9,49,121] },
      { input: {nums: [-5,-3,-2,-1]}, output: [1,4,9,25] },
      { input: {nums: [-10000, -9999, -7, -5, 0, 0, 10000]}, output: [0,0,25,49,99980001,100000000,100000000]},
    ];

    cases.forEach(({ input, output }) => {
      expect(sortedSquares3(input.nums)).toEqual(output);
    });
  });
});
