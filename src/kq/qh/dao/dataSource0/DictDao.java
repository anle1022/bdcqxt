package kq.qh.dao.dataSource0;

import java.util.List;

import kq.qh.entity.Dict;

import org.springframework.stereotype.Repository;

@Repository
public interface DictDao {

	List<Dict> findEntityList(String key);

}
