import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Константин on 14.03.2016.
 */
public class Window {
    JTextField fieldP = new JTextField();
    JTextField fieldA = new JTextField();

    JTextField fieldXi = new JTextField();
    JTextField fieldYi = new JTextField();
    JTextField fieldZ1 = new JTextField();

    JTextField fieldXj = new JTextField();
    JTextField fieldYj = new JTextField();
    JTextField fieldZ2 = new JTextField();



    public JFrame createFrame(String name) {
        JFrame frame = new JFrame();
        frame.setName(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Box apBox = Box.createHorizontalBox();

        Box box1 = Box.createHorizontalBox();
        box1.setBorder(new TitledBorder("Число p"));
        box1.add(fieldP);

        fieldA.setEditable(false);
        Box box2 = Box.createHorizontalBox();
        box2.setBorder(new TitledBorder("Число а"));
        box2.add(fieldA);
        box2.add(Box.createRigidArea(new Dimension(6, 6)));
        JButton buttonA = new JButton("Найти а");
        buttonA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isSimple(Integer.parseInt(fieldP.getText()))) {
                    JOptionPane.showMessageDialog(null,"Введите простое число", "Information", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if ("".equals(fieldP.getText())) return;
                int p = Integer.parseInt(fieldP.getText());
                int a = 2;

                metka:
                for (a = 2; a <= p; a++) {
                    int[] aMas = new int[p - 1];
                    aMas[0] = a;
                    for (int i = 2; i < p; i++) {
                        if (Math.pow(a, i) > p) aMas[i - 1] = ((int) (Math.pow(a, i) % p));
                        else aMas[i - 1] = (int) (p % Math.pow(a, i));
                    }

                    Arrays.sort(aMas);

                    for (int j = 0; j < aMas.length - 1; j++) {
                        if (aMas[j] == aMas[j + 1]) {
                            continue metka;
                        }
                    }
                    break;
                }
                fieldA.setText(Integer.toString(a));
            }
        });
        box2.add(buttonA);

        apBox.add(box1);
        apBox.add(Box.createRigidArea(new Dimension(6, 6)));
        apBox.add(box2);

        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(6, 6, 6, 6));

        Box abBox = Box.createHorizontalBox();
        abBox.add(createBox1());
        abBox.add(createBox2());

        mainBox.add(apBox);
        mainBox.add(abBox);

        frame.setContentPane(mainBox);
        frame.setSize(650, 350);
        return frame;
    }

    private Box createBox1() {

        Box box3 = Box.createHorizontalBox();
        box3.setBorder(new TitledBorder("Число Xi"));
        box3.add(fieldXi);
        box3.add(Box.createRigidArea(new Dimension(6, 6)));
        JButton fieldXiButton = new JButton("Выбрать Xi");
        fieldXiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("".equals(fieldP.getText())) {
                    JOptionPane.showMessageDialog(null, "Введите число p",
                            "Information", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Random random = new Random();
                fieldXi.setText(Integer.toString(random.nextInt(Integer.parseInt(fieldP.getText())) + 1));
            }
        });
        box3.add(fieldXiButton);

        Box box4 = Box.createHorizontalBox();
        box4.setBorder(new TitledBorder("Число Yi"));
        fieldYi.setEditable(false);
        box4.add(fieldYi);
        box4.add(Box.createRigidArea(new Dimension(6, 6)));
        JButton fieldYiButton = new JButton("Вычислить Yi");
        fieldYiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("".equals(fieldXi.getText())) {
                    JOptionPane.showMessageDialog(null, "Введите число Xi",
                            "Information", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if ("".equals(fieldA.getText())) {
                    JOptionPane.showMessageDialog(null, "Введите число a",
                            "Information", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Integer a = Integer.parseInt(fieldA.getText());
                Integer Xi = Integer.parseInt(fieldXi.getText());
                Integer p = Integer.parseInt(fieldP.getText());
                fieldYi.setText(Integer.toString((int)(Math.pow(a,Xi) % p)));
            }
        });
        box4.add(fieldYiButton);

        Box box5 = Box.createHorizontalBox();
        box5.setBorder(new TitledBorder("Число Zij"));
        fieldZ1.setEditable(false);
        box5.add(fieldZ1);
        box5.add(Box.createRigidArea(new Dimension(6, 6)));
        JButton fieldZ1Button = new JButton("Вычислить Zij");
        fieldZ1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("".equals(fieldXi.getText())) {
                    JOptionPane.showMessageDialog(null, "Введите число Xi",
                            "Information", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if ("".equals(fieldYj.getText())) {
                    JOptionPane.showMessageDialog(null, "Введите число Yj",
                            "Information", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Integer Xi = Integer.parseInt(fieldXi.getText());
                Integer Yj = Integer.parseInt(fieldYj.getText());
                Integer p = Integer.parseInt(fieldP.getText());
                fieldZ1.setText(Integer.toString((int)(Math.pow(Yj,Xi))));
            }
        });
        box5.add(fieldZ1Button);

        Box box = Box.createVerticalBox();
        box.setBorder(new CompoundBorder(new TitledBorder("Алиса"), new EmptyBorder(12, 12, 12, 12)));
        box.add(box3);
        box.add(Box.createRigidArea(new Dimension(6, 6)));
        box.add(box4);
        box.add(Box.createRigidArea(new Dimension(6, 6)));
        box.add(box5);
        return box;
    }

    private Box createBox2() {

        Box box3 = Box.createHorizontalBox();
        box3.setBorder(new TitledBorder("Число Xj"));
        box3.add(fieldXj);
        box3.add(Box.createRigidArea(new Dimension(6, 6)));
        JButton fieldXjButton = new JButton("Выбрать Xj");
        fieldXjButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("".equals(fieldP.getText())) {
                    JOptionPane.showMessageDialog(null, "Введите число p",
                            "Information", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Random random = new Random();
                fieldXj.setText(Integer.toString(random.nextInt(Integer.parseInt(fieldP.getText())) + 1));
            }
        });

        box3.add(fieldXjButton);

        Box box4 = Box.createHorizontalBox();
        box4.setBorder(new TitledBorder("Число Yj"));
        fieldYj.setEditable(false);
        box4.add(fieldYj);
        box4.add(Box.createRigidArea(new Dimension(6, 6)));
        JButton fieldYjButton = new JButton("Вычислить Yj");
        fieldYjButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("".equals(fieldXj.getText())) {
                    JOptionPane.showMessageDialog(null, "Введите число Xj",
                            "Information", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if ("".equals(fieldA.getText())) {
                    JOptionPane.showMessageDialog(null, "Введите число a",
                            "Information", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Integer a = Integer.parseInt(fieldA.getText());
                Integer Xj = Integer.parseInt(fieldXj.getText());
                Integer p = Integer.parseInt(fieldP.getText());
                fieldYj.setText(Integer.toString((int)(Math.pow(a,Xj) % p)));
            }
        });
        box4.add(fieldYjButton);

        Box box5 = Box.createHorizontalBox();
        box5.setBorder(new TitledBorder("Число Zji"));
        fieldZ2.setEditable(false);
        box5.add(fieldZ2);
        box5.add(Box.createRigidArea(new Dimension(6, 6)));
        JButton fieldZ2Button = new JButton("Вычислить Zji");
        fieldZ2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("".equals(fieldXj.getText())) {
                    JOptionPane.showMessageDialog(null, "Введите число Xj",
                            "Information", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if ("".equals(fieldYi.getText())) {
                    JOptionPane.showMessageDialog(null, "Введите число Yi",
                            "Information", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Integer Xj = Integer.parseInt(fieldXj.getText());
                Integer Yi = Integer.parseInt(fieldYi.getText());
                Integer p = Integer.parseInt(fieldP.getText());
                fieldZ2.setText(Integer.toString((int)(Math.pow(Yi,Xj))));
            }
        });
        box5.add(fieldZ2Button);

        Box box = Box.createVerticalBox();
        box.setBorder(new CompoundBorder(new TitledBorder("Боб"), new EmptyBorder(12, 12, 12, 12)));
        box.add(box3);
        box.add(Box.createRigidArea(new Dimension(6, 6)));
        box.add(box4);
        box.add(Box.createRigidArea(new Dimension(6, 6)));
        box.add(box5);
        return box;
    }

    public boolean isSimple(int n) {
        boolean fl = true;

        for (int i = 2; i < n; i++) {
            if (n % i == 0)
                fl = false;
        }
        return fl;
    }
}
