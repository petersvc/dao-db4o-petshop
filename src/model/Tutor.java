package model;

import java.util.ArrayList;
import java.util.List;

public class Tutor extends Person {

    private List<Pet> pets;
    //public Person tutorPet;

    public Tutor(String name, String document, String phone) {
        super(name, document, phone);
        pets = new ArrayList<Pet>();
    }

    public void addPet(Pet pet) {
        pets.add(pet);
    }

    public List<Pet> getPets() {
        return pets;
    }
    
    //public void setTutorPet(Person tutorDoPet) {
    	//this.tutorPet = tutorDoPet;
    //}
    @Override
    public String toString() {
        return "Tutor [name=" + getName() + ", document=" + getDocument() + ", phone=" + getPhone() + ", pets=" + pets + "]";
    }
}
