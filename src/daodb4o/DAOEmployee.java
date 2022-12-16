package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import model.Employee;

public class DAOEmployee  extends DAO<Employee>{
    public Employee read(Object chave){
        String document = (String) chave;
        Query q = manager.query();
        q.constrain(Employee.class);
        q.descend("document").constrain(document);
        List<Employee> resultados = q.execute();
        if (resultados.size()>0)
            return resultados.get(0);
        else
            return null;
    }
    
    public int size(){
        Query q = manager.query();
        q.constrain(Employee.class);
        return q.execute().size();
    }    
}
