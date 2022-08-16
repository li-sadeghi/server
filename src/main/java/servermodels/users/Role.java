package servermodels.users;

public enum Role {
    STUDENT,
    MASTER,
    EDU_ADMIN,
    MR_MOHSENI;

    public sharedmodels.users.Role toShared(){
        if (this == STUDENT)return sharedmodels.users.Role.STUDENT;
        else if (this == MASTER)return sharedmodels.users.Role.MASTER;
        else if (this == EDU_ADMIN)return sharedmodels.users.Role.EDU_ADMIN;
        else return sharedmodels.users.Role.MR_MOHSENI;
    }
}
