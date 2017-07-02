package ch.obermuhlner.math.big;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.MathContext;

import org.junit.Test;

import ch.obermuhlner.math.big.BigFloat.Context;
import static ch.obermuhlner.math.big.BigFloat.*;

public class BigFloatTest {

	@Test
	public void testValueOf() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(0, BigDecimal.ONE.compareTo(context.valueOf(BigDecimal.ONE).toBigDecimal()));
		assertEquals(0, BigDecimal.ONE.compareTo(context.valueOf(1).toBigDecimal()));
		assertEquals(0, BigDecimal.ONE.compareTo(context.valueOf(1.0).toBigDecimal()));
		
		Context anotherContext = context(MathContext.DECIMAL64);
		assertEquals(0, BigDecimal.ONE.compareTo(context.valueOf(anotherContext.valueOf(1.0)).toBigDecimal()));
		assertEquals(0, BigDecimal.ONE.compareTo(anotherContext.valueOf(context.valueOf(1.0)).toBigDecimal()));

		assertEquals(anotherContext, context.valueOf(anotherContext.valueOf(1.0)).getContext());
		assertEquals(anotherContext, anotherContext.valueOf(context.valueOf(1.0)).getContext());

	}
	
	@Test
	public void testPi() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(BigDecimalMath.pi(context.getMathContext()), context.pi().toBigDecimal());
	}
	
	@Test
	public void testE() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(BigDecimalMath.e(context.getMathContext()), context.e().toBigDecimal());
	}
	
	@Test
	public void testIsEqual() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(true, context.valueOf(5).isEqual(context.valueOf(5)));
		assertEquals(true, context.valueOf(5).isEqual(context.valueOf(5.0)));
		assertEquals(false, context.valueOf(1).isEqual(context.valueOf(5)));
	}	

	@Test
	public void testIsLessThan() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(true, context.valueOf(1).isLessThan(context.valueOf(5)));
		assertEquals(false, context.valueOf(5).isLessThan(context.valueOf(1)));
		assertEquals(false, context.valueOf(5).isLessThan(context.valueOf(5)));
	}	

	@Test
	public void testIsLessThanOrEqual() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(true, context.valueOf(1).isLessThanOrEqual(context.valueOf(5)));
		assertEquals(false, context.valueOf(5).isLessThanOrEqual(context.valueOf(1)));
		assertEquals(true, context.valueOf(5).isLessThanOrEqual(context.valueOf(5)));
	}	

	@Test
	public void testIsGreaterThan() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(true, context.valueOf(5).isGreaterThan(context.valueOf(1)));
		assertEquals(false, context.valueOf(1).isGreaterThan(context.valueOf(5)));
		assertEquals(false, context.valueOf(5).isGreaterThan(context.valueOf(5)));
	}	

	@Test
	public void testIsGreaterThanOrEqual() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(true, context.valueOf(5).isGreaterThanOrEqual(context.valueOf(1)));
		assertEquals(false, context.valueOf(1).isGreaterThanOrEqual(context.valueOf(5)));
		assertEquals(true, context.valueOf(5).isGreaterThanOrEqual(context.valueOf(5)));
	}	

	@Test
	public void testCompareTo() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(1, context.valueOf(5).compareTo(context.valueOf(1)));
		assertEquals(-1, context.valueOf(1).compareTo(context.valueOf(5)));
		assertEquals(0, context.valueOf(5).compareTo(context.valueOf(5)));
	}
	
	@Test
	public void testAdd() {
		Context context = context(MathContext.DECIMAL32);
		assertIsEqual(context.valueOf(5), context.valueOf(2).add(3));
		assertIsEqual(context.valueOf(5), context.valueOf(2).add(3L));
		assertIsEqual(context.valueOf(5), context.valueOf(2).add(3.0));
		assertIsEqual(context.valueOf(5), context.valueOf(2).add(new BigDecimal(3)));
		assertIsEqual(context.valueOf(5), context.valueOf(2).add(context.valueOf(3)));
	}

	@Test
	public void testSubtract() {
		Context context = context(MathContext.DECIMAL32);
		assertIsEqual(context.valueOf(2), context.valueOf(5).subtract(3));
		assertIsEqual(context.valueOf(2), context.valueOf(5).subtract(3L));
		assertIsEqual(context.valueOf(2), context.valueOf(5).subtract(3.0));
		assertIsEqual(context.valueOf(2), context.valueOf(5).subtract(new BigDecimal(3)));
		assertIsEqual(context.valueOf(2), context.valueOf(5).subtract(context.valueOf(3)));
	}

	@Test
	public void testMultiply() {
		Context context = context(MathContext.DECIMAL32);
		assertIsEqual(context.valueOf(6), context.valueOf(2).multiply(3));
		assertIsEqual(context.valueOf(6), context.valueOf(2).multiply(3L));
		assertIsEqual(context.valueOf(6), context.valueOf(2).multiply(3.0));
		assertIsEqual(context.valueOf(6), context.valueOf(2).multiply(new BigDecimal(3)));
		assertIsEqual(context.valueOf(6), context.valueOf(2).multiply(context.valueOf(3)));
	}

	@Test
	public void testDivide() {
		Context context = context(MathContext.DECIMAL32);
		assertIsEqual(context.valueOf(2), context.valueOf(6).divide(3));
		assertIsEqual(context.valueOf(2), context.valueOf(6).divide(3L));
		assertIsEqual(context.valueOf(2), context.valueOf(6).divide(3.0));
		assertIsEqual(context.valueOf(2), context.valueOf(6).divide(new BigDecimal(3)));
		assertIsEqual(context.valueOf(2), context.valueOf(6).divide(context.valueOf(3)));
	}

	@Test
	public void testRemainder() {
		Context context = context(MathContext.DECIMAL32);
		assertIsEqual(context.valueOf(1), context.valueOf(10).remainder(3));
		assertIsEqual(context.valueOf(1), context.valueOf(10).remainder(3L));
		assertIsEqual(context.valueOf(1), context.valueOf(10).remainder(3.0));
		assertIsEqual(context.valueOf(1), context.valueOf(10).remainder(new BigDecimal(3)));
		assertIsEqual(context.valueOf(1), context.valueOf(10).remainder(context.valueOf(3)));
	}

	@Test
	public void testPow() {
		Context context = context(MathContext.DECIMAL32);
		assertIsEqual(context.valueOf(8), context.valueOf(2).pow(3));
		assertIsEqual(context.valueOf(8), context.valueOf(2).pow(3L));
		assertIsEqual(context.valueOf(8), context.valueOf(2).pow(3.0));
		assertIsEqual(context.valueOf(8), context.valueOf(2).pow(new BigDecimal(3)));
		assertIsEqual(context.valueOf(8), context.valueOf(2).pow(context.valueOf(3)));
	}

	@Test
	public void testRoot() {
		Context context = context(MathContext.DECIMAL32);
		assertIsEqual(context.valueOf(2), context.valueOf(8).root(3));
		assertIsEqual(context.valueOf(2), context.valueOf(8).root(3L));
		assertIsEqual(context.valueOf(2), context.valueOf(8).root(3.0));
		assertIsEqual(context.valueOf(2), context.valueOf(8).root(new BigDecimal(3)));
		assertIsEqual(context.valueOf(2), context.valueOf(8).root(context.valueOf(3)));
	}

	@Test
	public void testFactorial() {
		Context context = context(MathContext.DECIMAL32);
		assertIsEqual(context.valueOf(720), context.factorial(6));
	}
	
	@Test
	public void testLog() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(log(context.valueOf(3)).toBigDecimal(), BigDecimalMath.log(new BigDecimal(3), MathContext.DECIMAL32));
	}
	
	@Test
	public void testLog2() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(log2(context.valueOf(3)).toBigDecimal(), BigDecimalMath.log2(new BigDecimal(3), MathContext.DECIMAL32));
	}
	
	@Test
	public void testLog10() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(log10(context.valueOf(3)).toBigDecimal(), BigDecimalMath.log10(new BigDecimal(3), MathContext.DECIMAL32));
	}
	
	@Test
	public void testExp() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(exp(context.valueOf(3)).toBigDecimal(), BigDecimalMath.exp(new BigDecimal(3), MathContext.DECIMAL32));
	}
	
	@Test
	public void testSqrt() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(sqrt(context.valueOf(3)).toBigDecimal(), BigDecimalMath.sqrt(new BigDecimal(3), MathContext.DECIMAL32));
	}
	
	@Test
	public void testSin() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(sin(context.valueOf(0)).toBigDecimal(), BigDecimalMath.sin(new BigDecimal(0), MathContext.DECIMAL32));
	}
	
	@Test
	public void testCos() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(cos(context.valueOf(0)).toBigDecimal(), BigDecimalMath.cos(new BigDecimal(0), MathContext.DECIMAL32));
	}
	
	@Test
	public void testTan() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(tan(context.valueOf(0)).toBigDecimal(), BigDecimalMath.tan(new BigDecimal(0), MathContext.DECIMAL32));
	}
	
	@Test
	public void testCot() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(cot(context.valueOf(1)).toBigDecimal(), BigDecimalMath.cot(new BigDecimal(1), MathContext.DECIMAL32));
	}
	
	@Test
	public void testAsin() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(asin(context.valueOf(0)).toBigDecimal(), BigDecimalMath.asin(new BigDecimal(0), MathContext.DECIMAL32));
	}
	
	@Test
	public void testAcos() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(acos(context.valueOf(0)).toBigDecimal(), BigDecimalMath.acos(new BigDecimal(0), MathContext.DECIMAL32));
	}
	
	@Test
	public void testAtan() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(atan(context.valueOf(0)).toBigDecimal(), BigDecimalMath.atan(new BigDecimal(0), MathContext.DECIMAL32));
	}
	
	@Test
	public void testAcot() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(acot(context.valueOf(0)).toBigDecimal(), BigDecimalMath.acot(new BigDecimal(0), MathContext.DECIMAL32));
	}

	@Test
	public void testSinh() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(sinh(context.valueOf(0)).toBigDecimal(), BigDecimalMath.sinh(new BigDecimal(0), MathContext.DECIMAL32));
	}
	
	@Test
	public void testCosh() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(cosh(context.valueOf(0)).toBigDecimal(), BigDecimalMath.cosh(new BigDecimal(0), MathContext.DECIMAL32));
	}
	
	@Test
	public void testCoth() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(coth(context.valueOf(1.1)).toBigDecimal(), BigDecimalMath.coth(BigDecimal.valueOf(1.1), MathContext.DECIMAL32));
	}
	
	@Test
	public void testTanh() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(tanh(context.valueOf(0)).toBigDecimal(), BigDecimalMath.tanh(new BigDecimal(0), MathContext.DECIMAL32));
	}
	
	@Test
	public void testAsinh() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(asinh(context.valueOf(0)).toBigDecimal(), BigDecimalMath.asinh(new BigDecimal(0), MathContext.DECIMAL32));
	}
	
	@Test
	public void testAcosh() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(acosh(context.valueOf(1.1)).toBigDecimal(), BigDecimalMath.acosh(BigDecimal.valueOf(1.1), MathContext.DECIMAL32));
	}
	
	@Test
	public void testAtanh() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(atanh(context.valueOf(0)).toBigDecimal(), BigDecimalMath.atanh(new BigDecimal(0), MathContext.DECIMAL32));
	}
	
	@Test
	public void testAcoth() {
		Context context = context(MathContext.DECIMAL32);
		assertEquals(acoth(context.valueOf(1.1)).toBigDecimal(), BigDecimalMath.acoth(BigDecimal.valueOf(1.1), MathContext.DECIMAL32));
	}

	@Test
	public void testAbs() {
		Context context = context(MathContext.DECIMAL32);
		assertIsEqual(context.valueOf(3), abs(context.valueOf(3)));
		assertIsEqual(context.valueOf(3), abs(context.valueOf(-3)));
	}

	@Test
	public void testNegate() {
		Context context = context(MathContext.DECIMAL32);
		assertIsEqual(context.valueOf(-3), negate(context.valueOf(3)));
		assertIsEqual(context.valueOf(3), negate(context.valueOf(-3)));
	}

	@Test
	public void testMax() {
		Context context = context(MathContext.DECIMAL32);
		assertIsEqual(context.valueOf(9), max(context.valueOf(9), context.valueOf(2)));
		assertIsEqual(context.valueOf(9), max(context.valueOf(2), context.valueOf(9)));
		
		assertIsEqual(context.valueOf(9), max(context.valueOf(2), context.valueOf(9), context.valueOf(3)));
		assertIsEqual(context.valueOf(9), max(context.valueOf(9), context.valueOf(2), context.valueOf(3)));
	}

	@Test
	public void testMin() {
		Context context = context(MathContext.DECIMAL32);
		assertIsEqual(context.valueOf(1), min(context.valueOf(1), context.valueOf(2)));
		assertIsEqual(context.valueOf(1), min(context.valueOf(2), context.valueOf(1)));
		
		assertIsEqual(context.valueOf(1), min(context.valueOf(2), context.valueOf(1), context.valueOf(3)));
		assertIsEqual(context.valueOf(1), min(context.valueOf(1), context.valueOf(2), context.valueOf(3)));
	}
	
	private static void assertIsEqual(BigFloat expected, BigFloat actual) {
		assertEquals(expected + "=" + actual, true, expected.isEqual(actual));
	}
}