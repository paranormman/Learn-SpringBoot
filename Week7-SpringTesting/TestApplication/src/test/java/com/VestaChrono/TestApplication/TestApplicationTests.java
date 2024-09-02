package com.VestaChrono.TestApplication;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

//@SpringBootTest
@Slf4j
class TestApplicationTests {

	@BeforeEach
	void setUp() {
		log.info("Starting the method, setting up config");
	}

	@AfterEach
	void tearDown() {
		log.info("Tearing Down the method");
	}

	@BeforeAll
	static void setUpOnce() {
		log.info("Starting once...");
	}

	@AfterAll
	static void tearDownOnce() {
		log.info("tearing down all...");
	}

	@Test
//	@Disabled
	void testNumberOne() {

//		Assertions from AssertJ can be chained with multiple assertions/methods
		Assertions.assertThat("Apple")
				.hasSize(5)
				.startsWith("App")
				.endsWith("le");

	}

	@Test
//	@DisplayName("testAddition")
	void testNumberTwo() {
		int a = 5;
		int b = 3;

		int result = addTwoNumbers(a, b);

//		Using Assert from JUnit, can have only a single function.
//		Assertions.assertEquals(8, addTwoNumbers(a, b));

//		Assertion from assertj API
		Assertions.assertThat(result)
				.isEqualTo(8)
				.isCloseTo(9, Offset.offset(1));

	}

	void testDivideTwoNumbers_whenDenominatorIsZero_ThenArithmeticException() {
		int a = 5;
		int b = 8;


        assertThatThrownBy(() -> divideTwoNumbers(a, b))
				.isInstanceOf(ArithmeticException.class)
				.hasMessage("Tried to divide by zero");
	}

	int addTwoNumbers(int a, int b) {
		return a+b;
	}

	double divideTwoNumbers(int a, int b) {
		try {
			return a/b;
		} catch (ArithmeticException e){
            log.error("Arithmetic Exception Occurred: {}", e.getLocalizedMessage());
			throw new ArithmeticException("Tried to divide by zero");
		}
	}

}
