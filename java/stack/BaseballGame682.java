/*
	You're now a baseball game point recorder.
	
	Given a list of strings, each string can be one of the 4 following types:
	
	Integer (one round's score): Directly represents the number of points you get in this round.
	"+" (one round's score): Represents that the points you get in this round are the sum of the last two valid round's points.
	"D" (one round's score): Represents that the points you get in this round are the doubled data of the last valid round's points.
	"C" (an operation, which isn't a round's score): Represents the last valid round's points you get were invalid and should be removed.
	Each round's operation is permanent and could have an impact on the round before and the round after.
	
	You need to return the sum of the points you could get in all the rounds.
	
	Example 1:
	Input: ["5","2","C","D","+"]
	Output: 30
	Explanation: 
	Round 1: You could get 5 points. The sum is: 5.
	Round 2: You could get 2 points. The sum is: 7.
	Operation 1: The round 2's data was invalid. The sum is: 5.  
	Round 3: You could get 10 points (the round 2's data has been removed). The sum is: 15.
	Round 4: You could get 5 + 10 = 15 points. The sum is: 30.
	Example 2:
	Input: ["5","-2","4","C","D","9","+","+"]
	Output: 27
	Explanation: 
	Round 1: You could get 5 points. The sum is: 5.
	Round 2: You could get -2 points. The sum is: 3.
	Round 3: You could get 4 points. The sum is: 7.
	Operation 1: The round 3's data is invalid. The sum is: 3.  
	Round 4: You could get -4 points (the round 3's data has been removed). The sum is: -1.
	Round 5: You could get 9 points. The sum is: 8.
	Round 6: You could get -4 + 9 = 5 points. The sum is 13.
	Round 7: You could get 9 + 5 = 14 points. The sum is 27.
	
	
	
	
	
	
	<문제>
	
	점수 계산기를 만든다.
	
	input으로 string[]가 ["5","2","C","D","+"] 이렇게 주어진다.
	
	각숫자는 단순히 스코어에 더하면 됨.
	
	C는 마지막으로 더한 숫자를 지우라는 뜻.
	
	D는 마지막으로 더한 숫자의 두배를 더하라는 뜻.
	
	+는 최근 score에 더한 값 두개의 합을 더하라는 뜻.
	
 */

package Stack;

import java.util.Stack;

public class BaseballGame682 {
	/*
	//<Trial01>
	
	//최대한 stack을 안쓰고 int[]를 쓰려고 했으나, "C"가 세번 나오는 괴랄한 input나오면 안됨..
	
	public static int calPoints(String[] ops) {
        int[] latestTwoScores = new int[3];
        int score = 0;
        
        for(int i = 0; i < ops.length; i++) {
        	if(ops[i].equals("C")) { //C가 연속으로 2개 나오면 에러
        		score -= latestTwoScores[2];
        		latestTwoScores[2] = latestTwoScores[1];
        		latestTwoScores[1] = latestTwoScores[0];
        	}
        	else if(ops[i].equals("D")) {
        		score = score + 2 * latestTwoScores[2];
        		latestTwoScores[0] = latestTwoScores[1];
        		latestTwoScores[1] = latestTwoScores[2];
            	latestTwoScores[2] *= 2;
        	}
        	else if(ops[i].equals("+")) {
        		int currScore = latestTwoScores[1] + latestTwoScores[2];
        		score += currScore;
        		latestTwoScores[0] = latestTwoScores[1];
        		latestTwoScores[1] = latestTwoScores[2];
        		latestTwoScores[2] = currScore;
        	}
        	else {
            	int currScore = Integer.parseInt(ops[i]);
        		score += currScore;
        		latestTwoScores[0] = latestTwoScores[1];
        		latestTwoScores[1] = latestTwoScores[2];
            	latestTwoScores[2] = currScore;
        	}
        }
		return score;
    }
    */
	
	//<문제풀이1>
	
	//위엣것과 로직은 같지만, stack을 이용하여 품.
	
	//Runtime: 2 ms, faster than 94.44% of Java online submissions for Baseball Game.
	//Memory Usage: 36 MB, less than 100.00% of Java online submissions for Baseball Game.
	
	public static int calPoints(String[] ops) {
		Stack<Integer> stack = new Stack<>();
		int score = 0;
		
		for(String str : ops) {
			if(str.equals("C")) {
				score -= stack.pop();
			}
			else if(str.equals("D")) {
				int currScore = 2 * stack.lastElement();
				score += currScore;
				stack.push(currScore);
			}
			else if(str.equals("+")) {
				int currScore = stack.pop()+stack.lastElement();
				score += currScore;
				stack.push(currScore - stack.lastElement());
				stack.push(currScore);
			}
			else {
				int currScore = Integer.parseInt(str);
				score += currScore;
				stack.push(currScore);
			}
			System.out.println(str+"  "+score+"   "+stack);
		}
		return score;
	}
	
	public static void main(String[] args) {
		//String[] ops = {"5","-2","4","C","D","9","+","+"};
		String[] ops = {"5","2","C","D","+"};
		//String[] ops = {"36","28","70","65","C","+","33","-46","84","C"};
		
		System.out.println(calPoints(ops));
	}
}
