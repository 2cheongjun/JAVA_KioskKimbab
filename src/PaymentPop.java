import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class PaymentPop extends JFrame { // 결제 확인창 ( 결제금액확인, 요청사항 입력가능, 체크박스옵션이 있음)

    JPanel payPanel = new JPanel(new BorderLayout()); // 최상단 패널
    JPanel payInfoPanel = new JPanel(new BorderLayout());// 상단 영역 (결제 정보 - 결제,취소)
    JPanel ordertxtPanel = new JPanel(new BorderLayout());// 주문요구사항
    JPanel checkPanel = new JPanel(new BorderLayout()); // 체크박스 패널
    JPanel btnPanel = new JPanel(new BorderLayout());// 버튼영역 패널
    JLabel notice = new JLabel("");

    JButton payBtn, cancelBtn; // 결제,취소버튼
    JTextArea ordertxtInfo = new JTextArea(); // 주문요청사항 입력필드 // (JTextArea ordertxtArea)

    JCheckBox check = new JCheckBox("포장손님 일회용 수저 필요없어요 ",false);


    public PaymentPop() {
        super("주문확인"); // 팝업타이틀

        setLayout(new BorderLayout()); // 수직 배치박스 레이아웃 설정

        Box payBox = Box.createVerticalBox(); // 결제정보박스
        payBox.add(Box.createVerticalStrut(20));

        Box ordertxtBox = Box.createVerticalBox(); // 주문요구사항박스
        ordertxtBox.add(Box.createVerticalStrut(20));

        Box checkBox = Box.createVerticalBox(); // 체크박스박스
        checkBox.add(Box.createVerticalStrut(10));

        Box btnBox = Box.createVerticalBox(); // 버튼박스
        btnBox.add(Box.createVerticalStrut(20));



        payInfoPanel.setBorder(new TitledBorder(new EtchedBorder(), " 주문정보를 확인하세요 ")); // 패널 타이틀 및 테두리선
        payInfoPanel.add(payBox, BorderLayout.CENTER);

        payInfoPanel.add(KioskPanel.textField); // 결제 총금액 가져오기


        ordertxtInfo.setFont(new Font("NanumBarunpen", Font.PLAIN, 24)); // 요청사항 폰트 설정
        ordertxtInfo.setForeground(new Color(213, 100, 62));
        ordertxtInfo.setColumns(1);
        ordertxtInfo.setRows(3);
        ordertxtInfo.setLineWrap(true);
        ordertxtInfo.setWrapStyleWord(true);


        ordertxtPanel.add(ordertxtBox, BorderLayout.CENTER);  //중간추가 요구사항 기재
        ordertxtPanel.add(ordertxtInfo); // 주문요구사항 적는 영역
        ordertxtPanel.setFont(new Font("NanumBarunpen", Font.PLAIN, 46));
        ordertxtPanel.setForeground(Color.BLACK);

        Border border = BorderFactory.createTitledBorder("주문 요청사항을 적어주세요.");
        ordertxtPanel.setBorder(border);


        checkPanel.add(checkBox, BorderLayout.SOUTH);  // 체크박스
        check.addActionListener(new ActionListener() { // 체크박스 이벤트 - true값 PaymentPopFinal에 전달
            @Override
            public void actionPerformed(ActionEvent e) {
                 boolean isChecked = check.isSelected();  // isSeledted도 boolean값, isChecked ==true
            }
        });
        checkPanel.add(check);


        btnPanel.add(btnBox, BorderLayout.CENTER);// 취소하기 버튼 // 결제하기 버튼
        cancelBtn = new JButton("취소하기");
        cancelBtn.setSize(200, 60);
        cancelBtn.setLocation(100, 0);
        cancelBtn.setFont(new Font("NanumBarunpen", Font.PLAIN, 16));
        cancelBtn.setForeground(Color.BLACK);
        payBtn = new JButton("결제하기");
        payBtn.setSize(200, 60);
        payBtn.setLocation(300, 0);
        payBtn.setFont(new Font("NanumBarunpen", Font.PLAIN, 16));
        payBtn.setForeground(Color.BLACK);

        btnPanel.add(payBtn);
        btnPanel.add(cancelBtn);
        btnPanel.add(notice);

        payPanel.add(payInfoPanel);
        payPanel.add(ordertxtPanel);
        payPanel.add(checkPanel);
        payPanel.add(btnPanel);
        add(payPanel);

        Box center = Box.createVerticalBox(); // 패널 수직으로 배치
        center.add(payInfoPanel);
        center.add(ordertxtPanel);
        center.add(checkPanel);
        center.add(btnPanel);

        add(center, BorderLayout.CENTER);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(560, 300, 600, 500);
//        this.setSize(600, 400); // 키오스크 판넬 사이즈
//        this.pack(); // 팩메소드는 JFrame의 내용물에 맞게 윈도우 크기를 조절함
        this.setVisible(true);

//------------------------------------------------------------------------------------------------------------ 버튼 이벤트



        payBtn.addActionListener(new ActionListener() { // 결제버튼 이벤트
            public void actionPerformed(ActionEvent e) {

                new PaymentPopFinal(true, ordertxtInfo, check.isSelected()); // true일때 결제, txtArea 주문요청사항, 체크값전달
            }
        });

        cancelBtn.addActionListener(new ActionListener() { // false 취소, txtArea 주문요청사항
            public void actionPerformed(ActionEvent e) {

                new PaymentPopFinal(false, ordertxtInfo, check.isSelected());
            }
        });
    }

}



