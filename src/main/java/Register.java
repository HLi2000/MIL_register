import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Entities.*;


public class Register extends JFrame{

    private JTextField username = new JTextField();
    private JPasswordField password = new JPasswordField();
    JLabel a1 = new JLabel("Username");
    JLabel a2 = new JLabel("Password");

    JButton confirmbtn = new JButton("register");
    JButton cancelbtn = new JButton("clear");
    JButton deletebtn = new JButton("delete");
    ImageIcon logo = new ImageIcon("img/whiteLogo.png");
    JLabel logo_label = new JLabel(logo);
    public boolean login_status = false;

    //basic properties
    Color text_color = new Color(181, 181, 181);
    Color panel_color = new Color(54, 54, 54);
    Color message_color = new Color(238, 130, 130);
    Font f_1 = new Font(Font.DIALOG, Font.PLAIN, 11);

    public Register() {
        super("Register");
        Container c = getContentPane();
        setBounds(600, 200, 350, 400);
        setResizable(false);
        c.setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        setVisible(true);
    }


    public void init(){


        //logo panel
        JPanel logopanel = new JPanel();
        logopanel.setBounds(0, 0, 350, 130);
        logopanel.setBackground(panel_color);
        logo_label.setBounds(0, 0, 350, 130);
        logopanel.add(logo_label);
        add(logopanel);

        /* Type Center*/
        JPanel fieldPanel = new JPanel();
        fieldPanel.setBounds(0, 130, 350, 270);
        fieldPanel.setBackground(panel_color);
        fieldPanel.setLayout(null);
        a1.setBounds(40, 48, 100, 20);
        a2.setBounds(40, 78, 100, 20);
        a1.setFont(f_1);
        a2.setFont(f_1);
        a1.setForeground(text_color);
        a2.setForeground(text_color);
        fieldPanel.add(a1);
        fieldPanel.add(a2);
        username.setBounds(110, 45, 175, 25);
        password.setBounds(110, 75, 175, 25);
        fieldPanel.add(username);
        fieldPanel.add(password);
        add(fieldPanel);

        confirmbtn.setBounds(30, 190, 75, 25);
        cancelbtn.setBounds(130, 190, 75, 25);
        deletebtn.setBounds(230,190,75,25);
        fieldPanel.add(confirmbtn);
        fieldPanel.add(cancelbtn);
        fieldPanel.add(deletebtn);
        add(fieldPanel);
        loop();
    }

    public void loop()  {
        cancelbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                username.setText("");
                password.setText("");
            }
        });


        confirmbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                User regist = new User();
                String username_s = username.getText();
                char[] pa = password.getPassword();
                String pa_s = String.valueOf(pa);
                regist.setUsername(username_s);
                regist.setPassword(pa_s);
                regist.hashcode();
                Client reg_cl = new Client();

                String[] result = {null};
                try {

                    JPanel messagepanel_suc = new JPanel();
                    messagepanel_suc.setBounds(0, 250, 350, 50);
                    messagepanel_suc.setBackground(panel_color);
                    result[0] = reg_cl.register(regist);
                    JLabel succ_message = new JLabel(result[0]);
                    Font f = new Font(Font.DIALOG, Font.BOLD, 12);
                    succ_message.setFont(f);
                    succ_message.setForeground(message_color);
                    succ_message.setBounds(70, 10, 100, 10);
                    messagepanel_suc.add(succ_message);
                    add(messagepanel_suc);
                    messagepanel_suc.repaint();
                    setVisible(true);
                } catch (Exception e) {
                    System.out.println("failed to regist");
                }
            }
        });
                deletebtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        User regist_delete = new User();
                        char[] pa = password.getPassword();
                        String pa_delete = String.valueOf(pa);
                        regist_delete.setPassword(pa_delete);
                        String username_delete = username.getText();
                        regist_delete.setUsername(username_delete);
                        regist_delete.hashcode();
                        Client reg_cl = new Client();
                        try {
                            reg_cl.delete(regist_delete);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        String[] result = {null};
                        try {
                            JPanel messagepanel_suc = new JPanel();
                            messagepanel_suc.setBounds(0, 250, 350, 50);
                            messagepanel_suc.setBackground(panel_color);
                            result[0] = reg_cl.delete(regist_delete);
                            JLabel succ_message = new JLabel(result[0]);
                            Font f = new Font(Font.DIALOG, Font.BOLD, 12);
                            succ_message.setFont(f);
                            succ_message.setForeground(message_color);
                            succ_message.setBounds(70, 10, 100, 10);
                            messagepanel_suc.add(succ_message);
                            add(messagepanel_suc);
                            messagepanel_suc.repaint();
                            setVisible(true);
                        } catch (Exception e) {
                            System.out.println("failed to regist");
                        }

                    }
                });
            }
    public static void main(String[] args) throws Exception {

        //skin
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(Exception e) {
            System.out.println(e);
        }

        new Register();

    }
        }
