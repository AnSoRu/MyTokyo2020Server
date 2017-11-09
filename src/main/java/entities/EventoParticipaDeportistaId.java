package entities;
// Generated 09-nov-2017 18:04:30 by Hibernate Tools 5.2.6.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * EventoParticipaDeportistaId generated by hbm2java
 */
@Embeddable
public class EventoParticipaDeportistaId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int eventoIdEvento;
	private int deportistaIdDeportista;

	public EventoParticipaDeportistaId() {
	}

	public EventoParticipaDeportistaId(int eventoIdEvento, int deportistaIdDeportista) {
		this.eventoIdEvento = eventoIdEvento;
		this.deportistaIdDeportista = deportistaIdDeportista;
	}

	@Column(name = "Evento_idEvento", nullable = false)
	public int getEventoIdEvento() {
		return this.eventoIdEvento;
	}

	public void setEventoIdEvento(int eventoIdEvento) {
		this.eventoIdEvento = eventoIdEvento;
	}

	@Column(name = "Deportista_idDeportista", nullable = false)
	public int getDeportistaIdDeportista() {
		return this.deportistaIdDeportista;
	}

	public void setDeportistaIdDeportista(int deportistaIdDeportista) {
		this.deportistaIdDeportista = deportistaIdDeportista;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EventoParticipaDeportistaId))
			return false;
		EventoParticipaDeportistaId castOther = (EventoParticipaDeportistaId) other;

		return (this.getEventoIdEvento() == castOther.getEventoIdEvento())
				&& (this.getDeportistaIdDeportista() == castOther.getDeportistaIdDeportista());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getEventoIdEvento();
		result = 37 * result + this.getDeportistaIdDeportista();
		return result;
	}

}