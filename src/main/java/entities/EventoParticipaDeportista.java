package entities;
// Generated 12-nov-2017 16:38:26 by Hibernate Tools 5.2.6.Final

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * EventoParticipaDeportista generated by hbm2java
 */
@Entity
@Table(name = "Evento_participa_Deportista", catalog = "mytokyo2020")
public class EventoParticipaDeportista implements java.io.Serializable {

	private EventoParticipaDeportistaId id;
	private Deportista deportista;
	private Evento evento;
	private Integer posicionFinal;
	private Date lastModification;

	public EventoParticipaDeportista() {
	}

	public EventoParticipaDeportista(EventoParticipaDeportistaId id, Deportista deportista, Evento evento,
			Date lastModification) {
		this.id = id;
		this.deportista = deportista;
		this.evento = evento;
		this.lastModification = lastModification;
	}

	public EventoParticipaDeportista(EventoParticipaDeportistaId id, Deportista deportista, Evento evento,
			Integer posicionFinal, Date lastModification) {
		this.id = id;
		this.deportista = deportista;
		this.evento = evento;
		this.posicionFinal = posicionFinal;
		this.lastModification = lastModification;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "eventoIdEvento", column = @Column(name = "Evento_idEvento", nullable = false)),
			@AttributeOverride(name = "deportistaIdDeportista", column = @Column(name = "Deportista_idDeportista", nullable = false)) })
	public EventoParticipaDeportistaId getId() {
		return this.id;
	}

	public void setId(EventoParticipaDeportistaId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Deportista_idDeportista", nullable = false, insertable = false, updatable = false)
	public Deportista getDeportista() {
		return this.deportista;
	}

	public void setDeportista(Deportista deportista) {
		this.deportista = deportista;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Evento_idEvento", nullable = false, insertable = false, updatable = false)
	public Evento getEvento() {
		return this.evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	@Column(name = "PosicionFinal")
	public Integer getPosicionFinal() {
		return this.posicionFinal;
	}

	public void setPosicionFinal(Integer posicionFinal) {
		this.posicionFinal = posicionFinal;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LastModification", nullable = false, length = 0)
	public Date getLastModification() {
		return this.lastModification;
	}

	public void setLastModification(Date lastModification) {
		this.lastModification = lastModification;
	}

}
