package org.apache.velocity.test.issues;

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

import org.apache.velocity.test.AbstractBaseTest;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests VELOCITY-587.
 */
public class Velocity587Test
    extends AbstractBaseTest
{

    @Before
    @Override
    public void before()
        throws Exception
    {
        super.before();
    }

    // remember, they're all doubled, since java will use them as escapes first.
    @Test
    public void testLiteralTwoBackslashes()
    {
        String template = "#set( $bs2 = \'\\\\\' )$bs2";
        String expected = "\\\\";
        assertEvalEquals( expected, template );
    }

    @Test
    public void testLiteralOneBackslash()
    {
        String template = "#set( $bs = \'\\\' )$bs";
        String expected = "\\";
        assertEvalEquals( expected, template );
    }

    // remember, they're all doubled, since java will use them as escapes first.
    @Test
    public void testInterpolatedTwoBackslashes()
    {
        String template = "#set( $bs2 = \"\\\\\" )$bs2";
        String expected = "\\\\";
        assertEvalEquals( expected, template );
    }

    @Test
    public void testInterpolatedOneBackslash()
    {
        String template = "#set( $bs = \"\\\" )$bs";
        String expected = "\\";
        assertEvalEquals( expected, template );
    }

}
