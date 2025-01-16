package main.java.desk.control;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FrameAuthDesign extends FrameDesign {
    private JDialog dialog = new JDialog();
    private JTextField textUserName = new JTextField();
    private JPasswordField textPassword = new JPasswordField();
    private JButton buttonAuth = new JButton();
    private JButton buttonCancel = new JButton();

    public FrameAuthDesign() {
        createWindow();
    }

    private void createWindow() {
        dialog.setName("FrameAuthDesign");
        dialog.setTitle("認証");
        dialog.setBounds(600, 250, 450, 250);
        dialog.setUndecorated(false); // タイトルバー表示・非表示
        dialog.setModal(true);
        //dialog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);

        labelsSetting("labelMessage", "認証が必要です。ユーザー名とパスワードを入力して下さい。", 25, 25, 350, 25);
        labelsSetting("labelUserName", "ユーザー:", 25, 50, 80, 25);
        labelsSetting("labelPassword", "パスワード:", 25, 100, 80, 25);

        textUserName = (JTextField)componentsSetting(textUserName, "textBoxUserName", 120, 50, 250, 25);
        textPassword = (JPasswordField)componentsSetting(textPassword, "textBoxPassword", 120, 100, 250, 25);

        buttonAuth.setText("認証");
        buttonAuth = (JButton)componentsSetting(buttonAuth, "buttonAuth", 25, 150, 150, 50);
        buttonCancel.setText("キャンセル");
        buttonCancel = (JButton)componentsSetting(buttonCancel, "buttonCancel", 250, 150, 150, 50);

        fPanel.setLayout(null);
    }

    public JDialog getDialog() {
        return dialog;
    }

    public JPanel getFPanel() {
        return fPanel;
    }

    public JTextField getTextUserName() {
        return this.textUserName;
    }

    public JPasswordField getTextPassword() {
        return this.textPassword;
    }
    public JButton getButtonAuth() {
        return this.buttonAuth;
    }
    public JButton getButtonCancel() {
        return this.buttonCancel;
    }
}