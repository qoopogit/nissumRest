package net.aigarcia.rest.dtos;

public class Response {
	private String mensaje;

	public Response() {
		super();
	}

	public Response(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
