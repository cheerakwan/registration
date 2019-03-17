package registration.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import registration.bean.HeaderResp;
import registration.bean.RegisterReq;
import registration.bean.RegisterResp;
import registration.bean.UserInfo;
import registration.exception.RegisterException;
import registration.service.RegisterService;
import registration.utils.CommonUtils;


@RestController
public class RegisterController {

    private static final Logger logger = LogManager.getLogger(RegisterController.class);


    private CommonUtils commonUtils;
    private RegisterService registerService;
    public RegisterController(RegisterService registerService, CommonUtils commonUtils) {
        this.registerService = registerService;
        this.commonUtils = commonUtils;
    }


    @PostMapping(path = "/registration")
    public ResponseEntity<?> register(@RequestBody RegisterReq rq) {
        RegisterResp resp = new RegisterResp();
        if(isValidData(rq)) {

            try {
                resp = registerService.register(rq);

            } catch (RegisterException e) {
                logger.error(e.getMessage());
                resp.setHeaderResp(setHeaderResp(e.getCode(), e.getMessage()));
            }
        }else {
            resp.setHeaderResp(setHeaderResp("1003", "Invalid data"));
        }

        return ResponseEntity.ok(resp);
    }

    private HeaderResp setHeaderResp(String code, String description) {
        HeaderResp headerResp = new HeaderResp();
        headerResp.setStatusCd(code);
        headerResp.setStatusDesc(description);
        return headerResp;
    }

    @GetMapping(path = "/users")
    public ResponseEntity<?> getUserRegister(@RequestParam(name = "referenceCode", required = true) String referenceCode) {
        UserInfo userInfo = registerService.getUserRegister(referenceCode);
        if(userInfo == null || userInfo.getReferenceCode() == null) {
            userInfo.setHeaderResp(setHeaderResp("2001", "Data not found."));
        }

        return ResponseEntity.ok(userInfo);
    }

    public boolean isValidData(RegisterReq rq) {
        if(commonUtils.isEmpty(rq.getUserName()) || commonUtils.isEmpty(rq.getPassword()) ||
        commonUtils.isEmpty(rq.getPhoneNumber()) || commonUtils.isEmpty(rq.getEmail()) ||
        rq.getSalary() == null ) {
            return false;
        }

        return true;
    }

}
