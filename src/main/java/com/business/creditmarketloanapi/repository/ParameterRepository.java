package com.business.creditmarketloanapi.repository;

import com.business.creditmarketloanapi.entity.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Contains API for basic CRUD operations and queries on the Parameter entity
 *
 * @author Julian Alvarado
 */
@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {
}
