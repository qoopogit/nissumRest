package net.aigarcia.rest.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.aigarcia.rest.daos.UserDAO;
import net.aigarcia.rest.daos.UserDaoCustom;
import net.aigarcia.rest.dtos.Response;
import net.aigarcia.rest.models.Phone;
import net.aigarcia.rest.models.User;
import net.aigarcia.rest.utils.Validator;

@RestController
@RequestMapping("users")
public class UserService {

	@Autowired
	private UserDAO userDao;

	@Autowired
	private UserDaoCustom userDaoCustom;

	/**
	 * Registra un nuevo usuario
	 */
	@PostMapping("add")
	public ResponseEntity create(@RequestBody User item, HttpServletResponse response) {
		try {
//			User item = new User();			
			// validacion de email
			if (!Validator.validateEmail(item.getEmail())) {
				return new ResponseEntity<>(new Response("Email inválido"), HttpStatus.BAD_REQUEST);
			}
			// valida que el email no se repita
			try {
				List<User> tt = userDaoCustom.findByEmail(item.getEmail());
				if (tt != null && !tt.isEmpty()) {
					return new ResponseEntity<>(new Response("Email ya registrado"), HttpStatus.BAD_REQUEST);
				}
			} catch (Exception ee) {
			}

			// validacion de password
			if (!Validator.validatePassword(item.getPassword())) {
				return new ResponseEntity<>(new Response("Password no cumple con las condiciones mínimas"),
						HttpStatus.BAD_REQUEST);
			}

			UUID corrId = UUID.randomUUID();
			item.setId(corrId.toString());
			System.out.println("UUID=" + corrId.toString());
			item.setCreatedDate(new Date());
			item.setLastLogin(new Date());
			item.setActive(Boolean.TRUE);
			item.setToken(UUID.randomUUID().toString());
			for (Phone phone : item.getPhones()) {
				phone.setId(UUID.randomUUID().toString());
				phone.setUser(item);
			}
			userDao.save(item);
			return new ResponseEntity<>(item, HttpStatus.OK);
//			return new ResponseEntity<>(new UserDTO(item.getId(), item.getCreatedDate(), item.getModifiedDate(), item.getLastLogin(), item.getToken(), item.isActive()), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new Response("Error :" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update")
	public ResponseEntity update(@RequestBody User item) {
		try {
			System.out.println("Updating UUID=" + item.getId());
			item.setModifiedDate(new Date());
			userDao.save(item);
			return new ResponseEntity<>(item, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new Response("Error :" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("list")
	public ResponseEntity list() {
		try {
			return new ResponseEntity<>(userDao.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new Response("Error :" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity delete(@PathVariable("id") String userID) {
		try {
			userDao.deleteById(userID);
			return new ResponseEntity<>(new Response("ok"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new Response("El id no existe"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("deleteAll")
	public ResponseEntity deleteAll() {
		try {
			userDao.deleteAll();
			return new ResponseEntity<>(new Response("ok"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new Response("Error :" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("get/{id}")
	public ResponseEntity get(@PathVariable("id") String userID) {
		try {
			return new ResponseEntity<>(userDao.findById(userID).get(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new Response("El id no existe"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
