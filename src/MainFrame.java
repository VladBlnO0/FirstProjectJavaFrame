import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class MainFrame extends JFrame {
    private JTabbedPane Create_DeletePane;
    private JTextField D_Phone_NumberTextField;
    private JTextField D_Full_NameTextField;
    private JTextField D_Company_NameTextField;
    private JTextField Con_WeightTextField;
    private JButton CreateButton;
    private JTextField D_AddressTextField;
    private JTextField D_Work_ExperienceTextField;
    private JTextField Cli_Company_NameTextField;
    private JTextField Cli_AddressTextField;
    private JTextField Cli_Phone_NumberTextField;
    private JComboBox Con_Type_of_CargoComboBox;
    private JTextField Con_Delivery_TimeTextField;
    private JTextField Con_Dispatch_StationTextField;
    private JTextField Con_Arrival_StationTextField;
    private JTextField Con_Date_of_ConclusionTextField;
    private JButton DeleteButton;
    private JComboBox Classes_ComboBox;
    private JPanel myFrame;

    interface Printable
    {
        void Show(Person obj);
    }
    class Cost_Calc
    {
        private static double tariff = 1.5;
        public double getCost(double weight) {
            return weight * tariff;
        }
    }
    class Insurance_Amount_Calc
    {
        private String[] Cargo_List = {"Consumer", "Special", "Hazardous"};
        public double Insurance_Calc(String cargo, double cost)
        {
            return switch (cargo) {
                case "Consumer" -> cost / 25;
                case "Special" -> cost / 35;
                case "Hazardous" -> cost / 50;
                default -> cost / 15;
            };
        }
    }
    abstract class Person {
        private String Company_Name;
        public String getCompany_Name() {
            return Company_Name;
        }
        private String Address;
        public String getAddress() {
            return Address;
        }
        private String Phone_Number;
        public String getPhone_Number() {
            return Phone_Number;
        }
        Person(String Company_Name, String Address, String Phone_Number) {
            this.Company_Name = Company_Name;
            this.Address = Address;
            this.Phone_Number = Phone_Number;
        }
    }
    class Dispatcher extends Person implements Printable {
        private Client client;
        private String Full_Name;
        private int Work_Experience;
        Dispatcher(String D_Company_Name,
                   String D_Address,
                   String D_Phone_Number,
                   String Full_Name,
                   int Work_Experience,
                   String C_Company_Name,
                   String C_Address,
                   String C_Phone_Number,
                   String Type_of_Cargo,
                   String Delivery_Time,
                   String Dispatch_Station,
                   String Arrival_Station,
                   double Weight,
                   String Date_of_Conclusion)
        {
            super(D_Company_Name, D_Address, D_Phone_Number);
            this.Full_Name = Full_Name;
            this.Work_Experience = Work_Experience;
            this.client = new Client(C_Company_Name,
                    C_Address,
                    C_Phone_Number,
                    Type_of_Cargo,
                    Delivery_Time,
                    Dispatch_Station,
                    Arrival_Station,
                    Weight,
                    Date_of_Conclusion);
        }
        public void Show(Person obj)
        {
            System.out.printf("%n%nDispatcher%nCompany name: %s", obj.getCompany_Name());
            System.out.printf("%nDispatcher name: %s", Full_Name);
            System.out.printf("%nAddress: %s", obj.getAddress());
            System.out.printf("%nPhone number: %s", obj.getPhone_Number());
            System.out.printf("%nWork experience: %s", Work_Experience);
            client.Show(obj);
        }
    }
    class Client extends Person implements Printable {
        private Contract contr;
        public int Contract_Id;
        Client(String Company_Name,
               String Address,
               String Phone_Number,
               String Type_of_Cargo,
               String Delivery_Time,
               String Dispatch_Station,
               String Arrival_Station,
               double Weight,
               String Date_of_Conclusion)
        {
            super(Company_Name, Address, Phone_Number);
            this.Contract_Id = ThreadLocalRandom.current().nextInt(1000, 8000 + 1);
            this.contr = new Contract(Type_of_Cargo,
                    Delivery_Time,
                    Dispatch_Station,
                    Arrival_Station,
                    Weight,
                    Date_of_Conclusion,
                    Contract_Id);
        }
        public void Show(Person obj)
        {
            System.out.printf("%n%nClient%nCompany name: %s", obj.getCompany_Name());
            System.out.printf("%nAddress: %s", obj.getAddress());
            System.out.printf("%nPhone number: %s", obj.getPhone_Number());
            System.out.printf("%nContract Id: %d", Contract_Id);
            contr.Info();
        }

        public int getContract_Id() {
            return Contract_Id;
        }
    }
    class Contract {
        private double Insurance_Amount;
        private String Type_of_Cargo;
        private String Delivery_Time;
        private String Dispatch_Station;
        private String Arrival_Station;
        private double Cost;
        private double Weight;
        private String Date_of_Conclusion;
        public int Contract_Id;
        public Contract
                (
                        String Type_of_Cargo,
                        String Delivery_Time,
                        String Dispatch_Station,
                        String Arrival_Station,
                        double Weight,
                        String Date_of_Conclusion,
                        int Contract_Id
                )
        {
            Cost_Calc calc1 = new Cost_Calc();
            Insurance_Amount_Calc calc2 = new Insurance_Amount_Calc();
            this.Type_of_Cargo = Type_of_Cargo;
            this.Delivery_Time = Delivery_Time;
            this.Dispatch_Station = Dispatch_Station;
            this.Arrival_Station = Arrival_Station;
            this.Weight = Weight;
            this.Cost = calc1.getCost(Weight);
            this.Insurance_Amount = calc2.Insurance_Calc(Type_of_Cargo, this.Cost);
            this.Date_of_Conclusion = Date_of_Conclusion;
            this.Contract_Id = Contract_Id;
        }
        void Info()
        {
            System.out.printf("%n%nContract%nInsurance amount: %4.2f", Insurance_Amount);
            System.out.printf("%nType of Cargo: %s", Type_of_Cargo);
            System.out.printf("%nDelivery time: %s", Delivery_Time);
            System.out.printf("%nDispatch station: %s", Dispatch_Station);
            System.out.printf("%nArrival station: %s", Arrival_Station);
            System.out.printf("%nCost: %4.2f", Cost);
            System.out.printf("%nWeight: %4.2f", Weight);
            System.out.printf("%nDate of Conclusion: %s", Date_of_Conclusion);
            System.out.printf("%nContract Id: %d", Contract_Id);
            System.out.printf("%n--------------------------------");
        }
    }
//            Dispatcher bond_1 = new Dispatcher(
//                    "MDRail",
//                    "Chișinău",
//                    "+373(22)33-61-04",
//                    "Sorin Anghel",
//                    3,
//                    "Acvilin-Grup",
//                    "Cahul",
//                    "+373(552)5-29-54 ",
//                    "Consumer",
//                    "3 hours",
//                    "Strășeni",
//                    "Cantemir",
//                    40,
//                    "12.2.2018");
//            obj_list.add(bond_1);
//            int i = 1;
//            for(Dispatcher obj : obj_list)
//
//    {
//        System.out.printf("%n%nObject Num: %d", i);
//        obj.Show(obj);
//        i++;
//    }

    ArrayList<Dispatcher> dispatchers = new ArrayList<Dispatcher>();
    public MainFrame(){
        setContentPane(myFrame);
        setTitle("Rail Transport");
        setSize(500,700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        CreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String D_Company_Name = D_Company_NameTextField.getText();
                String D_Address = D_AddressTextField.getText();
                String D_Phone_Number = D_Phone_NumberTextField.getText();
                String D_Full_Name = D_Full_NameTextField.getText();
                int D_Work_Experience = Integer.parseInt(D_Work_ExperienceTextField.getText());
                String Cli_Company_Name = Cli_Company_NameTextField.getText();
                String Cli_Address = Cli_AddressTextField.getText();
                String Cli_Phone_Number = Cli_Phone_NumberTextField.getText();
                String Con_Type_of_Cargo = String.valueOf(Con_Type_of_CargoComboBox.getSelectedItem());
                String Con_Delivery_Time = Con_Delivery_TimeTextField.getText();
                String Con_Dispatch_Station = Con_Dispatch_StationTextField.getText();
                String Con_Arrival_Station = Con_Arrival_StationTextField.getText();
                Double Con_Weight = Double.valueOf(Con_WeightTextField.getText());
                String Con_Date_of_Conclusion = Con_Date_of_ConclusionTextField.getText();
                Dispatcher new_disp = new Dispatcher(D_Company_Name,D_Address,D_Phone_Number,D_Full_Name,D_Work_Experience,
                        Cli_Company_Name, Cli_Address, Cli_Phone_Number,
                        Con_Type_of_Cargo,Con_Delivery_Time,Con_Dispatch_Station,Con_Arrival_Station,Con_Weight,Con_Date_of_Conclusion);
                dispatchers.add(new_disp);
                Classes_ComboBox.addItem(new_disp.client.getContract_Id());
            }
        });
//        Classes_ComboBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource() == Classes_ComboBox) {
//                    nameField.setText(tools.get(Classes_ComboBox.getSelectedIndex()).getName());
//                    costField.setText(Double.toString(tools.get(Classes_ComboBox.getSelectedIndex()).getCost()));
//                    ONRadioButton.setSelected(tools.get(Classes_ComboBox.getSelectedIndex()).getState());
//                    nameProdField.setText(tools.get(Classes_ComboBox.getSelectedIndex()).prod.getProdName());
//                    amountTextField.setText(Integer.toString(tools.get(Classes_ComboBox.getSelectedIndex()).prod.getProdAmount()));
//                    supplierTextField.setText(tools.get(Classes_ComboBox.getSelectedIndex()).prod.getProdSupplier());
//                }
//            }
//        });
    }
    public static void main(String[] args){
        MainFrame myFrame = new MainFrame();
    }
}


