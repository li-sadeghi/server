package servermodels.cw;

import javax.persistence.*;

@Entity
public class EducationalThing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String fileString;

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


    public sharedmodels.cw.EducationalThing toShared(){
        sharedmodels.cw.EducationalThing educationalThing = new sharedmodels.cw.EducationalThing();
        educationalThing.setId(id);
        educationalThing.setFileString(fileString);
        return educationalThing;
    }
}
