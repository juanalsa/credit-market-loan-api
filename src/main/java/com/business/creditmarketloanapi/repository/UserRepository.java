package com.business.creditmarketloanapi.repository;

import com.business.creditmarketloanapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Contains API for basic CRUD operations and queries on the User entity
 *
 * @author Julian Alvarado
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
