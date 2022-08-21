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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String name;
    @Column
    private String chairmanId;
    @Column
    private String educationalAssistantId;
    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL})
    @JoinTable(name = "department_course")
    private List<Course> courses  = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL})
    @JoinTable(name = "department_master")
    private List<Master> masters  = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "department_student")
    private List<Student> students  = new ArrayList<>();

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

    public String getChairmanId() {
        return chairmanId;
    }

    public void setChairmanId(String chairmanId) {
        this.chairmanId = chairmanId;
    }

    public String getEducationalAssistantId() {
        return educationalAssistantId;
    }

    public void setEducationalAssistantId(String educationalAssistantId) {
        this.educationalAssistantId = educationalAssistantId;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Master> getMasters() {
        return masters;
    }

    public void setMasters(List<Master> masters) {
        this.masters = masters;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public sharedmodels.department.Department toShared(){
        sharedmodels.department.Department department = new sharedmodels.department.Department();
        department.setChairmanId(chairmanId);
        ArrayList<String> courses1 =new ArrayList<>();
        for (Course course : courses) {
            courses1.add(course.getId());
        }
        department.setCoursesId(courses1);
        department.setId(id);
        department.setName(name);
        ArrayList<String> masters1 = new ArrayList<>();
        for (Master master : masters) {
            masters1.add(master.getUsername());
        }
        department.setMastersId(masters1);
        department.setEducationalAssistantId(educationalAssistantId);
        ArrayList<String> students1 = new ArrayList<>();
        for (Student student : students) {
            students1.add( student.getUsername());
        }
        department.setStudentsId(students1);
        return department;
    }
}
