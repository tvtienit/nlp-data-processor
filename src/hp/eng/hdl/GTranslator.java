package hp.eng.hdl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import hp.eng.utils.GTranslate;
import hp.eng.utils.IOHelper;
import hp.eng.utils.Log;

public class GTranslator {
	private BufferedReader bReader = null;
	private GTranslate translator = GTranslate.getInstance();
	
	public boolean handle(String inputFile, String outputFile) {
		Log.info(null, "Handling file. Please wait...");
		try {
			bReader = IOHelper.getBufferredReader(inputFile);
			String line = null;
			ArrayList<String> results = new ArrayList<>();

			while ((line = bReader.readLine()) != null) {
				results.add(translator.translate(line, "vi", "en"));
			}
			
			IOHelper.writeData(outputFile, results);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
				try {
					if (bReader != null) bReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
		}
		
		return true;
	}
}
