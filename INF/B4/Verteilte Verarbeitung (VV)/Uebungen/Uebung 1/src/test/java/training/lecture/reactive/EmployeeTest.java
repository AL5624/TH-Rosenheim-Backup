package training.lecture.reactive;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class EmployeeTest
{

    @Test
    public void testAbsentToError()
    {
        Employee employee = new Employee();
        assertThat("Anfangszustand : ABSENT", employee.getCurrentState(), equalTo(Employee.State.ABSENT));
        employee.transition(Employee.Symbol.RIGHT);
        assertThat("ABSENT - RIGHT --> ERROR",employee.getCurrentState(), equalTo(Employee.State.ERROR));
        employee.transition(Employee.Symbol.RIGHT);
        assertThat("ERROR - RIGHT --> ERROR", employee.getCurrentState(), equalTo(Employee.State.ERROR));
        employee.transition(Employee.Symbol.LEFT);
        assertThat("ERROR - LEFT --> ERROR", employee.getCurrentState(), equalTo(Employee.State.ERROR));
    }

    @Test
    public void testAbsentToPassageToPresentToError()
    {
        Employee employee = new Employee();
        assertThat("Anfangszustand : ABSENT", employee.getCurrentState(), equalTo(Employee.State.ABSENT));
        employee.transition(Employee.Symbol.LEFT);
        assertThat("ABSENT - LEFT --> PASSAGE", employee.getCurrentState(), equalTo(Employee.State.PASSAGE));
        employee.transition(Employee.Symbol.RIGHT);
        assertThat("PASSAGE - RIGHT --> PRESENT", employee.getCurrentState(), equalTo(Employee.State.PRESENT));
        employee.transition(Employee.Symbol.LEFT);
        assertThat("PASSAGE - LEFT --> ERROR", employee.getCurrentState(), equalTo(Employee.State.ERROR));
    }

    @Test
    public void testAbsentToAbsent()
    {
        Employee employee = new Employee();
        assertThat("Anfangszustand : ABSENT", employee.getCurrentState(), equalTo(Employee.State.ABSENT));
        employee.transition(Employee.Symbol.LEFT);
        assertThat("ABSENT - LEFT --> PASSAGE", employee.getCurrentState(), equalTo(Employee.State.PASSAGE));
        employee.transition(Employee.Symbol.RIGHT);
        assertThat("PASSAGE - RIGHT --> PRESENT", employee.getCurrentState(), equalTo(Employee.State.PRESENT));
        employee.transition(Employee.Symbol.RIGHT);
        assertThat("PRESENT - RIGHT --> PASSAGE", employee.getCurrentState(), equalTo(Employee.State.PASSAGE));
        employee.transition(Employee.Symbol.LEFT);
        assertThat("PASSAGE - LEFT --> ABSENT", employee.getCurrentState(), equalTo(Employee.State.ABSENT ));
    }
}