package practice.day7;

public enum pricePlan {
    LITE(10),
    BASIC(20),
    PREMIUM(30),
    FREEPASS(0);

    private int peopleNum;
    private int memberCnt; // 현재 회원수

    pricePlan(int peopleNum) {
        this.peopleNum = peopleNum;
        this.memberCnt = 0; // 초기화
    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public int getMemberCnt() {
        return memberCnt;
    }

    public void addCnt() {
        this.memberCnt++;
    }

    public boolean joinValidation() {
        return peopleNum == 0 || memberCnt < peopleNum;
    }
}
