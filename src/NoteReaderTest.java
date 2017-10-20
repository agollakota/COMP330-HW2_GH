
import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

//tests for expected output of noteReader class

public class NoteReaderTest {
	
	NoteReader nr = new NoteReader();

	@Test
	public void testNotesByEntry() {
		String expected = "List: [Sheep.txt]";
		File file = new File("txt/");
		assertEquals(expected, nr.notesByEntry(file, "sheep"));
	}

	@Test
	public void testSortByEntry() {
		String expected = "List: [2 - Sheep.txt][0 - Shopping.txt][0 - SF.txt][0 - NFL.txt][0 - Frost.txt][0 - Eggs.txt][0 - AFI.txt]";
		File file = new File("txt/");
		assertEquals(expected, nr.sortByEntry(file, "sheep"));
	}

	@Test
	public void testContainsMention() {
		String expected = "List: [2 - Eggs.txt][1 - Shopping.txt][1 - Sheep.txt][1 - NFL.txt][1 - Frost.txt][1 - AFI.txt][0 - SF.txt]";
		File file = new File("txt/");
		assertEquals(expected, nr.containsMention(file));
	}

	@Test
	public void testGetKeywords() {
		String expected = "List: [top20][threebagsfull][footballseason][bestmovies][needwool?][hungry][superbowl][healthy][vacation]"
				+ "[Poetry][Inspiring][breakfast][travel][fresh][groceries][top100]";
		File file = new File("txt/");
		assertEquals(expected, nr.getKeywords(file));
	}

	@Test
	public void testTopSort() {
		String expected = "List: [5 - Sheep.txt][5 - Eggs.txt][3 - Shopping.txt][3 - SF.txt][3 - NFL.txt][3 - Frost.txt][3 - AFI.txt]";
		File file = new File("txt/");
		assertEquals(expected, nr.topSort(file));
	}

}
