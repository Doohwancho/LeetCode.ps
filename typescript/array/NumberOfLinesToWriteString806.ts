
// pre-requisites: alphabet string to number

let a: string = 'a';
let b: string = 'b';

let a_ = <any>a;
let b_ = <any>b;

let benchmark = <any>a;

console.log(a_ - benchmark); //NaN
console.log(b_ - benchmark); //NaN

//c나 java에선 되는게 typescript에선 안되네?


let y: number = +a;
console.log(y); //NaN
//이것도 안되네?

let z = b.charCodeAt(0) - 97;
console.log(z);
//오 된다




//Solution1

// Runtime: 73 ms, faster than 75.00% of TypeScript online submissions for Number of Lines To Write String.
// Memory Usage: 44.5 MB, less than 25.00% of TypeScript online submissions for Number of Lines To Write String.

/*
function numberOfLines(widths: number[], s: string): number[] {
    let total: number = 0;
    let x: number = s.length > 0 ? 1 : 0;
    
    for(let str of s){
        let adder: number = widths[str.charCodeAt(0) - 97];
        if(total + adder > 100){
            x++;
            total = adder;
        } else {
            total += adder;
        }
    }
    return [x, total];
};
*/


// Solution 2 by lee215

//Runtime: 110 ms, faster than 41.67% of TypeScript online submissions for Number of Lines To Write String.
//Memory Usage: 45.1 MB, less than 16.67% of TypeScript online submissions for Number of Lines To Write String.

//성능 최적화를 떠나서 
//solution1과 같은 로직이지만, 읽기 훨씬 더 깔끔해서 좋다.

function numberOfLines(widths: number[], s: string): number[] {
    let x: number = 1;
    let y: number = 0;
    
    for(let char of s){
        let width: number = widths[char.charCodeAt(0) - 97];
        x = y + width > 100 ? x+1 : x;
        y = y + width > 100 ? width : y + width;
    }
    return [x, y];
};