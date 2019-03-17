package registration.bean;

public class RegisterResp {

    private HeaderResp headerResp;
    private String referenceCode;

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
