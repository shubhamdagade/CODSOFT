//Task5 - Address Book System

package Tasks;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}

class AddressBook implements Serializable {
    private List<Contact> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
    }

    public Contact searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(contacts);
            JOptionPane.showMessageDialog(null, "Address book saved successfully.", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving address book: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            contacts = (List<Contact>) ois.readObject();
            JOptionPane.showMessageDialog(null, "Address book loaded successfully.", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error loading address book: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

public class Task5_AddressBookSystem {
    private AddressBook addressBook;
    private JFrame frame;
    private JTextField nameField;
    private JTextField phoneNumberField;
    private JTextField emailAddressField;
    private JTextArea contactsTextArea;

    public Task5_AddressBookSystem() {
        addressBook = new AddressBook();
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Address Book System");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = createInputPanel();
        JPanel buttonsPanel = createButtonsPanel();
        contactsTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(contactsTextArea);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonsPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberField = new JTextField();
        JLabel emailAddressLabel = new JLabel("Email Address:");
        emailAddressField = new JTextField();

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(phoneNumberLabel);
        inputPanel.add(phoneNumberField);
        inputPanel.add(emailAddressLabel);
        inputPanel.add(emailAddressField);

        return inputPanel;
    }

    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();

        JButton addButton = new JButton("Add Contact");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phoneNumber = phoneNumberField.getText();
                String emailAddress = emailAddressField.getText();

                if (name.isEmpty() || phoneNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Name and Phone Number fields are required.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Contact contact = new Contact(name, phoneNumber, emailAddress);
                addressBook.addContact(contact);
                updateContactsTextArea();
                clearFields();
                JOptionPane.showMessageDialog(frame, "Contact added successfully.", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton removeButton = new JButton("Remove Contact");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                Contact contact = addressBook.searchContact(name);

                if (contact != null) {
                    addressBook.removeContact(contact);
                    updateContactsTextArea();
                    clearFields();
                    JOptionPane.showMessageDialog(frame, "Contact removed successfully.", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Contact not found.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton displayButton = new JButton("Display Contacts");
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateContactsTextArea();
            }
        });

        JButton saveButton = new JButton("Save Address Book");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showSaveDialog(frame);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String filename = fileChooser.getSelectedFile().getAbsolutePath();
                    addressBook.saveToFile(filename);
                }
            }
        });

        JButton loadButton = new JButton("Load Address Book");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(frame);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String filename = fileChooser.getSelectedFile().getAbsolutePath();
                    addressBook.loadFromFile(filename);
                    updateContactsTextArea();
                }
            }
        });

        buttonsPanel.add(addButton);
        buttonsPanel.add(removeButton);
        buttonsPanel.add(displayButton);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(loadButton);

        return buttonsPanel;
    }

    private void updateContactsTextArea() {
        List<Contact> contacts = addressBook.getAllContacts();
        StringBuilder sb = new StringBuilder();

        if (contacts.isEmpty()) {
            sb.append("No contacts found.");
        } else {
            sb.append("Contacts:\n");
            for (Contact contact : contacts) {
                sb.append("Name: ").append(contact.getName()).append(", ");
                sb.append("Phone Number: ").append(contact.getPhoneNumber()).append(", ");
                sb.append("Email Address: ").append(contact.getEmailAddress()).append("\n");
            }
        }

        contactsTextArea.setText(sb.toString());
    }

    private void clearFields() {
        nameField.setText("");
        phoneNumberField.setText("");
        emailAddressField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Task5_AddressBookSystem();
            }
        });
    }
}
