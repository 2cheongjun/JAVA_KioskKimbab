import javax.swing.*;



public class kioskFrame extends JFrame { //Frame은 최상위 컨테이너에 패널을 붙혀서 사용한다.
    public kioskFrame(String btnName) {
        this.setTitle("천국김밥 키오스크");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        KioskPanel kioskPanel = new KioskPanel();
        kioskPanel.setBtnName(btnName); // Kiosk패널로 값전달
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(null);
        this.setContentPane(kioskPanel);
        this.setResizable(false); // 사이즈고정
        this.setBounds(400, 0, 900, 1000); // 첫시작화면 사이즈
        this.setVisible(true);

    }
}
