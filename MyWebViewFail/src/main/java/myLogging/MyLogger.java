package myLogging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger extends Logger {

	public static String getPrintStackTrace(final Throwable throwable) {

		final StringWriter stringWriter = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(stringWriter);

		printWriter.println();
		printWriter.print("\t");

		throwable.printStackTrace(printWriter);

		printWriter.close();

		final String string = stringWriter.toString();

		return string.substring(0, string.length() - 2);

	}

	public MyLogger(final Class<?> clazz) {

		super(clazz.getName(), null);

		Locale.setDefault(Locale.ENGLISH);

		addConsoleHandler();
		addFileHandler();

	}

	private void addConsoleHandler() {

		final ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(Level.ALL);
		addHandler(consoleHandler);

	}

	private void addFileHandler() {

		final String dateString = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

		// The "%g" is part of the FileHandler name pattern.

		final String format = String.format("%s_%s_%s.log", MyLogger.class.getSimpleName(), dateString, "%g");

		try {
			final int tenMegaByte = 1000 * 1024 * 10;
			final FileHandler fileHandler = new FileHandler(format, tenMegaByte, 1000, true);
			fileHandler.setLevel(Level.ALL);
			fileHandler.setFormatter(new SimpleFormatter());
			addHandler(fileHandler);
		} catch (final Exception exception) {
			exception.printStackTrace();
		}

	}

}
