package entities;
// Generated 23-nov-2017 20:02:51 by Hibernate Tools 5.2.6.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Deportista generated by hbm2java
 */
@Entity
@Table(name = "Deportista", catalog = "mytokyo2020")
public class Deportista implements java.io.Serializable {

	private int idDeportista;
	private Pais pais;
	private String nombre;
	private String edad;
	private Date lastModification;
	private Integer medallasOro;
	private Integer medallasPlata;
	private Integer medallasBronce;
	private Set<EventoParticipaDeportista> eventoParticipaDeportistas = new HashSet<EventoParticipaDeportista>(0);

	public Deportista() {
	}

	public Deportista(int idDeportista, Pais pais, Date lastModification) {
		this.idDeportista = idDeportista;
		this.pais = pais;
		this.lastModification = lastModification;
	}

	public Deportista(int idDeportista, Pais pais, String nombre, String edad, Date lastModification,
			Integer medallasOro, Integer medallasPlata, Integer medallasBronce,
			Set<EventoParticipaDeportista> eventoParticipaDeportistas) {
		this.idDeportista = idDeportista;
		this.pais = pais;
		this.nombre = nombre;
		this.edad = edad;
		this.lastModification = lastModification;
		this.medallasOro = medallasOro;
		this.medallasPlata = medallasPlata;
		this.medallasBronce = medallasBronce;
		this.eventoParticipaDeportistas = eventoParticipaDeportistas;
	}

	@Id

	@Column(name = "idDeportista", unique = true, nullable = false)
	public int getIdDeportista() {
		return this.idDeportista;
	}

	public void setIdDeportista(int idDeportista) {
		this.idDeportista = idDeportista;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Pais_idPais", nullable = false)
	public Pais getPais() {
		return this.pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@Column(name = "Nombre", length = 45)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "Edad", length = 45)
	public String getEdad() {
		return this.edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LastModification", nullable = false, length = 0)
	public Date getLastModification() {
		return this.lastModification;
	}

	public void setLastModification(Date lastModification) {
		this.lastModification = lastModification;
	}

	@Column(name = "MedallasOro")
	public Integer getMedallasOro() {
		return this.medallasOro;
	}

	public void setMedallasOro(Integer medallasOro) {
		this.medallasOro = medallasOro;
	}

	@Column(name = "MedallasPlata")
	public Integer getMedallasPlata() {
		return this.medallasPlata;
	}

	public void setMedallasPlata(Integer medallasPlata) {
		this.medallasPlata = medallasPlata;
	}

	@Column(name = "MedallasBronce")
	public Integer getMedallasBronce() {
		return this.medallasBronce;
	}

	public void setMedallasBronce(Integer medallasBronce) {
		this.medallasBronce = medallasBronce;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "deportista")
	public Set<EventoParticipaDeportista> getEventoParticipaDeportistas() {
		return this.eventoParticipaDeportistas;
	}

	public void setEventoParticipaDeportistas(Set<EventoParticipaDeportista> eventoParticipaDeportistas) {
		this.eventoParticipaDeportistas = eventoParticipaDeportistas;
	}

}
