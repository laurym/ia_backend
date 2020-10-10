package com.itinerarios.springboot;

import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itinerarios.request.form.UsuarioReqCrearForm;
import com.itinerarios.request.form.UsuarioReqForm;
import com.itinerarios.springboot.utils.ConstantsUtil;
import com.itinerarios.springboot.utils.JWTUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@RequestMapping("usuarios")
@Api(tags  = "usuariosAPI")
public class UsuarioController {
	
	@PostMapping(value = "/registrar", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "Método para registrar el usuario. Interactua con el api de SSO ", notes = "Retorna el token obtenido")
	public String registrarUsuario(@RequestBody UsuarioReqCrearForm usuario) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String bodyString = mapper.writeValueAsString(usuario);
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("application/json");
		@SuppressWarnings("deprecation")
		okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, bodyString);

		Request request = new Request.Builder().url("https://ssoia.herokuapp.com/Usuarios").method("POST", body)
				.addHeader("x-api-key", ConstantsUtil.API_KEY)
				.addHeader("Content-Type", "application/json").build();
		Response response = client.newCall(request).execute();
		return response.body().string();

	}
	@PostMapping(value = "/logear", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "Método para logear el usuario. Interactua con el api de SSO ")
	public String logearUsuario(@RequestBody UsuarioReqForm usuario) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String bodyString = mapper.writeValueAsString(usuario);
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("application/json");
		@SuppressWarnings("deprecation")
		okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, bodyString);

		Request request = new Request.Builder().url("https://ssoia.herokuapp.com/Login").method("POST", body)
				.addHeader("x-api-key", ConstantsUtil.API_KEY)
				.addHeader("Content-Type", "application/json").build();
		Response response = client.newCall(request).execute();
		return response.body().string();

	}
	
	@GetMapping(value = "/verificar", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "Método para verificar el token. Interactua con el api de SSO. Retorna si el token es válido o no")
	public boolean verificarToken(@RequestHeader("token") String token) throws IOException {
		return JWTUtils.verificarToken(token);
	}
	
	@GetMapping(value = "/refrescar", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "Método para realizar refresh del token asociado al usuario. Interactua con el api de SSO ", notes = "Refresca el token obtenido")
	public String refrescarUsuario(@RequestHeader("token") String token) throws IOException {
		
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		
		Request request = new Request.Builder().url("https://ssoia.herokuapp.com/JWT/refresh").method("GET",null)
				.addHeader("x-api-key", ConstantsUtil.API_KEY)
				.addHeader("Authorization",token.substring(7))
				.addHeader("Content-Type", "application/json").build();
		Response response = client.newCall(request).execute();
		return response.body().string();

	}
}
