package ibf2024.assessment.paf.batch4.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2024.assessment.paf.batch4.models.Beer;
import ibf2024.assessment.paf.batch4.models.Brewery;
import ibf2024.assessment.paf.batch4.models.Order;
import ibf2024.assessment.paf.batch4.models.Style;
import ibf2024.assessment.paf.batch4.repositories.BeerRepository;
import ibf2024.assessment.paf.batch4.repositories.OrderRepository;

@Service
public class BeerService {

	@Autowired
	private BeerRepository beerRepository;

	@Autowired 
	private OrderRepository orderRepository;

	public List<Style> getStyles() {

		return beerRepository.getStyles();
	}

	public List<Beer> getBreweriesByBeer(int styleId) {

		return beerRepository.getBreweriesByBeer(styleId);
	}

	public Brewery getBeersFromBrewery(int breweryId) {
		
		Optional<Brewery> opt = beerRepository.getBeersFromBrewery(breweryId);
		if (opt.isPresent()) {
			//System.out.println(">>>>>> getBeersFromBrewery():" + opt.get().toString());
			return opt.get();
		}

		System.out.println(">>>>>> getBeersFromBrewery(): brewery is empty.");

		return new Brewery();
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	//don't need transactional because we are only saving one.
	public String placeOrder(Order order) throws Exception{
		// TODO: Task 5 
		String orderId = UUID.randomUUID().toString().substring(0, 8);
		order.setOrderId(orderId);
		order.setOrderDate(new Date());
		orderRepository.order(order);

		return orderId;
	}

}
