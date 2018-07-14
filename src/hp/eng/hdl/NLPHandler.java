package hp.eng.hdl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import hp.eng.nlp.SentenceDetector;
import hp.eng.nlp.Tokenizer;
import hp.eng.utils.IOHelper;
import hp.eng.utils.Log;

public class NLPHandler {
	private BufferedReader bReader = null;
	
	private SentenceDetector sentDetector;
	private Tokenizer tokenizer;
	
	public NLPHandler() {
		sentDetector = new SentenceDetector();
		tokenizer = new Tokenizer();
	}

	public boolean handle(String inputFile, String outputFile) {
		Log.info(null, "Handling file. Please wait...");
		try {
			bReader = IOHelper.getBufferredReader(inputFile);
			String line = null;
			String[] sentences = null;
			ArrayList<String> results = new ArrayList<String>();
			final Pattern doubleQuotation = Pattern.compile("[\u201c\u201d]");
			final Pattern singleQuotation = Pattern.compile("[\u2018\\u2019]");

			while ((line = bReader.readLine()) != null) {
				line = doubleQuotation.matcher(line).replaceAll("\"");
				line = singleQuotation.matcher(line).replaceAll("'");
				sentences = sentDetector.detect(line);
				for (String sentence : sentences) {
					sentence = tokenizer.tokenize(sentence);
					results.add(sentence);
				}
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
