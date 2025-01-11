import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleUI extends JFrame implements ActionListener {
    private JButton backButton;
    private JTextArea textArea;

    public SimpleUI() {
        // Set up the frame
        setTitle("Simple UI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the back button
        backButton = new JButton("Back");
        backButton.setSize(30, 30);
        backButton.addActionListener(this);

        // Create the text area
        textArea = new JTextArea("Hello");
        textArea.setEditable(false);

        // Add components to the frame
        add(backButton, BorderLayout.EAST);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Display the frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            // Handle the back button click
            // For example, you could navigate to a previous screen or perform other actions.
            System.out.println("Back button clicked");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SimpleUI();
            }
        });
    }
}