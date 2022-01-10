import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class registration extends JFrame{

    private JTextField username = new JTextField();
    private JPasswordField password = new JPasswordField();
    JLabel a1 = new JLabel("Username");
    JLabel a2 = new JLabel("Password");

    JButton confirmbtn = new JButton("confirm");
    JButton cancelbtn = new JButton("clear");
    ImageIcon logo = new ImageIcon("img/whiteLogo.png");
    JLabel logo_label = new JLabel(logo);
    public boolean login_status = false;

    //basic properties
    Color text_color = new Color(181, 181, 181);
    Color panel_color = new Color(54, 54, 54);
    Color message_color = new Color(238, 130, 130);
    Font f_1 = new Font(Font.DIALOG, Font.PLAIN, 11);


    public registration() {
        super("Registration");
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

        confirmbtn.setBounds(75, 190, 75, 25);
        cancelbtn.setBounds(190, 190, 75, 25);
        fieldPanel.add(confirmbtn);
        fieldPanel.add(cancelbtn);
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
                char[] pa = password.getPassword();
                String pa_s = String.valueOf(pa);
                regist.getUsername(username.getText());
                regist.getUsername(pa_s);
                regist.hashcode();
                Client reg_cl = new Client();

                if (username.getText().equals("123") ) {
                    try{
                    reg_cl.register(regist);
                    setVisible(false);}
                    catch (Exception e){
                        System.out.println("failed to regist");
                    }
                } else {
                    //show the error message
                    JPanel messagepanel = new JPanel();
                    messagepanel.setBounds(0, 250, 350, 50);
                    messagepanel.setBackground(panel_color);
                    JLabel error_message_1 = new JLabel("This username is already registered, ");
                    JLabel error_message_2 = new JLabel("please choose another one");
                    Font f = new Font(Font.DIALOG, Font.BOLD, 12);
                    error_message_1.setFont(f);
                    error_message_1.setForeground(message_color);
                    error_message_1.setBounds(70,10,100,10);
                    error_message_2.setBounds(60,40,100,10);
                    error_message_2.setFont(f);
                    error_message_2.setForeground(message_color);
                    messagepanel.add(error_message_1);
                    messagepanel.add(error_message_2);
                    add(messagepanel);
                    setVisible(true);
                }
            }
        });
    }
    public static void main(String[] args) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(Exception e) {
            System.out.println(e);
        }
        new registration();
    }

}
