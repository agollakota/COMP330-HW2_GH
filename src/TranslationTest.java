
import static org.junit.Assert.*;

import org.junit.Test;

public class TranslationTest {

	@Test
	public void testParser() throws Exception {
		Translation translation = new Translation();

		String english = "en";

		String french = "fr";

		String test = "Hello";

		String test2 = "Bonjour";

		String firstCase = translation.parser(english, french, test);

		String secondCase = translation.parser(french, english, test2);

		assertEquals(firstCase, test2);
		assertEquals(secondCase, test);

	}

}
