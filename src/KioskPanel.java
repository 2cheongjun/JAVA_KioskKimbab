import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class KioskPanel extends JPanel {  // 메인 KioskPanel에서는 메뉴선택,취소, 결제금액확인 가능 -> PaymentPop으로 연결됨



    static JButton[] tabBtn = new JButton[3];  // 최상단 카테고리 김밥/ 떡볶이분식 / 돈까스 (탭버튼 3개)
    String[] tabBtntext = new String[]{"김밥", "떡볶이/라면", "돈까스"};
    Color[] tabColor = {new Color(18, 26, 7), new Color(201, 59, 69), (new Color(213, 100, 62))}; // 탭텍스트 컬러값


    JButton[] kimbabBtns = new JButton[9]; // 김밥버튼 (총 9개) 이름, 이미지, 가격

    String[] kimbabList = new String[]{"천국김밥 [2000원]", "치즈김밥 [3000원]", "새우김밥 [2500원]",
                                        "돈까스김밥 [2500원]", "참치김밥 [3000원]", "멸치고추김밥 [3000원]",
                                        "스팸김밥 [3500원]", "진미채김밥 [3500원]", "날치알김밥 [3000원]"};
    ImageIcon[] normalIcon = {new ImageIcon("src/kimbab1.png"), new ImageIcon("src/kimbab1.png"), new ImageIcon("src/kimbab1.png"),
                              new ImageIcon("src/kimbab1.png"), new ImageIcon("src/kimbab1.png"), new ImageIcon("src/kimbab1.png"),
                              new ImageIcon("src/kimbab1.png"), new ImageIcon("src/kimbab1.png"), new ImageIcon("src/kimbab1.png")};
    int[] kimbabPrice = new int[]{2000, 3000, 2500, 2500, 3000, 3000, 3500, 3500, 3000};



    JButton[] tteokbokkiBtn = new JButton[4]; // 떡볶이/분식 버튼 (총 4개) 이름, 이미지, 가격
    String[] tteokbokkiList = new String[]{"떡볶이 [4000원]", "치즈 떡볶이 [5000원]", "라면 [3000원]", "짬뽕라면 [4000원]"};
    ImageIcon[] dduckIcon ={new ImageIcon("src/dduck1.png"), new ImageIcon("src/dduck2.png"),
                            new ImageIcon("src/ramen.png"), new ImageIcon("src/ramen2.png")};
    int[] tteokbokkiPrice = new int[]{4000, 5000, 3000, 4000};



    JButton[] porkcutletBtn = new JButton[6]; // 돈까스(총 6개) 이름, 이미지, 가격
    String[] porkcutletList = new String[]{"돈까스 [8000원]", "생선까스 [9000원]", "치즈까스 [10000원]",
                                            "히레까스 [9000원]", "치킨까스 [8000원]", "왕돈까스 [9000원]"};
    ImageIcon[] porkIcon ={new ImageIcon("src/don1.png"),new ImageIcon("src/don1.png"),new ImageIcon("src/don1.png"),
                           new ImageIcon("src/don1.png"),new ImageIcon("src/don1.png"),new ImageIcon("src/don1.png")};
    int[] porkcutletPrice = new int[]{8000, 9000, 10000, 9000, 8000, 9000};


    static JTextField textField = new JTextField(20); // 결제확인 표 높이

    String[] resultView = new String[]{"메뉴", "수량", "가격"};
    String[][] Data; // 수량,가격 표시
    int count = 1; // 수량은 1부터 시작

    DefaultTableModel model;
    JTable table;
    JScrollPane scrollPane;

    JButton[] selectBtn = new JButton[2]; // 전체취소, 결제버튼
    String[] SelectBtn = new String[]{"취소하기", "결제하기"};

    String btnName = ""; // KioskMain에서 가져오는 버튼text값

    public void setBtnName(String btnName) {
        this.btnName = btnName;
    }
    public String getBtnName() {
        return btnName;
    }

  //------------------------------------------------------------------------------------------------------Kiosk Panel시작

    public KioskPanel() { // 판넬 기본 틀 구성( 상단 카테고리 :김밥, 떡볶이/분식,돈까스
                            // 테이블 : 메뉴,수량,가격 출력
                            // 결제금액 : 총금액표시
                            // 선택버튼 : 선택취소,전체취소, 결제

    soundMenuselect menuselect = new soundMenuselect();// 메뉴를골라주세요효과음
    Thread menuselectsound = new Thread(menuselect);
    menuselectsound.start();


        this.setBackground(new Color(236, 232, 231));
        this.model = new DefaultTableModel(this.Data, this.resultView);// 열: Data 값  / 행 : 메뉴,수량,가격

        this.table = new JTable(this.model); // JTabel에 주문정보가 담긴다.
        //   this.scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        this.setLayout(null); // null값일 때 좌표로 위치와 사이즈 조정가능


//----------------------------------------------------------------------------------------------------------------------

        tabBtn tabBtn = new tabBtn();// 상단카테고리 : 김밥/ 떡볶이/분식 /돈까스 묶음
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

            this.kimbabBtns[i].addActionListener(e -> {

            soundSelect selectSound = new soundSelect(); // 선택효과음
            Thread btnSelect= new Thread(selectSound);
            btnSelect.start();

            DefaultTableModel model = (DefaultTableModel) KioskPanel.this.table.getModel();// 가로열에 메뉴명,수량,가격이 들어감
            model.addRow(new Object[]{KioskPanel.this.kimbabList[product], KioskPanel.this.count,KioskPanel.this.kimbabPrice[product]});


                int rowCont = KioskPanel.this.table.getRowCount(); // 열의 수   //하단 결제금액 확인창에 정보가 출력
                int sum = 0;

                for (int j = 0; j < rowCont; ++j) {
                    sum = sum + (Integer) KioskPanel.this.table.getValueAt(j, 2);
                }
                textField.setText(btnName + " / 수량 " + rowCont + " / 총 금액 " + sum + "원");
                selectBtn[1].setForeground(new Color (201, 59, 69)); // 결제완료텍스트 레드로 변경

            });

        }


        TteokbokkiMenuBtn dduck = new TteokbokkiMenuBtn();// 떡볶이 버튼 묶음
        dduck.setSize(800, 400);
        dduck.setLocation(50, 160);
        dduck.setBackground(new Color(236, 232, 231));
        this.add(dduck);
        dduck.setVisible(false);
        for (int i = 0; i < this.tteokbokkiBtn.length; ++i) { // 떡볶이 테이블에 입력되는 값
            int product = i;
            // 김밥 버튼을 클릭-> 테이블에 메뉴의 값을 가져온다.
            this.tteokbokkiBtn[i].addActionListener(e -> {
                soundSelect selectSound = new soundSelect();// 선택효과음
                Thread btnSelect= new Thread(selectSound);
                btnSelect.start();

                DefaultTableModel model = (DefaultTableModel) KioskPanel.this.table.getModel();// 가로열에 메뉴명,수량,가격이 들어감
                model.addRow(new Object[]{KioskPanel.this.tteokbokkiList[product], KioskPanel.this.count, KioskPanel.this.tteokbokkiPrice[product]});


                int rowCont = KioskPanel.this.table.getRowCount(); // 열의 수   //하단 결제금액 확인창에 정보가 출력
                int sum = 0;

                for (int j = 0; j < rowCont; ++j) {
                    sum = sum + (Integer) KioskPanel.this.table.getValueAt(j, 2);
                }
                textField.setText(btnName + " / 수량 " + rowCont + " / 총 금액 " + sum + "원");
                selectBtn[1].setForeground(new Color (201, 59, 69)); // 결제완료텍스트 레드로 변경

            });
        }

        porkcutletBtn porkcutlets = new porkcutletBtn();// 돈까스 버튼 묶음
        porkcutlets.setSize(800, 400);
        porkcutlets.setLocation(50, 160);
        porkcutlets.setBackground(new Color(236, 232, 231));
        this.add(porkcutlets);
        porkcutlets.setVisible(false);
        for (int i = 0; i < this.porkcutletBtn.length; ++i) { // 돈까스 테이블에 입력되는 값
            int product = i;
            this.porkcutletBtn[i].addActionListener(e -> { // 돈까스 버튼을 클릭-> 테이블에 메뉴의 값을 가져온다.
                soundSelect selectSound = new soundSelect();// 선택효과음
                Thread btnSelect= new Thread(selectSound);
                btnSelect.start();

                DefaultTableModel model = (DefaultTableModel) KioskPanel.this.table.getModel();// 가로열에 메뉴명,수량,가격이 들어감
                model.addRow(new Object[]{KioskPanel.this.porkcutletList[product], KioskPanel.this.count, KioskPanel.this.porkcutletPrice[product]});


                int rowCont = KioskPanel.this.table.getRowCount(); // 열의 수   //하단 결제금액 확인창에 정보가 출력
                int sum = 0;

                for (int j = 0; j < rowCont; ++j) {
                    sum = sum + (Integer) KioskPanel.this.table.getValueAt(j, 2);
                }
                textField.setText(btnName + " / 수량 " + rowCont + " / 총 금액 " + sum + "원");

                selectBtn[1].setForeground(new Color (201, 59, 69)); // 결제완료텍스트 레드로 변경
            });
        }


        Screen screen = new Screen(); // 메뉴정보가 담기는 하단 테이블
        screen.setSize(800, 200);
        screen.setLocation(50, 580);
        screen.setBackground(new Color(236, 232, 231));
        this.add(screen);



        textField.setFont(new Font("NanumBarunpen", Font.BOLD, 20));  // 총 결제금액 박스
        textField.setSize(800, 70);
        textField.setLocation(50, 780);
        textField.setText(" 총 결제 금액 : ");
        textField.setFont(new Font("NanumBarunpen", Font.BOLD, 20));
        textField.setForeground(new Color(213, 100, 62));
        textField.setBackground(Color.WHITE);
        textField.setEnabled(false); // 수정불가한 상태로 설정
        this.add(textField);


        JLabel checkNotice = new JLabel("※ 메뉴를 선택 후 결제버튼을 눌러주세요."); // 좌측 하단 주의사항 문구
        checkNotice.setSize(400, 60);
        checkNotice.setForeground(new Color (201, 59, 69));
        checkNotice.setFont(new Font("NanumBarunpen", Font.PLAIN, 16));
        checkNotice.setLocation(50, 870);
        this.add(checkNotice);


        selectPaymentBtn selectPayment = new selectPaymentBtn(); // 하단선택버튼묵음 :선택취소, 전체취소, 결제 버튼
        selectPayment.setSize(400, 60);
        selectPayment.setLocation(450, 870);
        selectPayment.setBackground(new Color(236, 232, 231));
        this.add(selectPayment);


//---------------------------------------------------------------------------------------------------------------------- 탭 레이블 텍스트 속성 // 알림문구

        JLabel kimbabNotice = new JLabel("김밥을 골라주세요."); // 상단 김밥탭 버튼 클릭시 뜨는 문구
        kimbabNotice.setSize(400, 60);
        kimbabNotice.setForeground(Color.BLACK);
        kimbabNotice.setFont(new Font("NanumBarunpen", Font.PLAIN, 16));
        kimbabNotice.setLocation(60, 110);
        kimbabNotice.setVisible(true);
        this.add(kimbabNotice);

        JLabel  dduckNotice = new JLabel("떡볶이/라면을 골라주세요."); // 상단 떡복이라면탭 버튼 클릭시 뜨는 문구
        dduckNotice.setSize(400, 60);
        dduckNotice.setForeground(Color.BLACK);
        dduckNotice.setFont(new Font("NanumBarunpen", Font.PLAIN, 16));
        dduckNotice.setLocation(60, 110);
        dduckNotice.setVisible(false);
        this.add(dduckNotice);

        JLabel porkcutletsNotice = new JLabel("돈까스를 골라주세요."); // 상단 돈까스탭 버튼 클릭시 뜨는 문구
        porkcutletsNotice.setSize(400, 60);
        porkcutletsNotice.setForeground(Color.BLACK);
        porkcutletsNotice.setFont(new Font("NanumBarunpen", Font.PLAIN, 16));
        porkcutletsNotice.setLocation(60, 110);
        porkcutletsNotice.setVisible(false);
        this.add(porkcutletsNotice);

//---------------------------------------------------------------------------------------------------------------------- 선택취소,전체취소,총금액,결제버튼 이벤트


        // 전체취소 버튼 이벤트
        this.selectBtn[0].addActionListener(e -> {
            JButton button = (JButton) e.getSource();
            DefaultTableModel model = (DefaultTableModel) KioskPanel.this.table.getModel();
            model.setRowCount(0); // 행수를 0으로 설정
            textField.setText(""); // 기존에 있는 내용을 지우고 새로 세팅
            textField.setText("메뉴를 선택해주세요.");
            textField.setFont(new Font("NanumBarunpen", Font.BOLD, 20));
        });

        // 결제완료 버튼 이벤트
        this.selectBtn[1].addActionListener(e -> {  // 총금액을 확인하고, 결제하기를 누르면 로딩팝업이 잠시 뜬후 결제확인창이 뜸

            new PaymentPop();


        });

//---------------------------------------------------------------------------------------------------------------------- 김밥,떡볶이,돈까스 이벤트

        // 김밥카테고리 이벤트 // 첫화면에는 김밥 카테고리가 보임
        KioskPanel.tabBtn[0].addActionListener(e -> {
            JButton button = (JButton) e.getSource();
            String str = button.getText();

            kimbabs.setVisible(true);
            dduck.setVisible(false);
            porkcutlets.setVisible(false);
            kimbabNotice.setVisible(true);
            dduckNotice.setVisible(false);
            porkcutletsNotice.setVisible(false);
            setBackground(new Color(236, 232, 231));

        });

        // 떡볶이/분식 카테고리 이벤트
        KioskPanel.tabBtn[1].addActionListener(e -> {
            JButton button = (JButton) e.getSource();
            String str = button.getText();

            kimbabs.setVisible(false);
            dduck.setVisible(true);
            porkcutlets.setVisible(false);
            kimbabNotice.setVisible(false);
            dduckNotice.setVisible(true);
            porkcutletsNotice.setVisible(false);
            setBackground(new Color(236, 232, 231));
        });

        KioskPanel.tabBtn[2].addActionListener(new ActionListener() { // 돈까스 카테고리 이벤트
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                String str = button.getText();

                if (str.equals("돈까스")) {
                    setBackground(Color.white);
                }
                kimbabs.setVisible(false);
                dduck.setVisible(false);
                porkcutlets.setVisible(true);
                kimbabNotice.setVisible(false);
                dduckNotice.setVisible(false);
                porkcutletsNotice.setVisible(true);
                setBackground(new Color(236, 232, 231));
            }
        });

    }


    //------------------------------------------------------------------------------------------------------------------ 정렬, 텍스트, 컬러 속성
    class tabBtn extends JPanel {  // 상단 카테고리 탭 버튼 정렬
        tabBtn() {
            this.setBackground(new Color(236, 232, 231));
            this.setLayout(new GridLayout(1, 3, 4, 4));

            for (int i = 0; i < tabBtn.length; ++i) { // 탭텍스트 + 이미지 아이콘
                tabBtn[i] = new JButton(KioskPanel.this.tabBtntext[i]);
                tabBtn[i].setForeground(tabColor[i]);
                tabBtn[i].setFocusable(false);
                tabBtn[i].setFont(new Font("NanumBarunpen", Font.BOLD, 16));

                this.add(tabBtn[i]);
            }
        }
    }


    class KimbabMenuBtn extends JPanel { // 김밥 버튼 정렬 및 텍스트 컬러
        KimbabMenuBtn() {
            this.setLayout(new GridLayout(3, 3, 4, 4));
            this.setBackground(new Color(246, 244, 241));
            this.setVisible(true); // 초기값으로 김밥들이 노출


            for (int i = 0; i < KioskPanel.this.kimbabBtns.length; ++i) {
                KioskPanel.this.kimbabBtns[i] = new JButton(KioskPanel.this.kimbabList[i],KioskPanel.this.normalIcon[i]); // 김밥이름 순서대로 넣기
                KioskPanel.this.kimbabBtns[i].setIconTextGap(10); // 버튼과 텍스트사이 간격
                KioskPanel.this.kimbabBtns[i].setForeground(new Color(18, 26, 7)); // 컬러값 순서대로 넣기
                KioskPanel.this.kimbabBtns[i].setFocusable(false);
                KioskPanel.this.kimbabBtns[i].setFont(new Font("NanumBarunpen", Font.BOLD, 18));

                this.add(KioskPanel.this.kimbabBtns[i]);
            }
        }
    }


    class TteokbokkiMenuBtn extends JPanel { // 떡볶이 버튼 정렬 및 텍스트 컬러
        TteokbokkiMenuBtn() {
            this.setLayout(new GridLayout(2, 2, 4, 4));
            this.setBackground(new Color(246, 244, 241));


            for (int i = 0; i < KioskPanel.this.tteokbokkiBtn.length; ++i) {
                KioskPanel.this.tteokbokkiBtn[i] = new JButton(KioskPanel.this.tteokbokkiList[i],KioskPanel.this.dduckIcon[i]);  // 떡볶이이름 순서대로 넣기
                KioskPanel.this.tteokbokkiBtn[i].setIconTextGap(10); // 버튼과 텍스트사이 간격
                KioskPanel.this.tteokbokkiBtn[i].setForeground(new Color(201, 59, 69));    // 컬러값 순서대로 넣기
                KioskPanel.this.tteokbokkiBtn[i].setFocusable(false);
                KioskPanel.this.tteokbokkiBtn[i].setFont(new Font("NanumBarunpen", Font.BOLD, 18));

                this.add(KioskPanel.this.tteokbokkiBtn[i]);
            }
        }
    }

    class porkcutletBtn extends JPanel { // 돈까스 버튼 정렬 및 텍스트 컬러
        porkcutletBtn() {
            this.setLayout(new GridLayout(3, 2, 4, 4));
            this.setBackground(new Color(246, 244, 241));


            for (int i = 0; i < KioskPanel.this.porkcutletBtn.length; ++i) {
                KioskPanel.this.porkcutletBtn[i] = new JButton(KioskPanel.this.porkcutletList[i],KioskPanel.this.porkIcon[i]); // 돈까스 이름 순서대로 넣기
                KioskPanel.this.porkcutletBtn[i].setIconTextGap(10); // 버튼과 텍스트사이 간격
                KioskPanel.this.porkcutletBtn[i].setForeground(new Color(213, 100, 62));  // 컬러값 순서대로 넣기
                KioskPanel.this.porkcutletBtn[i].setFocusable(false);
                KioskPanel.this.porkcutletBtn[i].setFont(new Font("NanumBarunpen", Font.BOLD, 18));

                this.add(KioskPanel.this.porkcutletBtn[i]);
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

            Dimension width = new Dimension(776, 160); // 테이블 가로 사이즈 조정
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
            this.setLayout(new GridLayout(1, 2, 3, 3));

            for (int i = 0; i < KioskPanel.this.SelectBtn.length; ++i) {
                KioskPanel.this.selectBtn[i] = new JButton(KioskPanel.this.
                        SelectBtn[i]);
                KioskPanel.this.selectBtn[i].setForeground(Color.BLACK);
                KioskPanel.this.selectBtn[i].setFocusable(false);
                KioskPanel.this.selectBtn[i].setFont(new Font("NanumBarunpen", Font.BOLD, 24));

                this.add(KioskPanel.this.selectBtn[i]);
            }
        }
    }
}



