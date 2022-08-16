package servermodels.users;

public enum Licence {
    ILLEGAL,
    ALLOWED;

    public sharedmodels.users.Licence toShared(){
        if (this == ILLEGAL)return sharedmodels.users.Licence.ILLEGAL;
        else return sharedmodels.users.Licence.ALLOWED;
    }
}
