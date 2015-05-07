package org.apache.velocity.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

import org.apache.velocity.runtime.parser.node.MathUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
public class Arithmetic_moduloTest
{

    private final Number n1;

    private final Number n2;

    private final double expectedResult;

    private Class<? extends Number> expectedResultType;

    private final boolean hasException;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    public Arithmetic_moduloTest( Number n1, Number n2, double expected, Class<? extends Number> expectedResultType,
                                  boolean hasException )
    {
        this.n1 = n1;
        this.n2 = n2;
        this.expectedResult = expected;
        this.expectedResultType = expectedResultType;
        this.hasException = hasException;
    }

    @Test
    public void moduloHelper()
    {
        if ( hasException )
        {
            expectedException.expect( ArithmeticException.class );
        }

        Number result = MathUtils.modulo( this.n1, this.n2 );
        {
            assertEquals( "The arithmetic operation produced an unexpected result.", this.expectedResult,
                          result.doubleValue(), 0.01 );
            assertEquals( "ResultType does not match.", this.expectedResultType, result.getClass() );
        }
    }

    @Parameters
    public static Collection<Object[]> testData()
    {
        return Arrays.asList( new Object[][] {
            { Integer.valueOf( 10 ), Short.valueOf( (short) 2 ), 0, Integer.class, false },
            { Byte.valueOf( (byte) 10 ), Short.valueOf( (short) 3 ), 1, Short.class, false },
            { BigInteger.valueOf( 10 ), Short.valueOf( (short) 4 ), 2, BigInteger.class, false },
            { Integer.valueOf( 10 ), Float.valueOf( 5.5f ), 4.5, Float.class, false },
            // Exceptions
            { new Integer( 10 ), new BigDecimal( 2.5 ), 4, BigDecimal.class, true } } );
    }

}
