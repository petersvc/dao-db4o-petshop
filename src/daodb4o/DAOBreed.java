package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import model.Breed;

public class DAOBreed extends DAO<Breed>{
    
    public Breed read(Object chave){
        String name = (String) chave;
        Query q = manager.query();
        q.constrain(Breed.class);
        q.descend("name").constrain(name);
        List<Breed> resultados = q.execute();
        if (resultados.size()>0)
            return resultados.get(0);
        else
            return null;
    }
    
    public int size(){
        Query q = manager.query();
        q.constrain(Breed.class);
        return q.execute().size();
    }

}
