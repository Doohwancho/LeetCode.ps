package julyChallenge;

public class TaskScheduler {
	
	//<Trial01>
	
	//너무 그지같이 푸는거 아니냐
	
	//좀 간지나게 풀자 좀
	
    public int leastInterval(char[] tasks, int n) {
        
        int rst = 0;
        
        int[] alphabet = new int[26];
        for(char t : tasks){
            alphabet[t-'A']++;
        }
        
        int max = 0;
        for(int i = 0; i < 26; i++){
            max = Math.max(max, alphabet[i]);
        }
        
        for(int k = 0; k < 26; k++){
            if(alphabet[k] == max){
                alphabet[k] = 0;
                break;
            }
        }
        
        rst += (1+n) * (max-1) + 1;
        
        int rest = 0;
        int lastRest = 0;
        int cnt = 0;
        do{
            lastRest = rest;
            rest = 0;
            for(int j = 0; j < 26; j++){
                if(alphabet[j] > 0){
                    alphabet[j]--;
                    rest++;
                    if(rest == n){
                        break;
                    }
                }
            }
            cnt++;
        }
        while(rest > 0);
        
        
        if(cnt-1 > max-1){
            rst += lastRest;
        }
        
        return rst;
    }
    
    
    //<Trial02>
    
    //input이 괴랄해서 어디가 나갔는지 파악이 안되네
	
    public int leastInterval(char[] tasks, int n) {
        
        int[] alphabet = new int[26];
        for(char t : tasks){
            alphabet[t-'A']++;
        }
        
        int maxIdx = 0;
        int max = 0;
        int total = 0;
        for(int i = 0; i < 26; i++){
            if(alphabet[i] > max){
                max = alphabet[i];
                maxIdx = i;
            };
            total += alphabet[i];
        }
        
        int rst = 0;
        int n_ = n;
        int limit = max-1;
        int adder = 0;
        
        while(limit > 0){
            
            for(int j = 0; j < 26; j++){
                if(j != maxIdx && alphabet[j] > 0){
                    alphabet[j]--;
                    n_--;
                    total--;
                    adder++;
                    if(n_ == 0) break;
                }
            }
            
            rst += (n == 0 ? 1+adder : (1+n));
            
            n_ = n;
            adder = 0;
            limit--;
            total--;
        }
        
        return rst + total;
    }
    
    
    //<문제풀이1 by alexander>
    
    //priority queue
    
    //Runtime: 59 ms
    //Memory Usage: 67.4 MB
    
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> counts = new HashMap<Character, Integer>();
        for (char t : tasks) {
            counts.put(t, counts.getOrDefault(t, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b - a); //빈도수 큰 순서대로
        pq.addAll(counts.values());

        int alltime = 0;
        int cycle = n + 1;
        while (!pq.isEmpty()) {
            int worktime = 0;
            List<Integer> tmp = new ArrayList<Integer>();
            for (int i = 0; i < cycle; i++) {
                if (!pq.isEmpty()) {
                    tmp.add(pq.poll()); //빈도수 젤 큰애가 일빠따로 드감. 
                    worktime++;
                }
            }
            for (int cnt : tmp) {
                if (--cnt > 0) {
                    pq.offer(cnt); //pq.offer()는 다시 빈도수 대로 들어가기 때문에 순서 신경써서 안넣어도 됨
                }
            }
            alltime += !pq.isEmpty() ? cycle : worktime; //남은게 있으면 1+n더해주고, 남은게 없으면 찌끄래기들 worktime더해줌
        }
        
        return alltime;
    }
    
    
    //<문제풀이2 by yu6>
    
    //이거라고~~~~~~~~~~
    
    //이게 첨부터 생각하던건데 이 사람은 해냈네
    
    //섹시하구만
    
    //Runtime: 4 ms
    //Memory Usage: 55.2 MB
    
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        int mf = 0, mc = 0;
        for(char c:tasks) mf = Math.max(mf, ++freq[c-'A']);
        for(int f:freq) if(f==mf) mc++; //최빈이랑 같은애들이 꼬리에 남을테니, 얘내들 구해서 맨 마지막에 더해줌
        return Math.max(tasks.length, (n+1)*(mf-1)+mc); //n이 0일걸 대비, Math.max(tasks.length, ~)
    }
}
