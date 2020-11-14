package com.itinerarios.springboot.utils;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itinerarios.request.form.UsuarioReqInfoForm;
import com.itinerarios.request.form.VueloReqCrearForm;
import com.itinerarios.response.form.GeneralResponseForm;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UsuarioUtils {

	public static UsuarioReqInfoForm getUsuario(String token)
			throws JsonMappingException, JsonProcessingException, IOException {
		OkHttpClient client = new OkHttpClient().newBuilder().build();

		ObjectMapper mapper = new ObjectMapper();

		Request request = new Request.Builder().url("https://ssoia.herokuapp.com/Usuarios/" + JWTUtils.getId(token))
				.method("GET", null).addHeader("x-api-key", ConstantsUtil.API_KEY)
				.addHeader("Authorization", token.substring(7)).addHeader("Content-Type", "application/json").build();
		Response response = client.newCall(request).execute();

		return mapper.readValue(response.body().string(), UsuarioReqInfoForm.class);
	}

}
