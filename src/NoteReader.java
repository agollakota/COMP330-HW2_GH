
import java.util.*;
import java.io.File;
import java.util.regex.*;
import java.io.FileInputStream;

//class that analyzes a folder of notes and
//sorts/retrieves the notes and strings within them

public class NoteReader {

	// retrieves all notes that contain the passed string parameter
	public String notesByEntry(File dir, String keyword) {

		File path = dir;
		String listString = "List: ";
		ArrayList<String> list = new ArrayList<String>();

		if (path.exists()) {
			Pattern p = Pattern.compile(keyword);

			for (File f : path.listFiles()) {
				if (!f.isFile())
					continue;
				try {
					FileInputStream fis = new FileInputStream(f);
					byte[] data = new byte[fis.available()];
					fis.read(data);
					String text = new String(data);
					Matcher m = p.matcher(text);
					if (m.find()) {
						list.add(f.getName());
					}
					fis.close();
				} catch (Exception e) {
					System.out.println("\t Error processing file : " + f.getName());
				}
			}
		} else {
			System.out.println("Path does not exist.");
		}
		for (String s : list) {
			listString += "[" + s + "]";
		}
		return (listString);
	}

	// organizes notes by number of occurrences in the text file of
	// the passed string parameter from greatest to least
	public String sortByEntry(File dir, String keyword) {

		File path = dir;
		String listString = "List: ";
		ArrayList<String> list = new ArrayList<String>();

		if (path.exists()) {
			Pattern p = Pattern.compile(keyword);
			ArrayList<String> c = new ArrayList<String>();

			for (File f : path.listFiles()) {
				if (!f.isFile())
					continue;
				try {
					FileInputStream fis = new FileInputStream(f);
					byte[] data = new byte[fis.available()];
					fis.read(data);
					String text = new String(data);
					Matcher m = p.matcher(text);
					while (m.find()) {
						c.add(f.getName());
					}
					fis.close();
				} catch (Exception e) {
					System.out.println("\t Error processing file : " + f.getName());
				}

			}
			int counter = 0;
			String temp;
			for (File g : path.listFiles()) {
				counter = Collections.frequency(c, g.getName());
				temp = Integer.toString(counter) + " - " + g.getName();
				list.add(temp);
			}
			Collections.sort(list);
			Collections.reverse(list);
		} else {
			System.out.println("Path does not exist.");

		}
		for (String s : list) {
			listString += "[" + s + "]";
		}
		return listString;
	}

	// retrieves and organizes all notes containing mentions
	// (words preceded by '@')from greatest to least
	public String containsMention(File dir) {

		File path = dir;
		String listString = "List: ";

		if (path.exists()) {
			Pattern p = Pattern.compile("@");
			ArrayList<String> list = new ArrayList<String>();
			ArrayList<String> c = new ArrayList<String>();

			for (File f : path.listFiles()) {
				if (!f.isFile())
					continue;
				try {
					FileInputStream fis = new FileInputStream(f);
					byte[] data = new byte[fis.available()];
					fis.read(data);
					String text = new String(data);
					Matcher m = p.matcher(text);
					while (m.find()) {
						c.add(f.getName());
					}
					fis.close();
				} catch (Exception e) {
					System.out.println("\t Error processing file : " + f.getName());
				}

			}
			int counter = 0;
			String temp;
			for (File g : path.listFiles()) {
				counter = Collections.frequency(c, g.getName());
				temp = Integer.toString(counter) + " - " + g.getName();
				list.add(temp);
			}
			Collections.sort(list);
			Collections.reverse(list);

			for (String ele : list) {
				listString += "[" + ele + "]";
			}
		} else {
			System.out.println("Path does not exist.");

		}
		return listString;
	}

	// retrieves a list of all existing keywords (words preceded by '#')
	public String getKeywords(File dir) {

		File path = dir;
		String listString = "List: ";
		Set<String> set = new HashSet<String>();

		if (path.exists()) {
			Pattern p = Pattern.compile("#(\\S+)");

			for (File f : path.listFiles()) {
				if (!f.isFile())
					continue;
				try {
					FileInputStream fis = new FileInputStream(f);
					byte[] data = new byte[fis.available()];
					fis.read(data);
					String text = new String(data);
					Matcher m = p.matcher(text);

					while (m.find()) {
						String name = m.group(1);
						set.add(name);
					}
					fis.close();
				} catch (Exception e) {
					System.out.println("\t Error processing file : " + f.getName());
				}
			}
			for (String ele : set) {
				listString += "[" + ele + "]";
			}
		} else {
			System.out.println("Path does not exist.");
		}
		return listString;
	}

	// sorts each note file by the total weight, defined by how many
	// mentions and keywords are present in the file from greatest to least
	public String topSort(File dir) {

		File path = dir;
		String listString = "List: ";
		ArrayList<String> list = new ArrayList<String>();

		if (path.exists()) {
			Pattern p = Pattern.compile("@");
			Pattern b = Pattern.compile("#");
			ArrayList<String> c = new ArrayList<String>();

			for (File f : path.listFiles()) {
				if (!f.isFile())
					continue;
				try {
					FileInputStream fis = new FileInputStream(f);
					byte[] data = new byte[fis.available()];
					fis.read(data);
					String text = new String(data);
					Matcher m = p.matcher(text);
					Matcher o = b.matcher(text);
					while (m.find() || o.find()) {
						c.add(f.getName());
					}
					fis.close();
				} catch (Exception e) {
					System.out.println("\t Error processing file : " + f.getName());
				}

			}
			int counter = 0;
			String temp;
			for (File g : path.listFiles()) {
				counter = Collections.frequency(c, g.getName());
				temp = Integer.toString(counter) + " - " + g.getName();
				list.add(temp);
			}
			Collections.sort(list);
			Collections.reverse(list);
			System.out.println("\t List : " + list);
		} else {
			System.out.println("Path does not exist.");
		}
		for (String s : list) {
			listString += "[" + s + "]";
		}
		return listString;
	}
}
