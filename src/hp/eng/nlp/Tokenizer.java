package hp.eng.nlp;

import opennlp.tools.tokenize.SimpleTokenizer;
 
public class Tokenizer {
	
	public Tokenizer() {
	}
 
	public String tokenize(String sentence) {
		SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
        String tokens[] = tokenizer.tokenize(sentence);
        String tokenizedSentence = "";

        for(int i = 0; i < tokens.length; i++){
        	tokenizedSentence += tokens[i] + " ";
        }
        
        return tokenizedSentence;
	}
}
 