package hp.eng.nlp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
 
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
 
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class SentenceDetector {
	
	public SentenceDetector() {
	}
 
    public String[] detect(String sentence) throws InvalidFormatException, IOException {
        InputStream is = new FileInputStream("res/sen_model/en-sent.bin");
        SentenceModel model = new SentenceModel(is);
 
        SentenceDetectorME sdetector = new SentenceDetectorME(model);
 
        String sentences[] = sdetector.sentDetect(sentence);
 
        return sentences;
    }
}
 