package servermodels.users;

import servermodels.department.Course;
import servermodels.department.Department;
import sharedmodels.users.SharedMaster;
import sharedmodels.users.SharedUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Master extends User{
    @Column
    @Enumerated(EnumType.STRING)
    private MasterRole masterRole;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Department department = new Department();
    @Column
    @Enumerated(EnumType.STRING)
    private MasterGrade grade;
    @Column
    private String roomNumber;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "Master_Course")
    private List<Course> courses = new ArrayList<>();

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

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
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
//        sharedMaster.setDepartment(department.toShared());
        ArrayList<sharedmodels.department.Course> courses1 =new ArrayList<>();
//        for (Course course : courses)
//            courses1.add(course.toShared());
//        }
        sharedMaster.setCourses(courses1);
        sharedMaster.setMasterRole(masterRole.toShared());
        return sharedMaster;
    }
}
