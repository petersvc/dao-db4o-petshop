package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import model.Tutor;

public class DAOTutor extends DAO<Tutor>{
    
    public Tutor read(Object chave){
        String cpf = (String) chave;
        Query q = manager.query();
        q.constrain(Tutor.class);
        q.descend("document").constrain(cpf);
        List<Tutor> resultados = q.execute();
        if (resultados.size()>0)
            return resultados.get(0);
        else
            return null;
    }
    
    public int size(){
        Query q = manager.query();
        q.constrain(Tutor.class);
        return q.execute().size();
    }

}