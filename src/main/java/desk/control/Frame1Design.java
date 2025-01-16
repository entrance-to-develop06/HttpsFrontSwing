package main.java.desk.control;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.table.TableColumn;

import main.java.desk.http.HttpSetting;

public class Frame1Design extends FrameDesign {
    private JFrame fWindow = new JFrame();
    private JTable table1;
    private JScrollPane scroll1;
    private JScrollPane scrollArea;
    private DefaultTableModel dTableModel;
    private String[] headers = {"ユニークID", "商品番号", "商品名", "編集日付", "編集時刻", "備考"};
    private JComboBox<String> cmbHttpVer = new JComboBox<>();
    private JComboBox<String> cmbSslProtocol = new JComboBox<>();
    private JTextArea labelArea = new JTextArea();
    private JLabel labelUniqueId = new JLabel();
    private JLabel labelFoot = new JLabel();
    private JTextField textUri = new JTextField();
    private JTextField textReqBody = new JTextField();
    private JTextField textShohinCode = new JTextField();
    private JTextField textShohinName = new JTextField();
    private JTextField textRemarks = new JTextField();
    private JButton buttonRead = new JButton();
    private JButton buttonAdd = new JButton();
    private JButton buttonChange = new JButton();
    private JButton buttonErase = new JButton();

    public Frame1Design() {
        createWindow();
    }

    private void createWindow() {
        fWindow.setTitle("RESTful API クライアント(HttpClient)");
        fWindow.setLocation(500,200);
        fWindow.setSize(800, 600);
        fWindow.setUndecorated(false); // タイトルバー表示・非表示
        fWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dTableModel = new DefaultTableModel(headers, 0);
        table1 = new JTable(dTableModel);
        scroll1 = new JScrollPane(table1);
        scroll1.setBounds(25,25,730,200);
        fPanel.setPreferredSize(new Dimension(700, 100));
        fPanel.add(scroll1);

        labelsSetting("labelUri", "URI:", 25, 235, 50, 25);
        textUri.setText("https://localhost:8443/rest/read");
        textUri = (JTextField)componentsSetting(textUri, "textUri", 65, 235, 300, 25);
        labelsSetting("labelHttpVer", "HTTPバージョン：", 375, 235, 110, 25);
        for (String ver : HttpSetting.httpVersion)
            cmbHttpVer.addItem(ver);
        cmbHttpVer = (JComboBox<String>)componentsSetting(cmbHttpVer, "cmbHttpVer", 485,235,80,25);
        labelsSetting("labelSslProtocol", "SSLプロトコル：", 575, 235, 100, 25);
        for (String ssl : HttpSetting.sslProtocol)
            cmbSslProtocol.addItem(ssl);
        cmbSslProtocol = (JComboBox)componentsSetting(cmbSslProtocol, "cmbSslProtocol", 675,235,80,25);
        labelsSetting("labelReqBody", "レスポンスBody：", 25, 260, 100, 25);
        textReqBody = (JTextField)componentsSetting(textReqBody, "textReqBody", 130, 260, 625, 25);

        labelArea.setText("");
        labelArea.setFocusable(false);
        scrollArea = new JScrollPane(labelArea);
        scrollArea = (JScrollPane)componentsSetting(scrollArea, "scrollArea", 25, 290, 350, 200);

        labelsSetting("label1", "ユニークID：", 400, 300, 100, 25);
        labelsSetting("label2", "商品番号：", 400, 350, 100, 25);
        labelsSetting("label3", "商品名：", 400, 400, 100, 25);
        labelsSetting("label4", "備考：", 400, 450, 100, 25);
        labelUniqueId.setHorizontalAlignment(JLabel.RIGHT);
        labelUniqueId = (JLabel)componentsSetting(labelUniqueId, "labelUniqueId", 500, 300, 250, 25);
        labelFoot.setText("Copyright  ©  2021-2025  entrance-to-develop06");
        labelFoot = (JLabel)componentsSetting(labelFoot, "labelFoot", 30, 538, 300, 25);

        textShohinCode = (JTextField)componentsSetting(textShohinCode, "textShohinCode", 600, 350, 150, 25);
        textShohinName = (JTextField)componentsSetting(textShohinName, "textShohinName", 540, 400, 210, 25);
        textRemarks = (JTextField)componentsSetting(textRemarks, "textRemarks", 450, 450, 300, 25);

        buttonRead.setText("抽出(GET)");
        buttonRead = (JButton)componentsSetting(buttonRead, "buttonQuery", 25, 490, 150, 50);
        buttonAdd.setText("追加(POST)");
        buttonAdd = (JButton)componentsSetting(buttonAdd, "buttonAdd", 220, 490, 150, 50);
        buttonChange.setText("更新(PUT)");
        buttonChange = (JButton)componentsSetting(buttonChange, "buttonChange", 410, 490, 150, 50);
        buttonErase.setText("削除(DELETE)");
        buttonErase = (JButton)componentsSetting(buttonErase, "buttonErase", 600, 490, 150, 50);
        fPanel.setLayout(null);
    }

    public void getTableRowSetTextField() {
        if (dTableModel.getRowCount() > 0) { //setRowCount(0)で全表示行クリアした後にもvalueChange()メソッドが呼ばれるので追加
            labelUniqueId.setText((/*(Integer)*/ table1.getValueAt(table1.getSelectedRow(), 0)).toString());
            textShohinCode.setText(((Short) table1.getValueAt(table1.getSelectedRow(), 1)).toString());
            textShohinName.setText(table1.getValueAt(table1.getSelectedRow(), 2).toString());
            textRemarks.setText(table1.getValueAt(table1.getSelectedRow(), 5).toString());
        }
    }

    public void textFieldClear() {
        labelUniqueId.setText("");
        textShohinCode.setText("");
        textShohinName.setText("");
        textRemarks.setText("");
    }

    public void tableSetting() {
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //table1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        TableColumn col = table1.getColumnModel().getColumn(0);
        col.setPreferredWidth(250);
        col = table1.getColumnModel().getColumn(1);
        col.setPreferredWidth(60);
        col = table1.getColumnModel().getColumn(2);
        col.setPreferredWidth(120);
        col = table1.getColumnModel().getColumn(3);
        col.setPreferredWidth(70);
        col = table1.getColumnModel().getColumn(4);
        col.setPreferredWidth(60);
        col = table1.getColumnModel().getColumn(5);
        col.setPreferredWidth(165);
        /**col = Table1.getColumnModel().getColumn(5);
         col.setPreferredWidth(35);**/
    }

    public JFrame getFWindow() {
        return fWindow;
    }

    public JPanel getFPanel() {
        return fPanel;
    }

    public JTable getTable1() {
        return table1;
    }
    public DefaultTableModel getDTableModel() {
        return dTableModel;
    }
    public JTextArea getLabelArea() {
        return labelArea;
    }
    public JComboBox<String> getCmbHttpVer() {
        return cmbHttpVer;
    }
    public JComboBox<String> getCmbSslProtocol() {
        return cmbSslProtocol;
    }
    public JTextField getTextUri() {
        return textUri;
    }
    public JTextField getTextReqBody() {
        return textReqBody;
    }
    public JLabel getLabelUniqueId() {
        return labelUniqueId;
    }
    public JLabel getLabelFoot() {
        return labelFoot;
    }
    public JTextField getTextShohinCode() {
        return textShohinCode;
    }
    public JTextField getTextShohinName() {
        return textShohinName;
    }
    public JTextField getTextRemarks() {
        return textRemarks;
    }
    public JButton getButtonRead() {
        return buttonRead;
    }
    public JButton getButtonAdd() {
        return buttonAdd;
    }
    public JButton getButtonChange() {
        return buttonChange;
    }
    public JButton getButtonErase() {
        return buttonErase;
    }
}