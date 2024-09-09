package practice.basic.day9;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class accountBookImpl implements accountBook {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private String date;
    private int menu;

    private Map<String, List<String[]>> bookHistory;
    private Map<String, Integer> moneyHistory;

    public accountBookImpl() {
        this.bookHistory = new HashMap<>();
        this.moneyHistory = new HashMap<>();
        this.date = "";
        this.menu = 0;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMenu() {
        return menu;
    }

    public void setMenu(int menu) {
        this.menu = menu;
    }


    @Override
    public void printMenu() {
        System.out.println("수행할 업무를 선택해주세요");
        System.out.println("1.[내역추가] 2.[내역조회] 3.[전체 삭제] 4.[내역삭제] 5.[프로그램 종료]");
        Scanner sc = new Scanner(System.in);
        setMenu(sc.nextInt());
    }

    @Override
    public void addItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("[가계부 내역 추가]");
        System.out.println("물품 이름을 입력해주세요.");
        String name = scanner.nextLine();
        System.out.println("물품 가격을 입력해주세요.");
        int price = scanner.nextInt();

        addHistory(name, price);
    }

    @Override
    public void addHistory(String name, int price) {
        String[] item = new String[2];
        item[0] = name;
        item[1] = String.valueOf(price);

        String formattedDate = LocalDateTime.now().format(formatter);

        // 해당 날짜에 이미 내역이 있는지 확인
        if (bookHistory.containsKey(formattedDate)) {
            // 내역이 있으면 리스트에 추가
            bookHistory.get(formattedDate).add(item);
            moneyHistory.put(formattedDate, moneyHistory.get(formattedDate) + price);
        } else {
            // 내역이 없으면 새로운 리스트 생성 후 추가
            List<String[]> itemList = new ArrayList<>();
            itemList.add(item);
            bookHistory.put(formattedDate, itemList);
            moneyHistory.put(formattedDate, price);
        }

        System.out.println("합계 : " + moneyHistory.get(formattedDate) + "원");
    }

    @Override
    public void displayAll() {
        for (String key : bookHistory.keySet()) {
            System.out.println(key);
        }
    }

    @Override
    public void displayDetail() {
        if(bookHistory.isEmpty()){
            System.out.println("내역이 없습니다.");
        } else{
            // 날짜 전체 출력
            displayAll();

            // 올바른 날짜형식 입력받기
            putRightDate();

            // 올바른 형식으로 입력된 날짜 가져오기
            String detailDate = getDate();
            // 입력받은 날짜의 내역들(배열 리스트)
            List<String[]> details = bookHistory.get(detailDate);

            if (details != null && !details.isEmpty()) {
                System.out.println("해당 날짜의 내역:");

                // 리스트 내 내역 출력
                for (int i = 0; i < details.size(); i++) {
                    // 리스트 내 내역들의 항목들
                    String[] detail = details.get(i);
                    System.out.println((i + 1) + ". 물품 이름: " + detail[0] + ", 물품 가격: " + detail[1] + "원");
                }

                System.out.println("합계: "+ moneyHistory.get(detailDate));

            } else {
                System.out.println("해당 날짜의 내역이 없습니다.");
            }
        }
    }

    @Override
    public void putRightDate() {
        Scanner sc = new Scanner(System.in);
        date = sc.nextLine();

        // 범위 내의 값인지 판단하고, 아닐시 반복.
        try {
            LocalDate.parse(date, formatter);
            setDate(date);
        } catch (Exception e) {
            System.out.println("yyyy-MM-dd 형식으로 입력해주세요");
            putRightDate(); // 재귀 호출로 올바른 형식 입력까지 반복
        }
    }

    @Override
    public void deleteAll() {
        System.out.println("[전체삭제]");
        if(bookHistory.isEmpty()){
            System.out.println("내역이 없습니다.");
        } else{
            System.out.println("삭제하려는 날짜(제목)를 입력해주세요.");
            displayAll();

            putRightDate();

            bookHistory.remove(getDate());
            moneyHistory.remove(getDate());
        }
    }

    @Override
    public void deleteItem() {
        System.out.println("[내역삭제]");
        if (bookHistory.isEmpty()) {
            System.out.println("내역이 없습니다.");
        } else {
            System.out.println("삭제하려는 날짜(제목)를 입력해주세요.");

            displayDetail();

            // 올바른 형식으로 입력된 날짜 가져오기
            String detailDate = getDate();
            // 입력받은 날짜의 내역들(배열 리스트)
            List<String[]> details = bookHistory.get(detailDate);

            Scanner sc = new Scanner(System.in);
            System.out.println("삭제하려는 내역의 번호를 입력해주세요:");
            int itemNumber = sc.nextInt();

            // 번호가 유효한지 확인하고, 삭제 처리
            if (itemNumber > 0 && itemNumber <= details.size()) {
                String[] itemToRemove = details.get(itemNumber - 1);
                int itemPrice = Integer.parseInt(itemToRemove[1]);  // 삭제할 아이템의 가격

                // 내역 삭제
                details.remove(itemNumber - 1);
                System.out.println("선택한 내역이 삭제되었습니다.");

                // 해당 날짜의 총액에서 삭제한 물품 가격을 차감
                moneyHistory.put(detailDate, moneyHistory.get(detailDate) - itemPrice);

                // 총액이 갱신된 후, 출력
                System.out.println("해당 날짜의 총액이 " + moneyHistory.get(detailDate) + "원으로 갱신되었습니다.");
            } else {
                System.out.println("잘못된 번호를 입력하셨습니다.");
            }

            // 내역이 모두 삭제되면 해당 날짜의 기록도 삭제 처리
            if (details.isEmpty()) {
                bookHistory.remove(detailDate);
                moneyHistory.remove(detailDate);
                System.out.println("해당 날짜의 모든 내역이 삭제되어 기록이 삭제되었습니다.");
            }
        }
    }

}
