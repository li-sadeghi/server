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
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "course_master")
    private Master master;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "course_department")
    private Department department;
    @Column
    private int unit;
    @Column
    private String weeklyTime;
    @Column
    private boolean haveCwPage = false;
    @Column
    private String examTime;
    @Column
    private String prerequisiteId;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "course_student_have_course")
    private List<Student> studentsHaveCourse = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "course_TA_Course")
    private List<Student> teacherAssistants  = new ArrayList<>();
    @OneToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "course_educational")
    private List<EducationalThing> educationalThings  = new ArrayList<>();
    @OneToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "course_homework")
    private List<HomeWork> homeWorks  = new ArrayList<>();


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

    public void setStudentsHaveCourse(List<Student> studentsHaveCourse) {
        this.studentsHaveCourse = studentsHaveCourse;
    }

    public List<Student> getTeacherAssistants() {
        return teacherAssistants;
    }

    public void setTeacherAssistants(List<Student> teacherAssistants) {
        this.teacherAssistants = teacherAssistants;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getPrerequisiteId() {
        return prerequisiteId;
    }

    public void setPrerequisiteId(String prerequisiteId) {
        this.prerequisiteId = prerequisiteId;
    }

    public List<EducationalThing> getEducationalThings() {
        return educationalThings;
    }

    public void setEducationalThings(List<EducationalThing> educationalThings) {
        this.educationalThings = educationalThings;
    }

    public List<HomeWork> getHomeWorks() {
        return homeWorks;
    }

    public void setHomeWorks(List<HomeWork> homeWorks) {
        this.homeWorks = homeWorks;
    }

    public boolean isHaveCwPage() {
        return haveCwPage;
    }

    public void setHaveCwPage(boolean haveCwPage) {
        this.haveCwPage = haveCwPage;
    }

    public boolean isMaaref(){
        if (this.department.getName().equals("Maaref"))return true;
        return false;
    }

    public sharedmodels.department.Course toShared(){
        sharedmodels.department.Course course = new sharedmodels.department.Course();
        course.setCapacity(capacity);
        course.setDepartmentId(department.getId());
        course.setId(id);
        course.setMaster((SharedMaster) master.toShared());
        course.setName(name);
        ArrayList<Integer> educationalThings1 = new ArrayList<>();
//        for (EducationalThing educationalThing : educationalThings) {
//            educationalThings1.add(educationalThing.getId());
//        }
        course.setEducationalThingsId(educationalThings1);
        course.setWeeklyTime(weeklyTime);
        course.setUnit(unit);
        ArrayList<String> students1 = new ArrayList<>();
        for (Student student : teacherAssistants) {
            students1.add(student.getUsername());
        }
        course.setTeacherAssistantsId(students1);
        ArrayList<String> students2 = new ArrayList<>();
        for (Student student : studentsHaveCourse) {
            students2.add( student.getUsername());
        }
        course.setStudentsHaveCourseId(students2);
        course.setPrerequisiteId(prerequisiteId);
//        ArrayList<Integer> homeWorks1 = new ArrayList<>();
//        for (HomeWork homeWork : homeWorks) {
//            homeWorks1.add(homeWork.getId());
//        }
//        course.setHomeWorksId(homeWorks1);
        course.setDepartmentName(department.getName());
        course.setExamTime(examTime);
        return course;
    }

}
