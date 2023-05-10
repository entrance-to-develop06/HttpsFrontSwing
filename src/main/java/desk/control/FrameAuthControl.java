package main.java.desk.control;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.desk.http.Authentication;

public class FrameAuthControl implements ActionListener{
    private FrameAuthDesign dialog = new FrameAuthDesign();

    public void goEvent() {
        dialog.getButtonAuth().addActionListener(this);
        dialog.getButtonCancel().addActionListener(this);
        dialog.getDialog().getContentPane().add(dialog.getFPanel(),BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (dialog.getButtonAuth().equals(event.getSource())) {
            Authentication.setUserID(dialog.getTextUserName().getText());
            Authentication.setPassword(dialog.getTextPassword().getPassword());
            dialog.getDialog().setVisible(false);
        } else {
            //キャンセルボタンクリック
            dialog.getDialog().setVisible(false);
        }
    }

    public void setVisible(boolean bool) {
        dialog.getDialog().setVisible(bool);
    }
}