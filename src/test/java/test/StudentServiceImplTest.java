package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.danko.testtask.dao.StudentDao;
import ru.danko.testtask.dao.impl.StudentDaoImpl;
import ru.danko.testtask.entity.Student;
import ru.danko.testtask.exception.DaoServiceException;
import ru.danko.testtask.services.StudentService;
import ru.danko.testtask.services.impl.StudentServiceImpl;
import ru.danko.testtask.validator.StudentValidator;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(JUnit4.class)
public class StudentServiceImplTest {

    private static final String TEST_SURNAME = "surname";

    @Mock
    private StudentValidator studentValidator;

    @Mock
    private StudentDaoImpl studentDao;

    @InjectMocks
    private StudentServiceImpl service;

    @Before
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetStudentBySurname() throws DaoServiceException {
        Mockito.when(studentValidator.isNameValid(TEST_SURNAME)).thenReturn(true);
        Student actual = new Student();
        actual.setSurname(TEST_SURNAME);
        Mockito.when(studentDao.findBySurname(TEST_SURNAME)).thenReturn(java.util.Optional.of(actual));
        Optional<Student> expected = service.findBySurname(TEST_SURNAME);
        Assert.assertEquals(expected, Optional.of(actual));
    }

}