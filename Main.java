import java.awt.*;
import javax.swing.*;

public class Main {
    static public int count;
    static public int max_value;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Prime Number Calculator");
        frame.getContentPane().setBackground(new java.awt.Color(255, 228, 225));
        frame.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.PINK));

        JLabel w1 = new JLabel("");
        w1.setBounds(30, 20, 300, 30);
        w1.setFont(new Font(Font.SERIF, Font.BOLD, 13));


        final JTextField txt1 = new JTextField();
        txt1.setBounds(30, 45, 150, 24);
        JLabel ul1 = new JLabel("Number");
        ul1.setBounds(240, 40, 150, 30);
        ul1.setFont(new Font(Font.SERIF, Font.BOLD | Font.ITALIC, 22));
        ul1.setForeground(new java.awt.Color(205, 92, 92));

        JLabel w2 = new JLabel("");
        w2.setBounds(30, 68, 300, 30);
        w2.setFont(new Font(Font.SERIF, Font.BOLD, 13));

        final JTextField txt2 = new JTextField();
        txt2.setBounds(30, 95, 150, 24);
        JLabel ul2 = new JLabel("Buffer Size ");
        ul2.setBounds(240, 90, 150, 30);
        ul2.setFont(new Font(Font.SERIF, Font.BOLD | Font.ITALIC, 22));
        ul2.setForeground(new java.awt.Color(205, 92, 92));


        JLabel w3 = new JLabel("");
        w3.setBounds(30, 122, 300, 30);
        w3.setFont(new Font(Font.SERIF, Font.BOLD, 13));

        final JTextField txt3 = new JTextField();
        txt3.setBounds(30, 145, 150, 24);
        JLabel ul3 = new JLabel("File Name ");
        ul3.setBounds(240, 140, 150, 30);
        ul3.setFont(new Font(Font.SERIF, Font.BOLD | Font.ITALIC, 22));
        ul3.setForeground(new java.awt.Color(205, 92, 92));

        JButton button = new JButton("♥ SOLVE ♥");
        button.setBounds(30, 200, 300, 40);
        button.setFont(new Font(Font.SERIF, Font.BOLD, 22));

        JLabel al1 = new JLabel("Largest Prime: ");
        al1.setBounds(30, 260, 300, 30);
        al1.setFont(new Font(Font.SERIF, Font.BOLD | Font.ITALIC, 22));
        JLabel ans1 = new JLabel("?");
        ans1.setBounds(290, 260, 60, 30);
        ans1.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        ans1.setForeground(Color.BLUE);

        JLabel al2 = new JLabel("Prime Count: ");
        al2.setBounds(30, 300, 300, 30);
        al2.setFont(new Font(Font.SERIF, Font.BOLD | Font.ITALIC, 22));
        JLabel ans2 = new JLabel("?");
        ans2.setBounds(290, 300, 60, 30);
        ans2.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        ans2.setForeground(Color.BLUE);

        JLabel al3 = new JLabel("Time elapsed: ");
        al3.setBounds(30, 340, 300, 30);
        al3.setFont(new Font(Font.SERIF, Font.BOLD | Font.ITALIC, 22));
        JLabel ans3 = new JLabel("?");
        ans3.setBounds(290, 340, 60, 30);
        ans3.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        ans3.setForeground(Color.BLUE);

        // Show Frame Elements
        frame.setSize(400, 450);
        frame.add(w1);
        frame.add(w2);
        frame.add(w3);
        frame.add(ul1);
        frame.add(ul2);
        frame.add(ul3);
        frame.add(txt1);
        frame.add(txt2);
        frame.add(txt3);
        frame.add(button);
        frame.add(al1);
        frame.add(al2);
        frame.add(al3);
        frame.add(ans1);
        frame.add(ans2);
        frame.add(ans3);
        frame.setLayout(null);
        frame.setVisible(true);
        // ...

        button.addActionListener(e -> {
            count = max_value = 0;
            long startTime = System.currentTimeMillis();
            Buffer buf = new Buffer(Integer.parseInt(txt2.getText()));

            Thread producer = new Thread(new Producer(buf, Integer.parseInt(txt1.getText())));
            Thread consumer = new Thread(new Consumer(buf, txt3.getText()));
            producer.start();
            consumer.start();
            try {
                // Wait consumer & Producer to end their work before showing answer
                consumer.join();
                producer.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            if (Integer.parseInt(txt2.getText()) > Integer.parseInt(txt1.getText())) {
                w2.setText("Note: Buffer Size > Number");
                w2.setForeground(Color.red);
            } else {
                w2.setText("✔");
                w2.setForeground(Color.green);
            }

            String t3 = txt3.getText();
            if (!t3.substring(t3.length() - 4).equals(".txt")) {
                w3.setText("Note: Extension should have (.txt)");
                w3.setForeground(Color.red);
            } else {
                w3.setText("✔");
                w3.setForeground(Color.green);
            }

            // Show Answer on the screen
            if (max_value == 0) {
                ans1.setText(String.valueOf("NONE"));
                w1.setText("Note: Smallest Prime Num is '2'");
                w1.setForeground(Color.red);
            } else {
                ans1.setText(String.valueOf(max_value));
                w1.setText("✔");
                w1.setForeground(Color.green);
            }

            ans2.setText(String.valueOf(count));
            ans3.setText(System.currentTimeMillis() - startTime + " ms");
            SwingUtilities.updateComponentTreeUI(frame);
        });
    }
}