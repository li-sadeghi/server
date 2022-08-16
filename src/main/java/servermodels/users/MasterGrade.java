package servermodels.users;

public enum MasterGrade {
    ASSISTANT_PROFESSOR,
    ASSOCIATE_PROFESSOR,
    FULL_PROFESSOR;

    public sharedmodels.users.MasterGrade toShared(){
        if (this == ASSISTANT_PROFESSOR)return sharedmodels.users.MasterGrade.ASSISTANT_PROFESSOR;
        else if (this == ASSOCIATE_PROFESSOR) return sharedmodels.users.MasterGrade.ASSOCIATE_PROFESSOR;
        else return sharedmodels.users.MasterGrade.FULL_PROFESSOR;
    }
}
