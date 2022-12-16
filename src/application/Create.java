package application;

import busines.Facade;

public class Create {
    public Create() {
		System.out.println("Creating records");
        try {
            Facade.init();
            System.out.println("Cleaning up database");
            Facade.cleanUpDatabase();

            System.out.println("Creating breeds");
            Facade.createBreed("Poodle");
            Facade.createBreed("Labrador");
            Facade.createBreed("Pitbull");

            System.out.println("Creating pets");
            Facade.createPet("Fido", "Poodle", 10);
            Facade.createPet("Rex", "Labrador", 20);
            Facade.createPet("Bolt", "Pitbull", 30);

            System.out.println("Creating tutors");
            Facade.createTutor("Joao", "123.456.789-00", "11 99999-9999");
            Facade.createTutor("Maria", "987.654.321-00", "11 99999-9998");
            Facade.createTutor("Jose", "123.123.123-00", "11 99999-9997");

            System.out.println("Adding pets to tutors");
            Facade.addPetToTutor(1, "123.456.789-00");
            Facade.addPetToTutor(2, "123.123.123-00");
            Facade.addPetToTutor(3, "987.654.321-00");

            System.out.println("Creating statuses");
            Facade.createStatus("Solicitado");
            Facade.createStatus("Em andamento");
            Facade.createStatus("Finalizado");

            System.out.println("Creating services");
            Facade.createService("Banho", 50);
            Facade.createService("Tosa", 30);
            Facade.createService("Banho e tosa", 70);
            Facade.createService("Vermifugação", 20);
            Facade.createService("Vacinação", 50);

            System.out.println("Creating employees");
            Facade.createEmployee("Joao", "634.456.789-00", "11 89999-9999");
            Facade.createEmployee("Maria", "098.654.321-00", "11 89999-9998");

            System.out.println("Creating service orders");
            Facade.createServiceOrder("123.456.789-00", 1, "Solicitado", "098.654.321-00");
            Facade.createServiceOrder("123.123.123-00", 2, "Solicitado", "098.654.321-00");
            Facade.createServiceOrder("987.654.321-00", 3, "Em andamento", "634.456.789-00");

            System.out.println("Adding services to service orders");
            Facade.addServiceToServiceOrder("Banho", 1);
            Facade.addServiceToServiceOrder("Tosa", 1);
            Facade.addServiceToServiceOrder("Banho e tosa", 2);
            Facade.addServiceToServiceOrder("Vermifugação", 2);
            Facade.addServiceToServiceOrder("Vacinação", 3);

        } catch (Exception e) {
           System.out.println("--->"+e.getMessage());
        }

        Facade.end();
    }

    public static void main(String[] args) {
        new Create();
    }

}