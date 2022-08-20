package servermodels.users;

import servermodels.department.Course;
import servermodels.department.Department;
import servermodels.department.PassedCourse;
import servermodels.department.TemporaryCourse;
import sharedmodels.users.SharedMaster;
import sharedmodels.users.SharedStudent;
import sharedmodels.users.SharedUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Entity
public class Student extends User{
    @Column
    private double average;
    @Column(name = "user_units")
    private int units;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "student_department")
    private Department department;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "student_master")
    private Master helperMaster;
    @Column
    private String enteringYear;
    @Column(name = "studentGrade")
    @Enumerated(EnumType.STRING)
    private StudentGrade grade;
    @Column
    @Enumerated(EnumType.STRING)
    private EducationalStatus status;
    @Column
    @Enumerated(EnumType.STRING)
    private Licence registrationLicence;
    @Column
    private String registrationTime;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Student_Course")
    private List<Course> courses = new ArrayList<>() ;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "student_passed")
    private List<PassedCourse> passedCourses  = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "student_temporary")
    private List<TemporaryCourse> temporaryCourses  = new ArrayList<>();

    public Student(){
        super();
    }

    public Student(String username, String password){
        super(username, password);
        this.setRole(Role.STUDENT);
        setRegistrationLicence();
        this.setAverage();
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public Master getHelperMaster() {
        return helperMaster;
    }

    public void setHelperMaster(Master helperMaster) {
        this.helperMaster = helperMaster;
    }

    public String getEnteringYear() {
        return enteringYear;
    }

    public void setEnteringYear(String enteringYear) {
        this.enteringYear = enteringYear;
    }

    public StudentGrade getGrade() {
        return grade;
    }

    public void setGrade(StudentGrade grade) {
        this.grade = grade;
    }

    public EducationalStatus getStatus() {
        return status;
    }

    public void setStatus(EducationalStatus status) {
        this.status = status;
    }

    public Licence getRegistrationLicence() {
        return registrationLicence;
    }

    public void setRegistrationLicence(Licence registrationLicence) {
        this.registrationLicence = registrationLicence;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<PassedCourse> getPassedCourses() {
        return passedCourses;
    }

    public void setPassedCourses(List<PassedCourse> passedCourses) {
        this.passedCourses = passedCourses;
    }

    public List<TemporaryCourse> getTemporaryCourses() {
        return temporaryCourses;
    }

    public void setTemporaryCourses(List<TemporaryCourse> temporaryCourses) {
        this.temporaryCourses = temporaryCourses;
    }

    public void setRegistrationLicence() {
        Random rd = new Random();
        int random = rd.nextInt() % 2;
        if (random == 0) this.registrationLicence = Licence.ALLOWED;
        else this.registrationLicence = Licence.ILLEGAL;
    }

    public void setAverage()  {
        int size = passedCourses.size();
        double sum = 0;
        int unitsPassed = 0;
        for (int i = 0; i < size; i++) {
            sum += passedCourses.get(i).getMark();
            unitsPassed += passedCourses.get(i).getUnit();
        }
        average = sum / unitsPassed;
        units = unitsPassed;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public SharedUser toShared() {
        SharedStudent sharedStudent = new SharedStudent();
        sharedStudent.setEmailAddress(getEmailAddress());
        sharedStudent.setRole(sharedmodels.users.Role.STUDENT);
        sharedStudent.setUsername(getUsername());
        sharedStudent.setUserImageBytes(getUserImageBytes());
        sharedStudent.setPhoneNumber(getPhoneNumber());
        sharedStudent.setNationalCode(getNationalCode());
        sharedStudent.setLastLogin(getLastLogin());
        sharedStudent.setFullName(getFullName());
        sharedStudent.setAverage(average);
//        if (department != null){
//            sharedStudent.setDepartment(department.toShared());
//        }
//        sharedStudent.setUnits(units);
//        if (temporaryCourses != null ){
//            ArrayList<sharedmodels.department.TemporaryCourse> temporaryCourses1 = new ArrayList<>();
//            for (int i = 0; i < temporaryCourses.size(); i++) {
//                TemporaryCourse temporaryCourse = temporaryCourses.get(i);
//                temporaryCourses1.add(temporaryCourse.toShared());
//            }
//            sharedStudent.setTemporaryCourses(temporaryCourses1);
//        }
        sharedStudent.setStatus(status.toShared());
        sharedStudent.setRegistrationTime(registrationTime);
        sharedStudent.setRegistrationLicence(registrationLicence.toShared());
//        if (passedCourses != null){
//            ArrayList<sharedmodels.department.PassedCourse> passedCourses1 = new ArrayList<>();
//            for (PassedCourse passedCourse : passedCourses) {
//                passedCourses1.add(passedCourse.toShared());
//            }
//            sharedStudent.setPassedCourses(passedCourses1);
//        }
//        if (helperMaster != null){
//            sharedStudent.setHelperMaster((SharedMaster) helperMaster.toShared());
//        }
        sharedStudent.setGrade(grade.toShared());
        sharedStudent.setEnteringYear(enteringYear);
//        if (courses != null){
//            ArrayList<sharedmodels.department.Course> courses1 =new ArrayList<>();
//            for (Course course : courses) {
//                courses1.add(course.toShared());
//            }
//            sharedStudent.setCourses(courses1);
//        }
        return sharedStudent;
    }

    @Override
    public String toString() {
        return "Student{" +
                "average=" + average +
                ", units=" + units +
                ", department=" + department +
                ", helperMaster=" + helperMaster +
                ", enteringYear='" + enteringYear + '\'' +
                ", grade=" + grade +
                ", status=" + status +
                ", registrationLicence=" + registrationLicence +
                ", registrationTime='" + registrationTime + '\'' +
                ", courses=" + courses +
                ", passedCourses=" + passedCourses +
                ", temporaryCourses=" + temporaryCourses +
                '}';
    }
}
