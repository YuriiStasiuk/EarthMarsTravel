import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Handler {
	public static int numberOfDays;
	public static int date;
	public static int step;
	public static double acceleration;
	public static int velocity;
	public static void main(String[] args) throws IOException {
		Handler.readData();		
		for(int i = 0; i < numberOfDays; i++) {
			//initialize new file
			new Mars(date);
			new Earth(date);
			for(;;) {
				Mars.nextStep(step);
				Earth.nextStep(step);
			}
		}
	}
	public static void readData() throws IOException {
		File file = new File("");
		FileReader fr = new FileReader(file);
		BufferedReader bf = new BufferedReader(fr);
		char[] charsA = new char[8];
	    int charsReadA = bf.read(charsA, 13, 21);
	    String acceleration;
	    if (charsReadA != -1) {
	        acceleration = new String(charsA, 0, charsReadA);
	    } else {
	        acceleration = "";
	    }
	    Handler.acceleration = Double.valueOf(acceleration);
	    char[] charsV = new char[8];
	    int charsReadV = bf.read(charsA, 13, 21);
	    String velocity;
	    if (charsReadV != -1) {
	        velocity = new String(charsV, 0, charsReadV);
	    } else {
	        velocity = "";
	    }
	    Handler.velocity = Integer.valueOf(velocity);
	}
}
