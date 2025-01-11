package dict;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;




public class GUI extends JFrame {

    public GUI() {
        // Set the title of the window
        setTitle("Swing UI Example");

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the components
        JPanel panel = new JPanel();

        // Create a label
        JLabel label = new JLabel("Hello, Swing!");
        
        // ActionListener cb = ;
        
        // Create a button
        JButton button = new JButton("Click me!");
        JTextField tf = new JTextField();
        button.addActionListener(e->{
            System.out.println(tf.getText());
        });

        JList list = new JList<>();
        list.add(new JLabel("Hello"));

        

        tf.setSize(90, 10);
        // Add the components to the panel
        panel.add(label);
        panel.add(button);
        panel.add(list);
        panel.add(tf);
        tf.setPreferredSize(new DimensionUIResource(200, 30));


        // Add the panel to the content pane of the frame
        getContentPane().add(panel);

        // Set the size of the window
        setSize(300, 200);

        // Make the window visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Create the Swing UI on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI();
            }
        });
    }
}
