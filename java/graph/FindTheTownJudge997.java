/*
	In a town, there are N people labelled from 1 to N.  There is a rumor that one of these people is secretly the town judge.
	
	If the town judge exists, then:
	
	The town judge trusts nobody.
	Everybody (except for the town judge) trusts the town judge.
	There is exactly one person that satisfies properties 1 and 2.
	You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled a trusts the person labelled b.
	
	If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.
	
	 
	
	Example 1:
	
	Input: N = 2, trust = [[1,2]]
	Output: 2
	Example 2:
	
	Input: N = 3, trust = [[1,3],[2,3]]
	Output: 3
	Example 3:
	
	Input: N = 3, trust = [[1,3],[2,3],[3,1]]
	Output: -1
	Example 4:
	
	Input: N = 3, trust = [[1,2],[2,3]]
	Output: -1
	Example 5:
	
	Input: N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
	Output: 3
	 
	
	Note:
	
	1 <= N <= 1000
	trust.length <= 10000
	trust[i] are all different
	trust[i][0] != trust[i][1]
	1 <= trust[i][0], trust[i][1] <= N
	
	
	
	
	
	<문제>
	
	N과 int[]가 다음과 같이 주어진다.
	
	N = 3, trust = [[1,3],[2,3]]
	
	N은 마을사람의 숫자를 나타낸다. N=3이니까 마을에 3명있다.
	
	trust에 [1,3]은 1번 사람이 3번 사람을 신뢰한다는 말이다.
	
	[2,3]은 2번사람이 3번사람을 신뢰한다는 말이다.
	
	우리가 할 일은, 마을 내에 judge가 있으면, judge의 번호를 반환하고 없으면 -1을 반환하면 된다.
	
	judge는 일반사람들과는 다르게 특별한 특징을 2개 가지고있다., 
	
	1)모든 사람들에게 신뢰받음
	
	2)아무도 신뢰하지 않음
	
 */

package Graph;

public class FindTheTownJudge997 {
	
	//<문제풀이1>
	
	//graph를 이용해서 풀어야 하는데 graph가 뭔지 몰라서 그냥 품..;;
	
	//Runtime: 3 ms, faster than 94.80% of Java online submissions for Find the Town Judge.
	//Memory Usage: 57.4 MB, less than 100.00% of Java online submissions for Find the Town Judge.
	
    public int findJudge(int N, int[][] trust) {
        int[] town = new int[N];
        for(int[] person: trust){
            town[person[1]-1]++;     //신뢰를 받았으면 +1해줌
            town[person[0]-1]+=1000; //누군가 신뢰한다는건 judge가 아니므로, 아예 범위에 벗어나도록 엄청큰숫자를 더해줌
        }
        for(int i = 0; i < N; i++){
            if(town[i] == N-1) return i+1; //신뢰를 받은 숫자가 마을사람들 숫자와 같아면, 해당 사람 이 judge이므로, 번호를 반환해줌.
        }
        return -1;
    }
}
