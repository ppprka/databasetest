package test;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import ru.danko.testtask.dao.StudentDao;
import ru.danko.testtask.dao.impl.StudentDaoImpl;
import ru.danko.testtask.entity.Student;
import ru.danko.testtask.exception.DaoServiceException;
import ru.danko.testtask.services.StudentService;
import ru.danko.testtask.services.impl.StudentServiceImpl;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class StudentServiceImplTest {

    private static StudentDao studentDao;
    private static StudentService studentService;
    public static final String ID = "id";

    @BeforeClass
    public static void setUp() {
        studentDao = mock(StudentDaoImpl.class);
        Whitebox.setInternalState(StudentDaoImpl.class, "INSTANCE", studentDao);
        studentService = new StudentServiceImpl();
    }

    @AfterClass
    public static void tearDown() {
        studentDao = null;
        studentService = null;
    }

    @Test
    public void findByIdPositiveTest() {
        try {
            Student expected = new Student();
            when(studentDao.findById(any(Long.class))).thenReturn(Optional.of(new Student()));
            Optional<Student> actual = studentService.findById("1");
            assertEquals(actual.get(), expected);
        } catch (DaoServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findByIdNegativeTest() {
        try {
            Optional<Student> expected = Optional.of(new Student());
            when(studentDao.findById(any(Long.class))).thenReturn(Optional.of(new Student()));
            Optional<Student> actual = studentService.findById(" ");
            assertNotEquals(actual, expected);
        } catch (DaoServiceException e) {
            fail("Incorrect data", e);
        }
    }

}