import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

                                                                                // 키오스크 첫화면 시작
                                                                                // 포장,매장 선택 -> 메인 KioskPanel로 이동
public class KioskMain extends JFrame implements ActionListener,MouseListener {

    JFrame frame = new JFrame(); // 최상위 프레임
    JPanel titlePanel = new JPanel(new BorderLayout()); // 타이틀 패널
    JPanel storePanel = new JPanel(new BorderLayout());   // 매장버튼패널
    JPanel packPanel = new JPanel(new BorderLayout());  // 포장버튼 패널

    JButton btnStore = new JButton(); // 버튼은 2개 (매장, 포장)
    JButton btnPacking = new JButton();

    JButton label = new  JButton(); // 상단 알림 텍스트 "이용방법을 선택하세요"


    public KioskMain() {
        super("천국김밥에 오신것을 환영합니다."); // 팝업 제목


        this.setLayout(new BorderLayout()); // 동서남북 배치가능, setLocation,setSize로 조절안됨..
        this.setLayout(new GridLayout(2, 2, 30, 0)); //보더레이아웃안에 그리드레이아웃을 넣을수있음.

        Box store = Box.createVerticalBox(); //  매장버튼 박스
        store.add(Box.createVerticalStrut(30));

        Box pack = Box.createVerticalBox(); // 포장버튼 박스
        pack.add(Box.createVerticalStrut(30));

        Box title = Box.createVerticalBox(); //  상단 알림 텍스트박스
        title.add(Box.createVerticalStrut(20));


        titlePanel.add(title, BorderLayout.CENTER); // 상단 알림 패널 속성
        label.setText("<HTML><body style='text-align:center;'> 천국김밥 키오스크 <br> 이용방법을 선택해주세요.<br> </body></HTML>"); // 안내문구
        label.setFont(new Font("NanumBarunpen", Font.PLAIN, 60));
        label.setForeground(Color.BLACK);
        label.setBorder(null);
        titlePanel.add(label);
        titlePanel.setBackground(new Color(236, 232, 231));
        frame.add(titlePanel);


        storePanel.add(store, BorderLayout.CENTER); // 매장버튼 패널 속성
        storePanel.setBackground(new Color(236, 232, 231));
        btnStore.setFocusable(false);
        btnStore.setFont(new Font("NanumBarunpen", Font.BOLD, 80));
        btnStore.setText("매장선택");
        btnStore.setForeground(new Color(213, 100, 62));


        storePanel.add(btnStore);
        frame.add(storePanel);


        packPanel.add(pack, BorderLayout.CENTER); // 포장버튼 패널 속성
        packPanel.setBackground(new Color(236, 232, 231));
        btnPacking.setFocusable(false);
        btnPacking.setBackground(Color.BLUE);
        btnPacking.setText("포장선택");
        btnPacking.setFont(new Font("NanumBarunpen", Font.BOLD, 80));
        btnPacking.setForeground(new Color(81,160,177));
        packPanel.add(btnPacking);
        frame.add(packPanel);


        Box vertical = Box.createVerticalBox(); // 수직배치
        vertical.add(titlePanel);
        add(vertical, BorderLayout.NORTH);

        Box center = Box.createHorizontalBox(); // 수평배치
        center.add(storePanel);
        center.add(packPanel);
        this.add(center, BorderLayout.CENTER);


        setBounds(400, 0, 900, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);


        btnStore.addMouseListener(this); // 마우스클릭 이벤트
        btnPacking.addMouseListener(this);
        btnStore.addActionListener(this); // 액션 이벤트 등록
        btnPacking.addActionListener(this);
    }



    //----------------------------------------------------------------------------------------------------------- 마우스 이벤트
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {


    }


    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == btnStore) {
            btnStore.setText("매장선택");
            btnStore.setForeground(new Color(213, 100, 62));
            btnPacking.setText("포장선택");
            btnPacking.setForeground(Color.LIGHT_GRAY);
        }else{
            btnStore.setText("매장선택");
            btnStore.setForeground(Color.LIGHT_GRAY);
            btnPacking.setText("포장선택");
            btnPacking.setForeground(new Color(81,160,177));
        }

    }

    @Override
    public void mouseExited (MouseEvent e){
        btnStore.setText("매장선택");
        btnStore.setForeground(new Color(213, 100, 62));

        btnPacking.setText("포장선택");
        btnPacking.setForeground(new Color(81,160,177));

    }

 //----------------------------------------------------------------------------------------------------------- 액션 이벤트

    public void actionPerformed(ActionEvent e) { // 매장,포장 버튼을누르면 Kiosk 프레임을 연결-> (메뉴판 화면)
        if (e.getSource() == btnStore) {
            label.setVisible(false);
            new kioskFrame(btnStore.getText());// 버튼이름 값 전달 (KioskFrame의 결제확인 textfield로 전달)

            soundBtnstore store = new soundBtnstore();// 효과음
            Thread sound = new Thread(store);
            sound.start();

        } else {
            label.setVisible(false);
            new kioskFrame(btnPacking.getText());

            soundBtnpack btnpack = new soundBtnpack();// 결제완료창 효과음
            Thread btnpacksound = new Thread(btnpack);
            btnpacksound.start();
        }
    }


    //-----------------------------------------------------------------------------------------------------------메인 시작
    public static void main(String[] args) { //첫 시작화면,  KioskMain의 팝업 ->매장,포장선택 -> 메뉴판 화면
        new KioskMain();

    }

}
