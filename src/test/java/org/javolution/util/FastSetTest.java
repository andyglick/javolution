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
import org.junit.Test;

import org.assertj.core.api.Assertions;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FastSetTest {

  private AbstractSet<String> fastSet;

  @Before
  public void init() {
    fastSet = new FastSet<>();
  }

  @Test
  public void testAny() {
    fastSet.add("AA");
    fastSet.add("A");
    fastSet.add("AAAA");
    fastSet.add("AAA");

    String anyString = fastSet.findAny();
    assertNotNull("Any String Obtained", anyString);
    assertTrue("Set Contains String", fastSet.contains(anyString));
  }

  @Test
  public void testClear() {
    fastSet.add("Test");
    fastSet.clear();
    assertEquals("Set Size Is 0 After Clear", 0, fastSet.size());
    assertTrue("Set Is Empty After Clear", fastSet.isEmpty());
  }

  @Test
  public void testContains() {
    fastSet.add("Test");
    assertTrue("Set Contains Test", fastSet.contains("Test"));
  }

  @Test
  public void testContainsAll() {
    Set<String> set = new FastSet<>();
    set.add("Test1");
    set.add("Test2");
    set.add("Test3");

    fastSet.add("Test1");
    fastSet.add("Test2");
    fastSet.add("Test3");

    assertTrue("Set Contains All of the Elements", fastSet.containsAll(set));
  }

  @Test
  public void testIsEmpty() {
    assertTrue("Set Is Empty", fastSet.isEmpty());
    fastSet.add("Test");
    assertFalse("Set Is NOT Empty", fastSet.isEmpty());
  }

  @Test
  public void testIteratorAndSetRetainsInsertOrder() {
    fastSet.add("A");
    fastSet.add("AA");
    fastSet.add("AAA");
    fastSet.add("AAAA");

    Iterator<String> iterator = fastSet.iterator();

    assertEquals("Element 1 is A", "A", iterator.next());
    assertEquals("Element 2 is AA", "AA", iterator.next());
    assertEquals("Element 3 is AAA", "AAA", iterator.next());
    assertEquals("Element 4 is AAAA", "AAAA", iterator.next());
  }

  @Test
  public void testRetainAll() {
    Set<String> set = new FastSet<>();
    set.add("A");

    fastSet.add("AA");
    fastSet.add("A");
    fastSet.add("AAAA");
    fastSet.add("AAA");

    fastSet.retainAll(set);

    Assertions.assertThat(fastSet).contains("A");
    Assertions.assertThat(fastSet.size()).isEqualTo(1);
  }

  @Test
  public void testReversedView() {
    fastSet.add("A");
    fastSet.add("AA");
    fastSet.add("AAA");
    fastSet.add("AAAA");

    Collection<String> reversedCollection = fastSet.reversed();

    Iterator<String> iterator = reversedCollection.iterator();

    assertEquals("Element 1 is AAAA", "AAAA", iterator.next());
    assertEquals("Element 2 is AAA", "AAA", iterator.next());
    assertEquals("Element 3 is AA", "AA", iterator.next());
    assertEquals("Element 4 is A", "A", iterator.next());
  }

  @Test
  public void testSize() {
    fastSet.add("Test1");
    fastSet.add("Test2");
    fastSet.add("Test3");
    assertEquals("Set Size Is 0 After Clear", 3, fastSet.size());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testUnmodifiableView() {
    Set<String> unmodifiableSet = fastSet.unmodifiable();
    unmodifiableSet.add("Test");
  }
}
