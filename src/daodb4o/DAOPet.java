package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import model.Pet;

public class DAOPet extends DAO<Pet> {
    public Pet read(Object chave){
        int id = (int) chave;
        Query q = manager.query();
        q.constrain(Pet.class);
        q.descend("id").constrain(id);
        List<Pet> resultados = q.execute();
        if (resultados.size()>0)
            return resultados.get(0);
        else
            return null;
    }

    public Pet readByName(Object name){
        Query q = manager.query();
        q.constrain(Pet.class);
        q.descend("name").constrain(name);
        List<Pet> resultados = q.execute();
        if (resultados.size()>0)
            return resultados.get(0);
        else
            return null;
    }
    
    public int size(){
        Query q = manager.query();
        q.constrain(Pet.class);
        return q.execute().size();
    }

    public void create(Pet obj){
        int id = super.generateId();
        obj.setId(id);
        super.create(obj);
    }
}
