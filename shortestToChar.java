package LeetCode11;


class shortestToChar1 {
    public int[] shortestToChar(String S, char C) {
        boolean firstCharFound = false;
        int lastCharLoc = 0;
        int[] shortDist = new int[S.length()];
        
        for(int i=0;i<S.length();i++){
            char currChar = S.charAt(i);
            
            if(!firstCharFound){
                if(currChar != C){
                    shortDist[i] = i;    
                } else {
                    firstCharFound = true;
                    lastCharLoc = i;
                    shortDist[i] = 0;
                    for(int j=0;j<i;j++){
                        shortDist[j] = i-shortDist[j];
                    }
                }
            } else {
                if(currChar != C){
                    shortDist[i] = i-lastCharLoc;
                } else {
                    shortDist[i]=0;
                    for(int j=lastCharLoc+1;j<i;j++){
                        if((i-lastCharLoc)<2*shortDist[j]){
                            shortDist[j]=i-j;
                        }
                    }
                    lastCharLoc = i;
                }
            }
        }
        return shortDist;
    }
}