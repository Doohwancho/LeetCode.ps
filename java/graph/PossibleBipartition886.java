package graph;

public class PossibleBipartition886 {
	
	//yet

    public boolean possibleBipartition(int N, int[][] dislikes) {
        int[] a = new int[N+1];
        int[] b = new int[N+1];
        int[] yet = new int[N+1];
        
        for(int[] d : dislikes){
            int x = d[0];
            int y = d[1];
            
            if(a[x] == 2){
                if(a[y] == 2) return false;
                b[y] = 2;
                continue;
            } else if(b[x] == 2){
                if(b[y] == 2) return false;
                a[y] = 2;
                continue;
            } else if(a[y] == 2){
                if(a[x] == 2) return false;
                b[x] = 2;
                continue;
            } else if(b[y] == 2){
                if(b[x] == 2) return false;
                a[x] = 2;
                continue;
            }
            
            if(a[x] == 1){
                a[x] = 2;
                if(a[y] == 2) return false;
                b[y] = 2;
                continue;
            } else if(b[x] == 1){
                b[x] = 2;
                if(b[y] == 2) return false;
                a[y] = 2;
                continue;
            } else if(a[y] == 1){
                a[y] = 2;
                if(a[x] == 2) return false;
                b[x] = 2;
                continue;
            } else if(b[y] == 1){
                b[y] = 2;
                if(b[x] == 2) return false;
                a[x] = 2;
                continue;
            }
            
            a[x] = 1;
            a[y] = 1;
            b[x] = 1;
            b[y] = 1;
            yet[x] = y;
            yet[y] = x;
        }
        
        return true;
    }
}
