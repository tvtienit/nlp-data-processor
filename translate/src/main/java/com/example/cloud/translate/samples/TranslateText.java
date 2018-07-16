/*
 * Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.cloud.translate.samples;

import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Language;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.LanguageListOption;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.google.common.collect.ImmutableList;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

public class TranslateText {
	private static TranslateText instance = null;
	/**
	 * Detect the language of input text.
	 *
	 * @param sourceText
	 *            source text to be detected for language
	 * @param out
	 *            print stream
	 */
	// [START translate_detect_language]
	private static void detectLanguage(String sourceText, PrintStream out) {
		Translate translate = createTranslateService();
		List<Detection> detections = translate.detect(ImmutableList.of(sourceText));
		System.out.println("Language(s) detected:");
		for (Detection detection : detections) {
			out.printf("\t%s\n", detection);
		}
	}
	// [END translate_detect_language]

	/**
	 * Translates the source text in any language to English.
	 *
	 * @param sourceText
	 *            source text to be translated
	 * @param out
	 *            print stream
	 */
	// [START translate_translate_text]
	private static void translateText(String sourceText, PrintStream out) {
		Translate translate = createTranslateService();
		Translation translation = translate.translate(sourceText);
		out.printf("Source Text:\n\t%s\n", sourceText);
		out.printf("Translated Text:\n\t%s\n", translation.getTranslatedText());
	}
	// [END translate_translate_text]

	/**
	 * Translate the source text from source to target language. Make sure that your
	 * project is whitelisted.
	 *
	 * @param sourceText
	 *            source text to be translated
	 * @param sourceLang
	 *            source language of the text
	 * @param targetLang
	 *            target language of translated text
	 * @param out
	 *            print stream
	 */
	// [START translate_text_with_model]
	private static String translateTextWithOptionsAndModel(String sourceText, String sourceLang, String targetLang) {

		Translate translate = createTranslateService();
		TranslateOption srcLang = TranslateOption.sourceLanguage(sourceLang);
		TranslateOption tgtLang = TranslateOption.targetLanguage(targetLang);

		// Use translate `model` parameter with `base` and `nmt` options.
		TranslateOption model = TranslateOption.model("nmt");

		Translation translation = translate.translate(sourceText, srcLang, tgtLang, model);
		return translation.getTranslatedText();
	}
	// [END translate_text_with_model]

	/**
	 * Translate the source text from source to target language.
	 *
	 * @param sourceText
	 *            source text to be translated
	 * @param sourceLang
	 *            source language of the text
	 * @param targetLang
	 *            target language of translated text
	 * @param out
	 *            print stream
	 */
	private static String translateTextWithOptions(String sourceText, String sourceLang, String targetLang,
			PrintStream out) {

		Translate translate = createTranslateService();
		TranslateOption srcLang = TranslateOption.sourceLanguage(sourceLang);
		TranslateOption tgtLang = TranslateOption.targetLanguage(targetLang);

		Translation translation = translate.translate(sourceText, srcLang, tgtLang);
		return translation.getTranslatedText();
	}

	/**
	 * Displays a list of supported languages and codes.
	 *
	 * @param out
	 *            print stream
	 * @param tgtLang
	 *            optional target language
	 */
	// [START translate_list_language_names]
	// [START translate_list_codes]
	private static void displaySupportedLanguages(PrintStream out, Optional<String> tgtLang) {
		Translate translate = createTranslateService();
		LanguageListOption target = LanguageListOption.targetLanguage(tgtLang.orElse("en"));
		List<Language> languages = translate.listSupportedLanguages(target);

		for (Language language : languages) {
			out.printf("Name: %s, Code: %s\n", language.getName(), language.getCode());
		}
	}
	// [END translate_list_codes]
	// [END translate_list_language_names]

	/**
	 * Create Google Translate API Service.
	 *
	 * @return Google Translate Service
	 */
	@SuppressWarnings("deprecation")
	public static Translate createTranslateService() {
		return TranslateOptions.newBuilder().setApiKey("AIzaSyBxa11T4CSP_vvbWtDcPZfomdi1sMzeGh0").build().getService();
	}

	public static TranslateText getInstance() {
		if (instance == null) {
			instance = new TranslateText();
		}
		
		return instance;
	}
	
	public String translate(String text, String sourceLang, String targetLang) {
		return translateTextWithOptionsAndModel(text, sourceLang, targetLang);
	}
}
