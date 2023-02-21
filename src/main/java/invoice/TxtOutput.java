package invoice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TxtOutput {
	
	String newPath; 
	FileWriter fw; 
	BufferedWriter bw;
	
	public TxtOutput(String newPath) {
		this.newPath = newPath;
		
	}
	
	
	protected void write(String line, double result) throws IOException{ 
	 fw = new FileWriter(newPath, true);
	 bw = new BufferedWriter(fw);
	 bw.write(line += " " + result);
	 bw.newLine();
	 bw.newLine();
	 bw.close();
	}
	
	protected void write(String line) throws IOException{ 
	 fw = new FileWriter(newPath, true);
	 bw = new BufferedWriter(fw);
	 bw.write(line);
	 bw.newLine();
	 bw.close();
		
	}
}
