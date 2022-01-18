package io.javabrains;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("Testing MathUtils class")
class MathUtilsTest {

	MathUtils math;
	TestInfo testInfo;
	TestReporter testReporter;

	@BeforeAll
	public void init() {
		math = new MathUtils();
	}
	
	@BeforeEach
	public void initBeforeEach(TestInfo testInfo, TestReporter testReporter) {
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		testReporter.publishEntry("Runing Test Info : Class : " + testInfo.getClass() + ", Method : " + testInfo.getTestMethod() );
	}

	@Test
	@DisplayName("Testing Add Method")
	void testAdd() {
		int expected = 14;
		int actual = math.add(5, 9);
		assertEquals(expected, actual,
				() -> "The add method should add two numbers..expected : " + expected + ", actual : " + actual);
	}

	@RepeatedTest(value = 3)
	@DisplayName("Testing Repeat Method")
	void testAddRep(RepetitionInfo repeat) {
		if(repeat.getCurrentRepetition() == 1)
			assertEquals(14, math.add(5, 9), () -> "The add method should add two numbers.."+repeat.getCurrentRepetition());
		else if(repeat.getCurrentRepetition() == 2)
			assertEquals(10, math.add(10, 0), () -> "The add method should add two numbers.."+repeat.getCurrentRepetition());
	}

	@Nested
	@DisplayName("Testing add method")
	class addTest {
		@Test
		@DisplayName("Testing Add for Positive Numbers")
		void testAddPositives() {
			assertEquals(14, math.add(5, 9), "Testing Add for Positive Numbers");
		}

		@Test
		@DisplayName("Testing Add for Negative Numbers")
		void testAddNegatives() {
			assertEquals(-9, math.add(-5, -4), "Testing Add for Negative Numbers");
		}
	}

	@Test
	void testDivide() {
		assertThrows(ArithmeticException.class, () -> math.divide(1, 0), "Divide method by 0");
	}

	@Test
	@Tag("Circle")
	void testCalcCircleArea() {
		double expected = Math.PI * 10 * 10;
		double actual = math.calcCircleArea(10);
		assertEquals(expected, actual, "Calculation of Circle Area..expected : " + expected + ", actual : " + actual);
	}
	
	@Test
	@Tag("Circle")
	void testCalcCircleDiameter() {
		double expected = 2 * Math.PI * 5;
		double actual = math.calcCircleDiameter(5);
		assertEquals(expected, actual, "Calculation of Circle Diameter..expected : " + expected + ", actual : " + actual);
	}

	@Test
	@DisplayName("Multiply with multiple asserts")
	void multiply() {
		assertAll(() -> assertEquals(4, math.multiply(1, 4), "Should return right multiplier value.."),
				() -> assertEquals(12, math.multiply(2, 6), "Should return right multiplier value.."),
				() -> assertEquals(0, math.multiply(9, 0), "Should return right multiplier value.."),
				() -> assertEquals(6, math.multiply(-3, -2), "Should return right multiplier value.."),
				() -> assertEquals(-12, math.multiply(6, -2), "Should return right multiplier value.."));
	}

	@Test
	void assume() {
		boolean isServerUp = false;
		assumeTrue(isServerUp);
	}

	@Test
	@Disabled
	@DisplayName("TDD Method - Should not run")
	void alwaysFail() {
		fail("This will always fail - intentionally");
	}

	@Test
	@EnabledOnOs(OS.LINUX)
	void linuxSpecificTest() {
		fail("This will always fail - intentionally");
	}

	@AfterAll
	public void destroy() {
		math = null;
	}
}
