package ibf2024.assessment.paf.batch4.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2024.assessment.paf.batch4.models.Beer;
import ibf2024.assessment.paf.batch4.models.Brewery;
import ibf2024.assessment.paf.batch4.models.Style;

@Repository
public class BeerRepository implements BeerQueries {

	@Autowired
    private JdbcTemplate jdbcTemplate;

	// DO NOT CHANGE THE SIGNATURE OF THIS METHOD
	public List<Style> getStyles() {
		// TODO: Task 2
		final SqlRowSet rs = jdbcTemplate.queryForRowSet(GET_STYLES);
        List<Style> beerStyles = new LinkedList<>();
        Boolean isDataEmpty = true;

        while(rs.next()) {
            isDataEmpty = false;
			Style style = new Style();
			style.setStyleId(rs.getInt("style_id"));
			style.setName(rs.getString("style_name"));
			style.setBeerCount(rs.getInt("beer_count"));
			beerStyles.add(style);
        }

        if(isDataEmpty) {
            System.out.println(">>> There is no beer... This should not happen");
            //throw new OrderIdNotFoundExceptio("order ID not found.");
        }

		return beerStyles;
	}
		
	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public List<Beer> getBreweriesByBeer(int styleId) {
		// TODO: Task 3
		final SqlRowSet rs = jdbcTemplate.queryForRowSet(GET_BREWERIES_BY_BEER, styleId);
        List<Beer> beers = new LinkedList<>();
        Boolean isDataEmpty = true;

        while(rs.next()) {
            isDataEmpty = false;
			Beer beer = new Beer();
			beer.setBeerName(rs.getString("beer_name"));
			beer.setBeerId(rs.getInt("beer_id"));
			beer.setBreweryId(rs.getInt("brewery_id"));
			beer.setBeerDescription(rs.getString("description"));
			beer.setBreweryName(rs.getString("brewery_name"));
			beers.add(beer);
        }

        if(isDataEmpty) {
            System.out.println(">>> There is no beer... This should not happen");
            //throw new OrderIdNotFoundExceptio("order ID not found.");
        }

		return beers;
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public Optional<Brewery> getBeersFromBrewery(int breweryId) {
		// TODO: Task 4
		final SqlRowSet rs = jdbcTemplate.queryForRowSet(GET_BEERS_FROM_BREWERY, breweryId);
        Brewery brewery = new Brewery();
		List<Beer> beers = new LinkedList<>();
        Boolean isBreweryInfoFilled = false;
		//System.out.println(">>>> getBeersFromBrewery address1" + rs.getString("beer_description"));

		
        while(rs.next()) {
			Beer beer = new Beer();
			beer.setBeerName(rs.getString("beer_name"));
			beer.setBeerId(rs.getInt("beer_id"));
			beer.setBreweryId(breweryId);
			beer.setBeerDescription(rs.getString("beer_description"));
			beer.setBreweryName(rs.getString("brewery_name"));
			beers.add(beer);

			if(isBreweryInfoFilled) continue;
			brewery.setName(rs.getString("brewery_name"));
			brewery.setDescription(rs.getString("brewery_description"));
			brewery.setAddress1(rs.getString("address1"));
			brewery.setAddress2(rs.getString("address2"));
			brewery.setPhone(rs.getString("phone"));
			brewery.setWebsite(rs.getString("website"));
			brewery.setCity(rs.getString("city"));
			brewery.setBreweryId(breweryId);
			isBreweryInfoFilled = true;
        }

		brewery.setBeers(beers);

		return Optional.of(brewery);
	}
}
