package hp.eng.hdl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import hp.eng.utils.IOHelper;
import hp.eng.utils.Log;

public class MarkHandler {
	private BufferedReader bReader = null;

	public boolean detect(String inputFile, String outputFile, String[] marks) {
		try {
			Log.info(null, "Handling file. Please wait...");
			bReader = IOHelper.getBufferredReader(inputFile);
			String line = null;
			ArrayList<String> results = new ArrayList<>();
			while ((line = bReader.readLine()) != null) {
				results.addAll(this.segment(line, marks));
			}

			IOHelper.writeData(outputFile, results);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (bReader != null) {
				try {
					bReader.close();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
		}

		return true;
	}

	private ArrayList<String> segment(String data, String[] marks) {
		ArrayList<String> sentences = new ArrayList<String>();
		sentences.add(data);
		for (String mark : marks) {
			sentences.addAll(segment(sentences, mark));
		}
		
		sentences.replaceAll(String::trim);
		return sentences;
	}

	private ArrayList<String> segment(ArrayList<String> data, String marks) {
		ArrayList<String> sentences = new ArrayList<String>();
		for (String line : data) {
			sentences.addAll(Arrays.asList(line.split("[" + marks + "]")));
		}

		data.clear();
		return sentences;
	}
}
