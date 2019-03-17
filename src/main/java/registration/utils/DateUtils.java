package registration.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateUtils {

    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}
