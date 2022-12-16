package application;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import daodb4o.Util;
import model.ServiceOrder;
import model.Tutor;

public class QueryData {
	protected ObjectContainer manager;

    public QueryData() {
        manager = Util.createManager();
        query();
        manager.close();
    }

    private void query() {
    	queryTutorByBreedName("Poodle");
        queryServiceOrdersByEmployeeAndStatus("Maria", "Solicitado");
        queryServiceOrdersQuantityByStatusAndService("Solicitado", "Banho");
    }
    
    private void queryTutorByBreedName(String breedName) {
    	System.out.println("Consultando tutores com pets da raça poodle");
    	Query q = manager.query();
		q.constrain(Tutor.class);
		q.descend("pets").descend("breed").descend("name").constrain(breedName);
		
		List<Tutor> resultados = q.execute();
		for(Tutor tutor: resultados)
			System.out.println(tutor);
    }

    private void queryServiceOrdersByEmployeeAndStatus(String employeeName, String statusName) {
    	System.out.println("Consultando ordens de servico do funcionário "+employeeName+" com status "+statusName);
    	Query q = manager.query();
        q.constrain(ServiceOrder.class);
        q.descend("employee").descend("name").constrain(employeeName);
        q.descend("status").descend("name").constrain(statusName);

        List<ServiceOrder> resultados = q.execute();
        for(ServiceOrder serviceOrder: resultados)
        	System.out.println(serviceOrder);
    }

    private void queryServiceOrdersQuantityByStatusAndService(String statusName, String serviceName) {
    	System.out.println("Consultando quantidade de ordens de servico com status "+statusName+" e servico "+serviceName);
    	Query q = manager.query();
        q.constrain(ServiceOrder.class);
        q.descend("status").descend("name").constrain(statusName);
        q.descend("services").descend("name").constrain(serviceName);

        List<ServiceOrder> resultados = q.execute();
        System.out.println("Quantidade de ordens de servico: "+resultados);
    }

    public static void main(String[] args) {
        new QueryData();
    }
    
}
