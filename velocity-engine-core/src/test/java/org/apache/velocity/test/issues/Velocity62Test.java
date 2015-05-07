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
 * This class tests VELOCITY-62.
 */
public class Velocity62Test
    extends AbstractBaseTest
{

    @Before
    public void before()
        throws Exception
    {
        super.before();
        context.put( "foo", "foo" );
    }

    @Test
    public void testNested()
    {
        String template = "#macro( outer )#set( $foo = 'bar' )#inner()#end" + "#macro( inner )$foo#end"
            + "#inner()#outer()#inner()";
        assertEvalEquals( "foobarbar", template );
    }

    @Test
    public void testRecursive()
    {
        context.put( "i", new Integer( 1 ) );
        String template = "#macro(recurse $i)" + "$i" + "#if( $i < 5 )" + "#set( $i = $i + 1 )" + "#recurse($i)"
            + "#end" + "#end" + "#recurse(1)";
        assertEvalEquals( "12345", template );
    }

}
