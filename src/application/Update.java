package application;

import busines.Facade;
import model.ServiceOrder;

public class Update {
    public Update() {
        System.out.println("Updating...");
        try {
            Facade.init();
            
            String oldEmployeeCPF = "098.654.321-00";
            String newEmployeeCPF = "634.456.789-00";
            
            int intServiceOrderID = 1;
            ServiceOrder serviceOrder = Facade.readServiceOrder(intServiceOrderID);

            System.out.println("[Before] Service Order: " + serviceOrder);
            Facade.changeEmployeeFromServiceOrder(oldEmployeeCPF, newEmployeeCPF, intServiceOrderID);
            System.out.println("[After] Service Order: " + serviceOrder);

            Facade.end();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Done!");
    }
    public static void main(String[] args) {
        new Update();
    }
}
