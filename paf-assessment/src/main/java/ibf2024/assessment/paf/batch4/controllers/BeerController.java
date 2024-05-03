package ibf2024.assessment.paf.batch4.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ibf2024.assessment.paf.batch4.models.Beer;
import ibf2024.assessment.paf.batch4.models.Brewery;
import ibf2024.assessment.paf.batch4.models.Order;
import ibf2024.assessment.paf.batch4.models.Style;
import ibf2024.assessment.paf.batch4.models.BeerOrder;
import ibf2024.assessment.paf.batch4.services.BeerService;

@Controller
@RequestMapping 
public class BeerController {

	@Autowired
	private BeerService beerService;

	//TODO Task 2 - view 0
	@GetMapping(path={"/", "index.html"}) 
    public ModelAndView loadView0Page() {
        ModelAndView mav = new ModelAndView("view0");

		List<Style> beerStyles = beerService.getStyles();

        mav.addObject("styles", beerStyles);

        return mav;
    }
	
	//TODO Task 3 - view 1
	//@{/beer/style/{id}?styleName={styleName}(id=*{styleId}, styleName=*{name})
	//http://localhost:8080/beer/style/26?styleName=American-Style%20Pale%20Ale
	@GetMapping(path={"/beer/style/{id}"}) 
    public ModelAndView loadView1Page(@PathVariable("id") int id,
									@RequestParam String styleName) {
        ModelAndView mav = new ModelAndView("view1");
		mav.addObject("styleName", styleName);

		List<Beer> beers = beerService.getBreweriesByBeer(id);

        mav.addObject("beers", beers);

        return mav;
    }

	//TODO Task 4 - view 2
	@GetMapping(path={"/beer/brewery/{id}"}) 
    public ModelAndView loadView2Page(@PathVariable("id") int id,
									@RequestParam String breweryName) {

        ModelAndView mav = new ModelAndView("view2");
		mav.addObject("breweryName", breweryName);
		//System.out.println(">>>>> Testing: loadview2Page()");

		Brewery brewery = beerService.getBeersFromBrewery(id);

		mav.addObject("brewery", brewery);

        return mav;
    }
	
	//TODO Task 5 - view 2, place order
	//>>>>>>> PlaceOrder(): {quantity=[2, 2, 0, 0, 0, 0, 0, 0], beerId=[5735, 5736, 5737, 5738, 5739, 5740, 5741, 5843]}
	//throws default exception page if unable to place order
	@PostMapping(path={"/brewery/{breweryId}/order"}) 
    public ModelAndView placeOrder(@PathVariable("breweryId") int id, @RequestBody MultiValueMap<String,String> beerForm) throws Exception {

		ModelAndView mav = new ModelAndView("view3");

		List<String> beerIdRawList = beerForm.get("beerId");
		List<String> quantityRawList = beerForm.get("quantity");

		Order order = new Order();
		order.setBreweryId(id);
		List<BeerOrder> beerOrders = new LinkedList<>();

		for(int i=0; i<beerIdRawList.size(); ++i) {
			if(Integer.parseInt(quantityRawList.get(i))<=0) continue; //ignore 0 quantity ones

			BeerOrder beerOrder = new BeerOrder();
			beerOrder.setBeerId(Integer.parseInt(beerIdRawList.get(i)));
			beerOrder.setQuantity(Integer.parseInt(quantityRawList.get(i)));
			beerOrders.add(beerOrder);
		}
		
		order.setOrders(beerOrders);
		String orderId = beerService.placeOrder(order);
		mav.addObject("orderId", orderId);

		return mav;
        //return mav;
    }
}
