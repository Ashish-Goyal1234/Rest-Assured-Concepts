package pojoClassesDeserialization;

import java.util.List;

public class Courses_Child_Class {
  
    private List<WebAutomation_SubChild_Class_1> webAutomation_SubChild_Class_1;
    private List<API_SubChild_Class_2> API_SubChild_Class_2;
    private List<Mobile_SubChild_Class_3> mobile_SubChild_Class_3;
    
    public List<WebAutomation_SubChild_Class_1> getWebAutomation() {
        return webAutomation_SubChild_Class_1;
    }
    
    public void setWebAutomation(List<WebAutomation_SubChild_Class_1> webAutomation_SubChild_Class_1) {
        this.webAutomation_SubChild_Class_1 = webAutomation_SubChild_Class_1;
    }
    
    public List<pojoClassesDeserialization.API_SubChild_Class_2> getAPI() {
        return API_SubChild_Class_2;
    }
    
    public void setAPI(List<pojoClassesDeserialization.API_SubChild_Class_2> aPI_SubChild_Class_2) {
        API_SubChild_Class_2 = aPI_SubChild_Class_2;
    }
    
    public List<Mobile_SubChild_Class_3> getMobile() {
        return mobile_SubChild_Class_3;
    }
    
    public void setMobile(List<Mobile_SubChild_Class_3> mobile_SubChild_Class_3) {
        this.mobile_SubChild_Class_3 = mobile_SubChild_Class_3;
    }

}
