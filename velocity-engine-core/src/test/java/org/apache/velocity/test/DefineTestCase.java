package org.apache.velocity.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

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
 * This class tests the #define directive
 */
public class DefineTestCase
    extends AbstractBaseTest
{
    @Before
    @Override
    public void before()
        throws Exception
    {
        super.before();
    }

    protected String defAndEval( String block )
    {
        return defAndEval( "def", block );
    }

    protected String defAndEval( String key, String block )
    {
        return evaluate( "#define( $" + key + " )" + block + "#end$" + key );
    }

    @Test
    public void testSimple()
    {
        assertEquals( "abc", defAndEval( "abc" ) );
        assertEvalEquals( "abc abc abc", "#define( $a )abc#end$a $a $a" );
    }

    @Test
    public void testNotSimple()
    {
        assertEquals( "true", defAndEval( "#if( $def )true#end" ) );
        assertEquals( "123", defAndEval( "#foreach( $i in [1..3] )$i#end" ) );
        assertEquals( "hello world", defAndEval( "#macro( test )hello world#end#test()" ) );
    }

    @Test
    public void testOverridingDefinitionInternally()
    {
        assertEvalEquals( "truefalse", "#define( $or )true#set( $or = false )#end$or$or" );
    }

    @Test
    public void testLateBinding()
    {
        context.put( "baz", "foo" );
        assertEvalEquals( "foobar", "#define( $lb )$baz#end${lb}#set( $baz = 'bar' )${lb}" );
    }

    @Test
    public void testRerendering()
    {
        context.put( "inc", new Inc() );
        assertEvalEquals( "1 2 3", "#define( $i )$inc#end$i $i $i" );
    }

    @Test
    public void testAssignation()
    {
        assertEvalEquals( "[][hello]", "#define( $orig )hello#end[#set( $assig = $orig )][$assig]" );
    }

    @Test
    public void testNonRenderingUsage()
    {
        String template = "#define($foo)\n" + " foo_contents\n" + "#end\n" + "#if ($foo)\n" + " found foo\n" + "#end";
        assertEvalEquals( " found foo\n", template );
    }

    @Test
    public void testRecursionLimit()
    {
        try
        {
            assertEvalEquals( "$r", "#define( $r )$r#end$r" );
        }
        catch ( Exception t )
        {
            fail( "Recursion should not have thrown an exception" );
        }
        catch ( Error e )
        {
            fail( "Infinite recursion should not be possible." );
        }
    }

    @Test
    public void testThingsOfQuestionableMorality()
    {
        // redefining $foo within $foo
        assertEquals( "foobar", defAndEval( "foo", "foo#define( $foo )bar#end$foo" ) );
    }

    public static class Inc
    {
        int foo = 1;

        public String toString()
        {
            return String.valueOf( foo++ );
        }
    }
}
