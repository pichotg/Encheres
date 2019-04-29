package dal;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
	
	List<T> selectAll();
	List<Integer> selectId();
	void insert(T t) throws SQLException;
	void update(T t);
	void delete(int id);
	List<String> nomColonnes();
	List<T> recherche(String nomColonne, String valeur);
	
}
