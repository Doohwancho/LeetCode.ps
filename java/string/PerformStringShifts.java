package thirtyDayChallenge;

public class PerformStringShifts {
	
	/*
	//<문제풀이1>
	
	//31 / 31 test cases passed.
	//Status: Accepted
	//Runtime: 0 ms
	//Memory Usage: 37.3 MB
	
    public String stringShift(String s, int[][] shift) {
    	//step01 : 왼쪽인지 오른쪽인지, 오른쪽이면 몇칸을 땡기는지 구한다.
        int dir = 0;
        
        for(int i = 0; i < shift.length; i++){
            if(shift[i][0]==0){
                dir-=shift[i][1];
            } else {
                dir+=shift[i][1];
            }
        }
        
        //step02 : dir값이 -면 왼쪽, +면 오른쪽. 거기에 맞춰서 stringbuilder를 이용하여 string을 잘라 이어맞춘다.
        StringBuilder sb = new StringBuilder();
        int move = dir%s.length();
        char[] sChar = s.toCharArray();
        
        if(move<0) {
			sb.append(sChar, -move, s.length()+move).append(sChar, 0, -move);
		} else {
			sb.append(sChar, s.length()-move, move).append(sChar, 0, s.length()-move);
		}
        return sb.toString();
    }
    */
	
    
    //<문제풀이 by rock>
    
    //같은 로직, 더 깔끔한 코드
    
    public String stringShift(String s, int[][] shift) {
        int pos = 0, len = s.length();
        for (int[] sh : shift) {
            pos += sh[0] == 0 ? sh[1] : -sh[1];
        }
        pos = (pos % len + len) % len;
        return s.substring(pos) + s.substring(0, pos); //"unhappy".substring(2) returns "happy"
    }
}
