package de.thro.inf.prg3.a04.collections;

import de.thro.inf.prg3.a04.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class SimpleListTests {

    private final Logger logger = LogManager.getLogger();
    private SimpleList<Integer> testList;

    @BeforeEach
    void setup() {
        testList = new SimpleListImpl<Integer>();

        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);
        testList.add(5);
    }

    @Test
    void testAddElements() {
        logger.info("Testing if adding and iterating elements is implemented correctly");
        int counter = 0;
        for (Integer o : testList) {
            counter++;
        }
        assertEquals(5, counter);
    }

    @Test
    void testSize() {
        logger.info("Testing if size() method is implemented correctly");
        assertEquals(5, testList.size());
    }

    @Test
    void testFilterAnonymousClass() {
        logger.info("Testing the filter possibilities by filtering for all elements greater 2");
        SimpleList<Integer> result = testList.filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer item) {
                return item > 2;
            }
        });

        for (Integer i : result) {
            assertTrue(i > 2);
        }
    }

    @Test
    void testAddEmpty() {
        logger.info("Testing to add a new empty list element");
        SimpleList<Person> result = new SimpleListImpl<>();
        result.addDefault(Person.class);
        logger.info("Person: " + result.get(0));
        assertEquals(1, result.size());
    }

    @Test
    void testFilterLambda() {
        logger.info("Testing the filter possibilities by filtering for all elements which are dividable by 2");
        SimpleList<Integer> result = testList.filter(o -> o % 2 == 0);
        for (Integer i : result) {
            assertEquals(i % 2, 0);
        }
    }
}
