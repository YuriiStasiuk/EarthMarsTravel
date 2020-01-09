public class Earth {
	static double x;
	static double y;
	static double phi;
	static double a = 149598261000.0;
	static double e = 0.01671123;	
	public Earth(Date date) {	
		Earth.phi = 259;
		double r = (a*Earth.sqrt(1-e*e))/(1 + e*Earth.cos(24-phi));
		Earth.x = r * Earth.cos(phi);
		Earth.y = r * Earth.sin(phi);
		int time = 0;   ///// add time counter
		int step = 500;
		int numberOfSteps = (int) (time/step);
		for(int i = 0; i < numberOfSteps; i++) {
			Earth.nextStep(step);
		}
	}
	public static void nextStep(int step){
		double r = Math.sqrt(x*x+y*y);
		double deltaPhi = (2*Math.PI*a*a*Math.sqrt(1-e*e))/(step*Math.pow(r, 2));
		phi += deltaPhi;
		r = (a*Math.sqrt(1-e*e))/(1 + e*Math.cos(259-phi));
		x =  (r * Math.cos(phi));
		y =  (r * Math.sin(phi));
	}
}
