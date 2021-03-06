package registration.service;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import registration.bean.HeaderResp;
import registration.bean.RegisterReq;
import registration.bean.RegisterResp;
import registration.bean.UserInfo;
import registration.entity.User;
import registration.exception.RegisterException;
import registration.repository.UserRepository;
import registration.utils.DateUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class RegisterService {

    private static String platinum = "PLATINUM";
    private static String gold = "GOLD";
    private static String silver = "SILVER";

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private DateUtils dateUtils;

    public RegisterService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, DateUtils dateUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.dateUtils = dateUtils;
    }

    @Transactional
    public RegisterResp register(RegisterReq rq) throws RegisterException {
        RegisterResp resp = new RegisterResp();
        if(isLowerSalary(rq.getSalary())) {
            throw new RegisterException("1001", "Salary must be more than or equal to 15000 baht");
        }

        if(isPlatinum(rq.getSalary())) {
            rq.setMemberType(platinum);
        }else if(isGold(rq.getSalary())) {
            rq.setMemberType(gold);
        }else if(isSilver(rq.getSalary())) {
            rq.setMemberType(silver);
        }

        if(mobileExist(rq.getPhoneNumber())) {
            throw new RegisterException("1002", "This mobile is already in the system.");
        }
        String refCode = generateReferenceCode(rq.getPhoneNumber());

        User user = new User();
        user.setUserName(rq.getUserName());
        user.setPassword(passwordEncoder.encode(rq.getPassword()));
        user.setEmail(rq.getEmail());
        user.setMobile(rq.getPhoneNumber());
        user.setSalary(rq.getSalary());
        user.setMemberType(rq.getMemberType());
        user.setReferenceCode(refCode);
        user.setCreateBy("Admin");
        user.setCreateTime(LocalDateTime.now());

        User result = userRepository.save(user);

        resp.setReferenceCode(result.getReferenceCode());
        resp.setHeaderResp(statusSuccess());
        return resp;

    }

    public boolean mobileExist(String email) {
        List<User> user = userRepository.findByMobile(email);
        if(user != null && user.size() > 0) {
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

    private HeaderResp statusSuccess() {
        HeaderResp headerResp = new HeaderResp();
        headerResp.setStatusCd("0000");
        headerResp.setStatusDesc("SUCCESS");
        return headerResp;
    }

    public boolean isPlatinum(BigDecimal salary) {

        return salary.doubleValue() > 50000;
    }

    public boolean isGold(BigDecimal salary) {
        return  salary.doubleValue() >= 30000 && salary.doubleValue() <= 50000;
    }

    public boolean isSilver(BigDecimal salary) {
        return salary.doubleValue() < 30000;
    }

    public boolean isLowerSalary(BigDecimal salary) {
        return salary.doubleValue() < 15000;
    }

    public UserInfo getUserRegister(String referenceCode) {
        UserInfo userInfo = new UserInfo();
        List<User> users = userRepository.findByReferenceCode(referenceCode);
        if(users != null && users.size() > 0) {
            BeanUtils.copyProperties(users.get(0), userInfo);
            userInfo.setHeaderResp(statusSuccess());
        }

        return  userInfo;
    }

}
