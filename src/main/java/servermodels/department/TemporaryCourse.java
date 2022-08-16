package servermodels.department;

import servermodels.users.Master;
import servermodels.users.Student;
import sharedmodels.users.SharedMaster;
import sharedmodels.users.SharedStudent;

import javax.persistence.*;

@Entity
public class TemporaryCourse {
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
    @Column
    private String protestText;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Master master;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Student student;


    public TemporaryCourse() {
    }

    public TemporaryCourse(double mark) {
        this.mark = mark;
        if (mark >= 10) status = PassStatus.PASS;
        else if (mark >= 0) status = PassStatus.FAIL;
        else status = PassStatus.W;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
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

    public PassStatus getStatus() {
        return status;
    }

    public void setStatus(PassStatus status) {
        this.status = status;
    }

    public String getProtestText() {
        return protestText;
    }

    public void setProtestText(String protestText) {
        this.protestText = protestText;
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
    public sharedmodels.department.TemporaryCourse toShared(){
        sharedmodels.department.TemporaryCourse temporaryCourse = new sharedmodels.department.TemporaryCourse();
        temporaryCourse.setId(id);
        temporaryCourse.setMark(mark);
        temporaryCourse.setMaster((SharedMaster) master.toShared());
        temporaryCourse.setName(name);
        temporaryCourse.setStatus(status.toShared());
        temporaryCourse.setProtestText(protestText);
        temporaryCourse.setUnit(unit);
        temporaryCourse.setStudent((SharedStudent) student.toShared());
        return temporaryCourse;
    }
}
