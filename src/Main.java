import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
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
public class Main {
    public static void main(String[] args) {
        Dispatcher bond_1 = new Dispatcher(
                "MDRail",
                "Chișinău",
                "+373(22)33-61-04",
                "Sorin Anghel",
                3,
                "Acvilin-Grup",
                "Cahul",
                "+373(552)5-29-54 ",
                "Consumer",
                "3 hours",
                "Strășeni",
                "Cantemir",
                40,
                "12.2.2018");
        ArrayList<Dispatcher> obj_list = new ArrayList<Dispatcher>();
        obj_list.add(bond_1);
        int i = 1;
        for(Dispatcher obj : obj_list){
            System.out.printf("%n%nObject Num: %d", i);
            obj.Show(obj);
            i++;
        }
    }
}
