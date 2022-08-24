package load;

import save.Update;
import servermodels.department.Course;

import java.util.List;

public class CheckSelectionUnit {
    public static void checkCourses(){
        List<Course> allCourses= Load.fetchAll(Course.class);
        for (Course course : allCourses) {
            course.setHaveCwPage(true);
        }
        for (Course course : allCourses) {
            Update.update(course);
        }
    }
}
