import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Handler {
	public static int numberOfDays;
	public static Date date;
	public static int step;
	public static double acceleration;
	public static int velocity;
	public static String toWhichPlanet;
	public static void main(String[] args) throws IOException {
		Handler.readData();		
		int planet;
		if(toWhichPlanet.equals("Mars")
			planet = 2;
		else
			planet = 1;
		for(int i = 0; i < numberOfDays; i++) {
			String nameOfFile = "Orbit" + toString(i+1);
			File file = new File(nameOfFile);
			file.createNewFile();
			new Mars(date);
			new Earth(date);
			new SpaceShift(date, acceleration, velocity, planet);
			for(;;) {
				Mars.nextStep(step);
				Earth.nextStep(step);
			}
		}
	}
	public static void readData() throws IOException {
		File file = new File("StartingData.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader br = new BufferedReader(fileReader);
		String line;
		StringBuilder sb = new StringBuilder();
		double arr[] = new double[4];
		for(int j = 0; j < 7; j++) {
			line = br.readLine();
			sb.append(line);
			for(int i = 0; i < sb.length(); i++) {
				if(sb.charAt(i) == ':') {
					sb.delete(0, i+2);
				}
		   }
		   if(j<4) {
			   arr[j] = Double.parseDouble(sb.toString());
		   }else if (j==4){
			   try {
				   Handler.date=new SimpleDateFormat("MM/dd/yyyy").parse(sb.toString());
			   }catch(java.text.ParseException e) {}
		   }else {
			   if(sb.length() == 1) {
				   Handler.toWhichPlanet = sb.charAt(0);
			   }else {
				   Handler.direction = sb.toString();
			   }
		   	}
		}
		fileReader.close();
		br.close();
		Handler.acceleration = arr[0];
		Handler.velocity = (int)arr[1];
		Handler.step = (int)arr[2];
		Handler.numberOfDays = (int)arr[3];
	}
}
