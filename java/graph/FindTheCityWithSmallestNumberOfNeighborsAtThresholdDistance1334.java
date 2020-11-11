package graph;

public class FindTheCityWithSmallestNumberOfNeighborsAtThresholdDistance1334 {

	//<Trial01>
	
	//yet
	
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[] rst = new int[n];
        
        for(int i = 0; i < n; i++){
            double[] tmp = new double[n];
            tmp[i] = 0.1;
            
            for(int j = 0; j < edges.length; j++){
                boolean flag = true;
                
                if(tmp[edges[j][0]] > 0){
                    double next = Math.floor(tmp[edges[j][0]]) + edges[j][2];
                    if(next <= distanceThreshold){
                        tmp[edges[j][1]] = next;
                        flag = false;
                    }
                }
                else if(tmp[edges[j][1]] > 0){
                    double next = Math.floor(tmp[edges[j][0]]) + edges[j][2];
                    if(next <= distanceThreshold){
                        tmp[edges[j][0]] = next;
                        flag = false;
                    }
                }
                if(flag){ //if same as prev, break;
                    break;
                }
                rst[i]++;
                
                for(int p = 0; p < n; p++){
                    System.out.print(tmp[p]+" ");
                }
                System.out.println();
            }
            System.out.println();
            for(int q = 0; q < n; q++){
                System.out.print(rst[q]+" ");
            }
            System.out.println();
            System.out.println();
        }
        
        System.out.print("rst");
        
        //pick least with largest num
        int idx = n-1;
        for(int q = 0; q < n; q++){
            System.out.print(rst[q]+" ");
        }
        
        return rst[idx];
    }
}
