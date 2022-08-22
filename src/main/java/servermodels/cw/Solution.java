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
    @Column
    private String time;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "solution_student")
    private Student responsive;
    @Column
    private String answerFileString;
    @Column
    private String answerFileType;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "sol_homework")
    private HomeWork homeWork;

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

    public HomeWork getHomeWork() {
        return homeWork;
    }

    public void setHomeWork(HomeWork homeWork) {
        this.homeWork = homeWork;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAnswerFileType() {
        return answerFileType;
    }

    public void setAnswerFileType(String answerFileType) {
        this.answerFileType = answerFileType;
    }

    public sharedmodels.cw.Solution toShared(){
        sharedmodels.cw.Solution solution = new sharedmodels.cw.Solution();
        solution.setId(id);
        solution.setMark(mark);
        solution.setTime(time);
        solution.setAnswerFileType(answerFileType);
        solution.setResponsiveName(responsive.getFullName());
        solution.setResponsiveId(responsive.getUsername());
        solution.setAnswerFileString(answerFileString);
        return solution;
    }
}
