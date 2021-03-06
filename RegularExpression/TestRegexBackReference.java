package regex;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TestRegexBackReference {
	
	public static void main(String[] args) {
		String inputStr = "One:two:three:four";
		String regexStr = "(.+):(.+):(.+):(.+)";  // pattern to be matched
		String replacementStr = "$4-$3-$2-$1";    // replacement pattern with back references

		// Step 1: Allocate a Pattern object to compile a regex
		Pattern pattern = Pattern.compile(regexStr);

		// Step 2: Allocate a Matcher object from the Pattern, and provide the input
		Matcher matcher = pattern.matcher(inputStr);

		// Step 3: Perform the matching and process the matching result
		//String outputStr = matcher.replaceAll(replacementStr);     // all matches
		//String outputStr = matcher.replaceFirst(replacementStr); // first match only
		//System.out.println(outputStr);   // Output: four-three-two-One

		while (matcher.find()) {
			System.out.println("find() found substring \"" + matcher.group()
			+ "\" starting at index " + matcher.start()
			+ " and ending at index " + matcher.end());
			System.out.println("Group count is: " + matcher.groupCount());
			for (int i = 0; i < matcher.groupCount(); ++i) {
				System.out.println("Group " + i + ": substring=" 
						+ matcher.group(i) + ", start=" + matcher.start(i) 
						+ ", end=" + matcher.end(i));
			}
		}
	}
	
}