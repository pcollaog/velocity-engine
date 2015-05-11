package org.apache.velocity.util.introspection;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import junit.framework.TestCase;

import org.apache.velocity.runtime.log.Log;
import org.apache.velocity.util.ArrayIterator;
import org.apache.velocity.util.EnumerationIterator;

public class UberspectImpl_getIteratorTest
    extends TestCase
{

    public void testArrayListObject()
        throws Exception
    {
        UberspectImpl uber = new UberspectImpl();
        Info i = mock( Info.class );
        Iterator iterator = uber.getIterator( new ArrayList<String>(), i );

        assertNotNull( iterator );
    }

    public void testCollectionObject()
        throws Exception
    {
        UberspectImpl uber = new UberspectImpl();
        Info i = mock( Info.class );
        Iterator iterator = uber.getIterator( new String[] {}, i );

        assertNotNull( iterator );
        assertTrue( iterator instanceof ArrayIterator );
    }

    public void testMapObject()
        throws Exception
    {
        UberspectImpl uber = new UberspectImpl();
        Info i = mock( Info.class );
        Iterator iterator = uber.getIterator( new HashMap<String, String>(), i );

        assertNotNull( iterator );
    }

    public void testIteratorObject()
        throws Exception
    {
        Log log = mock( Log.class );
        when( log.isDebugEnabled() ).thenReturn( Boolean.FALSE );

        UberspectImpl uber = new UberspectImpl();
        uber.setLog( log );

        Info info = mock( Info.class );
        List<String> list = new ArrayList<String>();
        Iterator iterator = uber.getIterator( list.iterator(), info );

        assertNotNull( iterator );
    }

    public void testEnumerationObject()
        throws Exception
    {
        Log log = mock( Log.class );
        when( log.isDebugEnabled() ).thenReturn( Boolean.FALSE );

        UberspectImpl uber = new UberspectImpl();
        uber.setLog( log );

        Info info = mock( Info.class );

        Vector<String> vector = new Vector<String>();

        Iterator iterator = uber.getIterator( vector.elements(), info );

        assertNotNull( iterator );
        assertTrue( iterator instanceof EnumerationIterator );
    }

    public void testOtherObject()
        throws Exception
    {
        Log log = mock( Log.class );
        when( log.isDebugEnabled() ).thenReturn( Boolean.FALSE );

        UberspectImpl uber = new UberspectImpl();
        uber.setLog( log );

        Info info = mock( Info.class );

        Iterator iterator = uber.getIterator( new Object(), info );

        assertNull( iterator );
    }

    public void testOtherObject2()
        throws Exception
    {
        Log log = mock( Log.class );
        when( log.isDebugEnabled() ).thenReturn( Boolean.FALSE );

        UberspectImpl uber = new UberspectImpl();
        uber.setLog( log );

        Info info = mock( Info.class );
        FakeIterator obj = new FakeIterator();
        Iterator iterator = uber.getIterator( obj, info );

        assertNotNull( iterator );
        assertTrue( iterator instanceof IteratorImplementation );
    }

    private static class FakeIterator
        implements Iterable<String>
    {

        public Iterator<String> iterator()
        {
            return new IteratorImplementation();
        }

    }

    private static final class IteratorImplementation
        implements Iterator<String>
    {
        public boolean hasNext()
        {
            return false;
        }

        public String next()
        {
            return null;
        }

        public void remove()
        {

        }
    }
}
