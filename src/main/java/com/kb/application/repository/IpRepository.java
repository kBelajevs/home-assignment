package com.kb.application.repository;

import com.kb.application.model.dao.IpEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpRepository  extends CrudRepository<IpEntity, Long> {
}
