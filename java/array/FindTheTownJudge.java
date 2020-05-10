package mayChallenge;

public class FindTheTownJudge {
	
	//<문제풀이1>
	
	//모두한테 지목받아야 하니까, 지목받을때마다 p[t[1]]++; 해주고 
	
	//맨 마지막에, if(p[i]==N-1)로 본인 빼고 지목받았는지 봐야지
	
	//근데 judge trusts no one in town이라고 했으니까
	
	//본인 제외 다른사람들에게 모두 신뢰받아도, 본인이 누굴 신뢰하면 judge가 아니잖아?
	
	//그럼 이 경우 예외처리를 어떻게 할지 생각하다가
	
	//그냥 본인이 누굴 신뢰할때 -1처리를 해주면, 마을사람들에게 모두 신뢰를 받아도, 결과적으론 N-2가 될테니까 됐네.
	
	//Runtime: 2 ms
	//Memory Usage: 46.8 MB
    public int findJudge(int N, int[][] trust) {
        if(N==1) return 1;
        int[] p = new int[N+1];
        for(int[] t : trust){
            p[t[0]]--;
            p[t[1]]++;
        }
        for(int i = 0; i < N+1; i++){
            if(p[i]==N-1) return i;
        }
        return -1;
    }
}
