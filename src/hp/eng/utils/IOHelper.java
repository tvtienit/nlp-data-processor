package hp.eng.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class IOHelper {
	private static BufferedReader bReader = null;
	private static BufferedWriter bWriter = null;
	
	private static InputStreamReader iReader = null;
	private static OutputStreamWriter oWriter = null;
	
	public static BufferedReader getBufferredReader(String inputFile) throws IOException{
		iReader = new InputStreamReader(new FileInputStream(inputFile), "UTF-8");
		bReader = new BufferedReader(iReader);
		
		return bReader;
	}
	
	public static boolean writeData(String outputFile, ArrayList<String> results) throws IOException {
		Log.info(null, "Writing new file. Please wait...");
		try {
			oWriter = new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-8");
			bWriter = new BufferedWriter(oWriter);
			
			for (String sentence : results) {
				bWriter.append(sentence + "\n");
			}
			
			Log.info(null, "Done");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (bWriter != null) bWriter.close();
				if (oWriter != null) oWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}
}
