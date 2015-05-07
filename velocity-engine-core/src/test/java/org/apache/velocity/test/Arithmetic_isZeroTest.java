package org.apache.velocity.test;

import static org.junit.Assert.assertEquals;

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
public class Arithmetic_isZeroTest
{

    private final Number n1;

    private final boolean expected;

    /**
     * 
     * @param n1
     * @param expected
     */
    public Arithmetic_isZeroTest( Number n1, boolean expected )
    {
        this.n1 = n1;
        this.expected = expected;
    }

    @Test
    public void subtractHelper()
    {
        assertEquals( this.expected, MathUtils.isZero( this.n1 ) );
    }

    @Parameters
    public static Collection<Object[]> testData()
    {
        return Arrays.asList( new Object[][] {
            { new Integer( 0 ), true },
            { new Integer( 1 ), false },
            { new Integer( -1 ), false },
            { new Float( 0f ), true },
            { new Float( 0.00001f ), false },
            { new Float( -0.00001f ), false } } );
    }

}
