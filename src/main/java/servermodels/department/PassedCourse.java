package servermodels.department;

import servermodels.users.Master;
import servermodels.users.Student;
import sharedmodels.users.SharedStudent;

import javax.persistence.*;

@Entity
public class PassedCourse {
    @Id
    private String id;
    @Column
    private double mark;
    @Column
    private int unit;
    @Column
    private String name;
    @Column
    @Enumerated(EnumType.STRING)
    private PassStatus status;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Master master;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Student student;


    public PassedCourse() {
    }

    public PassedCourse(double mark) {
        this.mark = mark;
        if (mark >= 10) status = PassStatus.PASS;
        else if (mark >= 0) status = PassStatus.FAIL;
        else status = PassStatus.W;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public PassStatus getStatus() {
        return status;
    }

    public void setStatus(PassStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public sharedmodels.department.PassedCourse toShared(){
        sharedmodels.department.PassedCourse passedCourse = new sharedmodels.department.PassedCourse();
        passedCourse.setId(id);
        passedCourse.setMark(mark);
        passedCourse.setMaster((SharedStudent) master.toShared());
        passedCourse.setName(name);
        passedCourse.setStatus(status.toShared());
        passedCourse.setStudent((SharedStudent) student.toShared());
        passedCourse.setUnit(unit);
        return passedCourse;
    }
}
