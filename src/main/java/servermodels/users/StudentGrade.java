package servermodels.users;

public enum StudentGrade {
    UNDERGRADUATE,
    GRADUATED,
    PHD;

    public sharedmodels.users.StudentGrade toShared(){
        if (this == UNDERGRADUATE) return sharedmodels.users.StudentGrade.UNDERGRADUATE;
        else if (this == GRADUATED) return sharedmodels.users.StudentGrade.GRADUATED;
        else return sharedmodels.users.StudentGrade.PHD;
    }
}
