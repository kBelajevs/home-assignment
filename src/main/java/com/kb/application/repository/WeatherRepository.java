package com.kb.application.repository;

import com.kb.application.model.dao.WeatherEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends CrudRepository<WeatherEntity, Long> {
}
