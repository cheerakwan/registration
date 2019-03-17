package registration.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import registration.bean.HeaderResp;
import registration.bean.RegisterReq;
import registration.bean.RegisterResp;
import registration.exception.RegisterException;
import registration.service.RegisterService;


@RestController
public class RegisterController {

    private static final Logger logger = LogManager.getLogger(RegisterController.class);



    private RegisterService registerService;
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }


    @PostMapping(path = "/registration")
    public ResponseEntity<?> register(@RequestBody RegisterReq rq) {
        RegisterResp resp = new RegisterResp();
        try{
            resp = registerService.register(rq);

        }catch (RegisterException e) {
            logger.error(e.getMessage());
            resp.setHeaderResp(setHeaderResp(e.getCode(), e.getMessage()));
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


        return ResponseEntity.ok().build();
    }

}
