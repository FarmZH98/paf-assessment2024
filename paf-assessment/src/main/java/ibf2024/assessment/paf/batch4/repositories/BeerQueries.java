package ibf2024.assessment.paf.batch4.repositories;

public interface BeerQueries {
    /*
    select 
    s.id as style_id,
    s.style_name as style_name,
    count(b.style_id) as beer_count
    from styles s
    left join beers b on b.style_id = s.id
    group by s.id
    order by beer_count desc, style_name asc
     */
    public final static String GET_STYLES = 
    """
        select 
        s.id as style_id,
        s.style_name as style_name,
        count(b.style_id) as beer_count
        from styles s
        left join beers b on b.style_id = s.id
        group by s.id
        order by beer_count desc, style_name asc         
    """;

    /*
    select 
    b.style_id as style_id,
    b.name as beer_name,
    b.descript as description,
    bre.name as brewery_name
    from beers b
    left join breweries bre on b.brewery_id=bre.id
    where b.style_id=3
    order by beer_name asc
     */
    public static final String GET_BREWERIES_BY_BEER = 
    """
        select 
        b.id as beer_id,
        b.brewery_id as brewery_id,
        b.name as beer_name,
        b.descript as description,
        bre.name as brewery_name
        from beers b
        left join breweries bre on b.brewery_id=bre.id
        where b.style_id=?
        order by beer_name asc
    """;

    /*
    select 
    b.name as beer_name,
    b.descript as beer_description,
    bre.name as brewery_name,
    bre.descript as brewery_description,
    bre.address1 as address1,
    bre.address2 as address2,
    bre.phone as phone,
    bre.website as website
    from beers b
    left join breweries bre on b.brewery_id=bre.id
    where brewery_id=1412
     */
    public static final String GET_BEERS_FROM_BREWERY = 
    """
        select 
        b.id as beer_id,
        b.name as beer_name,
        b.descript as beer_description,
        bre.name as brewery_name,
        bre.descript as brewery_description,
        bre.address1 as address1,
        bre.address2 as address2,
        bre.phone as phone,
        bre.website as website,
        bre.city as city
        from beers b
        left join breweries bre on b.brewery_id=bre.id
        where brewery_id=?
    """;
}
