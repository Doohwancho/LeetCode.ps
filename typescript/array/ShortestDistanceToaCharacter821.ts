//Trial01

function shortestToChar(s: string, c: string): number[] {
    let arr = new Array(s.length);
    
    for(let i = 0; i < s.length; i++){
        if(s.charAt(i) == c){
            let start: number = 0;
            for(let p = i, q = i; p < s.length || q > 0; p++, q--){
                arr[p] = arr[q] = start;
                start++;
            }
        }
    }
    return arr;
};