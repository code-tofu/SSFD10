package ibf2022.batch2.ssf.frontcontroller.model;

public class SessAuth {
    private boolean authFlag = false;
    private boolean CaptchaFlag = false;
    private int failCount = 0;
    
    public boolean isAuthFlag() {
        return authFlag;
    }
    public void setAuthFlag(boolean authFlag) {
        this.authFlag = authFlag;
    }
    public boolean isCaptchaFlag() {
        return CaptchaFlag;
    }
    public void setCaptchaFlag(boolean notCaptchaFlag) {
        this.CaptchaFlag = notCaptchaFlag;
    }
    public int getFailCount() {
        return failCount;
    }
    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }
    
    @Override
    public String toString() {
        return "SessAuth [authFlag=" + authFlag + ", CaptchaFlag=" + CaptchaFlag + ", failCount=" + failCount + "]";
    }
    
    
}
