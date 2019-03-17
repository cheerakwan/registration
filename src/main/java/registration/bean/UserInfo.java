package registration.bean;

import java.math.BigDecimal;

public class UserInfo {
    private HeaderResp headerResp;
    private String userName;
    private String mobile;
    private BigDecimal salary;
    private String email;
    private String memberType;
    private String referenceCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public HeaderResp getHeaderResp() {
        return headerResp;
    }

    public void setHeaderResp(HeaderResp headerResp) {
        this.headerResp = headerResp;
    }
}
