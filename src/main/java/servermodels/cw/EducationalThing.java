package servermodels.cw;

import servermodels.department.Course;

import javax.persistence.*;


@Entity
public class EducationalThing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;
    @Column
    private String fileString;
    @Column
    private String fileType;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "course_educational")
    private Course course;

    public EducationalThing() {
    }

    public EducationalThing(String fileString) {
        this.fileString = fileString;
    }

    public String getFileString() {
        return fileString;
    }

    public void setFileString(String fileString) {
        this.fileString = fileString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public sharedmodels.cw.EducationalThing toShared(){
        sharedmodels.cw.EducationalThing educationalThing = new sharedmodels.cw.EducationalThing();
        educationalThing.setId(id);
        educationalThing.setFileString(fileString);
        educationalThing.setName(name);
        educationalThing.setFileType(fileType);
        return educationalThing;
    }
}
