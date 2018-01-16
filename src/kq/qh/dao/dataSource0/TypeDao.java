package kq.qh.dao.dataSource0;

import java.util.List;






import org.springframework.stereotype.Repository;

import kq.qh.entity.Type;

@Repository
public interface TypeDao {

	public List<Type> findAll();
	
	public Type findAll(Type type);
}
