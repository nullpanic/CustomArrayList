package dev.nullpanic.CustomArrayList;

import dev.nullpanic.customarraylist.CustomArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayListTest {

    private CustomArrayList<String> list = new CustomArrayList<>();

    @BeforeEach
    public void init() {
        list.add("Charlie");
        list.add("Johnson");
        list.add("Bobby");
    }

    @Test
    public void testCustomArrayList_WhenSizeIsNegative_ShouldThrowIllegalArgumentEx() {

        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    CustomArrayList<String> list = new CustomArrayList<>(-5);
                });

        assertEquals("Size cannot be less than zero", exception.getMessage());
    }

    @Test
    public void testSet_WhenIndexOutOfBound_ShouldThrowIndexOutOfBoundEx() {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        Throwable exception = assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    list.set(777, 50);
                });

        assertEquals("Index 777 out of bounds for length 5", exception.getMessage());
    }

    @Test
    public void testSet_WhenInvoked_ShouldUpdateElement() {
        list.set(1, "Robby");

        assertEquals("Robby", list.get(1));
    }

    @Test
    public void testAdd_WhenOneArg_ShouldAddNewElementToEndArray() {
        CustomArrayList<Integer> values = new CustomArrayList<>();

        values.add(5);
        values.add(7);
        values.add(12);

        assertEquals(12, values.get(2));
    }

    @Test
    public void testAdd_WhenListIsFilled_ShouldIncreaseCapacity() {
        CustomArrayList<Integer> values = new CustomArrayList<>();

        values.add(5);
        values.add(7);
        values.add(12);
        values.add(23);
        values.add(75);
        values.add(8);

        System.out.println(values.size());

        assertTrue(values.size() > 5);
    }

    @Test
    public void testAdd_WhenIndexOutOfBound_ShouldThrowIndexOutOfBoundEx() {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        Throwable exception = assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    list.add(777, 50);
                });

        assertEquals("Index 777 out of bounds for length 5", exception.getMessage());
    }

    @Test
    public void testAdd_WhenInvoked_ShouldAddElementByIndex() {
        CustomArrayList<Long> list = new CustomArrayList<>();
        list.add(444L);
        list.add(555L);
        list.add(666L);
        list.add(777L);

        list.add(2, 222L);

        assertEquals(222L, list.get(2));
    }

    @Test
    public void testGet_WhenInvoked_ShouldReturnElementByIndex() {
        assertEquals("Charlie", list.get(0));
    }

    @Test
    public void testRemove_WhenInvokedByIndex_ShouldDecreaseSize() {
        list.remove(1);
        assertEquals(2, list.size());
    }

    @Test
    public void testRemove_WhenElementExist_ShouldReturnRemovedValue() {
        assertEquals("Charlie", list.remove(0));
    }

    @Test
    void testRemove_WhenInvokedByObject_ShouldDecreaseSize() {
        list.remove("Charlie");
        assertEquals(2, list.size());
    }

    @Test
    void testRemove_WhenObjectExist_ShouldReturnTrue() {
        assertTrue(list.remove("Charlie"));
    }

    @Test
    void testRemove_WhenObjectNotExist_ShouldReturnFalse() {
        assertFalse(list.remove("David"));
    }


    @Test
    void testClear_WhenInvoked_ShouldDeleteAllElements() {
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void testSort_WhenInvoked_ShouldSortByComparator() {
        CustomArrayList<Integer> values = new CustomArrayList<>();
        values.add(71);
        values.add(43);
        values.add(54);
        values.add(56);
        values.add(1);
        values.add(100);
        values.add(16);
        values.add(56);
        values.add(86);
        values.add(76);

        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };

        values.sort(comparator);

        assertEquals(100, values.get(9));
        assertEquals(1, values.get(0));
    }

}