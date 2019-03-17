package registration.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import registration.entity.User;
import registration.repository.UserRepository;
import registration.utils.DateUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterServiceTest {

    @Autowired
    RegisterService registerService;

    @MockBean
    DateUtils dateUtils;

    @MockBean
    UserRepository userRepository;

    @Test
    public void testGenerateReferenceCodeOfDay20190315() {
        String mobile = "0872562099";
        LocalDate day20190315 = LocalDate.of(2019, Month.MARCH, 15);
        Mockito.when(dateUtils.getCurrentDate()).thenReturn(day20190315);
        String result = registerService.generateReferenceCode(mobile);

        assertEquals("201903152099", result);
    }

    @Test
    public void testGenerateReferenceCodeOfDay20190316() {
        String mobile = "0872562099";
        LocalDate day20190216 = LocalDate.of(2019, Month.FEBRUARY, 16);
        Mockito.when(dateUtils.getCurrentDate()).thenReturn(day20190216);
        String result = registerService.generateReferenceCode(mobile);

        assertEquals("201902162099", result);
    }

    @Test
    public void testMobileExist() {
        String mobile = "0871365309";
        User u = new User();
        u.setMobile(mobile);
        Mockito.when(userRepository.findByMobile(mobile)).thenReturn(Arrays.asList(u));
        boolean result = registerService.mobileExist(mobile);
        assertTrue(result);
    }

    @Test
    public void testMobileNotExist() {
        String mobile = "0871365309";
        Mockito.when(userRepository.findByMobile(mobile)).thenReturn(Arrays.asList());
        boolean result = registerService.mobileExist(mobile);
        assertFalse(result);
    }

    @Test
    public void testIsNotLowerSalary() {
        BigDecimal s = new BigDecimal("50000");
        boolean  result = registerService.isLowerSalary(s);
        assertFalse(result);
    }

    @Test
    public void testIsLowerSalary() {
        BigDecimal s = new BigDecimal("14999");
        boolean  result = registerService.isLowerSalary(s);
        assertTrue(result);
    }
}
