//Question

//1 <= arr.length <= 104
//0 <= arr[i] <= 9


//difficulty: easy
//data structure: array
//algorithm: two pointers
//time complexity: O(n)
//space complexity: O(1)




function duplicateZeros(arr: number[]): number[]{
	let a = 0;
	let b = arr.length-1;
	while(a < b){
		if(arr[a] === 0){
			arr.splice(b, 1);
			arr.splice(a, 0, 0);
			a++;
		}
		a++;
	}
};

describe("", () => {
  it("should pass", () => {
    const cases = [
      { input: [1,0,2,3,0,4,5,0], output: [1,0,0,2,3,0,0,4]  },
      { input: [1,2,3], output: [1,2,3] },
    ];

    cases.forEach(({ input, output }) => {
      expect(duplicateZeros(input)).toEqual(output);
    });
  });
});
