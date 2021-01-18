package pojoClassesDeserialization;


public class GetCourse_Main_Class {
    
    private String url;
    private String services;
    private String expertise;
    private Courses_Child_Class courses_Child_Class;   // Created new class "Ex2_courses" as it has more 3 variables and written return type as class.
    private String instructor;
    private String linkedIn;
        
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getServices() {
        return services;
    }
    
    public void setServices(String services) {
        this.services = services;
    }
    
    public String getExpertise() {
        return expertise;
    }
    
    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }
    
    public Courses_Child_Class getCourses() {
        return courses_Child_Class;
    }
    
    public void setCourses(Courses_Child_Class courses_Child_Class) {
        this.courses_Child_Class = courses_Child_Class;
    }
    
    public String getInstructor() {
        return instructor;
    }
    
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    
    public String getLinkedIn() {
        return linkedIn;
    }
    
    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }
}
