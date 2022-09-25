package rccloud.data;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import rccloud.Ingredient;
import rccloud.RiceNoodle;
import rccloud.RiceNoodleOrder;

@Repository
public class JdbcOrderRepository implements OrderRepository {
	
	private JdbcOperations jdbcOperations; // we can use 'JdbcTemplate' class instead

	public JdbcOrderRepository(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}

	@Override
	@Transactional
	public RiceNoodleOrder save(RiceNoodleOrder order) {
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
				"insert into Rice_Noodle_Order "
				+ "(delivery_name, delivery_street, delivery_town, delivery_district, "
				+ "delivery_province, cc_number, cc_expiration, cc_cvv, placed_at) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)", 
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, 
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP);
		
		pscf.setReturnGeneratedKeys(true);
		
		order.setPlacedAt(new Date());
		
		PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(
				order.getDeliveryName(), order.getDeliveryStreet(),
				order.getDeliveryTown(), order.getDeliveryDistrict(),
				order.getDeliveryProvince(), order.getCcNumber(),
				order.getCcExpiration(), order.getCcCVV(), order.getPlacedAt()));
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcOperations.update(psc, keyHolder);
		
		long orderId = keyHolder.getKey().longValue();
		
		List<RiceNoodle> noodles = order.getRiceNoodles();
		
		int i = 0;
		for (RiceNoodle noodle : noodles) {
			saveRiceNoodle(orderId, i++, noodle);
		}
		
		return order;
	}
	
	private long saveRiceNoodle(long orderId, int orderKey, RiceNoodle noodle) {
		noodle.setCreatedAt(new Date());
		
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
				"insert into Rice_Noodle (name, created_at, rice_noodle_order, rice_noodle_order_key) "
				+ "values (?, ?, ?, ?)", 
				Types.VARCHAR, Types.TIMESTAMP, Types.BIGINT, Types.BIGINT);
			
		pscf.setReturnGeneratedKeys(true);
		
		PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(
				noodle.getName(), noodle.getCreatedAt(), orderId, orderKey));
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcOperations.update(psc, keyHolder);
		
		long noodleId = keyHolder.getKey().longValue();
		noodle.setId(noodleId);
		
		saveIngredientRefs(noodleId, noodle.getIngredients());
		
		return noodleId;
	}
	
	private void saveIngredientRefs(long noodleId, List<Ingredient> ingredients) {
		int key = 0;
		for (Ingredient ingredient : ingredients) {
			jdbcOperations.update("insert into Ingredient_Ref (ingredient, rice_noodle, rice_noodle_key) "
					+ "values (?, ?, ?)", 
					ingredient.getId(), noodleId, key++);
		}
	}
	
}
