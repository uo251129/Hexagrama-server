package audiveris;

import org.audiveris.omr.Main;

public class AudiverisAdapter {
	
	private void processImage(String filename){
		String[] params = new String[6];
        params[0] = "-batch";
        params[1] = "-export";
        params[2] = "-output";
        params[3] = ".\\output";
        params[4] = "--";
        params[5] = ".\\data\\"+filename+".png";

        Main.main(params);
	}
	
	public void convertImage(String filename) {
		processImage(filename);
	}
}
