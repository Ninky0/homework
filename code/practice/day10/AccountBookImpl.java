package practice.day10;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountBookImpl implements AccountBook {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    List<String> itemList = new ArrayList<>();

    private String date;
    private int menu;
    private int total;

    // 1. 바탕화면 경로 가져오기
    private String desktopPath;
    // 2. 폴더 경로 설정
    private String folderPath;
    // 폴더 생성
    private Path myFolder;
    private String today;
    private Path detailFile;


    public AccountBookImpl() {
        this.desktopPath = System.getProperty("user.home") + File.separator + "Desktop";
        this.folderPath = desktopPath + File.separator + "가계부";
        this.myFolder = Paths.get(folderPath);

        this.date = "";
        this.menu = 0;
        this.total = 0;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public void createFolder() {
        try {
            if ( Files.notExists(myFolder) ) {
                // 폴더 존재 X
                Files.createDirectory(myFolder);
                System.out.println("가계부 폴더가 생성되었습니다.");
            } else {
                System.out.println("가계부 폴더가 이미 생성되었습니다.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void printMenu() {
        System.out.println("수행할 업무를 선택해주세요");
        System.out.println("1.[내역추가] 2.[내역조회] 3.[삭제] 4.[프로그램 종료]");
        Scanner sc = new Scanner(System.in);
        setMenu(sc.nextInt());
    }

    @Override
    public void addItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("[가계부 내역 추가]");
        System.out.println("내역 추가 완료시, end 를 입력해주세요.");

        while (true) {
            System.out.println("물품 이름을 입력해주세요.");
            String name = scanner.nextLine();

            if (name.equals("end")) {
                break; // 'end'를 입력하면 while문 종료
            }

            System.out.println("물품 가격을 입력해주세요.");
            try {
                int price = Integer.parseInt(scanner.nextLine()); // 가격 입력 후 줄바꿈 문자 처리
                total += price;
                setTotal(total);
                addList(name, price);
            } catch (NumberFormatException e) {
                System.out.println("올바른 숫자를 입력해주세요.");
            }
        }

        System.out.println("합계: " + getTotal() +"원");
        addHistoryFile();
        setTotal(0);
    }

    @Override
    public void addList(String name, int price) {
        String item = (name + " : "+ price +"원");
        itemList.add(item);
    }

    @Override
    public void addHistoryFile() {
        today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        detailFile = myFolder.resolve(today + ".txt");

        // 파일 생성하고 내역을 저장
        if (Files.notExists(detailFile)) {
            try (FileOutputStream fos = new FileOutputStream(detailFile.toFile())) {
                StringBuilder content = new StringBuilder();
                for (String item : itemList) {
                    content.append(item).append("\n"); // 각 항목을 줄바꿈으로 구분
                }
                content.append("합계: ").append(getTotal()).append("원\n");

                fos.write(content.toString().getBytes());
                System.out.println(today + ".txt 파일을 생성하고 내용을 썼습니다.");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println(today + ".txt 파일이 이미 존재합니다.");
        }
    }

    @Override
    public void displayAll() {
        File dir = new File(myFolder.toString());
        String[] allFiles = dir.list();
        for (String fileName : allFiles) {
            System.out.println(fileName);
        }
    }

    @Override
    public void displayDetail() {
        if (Files.notExists(myFolder)) {
            System.out.println("내역이 없습니다.");
        } else {
            // 날짜 전체 출력
            displayAll();

            // 올바른 날짜형식 입력받기
            putRightDate();

            // 올바른 형식으로 입력된 날짜 가져오기
            String detailDate = getDate();

            detailFile = myFolder.resolve(detailDate + ".txt");

            if (Files.exists(detailFile)) {
                try (BufferedReader reader = new BufferedReader(new FileReader(detailFile.toFile()))) {
                    String line;
                    System.out.println("읽어오기 : " + detailDate + ".txt");

                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println(detailDate + ".txt 파일이 존재하지 않습니다.");
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
        if (Files.notExists(myFolder)) {
            System.out.println("내역이 없습니다.");
        } else {
            System.out.println("삭제하려는 날짜(제목)를 입력해주세요.");
            displayAll(); // 폴더 내의 파일 목록을 보여줌

            putRightDate(); // 사용자로부터 올바른 형식의 날짜 입력을 받음

            // 올바른 형식으로 입력된 날짜 가져오기
            String detailDate = getDate();

            // 해당 파일을 "가계부" 폴더에서 삭제
            detailFile = myFolder.resolve(detailDate + ".txt");


                if (Files.exists(detailFile)) {
                    try {
                        Files.delete(detailFile);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(detailDate + ".txt 파일을 삭제했습니다.");
                } else {
                    System.out.println(detailDate + ".txt 파일이 존재하지 않습니다.");
                }

        }
    }
}
