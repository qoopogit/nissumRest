package net.aigarcia.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import net.aigarcia.rest.models.Phone;
import net.aigarcia.rest.models.User;
import net.aigarcia.rest.services.UserService;

//@SpringBootTest
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TestRestApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private UserService controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	/**
	 * Prueba le metodo agregar donde debe dar ok y luego vuevle a agregar el mismo
	 * usuario donde debe dar error de email invalido
	 * 
	 * @throws Exception
	 */
	void testAddUserOK() throws Exception {
		String url = "http://localhost:" + port + "/";
		User nuevo = new User();
		nuevo.setName("Test User");
		nuevo.setEmail("test.user@email.com");
		nuevo.setPassword("Mi1Password4");
		nuevo.setPhones(new ArrayList<>());
		nuevo.getPhones().add(new Phone("17354", "04", "+593"));
		nuevo.getPhones().add(new Phone("123456789", "04", "+593"));

		// Prueba el metodo agregar
		ResponseEntity<User> resp = restTemplate.postForEntity(url + "add", nuevo, User.class);
		assertThat(resp != null);
		assertThat(resp.getStatusCode() == HttpStatus.OK);
		assertThat(resp.getBody() != null);
		assertThat(resp.getBody().getId() != null);

		// Prueba el metodo agregar
		resp = restTemplate.postForEntity(url + "add", nuevo, User.class);
		assertThat(resp != null);
		assertThat(resp.getStatusCode() == HttpStatus.BAD_REQUEST);
		assertThat(resp.getBody() != null);
		assertThat(resp.getBody().getId() != null);
	}

	@Test
	/**
	 * Prueba el metodo agregar con una password que no cumple los requisitos
	 * 
	 * @throws Exception
	 */
	void testAddUserWrongPassword() throws Exception {
		String url = "http://localhost:" + port + "/";
		User nuevo = new User();
		nuevo.setName("Test User");
		nuevo.setEmail("test2.user@email.com");
		nuevo.setPassword("password");
		nuevo.setPhones(new ArrayList<>());
		nuevo.getPhones().add(new Phone("17354", "04", "+593"));
		nuevo.getPhones().add(new Phone("123456789", "04", "+593"));
		ResponseEntity<User> resp = restTemplate.postForEntity(url + "add", nuevo, User.class);
		assertThat(resp != null);
		assertThat(resp.getStatusCode() == HttpStatus.BAD_REQUEST);
	}

}
