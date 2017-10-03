import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.PropertiesUtils;

public class SentimentTest {
	private String text = null;
	private static final String NEW_LINE_SEPARATOR = "\n";

	@SuppressWarnings("resource")
	public void sentimentResult(String str, String output) {
		try {
			String text = str;
			String result = custSentiment(text);
			String outputFile = output;
			String destinationCSVFile = "/Users/rajka/csv/" + outputFile + ".csv";
			;
			FileWriter fstream = new FileWriter(destinationCSVFile, true);
			BufferedWriter out = null;
			out = new BufferedWriter(fstream);
			if (result.equals("")) {
				String fstring1 = "Empty Line" + "|" + "-99999";
				out.write(fstring1);
				System.out.println(fstring1);
				out.append(NEW_LINE_SEPARATOR);
				out.flush();
				System.out.println("*** Also wrote this information to file1: " + destinationCSVFile);
			} else {
				String fstring = text + "|" + result;
				out.write(fstring);
				out.append(NEW_LINE_SEPARATOR);
				out.flush();
				System.out.println("*** Also wrote this information to file: " + destinationCSVFile);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String custSentiment(String str) {
		this.text = str;
		if (text.equals("")) {
			return "";
		}
		StanfordCoreNLP pipeline = new StanfordCoreNLP(PropertiesUtils.asProperties("annotators",
				"tokenize,cleanxml,ssplit,regexner,truecase,pos,lemma,ner,gender,parse,sentiment",
				"ssplit.isOneSentence", "true", "parse.model", "edu/stanford/nlp/models/srparser/englishSR.ser.gz",
				"tokenize.language", "en"));

		Annotation annotation = pipeline.process(text);
		List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		for (CoreMap sentence : sentences) {
			String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
			if (sentiment.equalsIgnoreCase("Very Negative")) {
				return sentiment;
			} else if (sentiment.equalsIgnoreCase("Negative")) {
				return sentiment;
			} else if (sentiment.equalsIgnoreCase("Positive")) {
				return sentiment;
			} else if (sentiment.equalsIgnoreCase("Very Positive")) {
				return sentiment;
			} else if (sentiment.equalsIgnoreCase("Neutral")) {
				return sentiment;
			}
		}
		return str;
	}
}
