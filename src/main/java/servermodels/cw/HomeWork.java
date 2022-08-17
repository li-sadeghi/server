package servermodels.cw;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class HomeWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String homeworkFileString;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Solution> solutions;

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

    public void setSolutions(ArrayList<Solution> solutions) {
        this.solutions = solutions;
    }

    public sharedmodels.cw.HomeWork toShared(){
        sharedmodels.cw.HomeWork homeWork = new sharedmodels.cw.HomeWork();
        homeWork.setHomeworkFileString(homeworkFileString);
        homeWork.setId(id);
        ArrayList<sharedmodels.cw.Solution> solutions1 = new ArrayList<>();
        for (Solution solution : solutions) {
            solutions1.add(solution.toShared());
        }
        homeWork.setSolutions(solutions1);
        return homeWork;
    }

}
