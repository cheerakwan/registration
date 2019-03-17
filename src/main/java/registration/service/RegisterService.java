package registration.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import registration.bean.RegisterReq;
import registration.bean.RegisterResp;
import registration.entity.User;
import registration.exception.EmailExistsException;
import registration.repository.UserRepository;
import registration.utils.DateUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RegisterService {


    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private DateUtils dateUtils;

    public RegisterService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, DateUtils dateUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.dateUtils = dateUtils;
    }

    @Transactional
    public RegisterResp register(RegisterReq rq) throws EmailExistsException {
        RegisterResp resp = new RegisterResp();
        if(emailExist(rq.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email adress:" + rq.getEmail());
        }
        User user = new User();
        user.setUserName(rq.getUserName());
        user.setPassword(passwordEncoder.encode(rq.getPassword()));
        user.setEmail(rq.getEmail());
        user.setMobile(rq.getPhoneNumber());
        user.setSalary(rq.getSalary());
        user.setCreateBy("Admin");
        user.setCreateTime(LocalDateTime.now());

        User result = userRepository.save(user);

        String refCode = generateReferenceCode(result.getMobile());
        resp.setReferenceCode(refCode);
        return resp;

    }

    public boolean emailExist(String email) {
        User user = userRepository.findByEmailAddress(email);
        if(user != null && user.getEmail() != null) {
            return true;
        }
        return false;
    }

    public String generateReferenceCode(String mobile) {
        LocalDate currentDate = dateUtils.getCurrentDate();
        String dateString = currentDate.format(DateTimeFormatter.ofPattern("YYYYMMdd"));
        String suffix = mobile.substring(mobile.length() - 4, mobile.length());
        return dateString + suffix;
    }

}
