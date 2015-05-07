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
public class Arithmetic_compareTest
{

    private final Number n1;

    private final Number n2;

    private final int expectedResult;

    /**
     * @param n1
     * @param n2
     * @param expected
     * @param expectedResultType
     */
    public Arithmetic_compareTest( Number n1, Number n2, int expectedResult )
    {
        this.n1 = n1;
        this.n2 = n2;
        this.expectedResult = expectedResult;
    }

    @Test
    public void testCompare()
    {
        int result = MathUtils.compare( n1, n2 );
        assertEquals( expectedResult, result );
    }

    @Parameters
    public static Collection<Object[]> testData()
    {
        return Arrays.asList( new Object[][] {
            { Integer.valueOf( 10 ), Short.valueOf( (short) 10 ), 0 },
            { Integer.valueOf( 10 ), Short.valueOf( (short) 11 ), -1 },
            { BigInteger.valueOf( 10 ), Short.valueOf( (short) 11 ), -1 },
            { Byte.valueOf( (byte) 10 ), Short.valueOf( (short) 3 ), 1 },
            { Float.valueOf( 10 ), Short.valueOf( (short) 11 ), -1 },
            { Double.valueOf( 10 ), Short.valueOf( (short) 11 ), -1 } } );
    }

}
