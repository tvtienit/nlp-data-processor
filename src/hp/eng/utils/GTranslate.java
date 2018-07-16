package hp.eng.utils;

import com.example.cloud.translate.samples.TranslateText;

public class GTranslate {
	private static GTranslate instance = null;
	private TranslateText translator = null;
	
	public GTranslate() {
		translator = TranslateText.getInstance();
	}
	
	public static GTranslate getInstance() {
		if (instance == null) {
			instance = new GTranslate();
		}
		
		return instance;
	}
	
	public String translate(String text, String sourceLang, String targetLang) {
		return translator.translate(text, sourceLang, targetLang);
	}
}
