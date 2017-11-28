package models;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import controllers.PlayersController;

public class MarketPlaceTest {
	MarketServiceExt marketService;
	MarketPlace marketNew;
	ArrayList<Player>lista;
	@Before
	public void initialize() {
		marketService=mock(MarketServiceExt.class);
		lista=new ArrayList<Player>();
		Player a=new Player("Asier","M","Real Sociedad","ESP",20);
		a.setPlayer_points((double) 20);
		lista.add(a);
		Player b=new Player("Paula","G","Real Sociedad","ESP",30);
		b.setPlayer_points((double) 50);
		lista.add(b);
		Player c=new Player("Javier","M","Real Madrid","ESP",10);
		c.setPlayer_points((double)30);
		lista.add(c);		
		Player d=new Player("Juan","F","Bilbao","ESP",15);
		d.setPlayer_points((double)60);
		lista.add(d);
		marketNew=new MarketPlace(lista);
		when(marketService.setMarketTotalPoints(lista)).thenReturn((double)1000);
		when(marketService.setMarketTotalValue(lista)).thenReturn((double)20000);
	}
	@Test
	public void test() {
		assertEquals(4,marketNew.getPlayersList().size());
		assertEquals("Javier",marketNew.getPlayersList().get(2).getPlayerShirt());
		marketNew.setMarketServiceExt(marketService);
		marketNew.setMarketTotalPoints();
		marketNew.setMarketTotalValue();
		assertEquals(20*20000/1000,(double)marketNew.setValue().get(0).getPlayer_value(),0);
		marketNew.setMarketValuePlayers();
		marketNew.setMarketServiceExt(new MarketServiceExt());
		assertNotEquals(20*200000/1000, (double)marketNew.setValue().get(0).getPlayer_value(),0);
		verify(marketService,times(2)).setMarketTotalPoints(lista); 
	}	
}
