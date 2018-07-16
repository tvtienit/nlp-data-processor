package hp.eng.nlp;

import java.io.IOException;

import hp.eng.hdl.GTranslator;
import hp.eng.hdl.MarkHandler;
import hp.eng.hdl.NLPHandler;
import hp.eng.utils.InputScanner;
import hp.eng.utils.Log;

public class MainNLP {
    public static void main(String[] args) throws IOException {
    	Log.write(	""							  +
    			    "Please choose an action: \n" +
    				"1. Mark segmentation	  \n" +
    				"2. Sentence segmentation based on OpenNLP"    			
    			 );
    	Log.write("Your option: ", false);
    	switch(InputScanner.getInt()) {
    	case 1:
    		String[] marks = { "." };
    		MarkHandler mHandler = new MarkHandler();
    		mHandler.detect("data/harry_potter.tkn.vi", "data/harry_potter.vi.out", marks);
    		break;
    	case 2: // English segmentation based on OpenNLP 
    		NLPHandler sentHandler = new NLPHandler();
            sentHandler.handle("data/harry_potter.eng", "OutputFileName");
    		break;
    	case 3: // Google Translator
    		GTranslator translator = new GTranslator();
    		translator.handle("data/trans.inp", "data/trans.out");
    		break;
    	default:
    		Log.error("Unsupported option");
    		break;
    	}
    }
}
 