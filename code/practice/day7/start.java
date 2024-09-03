package practice.day7;

public class start {

    public static void main(String[] args) {
        managementImpl mg = new managementImpl();

        mg.printPlan();

        int plan = mg.getPlan();

        while (true) {
            mg.printMenu();

            switch (mg.getChoice()) {
                case 1:
                    mg.addMember();
                    break;
                case 2:
                    mg.findMemberByEmail();
                    break;
                case 3:
                    mg.findMemberByName();
                    break;
                case 4:
                    mg.printAll();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    System.out.println("이용해주셔서 감사합니다.");
                    return;
                default:
                    System.out.println("잘못 누르셨습니다.");
            }
        }
    }
}
