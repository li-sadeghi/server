package servermodels.cw;
import servermodels.users.Student;
import sharedmodels.users.SharedStudent;

import javax.persistence.*;


@Entity
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private double mark;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.ALL})
    @JoinTable(name = "solution_student")
    private Student responsive;
    @Column
    private String answerFileString;

    public Solution() {
    }

    public Solution(Student responsive, String answerFileString) {
        this.responsive = responsive;
        this.answerFileString = answerFileString;
    }

    public Student getResponsive() {
        return responsive;
    }

    public void setResponsive(Student responsive) {
        this.responsive = responsive;
    }

    public String getAnswerFileString() {
        return answerFileString;
    }

    public void setAnswerFileString(String answerFileString) {
        this.answerFileString = answerFileString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }
    public sharedmodels.cw.Solution toShared(){
        sharedmodels.cw.Solution solution = new sharedmodels.cw.Solution();
        solution.setId(id);
        solution.setMark(mark);
        solution.setResponsiveId(responsive.getUsername());
        solution.setAnswerFileString(answerFileString);
        return solution;
    }
}
