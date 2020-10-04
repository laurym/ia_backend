package com.itinerarios.springboot;

import java.io.IOException;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itinerarios.request.form.UsuarioReqCrearForm;
import com.itinerarios.request.form.UsuarioReqForm;
import com.itinerarios.response.form.GeneralResponseForm;
import com.itinerarios.springboot.utils.ConstantsUtil;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request;
import okhttp3.Response;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@RequestMapping("rest/usuarios")
public class UsuarioController {
	
	@PostMapping(value = "/registrar", consumes = "application/json", produces = "application/json")
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
}
