/*
	You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
	
	Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
	
	You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
	
	Example:
	
	Given n = 5, and version = 4 is the first bad version.
	
	call isBadVersion(3) -> false
	call isBadVersion(5) -> true
	call isBadVersion(4) -> true
	
	Then 4 is the first bad version. 
	
	
	<문제>
	
	공장에서 한 물건의 버전 개발을 한다.
	
	버전 개발을 할 때, 이전 버전에 문제가 있으면 다음 버전도 문제가 있을 수 밖에 없다. 이전버전을 베이스로 두고 개발했기 때문이다.
	
	따라서 ver1 -> ver2 -> ver3 -> ver4 -> ver5가 있을 때,
	
	ver3에 문제가 있으면, 그 이후에 있는 모든 버전들(ver4, ver5)에 문제가 있다.
	
	버전을 나타내는 숫자 n이 주어지면, 1부터 n까지 숫자중에 문제가 있는 버전 중 가장 먼저 문제가 발생한 버전을 찾아라.
	
	isBadVersion()이라는 내부 API가 제공된다.
	
	이 API에 숫자를 넣으면, 해당 버전에 문제가 있으면 true를 반환하고, 문제가 없으면 false를 반환한다.
	
	위 api를 이용하여 문제를 해결하라.
 */

package BinarySearch;

public class FirstBadVersion278 {
    
	//<문제풀이1>
	
	//예를들어 주어진 n이 5이고, 찾고자 하는 최소 버전인 m이 3이라고 했을 때, isBadVersion()을 돌리면
	
	// false false true true true
	
	//binary-search를 이용하는데, 대략적인 logic은
	
	//true가 나오면 해당 숫자보다 왼쪽에서 찾아야 하니까 오른쪽 끝단인 r(right)를 m으로 해주고
	
	//false가 나오면 해당 숫자보다 오른쪽에서 찾아야 하니까 왼쪽 끝단인 l(left)를 l+1으로 해준다.
	
	//그렇게 돌리면 매 loop마다 m이 2 -> 4 -> 3 -> 3으로 된다.
	
	//Runtime: 10 ms, faster than 99.75% of Java online submissions for First Bad Version.
	//Memory Usage: 33 MB, less than 100.00% of Java online submissions for First Bad Version.
	
	private static boolean isBadVersion(int m) {
		return m >= 3;
	}
	
	public static int firstBadVersion(int n) {
        int l = 0, r = n, m = 0;
  
        while(l < r){
            m = l + (r-l)/2;
            if(isBadVersion(m)) r = m;
            else l = m + 1;
            System.out.println(m);
        }
        return l;
    }
	
	public static void main(String[] args) {
		int n = 5;
		System.out.println(firstBadVersion(n));
	}
}
