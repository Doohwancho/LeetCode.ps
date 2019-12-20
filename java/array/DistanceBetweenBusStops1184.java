/*
	A bus has n stops numbered from 0 to n - 1 that form a circle. We know the distance between all pairs of neighboring stops where distance[i] is the distance between the stops number i and (i + 1) % n.
	
	The bus goes along both directions i.e. clockwise and counterclockwise.
	
	Return the shortest distance between the given start and destination stops.
	
	
	
	<문제>
	
	이건 그림보고 이해하면 편함
	
	distance = [1,2,3,4], start = 0, destination = 1
	
	0(start)--<1>----- 1(destination)
	|				   |
   <4>                <2>
	|				   |
	3---------<3>------2
	
	 
	 출발지와 도착지가 정해지고, 각 위치 사이의 거리(<>안에 있는 숫자)가 주어진다.
	 
	 첫번째 지역과 마지막 지역이 이어져 있기 때문에, start에서 destination가기 위한 방법은 2가지다. 정방향 혹은 역방향.
	 
	 위의 예시의 경우, 정방향(1)과 역방향(4+3+2 == 9)중 더 짧은 거리를 반환하면 된다.
	 
 */

package Array;

public class DistanceBetweenBusStops1184 {
	
	//<문제풀이1>
	
	//총 거리를 구해 total에 넣고,
	
	//인코스는 start <= in < destination 구해서 넣고,
	
	//아웃코스는 총 거리에서 인코스 빼줘서 구해서
	
	//인코스와 아웃코스 중 더 짧은 거리를 반환.
	
	//문제 input에서 start가 destination보다 더 큰 경우엔(역방향)
	
	//if else문에 else문에 걸려서, 같은 로직인데 destination <= in < start로 바꿈.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Distance Between Bus Stops.
	//Memory Usage: 37.6 MB, less than 100.00% of Java online submissions for Distance Between Bus Stops.
	public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int total = 0;
        int in = 0;
        int out = 0;
        
        if(start < destination){
            for(int i = 0; i < distance.length; i++){
                total += distance[i];
                if(i >= start && i < destination){
                    in += distance[i];
                }
            }
        } else {
            for(int i = 0; i < distance.length; i++){
                total += distance[i];
                if(i >= destination && i < start){
                    in += distance[i];
                } 
            }
        }
        out = total-in;
        return in > out ? out : in;
    }
}
