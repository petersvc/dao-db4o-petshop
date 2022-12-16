package model;

import java.util.ArrayList;

public class Employee extends Person {
    
    private ArrayList<ServiceOrder> serviceOrders;

    public Employee(String name, String document, String phone) {
        super(name, document, phone);
        serviceOrders = new ArrayList<ServiceOrder>();
    }

    public void addServiceOrder(ServiceOrder serviceOrder) {
        serviceOrders.add(serviceOrder);
    }

    public ArrayList<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    @Override
    public String toString() {
        return "Employee [name=" + getName() + ", document=" + getDocument() + ", phone=" + getPhone() + "]";
    }
}
