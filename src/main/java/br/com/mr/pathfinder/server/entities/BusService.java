package br.com.mr.pathfinder.server.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Geometry;

@Entity
// @Table(name = "BUS_SERVICE", uniqueConstraints = {
// @UniqueConstraint(columnNames = {"CODE"}) })
@Table(name = "BUS_SERVICE")
@SequenceGenerator(name = "BUS_SERVICE_SEQUENCY", sequenceName = "BUS_SERVICE_SEQUENCY")
public class BusService implements Serializable {
	private static final long serialVersionUID = -8256492643595544989L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUS_SERVICE_SEQUENCY")
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "CODE")
	private String code;

	@Column(name = "DESCRIPTION", length = 2048)
	private String description;

	@OneToMany(mappedBy = "busService")
	private Set<Intinerary> intineraries;

	@Column(name = "THE_GEOM")
	@Type(type = "org.hibernatespatial.GeometryUserType")
	@JsonIgnore
	private Geometry path;

	@ManyToMany
	@JoinTable(name = "BUSSERVICE_BUSTOP", joinColumns = @JoinColumn(name = "BUS_SERVICE_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "BUS_STOP_ID", referencedColumnName = "ID"))
	@JsonIgnore
	private Set<BusStop> busStops;

	public BusService() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Intinerary> getIntineraries() {
		return intineraries;
	}

	public void setIntineraries(Set<Intinerary> intineraries) {
		this.intineraries = intineraries;
	}

	public Set<BusStop> getBusStops() {
		if (busStops == null) {
			setBusStops(new HashSet<BusStop>());
		}
		return busStops;
	}

	public void setBusStops(Set<BusStop> busStops) {
		this.busStops = busStops;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Geometry getPath() {
		return path;
	}

	public void setPath(Geometry path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "BusService [id=" + id + ", code=" + code + ", name=" + name + "]";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
