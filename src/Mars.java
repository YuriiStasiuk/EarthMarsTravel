public class Mars {
	static double x;
	static double y;
	static double phi;
	static double a = 227939100000.0;
	static double e = 0.093315;	
	public Mars(int date) {
		
	}
	public static void nextStep(int step) {
		double r = Math.sqrt(x*x+y*y);
		double deltaPhi = (2*Math.PI*a*a*Math.sqrt(1-e*e))/(step*Math.pow(r, 2));
		phi += deltaPhi;
		r = (a*Math.sqrt(1-e*e))/(1 + e*Math.cos(24-phi));
		x = r * Math.cos(phi);
		y = r * Math.sin(phi);
	}
}
