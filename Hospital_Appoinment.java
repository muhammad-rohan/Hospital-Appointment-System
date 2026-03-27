import javax.swing.*;
import java.awt.*;
public class Hospital_Appoinment extends JFrame{
    private CardLayout Layout;
    private JPanel Panel;
    private JTextField nameField, ageField;
    private JComboBox<String> genderCombo,specializationCombo,doctorCombo,timeCombo;
    private JLabel feeLabel;
    private JTextArea receiptArea;
    private Color HeadingColor=new Color(41,128,185);
    private Color buttonColor=new Color(52,152,219);
    public Hospital_Appoinment (){
        setTitle("Hospital Appointment System");
        setSize(500,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel mainP=new JPanel(new BorderLayout());
        JLabel title=new JLabel("Hospital Appointment System",JLabel.CENTER);
        title.setFont(new Font("Arial",Font.BOLD,24));
        title.setBackground(HeadingColor);
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBorder(BorderFactory.createEmptyBorder(15,0,15,0));
        mainP.add(title,BorderLayout.NORTH);
        Layout=new CardLayout();
        Panel=new JPanel(Layout);
        Panel.add(RegScreen(),"Screen1");
        Panel.add(AppoinmentScreen(),"Screen2");
        Panel.add(ReciptScreen(),"Screen3");
        mainP.add(Panel,BorderLayout.CENTER);
        add(mainP);
        setVisible(true);
    }
    private JPanel RegScreen(){
        JPanel obj1=new JPanel(new GridLayout(5,2,15,15));
        obj1.setBackground(Color.WHITE);
        obj1.setBorder(BorderFactory.createEmptyBorder(30,40,30,40));
        obj1.add(createLabel("Name:"));
        nameField=new JTextField();
        nameField.setFont(new Font("Arial",Font.PLAIN,14));
        obj1.add(nameField);
        obj1.add(createLabel("Age:"));
        ageField=new JTextField(); // <-- make ageField class-level
        ageField.setFont(new Font("Arial",Font.PLAIN,14));
        obj1.add(ageField);
        obj1.add(createLabel("Gender:"));
        genderCombo=new JComboBox<>(new String[]{"Male","Female","Others"});
        genderCombo.setFont(new Font("Arial",Font.PLAIN,14));
        obj1.add(genderCombo);
        obj1.add(new JLabel());
        JButton next_button=new JButton("Next");
        next_button.setFont(new Font("Arial",Font.BOLD,14));
        next_button.setBackground(buttonColor);
        next_button.setForeground(Color.WHITE);
        next_button.setFocusPainted(false);
        next_button.addActionListener(e->{
            if(nameField.getText().isEmpty()||ageField.getText().isEmpty())
                JOptionPane.showMessageDialog(null,"Please fill all fields!");
            else
                Layout.show(Panel,"Screen2");
        });
        obj1.add(next_button);
        return obj1;
    }
    private JPanel AppoinmentScreen(){
        JPanel obj2=new JPanel(new GridLayout(6,2,15,15));
        obj2.setBackground(Color.WHITE);
        obj2.setBorder(BorderFactory.createEmptyBorder(30,40,30,40));
        obj2.add(createLabel("Specialization:"));
        specializationCombo=new JComboBox<>(new String[]{"Genaral","Neurology","Cardiology","Orthopedics"});
        specializationCombo.setFont(new Font("Arial",Font.PLAIN,14));
        obj2.add(specializationCombo);
        obj2.add(createLabel("Doctor Name:"));
        doctorCombo=new JComboBox<>(new String[]{"Dr. Shuvo","Dr. Rahat","Dr. Omor","Dr. Johirul"});
        doctorCombo.setFont(new Font("Arial",Font.PLAIN,14));
        obj2.add(doctorCombo);
        obj2.add(createLabel("Time Slot:"));
        timeCombo=new JComboBox<>(new String[]{"10:00 AM","2:00 PM","4:30 PM"});
        timeCombo.setFont(new Font("Arial",Font.PLAIN,14));
        obj2.add(timeCombo);
        obj2.add(createLabel("Consultation Fee:"));
        feeLabel=new JLabel("Select specialization");
        feeLabel.setFont(new Font("Arial",Font.PLAIN,14));
        obj2.add(feeLabel);
        specializationCombo.addActionListener(e->{
            String s=(String)specializationCombo.getSelectedItem();
            String fee="300 BDT";
            if("Neurology".equals(s)){
                fee="1000 BDT";
                doctorCombo.setModel(new DefaultComboBoxModel<>(new String[]{"Dr. Shuvo","Dr. Rahat"}));
            }
            else if("Cardiology".equals(s)){
                fee="500 BDT";
                doctorCombo.setModel(new DefaultComboBoxModel<>(new String[]{"Dr. Omor","Dr. Johirul"}));
            }
            else if("Orthopedics".equals(s)){
                fee="800 BDT";
                doctorCombo.setModel(new DefaultComboBoxModel<>(new String[]{"Dr. Sumon","Dr. Hasan"}));
            }
            else{
                doctorCombo.setModel(new DefaultComboBoxModel<>(new String[]{"Dr. Ratul","Dr. Simmin"}));
            }
            feeLabel.setText(fee);
        });
        JButton back_button=new JButton("Back");
        back_button.setFont(new Font("Arial",Font.BOLD,14));
        back_button.setBackground(new Color(230,126,34));
        back_button.setForeground(Color.WHITE);
        back_button.setFocusPainted(false);
        back_button.addActionListener(e->Layout.show(Panel,"Screen1"));
        obj2.add(back_button);
        JButton next_button=new JButton("Next");
        next_button.setFont(new Font("Arial",Font.BOLD,14));
        next_button.setBackground(buttonColor);
        next_button.setForeground(Color.WHITE);
        next_button.setFocusPainted(false);
        next_button.addActionListener(e->{
            String recipt=
                            "  APPOINTMENT CONFIRMATION            \n"+
                            "                                      \n"+
                            " Name: "+nameField.getText()+"\n"+
                            " Age: "+ageField.getText()+"\n"+
                            " Gender: "+genderCombo.getSelectedItem()+"\n"+
                            " Specialization: "+specializationCombo.getSelectedItem()+"\n"+
                            " Doctor: "+doctorCombo.getSelectedItem()+"\n"+
                            " Time: "+timeCombo.getSelectedItem()+"\n"+
                            " Fee: "+feeLabel.getText()+"\n"+
                            "Thank you for booking!";
            receiptArea.setText(recipt);
            Layout.show(Panel,"Screen3");
        });
        obj2.add(next_button);
        return obj2;
    }
    private JPanel ReciptScreen(){
        JPanel obj3=new JPanel(new BorderLayout(10,10));
        obj3.setBackground(Color.WHITE);
        obj3.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));

        receiptArea=new JTextArea(); // <-- class-level variable
        receiptArea.setFont(new Font("Monospaced",Font.PLAIN,13));
        receiptArea.setEditable(false);
        receiptArea.setLineWrap(true);
        receiptArea.setWrapStyleWord(true);
        obj3.add(new JScrollPane(receiptArea),BorderLayout.CENTER);
        JPanel buttonPanel=new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        JButton back_button=new JButton("Back");
        back_button.setFont(new Font("Arial",Font.BOLD,14));
        back_button.setBackground(new Color(230,126,34));
        back_button.setForeground(Color.WHITE);
        back_button.setFocusPainted(false);
        back_button.addActionListener(e->Layout.show(Panel,"Screen2"));
        buttonPanel.add(back_button);

        JButton finis_button=new JButton("Finish");
        finis_button.setFont(new Font("Arial",Font.BOLD,14));
        finis_button.setBackground(new Color(46,204,113));
        finis_button.setForeground(Color.WHITE);
        finis_button.setFocusPainted(false);
        finis_button.addActionListener(e->System.exit(0));
        buttonPanel.add(finis_button);
        JButton new_button=new JButton("New Appointment");
        new_button.setFont(new Font("Arial",Font.BOLD,14));
        new_button.setBackground(buttonColor);
        new_button.setForeground(Color.WHITE);
        new_button.setFocusPainted(false);
        new_button.addActionListener(e->{
            nameField.setText("");
            ageField.setText(""); // <-- reset age
            specializationCombo.setSelectedIndex(0);
            doctorCombo.setSelectedIndex(0);
            timeCombo.setSelectedIndex(0);
            feeLabel.setText("Select specialization");
            Layout.show(Panel,"Screen1");
        });
        buttonPanel.add(new_button);
        obj3.add(buttonPanel,BorderLayout.SOUTH);
        return obj3;
    }
    private JLabel createLabel(String text){
        JLabel label=new JLabel(text);
        label.setFont(new Font("Arial",Font.BOLD,14));
        label.setForeground(HeadingColor);
        return label;
    }
    public static void main(String[]args) {
        new Hospital_Appoinment();
    }
}