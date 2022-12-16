package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import model.ServiceOrder;

public class DAOServiceOrder extends DAO<ServiceOrder> {
    public ServiceOrder read(Object chave){
        int id = (int) chave;
        Query q = manager.query();
        q.constrain(ServiceOrder.class);
        q.descend("id").constrain(id);
        List<ServiceOrder> resultados = q.execute();
        if (resultados.size()>0)
            return resultados.get(0);
        else
            return null;
    }

    public int size(){
        Query q = manager.query();
        q.constrain(ServiceOrder.class);
        return q.execute().size();
    }

    public void create(ServiceOrder obj){
        int id = super.generateId();
        obj.setId(id);
        super.create(obj);
    }
}