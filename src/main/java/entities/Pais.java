package entities;
// Generated 23-nov-2017 20:02:51 by Hibernate Tools 5.2.6.Final

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
 * Pais generated by hbm2java
 */
@Entity
@Table(name = "Pais", catalog = "mytokyo2020")
public class Pais implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7071339722495372231L;
	private int idPais;
	private String nombre;
	private Integer medallasOro;
	private Integer medallasPlata;
	private Integer medallasBronce;
	private Integer posRanking;
	private Date lastModificaton;
	private Set<Deportista> deportistas = new HashSet<Deportista>(0);

	public Pais() {
	}

	public Pais(int idPais, Date lastModificaton) {
		this.idPais = idPais;
		this.lastModificaton = lastModificaton;
	}

	public Pais(int idPais, String nombre, Integer medallasOro, Integer medallasPlata, Integer medallasBronce,
			Integer posRanking, Date lastModificaton, Set<Deportista> deportistas) {
		this.idPais = idPais;
		this.nombre = nombre;
		this.medallasOro = medallasOro;
		this.medallasPlata = medallasPlata;
		this.medallasBronce = medallasBronce;
		this.posRanking = posRanking;
		this.lastModificaton = lastModificaton;
		this.deportistas = deportistas;
	}

	@Id

	@Column(name = "idPais", unique = true, nullable = false)
	public int getIdPais() {
		return this.idPais;
	}

	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}

	@Column(name = "Nombre", length = 45)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	@Column(name = "PosRanking")
	public Integer getPosRanking() {
		return this.posRanking;
	}

	public void setPosRanking(Integer posRanking) {
		this.posRanking = posRanking;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LastModificaton", nullable = false, length = 0)
	public Date getLastModificaton() {
		return this.lastModificaton;
	}

	public void setLastModificaton(Date lastModificaton) {
		this.lastModificaton = lastModificaton;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pais")
	public Set<Deportista> getDeportistas() {
		return this.deportistas;
	}

	public void setDeportistas(Set<Deportista> deportistas) {
		this.deportistas = deportistas;
	}

}
