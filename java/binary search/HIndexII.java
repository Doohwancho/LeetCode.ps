package juneChallenge;

public class HIndexII {
	
	//<문제풀이1>
	
	//case1) when array has h-index
	//	[0,1,3,5,6]
	//	if(citations[m] >= (n-m)) r = m;
	//	put m(what we want) on r -> increase l until l == r to break while loop-> return total length - l (n-m)
	
	//case2) when array does NOT have h-index and elements > h-index
	//	[11,14]
	//	decrease r until 0, l == r to break while loop -> return total length - l (== total length)
	
	//case3) when array does NOT have h-index and elements < h-index
	//	[0,0]
	//	increase l until l == r to break while loop -> return total length - l (== 0)
	
	//Runtime: 0 ms
	//Memory Usage: 47.1 MB
	
    public int hIndex(int[] citations) {
        int l = 0, r = citations.length, n = r;
        while(l < r){
            int m = (l+r)/2;
            if(citations[m] >= (n-m)){
                r = m;
            } else {
                l = m+1;
            }
        }
        return n-l;
    }
}
