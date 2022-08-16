package servermodels.users;

public enum EducationalStatus {
    STUDYING,
    GRADUATED;

    public sharedmodels.users.EducationalStatus toShared(){
        if (this == STUDYING)return sharedmodels.users.EducationalStatus.STUDYING;
        else return sharedmodels.users.EducationalStatus.GRADUATED;
    }
}
