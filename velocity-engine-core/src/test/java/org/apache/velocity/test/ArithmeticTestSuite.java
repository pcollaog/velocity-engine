package org.apache.velocity.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    Arithmetic_addTest.class,
    Arithmetic_compareTest.class,
    Arithmetic_divideTest.class,
    Arithmetic_isZeroTest.class,
    Arithmetic_moduloTest.class,
    Arithmetic_multiplyTest.class,
    Arithmetic_subtractTest.class, })
public class ArithmeticTestSuite
{

}
