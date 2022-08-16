package servermodels.department;

import servermodels.cw.EducationalThing;
import servermodels.cw.HomeWork;
import servermodels.users.Master;
import servermodels.users.Student;
import sharedmodels.users.SharedMaster;
import sharedmodels.users.SharedStudent;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private int capacity;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Master master;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Department department;
    @Column
    private int unit;
    @Column
    private String weeklyTime;
    @Column
    private String examTime;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Course prerequisite;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Student> studentsHaveCourse = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "TA-Course")
    private List<Student> teacherAssistants = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<EducationalThing> educationalThings = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<HomeWork> homeWorks = new ArrayList<>();


    public Course() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public String getWeeklyTime() {
        return weeklyTime;
    }

    public void setWeeklyTime(String weeklyTime) {
        this.weeklyTime = weeklyTime;
    }

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    public List<Student> getStudentsHaveCourse() {
        return studentsHaveCourse;
    }

    public void setStudentsHaveCourse(ArrayList<Student> studentsHaveCourse) {
        this.studentsHaveCourse = studentsHaveCourse;
    }

    public List<Student> getTeacherAssistants() {
        return teacherAssistants;
    }

    public void setTeacherAssistants(ArrayList<Student> teacherAssistants) {
        this.teacherAssistants = teacherAssistants;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Course getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(Course prerequisite) {
        this.prerequisite = prerequisite;
    }

    public List<EducationalThing> getEducationalThings() {
        return educationalThings;
    }

    public void setEducationalThings(ArrayList<EducationalThing> educationalThings) {
        this.educationalThings = educationalThings;
    }

    public List<HomeWork> getHomeWorks() {
        return homeWorks;
    }

    public void setHomeWorks(ArrayList<HomeWork> homeWorks) {
        this.homeWorks = homeWorks;
    }

    public sharedmodels.department.Course toShared(){
        sharedmodels.department.Course course = new sharedmodels.department.Course();
        course.setCapacity(capacity);
        course.setDepartment(department.toShared());
        course.setId(id);
        course.setMaster((SharedMaster) master.toShared());
        course.setName(name);
        ArrayList<sharedmodels.cw.EducationalThing> educationalThings1 = new ArrayList<>();
        for (EducationalThing educationalThing : educationalThings) {
            educationalThings1.add(educationalThing.toShared());
        }
        course.setEducationalThings(educationalThings1);
        course.setWeeklyTime(weeklyTime);
        course.setUnit(unit);
        ArrayList<SharedStudent> students1 = new ArrayList<>();
        for (Student student : teacherAssistants) {
            students1.add((SharedStudent) student.toShared());
        }
        course.setTeacherAssistants(students1);
        ArrayList<SharedStudent> students2 = new ArrayList<>();
        for (Student student : studentsHaveCourse) {
            students2.add((SharedStudent) student.toShared());
        }
        course.setStudentsHaveCourse(students2);
        course.setPrerequisite(prerequisite.toShared());
        ArrayList<sharedmodels.cw.HomeWork> homeWorks1 = new ArrayList<>();
        for (HomeWork homeWork : homeWorks) {
            homeWorks1.add(homeWork.toShared());
        }
        course.setHomeWorks(homeWorks1);
        course.setExamTime(examTime);
        return course;
    }

}
