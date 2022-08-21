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
    private String registrationTimeStart;
    @Column
    private String registrationTimeEnd;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Student_Course")
    private List<Course> courses = new ArrayList<>() ;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "student_starredCourse")
    private List<Course> starredCourses = new ArrayList<>();
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
        average = 0;
        units = 0;
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

    public String getRegistrationTimeStart() {
        return registrationTimeStart;
    }

    public void setRegistrationTimeStart(String registrationTimeStart) {
        this.registrationTimeStart = registrationTimeStart;
    }

    public String getRegistrationTimeEnd() {
        return registrationTimeEnd;
    }

    public void setRegistrationTimeEnd(String registrationTimeEnd) {
        this.registrationTimeEnd = registrationTimeEnd;
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

    public List<Course> getStarredCourses() {
        return starredCourses;
    }

    public void setStarredCourses(List<Course> starredCourses) {
        this.starredCourses = starredCourses;
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
    }

    public void setUnits(){
        int unitsHave = 0;
        for (Course course : courses) {
            unitsHave += course.getUnit();
        }
        units = unitsHave;
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
        if (department != null){
            sharedStudent.setDepartmentId(department.getId());
        }
        sharedStudent.setUnits(units);
        if (temporaryCourses != null ){
            ArrayList<String> temporaryCoursesId = new ArrayList<>();
            for (int i = 0; i < temporaryCourses.size(); i++) {
                TemporaryCourse temporaryCourse = temporaryCourses.get(i);
                temporaryCoursesId.add(temporaryCourse.getId());
            }
            sharedStudent.setTemporaryCoursesId(temporaryCoursesId);
        }
        sharedStudent.setStatus(status.toShared());
        sharedStudent.setRegistrationTimeStart(registrationTimeStart);
        sharedStudent.setRegistrationTimeEnd(registrationTimeEnd);
        sharedStudent.setRegistrationLicence(registrationLicence.toShared());
        if (passedCourses != null){
            ArrayList<String> passedCourses1 = new ArrayList<>();
            for (PassedCourse passedCourse : passedCourses) {
                passedCourses1.add(passedCourse.getId());
            }
            sharedStudent.setPassedCoursesId(passedCourses1);
        }
        if (helperMaster != null){
            sharedStudent.setHelperSharedMasterId(helperMaster.getUsername());
        }
        sharedStudent.setGrade(grade.toShared());
        sharedStudent.setEnteringYear(enteringYear);
        if (courses != null){
            ArrayList<String> courses1 =new ArrayList<>();
            for (Course course : courses) {
                courses1.add(course.getId());
            }
            sharedStudent.setCoursesId(courses1);
        }
        return sharedStudent;
    }
}
