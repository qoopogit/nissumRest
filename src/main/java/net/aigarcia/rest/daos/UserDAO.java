package net.aigarcia.rest.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import net.aigarcia.rest.models.User;

public interface UserDAO extends JpaRepository<User, String> {
	
}
