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
public class Arithmetic_addTest
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
    public Arithmetic_addTest( Number n1, Number n2, double expected, Class<?> expectedResultType )
    {
        this.n1 = n1;
        this.n2 = n2;
        this.expected = expected;
        this.expectedResultType = expectedResultType;
    }

    @Test
    public void addHelper()
    {
        Number result = MathUtils.add( this.n1, this.n2 );
        assertEquals( "The arithmetic operation produced an unexpected result.", this.expected, result.doubleValue(),
                      0.01 );
        assertEquals( "ResultType does not match.", this.expectedResultType, result.getClass() );
    }

    @Parameters
    public static Collection<Object[]> testData()
    {
        return Arrays
            .asList( new Object[][] {
                { Integer.valueOf( 10 ), Short.valueOf( (short) 20 ), 30, Integer.class },
                { Byte.valueOf( (byte) 10 ), Short.valueOf( (short) 20 ), 30, Short.class },
                { Float.valueOf( 10 ), Short.valueOf( (short) 20 ), 30, Float.class },
                { Byte.valueOf( (byte) 10 ), Double.valueOf( 20 ), 30, Double.class },
                { BigInteger.valueOf( 10 ), Integer.valueOf( 20 ), 30, BigInteger.class },
                { Integer.valueOf( 20 ), BigDecimal.valueOf( 10 ), 30, BigDecimal.class },
                {
                    Integer.valueOf( Integer.MAX_VALUE ),
                    Short.valueOf( (short) 20 ),
                    (double) Integer.MAX_VALUE + 20,
                    Long.class },
                { Integer.valueOf( 20 ), Long.valueOf( Long.MAX_VALUE ), (double) Long.MAX_VALUE + 20, BigInteger.class },
                {
                    Integer.valueOf( -20 ),
                    Long.valueOf( Long.MIN_VALUE ),
                    (double) Long.MIN_VALUE - 20,
                    BigInteger.class } } );
    }

}
