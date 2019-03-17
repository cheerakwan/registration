package registration.utils;

import org.springframework.stereotype.Component;

@Component
public class CommonUtils {

    public boolean isEmpty(String data) {
        return data == null || data.trim().equals("");
    }
}
