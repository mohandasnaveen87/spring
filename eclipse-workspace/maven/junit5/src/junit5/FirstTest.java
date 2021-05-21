package junit5;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class FirstTest {
   @BeforeEach
	void beforeTesting(TestInfo info) {
		System.out.println("call before "+info.getDisplayName());
	}
   @AfterEach
  	void afterTesting(TestInfo info) {
  		System.out.println("call after "+info.getDisplayName());
  	}
	@Test
	void test(TestInfo info) {
		//fail("Not yet implemented");
		int actualLength="NAVEEN".length();
		assertEquals(7, actualLength);
	}
	@Test
	void upper() {
		//fail("Not yet implemented");
		String newString="NAVEEN".toLowerCase();
		assertNotNull(newString);
		
	}
	@Test
	void arrayEquality() {
		String str="abc def ghi";
		String [] actualArray=str.split(" ");
		String [] expectedArray =new String []{"abc ","def","ghi"};
		assertArrayEquals(expectedArray, actualArray);
		
	}
	
	@BeforeAll
	static void connect2DB() {
		
		System.out.println("Connect to db once ");
		
	}
	@AfterAll
	static void disconnectfromDB() {
		
		System.out.println("DisConnect to db once ");
		
	}
	
	@Test
	@RepeatedTest(value = 5)
	@DisplayName(value = "Null Pointer Throwing")
	void checkException() {
		String str="as";
		
		assertThrows(NullPointerException.class, ()->{str.length();});
	}
	
	@ParameterizedTest
	@ValueSource(strings= {"abc","def","ghi"})
	void parameterizedTesting(String param) {
		assertTrue(param.length()==3);
	}
	@ParameterizedTest(name="{0} length is {1}")
	@CsvSource(value = { "babu,4","papa,3" })
	void parameterizedTestingCSV(String param1,int param2) {
		
		assertEquals(param1.length(), param2);
	}
	
	@Test
	void performanceTest() {
		assertTimeout(Duration.ofSeconds(10),()->{
		for(int j=0;j<10000000;j++) {
			System.out.println(j);
		}
		});
	}
	
	String str="";
	@Nested
	class SomeRandomGroupTests{
		
		@Test
		void test1() {
			assertEquals(0, str.length());
		}
		@Test
		void test2() {
			assertEquals("", str.toUpperCase());
		}
	}
}

