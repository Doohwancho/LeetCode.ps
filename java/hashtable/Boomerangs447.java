package HashTable;

import java.util.HashMap;
import java.util.Map;

public class Boomerangs447 {

	/*
	 * //
	 * 
	 * //어레이 안에 좌표가 3개만 있는줄 알았자너~
	 * 
	 * private static boolean distance(int[] a1, int[] a2, int[] a3) { return
	 * Math.pow(a1[0]-a2[0], 2)+Math.pow(a1[1]-a2[1], 2) == Math.pow(a1[0]-a3[0],
	 * 2)+Math.pow(a1[1]-a3[1], 2); }
	 * 
	 * public static int numberOfBoomerangs(int[][] points) { int rst = 0; for(int i
	 * = 0; i < 3; i++) { int j = 0; int k = 0; while(i+j+k < 3) { if(j == i) j++;
	 * if(k == j || k == i) k++; } if(distance(points[i], points[j], points[k]))
	 * rst++; if(distance(points[i], points[k], points[j])) rst++; } return rst; }
	 */

	/*
	 * //
	 * 
	 * //너무 많은 input 넣으면, permutation 하는 경우의 수가 너무 많아서 에러 걸림.
	 * 
	 * private static boolean distance(int[] a1, int[] a2, int[] a3) { return
	 * Math.pow(a1[0]-a2[0], 2)+Math.pow(a1[1]-a2[1], 2) == Math.pow(a1[0]-a3[0],
	 * 2)+Math.pow(a1[1]-a3[1], 2); }
	 * 
	 * public static int numberOfBoomerangs(int[][] points) { int rst = 0; for(int i
	 * = 0; i < points.length; i++) { for(int j = 0; j < points.length; j++) {
	 * for(int k = 0; k < points.length; k++) { if((i != j && j != k && i != k) &&
	 * distance(points[i], points[j], points[k])) rst++; } } } return rst; }
	 */

	/*
	 * public static int numberOfBoomerangs(int[][] points) { //2중 for문쓰고, i-j랑 i+j를
	 * 만들어서, 나머지와 비교. //비교가 call by reference 일텐데 어떻게? //비교 수 * 2 int rst = 0;
	 * for(int i = 0; i < points.length; i++) { for(int j = i+1; j < points.length;
	 * j++) { int compare1 = points[i][0] * 2 - points[j][0]; int compare2 =
	 * points[i][1] * 2 - points[j][1]; int compare3 = points[j][0] * 2 -
	 * points[i][0]; int compare4 = points[j][1] * 2 - points[i][1];
	 * 
	 * for(int k = j+1; k < points.length; k++) {
	 * System.out.println(i+"    "+j+"     "+k+"    "+compare1+"    "+compare2+"   "
	 * +compare3+"    "+compare4); if(points[k][0] == compare1 && points[k][1] ==
	 * compare2 || points[k][0] == compare3 && points[k][1] == compare4) {
	 * System.out.println("true"); rst++; } } } } return rst*2; }
	 */

//<문제풀이 by fabrizio3>

//int[][] points = {{0,0},{1,0},{2,0}};을 예로들면, 매 for문마다, 다음과 같이 distances 맵이 형성된다.

//{1=1, 4=1}
//{1=2}
//{1=1, 4=1}

//여기서 점 A로부터의 거리가 같으면, {1=2}처럼 2 이상 값이 나와 겹치게 되는데, 이 부분만 결과값에 더해준다.

//마지막에 등장하는 for문은 점사이들의 거리들이 있으면, 그중 2개만 고르는 경우의 수를 구한 것. 
//nP2 = n!/(n-2)! = n * (n-1)

	public static int numberOfBoomerangs(int[][] points) {
		int boomerangs = 0;
		for (int i = 0; i < points.length; i++) {
			Map<Integer, Integer> distances = new HashMap<>();
			for (int j = 0; j < points.length; j++) {
				if (i == j)
					continue;
				int dx = points[i][0] - points[j][0];
				int dy = points[i][1] - points[j][1];
				int dSquare = (dx * dx) + (dy * dy);
				if (distances.containsKey(dSquare)) {
					distances.put(dSquare, distances.get(dSquare) + 1);
				} else {
					distances.put(dSquare, 1);
				}
			}

			System.out.println(distances);
			for (Integer radius : distances.keySet()) {
				int numberOfPoints = distances.get(radius);
				if (numberOfPoints > 1) {
					boomerangs += numberOfPoints * (numberOfPoints - 1); // n*(n-1)
				}
			}
		}
		return boomerangs;
	}

	public static void main(String[] args) {
		int[][] points = { { 0, 0 }, { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		//int[][] points = {{0,0},{1,0},{2,0}};

		System.out.println(numberOfBoomerangs(points));
	}
}