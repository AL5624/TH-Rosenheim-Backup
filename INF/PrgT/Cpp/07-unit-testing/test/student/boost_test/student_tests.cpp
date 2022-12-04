#define BOOST_TEST_MODULE TestStudent
#include <boost/test/included/unit_test.hpp>
#include "Student.h"

/* See https://www.boost.org/doc/libs/1_74_0/libs/test/doc/html/boost_test/testing_tools/extended_comparison/floating_point.html */
namespace utf = boost::unit_test;

/*
 * TODO: Add a StudentFixture
 * Have a look here: https://www.boost.org/doc/libs/1_74_0/libs/test/doc/html/boost_test/tests_organization/fixtures.html
 * You can choose to use the default constructor or the value constructor
 */

/*
 * TODO: Add a boost test suite and connect it to the Student fixture
 * Tip: Also finish the task at the end of this file
 */


BOOST_AUTO_TEST_CASE(VisitLecture)
{
    BOOST_TEST("" == student->getEmoji());
    student->visitLecture();
    BOOST_TEST("focused" == student->getEmoji(), "The student does not focus.");
}

/*
 * TODO: Add a test that calls solveAssignment(9)
 * What are 2 possible tests for Right-BICEP?
 * Add a tolerance, provide a test message
 */





/*
 * TODO: Add a test for Right-BICEP - Boundary Condition
 * Call solveAssignment(-1), provide a test message
 */





/* TODO: Add a test for Right-BICEP - Inverse Operation
 * Call solveAssignment(9)
 * Test the inverse operation (multiplication)
 * Add a tolerance, provide a test message
 */



/* TODO: Add a test for Right-BICEP - Cross-Check
 * Call solveAssignment(9)
 * Implement an alternative approach,
 * i.e. use the Heron Method (https://de.wikipedia.org/wiki/Heron-Verfahren)
 * Test the inverse operation (multiplication)
 * Add a tolerance, provide a test message
 */




/*
 * TODO: Intentionally fail a test of your choice, provide a text message
 * Test your code locally and comment it out, to allow the pipeline to succeed
 * when committing.
 */




/*
 * TODO: Add a test that uses BOOST_REQUIRE and solveDifficultAssignment("Peter").
 * This test is followed by a BOOST_CHECK_EQUAL for solveDifficultAssignment("Anna").
 */






/* TODO: What steps are needed to make this test pass?
 * Look here for adding a timeout: https://www.boost.org/doc/libs/1_74_0/libs/test/doc/html/boost_test/testing_tools/timeout.html
 */

BOOST_AUTO_TEST_CASE(SolveDifficultAssignmentTimeout, *utf::timeout(1)) {
    BOOST_TEST(student->solveDifficultAssignment("Argh"));
    BOOST_TEST(student->solveDifficultAssignment("Geist ziert Leben, Mut hegt Siege, Beileid trägt belegbare Reue, Neid dient nie, nun eint Neid die Neuerer, abgelebt gärt die Liebe, Geist geht, umnebelt reizt Sieg."));
}




/* TODO: End the boost test suite */