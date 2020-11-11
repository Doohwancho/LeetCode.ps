package graph;

public class FindTheCityWithSmallestNumberOfNeighborsAtThresholdDistance1334 {

	//yet
	
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[] rst = new int[n];
        
        for(int i = 0, curr = 0; i < n; i++){
            int[] tmp = new int[n];
            //initiate
            for(int j = 0; j < n; j++){
                if(edges[j][0] == i){
                    tmp[i] += edges[j][2];
                    i = edges[j][1];
                }
                else if(edges[j][1] == i){
                    tmp[i] += edges[j][2];
                    i = edges[j][0];
                }
                if(tmp[i] > distanceTrheshold){
                    break;
                }
                rst[i]++;
            }
        }
        
        //pick least with largest num
        int idx = n-1;
        for(int i = 0; i < n; i++){
            
        }
        
        return rst[idx];
    }
}
