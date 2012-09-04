package br.com.mr.pathfinder.server.dao;

import java.util.List;

import javax.inject.Named;

import org.hibernate.criterion.Restrictions;

import br.com.mr.pathfinder.server.entities.BusService;

@Named
public class DaoBusService extends Dao<BusService> {
	public DaoBusService() {
		clazz = BusService.class;
	}

	public List<BusService> findByCode(String trim) {
		List<BusService> list = criteria().add(Restrictions.eq("code", trim)).list();
		return list;
	}
}
