import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;



public class PaymentPopFinal extends JFrame { // 마지막 결제완료, 취소 확인 창
    Panel popFinal = new Panel(new BorderLayout()); // 최상위 패널
    JPanel payinfoPanel = new JPanel(new BorderLayout());// 결제 정보 패널
    JPanel ordertxtPanel = new JPanel(new BorderLayout());// 주문요청정보 패널
    JPanel numberPanel = new JPanel(new BorderLayout()); // 주문번호 패널
    JPanel timePanel = new JPanel(new BorderLayout()); // 결제완료시간 패널
    JPanel btnPanel = new JPanel(new BorderLayout()); // 하단 확인버튼 패널
    JButton confirmBtn = new JButton("<HTML><body style='text-align:center;'><br>확인<br><br></body></HTML>");

    JLabel completeTxt = new JLabel(); // 결제완료문구

    JLabel orderNumber = new JLabel("224"); // 주문번호
    JLabel cancelTxt = new JLabel(); // 취소문구
    JLabel time = new JLabel(); // 현재시간표기


    public PaymentPopFinal(boolean btn, JTextArea ordertxtInfo, boolean isChecked) { // PaymentPop에서 결제버튼, 취소버튼 클릭시 출력되는 문구


        super("주문확인"); // 팝업타이틀


        LoadingPop loadingPop = new LoadingPop();// 결제완료창 효과음
        Thread progress = new Thread(loadingPop);
        progress.start();


        setBounds(560, 300, 600, 500);
        setLayout(new BorderLayout()); // 수직 배치박스 레이아웃 설정

        Box payinfo = Box.createVerticalBox(); // 결제완료
        payinfo.add(Box.createVerticalStrut(30));

        Box ordertxt = Box.createVerticalBox(); // 요청사항 박스
        ordertxt.add(Box.createVerticalStrut(30));

        Box numberBox = Box.createVerticalBox(); // 주문번호박스
        numberBox.add(Box.createVerticalStrut(30));

        Box timeBox = Box.createVerticalBox(); // 시간표기박스
        timeBox.add(Box.createVerticalStrut(10));

        Box btnBox = Box.createVerticalBox(); // 하단버튼박스
        btnBox.add(Box.createVerticalStrut(4));


        payinfoPanel.setBorder(
                new TitledBorder(new EtchedBorder(), " 결제정보 확인")); // 결제정보 속성 ,타이틀 및 테두리선
        payinfoPanel.add(payinfo, BorderLayout.CENTER);
        popFinal.add(payinfoPanel); // 패널 타이블 박스, 판넬에 붙이기


        ordertxtPanel.setBorder(
                new TitledBorder(new EtchedBorder(), " 주문요청사항")); // 주문요청사항속성, 패널 타이틀 및 테두리선
        ordertxtPanel.add(payinfo, BorderLayout.CENTER);
        popFinal.add(ordertxtPanel);


        numberPanel.setBorder(
                new TitledBorder(new EtchedBorder(), "주문번호"));  // 주문번호 속성,  패널 타이틀 및 테두리선
        numberPanel.add(numberBox, BorderLayout.CENTER);
        popFinal.add(numberPanel);


        timePanel.add(timeBox, BorderLayout.SOUTH); // 하단 시간박스
        SimpleDateFormat simpleDate = new SimpleDateFormat("결제완료 : yyyy-MM-dd / HH시 mm분 "); // 결제시간 표기
        String timeTxt = simpleDate.format(System.currentTimeMillis());
        time.setText(timeTxt);
        time.setForeground(Color.BLACK);
        timePanel.add(time);
        popFinal.add(timePanel);


        btnPanel.add(btnBox, BorderLayout.SOUTH); // 하단 확인버튼
        btnBox.setForeground(Color.BLACK);
        btnPanel.add(confirmBtn);
        popFinal.add(btnPanel);


        confirmBtn.setFocusable(false); //  버튼 속성,이벤트
        confirmBtn.setFont(new Font("NanumBarunpen", Font.PLAIN, 16));
        confirmBtn.setSize(200, 60);
        confirmBtn.setLocation(100, 50);
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // 확인버튼 눌렀을때 창을 종료함
                System.exit(0);
            }
        });
        add(popFinal); //결제판넬에 붙임


        Box center = Box.createHorizontalBox(); // 박스 수평배치
        center.add(payinfoPanel);
        center.add(ordertxtPanel);
        add(center, BorderLayout.CENTER);

        Box vertical = Box.createVerticalBox(); // 박스 수직배치
        vertical.add(numberPanel);
        vertical.add(timePanel);
        vertical.add(btnPanel);
        add(vertical, BorderLayout.SOUTH);


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

//        this.pack(); // 팩메소드는 JFrame의 내용물에 맞게 윈도우 크기를 조절함
        this.setVisible(true);

//----------------------------------------------------------------------------------------------------------------------


        if (btn) { // 결제시 출력되는 문구

            if (isChecked) {
                ordertxtInfo.setFont(new Font("NanumBarunpen", Font.PLAIN, 16)); // PaymetPop에서 넘겨 받은 요청사항 텍스트 크기 재설정
                ordertxtInfo.setForeground(new Color(213, 100, 62));
                ordertxtInfo.setColumns(1);
                ordertxtInfo.setRows(3);
                ordertxtInfo.setLineWrap(true);
                ordertxtInfo.setWrapStyleWord(true);
                completeTxt.setText("<HTML><body>결제완료!<br><br>메뉴가 준비되면 호출모니터로 안내해드립니다.</body></HTML>");
                ordertxtInfo.append("(일회용 수저 필요없음 체크)"); // 결제창 체크박스 체크시 + (일회용 수저 필요없음 체크)더해 출력
            }
            else {
                ordertxtInfo.setFont(new Font("NanumBarunpen", Font.PLAIN, 16)); // PaymetPop에서 넘겨 받은 요청사항 텍스트 크기 재설정
                ordertxtInfo.setForeground(new Color(213, 100, 62));
                ordertxtInfo.setColumns(1);
                ordertxtInfo.setRows(3);
                ordertxtInfo.setLineWrap(true);
                ordertxtInfo.setWrapStyleWord(true);
                completeTxt.setText("<HTML><body>결제완료!<br><br>메뉴가 준비되면 호출모니터로 안내해드립니다.</body></HTML>");

            }

            soundSuccess sucessbtn = new soundSuccess();// 결제완료창 효과음
            Thread sucesssound = new Thread(sucessbtn);
            sucesssound.start();



            completeTxt.setFont(new Font("NanumBarunpen", Font.PLAIN, 24)); // 결제완료 텍스트 속성
            completeTxt.setForeground(Color.BLACK);
            payinfoPanel.add(completeTxt); // 결제완료텍스트

            ordertxtPanel.add(ordertxtInfo); //요청사항
            ordertxtInfo.setEnabled(false);

            numberPanel.add(orderNumber); // 주문번호 텍스트 속성
            orderNumber.setFont(new Font("NanumBarunpen", Font.PLAIN, 30));
            orderNumber.setForeground(Color.BLACK);
            orderNumber.setText("224");


        } else {
            payinfoPanel.add(cancelTxt); // 취소 텍스트 속성
            cancelTxt.setText("<HTML><body>취소되었습니다.<body>다시 주문해주세요.</body></HTML>");
            cancelTxt.setFont(new Font("NanumBarunpen", Font.PLAIN, 24));
            cancelTxt.setForeground(Color.BLACK);

            soundCancel cancelbtn = new soundCancel();// 취소 효과음
            Thread cancelbtnsound = new Thread(cancelbtn);
            cancelbtnsound.start();

        }
    }
}


