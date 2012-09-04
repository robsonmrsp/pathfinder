package br.com.mr.pathfinder.server;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.mr.pathfinder.server.dao.DaoBusStop;
import br.com.mr.pathfinder.server.entities.BusStop;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class CreateObjectsTest {

	@Inject
	DaoBusStop daoBusStop;

	@Test()
	public void testeDao() {
 
		List<BusStop> stop = daoBusStop.findNextBusStops(-3.701836, -38.589862 );
		assertNotNull("Deveria ter salvo o usuário e definido seu ID", stop);
	}
}
