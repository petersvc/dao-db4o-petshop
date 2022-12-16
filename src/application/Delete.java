package application;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import daodb4o.Util;
import model.Breed;
import model.Pet;
import model.Service;
import model.ServiceOrder;
import model.Status;
import model.Tutor;

public class Delete {

    protected ObjectContainer manager;

    public Delete() {
        manager = Util.createManager();
        delete();
        manager.close();
    }

    private void delete() {
        deleteTutor();
        deletePet();
        deleteBreed();
        deleteStatus();
        deleteService();
        deleteServiceOrder();
    }
    
    private void deleteTutor() {
        System.out.println("Deleting tutor...");
        ObjectSet<Tutor> tutors = manager.query(Tutor.class);
        manager.delete(tutors.next());
    }

    private void deletePet() {
        System.out.println("Deleting pet...");
        ObjectSet<Pet> pets = manager.query(Pet.class);
        manager.delete(pets.next());
    }

    private void deleteBreed() {
        System.out.println("Deleting breed...");
        ObjectSet<Breed> breeds = manager.query(Breed.class);
        manager.delete(breeds.next());
    }

    private void deleteStatus() {
        System.out.println("Deleting status...");
        ObjectSet<Status> statuses = manager.query(Status.class);
        manager.delete(statuses.next());
    }

    private void deleteService() {
        System.out.println("Deleting service...");
        ObjectSet<Service> services = manager.query(Service.class);
        manager.delete(services.next());
    }

    private void deleteServiceOrder() {
        System.out.println("Deleting service order...");
        ObjectSet<ServiceOrder> serviceOrders = manager.query(ServiceOrder.class);
        manager.delete(serviceOrders.next());
    }

    public static void main(String[] args) {
        new Delete();
        System.out.print("Done");
    }

}
