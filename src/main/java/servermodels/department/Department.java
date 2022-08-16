package servermodels.department;

import servermodels.users.Master;
import servermodels.users.Student;
import sharedmodels.users.SharedMaster;
import sharedmodels.users.SharedStudent;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {
    @Id
    private String id;
    @Column
    private String name;
    @OneToOne
    @JoinTable(name = "chairman-department")
    private Master chairman;
    @OneToOne
    @JoinTable(name = "eduAs-department")
    private Master educationalAssistant;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Course> courses = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Master> masters = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Student> students = new ArrayList<>();

    public Department() {
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

    public Master getChairman() {
        return chairman;
    }

    public void setChairman(Master chairman) {
        this.chairman = chairman;
    }

    public Master getEducationalAssistant() {
        return educationalAssistant;
    }

    public void setEducationalAssistant(Master educationalAssistant) {
        this.educationalAssistant = educationalAssistant;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public List<Master> getMasters() {
        return masters;
    }

    public void setMasters(ArrayList<Master> masters) {
        this.masters = masters;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public sharedmodels.department.Department toShared(){
        sharedmodels.department.Department department = new sharedmodels.department.Department();
        department.setChairman((SharedMaster) chairman.toShared());
        ArrayList<sharedmodels.department.Course> courses1 =new ArrayList<>();
        for (Course course : courses) {
            courses1.add(course.toShared());
        }
        department.setCourses(courses1);
        department.setId(id);
        department.setName(name);
        ArrayList<SharedMaster> masters1 = new ArrayList<>();
        for (Master master : masters) {
            masters1.add((SharedMaster) master.toShared());
        }
        department.setMasters(masters1);
        department.setEducationalAssistant((SharedMaster) educationalAssistant.toShared());
        ArrayList<SharedStudent> students1 = new ArrayList<>();
        for (Student student : students) {
            students1.add((SharedStudent) student.toShared());
        }
        department.setStudents(students1);
        return department;
    }
}
