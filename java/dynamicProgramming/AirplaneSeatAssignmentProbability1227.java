package dynamicProgramming;

public class AirplaneSeatAssignmentProbability1227 {

	//<문제풀이1 by rock>
	
	//증명은 아직.
	
	//근데 brain-teaser수준으로 생각하면 왜 n>=2 일때 답이 1/2이 될 수 밖에 없는지는 알겠다.
	
	//첫번째 사람이 랜덤하게 자리에 앉는다. 지 자리에 안앉았다고 가정하자.
	
	//그럼 두번째, 세번째 승객이 와서 자리에 앉다가, 자기자리에 첫번째 사람이 앉아있네? 하면, 쫒아내고 자기자리에 앉는다.
	
	//(문제에서는 자기 자리에 앉아있는 첫번째 승객을 쫒아내는게 아니라, 어? 내 자리가 뺏겼네? 하고 다른 랜덤한 자리에 앉는 식으로 되어있다.
	
	//그런데, 이렇게 생각하는 것 보다, 자기 자리를 찾았으면, 첫번째 승객을 쫒아내고, 첫번째 승객보고 다른 자리를 랜덤하게 가라고 하는게 더 이해하기 수월하다.)
	
	//그렇게 이리쫒기고 저리 쫒기던 첫번째 승객은, 마지막 n번째 손님이 왔을 때, 남은자리가 첫번째 승객의 자리, 마지막 승객의 자리밖에 안남아 있으므로,
	
	//n번째 손님의 자리에 첫번재 손님이 앉아있을 확률은 1/2이다.
	
	//Runtime: 36 ms, faster than 6.90% of Java online submissions for Airplane Seat Assignment Probability.
	//Memory Usage: 44.7 MB, less than 7.76% of Java online submissions for Airplane Seat Assignment Probability.
	
    public double nthPersonGetsNthSeat(int n) {
        if (n == 1) return 1.0d;
        return 1d / n + (n - 2d) / n * nthPersonGetsNthSeat(n - 1);
    }
}
