import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CsvFileReaderExample extends SentimentTest {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Please specify input and output file names");
			System.exit(-1);
		}
		String input=args[0];
		String output=args[1];
		String csvFile = "/Users/rajka/csv/"+input+".csv";
		BufferedReader br = null;
		String line = "";
		try {
			br = new BufferedReader(new FileReader(csvFile));
			int n = 0;
			while ((line = br.readLine()) != null) {
				if (line.trim().length() == 0) {
					System.out.println(line.trim().length());
					n = n + 1;
					System.out.println("Line No : " + n);
					String text = line;
					System.out.println("Text" + text);
					CsvFileReaderExample obj = new CsvFileReaderExample();
					obj.sentimentResult(text,output);
				} else {
					String splChrs = "-./@#$%^&_+=()";
					boolean found = line.matches("[" + splChrs + "]+");
					System.out.println("value of found:" + found);
					if (found == true) {
						n = n + 1;
						System.out.println("Line No : " + n);
						String text = line;
						System.out.println("Text" + text);
						CsvFileReaderExample obj = new CsvFileReaderExample();
						obj.sentimentResult("",output);
					} else {
						n = n + 1;
						System.out.println("Line No : " + n);
						String text = line;
						System.out.println("Text" + text);
						CsvFileReaderExample obj = new CsvFileReaderExample();
						obj.sentimentResult(text,output);
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
