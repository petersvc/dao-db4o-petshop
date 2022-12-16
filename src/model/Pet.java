package model;

public class Pet {
	
	private String name;
	private Breed breed;
	private double weight;
	private int id;
	
	public Pet(String name, Breed breed, double weight) {
		super();
		this.name = name;
		this.breed = breed;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Breed getBreed() {
		return breed;
	}

	public void setBreed(Breed breed) {
		this.breed = breed;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Pet [name=" + name + ", breed=" + breed + ", weight=" + weight + "]";
	}
	
}
