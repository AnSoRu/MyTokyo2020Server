package entities;
// Generated 09-nov-2017 17:36:49 by Hibernate Tools 5.2.6.Final

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
 * Disciplina generated by hbm2java
 */
@Entity
@Table(name = "Disciplina", catalog = "mytokyo2020")
public class Disciplina implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idDisciplina;
	private String nombre;
	private String descripcion;
	private Date lastModification;
	private Set<Evento> eventos = new HashSet<Evento>(0);

	public Disciplina() {
	}

	public Disciplina(int idDisciplina, String nombre) {
		this.idDisciplina = idDisciplina;
		this.nombre = nombre;
	}

	public Disciplina(int idDisciplina, String nombre, String descripcion, Date lastModification, Set<Evento> eventos) {
		this.idDisciplina = idDisciplina;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.lastModification = lastModification;
		this.eventos = eventos;
	}

	@Id

	@Column(name = "idDisciplina", unique = true, nullable = false)
	public int getIdDisciplina() {
		return this.idDisciplina;
	}

	public void setIdDisciplina(int idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	@Column(name = "Nombre", nullable = false, length = 45)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "Descripcion", length = 16777215)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LastModification", length = 0)
	public Date getLastModification() {
		return this.lastModification;
	}

	public void setLastModification(Date lastModification) {
		this.lastModification = lastModification;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "disciplina")
	public Set<Evento> getEventos() {
		return this.eventos;
	}

	public void setEventos(Set<Evento> eventos) {
		this.eventos = eventos;
	}

}
