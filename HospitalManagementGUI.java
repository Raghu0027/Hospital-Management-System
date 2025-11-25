import java.awt.*;
import javax.swing.*;

public class HospitalManagementGUI extends JFrame {
    private Management hospitalManagement;
    private JTabbedPane tabbedPane;

    // Department Panel Components
    private JTextField deptNameField, deptHeadField, deptStaffField;
    private JTextArea deptDisplayArea;
    private JButton addDeptButton;

    // Ambulance Panel Components
    private JTextField ambIdField, ambDriverField;
    private JCheckBox ambAvailableCheckbox;
    private JTextArea ambDisplayArea;
    private JButton addAmbButton;

    // Patient Panel Components
    private JTextField patientIdField, patientNameField, patientAgeField, patientIllnessField;
    private JTextArea patientDisplayArea;
    private JButton addPatientButton, assignAmbButton;
    private JTextField patientIdToAssignAmbField;

    public HospitalManagementGUI() {
        hospitalManagement = new Management();
        initializeSampleData();

        setTitle("Hospital Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        add(tabbedPane);

        // Create and add panels for each section
        createDepartmentPanel();
        createAmbulancePanel();
        createPatientPanel();

        setVisible(true);
    }

    private void initializeSampleData() {
        // Add some initial data
        hospitalManagement.addDepartment(new Department("Cardiology", "Dr. Smith", 15));
        hospitalManagement.addDepartment(new Department("Emergency", "Dr. Jones", 20));
        hospitalManagement.addAmbulance(new Ambulance("AMB101", "John Doe"));
        hospitalManagement.addAmbulance(new Ambulance("AMB102", "Jane Smith"));
        hospitalManagement.addPatient(new Patient("P001", "Alice", 30, "Fever"));
        hospitalManagement.addPatient(new Patient("P002", "Bob", 50, "Heart Attack"));
    }

    private void createDepartmentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        tabbedPane.addTab("Departments", panel);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Name:"));
        deptNameField = new JTextField();
        inputPanel.add(deptNameField);
        inputPanel.add(new JLabel("Head:"));
        deptHeadField = new JTextField();
        inputPanel.add(deptHeadField);
        inputPanel.add(new JLabel("Staff Count:"));
        deptStaffField = new JTextField();
        inputPanel.add(deptStaffField);
        addDeptButton = new JButton("Add Department");
        inputPanel.add(addDeptButton);

        panel.add(inputPanel, BorderLayout.NORTH);

        deptDisplayArea = new JTextArea(15, 50);
        deptDisplayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(deptDisplayArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        addDeptButton.addActionListener(e -> {
            String name = deptNameField.getText();
            String head = deptHeadField.getText();
            int staff = Integer.parseInt(deptStaffField.getText());
            hospitalManagement.addDepartment(new Department(name, head, staff));
            updateDepartmentDisplay();
        });

        updateDepartmentDisplay();
    }

    private void updateDepartmentDisplay() {
        deptDisplayArea.setText("Departments:\n");
        for (Department dept : hospitalManagement.getDepartments()) {
            deptDisplayArea.append("Name: " + dept.getName() + ", Head: " + dept.getHead() + ", Staff: " + dept.getNumberOfStaff() + "\n");
        }
    }

    private void createAmbulancePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        tabbedPane.addTab("Ambulances", panel);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("ID:"));
        ambIdField = new JTextField();
        inputPanel.add(ambIdField);
        inputPanel.add(new JLabel("Driver Name:"));
        ambDriverField = new JTextField();
        inputPanel.add(ambDriverField);
        ambAvailableCheckbox = new JCheckBox("Available", true);
        inputPanel.add(ambAvailableCheckbox);
        addAmbButton = new JButton("Add Ambulance");
        inputPanel.add(addAmbButton);

        panel.add(inputPanel, BorderLayout.NORTH);

        ambDisplayArea = new JTextArea(15, 50);
        ambDisplayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(ambDisplayArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        addAmbButton.addActionListener(e -> {
            String id = ambIdField.getText();
            String driver = ambDriverField.getText();
            Ambulance newAmb = new Ambulance(id, driver);
            newAmb.setAvailable(ambAvailableCheckbox.isSelected());
            hospitalManagement.addAmbulance(newAmb);
            updateAmbulanceDisplay();
        });

        updateAmbulanceDisplay();
    }

    private void updateAmbulanceDisplay() {
        ambDisplayArea.setText("Ambulances:\n");
        for (Ambulance amb : hospitalManagement.getAmbulances()) {
            ambDisplayArea.append("ID: " + amb.getId() + ", Driver: " + amb.getDriverName() + ", Available: " + amb.isAvailable() + "\n");
        }
    }

    private void createPatientPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        tabbedPane.addTab("Patients", panel);

        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(new JLabel("Patient ID:"));
        patientIdField = new JTextField();
        inputPanel.add(patientIdField);
        inputPanel.add(new JLabel("Name:"));
        patientNameField = new JTextField();
        inputPanel.add(patientNameField);
        inputPanel.add(new JLabel("Age:"));
        patientAgeField = new JTextField();
        inputPanel.add(patientAgeField);
        inputPanel.add(new JLabel("Illness:"));
        patientIllnessField = new JTextField();
        inputPanel.add(patientIllnessField);
        addPatientButton = new JButton("Add Patient");
        inputPanel.add(addPatientButton);

        JPanel assignAmbPanel = new JPanel(new FlowLayout());
        assignAmbPanel.add(new JLabel("Assign Ambulance to Patient ID:"));
        patientIdToAssignAmbField = new JTextField(10);
        assignAmbPanel.add(patientIdToAssignAmbField);
        assignAmbButton = new JButton("Assign Ambulance");
        assignAmbPanel.add(assignAmbButton);
        inputPanel.add(assignAmbPanel);


        panel.add(inputPanel, BorderLayout.NORTH);

        patientDisplayArea = new JTextArea(15, 50);
        patientDisplayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(patientDisplayArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        addPatientButton.addActionListener(e -> {
            String id = patientIdField.getText();
            String name = patientNameField.getText();
            int age = Integer.parseInt(patientAgeField.getText());
            String illness = patientIllnessField.getText();
            hospitalManagement.addPatient(new Patient(id, name, age, illness));
            updatePatientDisplay();
        });

        assignAmbButton.addActionListener(e -> {
            String patientId = patientIdToAssignAmbField.getText();
            Patient patient = hospitalManagement.getPatientById(patientId);
            if (patient != null) {
                hospitalManagement.assignAmbulanceToPatient(patient);
                updateAmbulanceDisplay(); // Update ambulance status after assignment
                updatePatientDisplay();
            } else {
                JOptionPane.showMessageDialog(null, "Patient not found with ID: " + patientId);
            }
        });

        updatePatientDisplay();
    }

    private void updatePatientDisplay() {
        patientDisplayArea.setText("Patients:\n");
        for (Patient patient : hospitalManagement.getPatients()) {
            patientDisplayArea.append("ID: " + patient.getPatientId() + ", Name: " + patient.getName() + ", Age: " + patient.getAge() + ", Illness: " + patient.getIllness() + "\n");
        }
    }

    public static void main(String[] args) {
        // This main is for testing HospitalManagementGUI directly
        new HospitalManagementGUI();
    }
}
