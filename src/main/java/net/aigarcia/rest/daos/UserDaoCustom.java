package net.aigarcia.rest.daos;

import java.util.List;

import net.aigarcia.rest.models.User;

public interface UserDaoCustom {
	public List<User> findByEmail(String email);
}
