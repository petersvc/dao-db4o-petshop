package busines;

import java.util.List;

import daodb4o.DAO;
import daodb4o.DAOPet;
import daodb4o.DAOBreed;
import daodb4o.DAOTutor;
import daodb4o.DAOStatus;
import daodb4o.DAOService;
import daodb4o.DAOEmployee;
import daodb4o.DAOServiceOrder;
import model.Pet;
import model.Breed;
import model.Tutor;
import model.Status;
import model.Service;
import model.Employee;
import model.ServiceOrder;

public class Facade {
    private Facade() {
    }

    private static DAOBreed daoBreed = new DAOBreed();
    private static DAOPet daoPet = new DAOPet();
    private static DAOTutor daoTutor = new DAOTutor();
    private static DAOStatus daoStatus = new DAOStatus();
    private static DAOService daoService = new DAOService();
    private static DAOEmployee daoEmployee = new DAOEmployee();
    private static DAOServiceOrder daoServiceOrder = new DAOServiceOrder();

    public static void init() {
        DAO.open();
    }

    public static void end() {
        DAO.close();
    }

    public static void createBreed(String name) throws Exception {
        if (daoBreed.read(name) == null) {
            daoBreed.create(new Breed(name));
        } else {
            throw new Exception("Breed already exists");
        }
    }

    public static List<Breed> readAllBreeds() {
        return daoBreed.readAll();
    }

    public static void createPet(String name, String breedName, double weight) throws Exception {
        Breed breed = daoBreed.read(breedName);
        if (breed != null) {
            if (daoPet.readByName(name) == null) {
            	daoPet.create(new Pet(name, breed, weight));
            } else {
                throw new Exception("Pet Already exist");
            }
        } else {
            throw new Exception("Breed not found");
        }
        
    }
    
    public static void deletePet(String name) throws Exception {
        Pet pet = daoPet.readByName(name);
        if (pet != null) {
            daoPet.delete(pet);
        } else {
            throw new Exception("Pet not found");
        }
    }
    
    public static void updatePetWeight(String name, double weight) throws Exception {
        Pet p = daoPet.readByName(name);
        if (p != null) {
            p.setWeight(weight);
            daoPet.update(p);
        } else {
            throw new Exception("Pet not found");
        }        
    }
    
    public static List<Pet> readAllPets() {
        List<Pet> pets = daoPet.readAll();
        return pets;
    }

    public static void createTutor(String name, String cpf, String phone) throws Exception {
        if (daoTutor.read(cpf) == null) {
            daoTutor.create(new Tutor(name, cpf, phone));
        } else {
            throw new Exception("Tutor already exists");
        }
        
    }

    public static List<Tutor> readAllTutors() {
        return daoTutor.readAll();
    }

    public static void addPetToTutor(int petId, String tutorCPF) throws Exception {
        Pet pet = daoPet.read(petId);
        if (pet == null) {
            throw new Exception("Pet with id " + petId + " not found");
        }
        Tutor tutor = daoTutor.read(tutorCPF);
        if (tutor == null) {
            throw new Exception("Tutor with cpf " + tutorCPF + " not found");
        }
        if (tutor.getPets().contains(pet)) {
            throw new Exception("Pet with id " + petId + " already belongs to tutor with cpf " + tutorCPF);
        }
        tutor.addPet(pet);
        daoTutor.update(tutor);
    }

    public static void createStatus(String name) throws Exception {
        if (daoStatus.read(name) == null) {
            daoStatus.create(new Status(name));
        } else {
            throw new Exception("Status already exists");
        }
    }

    public static List<Status> readAllStatus() {
        return daoStatus.readAll();
    }

    public static void createService(String name, double price) throws Exception {
        if (daoService.read(name) == null) {
            daoService.create(new Service(name, price));
        } else {
            throw new Exception("Service already exists");
        }
    }

    public static List<Service> readAllServices() {
        return daoService.readAll();
    }

    public static void createEmployee(String name, String cpf, String phone) throws Exception {
        if (daoEmployee.read(cpf) == null) {
            daoEmployee.create(new Employee(name, cpf, phone));
        } else {
            throw new Exception("Employee already exists");
        }
    }

    public static List<Employee> readAllEmployees() {
        return daoEmployee.readAll();
    }

    public static void createServiceOrder(String tutorCPF, int petId, String statusName, String employeeCPF)
            throws Exception {
        Tutor tutor = daoTutor.read(tutorCPF);
        if (tutor == null) {
            throw new Exception("Tutor with cpf " + tutorCPF + " not found");
        }
        Pet pet = daoPet.read(petId);
        if (pet == null) {
            throw new Exception("Pet with id " + petId + " not found");
        }
        Status status = daoStatus.read(statusName);
        if (status == null) {
            throw new Exception("Status with name " + statusName + " not found");
        }
        Employee employee = daoEmployee.read(employeeCPF);
        if (employee == null) {
            throw new Exception("Employee with cpf " + employeeCPF + " not found");
        }

        ServiceOrder serviceOrder = new ServiceOrder(tutor, pet, status, employee);
        daoServiceOrder.create(serviceOrder);
    }

    public static List<ServiceOrder> readAllServiceOrders() {
        return daoServiceOrder.readAll();
    }

    public static ServiceOrder readServiceOrder(int id) {
        return daoServiceOrder.read(id);
    }

    public static void addServiceToServiceOrder(String serviceName, int serviceOrderId) throws Exception {
        Service service = daoService.read(serviceName);
        if (service == null) {
            throw new Exception("Service with name " + serviceName + " not found");
        }
        ServiceOrder serviceOrder = daoServiceOrder.read(serviceOrderId);
        if (serviceOrder == null) {
            throw new Exception("ServiceOrder with id " + serviceOrderId + " not found");
        }
        serviceOrder.addService(service);
        daoServiceOrder.update(serviceOrder);
    }

    public static void changeEmployeeFromServiceOrder(String oldEmployeeCPF, String newEmployeeCPF, int serviceOrderId)
            throws Exception {
        Employee oldEmployee = daoEmployee.read(oldEmployeeCPF);
        if (oldEmployee == null) {
            throw new Exception("Employee with cpf " + oldEmployeeCPF + " not found");
        }
        Employee newEmployee = daoEmployee.read(newEmployeeCPF);
        if (newEmployee == null) {
            throw new Exception("Employee with cpf " + newEmployeeCPF + " not found");
        }
        ServiceOrder serviceOrder = daoServiceOrder.read(serviceOrderId);
        if (serviceOrder == null) {
            throw new Exception("ServiceOrder with id " + serviceOrderId + " not found");
        }

        if (serviceOrder.getEmployee().getDocument().equals(newEmployee.getDocument())) {
            throw new Exception("ServiceOrder already has employee with cpf " + newEmployee.getDocument());
        }

        serviceOrder.setEmployee(newEmployee);
        daoServiceOrder.update(serviceOrder);
    }

    public static void cleanUpDatabase() {
        daoBreed.deleteAll();
        daoPet.deleteAll();
        daoTutor.deleteAll();
        daoStatus.deleteAll();
        daoService.deleteAll();
        daoEmployee.deleteAll();
        daoServiceOrder.deleteAll();
    }

}
