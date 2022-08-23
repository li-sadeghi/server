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
    @Column
    private String homeWorkFileType;
    @Column(name = "homework_start")
    private String startTime;
    @Column(name = "homework_end")
    private String endTime;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "homeWork", cascade = {CascadeType.PERSIST, CascadeType.ALL})

    private List<Solution> solutions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "course_homework")
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getHomeWorkFileType() {
        return homeWorkFileType;
    }

    public void setHomeWorkFileType(String homeWorkFileType) {
        this.homeWorkFileType = homeWorkFileType;
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
        homeWork.setStartTime(startTime);
        homeWork.setEndTime(endTime);
        homeWork.setCourseId(course.getId());
        homeWork.setHomeWorkFileType(homeWorkFileType);
        return homeWork;
    }

}
