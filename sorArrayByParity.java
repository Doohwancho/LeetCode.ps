class RecentCounter {

    List tList;
    int listP;
    
    public RecentCounter() {
        tList = new ArrayList<Integer>();
        listP = 0;
    }
    
    public int ping(int t) {
        int res = 1;
        tList.add(t);
        listP += 1;
        int left = t - 3000;
        if (left > 0) {
            int leftT = findLeftP(tList, left);  
            res = listP - leftT;
        }
        return left > 0 ? res : tList.size();
    }
    
    
    private int findLeftP(List<Integer> tList, int left) {
        int head = 0;
        int end = tList.size() - 1;
        
            while (end > head + 1) {
                int mid = end + (head - end) / 2;
                if (tList.get(mid) >= left) {
                    end = mid;
                } else {
                    head = mid;
                }
            }            
        return tList.get(head) >= left ? head : end;
    }   
}