package com.itinerarios.springboot;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itinerarios.dto.AerolineaDTO;
import com.itinerarios.entity.Aerolinea;
import com.itinerarios.request.form.UsuarioReqCrearForm;
import com.itinerarios.request.form.UsuarioReqForm;
import com.itinerarios.request.form.UsuarioReqInfoForm;
import com.itinerarios.request.form.VueloReqCrearForm;
import com.itinerarios.response.form.GeneralResponseForm;
import com.itinerarios.response.form.InfoUsuarioResponseForm;
import com.itinerarios.service.VueloServiceImpl;
import com.itinerarios.springboot.repository.AerolineaRepository;
import com.itinerarios.springboot.repository.VueloRepository;
import com.itinerarios.springboot.utils.ConstantsUtil;
import com.itinerarios.springboot.utils.DTOUtils;
import com.itinerarios.springboot.utils.JWTUtils;
import com.itinerarios.springboot.utils.UsuarioUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@RequestMapping("usuarios")
@Api(tags = "usuariosAPI")
public class UsuarioController {
	
	@Autowired
	VueloServiceImpl vueloService;

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
				.addHeader("x-api-key", ConstantsUtil.API_KEY).addHeader("Content-Type", "application/json").build();
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
				.addHeader("x-api-key", ConstantsUtil.API_KEY).addHeader("Content-Type", "application/json").build();
		Response response = client.newCall(request).execute();
		return response.body().string();

	}

	@GetMapping(value = "/verificar", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "Método para verificar el token. Interactua con el api de SSO. Retorna si el token es válido o no")
	public boolean verificarToken(@RequestHeader("token") String token) throws IOException {
		return JWTUtils.verificarToken(token);
	}

	@GetMapping(value = "/getInfoUsuario",produces = "application/json")
	public InfoUsuarioResponseForm crearVuelo(@RequestHeader("token") String token)
			throws JsonMappingException, JsonProcessingException, IOException {
		
		UsuarioReqInfoForm usuario = UsuarioUtils.getUsuario(token);
		InfoUsuarioResponseForm usuarioResp = new InfoUsuarioResponseForm();
		AerolineaDTO aerolinea = new AerolineaDTO();
		aerolinea = DTOUtils.convertToDto(vueloService.buscarAerolinea(usuario.getPropiedades().get("aerolinea")));
		usuarioResp.setNombre(usuario.getNombre());
		usuarioResp.setApellido(usuario.getApellido());
		usuarioResp.setAerolinea(aerolinea.getNombreAerolinea());
		usuarioResp.setImagen(aerolinea.getLogoLink());
		
		return usuarioResp;
	}
}
