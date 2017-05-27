package cn.sourcecodes.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 * Created by cn.sourcecodes on 2017/5/23.
 */
public class DialogAuthenticator extends Authenticator {
    private JDialog passwordDialog;
    private JTextField usernameField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JButton okButton = new JButton("ok");
    private JButton cancelButton = new JButton("cancel");
    private JLabel mainLabel = new JLabel("please enter username and password");

    public DialogAuthenticator() {

    }
    public DialogAuthenticator(String username) {

    }
    public DialogAuthenticator(JFrame parent) {

    }
    public DialogAuthenticator(String username, JFrame parent) {
        this.passwordDialog = new JDialog(parent, true);
        Container pane = passwordDialog.getContentPane();
        pane.setLayout(new GridLayout(4, 1));

        JLabel userLabel = new JLabel("username:");
        JLabel passwordLabel = new JLabel("password");
        pane.add(mainLabel);

        JPanel p2 = new JPanel();
        p2.add(userLabel);
        p2.add(usernameField);
        usernameField.setText(username);
        pane.add(p2);

        JPanel p3 = new JPanel();
        p3.add(passwordLabel);
        p3.add(passwordField);
        pane.add(p3);

        JPanel p4 = new JPanel();
        p4.add(okButton);
        p4.add(cancelButton);
        pane.add(p4);
        passwordDialog.pack();

        ActionListener a1 = new OkResponse();
        okButton.addActionListener(a1);
        usernameField.addActionListener(a1);
        passwordField.addActionListener(a1);
        cancelButton.addActionListener(new CancelResponse());
    }

    private void show() {
        String prompt = this.getRequestingPrompt();
        if(prompt == null) {
            String site = this.getRequestingSite().getHostName();
            String protocol = this.getRequestingProtocol();
            int port = this.getRequestingPort();
            if(site != null & protocol != null) {
                prompt = protocol + "://" + site;
                if(port > 0) prompt += ":" + port;
            } else {
                prompt = "";
            }
        }
    }

    public PasswordAuthentication getPasswordAuthentication() {
        this.show();
        return this.response;
    }


    PasswordAuthentication response = null;

    class OkResponse implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            passwordDialog.setVisible(false);

            char[] password = passwordField.getPassword();
            String username = usernameField.getText();

            passwordField.setText("");
            response = new PasswordAuthentication(username, password);
        }
    }

    class CancelResponse implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            passwordDialog.setVisible(false);
            passwordField.setText("");
            response = null;
        }
    }

}

