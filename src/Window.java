import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

/**
 * Created by Константин on 14.03.2016.
 */
public class Window {
    public JFrame createFrame(String name) {
        JFrame frame = new JFrame();
        frame.setName(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel enterP = new JLabel("Enter P");
        JTextField fieldForP = new JTextField();
        JTextArea fieldForI = new JTextArea();
        JTextArea fieldForJ = new JTextArea();
        JButton okButton = new JButton("OK");
        okButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        Box box1 = Box.createHorizontalBox();
        box1.add(enterP);
        box1.add(Box.createRigidArea(new Dimension(6, 6)));
        box1.add(fieldForP);

        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainBox.add(box1);
        mainBox.add(Box.createRigidArea(new Dimension(6, 6)));
        mainBox.add(fieldForI);
        mainBox.add(Box.createRigidArea(new Dimension(6, 6)));
        mainBox.add(fieldForJ);
        mainBox.add(Box.createRigidArea(new Dimension(6, 6)));
        mainBox.add(okButton);


        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("".equals(fieldForP.getText())) return;
                int p = Integer.parseInt(fieldForP.getText());
                Random random = new Random();
                int x = random.nextInt(p - 1) + 1;
                int a = 7;
                int aMas[] = new int[p - 1];
                for (int i = 1; i < p; i++) {
                    aMas[i - 1] = (int) (Math.pow(a, i) % p);
                }
                for (int i = 0; i < p - 1; i++) {
                    System.out.printf(Integer.toString(aMas[i]) + " ");
                }
                int[] list = new int[10000];
                for (int i = 9999; i > 0; i -= 2) {
                    list[i] = i;
                }
                quickSort(list, 0, 9999);
/*                fieldForI.setText("Xi = " + Math.pow(2,6000));*/
            }
        });

        frame.setContentPane(mainBox);
        frame.setSize(400, 300);
        return frame;
    }

    int partition(int arr[], int left, int right) {
        int i = left, j = right;
        int tmp;
        int pivot = arr[(left + right) / 2];

        while (i <= j) {
            while (arr[i] < pivot)
                i++;
            while (arr[j] > pivot)
                j--;
            if (i <= j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }
        ;

        return i;
    }

    void quickSort(int arr[], int left, int right) {
        int index = partition(arr, left, right);
        if (left < index - 1)
            quickSort(arr, left, index - 1);
        if (index < right)
            quickSort(arr, index, right);
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
