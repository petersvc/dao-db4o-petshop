package application;

import java.util.List;

import busines.Facade;
import model.Breed;
import model.Pet;
import model.Status;
import model.Tutor;
import model.Service;
import model.Employee;
import model.ServiceOrder;

public class ReadAll {

    public ReadAll() {
        Facade.init();
        readAll();
        Facade.end();
    }

    private void readAll() {
        readAllTutors();
        printDivider();
        readAllPets();
        printDivider();
        readAllBreeds();
        printDivider();
        readAllStatus();
        printDivider();
        readAllServices();
        printDivider();
        readAllEmployees();
        printDivider();
        readAllServiceOrders();
    }
    
    private void printDivider() {
        System.out.println("====================================");
    }

    private void readAllTutors() {
        System.out.println("Tutors:");
        List<Tutor> tutors = Facade.readAllTutors();
        for (Tutor tutor : tutors) {
            System.out.println(tutor);
        }
    }

    private void readAllPets() {
        System.out.println("Pets:");
        List<Pet> pets = Facade.readAllPets();
        for (Pet pet : pets) {
            System.out.println(pet);
        }
    }

    private void readAllBreeds() {
        System.out.println("Breeds:");
        List<Breed> breeds = Facade.readAllBreeds();
        for (Breed breed : breeds) {
            System.out.println(breed);
        }
    }

    private void readAllStatus() {
        System.out.println("Status:");
        List<Status> status = Facade.readAllStatus();
        for (Status s : status) {
            System.out.println(s);
        }
    }

    private void readAllServices() {
        System.out.println("Services:");
        List<Service> services = Facade.readAllServices();
        for (Service service : services) {
            System.out.println(service);
        }
    }

    private void readAllEmployees() {
        System.out.println("Employees:");
        List<Employee> employees = Facade.readAllEmployees();
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    private void readAllServiceOrders() {
        System.out.println("Service Orders:");
        List<ServiceOrder> serviceOrders = Facade.readAllServiceOrders();
        for (ServiceOrder serviceOrder : serviceOrders) {
            System.out.println(serviceOrder);
        }
    }
    public static void main(String[] args) {
        new ReadAll();
    }
}
