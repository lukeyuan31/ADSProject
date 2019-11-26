class building{
    private int buildingNum;
    private int executed_time;
    private int total_time;

    public building(int buildingNum,int total_time){
        this.buildingNum=buildingNum;
        this.total_time=total_time;
    }

    public int getBuildingNum() {
        return buildingNum;
    }

    public int getExecuted_time() {
        return executed_time;
    }

    public int getTotal_time() {
        return total_time;
    }

    public void setBuildingNum(int buildingNum) {
        this.buildingNum = buildingNum;
    }

    public void setExecuted_time(int executed_time) {
        this.executed_time = executed_time;
    }

    public void setTotal_time(int total_time) {
        this.total_time = total_time;
    }
}
