package it.antonio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "auto")
public class Auto{
	
	@Id
	@Column(name = "targa", unique = true)
	private String targa;

	@Column(name = "marca", nullable = false)
	private String marca;

	@Column(name = "modello", nullable = false)
	private String modello;

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}
	
	@Override
	public String toString() {
		return "Auto [targa=" + targa + ", marca=" + marca + ", modello=" + modello + "]";
	}
	


}