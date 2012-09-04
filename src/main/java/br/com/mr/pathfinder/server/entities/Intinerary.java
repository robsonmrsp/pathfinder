package br.com.mr.pathfinder.server.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "INTINERARY", uniqueConstraints = { @UniqueConstraint(columnNames = { "INDEX", "BUS_SERVICE_ID" }) })
@SequenceGenerator(name = "INTINERARY_SEQUENCY", sequenceName = "INTINERARY_SEQUENCY")
public class Intinerary implements Serializable {
	private static final long serialVersionUID = 830912508781271940L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INTINERARY_SEQUENCY")
	private Long id;

	@Column(name="INDEX")
	private Integer index;

	@Column(name="NAME")
	private String name;

	@ManyToOne
	@JoinColumn(name = "BUS_SERVICE_ID", referencedColumnName = "ID")
	private BusService busService;

	public Intinerary(Integer index, String name) {
		super();
		this.index = index;
		this.name = name;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BusService getBusService() {
		return busService;
	}

	public void setBusService(BusService busService) {
		this.busService = busService;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
