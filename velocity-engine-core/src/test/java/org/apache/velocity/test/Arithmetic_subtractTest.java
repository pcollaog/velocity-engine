package org.apache.velocity.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

import org.apache.velocity.runtime.parser.node.MathUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

/**
 * Test arithmetic operations. Introduced after extending from Integer-only
 * to Number-handling.</p>
 * 
 * Migrate from {@link ArithmeticTestCase}
 *
 * @author pcollaog
 */
@RunWith(Parameterized.class)
public class Arithmetic_subtractTest
{

    private final Number n1;

    private final Number n2;

    private final double expected;

    private Class<?> expectedResultType;

    /**
     * @param n1
     * @param n2
     * @param expected
     * @param expectedResultType
     */
    public Arithmetic_subtractTest( Number n1, Number n2, double expected, Class<?> expectedResultType )
    {
        this.n1 = n1;
        this.n2 = n2;
        this.expected = expected;
        this.expectedResultType = expectedResultType;
    }

    @Test
    public void subtractHelper()
    {
        Number result = MathUtils.subtract( this.n1, this.n2 );
        assertEquals( "The arithmetic operation produced an unexpected result.", this.expected, result.doubleValue(),
                      0.01 );
        assertEquals( "ResultType does not match.", this.expectedResultType, result.getClass() );
    }

    @Parameters
    public static Collection<Object[]> testData()
    {
        return Arrays
            .asList( new Object[][] {
                { Integer.valueOf( 100 ), Short.valueOf( (short) 20 ), 80, Integer.class },
                { Byte.valueOf( (byte) 100 ), Short.valueOf( (short) 20 ), 80, Short.class },
                { Float.valueOf( 100 ), Short.valueOf( (short) 20 ), 80, Float.class },
                { Byte.valueOf( (byte) 100 ), Double.valueOf( 20 ), 80, Double.class },
                { BigInteger.valueOf( 100 ), Integer.valueOf( 20 ), 80, BigInteger.class },
                { Integer.valueOf( 100 ), BigDecimal.valueOf( 20 ), 80, BigDecimal.class },
                // Test overflow
                {
                    Integer.valueOf( Integer.MIN_VALUE ),
                    Short.valueOf( (short) 20 ),
                    (double) Integer.MIN_VALUE - 20,
                    Long.class },
                {
                    Integer.valueOf( -20 ),
                    Long.valueOf( Long.MAX_VALUE ),
                    -20d - (double) Long.MAX_VALUE,
                    BigInteger.class },
                {
                    Integer.MAX_VALUE,
                    Long.MIN_VALUE,
                    (double) Long.MAX_VALUE + (double) Integer.MAX_VALUE,
                    BigInteger.class } } );
    }

}
