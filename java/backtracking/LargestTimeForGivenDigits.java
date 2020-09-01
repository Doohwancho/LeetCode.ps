package augustChallenge;

import java.util.LinkedList;

public class LargestTimeForGivenDigits {

	
    //<Trial01>
	
	//brute-force
	
	//[0,6,2,6]에서 막힘
	
	//06:26해야 하는데,
	
	//20:6 에서 return ""해버리네..
	
    private int visited(int[] A, int digit, int limit){
        for(int i = 0, j = -1; i < A.length; i++){
            if(A[i] <= limit && A[i] > digit){
                if(j != -1){
                    A[j] = digit;
                }
                j = i;
                digit = A[i];
                A[i] = -1;
            }
        }
        return digit;
    }
    
    public String largestTimeFromDigits(int[] A) {
        StringBuilder sb = new StringBuilder();
        
        int first = visited(A, -1, 2);
        
        if(first == -1){
            return "";
        } else {
            sb.append(first);
        }
        
        
        if(first == 2){
            int second = visited(A, -1, 3);
            
            if(second == -1){
                return "";
            } else {
                sb.append(second);
                sb.append(':');
            }
        } else {
            int second = visited(A, -1, 9);
            
            sb.append(second);
            sb.append(':');
        }
        
        int third = visited(A, -1, 5);
        
        if(third == -1){
            return "";
        } else {
            sb.append(third);
        }
        
        for(int i = 0; i < A.length; i++){
            if(A[i] != -1){
                sb.append(A[i]);
                break;
            }
        }
        
        return sb.toString();
    }
    
    
    //<문제풀이1 by ambuj_kumar>
    
    //brute-force
    
    //A에서 나올 수 있는 모든 경우의 수를 다 시간으로 바꿔서 Math.max()하는 방법
    
    //00:00 보다 같거나 크고 23:59보다 같거나 작으면 숫자를 다시 STring으로 변환해서 리턴. 
    
    //Runtime: 9 ms
    //Memory Usage: 38.1 MB
    
    public String largestTimeFromDigits(int[] A) {
        int[][] sequences = new int[][]{
            {0, 1, 2, 3},
            {0, 1, 3, 2},
            {0, 2, 1, 3},
            {0, 2, 3, 1},
            {0, 3, 1, 2},
            {0, 3, 2, 1},
            {1, 0, 2, 3},
            {1, 0, 3, 2},
            {1, 2, 0, 3},
            {1, 2, 3, 0},
            {1, 3, 0, 2},
            {1, 3, 2, 0},
            {2, 0, 1, 3},
            {2, 0, 3, 1},
            {2, 1, 0, 3},
            {2, 1, 3, 0},
            {2, 3, 0, 1},
            {2, 3, 1, 0},
            {3, 0, 1, 2},
            {3, 0, 2, 1},
            {3, 1, 0, 2},
            {3, 1, 2, 0},
            {3, 2, 0, 1},
            {3, 2, 1, 0}
        };
        
        int i, j;
        int max = -1;
        for(i = 0; i < 24; i++){
            max = Math.max(max, getMinutes(A[sequences[i][0]], A[sequences[i][1]], A[sequences[i][2]], A[sequences[i][3]]));
        }
        
        if(max < 0)
            return "";
        else{
            int minutes = max % 60;
            int hours = max / 60;
            
            String result = "";
            if(hours < 10)
                result += "0" + hours;
            else
                result += hours;
            
            result += ":";
            
            if(minutes < 10)
                result += "0" + minutes;
            else
                result += minutes;
            
            return result;
        }
    }
    
    private int getMinutes(int a, int b, int c, int d){
        int hours = 10*a + b;
        int minutes = 10*c + d;
        
        if(hours < 0 || hours > 23 || minutes < 0 || minutes > 59)
            return -1;
        else
            return (60 * hours + minutes);
    }
    
    
    //<문제풀이2 by rock>
    
    //brute-force
    
    //문제풀이1과 똑같은 로직인데, 코드의 양만 줄이고 가독성을 높힌 버전.
    
    //String 더하기 장난질을 많이하고 .compareTo()장난질을 많이해서 문제풀이1보다 성능은 구림.
    
    //Runtime: 20 ms
    //Memory Usage: 40.5 MB
    
    public String largestTimeFromDigits(int[] A) {
        String ans = "";
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < 4; ++k) {
                    if (i == j || i == k || j == k) continue; // avoid duplicate among i, j & k.
                    String h = "" + A[i] + A[j], m = "" + A[k] + A[6 - i - j - k], t = h + ":" + m; // hour, minutes, & time.
                    if (h.compareTo("24") < 0 && m.compareTo("60") < 0 && ans.compareTo(t) < 0) ans = t; // hour < 24; minute < 60; update result.
                }
            }
        }
        return ans;
    }
    
    
    //<문제풀이3 by Self_Learner>
    
    //backtracking
    
    //똑똑하구만
    
    //Runtime: 13 ms
    //Memory Usage: 37.7 MB
    
    String time;
    int[] max = {2, 3, 5, 9}; //각 시간의 자리에 최대치를 정해놔서 이것 이상이면 넘어감.
    
    public String largestTimeFromDigits(int[] A) {
        
        int[] cur = new int[4];
        Arrays.fill(cur, -1);
        
        findLargest(A, new boolean[4], cur, 0);
        return time == null ? "" : time;
    }
    
    private void findLargest(int[] A, boolean[] used, int[] cur, int pos) {
        if (pos == 4) { //시간 4자리수가 다 채워졌다면,
            String s = "" + cur[0] + cur[1] + ":" + cur[2] + cur[3];
            if (time == null || time.compareTo(s) < 0) { //time을 최댓값으로 갱신하고 return
                time = s;
            }
            return;
        }
        if (pos == 1) {
            if (cur[0] == 1 || cur[0] == 0){
                max[1] = 9; //23:59에서 두번째 자리는 첫번째 자리가 2일땐 3이 최대치이지만, 0이나 1일때는 9가 최대치 이기 때문에 업데이트.
            } else if (cur[0] == 2) {
                max[1] = 3;
            }
        }
        for (int j = 0; j < 4; j++) {
            if (used[j] || A[j] > max[pos]) continue; //각 자리의 최대치 이상이면 넘어감.
            cur[pos] = A[j];
            used[j]  = true;
            findLargest(A, used, cur, pos + 1);
            cur[pos] = -1; //[0,1,2,3] 순서대로 다 썼다면, [0,1,2,?] -> [0,1,?,?] -> [0,1,3,2] (if used[j]에서 이미 사용된건 걸러지기 때문)  
            used[j] = false;
        }
    }
    
    
    
    //<문제풀이 4 by tyuan73>
    
    //backtracking
    
    //얘도 나름 신박한게, 문제풀이3에서는 A의 모든 경우의 수를 돌면서
    
    //각 자리수가 오바되지 않으면 ㄱㄱ해서 4*3*2*1 = 24라면,
    
    //얘는 시간 24번중에 되는애 * 분 60개중애 되는애 해서 1440번 돌음.
    
    //물론 얘도 조건에 맞는애만 돌았기 때문에, 문제풀이3과 큰 차이는 없음.
    
    //Runtime: 17 ms
    //Memory Usage: 39.7 MB
    
    public String largestTimeFromDigits(int[] A) {
        int[] count = new int[10];
        for(int a : A) count[a]++;
        for(int i = 23; i >= 0; i--) {
            int a = i / 10, b = i % 10;
            count[a]--;
            count[b]--;
            if (count[a] >= 0 && count[b] >= 0) {
                for(int j = 59; j >= 0; j--) {
                    int p = j / 10, q = j % 10;
                    count[p]--;
                    count[q]--;
                    if (count[p] >= 0 && count[q] >= 0)
                        return a + "" + b + ":" + p + "" + q;
                    count[p]++;
                    count[q]++;
                }
            }
            count[a]++;
            count[b]++;
        }
        return "";
    }
    

}
