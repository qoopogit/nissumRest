package net.aigarcia.rest.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import net.aigarcia.rest.models.Phone;

public interface PhoneDAO extends JpaRepository<Phone, String> {
	
}
