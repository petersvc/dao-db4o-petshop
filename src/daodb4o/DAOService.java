package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import model.Service;

public class DAOService extends DAO<Service>{
    public Service read(Object chave){
        String name = (String) chave;
        Query q = manager.query();
        q.constrain(Service.class);
        q.descend("name").constrain(name);
        List<Service> resultados = q.execute();
        if (resultados.size()>0)
            return resultados.get(0);
        else
            return null;
    }
    
    public int size(){
        Query q = manager.query();
        q.constrain(Service.class);
        return q.execute().size();
    }
}