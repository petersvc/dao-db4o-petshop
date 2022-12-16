package daodb4o;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

public abstract class DAO<T> implements DAOInterface<T> {
	protected static ObjectContainer manager;

	public static void open() {
		if (manager == null) {
			manager = Util.createManager(); // banco local
			// manager = Util.conectarDb4oRemoto(); //banco remoto
		}
	}

	public static void close() {
		if (manager != null && !manager.ext().isClosed()) {
			manager.close();
			manager = null;
		}
	}

	// ----------CRUD-----------------------

	public void create(T obj) {
		manager.store(obj);
		commit();
	}

	public abstract T read(Object chave);

	public T update(T obj) {
		manager.store(obj);
		commit();
		return obj;
	}

	public void delete(T obj) {
		manager.delete(obj);
		commit();
	}

	public void refresh(T obj) {
		manager.ext().refresh(obj, Integer.MAX_VALUE);
		commit();
	}

	@SuppressWarnings("unchecked")
	public List<T> readAll() {
		manager.ext().purge(); // limpar cache do manager

		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		Query q = manager.query();
		q.constrain(type);
		return (List<T>) q.execute();
	}

	@SuppressWarnings("unchecked")
	// deletar todos objetos de um tipo (e subtipo)
	public void deleteAll() {
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];

		Query q = manager.query();
		q.constrain(type);
		for (Object t : q.execute()) {
			manager.delete(t);
			commit();
		}
	}

	// --------transa��o---------------
	public static void begin() {
	} // tem que ser vazio

	public static void commit() {
		manager.commit();
	}

	public static void rollback() {
		manager.rollback();
	}

	// gerar novo id para o tipo T
	// -- obter o maior id para o tipo: ordenar decrescentemente e depois acessa o
	// id do primeiro resultado

	public int generateId() {
		@SuppressWarnings("unchecked")
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];

		// verificar se o banco esta vazio
		if (manager.query(type).size() == 0) {
			return 1; // primeiro id gerado
		} else {
			// obter o maior valor de id para o tipo
			Query q = manager.query();
			q.constrain(type);
			q.descend("id").orderDescending();
			List<T> resultados = q.execute();
			if (resultados.isEmpty())
				return 1; // nenhum resultado, retorna primeiro id
			else
				try {
					// obter objeto localizado
					T objeto = resultados.get(0);
					Field atributo = type.getDeclaredField("id");
					atributo.setAccessible(true);
					// obter atributo id do objeto localizado e incrementa-lo
					int maxid = (Integer) atributo.get(objeto); // valor do id
					return maxid + 1;

				} catch (NoSuchFieldException e) {
					throw new RuntimeException("classe " + type + " - nao tem atributo id");
				} catch (IllegalAccessException e) {
					throw new RuntimeException("classe " + type + " - atributo id inacessivel");
				}
		}
	}

}
