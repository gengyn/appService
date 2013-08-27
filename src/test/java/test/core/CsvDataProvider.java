package test.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Convenience class to provide data sets for unit tests within TestNG.
 * 
 * @author cjsr
 */
public class CsvDataProvider implements Iterator {

	/**
	 * @testng.data-provider name="CsvDataProvider"
	 * @param method
	 *            the method the TestNg passes to you
	 * @return an Iterator of Object[]
	 */
	public static Iterator getDataProvider(Method method) throws IOException {
		return getDataProvider(method.getDeclaringClass(), method);
	}

	/**
	 * Call this directly when necessary, to avoid issues with the method's
	 * declaring class not being the test class.
	 * 
	 * @param cls
	 *            The actual test class - matters for the CsvFileName and the
	 *            class loader
	 * @return an Iterator of Object[]
	 */
	public static Iterator getDataProvider(Class cls, Method method)
			throws IOException {
		String className = cls.getName();
		String dirPlusPrefix = className.replace('.', '/');
		String fileName = method.getName() + ".csv";
		String filePath = dirPlusPrefix + "." + fileName;

		return new CsvDataProvider(cls, method, filePath);
	}

	private CSVReader reader;
	private String[] last;
	private Class[] parameterTypes;
	private Converter[] parameterConverters;

	/**
	 * Basic constructor that will provide the data from the given file for the
	 * given method
	 * 
	 * @throws IOException
	 *             when file io fails
	 */
	public CsvDataProvider(Class cls, Method method, String csvFilePath)
			throws IOException {
		InputStream is = cls.getClassLoader().getResourceAsStream(csvFilePath);
		InputStreamReader isr = new InputStreamReader(is);
		reader = new CSVReader(isr);
		parameterTypes = method.getParameterTypes();
		int len = parameterTypes.length;
		parameterConverters = new Converter[len];
		for (int i = 0; i < len; i++) {
			parameterConverters[i] = ConvertUtils.lookup(parameterTypes[i]);
		}
	}

	/**
	 * @return see Iterator contract.
	 */
	public boolean hasNext() {
		return (getNextLine() != null);
	}

	/**
	 * Get the next line, or the current line if it's already there.
	 * 
	 * @return the line.
	 */
	private String[] getNextLine() {
		if (last == null) {
			try {
				last = reader.readNext();
			} catch (IOException ioe) {
				throw new RuntimeException(ioe);
			}
		}
		return last;
	}

	/**
	 * @return the Object[] representation of the next line
	 */
	public Object next() {
		String[] next;
		if (last != null) {
			next = last;
		} else {
			next = getNextLine();
		}
		last = null;
		Object[] args = parseLine(next);
		return args;
	}

	/**
	 * @return the correctly parsed and wrapped values
	 * @todo need a standard third-party CSV parser plugged in here
	 */
	private Object[] parseLine(String[] svals) {
		int len = svals.length;
		Object[] ovals = new Object[len];
		for (int i = 0; i < len; i++) {
			ovals[i] = parameterConverters[i].convert(parameterTypes[i],
					svals[i]);
		}
		return ovals;
	}

	/**
	 * Always throws UnsupportedOperationException.
	 */
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
