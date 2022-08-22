package servermodels.users;

import servermodels.department.Course;
import servermodels.department.Department;
import sharedmodels.users.SharedMaster;
import sharedmodels.users.SharedStudent;
import sharedmodels.users.SharedUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Master extends User{
    @Column
    @Enumerated(EnumType.STRING)
    private MasterRole masterRole;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "master_Department")
    private Department department ;
    @Column(name = "masterGrade")
    @Enumerated(EnumType.STRING)
    private MasterGrade grade;
    @Column
    private String roomNumber;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Master_Course")
    private List<Course> courses  = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "master_student")
    private List<Student> studentsIsHelperMaster = new ArrayList<>();

    public Master() {
        super();
    }

    public Master(String username, String password) {
        super(username, password);
        this.setRole(Role.MASTER);
    }

    public MasterRole getMasterRole() {
        return masterRole;
    }

    public void setMasterRole(MasterRole masterRole) {
        this.masterRole = masterRole;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public MasterGrade getGrade() {
        return grade;
    }

    public void setGrade(MasterGrade grade) {
        this.grade = grade;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Student> getStudentsIsHelperMaster() {
        return studentsIsHelperMaster;
    }

    public void setStudentsIsHelperMaster(List<Student> studentsIsHelperMaster) {
        this.studentsIsHelperMaster = studentsIsHelperMaster;
    }

    @Override
    public SharedUser toShared() {
        SharedMaster sharedMaster = new SharedMaster();
        sharedMaster.setEmailAddress(getEmailAddress());
        sharedMaster.setRole(getRole().toShared());
        sharedMaster.setUsername(getUsername());
        sharedMaster.setUserImageBytes(getUserImageBytes());
        sharedMaster.setPhoneNumber(getPhoneNumber());
        sharedMaster.setNationalCode(getNationalCode());
        sharedMaster.setLastLogin(getLastLogin());
        sharedMaster.setFullName(getFullName());
        sharedMaster.setRoomNumber(roomNumber);
        sharedMaster.setGrade(grade.toShared());
        sharedMaster.setDepartmentId(department.getId());
        ArrayList<String> courses1 =new ArrayList<>();
        for (Course course : courses)
            courses1.add(course.getId());

        List<String> students = new ArrayList<>();
        for (Student student : studentsIsHelperMaster) {
            students.add( student.getUsername());
        }
        sharedMaster.setStudentsIsHelperMasterIds(students);
        sharedMaster.setCoursesId(courses1);
        sharedMaster.setMasterRole(masterRole.toShared());
        return sharedMaster;
    }
}
