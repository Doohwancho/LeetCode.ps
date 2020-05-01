package mayChallenge;

public class FirstBadVersion {

	//<문제풀이1>
	
	//binary search
	
	//보통 binary search를 오름차순 정렬된 수의 배열에서 쓰는데, 
	
	//{false,false,false,true,true,true,true} 같이
	
	//어떤 패턴이 반복되다가 다른 패턴2로 넘어가는 경계선을 찾을때도 쓸 수 있다.
	
	//binary search라는게 {1,2,3,4,5}에서 2의 인덱스를 찾는다고 치면, 반으로 뚝 잘라서
	
	//가운데 있는 3을 보고, 어라? 2보다 크네? 그러면 보는 범위(left~right)에서 right를 3의 인덱스-1로 설정한 후 다시봐야 겠다
	
	//라고 해서 두번째 볼땐 left~right가 index 0~index1이 되고, 해당 범위에 가운데 오는 애가 우리가 찾는 숫자인지 확인하고,
	
	//아니라면 다시 자른 반에더 또 반을 자르는걸 찾을떄까지 반복하는 건데.
	
	//이 경우도, true면 right를 한칸 왼쪽으로 떙겨서 다시 봐 보다가
	
	//false가 나오면 이번엔 left를 한칸 오른쪽으로 떙겨서 다시 찾는걸 반복하는것.
	
	//l==r이 될떄까지.
	
	//그러면 마지막에 l이 true값의 가장 첫번째 인덱스를 가르키게 되어있다.

	//22 / 22 test cases passed.
	//Status: Accepted
	//Runtime: 12 ms
	//Memory Usage: 35.9 MB
	
	public int firstBadVersion(int n) {
        int l = 0, r = n, m;
        while(l <= r){
            m = l + (r-l)/2; //(l+r)/2로 했는데 testcase에서 l+r이 Integer.MAX_VALUE를 초과하는 경우를 대비해서, 이렇게 해줌.
            if(isBadVersion(m)==true) r = m-1;
            else l = m+1;
        }
        return l;
    }
}
