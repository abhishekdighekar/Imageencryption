import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class course_project {
    public static void operate(int key) {

        JFileChooser fileChooser = new JFileChooser();// built in function of swing package
        fileChooser.showOpenDialog(null);// used to open the file manager of the device
        File file = fileChooser.getSelectedFile();// checking if the file is selected or not
        // file FileInputStream
        try {

            FileInputStream fis = new FileInputStream(file); // creating an object fis to take the input from the file

            byte[] data = new byte[fis.available()];// without blocking next method it will read the remaining bytes of
                                                    // file
            fis.read(data);
            int i = 0;
            for (byte b : data) {// running for each loop until all the bytes of the file is encrypted by the key
                                 // which is assigned by the user

                System.out.println(b);
                data[i] = (byte) (b ^ key);// encrypting the file using ^ (XOR) operator with provided key
                i++;
            }

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
            fis.close();
            JOptionPane.showMessageDialog(null, "Done");// printing the message done after the file is succesfully
                                                        // encrypted.

        } catch (Exception e) {// if exception occured then we are catching it.
            e.printStackTrace();
        }
    }

    // Main program.
    public static void main(String[] args) {

        System.out.println("Commencing trials.....");

        // desingning interface using Jframe, JButton classes which we are using from
        // package swing imported above
        JFrame f = new JFrame();
        f.setTitle("Image Encryption and Decryption");
        f.setSize(400, 400);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("Roboto", Font.BOLD, 25);
        // creating button
        JButton button = new JButton();
        button.setText("Encrypt/Decrypt an image");
        button.setFont(font);

        // creating text field

        JTextField textField = new JTextField(10);
        textField.setFont(font);

        button.addActionListener(e -> {
            System.out.println("A button has been clicked.");
            String text = textField.getText();
            int temp = Integer.parseInt(text);
            operate(temp);
        });

        f.setLayout(new FlowLayout());

        f.add(button);
        f.add(textField);
        f.setVisible(true);

    }
}