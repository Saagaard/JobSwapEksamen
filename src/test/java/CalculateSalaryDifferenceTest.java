import org.eksamen.jobswap.businessServices.CalculateSalaryDifference;
import org.eksamen.jobswap.domain.Job;
import org.eksamen.jobswap.persistence.EmployeeDAOImpl;
import org.eksamen.jobswap.persistence.WorkplaceDAOImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CalculateSalaryDifferenceTest {

    EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
    WorkplaceDAOImpl workplaceDAO = new WorkplaceDAOImpl();

    private Job createJob(float salary) throws Exception {
        return new Job(
                1,
                employeeDAO.read(1),
                workplaceDAO.read(1),
                "Softwareudvikler",
                LocalDate.now(),
                salary
        );
    }

    @Test
    void testSalaryDifference_IN() throws Exception {
        Job job1 = createJob(10000f);
        Job job2 = createJob(11200f); // 12% increase

        float result = CalculateSalaryDifference.calculateSalaryDifference(job1, job2);
        assertEquals(12f, result, 0.01f, "Expected salary difference: 12%");
    }

    @Test
    void testSalaryDifference_OUT() throws Exception {
        Job job1 = createJob(10000f);
        Job job2 = createJob(11600f); // 16% increase

        float result = CalculateSalaryDifference.calculateSalaryDifference(job1, job2);
        assertEquals(16f, result, 0.01f, "Expected salary difference: 16%");
    }

    @Test
    void testSalaryDifference_ON_0_PERCENT() throws Exception {
        Job job1 = createJob(10000f);
        Job job2 = createJob(10000f);

        float result = CalculateSalaryDifference.calculateSalaryDifference(job1, job2);
        assertEquals(0f, result, 0.01f);
    }

    @Test
    void testSalaryDifference_ON_15_PERCENT() throws Exception {
        Job job1 = createJob(10000f);
        Job job2 = createJob(11500f); // 15% salary difference

        float result = CalculateSalaryDifference.calculateSalaryDifference(job1, job2);
        assertEquals(15f, result, 0.01f);
    }

    @Test
    void testSalaryDifference_OFF() throws Exception {
        Job job1 = createJob(10000f);
        Job job2 = createJob(11510f); // 15.1% salary difference

        float result = CalculateSalaryDifference.calculateSalaryDifference(job1, job2);
        assertEquals(15.1f, result, 0.01f);
    }
}
