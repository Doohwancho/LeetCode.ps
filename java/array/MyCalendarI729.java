package array;

public class MyCalendarI729 {
    
	/*
	//<Trial01 - memory limit exceeded>
	
	//start and end are integers in the range [0, 10^9]. 라길래 new int[1000000000]선언했는데 안되네.
	 
	//야 그냥 컴퓨터 좋은거좀 사서 쓰면 안돼냐
	
	
	//book할땐 그 범위가 걸치면 double booking이 되는것이라, 양 끝단이 기존에 booking해놓은것만 겹치는지 확인하면 됨.
	
	//만약 걸쳐있다면 false를 반환하고, 걸쳐있지 않다면, starting point와 ending point만 체크해놓고 true라고 일단 반환함.
	
	//반환 하고 나서 내부적으로  reordering()을 통해, 체크해놨던 범위 안의 숫자들을 1로 바꾸고 싶긴 한데,
	
	//내부적으로 돌게끔하는게 없으므로, 일단 else문에 return 전에 넣어놓음.
	
	int[] container = new int[100000000];
    
	public MyCalendar() {
        
    }
    
    public boolean book(int start, int end) {
    	if(this.container[start] > 0 || this.container[end-1] > 0){
            return false;
        }
        else{
            this.container[start]++;
            this.container[end]--;    
            reordering(start, end);
            return true;
        }
    }
    
    private void reordering(){
        for(int i = start+1; i < end+1; i++){
            this.container[i] += this.container[i-1];
        }
    }
    */
	
	/*
	//<문제풀이1 by alexander>
	
	//[3,7], [start = 5, end = 10] -> false
	
	//[3,7], [start = 8, end = 10] -> true
	
	//아하
	
	//Runtime: 75 ms, faster than 24.64% of Java online submissions for My Calendar I.
	//Memory Usage: 42.1 MB, less than 100.00% of Java online submissions for My Calendar I.
	
    private List<int[]> books = new ArrayList<>();
    public boolean book(int start, int end) {
        for (int[] b : books)
            if (Math.max(b[0], start) < Math.min(b[1], end)) return false;
        books.add(new int[]{ start, end });
        return true;
    }
	
	*/
}
