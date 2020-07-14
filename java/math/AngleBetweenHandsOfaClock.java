package julyChallenge;

public class AngleBetweenHandsOfaClock {

	//<문제풀이1>
	
	//minutes/60하면 60이 아닌이상 0밖에 안나와서, 형변환을써서 우회함.
	
	//Runtime: 0 ms
	//Memory Usage: 38.7 MB
	
    public double angleClock(int hour, int minutes) {
        double h = ((30 * hour) + (30 * ((double)minutes/60))) % 360;
        double m = 6 * minutes;
        return Math.abs(h-m) < 180 ? Math.abs(h-m) : 360 - Math.abs(h-m);
    }
    
    
}
