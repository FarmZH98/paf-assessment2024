package ibf2024.assessment.paf.batch4.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import ibf2024.assessment.paf.batch4.models.Order;
import ibf2024.assessment.paf.batch4.util.Utils;

@Repository
public class OrderRepository {

	// TODO: Task 5
	@Autowired
    private MongoTemplate template;

	public void order(Order order) throws Exception {

        Order savedOrder = template.save(order, Utils.C_ORDERS);
    }

}
