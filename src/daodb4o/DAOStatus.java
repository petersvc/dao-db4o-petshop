package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import model.Status;

public class DAOStatus extends DAO<Status>{
    public Status read(Object chave){
        String name = (String) chave;
        Query q = manager.query();
        q.constrain(Status.class);
        q.descend("name").constrain(name);
        List<Status> resultados = q.execute();
        if (resultados.size()>0)
            return resultados.get(0);
        else
            return null;
    }
    
    public int size(){
        Query q = manager.query();
        q.constrain(Status.class);
        return q.execute().size();
    }
}