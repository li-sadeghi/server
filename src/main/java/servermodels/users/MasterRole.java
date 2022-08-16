package servermodels.users;

public enum MasterRole {
    MASTER,
    EDUCATIONAL_ASSISTANT,
    CHAIRMAN;

    public sharedmodels.users.MasterRole toShared(){
        if (this == MASTER)return sharedmodels.users.MasterRole.MASTER;
        else if (this == EDUCATIONAL_ASSISTANT) return sharedmodels.users.MasterRole.EDUCATIONAL_ASSISTANT;
        else return sharedmodels.users.MasterRole.CHAIRMAN;
    }
}
