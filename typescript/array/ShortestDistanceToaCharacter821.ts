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



// Solution1

//Runtime: 108 ms, faster than 66.67% of TypeScript online submissions for Shortest Distance to a Character.
//Memory Usage: 45 MB, less than 94.44% of TypeScript online submissions for Shortest Distance to a Character.

//O(N^2)

//loop 돌면서 파라미터 c만나면 양방향으로 물결치는 방법.

//문제 퀄리티가 좋네. 괜히 좋아요 비율이 높은게 아녀


function shortestToChar(s: string, c: string): number[] {
    let arr = new Array(s.length).fill(s.length);
    
    for(let i = 0; i < s.length; i++){
        if(s.charAt(i) == c){
            let start: number = 0;
            let p: number = i;
            let q: number = i;
            
            while(p < s.length && start < arr[p]){
                arr[p] = start;
                p++;
                start++;
            }
            start = 0;
            while(q >= 0 && start <= arr[q]){
                arr[q] = start;
                q--;
                start++;
            }
        }
    }
    return arr;
};



//Solution 2 by Lee215

//Runtime: 92 ms, faster than 77.78% of TypeScript online submissions for Shortest Distance to a Character.
//Memory Usage: 45.6 MB, less than 61.11% of TypeScript online submissions for Shortest Distance to a Character.


//정방향, 뒤로 loop 두번 돌면 완성!

function shortestToChar(s: string, c: string): number[] {
    let arr = new Array(s.length).fill(0);
    let pos: number = -s.length;
    
    for(let i = 0; i < s.length; i++){
        if(s.charAt(i) == c){
            pos = i;
        }
        arr[i] = i-pos;
    }
    pos = Infinity;
    
    for(let i = s.length-1; i >= 0; i--){
        if(s.charAt(i) == c){
            pos = i;
        }
        arr[i] = Math.min(arr[i], pos-i);
    }
    
    return arr;
};