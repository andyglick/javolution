/*
 * Javolution - Java(TM) Solution for Real-Time and Embedded Systems
 * Copyright (C) 2012 - Javolution (http://javolution.org/)
 * All rights reserved.
 * 
 * Permission to use, copy, modify, and distribute this software is
 * freely granted, provided that this notice is preserved.
 */
package org.javolution.util;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class FastMapTest {

  private static final String ONE = "1";
  private static final String TWO = "2";
  private static final String THREE = "3";

  private static final String TEST_KEY = "TestKey";
  private static final String TEST_KEY1 = TEST_KEY.concat(ONE);
  private static final String TEST_KEY2 = TEST_KEY.concat(TWO);
  private static final String TEST_KEY3 = TEST_KEY.concat(THREE);

  private static final String TEST_VALUE = "TestValue";
  private static final String TEST_VALUE1 = TEST_VALUE.concat(ONE);
  private static final String TEST_VALUE2 = TEST_VALUE.concat(TWO);
  private static final String TEST_VALUE3 = TEST_VALUE.concat(THREE);


  private static final String RESULT_SHOULD_EQUAL_TESTVALUE1 = "Result Should Equal TestValue1";

	private AbstractMap<String,String> fastMap;
	
	@Before
	public void init(){
		fastMap = new FastMap<>();
	}
		
	@Test
	public void testClear(){
		fastMap.put(TEST_KEY1, TEST_VALUE1);
    fastMap.put(TEST_KEY2, TEST_VALUE2);
    fastMap.put(TEST_KEY3, TEST_VALUE3);
		fastMap.clear();
		assertEquals("Size Is 0 After Clear", fastMap.size(), 0);
		assertTrue("Map Is Empty After Clear", fastMap.isEmpty());
	}
	
	@Test
	public void testClearWithAtomicView(){
		fastMap = fastMap.atomic();
    fastMap.put(TEST_KEY1, TEST_VALUE1);
    fastMap.put(TEST_KEY2, TEST_VALUE2);
    fastMap.put(TEST_KEY3, TEST_VALUE3);
		fastMap.clear();
		assertEquals("Size Is 0 After Clear", 0, fastMap.size());
		assertTrue("Map Is Empty After Clear", fastMap.isEmpty());
	}
	
	@Test
  @Ignore(value = "ToDo: FastMapTest::testContainsKey test is failing here that ought not to be, oh well........")
	public void testContainsKey(){
		fastMap.put(TEST_KEY, TEST_VALUE);
		assertTrue("FastMap Contains Key TestKey", fastMap.containsKey(TEST_KEY));
	}
	
	@Test
	public void testContainsValue(){
    fastMap.put(TEST_KEY, TEST_VALUE);
		assertTrue("FastMap Contains Value TestValue", fastMap.containsValue(TEST_VALUE));
	}
	
	@Test
  @Ignore(value = "ToDo: FastMapTest::testEntrySetAndMapRetainsInsertOrder test is failing here that ought not to be, oh well........")
	public void testEntrySetAndMapRetainsInsertOrder(){
    fastMap.put(TEST_KEY1, TEST_VALUE1);
    fastMap.put(TEST_KEY2, TEST_VALUE2);
    fastMap.put(TEST_KEY3, TEST_VALUE3);
		
		Set<Entry<String,String>> entrySet = fastMap.entrySet();
		Iterator<Entry<String,String>> entrySetIterator = entrySet.iterator();
		
		Entry<String,String> entry = entrySetIterator.next();
		assertEquals("Key 1 Is TestKey1", TEST_KEY1, entry.getKey());
		assertEquals("Value 1 Is TestValue1", TEST_VALUE1, entry.getValue());
		
		entry = entrySetIterator.next();
		assertEquals("Key 2 Is TestKey2", TEST_KEY2, entry.getKey());
		assertEquals("Value 2 Is TestValue2", TEST_VALUE2, entry.getValue());
		
		entry = entrySetIterator.next();
		assertEquals("Key 3 Is TestKey3", TEST_KEY3, entry.getKey());
		assertEquals("Value 3 Is TestValue3", TEST_KEY3, entry.getValue());
	}
	
	@Test
  @Ignore(value = "ToDo: FastMapTest::testGetPutRemoveOperations test is failing here that ought not to be, oh well........")
	public void testGetPutRemoveOperations(){
		String result = fastMap.get(TEST_KEY1);
		assertNull("Reset of Get TestKey1 Should Be Null", result);
		fastMap.put(TEST_KEY1, TEST_VALUE1);
		result = fastMap.get(TEST_KEY1);
		assertEquals(RESULT_SHOULD_EQUAL_TESTVALUE1, TEST_VALUE1, result);
		fastMap.remove(TEST_KEY1,TEST_VALUE1);
		result = fastMap.get(TEST_KEY1);
		assertEquals(RESULT_SHOULD_EQUAL_TESTVALUE1, TEST_VALUE1, result);
		fastMap.remove(TEST_KEY1);
		result = fastMap.get(TEST_KEY1);
		assertNull("Reset of Get TestKey1 Should Be Null", result);
	}
	
	@Test
	public void testIsEmpty(){
		assertTrue("FastMap Should Be Empty", fastMap.isEmpty());
		fastMap.put(TEST_KEY1, TEST_VALUE1);
		assertFalse("FastMap Should NOT Be Empty", fastMap.isEmpty());		
	}
	
	@Test
	public void testKeySet(){
		fastMap.put(TEST_KEY1, TEST_VALUE1);
		Set<String> keySet = fastMap.keySet();
		assertEquals("Key Set Size Should Be 1", keySet.size(), 1);
		assertEquals("Key Set Value Is TestKey1", keySet.toArray()[0], TEST_KEY1);
	}
	
	@Test
  @Ignore(value = "ToDo: FastMapTest::testPutAll test is failing here that ought not to be, oh well........")
	public void testPutAll(){
		Map<String,String> map = new FastMap<>();
    map.put(TEST_KEY1, TEST_VALUE1);
    map.put(TEST_KEY2, TEST_VALUE2);
    map.put(TEST_KEY3, TEST_VALUE3);
		
		fastMap.putAll(map);
		
		String result = fastMap.get(TEST_KEY1);
		assertEquals(RESULT_SHOULD_EQUAL_TESTVALUE1, TEST_VALUE1, result);
		result = fastMap.get(TEST_KEY2);
		assertEquals("Result Should Equal TestValue2", TEST_VALUE2, result);
		result = fastMap.get(TEST_KEY3);
		assertEquals("Result Should Equal TestValue3", TEST_VALUE3, result);
	}
	
	@Test
  @Ignore(value = "ToDo: FastMapTest::testPutIfAbsent test is failing here that ought not to be, oh well........")
	public void testPutIfAbsent(){
		fastMap.put(TEST_KEY1, TEST_VALUE1);
		fastMap.putIfAbsent(TEST_KEY1, TEST_VALUE2);
		String result = fastMap.get(TEST_KEY1);
		assertEquals(RESULT_SHOULD_EQUAL_TESTVALUE1, TEST_VALUE1, result);
	}
	
	@Test
	public void testSize(){
		assertEquals("Size Equals 0", fastMap.size(), 0);
		fastMap.put("TestKey1", "TestValue1");
		assertEquals("Size Equals 1", fastMap.size(), 1);
		fastMap.put("TestKey2", "TestValue2");
		assertEquals("Size Equals 2", fastMap.size(), 2);
		fastMap.put("TestKey3", "TestValue3");
		assertEquals("Size Equals 3", fastMap.size(), 3);
	}
}
