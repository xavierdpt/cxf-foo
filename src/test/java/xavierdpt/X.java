package xavierdpt;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.junit.Test;

public class X {

	private final static String NAME = "cxf-rt-frontend-jaxws-3.3.0";

	@Test
	public void test() throws ZipException, IOException {

		String[] jars = System.getProperty("java.class.path").split(";");
		for (String jar : jars) {
			if (jar.endsWith(NAME + ".jar")) {
				ZipFile z = new ZipFile(new File(jar));
				Enumeration<? extends ZipEntry> entries = z.entries();
				while (entries.hasMoreElements()) {
					ZipEntry entry = entries.nextElement();
					String entryName = entry.getName();
					if (entryName.endsWith(".class") && !entryName.contains("$")) {
						String className = entryName.substring(0, entryName.length() - ".class".length())
								.replaceAll("/", ".");
						System.out.println(className);
						try {
							Class<?> clazz = Class.forName(className);
						} catch (Exception ex) {
							System.out.println(
									ex.getClass().getName() + (ex.getMessage() != null ? " " + ex.getMessage() : ""));
						}
					}

				}
			}
		}

	}

}
