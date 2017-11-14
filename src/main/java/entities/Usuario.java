package entities;
// Generated 14-nov-2017 11:46:19 by Hibernate Tools 5.2.6.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name = "Usuario", catalog = "mytokyo2020")
public class Usuario implements java.io.Serializable {

	private String username;
	private Integer edad;
	private String password;
	private Date lastModification;
	private Set<UsuarioCompraEvento> usuarioCompraEventos = new HashSet<UsuarioCompraEvento>(0);

	public Usuario() {
	}

	public Usuario(String username, String password, Date lastModification) {
		this.username = username;
		this.password = password;
		this.lastModification = lastModification;
	}

	public Usuario(String username, Integer edad, String password, Date lastModification,
			Set<UsuarioCompraEvento> usuarioCompraEventos) {
		this.username = username;
		this.edad = edad;
		this.password = password;
		this.lastModification = lastModification;
		this.usuarioCompraEventos = usuarioCompraEventos;
	}

	@Id

	@Column(name = "Username", unique = true, nullable = false, length = 10)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "Edad")
	public Integer getEdad() {
		return this.edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	@Column(name = "Password", nullable = false, length = 45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LastModification", nullable = false, length = 0)
	public Date getLastModification() {
		return this.lastModification;
	}

	public void setLastModification(Date lastModification) {
		this.lastModification = lastModification;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario")
	public Set<UsuarioCompraEvento> getUsuarioCompraEventos() {
		return this.usuarioCompraEventos;
	}

	public void setUsuarioCompraEventos(Set<UsuarioCompraEvento> usuarioCompraEventos) {
		this.usuarioCompraEventos = usuarioCompraEventos;
	}

}
