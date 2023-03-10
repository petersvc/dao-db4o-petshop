package model;

public class Status {
    
    private String name;

    public Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Status [name=" + name + "]";
    }
}
