//Question


//solution1

//difficulty: easy
//data structure: array
//time complexity: O(N)
//space complexity: O(1)


function isAnagram(s: string, t: string): boolean {
	let s_len:number = s.length;
    let t_len:number = t.length;

    if(s_len != t_len) return false;

    let alphabet:number[] = new Array(26).fill(0);
    for(let i = 0; i < s_len; i++){
        alphabet[s.charCodeAt(i)-'a'.charCodeAt(0)]++;
        alphabet[t.charCodeAt(i)-'a'.charCodeAt(0)]--;
    }
    for(let i = 0; i < 26; i++){
        if(alphabet[i] != 0) return false;
    }
    return true;
};



//solution2 by pth_1641

//성능은 구린데, 보기에 깔끔하다.
//ts에서는 두 string을 === 비교하면, equals 연산을 해주나보다.

function isAnagram2(s: string, t: string): boolean {
    const sortedS: string = s.split('').sort().join('');
    const sortedT: string = t.split('').sort().join('');
    return sortedS === sortedT;
};




describe("", () => {
  it("should pass", () => {
    const cases = [
      { input: {s: "anagram", t: "nagaram"}, output: true },
      { input: {s: "rat", t: "car"} , output: false },
    ];

    cases.forEach(({ input, output }) => {
      expect(isAnagram(input.s, input.t)).toEqual(output);
    });
  });
});
