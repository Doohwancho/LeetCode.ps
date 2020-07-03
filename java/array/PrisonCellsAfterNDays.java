package julyChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PrisonCellsAfterNDays {
	
	//<Trial01 - time limit exceeded>
	
	//brute-force
	
	//game of life인데
	
	//이게 N을 넣으면 prediction이 가능한 거였나?
	
	//물론 패턴이 정해져있으니까 가능할거같긴 한데 뭐지?
	
    public int[] prisonAfterNDays(int[] cells, int N) {
        while(N > 0){
            for(int i = 1, i_ = 0, j = cells[0]; i < cells.length-1; i++){
                i_ = cells[i];
                cells[i] = j ^ cells[i+1] ^ 1;
                j = i_;
            }
            cells[0] = cells[cells.length-1] = 0;
            N--;
        }
        return cells;
    }
    
    
    //<문제풀이1 by lee215>
    
    //와씨 또라인가
    
    //cells가 계속 바뀌는걸 Arrays.toString으로 map에 박아넣고
    
    //똑같은게 또나오면, (예전에 나온 번째 - N)해서 예전부터 N까지 총 몇번 걸러야 제자리로 돌아가는지 파악하고
    
    //그걸 남은 N에서 나눌만큼 나눈 후, 나머지만 빼냄.
    
    //Arrays.toString()을 여기에 쓰네. 참신허이
    
    //Runtime: 9 ms
    //Memory Usage: 41.8 MB
    
    public int[] prisonAfterNDays(int[] cells, int N) {
        Map<String, Integer> seen = new HashMap<>();
        while (N > 0) {
            int[] cells2 = new int[8];
            seen.put(Arrays.toString(cells), N--);
            for (int i = 1; i < 7; ++i)
                cells2[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
            cells = cells2;
            if (seen.containsKey(Arrays.toString(cells))) {
                N %= seen.get(Arrays.toString(cells)) - N;
            }
        }
        return cells;
    }
    
    //<문제풀이2 by lee215>
    
    //반복되는 패턴을 찾았데.
    
    //어떻게 찾았냐고?
    
    //lee215's avatar
//    lee215
//    January 9, 2019 2:38 PM
//
//    Read More
//    I brute force all cases.
    
    //노가다 했데 ㅋㅋ
    
    //1번째, 7번째, 14번째에 똑같이 반복된데
    
    //이말은 1번째 반복되는 애든 7번째 반복되는 애든 14번째엔 무조건 반복된다는 말이니까,
    
    //%14로 반복되는애들 자르고 나머지 애들만 game of life로 조지면 된데
    
    //반복되는 패턴 찾은거 레전드네 참
    
    //Runtime: 1 ms
    //Memory Usage: 40.3 MB
    
    public int[] prisonAfterNDays(int[] cells, int N) {
        for (N = (N - 1) % 14 + 1; N > 0; --N) {
            int[] cells2 = new int[8];
            for (int i = 1; i < 7; ++i)
                cells2[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
            cells = cells2;
        }
        return cells;
    }
}
