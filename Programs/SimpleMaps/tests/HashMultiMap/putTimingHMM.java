import org.junit.*;
import jug.*;
import cs2321.*;
import net.datastructures.*;
import java.util.Random;

public class putTimingHMM implements DataSeries {

	private HashMultiMap<String, String> TARGET = init();
	private HashMultiMap<String, String> T = init();

	public HashMultiMap<String, String> init() {
		return new HashMultiMap<String, String>();
	}

	public double[] xAxis() throws Throwable {
		
			double[] xAxis = {2, 4, 8, 16, 20, 32, 40, 64, 128, 256, 400, 512, 1024, 2048, 4000};
			return xAxis;  
         	
	}

	public double[] yAxis() throws Throwable {
		
			int REPS = 100;
			double[] xAxis = xAxis();
			double[] yAxis = new double[xAxis.length];
			HashMultiMap<String, String>[] target = (HashMultiMap<String, String>[])new HashMultiMap[REPS];
			for(int index = 0; index < xAxis.length; index++) {
				double n = xAxis[index];
				
				//setup (for each rep)
				for(int it = 0; it < REPS; it++) {
					target[it] = init();
				}
				// Timing
				long start = System.currentTimeMillis();

				for(int it = 0; it < REPS; it++) {
					for(int count = 0; count < n; count++) {
						target[it].put(""+count, ""+count);
					}
				}
		         	yAxis[index] = (double)(System.currentTimeMillis() - start) / REPS;

			} // end for index
             		return yAxis;
		
	}
}
