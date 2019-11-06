/*
	Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie. Each child i has a greed factor gi, which is the minimum size of a cookie that the child will be content with; and each cookie j has a size sj. If sj >= gi, we can assign the cookie j to the child i, and the child i will be content. Your goal is to maximize the number of your content children and output the maximum number.
	
	Note:
	You may assume the greed factor is always positive.
	You cannot assign more than one cookie to one child.
	
	Example 1:
	Input: [1,2,3], [1,1]
	
	Output: 1
	
	Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3. 
	And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
	You need to output 1.
	Example 2:
	Input: [1,2], [1,2,3]
	
	Output: 2
	
	Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2. 
	You have 3 cookies and their sizes are big enough to gratify all of the children, 
	You need to output 2.

 
 	
 	
 	<문제>
 	
 	애들한테 쿠키를 준다고 하자. g는 애들이고 s는 내가 가진 쿠키다.
 	
 	어레이 g,s가 다음과 같이 주어진다고 하자.
 	
 	g = [1,2,3], s = [1,1]
 	
 	여기서 숫자의 크기는 g,s에서 각각 다른의미다.
 	
 	s에서는 숫자가 크면 클수록 맛있는 쿠키다. [1,10]이면, 첫번째 쿠키는 1만큼 맛있고, 두번째쿠키는 10만큼 맛있다.
 	
 	g에서는 숫자가 크면 클수록 애가 쿠키맛을 기대한다. 예를들어 [1,2,3]이면, 첫번째 아이는 1만큼 맛있는 쿠키를, 두번째 아이는 2만큼 맛있는 쿠키를, 세번째 아이는 3만큼 맛있는 쿠키를 원한다.
 	
 	아이들한테 쿠키를 하나씩 나누어 주는데, 아이들이 기대하는 것보다 이하의 맛을지닌 쿠키를 주지 못한다고 할 때,
 	
 	최대로 만족시킬 수 있는 아이들의 숫자는?
 */

package Greedy;

import java.util.Arrays;

public class AssignCookies455 {
	
	/*
	//<문제풀이1>
	
	//가장 욕심이 많은 놈부터 만족시켜 줘야 되겠지?
	
	//그렇다면 Arrays.sort()로 오름차순 정렬해주고, 맨 뒤에 가장 욕심 많은애부터 체크하되,
	
	//욕심이 가진 쿠키중에 가장 맛있는것보다 크면 쿨하게 패스하면서 카운팅 해줌.
	
	//Runtime: 8 ms, faster than 98.42% of Java online submissions for Assign Cookies.
	//Memory Usage: 40.2 MB, less than 100.00% of Java online submissions for Assign Cookies
	
	public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
		
        int rst = 0;
        
		for(int i = s.length-1, j = g.length-1; i>=0 && j>=0; ) {
			if(g[j] > s[i]) {
				j--;
				continue;
			}
			else {
				j--;
				i--;
				rst++;
			}
		}
		return rst;
    }
    */
	
	//<문제풀이2 by asurana28>
	
	//난 가장 욕심많은애부터 줬는데, 얜 가장 욕심없는애부터 줌
	
	//성능동일
	
	//Runtime: 8 ms, faster than 98.42% of Java online submissions for Assign Cookies.
	//Memory Usage: 40.5 MB, less than 95.24% of Java online submissions for Assign Cookies.
	
	public static int findContentChildren(int[] g, int[] s) {
	    Arrays.sort(g);
	    Arrays.sort(s);
	    
	    int i=0;
	    for(int j=0; i<g.length && j<s.length; j++) {
	        if(s[j] >= g[i])
	            i++;
	    }
	    
	    return i;
	}
	
	public static void main(String[] args) {
		int[] g = {1,2,3};
		int[] s = {1,1};
		System.out.println(findContentChildren(g, s));
	}
}
