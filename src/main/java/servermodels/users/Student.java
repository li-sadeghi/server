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
    @Column
    private int units;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Department department;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Master helperMaster;
    @Column
    private String enteringYear;
    @Column
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
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "Student_Course")
    private List<Course> courses = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<PassedCourse> passedCourses = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<TemporaryCourse> temporaryCourses = new ArrayList<>();

    public Student(){
        super();
    }

    public Student(String username, String password){
        super(username, password);
        this.setRole(Role.STUDENT);
        setRegistrationLicence();
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

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public List<PassedCourse> getPassedCourses() {
        return passedCourses;
    }

    public void setPassedCourses(ArrayList<PassedCourse> passedCourses) {
        this.passedCourses = passedCourses;
    }

    public List<TemporaryCourse> getTemporaryCourses() {
        return temporaryCourses;
    }

    public void setTemporaryCourses(ArrayList<TemporaryCourse> temporaryCourses) {
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
            //TODO
        }
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
        sharedStudent.setDepartment(department.toShared());
        sharedStudent.setUnits(units);
        ArrayList<sharedmodels.department.TemporaryCourse> temporaryCourses1 = new ArrayList<>();
        for (TemporaryCourse temporaryCourse : temporaryCourses) {
            temporaryCourses1.add(temporaryCourse.toShared());
        }
        sharedStudent.setTemporaryCourses(temporaryCourses1);
        sharedStudent.setStatus(status.toShared());
        sharedStudent.setRegistrationTime(registrationTime);
        sharedStudent.setRegistrationLicence(registrationLicence.toShared());
        ArrayList<sharedmodels.department.PassedCourse> passedCourses1 = new ArrayList<>();
        for (PassedCourse passedCourse : passedCourses) {
            passedCourses1.add(passedCourse.toShared());
        }
        sharedStudent.setPassedCourses(passedCourses1);
        sharedStudent.setHelperMaster((SharedMaster) helperMaster.toShared());
        sharedStudent.setGrade(grade.toShared());
        sharedStudent.setEnteringYear(enteringYear);
        ArrayList<sharedmodels.department.Course> courses1 =new ArrayList<>();
        for (Course course : courses) {
            courses1.add(course.toShared());
        }
        sharedStudent.setCourses(courses1);
        return sharedStudent;
    }
}
