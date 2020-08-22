package augustChallenge;

import java.util.Random;
import java.util.TreeMap;

	class Solution {

	    Random rand;
	    Random rand2;
	    int n = 0;
	    int[][] c;
	    
	    public Solution(int[][] rects) {
	        rand = new Random();
	        rand2 = new Random();
	        n = rects.length;
	        c = rects;
	    }
	    
	    public int[] pick() {
	        int[] obj = c[rand.nextInt(n)];
	        int minX = obj[0];
	        int maxX = obj[2];
	        int minY = obj[1];
	        int maxY = obj[3];
	        
	        int xRange = Math.abs(Math.abs(maxX)-Math.abs(minX));
	        int yRange = Math.abs(Math.abs(maxY)-Math.abs(minY));
	        
	        return new int[] {
	            xRange == 0 ? minX : minX + rand2.nextInt(xRange), //rand.nextInt(0)하면 에러남. java.lang.IllegalArgumentException: bound must be positive
	            yRange == 0 ? minY : minY + rand2.nextInt(yRange)
	        };      
	    }
	}
	
	
	//<문제풀이1 by wangzi6147>
	
	//treemap
	
	//Runtime: 59 ms
	//Memory Usage: 46.5 MB

	import java.util.Random;

	class Solution {

	    int[][] rects;
	    Random rand;
	    TreeMap<Integer, int[]> tm;
	    int area = 0;
	    
	    
	    public Solution(int[][] rects) {
	        this.rects = rects;
	        rand = new Random();
	        tm = new TreeMap<>();
	        
	        for(int[] rect : rects){
	            area += (rect[2]-rect[0]+1) * (rect[3]-rect[1]+1); //+1해주는 이유는 한쪽이 0이면 반대쪽이 뭐든 넓이가 0되니까.
	            tm.put(area, rect); //누적으로 넣어줘야 rand.nextInt(area)에서 균등하게 뽑힘
	        }
	    }
	    
	    public int[] pick() {
	        int randomPointInRect = rand.nextInt(area);
	        int targetRect = tm.higherKey(randomPointInRect);
	        int[] targetRectCoord = tm.get(targetRect);
	        return new int[]{
	        	targetRectCoord[0] + (targetRect - randomPointInRect - 1) % (targetRectCoord[2] - targetRectCoord[0] + 1), //targetRectCoord[0] + (targetRect - randomPointInRect - 1) / (targetRectCoord[3] - targetRectCoord[1] + 1),  이렇게 해야 정확한 x값이 나와야 할 것 같은데, 그렇게하면 pass안됨. 
	            targetRectCoord[1] + (targetRect - randomPointInRect - 1) / (targetRectCoord[2] - targetRectCoord[0] + 1)        
	        };
	        /*
	                return new int[]{
			            targetRectCoord[0] + (targetRect - randomPointInRect - 1) / (targetRectCoord[3] - targetRectCoord[1] + 1),
			            targetRectCoord[1] + (targetRect - randomPointInRect - 1) % (targetRectCoord[3] - targetRectCoord[1] + 1)        
			        };
	        		
	        		(x2-x1)말고 이렇게 반대로 해도 되긴 하는데, (y2-y1)
	        		왜 나머지인거지
	        		어씨
	        		
	        		Serialize a point (x,y) in the rectangle to an integer:

						Points can be mapped row by row, from bottom to top;
						In each row, there are width points;
						So point (x,y), can be mapped as follows:
						id = number-of-rows * width + index-in-the-last-row, or id = (y-y0) * width + (x - x0)
						So (y-y0) = id / width, (x-x0) = id % width

					---
					아
					
					targetRectCoord[0]가 지금 현재 사각형의 x1이고
					targetRect는 그 다음으로 큰 사각형의 넓이인데
					그 넓이의 차이가 (targetRect - randomPointInRect - 1),
					근데 이 넓이의 차이는 현재 직사각형의 x범위를 벗어날 수 있잖아?
					그래서 현재 직사각형의 넓이(width)인 (targetRectCoord[2] - targetRectCoord[0] + 1) 을 %해주면
					현재 직사각형 넓이(targetRectCoord[2] - targetRectCoord[0] + 1) 안의 속하는 값중 랜덤한 값이 나오겠지?
					그걸 x1의 왼쪽 아래 꼭짓점인 targetRectCoord[0]에 더해주는거고.
	         */
	    }
	}
}
