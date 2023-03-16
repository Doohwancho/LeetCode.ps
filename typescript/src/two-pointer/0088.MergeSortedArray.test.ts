//Question


// Constraints:

// nums1.length == m + n
// nums2.length == n
// 0 <= m, n <= 200
// 1 <= m + n <= 200
// -109 <= nums1[i], nums2[j] <= 109


//difficulty: easy
//data structure: array
//algorithm: two pointer
//time complexity: O(N)
//space complexity: O(1)


function merge(nums1: number[], m: number, nums2: number[], n: number): void {
  let i = m-1;
  let j = n-1;
  for(let index = m+n-1; index>= 0 && j>=0; index--){
    if(nums1[i] > nums2[j]){
      nums1[index] = nums1[i];
      i--;
    }else{
      nums1[index] = nums2[j];
      j--;
    }
  }
};

describe("", () => {
  it("should pass", () => {
    const cases = [
      { input: {nums1: [1,2,3,0,0,0], m:3, nums2:[2,5,6], n:3} , output: [1,2,2,3,5,6] },
      { input: {nums1: [1], m:1, nums2: [], n:0 } , output: [1] },
      { input: {nums1: [0], m:0, nums2: [1], n:1 } , output: [1] },
    ];

    cases.forEach(({ input, output }) => {
      expect(merge(input)).toEqual(output);
    });
  });
});
