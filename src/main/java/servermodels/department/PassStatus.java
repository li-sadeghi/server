package servermodels.department;

public enum PassStatus {
    PASS,
    FAIL,
    W;

    public sharedmodels.department.PassStatus toShared(){
        if (this == PASS)return sharedmodels.department.PassStatus.PASS;
        else if (this == FAIL)return sharedmodels.department.PassStatus.FAIL;
        else return sharedmodels.department.PassStatus.W;
    }
}
