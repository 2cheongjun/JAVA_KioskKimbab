import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class KioskPanel extends JPanel {  // 메인 KioskPanel에서는 메뉴선택,취소, 결제금액확인 가능 -> PaymentPop으로 연결됨


    // 최상단 카테고리 김밥/ 떡볶이분식 / 돈까스 / 음료(탭버튼 4개)
    static JButton[] tabBtn = new JButton[4];
    String[] tabBtntext = new String[]{"김밥", "떡볶이/라면", "돈까스", "음료수"};
    Color[] tabColor = {new Color(18, 26, 7), new Color(201, 59, 69), new Color(213, 100, 62), (new Color(76, 157, 157))}; // 탭텍스트 컬러값


    // 김밥버튼 (총 9개) 이름, 이미지, 가격
    JButton[] kimbabBtns = new JButton[9];

    String[] kimbabList = new String[]{"천국김밥 [2000원]", "치즈김밥 [3000원]", "새우김밥 [2500원]",
            "돈까스김밥 [2500원]", "참치김밥 [3000원]", "멸추김밥 [3000원]",
            "스팸김밥 [3500원]", "진미채김밥 [3500원]", "날치알김밥 [3000원]"};
    ImageIcon[] normalIcon = {new ImageIcon("src/kimbab1.png"), new ImageIcon("src/kimbab1.png"), new ImageIcon("src/kimbab1.png"),
            new ImageIcon("src/kimbab1.png"), new ImageIcon("src/kimbab1.png"), new ImageIcon("src/kimbab1.png"),
            new ImageIcon("src/kimbab1.png"), new ImageIcon("src/kimbab1.png"), new ImageIcon("src/kimbab1.png")};
    int[] kimbabPrice = new int[]{2000, 3000, 2500, 2500, 3000, 3000, 3500, 3500, 3000};


    // 떡볶이/분식 버튼 (총 4개) 이름, 이미지, 가격
    JButton[] tteokbokkiBtn = new JButton[4];
    String[] tteokbokkiList = new String[]{"떡볶이 [4000원]", "치즈떡볶이 [5000원]", "라면 [3000원]", "짬뽕라면 [4000원]"};
    ImageIcon[] dduckIcon = {new ImageIcon("src/dduck1.png"), new ImageIcon("src/dduck2.png"),
            new ImageIcon("src/ramen.png"), new ImageIcon("src/ramen2.png")};
    int[] tteokbokkiPrice = new int[]{4000, 5000, 3000, 4000};


    // 돈까스(총 6개) 이름, 이미지, 가격
    JButton[] porkcutletBtn = new JButton[6];
    String[] porkcutletList = new String[]{"돈까스 [8000원]", "생선까스 [9000원]", "치즈까스 [10000원]",
            "히레까스 [9000원]", "치킨까스 [8000원]", "왕돈까스 [9000원]"};
    ImageIcon[] porkIcon = {new ImageIcon("src/don1.png"), new ImageIcon("src/don1.png"), new ImageIcon("src/don1.png"),
            new ImageIcon("src/don1.png"), new ImageIcon("src/don1.png"), new ImageIcon("src/don1.png")};
    int[] porkcutletPrice = new int[]{8000, 9000, 10000, 9000, 8000, 9000};


    // 음료수(총 4개) 이름, 이미지, 가격
    JButton[] drinkBtn = new JButton[4];
    String[] drinkBtnList = new String[]{"콜라[2000원]", "사이다[2000원]", "포카리[3000원]", "밀키스[2000원]"};
    ImageIcon[] drinkIcon = {new ImageIcon("src/coke.png"), new ImageIcon("src/sprite.png"), new ImageIcon("src/pocary.png"), new ImageIcon("src/soda.png")};
    int[] drinkPrice = new int[]{2000, 2000, 3000, 2000};


    // 결제확인 표 높이
    static JTextField textField = new JTextField(20);
    String[] resultView = new String[]{"메뉴", "수량", "가격"};
    String[][] Data; // 수량,가격 표시
    int count = 1; // 수량은 1부터 시작
    DefaultTableModel model;
    JTable table;
    JScrollPane scrollPane;

    // 처음으로(뒤로가기버튼), 전체취소,선택취소, 결제버튼
    JButton[] selectBtn = new JButton[4];
    String[] SelectBtn = new String[]{"< 처음으로", "전체취소", "선택취소", "결제"};
    // KioskMain에서 가져오는 버튼text값
    String btnName = "";

    public void setBtnName(String btnName) {
        this.btnName = btnName;
    }

    public String getBtnName() {
        return btnName;
    }

    //------------------------------------------------------------------------------------------------------Kiosk Panel시작

    public KioskPanel() { // 판넬 기본 틀 구성( 상단 카테고리 :김밥, 떡볶이/분식,돈까스
        // 테이블 :  메뉴,수량,가격 출력
        // 결제금액 : 총금액표시
        // 선택버튼 : 처음으로(뒤로가기버튼),선택취소,전체취소, 결제

        // 메뉴 선택해주세요 효과음 쓰레
        soundMenuselect menuSelect = new soundMenuselect();
        Thread menuSelectSound = new Thread(menuSelect);
        menuSelectSound.start();


        this.setBackground(new Color(236, 232, 231));
        this.model = new DefaultTableModel(this.Data, this.resultView);// 열: Data 값  / 행 : 메뉴,수량,가격

        this.table = new JTable(this.model); // JTabel에 주문정보가 담긴다.
        table.setFillsViewportHeight(true);

        this.setLayout(null); // null값일 때 좌표로 위치와 사이즈 조정가능


//----------------------------------------------------------------------------------------------------------------------

        tabBtn tabBtn = new tabBtn();// 상단카테고리 : 김밥/ 떡볶이/분식 /돈까스/ 음료 탭
        tabBtn.setSize(400, 60);
        tabBtn.setLocation(50, 50);
        tabBtn.setBackground(new Color(236, 232, 231));
        this.add(tabBtn);


        KimbabMenuBtn kimbabs = new KimbabMenuBtn();// 김밥버튼 묶음
        kimbabs.setSize(800, 400);
        kimbabs.setLocation(50, 160);
        kimbabs.setBackground(new Color(236, 232, 231));
        this.add(kimbabs);


        for (int i = 0; i < this.kimbabBtns.length; ++i) { // 김밥 테이블에 입력되는 값
            int product = i;
            int[] cliked = {0};
            this.kimbabBtns[i].addActionListener(e -> {

                soundSelect selectSound = new soundSelect(); // 선택효과음
                Thread btnSelect = new Thread(selectSound);
                btnSelect.start();

                DefaultTableModel model = (DefaultTableModel) KioskPanel.this.table.getModel();// 가로열에 메뉴명,수량,가격이 들어감
                model.addRow(new Object[]{KioskPanel.this.kimbabList[product], KioskPanel.this.count, KioskPanel.this.kimbabPrice[product]});

                // 결제금액확인창에 뜨는 내용
                int rowCont = KioskPanel.this.table.getRowCount();
                int sum = 0;
                for (int j = 0; j < rowCont; ++j) {
                    sum = sum + (Integer) KioskPanel.this.table.getValueAt(j, 2);
                }
                textField.setText(btnName + " / 수량 " + rowCont + " / 총 금액 " + sum + "원");
                // 상단의 메뉴버튼을 한번이라도 누르면, 결제완료텍스트 레드로 변경 됨
                selectBtn[3].setForeground(new Color(201, 59, 69));

                // 버튼클릭수 받아 베스트표기


                for (int k = 0; k < this.kimbabBtns.length; ++k) {
                    if ((JButton) e.getSource() == kimbabBtns[k]) {
                        System.out.println(cliked[0]);
                        cliked[0]++;
                        if (cliked[0] > 5) {
                            kimbabBtns[k].setText(kimbabList[product] + "★베스트");
                        }
                    }
                }
            });

        }

        // 떡볶이 버튼 묶음

        TteokbokkiMenuBtn dduck = new TteokbokkiMenuBtn();
        dduck.setSize(800, 400);
        dduck.setLocation(50, 160);
        dduck.setBackground(new Color(236, 232, 231));
        this.add(dduck);
        dduck.setVisible(false);
        for (int i = 0; i < this.tteokbokkiBtn.length; ++i) {
            int product = i;
            int[] cliked = {0};

            this.tteokbokkiBtn[i].addActionListener(e -> {
                // 선택효과음 쓰레드
                soundSelect selectSound = new soundSelect();
                Thread btnSelect = new Thread(selectSound);
                btnSelect.start();

                // 가로열에 메뉴명,수량,가격이 들어감
                DefaultTableModel model = (DefaultTableModel) KioskPanel.this.table.getModel();
                model.addRow(new Object[]{KioskPanel.this.tteokbokkiList[product], KioskPanel.this.count, KioskPanel.this.tteokbokkiPrice[product]});

                int rowCont = KioskPanel.this.table.getRowCount(); // 열의 수   //하단 결제금액 확인창에 정보가 출력
                int sum = 0;

                for (int j = 0; j < rowCont; ++j) {
                    sum = sum + (Integer) KioskPanel.this.table.getValueAt(j, 2);
                }
                textField.setText(btnName + " / 수량 " + rowCont + " / 총 금액 " + sum + "원");
                // 상단의 메뉴버튼을 한번이라도 누르면, 결제완료텍스트 레드로 변경 됨
                selectBtn[3].setForeground(new Color(201, 59, 69));



                // 버튼클릭수 받아 베스트표기
                for (int k = 0; k < this.tteokbokkiBtn.length; ++k) {
                    if ((JButton) e.getSource() == tteokbokkiBtn[k]) {
                        System.out.println(cliked[0]);
                        cliked[0]++;
                        if (cliked[0] > 5) {
                            tteokbokkiBtn[k].setText(tteokbokkiList[product] + "★베스트");
                        }
                    }
                }

            });
        }
        // 돈까스 버튼 묶음

        porkcutletBtn porkcutlets = new porkcutletBtn();
        porkcutlets.setSize(800, 400);
        porkcutlets.setLocation(50, 160);
        porkcutlets.setBackground(new Color(236, 232, 231));
        this.add(porkcutlets);
        porkcutlets.setVisible(false);
        for (int i = 0; i < this.porkcutletBtn.length; ++i) { // 돈까스 테이블에 입력되는 값

            int product = i;
            int[] cliked = {0};

            this.porkcutletBtn[i].addActionListener(e -> {
                // 선택효과음 쓰레드
                soundSelect selectSound = new soundSelect();
                Thread btnSelect = new Thread(selectSound);
                btnSelect.start();

                DefaultTableModel model = (DefaultTableModel) KioskPanel.this.table.getModel();
                model.addRow(new Object[]{KioskPanel.this.porkcutletList[product], KioskPanel.this.count, KioskPanel.this.porkcutletPrice[product]});


                int rowCont = KioskPanel.this.table.getRowCount(); // 열의 수   //하단 결제금액 확인창에 정보가 출력
                int sum = 0;

                for (int j = 0; j < rowCont; ++j) {
                    sum = sum + (Integer) KioskPanel.this.table.getValueAt(j, 2);
                }
                textField.setText(btnName + " / 수량 " + rowCont + " / 총 금액 " + sum + "원");
                // 상단의 메뉴버튼을 한번이라도 누르면, 결제완료텍스트 레드로 변경 됨
                selectBtn[3].setForeground(new Color(201, 59, 69));

                // 버튼클릭수 받아 베스트표기
                for (int k = 0; k < this.porkcutletBtn.length; ++k) {
                    if ((JButton) e.getSource() == porkcutletBtn[k]) {
                        System.out.println(cliked[0]);
                        cliked[0]++;
                        if (cliked[0] > 5) {
                            porkcutletBtn[k].setText(porkcutletList[product] + "★베스트");
                        }
                    }
                }
            });
        }

        drinkBtn drink = new drinkBtn();// 음료 버튼 묶음
        drink.setSize(800, 400);
        drink.setLocation(50, 160);
        drink.setBackground(new Color(236, 232, 231));
        this.add(drink);
        drink.setVisible(false);
        for (int i = 0; i < this.drinkBtn.length; ++i) { // 돈까스 테이블에 입력되는 값
            int product = i;
            int[] cliked = {0};

            this.drinkBtn[i].addActionListener(e -> { // 돈까스 버튼을 클릭-> 테이블에 메뉴의 값을 가져온다.
                soundSelect selectSound = new soundSelect();// 선택효과음
                Thread btnSelect = new Thread(selectSound);
                btnSelect.start();

                DefaultTableModel model = (DefaultTableModel) KioskPanel.this.table.getModel();// 가로열에 메뉴명,수량,가격이 들어감
                model.addRow(new Object[]{KioskPanel.this.drinkBtnList[product], KioskPanel.this.count, KioskPanel.this.drinkPrice[product]});

                int rowCont = KioskPanel.this.table.getRowCount(); // 열의 수   //하단 결제금액 확인창에 정보가 출력
                int sum = 0;

                for (int j = 0; j < rowCont; ++j) {
                    sum = sum + (Integer) KioskPanel.this.table.getValueAt(j, 2);
                }
                textField.setText(btnName + " / 수량 " + rowCont + " / 총 금액 " + sum + "원");
                // 상단의 메뉴버튼을 한번이라도 누르면, 결제완료텍스트 레드로 변경 됨
                selectBtn[3].setForeground(new Color(201, 59, 69));


                // 버튼클릭수 받아 베스트표기
                for (int k = 0; k < this.drinkBtn.length; ++k) {
                    if ((JButton) e.getSource() == drinkBtn[k]) {
                        System.out.println(cliked[0]);
                        cliked[0]++;
                        if (cliked[0] > 5) {
                            drinkBtn[k].setText(drinkBtnList[product] + "★베스트");
                        }
                    }
                }
            });
        }

        // 메뉴정보가 담기는 하단 테이블 ( 상단 메뉴들을 누르면 테이블에 정보가 담김, 테이블의 열을 선택해 선택취소가 가능)
        Screen screen = new Screen();
        screen.setSize(800, 200);
        screen.setLocation(50, 580);
        screen.setBackground(new Color(236, 232, 231));
        this.add(screen);


        // 총 결제금액 박스
        textField.setFont(new Font("NanumBarunpen", Font.BOLD, 20));
        textField.setSize(800, 70);
        textField.setLocation(50, 780);
        textField.setText(" 총 결제 금액 : ");
        textField.setFont(new Font("NanumBarunpen", Font.BOLD, 20));
        textField.setForeground(new Color(213, 100, 62));
        textField.setBackground(Color.WHITE);
        textField.setEnabled(false);
        this.add(textField);


        // 좌측 하단 주의사항 문구
        JLabel checkNotice = new JLabel("※ 메뉴를 선택 후 결제버튼을 눌러주세요.");
        checkNotice.setSize(400, 60);
        checkNotice.setForeground(new Color(201, 59, 69));
        checkNotice.setFont(new Font("NanumBarunpen", Font.PLAIN, 16));
        checkNotice.setLocation(50, 870);
        this.add(checkNotice);


        // 하단선택버튼묵음 : 이전버튼(키오스크첫화면으로 이동함), 선택취소, 전체취소, 결제 버튼
        selectPaymentBtn selectPayment = new selectPaymentBtn();
        selectPayment.setSize(500, 60);
        selectPayment.setLocation(350, 870);
        selectPayment.setBackground(new Color(236, 232, 231));
        this.add(selectPayment);


//-------------------------------------------------------------------------------------------- 탭 레이블 텍스트 속성 // 알림문구
        // 상단 김밥탭 버튼 클릭시 탭하단에 뜨는 문구
        JLabel kimbabNotice = new JLabel("김밥을 골라주세요.");
        kimbabNotice.setSize(400, 60);
        kimbabNotice.setForeground(Color.BLACK);
        kimbabNotice.setFont(new Font("NanumBarunpen", Font.PLAIN, 16));
        kimbabNotice.setLocation(60, 110);
        kimbabNotice.setVisible(true);
        this.add(kimbabNotice);
        // 상단 떡복이라면탭 버튼 클릭시 탭하단에 뜨는 문구
        JLabel dduckNotice = new JLabel("떡볶이/라면을 골라주세요.");
        dduckNotice.setSize(400, 60);
        dduckNotice.setForeground(Color.BLACK);
        dduckNotice.setFont(new Font("NanumBarunpen", Font.PLAIN, 16));
        dduckNotice.setLocation(60, 110);
        dduckNotice.setVisible(false);
        this.add(dduckNotice);
        // 상단 돈까스탭 버튼 클릭시 뜨는 문구
        JLabel porkcutletsNotice = new JLabel("돈까스를 골라주세요.");
        porkcutletsNotice.setSize(400, 60);
        porkcutletsNotice.setForeground(Color.BLACK);
        porkcutletsNotice.setFont(new Font("NanumBarunpen", Font.PLAIN, 16));
        porkcutletsNotice.setLocation(60, 110);
        porkcutletsNotice.setVisible(false);
        this.add(porkcutletsNotice);
        // 상단 음료 버튼 클릭시 뜨는 문구
        JLabel drinkNotice = new JLabel("음료를 골라주세요.");
        drinkNotice.setSize(400, 60);
        drinkNotice.setForeground(Color.BLACK);
        drinkNotice.setFont(new Font("NanumBarunpen", Font.PLAIN, 16));
        drinkNotice.setLocation(60, 110);
        drinkNotice.setVisible(false);
        this.add(drinkNotice);

//---------------------------------------------------------------------------------------------------------------------- 선택취소,전체취소,총금액,결제버튼 이벤트


        // 이전버튼 이벤트을 누르면 키오스크 첫화면으로 이동해서 포장, 매장을 다시 선택가능
        this.selectBtn[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new KioskMain();
            }
        });


        // 전체취소 버튼 이벤트 (선택한메뉴 전체삭제 기능)
        this.selectBtn[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) KioskPanel.this.table.getModel();
                model.setRowCount(0); // 행수를 0으로 설정
                textField.setText(""); // 기존에 있는 내용을 지우고 새로 세팅
                textField.setText("메뉴를 선택해주세요.");
                textField.setFont(new Font("NanumBarunpen", Font.BOLD, 20));
            }
        });


        // 선택취소 버튼 이벤트(선택한 행의 메뉴를 삭제할수 있음)
        this.selectBtn[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) KioskPanel.this.table.getModel(); //-열 ,-행인 테이블 만들기
                model.removeRow(KioskPanel.this.table.getSelectedRow());// 열을 삭제

                int rowCont = KioskPanel.this.table.getRowCount(); // 열의 수   //하단 결제금액 확인창에 정보가 출력
                int sum = 0;

                for (int j = 0; j < rowCont; ++j) {
                    sum = sum + (Integer) KioskPanel.this.table.getValueAt(j, 2);
                }
                textField.setText(btnName + " / 수량 " + rowCont + " / 총 금액 " + sum + "원");

            }
        });

        // 결제완료 버튼 이벤트
        // 총금액을 확인하고, 결제하기를 누르면 로딩팝업이 잠시 뜬후 결제확인창이 뜸
        this.selectBtn[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PaymentPop();

            }
        });

//---------------------------------------------------------------------------------------------------------------------- 김밥,떡볶이,돈까스 이벤트


        // 김밥카테고리 이벤트 // 첫화면에는 김밥 카테고리가 보임 // 선택되지 않은 탭의 메뉴 SetVisible false상태로 안보임
        KioskPanel.tabBtn[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                String str = button.getText();

                kimbabs.setVisible(true);
                kimbabNotice.setVisible(true);
                dduck.setVisible(false);
                dduckNotice.setVisible(false);
                porkcutlets.setVisible(false);
                porkcutletsNotice.setVisible(false);
                drink.setVisible(false);
                drinkNotice.setVisible(false);

                setBackground(new Color(236, 232, 231));
            }
        });


        // 떡볶이/분식 카테고리 이벤트
        KioskPanel.tabBtn[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                String str = button.getText();

                kimbabs.setVisible(false);
                kimbabNotice.setVisible(false);
                dduck.setVisible(true);
                dduckNotice.setVisible(true);
                porkcutlets.setVisible(false);
                porkcutletsNotice.setVisible(false);
                drink.setVisible(false);
                drinkNotice.setVisible(false);

                setBackground(new Color(236, 232, 231));
            }
        });

        // 돈까스 카테고리 이벤트
        KioskPanel.tabBtn[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                String str = button.getText();

                if (str.equals("돈까스")) {
                    setBackground(Color.white);
                }
                kimbabs.setVisible(false);
                dduck.setVisible(false);
                kimbabNotice.setVisible(false);
                dduckNotice.setVisible(false);
                porkcutlets.setVisible(true);
                porkcutletsNotice.setVisible(true);
                drink.setVisible(false);
                drinkNotice.setVisible(false);

                setBackground(new Color(236, 232, 231));
            }
        });

        // 음료 카테고리 이벤트
        KioskPanel.tabBtn[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                String str = button.getText();

                if (str.equals("음료수")) {
                    setBackground(Color.white);
                }
                kimbabs.setVisible(false);
                kimbabNotice.setVisible(false);
                dduck.setVisible(false);
                dduckNotice.setVisible(false);
                porkcutlets.setVisible(false);
                porkcutletsNotice.setVisible(false);
                drink.setVisible(true);
                drinkNotice.setVisible(true);
                setBackground(new Color(236, 232, 231));

            }
        });

    }



    //------------------------------------------------------------------------------------------------------------------ 정렬, 텍스트, 컬러 속성
    // 상단 카테고리 탭 버튼 정렬
    class tabBtn extends JPanel {
        tabBtn() {
            this.setBackground(new Color(236, 232, 231));
            this.setLayout(new GridLayout(1, 4, 4, 4));

            for (int i = 0; i < tabBtn.length; ++i) { // 탭텍스트
                tabBtn[i] = new JButton(KioskPanel.this.tabBtntext[i]);
                tabBtn[i].setForeground(tabColor[i]);
                tabBtn[i].setFocusable(false);
                tabBtn[i].setFont(new Font("NanumBarunpen", Font.BOLD, 16));

                this.add(tabBtn[i]);
            }
        }
    }

    // 김밥 버튼 정렬 및 텍스트 컬러
    class KimbabMenuBtn extends JPanel {
        KimbabMenuBtn() {
            this.setLayout(new GridLayout(3, 3, 4, 4));
            this.setBackground(new Color(246, 244, 241));
            // 초기값으로 김밥들이 노출
            this.setVisible(true);


            for (int i = 0; i < KioskPanel.this.kimbabBtns.length; ++i) {
                KioskPanel.this.kimbabBtns[i] = new JButton(KioskPanel.this.kimbabList[i], KioskPanel.this.normalIcon[i]);
                KioskPanel.this.kimbabBtns[i].setIconTextGap(10); // 버튼과 텍스트사이 간격
                KioskPanel.this.kimbabBtns[i].setForeground(new Color(18, 26, 7)); // 컬러값 순서대로 넣기
                KioskPanel.this.kimbabBtns[i].setFocusable(false);
                KioskPanel.this.kimbabBtns[i].setFont(new Font("NanumBarunpen", Font.BOLD, 18));

                this.add(KioskPanel.this.kimbabBtns[i]);
            }
        }
    }

    // 떡볶이 버튼 정렬 및 텍스트 컬러
    class TteokbokkiMenuBtn extends JPanel {
        TteokbokkiMenuBtn() {
            this.setLayout(new GridLayout(2, 2, 4, 4));
            this.setBackground(new Color(246, 244, 241));


            for (int i = 0; i < KioskPanel.this.tteokbokkiBtn.length; ++i) {
                KioskPanel.this.tteokbokkiBtn[i] = new JButton(KioskPanel.this.tteokbokkiList[i], KioskPanel.this.dduckIcon[i]);
                KioskPanel.this.tteokbokkiBtn[i].setIconTextGap(10); // 버튼과 텍스트사이 간격
                KioskPanel.this.tteokbokkiBtn[i].setForeground(new Color(201, 59, 69));    // 컬러값 순서대로 넣기
                KioskPanel.this.tteokbokkiBtn[i].setFocusable(false);
                KioskPanel.this.tteokbokkiBtn[i].setFont(new Font("NanumBarunpen", Font.BOLD, 18));

                this.add(KioskPanel.this.tteokbokkiBtn[i]);
            }
        }
    }

    // 돈까스 버튼 정렬 및 텍스트 컬러
    class porkcutletBtn extends JPanel {
        porkcutletBtn() {
            this.setLayout(new GridLayout(3, 2, 4, 4));
            this.setBackground(new Color(246, 244, 241));


            for (int i = 0; i < KioskPanel.this.porkcutletBtn.length; ++i) {
                KioskPanel.this.porkcutletBtn[i] = new JButton(KioskPanel.this.porkcutletList[i], KioskPanel.this.porkIcon[i]);
                KioskPanel.this.porkcutletBtn[i].setIconTextGap(10); // 버튼과 텍스트사이 간격
                KioskPanel.this.porkcutletBtn[i].setForeground(new Color(213, 100, 62));  // 컬러값 순서대로 넣기
                KioskPanel.this.porkcutletBtn[i].setFocusable(false);
                KioskPanel.this.porkcutletBtn[i].setFont(new Font("NanumBarunpen", Font.BOLD, 18));

                this.add(KioskPanel.this.porkcutletBtn[i]);
            }
        }
    }


    // 음료 버튼 정렬 및 텍스트 컬러
    class drinkBtn extends JPanel {
        drinkBtn() {
            this.setLayout(new GridLayout(2, 2, 4, 4));
            this.setBackground(new Color(246, 244, 241));


            // 음료 이름 순서대로 넣기
            for (int i = 0; i < KioskPanel.this.drinkBtn.length; ++i) {
                KioskPanel.this.drinkBtn[i] = new JButton(KioskPanel.this.drinkBtnList[i], KioskPanel.this.drinkIcon[i]);
                KioskPanel.this.drinkBtn[i].setIconTextGap(10); // 버튼과 텍스트사이 간격
                KioskPanel.this.drinkBtn[i].setForeground(new Color(76, 157, 157));
                KioskPanel.this.drinkBtn[i].setFocusable(false);
                KioskPanel.this.drinkBtn[i].setFont(new Font("NanumBarunpen", Font.BOLD, 18));

                this.add(KioskPanel.this.drinkBtn[i]);
            }
        }
    }

    class Screen extends JPanel { // 테이블 속성 정렬 및 텍스트 컬러
        Screen() {
            this.setBackground(new Color(246, 244, 241));
            DefaultTableModel model = (DefaultTableModel) KioskPanel.this.table.getModel();
            table.setFillsViewportHeight(true);
            KioskPanel.this.table.setBounds(50, 300, 800, 230);
            KioskPanel.this.table.setRowHeight(20); // 열 높이
            KioskPanel.this.table.getTableHeader().setFont(new Font("NanumBarunpen", Font.PLAIN, 12));
            KioskPanel.this.table.setFocusable(false); // 열 높이
            // 테이블 가로 사이즈 조정
            Dimension width = new Dimension(776, 160);
//            KioskPanel.this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            KioskPanel.this.table.setPreferredScrollableViewportSize(width);
            JScrollPane jp = new JScrollPane(KioskPanel.this.table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            jp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); // 스크롤 생성
            this.add(jp);
        }
    }


    class selectPaymentBtn extends JPanel { // 하단 선택버튼 정렬

        selectPaymentBtn() {
            this.setBackground(new Color(246, 244, 241));
            this.setLayout(new GridLayout(1, 4, 3, 3));

            for (int i = 0; i < KioskPanel.this.SelectBtn.length; ++i) {
                KioskPanel.this.selectBtn[i] = new JButton(KioskPanel.this.
                        SelectBtn[i]);
                KioskPanel.this.selectBtn[i].setForeground(Color.BLACK);
                KioskPanel.this.selectBtn[i].setFocusable(false);
                KioskPanel.this.selectBtn[i].setFont(new Font("NanumBarunpen", Font.BOLD, 18));

                this.add(KioskPanel.this.selectBtn[i]);
            }
        }
    }

}



