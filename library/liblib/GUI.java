package liblib;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.plaf.DimensionUIResource;

import liblib.exceptions.DuplicateIdException;
import liblib.exceptions.ItemAlreadyExists;
import liblib.exceptions.ItemNotAvailable;
import liblib.exceptions.ItemNotFoundException;
import liblib.exceptions.RelationAlreadyExists;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class GUI extends JFrame {
    private JPanel currentActivity;

    Manager manager;

    //#region UTILS
    private boolean isTextFieldEmpty(JTextField tf) {
        return tf.getText().isEmpty();
    }
    private void switchActivitiy(GuiActivity activity) {
        if (this.currentActivity != null) {
            this.getContentPane().remove(this.currentActivity);
        }
        this.currentActivity = activity.run();
        this.getContentPane().add(this.currentActivity);
        this.revalidate();
        this.repaint();
    }
    
    private boolean isValidEmail(String test){
        return test.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
  
    //#endregion


    //#region HOME
    JPanel dummy_screen() {
        JPanel root = new JPanel();
        this.setSize(600, 400);
        JLabel label = new JLabel("Hello", JLabel.LEFT);
        label.setBounds(0, 0, 200, 40);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        // label.setText();
        root.add(label, BorderLayout.CENTER);
        final int rootw = 600;
        final int rooth = root.getHeight();

        label.setBounds(0, 0, 600, 400);
        Runnable fiber = () -> {
            int x = 0, y = 0;

            while (true) {
                try {
                    label.setBounds(x = x + 50, y, 100, 40);
                    if (x >= rootw) {
                        x = 0;
                    }
                    Thread.sleep(300);

                } catch (InterruptedException e) {
                }
            }
        };

        Thread th = new Thread(fiber);
        th.start();

        return root;
    }

    JPanel home_screen() {
        JPanel root = new JPanel();

        JButton addItemButton = new JButton("Add Item");

        addItemButton.addActionListener((ev) -> {
            this.switchActivitiy(this::add_book_screen);
        });

        JButton showItemsButton = new JButton("Show items");
        showItemsButton.addActionListener(e -> {
            switchActivitiy(this::items_table_screen);
        });

        JButton borrowButton = new JButton("Create Borrow");
        borrowButton.addActionListener(e -> {
            switchActivitiy(this::borrow_screen);
        });

        JButton addClientButton = new JButton("Add Client");
        addClientButton.addActionListener(e -> {
            switchActivitiy(this::add_client_screen);
        });

        JButton listClientsButton = new JButton("List Clients");
        listClientsButton.addActionListener(e -> {
            switchActivitiy(this::client_table_screen);
        });

        root.add(addItemButton);
        root.add(addClientButton);
        root.add(showItemsButton);
        root.add(borrowButton);
        root.add(listClientsButton);
        return root;
    }

    //#endregion


    //#region ITEMS
    JPanel add_book_screen() {
        JPanel root = new JPanel(new GridLayout(0, 1, 0, 0)); // One column, dynamic rows, with spacing

        JPanel actionsPanel = new JPanel();

        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Book Title");
        JTextField titleField = new JTextField();
        titleField.setPreferredSize(new DimensionUIResource(200, 30));
        titlePanel.add(titleLabel);
        titlePanel.add(titleField);

        JPanel authorPanel = new JPanel();
        JLabel authorLabel = new JLabel("Author");
        JTextField authorField = new JTextField();
        authorField.setPreferredSize(new DimensionUIResource(200, 30));
        authorPanel.add(authorLabel);
        authorPanel.add(authorField);

        JButton submitButton = new JButton("Add Book");
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> switchActivitiy(this::home_screen));
        submitButton.addActionListener(e -> {
            String title = titleField.getText();
            String author = authorField.getText();
            if (isTextFieldEmpty(titleField)) {
                Border border = BorderFactory.createLineBorder(Color.RED, 2);
                titleField.setBorder(border);
                return;
            }
            if (isTextFieldEmpty(authorField)) {
                Border border = BorderFactory.createLineBorder(Color.RED, 2);
                authorField.setBorder(border);
                return;
            }
            try {
                manager.addItem(new Book(title, author));
                switchActivitiy(this::home_screen);
            } catch (DuplicateIdException error) {
            }
        });
        actionsPanel.add(submitButton);
        actionsPanel.add(cancelButton);

        root.add(titlePanel);
        root.add(authorPanel);
        root.add(actionsPanel);

        return root;
    }

    JPanel items_table_screen() {
        JPanel root = new JPanel();

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            switchActivitiy(this::home_screen);
        });

        ArrayList<java.util.List<String>> data = new ArrayList<>();
        System.out.println(manager.items.size());
        manager.streamItems().forEach(item -> {
            System.out.println(item);
            String author = "UNKNOWN";
            if (item instanceof Book) {
                author = ((Book) item).author;
            }
            data.add(Arrays.asList(String.format("%d", item.id), item.title, author, String.format("%d", item.stock)));
        });
        String[] columnNames = {
                "ID",
                "title",
                "author",
                "stock"
        };

        JTable table = new JTable(liblib.Utils.to2DStringArray(data), columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        root.add(backButton);
        root.add(scrollPane);

        return root;
    }

    //#endregion


    //#region RELATIONS
    JPanel borrow_screen() {
        JPanel root = new JPanel(new GridLayout(0, 1, 0, 0)); // One column, dynamic rows, with spacing

        JLabel status = new JLabel();
        status.setSize(10, 10);

        JPanel clientPanel = new JPanel();
        JLabel clientLabel = new JLabel("Client");
        JTextField clientField = new JTextField();
        clientField.setPreferredSize(new DimensionUIResource(200, 30));
        clientPanel.add(clientLabel);
        clientPanel.add(clientField);

        JPanel itemPanel = new JPanel();
        JLabel itemLabel = new JLabel("Item");
        JTextField itemField = new JTextField();
        itemField.setPreferredSize(new DimensionUIResource(200, 30));
        itemPanel.add(itemLabel);
        itemPanel.add(itemField);

        JButton submitButton = new JButton("Borrow");
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> switchActivitiy(this::home_screen));
        submitButton.addActionListener(e -> {
            String client = clientField.getText();
            String item = itemField.getText();
            if (isTextFieldEmpty(clientField)) {
                Border border = BorderFactory.createLineBorder(Color.RED, 2);
                clientField.setBorder(border);
                return;
            }
            if (isTextFieldEmpty(itemField)) {
                Border border = BorderFactory.createLineBorder(Color.RED, 2);
                itemField.setBorder(border);
                return;
            }

            try {
                int client_int = Integer.parseInt(client);
                int item_int = Integer.parseInt(item);
                manager.borrowItem(client_int, item_int);
            } catch (NumberFormatException error) {
                status.setText("User or item id is not valid");
            } catch (RelationAlreadyExists error) {
                status.setText("User already borrowed this item");
            } catch (ItemNotFoundException error) {
                status.setText("Item with this id cannot be found");
            } catch (ItemNotAvailable error) {
                status.setText("No stock is available for this item");
            }

        });
        JPanel actionsPanel = new JPanel();
        actionsPanel.add(submitButton);
        actionsPanel.add(cancelButton);

        root.add(clientPanel);
        root.add(itemPanel);
        root.add(actionsPanel);
        root.add(status);

        return root;
    }
    //#endregion




    //#region CLIENT
    JPanel add_client_screen() {
        JPanel root = new JPanel(new GridLayout(0, 1, 0, 0));
        JLabel status = new JLabel();
        status.setForeground(Color.RED);

        JPanel namePanel = new JPanel();
        JLabel nameLabel = new JLabel("Client name");
        JTextField nameTextField = new JTextField();
        nameTextField.setPreferredSize(new DimensionUIResource(200, 30));
        namePanel.add(nameLabel);
        namePanel.add(nameTextField);

        JPanel agePanel = new JPanel();
        JLabel ageLabel = new JLabel("Client age");
        JTextField ageTextField = new JTextField();
        ageTextField.setPreferredSize(new DimensionUIResource(200, 30));
        agePanel.add(ageLabel);
        agePanel.add(ageTextField);

        JPanel emailPanel = new JPanel();
        JLabel emailLabel = new JLabel("Client email");
        JTextField emailTextField = new JTextField();
        emailTextField.setPreferredSize(new DimensionUIResource(200, 30));
        emailPanel.add(emailLabel);
        emailPanel.add(emailTextField);

        JButton submitButton = new JButton("Add Client");
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> switchActivitiy(this::home_screen));
        submitButton.addActionListener(e -> {
            String name = nameTextField.getText();
            String age = ageTextField.getText();
            String email = emailTextField.getText();
            if (isTextFieldEmpty(nameTextField)) {
                Border border = BorderFactory.createLineBorder(Color.RED, 2);
                nameTextField.setBorder(border);
                return;
            }
            if (isTextFieldEmpty(ageTextField)) {
                Border border = BorderFactory.createLineBorder(Color.RED, 2);
                ageTextField.setBorder(border);
                return;
            }
            if (isTextFieldEmpty(emailTextField)) {
                Border border = BorderFactory.createLineBorder(Color.RED, 2);
                emailTextField.setBorder(border);
                return;
            }
            if(!isValidEmail(email)){
                status.setText("Invalid email");
                return;
            }
            try {
                manager.addClient(new Client(name, Integer.parseInt(age), email));
                switchActivitiy(this::home_screen);
            } catch (NumberFormatException error) {
                status.setText("Age is not valid");
            } catch (ItemAlreadyExists error) {
                status.setText("Client with this email already exists");
            }
        });

        JPanel actionsPanel = new JPanel();
        actionsPanel.add(submitButton);
        actionsPanel.add(cancelButton);

        root.add(actionsPanel);
        root.add(agePanel);
        root.add(emailPanel);
        root.add(namePanel);

        root.add(status);

        return root;
    }

    JPanel client_table_screen() {
        JPanel root = new JPanel();

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            switchActivitiy(this::home_screen);
        });

        ArrayList<java.util.List<String>> data = new ArrayList<>();
        manager.clients.values().forEach(client -> {
            data.add(Arrays.asList(String.format("%d", client.getID()), client.getName(),
                    String.format("%d", client.getAge()), client.getEmail(), String.format("%d", manager.getRelationsByClientID(client.getID()).size())));
        });
        String[] columnNames = {
                "ID",
                "name",
                "age",
                "email",
                "borrowed"
        };

        JTable table = new JTable(liblib.Utils.to2DStringArray(data), columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.getDefaultEditor(Object.class).addCellEditorListener(new CellEditorListener() {
            public void editingStopped(ChangeEvent e) {
                int row = table.getSelectedRow();
                int col = table.getSelectedColumn();

                String edited_id = (String) table.getValueAt(row, 0);
                String new_value = (String) table.getValueAt(row, col);
                int id = Integer.parseInt(edited_id);
                String column_name = (String) table.getColumnName(col);
                Optional<Client> clientOpt = manager.getClientByID(id);
                Client client = clientOpt.get();
                // TODO handle case where client not found
                System.out.println(column_name);
                switch (column_name) {
                    case "name":
                        client.setName(new_value);
                        switchActivitiy(GUI.this::client_table_screen);
                        break;
                    case "age":
                        client.setAge(Integer.parseInt(new_value));
                        switchActivitiy(GUI.this::client_table_screen);
                        break;
                    default:
                        System.out.println("BAD COL");
                }

                System.out.format("%s\n", edited_id);

                if (row >= 0 && col >= 0) {
                    Object value = table.getValueAt(row, col);
                    System.out.println("Cell editing stopped: row=" + row + ", col=" + col + ", value=" + value);
                } else {
                    System.out.println("Cell editing stopped, but row or column is invalid.");
                }
            }

            public void editingCanceled(ChangeEvent e) {
            }
        });
        root.add(backButton);
        root.add(scrollPane);
        return root;
    }

    //#endregion

    

    //#region CTOR
    public GUI() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.manager = new Manager();

        try {
            manager.addClient(new Client("Mohammed Amr", 24, "next.mohammed.amr@gmail.com"));
            manager.addClient(new Client("Mohammed Amr", 24, "next.mohammed.amr@gmail.com"));
            // manager.addClient(new Client("Fares Edres", 24,
            // "next.mohammed.amr@gmail.com"));
            // manager.addClient(new Client("Osama Ismail", 24,
            // "next.mohammed.amr@gmail.com"));
            // manager.addClient(new Client("Mahmoud Elbanna", 24,
            // "next.mohammed.amr@gmail.com"));
            // manager.addClient(new Client("Ahmed Ramadan", 24,
            // "next.mohammed.amr@gmail.com"));

            manager.addItem(new Book("Noone can be found", "Me"));
            manager.addItem(new Book("MM", "Me"));
            manager.addItem(new Book("AA", "Me"));

            manager.borrowItem(0, 0);

        } catch (ItemAlreadyExists err) {
            System.out.println(err);
        } catch (DuplicateIdException error) {
            System.out.println(
                    error.getMessage() + "AA");
        } catch (ItemNotFoundException err) {
            System.out.println(err.getMessage());
        } catch (RelationAlreadyExists err) {
            System.out.println(err.getMessage());
        } catch (ItemNotAvailable err) {
            System.out.println(err.getClass().getName());
        }

        this.setBounds(50, 50, 600, 400);

        boolean CHANGE_ME_TO_START_DUMMY_UI = false;

        switchActivitiy(CHANGE_ME_TO_START_DUMMY_UI ? this::dummy_screen : this::home_screen);

        this.setVisible(true);

    }

}
