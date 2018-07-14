package hp.eng.nlp;

import java.io.IOException;

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
    	case 0:
    		String[] marks = { ".", ";", "," };
    		MarkHandler mHandler = new MarkHandler();
    		mHandler.detect("data/mark.inp", "data/mark.out", marks);
    		break;
    	case 1: // English segmentation based on OpenNLP 
    		NLPHandler sentHandler = new NLPHandler();
            sentHandler.handle("data/harry_potter.eng", "OutputFileName");
    		break;
    	default:
    		Log.error("Unsupported option");
    		break;
    	}
    }
}
 