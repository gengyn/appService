package test.core;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;

//public class JobManagerTest extends AbstractTestNGSpringContextTests {
@ContextConfiguration(locations = { "classpath:spring.xml"})
@SuppressWarnings("rawtypes")
public class TestBase extends AbstractTestNGSpringContextTests {
	@Autowired
	protected ApplicationContext applicationContext;

	@DataProvider(name = "CsvDataProvider")
	public static Iterator getDataProvider(Method method) throws IOException {
		return getDataProvider(method.getDeclaringClass(), method);
	}

	public static Iterator getDataProvider(Class cls, Method method)
			throws IOException {
		String fileName = cls.getSimpleName() + "." + method.getName() + ".csv";
		System.out
				.println("*******************************************************");
		System.out.println(fileName);
		System.out
				.println("*******************************************************");
		Iterator csvDataProvider = new CsvDataProvider(cls, method, fileName);
		// 跳过第一行
		csvDataProvider.next();
		return csvDataProvider;
	}
}

