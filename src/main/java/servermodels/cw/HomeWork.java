package servermodels.cw;


import servermodels.department.Course;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class HomeWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String homeWorkName;
    @Column
    private String homeworkFileString;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.ALL})
    @JoinTable(name = "homework_solution")
    private List<Solution> solutions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "homework_course")
    private Course course;

    public HomeWork() {
    }

    public HomeWork(String homeworkFileString) {
        this.homeworkFileString = homeworkFileString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHomeworkFileString() {
        return homeworkFileString;
    }

    public void setHomeworkFileString(String homeworkFileString) {
        this.homeworkFileString = homeworkFileString;
    }

    public List<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }

    public String getHomeWorkName() {
        return homeWorkName;
    }

    public void setHomeWorkName(String homeWorkName) {
        this.homeWorkName = homeWorkName;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public sharedmodels.cw.HomeWork toShared(){
        sharedmodels.cw.HomeWork homeWork = new sharedmodels.cw.HomeWork();
        homeWork.setHomeworkFileString(homeworkFileString);
        homeWork.setId(id);
        ArrayList<Integer> solutions1 = new ArrayList<>();
        for (Solution solution : solutions) {
            solutions1.add(solution.getId());
        }
        homeWork.setHomeWorkName(homeWorkName);
        homeWork.setSolutionsId(solutions1);
        return homeWork;
    }

}
