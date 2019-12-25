public class SpaceShift {
	static double G = 6.67 * Math.pow(10, -11);
	static double mSun = (int) (2*Math.pow(10, 20));
	static double mEarth = (int) (5.9 * Math.pow(10, 24));
	static double mMars = (int) (6.4 * Math.pow(10, 23));
	static double a = 149598261000.0;
	static double e = 0.01671123;
	static double acceleration;
	static double x;
	static double y;
	static double v;
	static double vx;
	static double vy;
	static double phi;
	public SpaceShift(double accelaration,double v) {
		this.acceleration = accelaration;
		this.v = v;		
		phi = Earth.phi + 0.4;
		double r = (a*Math.sqrt(1-e*e))/(1 + e*Math.cos(259-phi));
		x = (r * Math.cos(phi));
		y = (r * Math.sin(phi));
		SpaceShift.velocities();
	}
	public static void velocities() {
		double x0 = Earth.x;
		double y0 = Earth.y;
		double r1 = (a*Math.sqrt(1-e*e))/(1 + e*Math.cos(259-Earth.phi-0.1));
		double x1 = r1 * Math.cos(phi+0.1);
		double y1 = r1 * Math.sin(phi+0.1);
		double deltaX = x0 - x1;
		double deltaY = y0 - y1;
		if(deltaY==0) {
			vx = v;
			vy = 0;
		}
		else if(deltaX==0) {
			vy = v;
			vx = 0;
		}
		else {
		vx = Math.sqrt(v*v/(1+Math.pow((deltaX/deltaY),2)));
		if(deltaX<0)
			vx*= -1;
		vy = Math.sqrt(v*v/(1+Math.pow((deltaY/deltaX),2)));
		if(deltaY<0)
			vy*= -1;
		}
	}
	public static void nextStepToMars(int step) {
		double axSun = (G*mSun*x)/(Math.pow(x*x+y*y, 3/2));
		double axEarth = (G*mSun*(x-Earth.x))/(Math.pow((x-Earth.x)*(x-Earth.x)+(y-Earth.y)*(y-Earth.y), 3/2));
		double axMars = (G*mSun*(x-Mars.x))/(Math.pow((x-Mars.x)*(x-Mars.x)+(y-Mars.y)*(y-Mars.y), 3/2));
		double axEngine = acceleration*(x-Mars.x)/(Math.pow((x-Mars.x)*(x-Mars.x)+(y-Mars.y)*(y-Mars.y), 1/2));
		double aySun = (G*mSun*y)/(Math.pow(x*x+y*y, 3/2));
		double ayEarth = (G*mSun*(y-Earth.y))/(Math.pow((x-Earth.x)*(x-Earth.x)+(y-Earth.y)*(y-Earth.y), 3/2));
		double ayMars = (G*mSun*(y-Mars.y))/(Math.pow((x-Mars.x)*(x-Mars.x)+(y-Mars.y)*(y-Mars.y), 3/2));
		double ayEngine = acceleration*(y-Mars.y)/(Math.pow((x-Mars.x)*(x-Mars.x)+(y-Mars.y)*(y-Mars.y), 1/2));
		double ax = -axSun - axEarth - axMars + axEngine;
		double ay = -aySun - ayEarth - ayMars + ayEngine;
		x = x + vx*step + ax*step*step/2;
		y = y + vy*step + ay*step*step/2;
		vx += ax*step;
		vy += ay*step;
	}
	public static void nextStepFromSun(int step) {
		double axSun = (G*mSun*x)/(Math.pow(x*x+y*y, 3/2));
		double axEarth = (G*mSun*(x-Earth.x))/(Math.pow((x-Earth.x)*(x-Earth.x)+(y-Earth.y)*(y-Earth.y), 3/2));
		double axMars = (G*mSun*(x-Mars.x))/(Math.pow((x-Mars.x)*(x-Mars.x)+(y-Mars.y)*(y-Mars.y), 3/2));
		double axEngine = acceleration*x/(Math.pow((x-Mars.x)*(x-Mars.x)+(y-Mars.y)*(y-Mars.y), 1/2));
		double aySun = (G*mSun*y)/(Math.pow(x*x+y*y, 3/2));
		double ayEarth = (G*mSun*(y-Earth.y))/(Math.pow((x-Earth.x)*(x-Earth.x)+(y-Earth.y)*(y-Earth.y), 3/2));
		double ayMars = (G*mSun*(y-Mars.y))/(Math.pow((x-Mars.x)*(x-Mars.x)+(y-Mars.y)*(y-Mars.y), 3/2));
		double ayEngine = acceleration*y/(Math.pow((x-Mars.x)*(x-Mars.x)+(y-Mars.y)*(y-Mars.y), 1/2));
		double ax = -axSun - axEarth - axMars + axEngine;
		double ay = -aySun - ayEarth - ayMars + ayEngine;
		x = x + vx*step + ax*step*step/2;
		y = y + vy*step + ay*step*step/2;
		vx += ax*step;
		vy += ay*step;
	}
	public static void nextStepTangent(int step) {
		double axSun = (G*mSun*x)/(Math.pow(x*x+y*y, 3/2));
		double axEarth = (G*mSun*(x-Earth.x))/(Math.pow((x-Earth.x)*(x-Earth.x)+(y-Earth.y)*(y-Earth.y), 3/2));
		double axMars = (G*mSun*(x-Mars.x))/(Math.pow((x-Mars.x)*(x-Mars.x)+(y-Mars.y)*(y-Mars.y), 3/2));
		double axEngine = acceleration*y/(Math.pow((x-Mars.x)*(x-Mars.x)+(y-Mars.y)*(y-Mars.y), 1/2));
		double aySun = (G*mSun*y)/(Math.pow(x*x+y*y, 3/2));
		double ayEarth = (G*mSun*(y-Earth.y))/(Math.pow((x-Earth.x)*(x-Earth.x)+(y-Earth.y)*(y-Earth.y), 3/2));
		double ayMars = (G*mSun*(y-Mars.y))/(Math.pow((x-Mars.x)*(x-Mars.x)+(y-Mars.y)*(y-Mars.y), 3/2));
		double ayEngine = acceleration*x/(Math.pow((x-Mars.x)*(x-Mars.x)+(y-Mars.y)*(y-Mars.y), 1/2));
		double ax = -axSun - axEarth - axMars + axEngine;
		double ay = -aySun - ayEarth - ayMars + ayEngine;
		x = x + vx*step + ax*step*step/2;
		y = y + vy*step + ay*step*step/2;
		vx += ax*step;
		vy += ay*step;
	}
}
