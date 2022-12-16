package daodb4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

import model.ServiceOrder;
import model.Breed;
import model.Employee;
import model.Tutor;
import model.Pet;
import model.Service;
import model.Status;

public class Util {

	public static ObjectContainer createManager(){
		
		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
		config.common().messageLevel(0);
		
        config.common().objectClass(ServiceOrder.class).cascadeOnUpdate(true);
        config.common().objectClass(ServiceOrder.class).cascadeOnDelete(true);
        config.common().objectClass(ServiceOrder.class).cascadeOnActivate(true);
        
        config.common().objectClass(Breed.class).cascadeOnUpdate(true);
        config.common().objectClass(Breed.class).cascadeOnDelete(true);
        config.common().objectClass(Breed.class).cascadeOnActivate(true);
        
        config.common().objectClass(Pet.class).cascadeOnUpdate(false);
        config.common().objectClass(Pet.class).cascadeOnDelete(false);
        config.common().objectClass(Pet.class).cascadeOnActivate(true);
        
        config.common().objectClass(Employee.class).cascadeOnUpdate(true);
        config.common().objectClass(Employee.class).cascadeOnDelete(true);
        config.common().objectClass(Employee.class).cascadeOnActivate(true);
        
        config.common().objectClass(Tutor.class).cascadeOnUpdate(true);
        config.common().objectClass(Tutor.class).cascadeOnDelete(true);
        config.common().objectClass(Tutor.class).cascadeOnActivate(true);
        
        config.common().objectClass(Service.class).cascadeOnUpdate(true);
        config.common().objectClass(Service.class).cascadeOnDelete(true);
        config.common().objectClass(Service.class).cascadeOnActivate(true);      
        
        config.common().objectClass(Status.class).cascadeOnUpdate(true);
        config.common().objectClass(Status.class).cascadeOnDelete(true);
        config.common().objectClass(Status.class).cascadeOnActivate(true);
        
      
		config.common().objectClass(ServiceOrder.class).objectField("tutor").indexed(true);

        config.common().objectClass(ServiceOrder.class).minimumActivationDepth(5);

		ObjectContainer manager = Db4oEmbedded.openFile(config, "pet_databse.db4o");
		return manager;
	}

}
