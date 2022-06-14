package com.is.cole.services.usuarios;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.is.cole.daos.IRoleDao;
import com.is.cole.daos.IRolesUsuarioDao;
import com.is.cole.daos.IUserDao;
import com.is.cole.daos.IViajeDao;
import com.is.cole.dtos.Result;
import com.is.cole.dtos.Usuarios.RoleDto;
import com.is.cole.dtos.Usuarios.UsuarioDto;
import com.is.cole.entities.RoleUsuario;
import com.is.cole.entities.Roles;
import com.is.cole.entities.Usuarios;

@Service
public class UsuariosServiceImpl implements IUsuariosService {

	/********************************* Usuarios *******************************/

	/**
	 * Se guarda un usuario (Sirve tanto para crear uno nuevo,
	 * como tambien para actualizar uno anterior)
	 */
	@Override
	@Transactional
	public UsuarioDto saveUsuario(UsuarioDto dto) {
		Usuarios bean = parseDtoToBeanUsuario(dto);
		bean.setPassword(passwordEncoder.encode(dto.getPassword()));
		Usuarios beanGuardado = usuarioDao.save(bean);
		return parseBeanToDtoUsuario(beanGuardado);
	}
	
	/**
	 * Se elimina un usuario por medio de su id
	 */
	@Override
	@Transactional
	public void deleteUsuario(Integer id) {
		usuarioDao.deleteById(id);
	}
	
	/**
	 * Se obtiene un usuario por medio de su id
	 */
	@Override
	@Transactional
	public UsuarioDto getUsuario(Integer id) {
		Usuarios beanObtenido = usuarioDao.getById(id);
		return parseBeanToDtoUsuario(beanObtenido);
	}
	
	/**
	 * Se obtiene un usuario por medio de su correo
	 */
	@Override
	@Transactional
	public UsuarioDto getUsuarioByCorreo(String correo) {
		Usuarios bean = userDao.findByCorreo(correo);
		return parseBeanToDtoUsuario(bean);
	}
	
	/**
	 * Se obtiene un usuario por medio de un colectivo id
	 * esto es posible mediante la relacion entre ellos en 
	 * la entidad Viaje
	 */
	@Override
	@Transactional
	public UsuarioDto getUsuarioByColectivoId(Integer id) {
		Usuarios user= viajeDao.findByColectivoId(id).getUsuario();
		return parseBeanToDtoUsuario(user);
	}

	/**
	 * Obtener todos los usuarios
	 */
	@Override
	@Transactional
	public Result<UsuarioDto> getAllUsuario() {
		Result<UsuarioDto> result = new Result<>();
		List<UsuarioDto> list = usuarioDao.findAll().stream().map((bean) -> {
			return parseBeanToDtoUsuario(bean);
		}).collect(Collectors.toList());
		result.setResult(list);
		return result;
	}

	/***************************** Roles ****************************************/
	
	/**
	 * Se guarda un role (sirve tanto para crear uno nuevo, como tambien
	 * para actualizar uno anterior)
	 */
	@Override
	@Transactional
	public RoleDto saveRole(RoleDto dto) {
		Roles role = parseDtoToBeanRole(dto);
		Roles guardarRole = roleDao.save(role);
		return parseBeanToDtoRole(guardarRole);
	}
	
	/**
	 * Se obtiene un role por medio de su id
	 */
	@Override
	@Transactional
	public RoleDto getRole(Integer id) {
		Roles bean = roleDao.getById(id);
		return parseBeanToDtoRole(bean);
	}
	
	/**
	 * Se elimina un role por medio de su id
	 */
	@Override
	@Transactional
	public void deleteRole(Integer id) {
		roleDao.deleteById(id);
	}
	
	/**
	 * Se obtiene todos los roles
	 */
	@Override
	@Transactional
	public Result<RoleDto> getAllRole() {
		Result<RoleDto> result = new Result<>();
		List<RoleDto> list = roleDao.findAll().stream().map((bean) -> {
			return parseBeanToDtoRole(bean);
		}).collect(Collectors.toList());
		result.setResult(list);
		return result;
	}

	/**
	 * Metodo de la implementacion de agregar Role a Usuario
	 */
	@Override
	@Transactional
	public void agregarRoleAUsuario(Integer userId, Integer roleId) {
		if (isUsuarioRole(userId, roleId))
			return;

		RoleUsuario ru = new RoleUsuario();
		ru.setUsuarios(userDao.getById(userId));
		ru.setRoles(roleDao.getById(roleId));

		roleUserDao.save(ru);
	}

	/**
	 * Metodo agrega un Role a un Usuario
	 */
	@Override
	@Transactional
	public void agregarRoleAUsuario(Integer userId, String roleName) {
		Roles role = roleDao.findByName(roleName);
		agregarRoleAUsuario(userId, role.getId());
	}

	/**
	 * Metodo de la implementacion de eliminar Role a Usuario
	 */
	@Override
	@Transactional
	public void quitarRoleAUsuario(Integer userId, Integer roleId) {
		if (!isUsuarioRole(userId, roleId))
			return;

		RoleUsuario ru = roleUserDao.findByUsuarioIdRoleId(userId, roleId);
		roleUserDao.deleteById(ru.getId());
	}

	/**
	 * Metodo quita el Role al Usuario asignado
	 */
	@Override
	@Transactional
	public void quitarRoleAUsuario(Integer userId, String roleName) {
		Roles role = roleDao.findByName(roleName);
		quitarRoleAUsuario(userId, role.getId());
	}

	/**
	 * Metodo Boolean para buscar un RoleUser por medio del indentificador usuario y
	 * role
	 */
	@Override
	@Transactional
	public Boolean isUsuarioRole(Integer userId, Integer roleId) {
		RoleUsuario roleUsuario = roleUserDao.findByUsuarioIdRoleId(userId, roleId);
		System.out.println(roleUsuario);
		if (roleUsuario == null)
			return false;
		return true;
	}

	/**
	 * Devuelve un valor Boolean, se introduce un usuario y el role para ver su
	 * existencia.
	 * 
	 */
	@Override
	@Transactional
	public Boolean isUsuarioRole(Integer userId, String roleName) {
		Roles role = roleDao.findByName(roleName);
		return isUsuarioRole(userId, role.getId());
	}

	/************************* Usuarios parses ***********************************/

	/**
	 * Metodo para pasar de Dto a Bean de la entidad Usuario
	 * 
	 * @param dto
	 * @return
	 */
	private Usuarios parseDtoToBeanUsuario(UsuarioDto dto) {
		Usuarios bean = new Usuarios();
		bean.setId(dto.getId());
		bean.setNombre(dto.getNombre());
		bean.setApellido(dto.getApellido());
		bean.setCorreo(dto.getCorreo_electronico());
		bean.setPassword(dto.getPassword());
		return bean;
	}

	/**
	 * Metodo para pasar Bean a Dto de la entidad Usuario
	 * 
	 * @param bean
	 * @return
	 */
	private UsuarioDto parseBeanToDtoUsuario(Usuarios bean) {
		UsuarioDto dto = new UsuarioDto();
		dto.setId(bean.getId());
		dto.setNombre(bean.getNombre());
		dto.setApellido(bean.getApellido());
		dto.setCorreo_electronico(bean.getCorreo());
		dto.setPassword(bean.getPassword());

		dto.setRoleList(
				roleUserDao.findByUsuarioId(bean.getId()).stream().map(ru -> roleDao.getById(ru.getRoles().getId()))
						.map(r -> parseBeanToDtoRole(r)).collect(Collectors.toList()));

		return dto;
	}

	/**************************** Roles parses ************************************/

	/**
	 * Metodo para pasar de Bean a Dto de la entidad Roles
	 * 
	 * @param bean
	 * @return
	 */
	private RoleDto parseBeanToDtoRole(Roles bean) {
		RoleDto dto = new RoleDto();

		dto.setId(bean.getId());
		dto.setNombre(bean.getNombre());
		dto.setDescripcion(bean.getDescription());

		return dto;
	}

	/**
	 * Metodo para pasar de Dto a Bean de la entidad Roles.
	 * 
	 * @param dto
	 * @return
	 */
	private Roles parseDtoToBeanRole(RoleDto dto) {
		Roles bean = new Roles();

		bean.setId(dto.getId());
		bean.setNombre(dto.getNombre());
		bean.setDescription(dto.getDescripcion());

		return bean;
	}

	@Autowired
	private IUserDao usuarioDao;
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IRolesUsuarioDao roleUserDao;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IViajeDao viajeDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
}
