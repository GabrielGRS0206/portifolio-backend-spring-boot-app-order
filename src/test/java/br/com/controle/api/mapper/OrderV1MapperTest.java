/**
 * 
 */
package br.com.controle.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.controle.api.dto.request.OrderItemRequestV1Dto;
import br.com.controle.api.dto.request.OrderRequestV1Dto;
import br.com.controle.api.dto.response.OrderResponseV1Dto;
import br.com.controle.domain.model.Order;
import br.com.controle.domain.model.OrderItem;
import br.com.controle.domain.model.Product;
import br.com.controle.domain.utils.Utils;

/**
 * @author Gabriel Rocha Severino
 *
 */
class OrderV1MapperTest {

	OrderV1Mapper mapper = new OrderV1Mapper();

	/**
	 * Test method for
	 * {@link OrderV1Mapper#entityToDto(br.com.controle.domain.model.Order, java.lang.String)}.
	 */
	@Test
	void testEntityToDto() {
		OrderResponseV1Dto response = mapper.entityToDto(mockOrder(), OrderV1Mapper.COMPLETE);
		assertNotNull(response, "response is null");
	}

	/**
	 * @return
	 */
	private Order mockOrder() {
		var order = new Order();
		var item = new OrderItem();
		var product = new Product();
		product.setId(1l);
		product.setDescription(Utils.EMPTY);
		product.setObservation(Utils.EMPTY);
		product.setUnitPrice(BigDecimal.ONE);
		item.setProduct(product);
		order.setItems(Arrays.asList());
		return order;
	}

	/**
	 * Test method for
	 * {@link OrderV1Mapper#dtoToEntity(OrderRequestV1Dto)}.
	 */
	@Test
	void testDtoToEntity() {
		Order order = mapper.dtoToEntity(mock());
		assertNotNull(order, "order is null");
	}

	private OrderRequestV1Dto mock() {
		var request = new OrderRequestV1Dto();
		var item= new OrderItemRequestV1Dto();
		item.setAdditional(BigDecimal.ONE);
		item.setPrice(BigDecimal.ONE);
		item.setQuantity(BigDecimal.ONE);
		request.setItems(Arrays.asList(item));
		request.setIdClient(1l);
		return request;
	}
	

	/**
	 * Test method for
	 * {@link OrderV1Mapper#listToDto(OrderRequestV1Dto)}.
	 */
	@Test
	void testListToDto() {
		List<OrderResponseV1Dto> orders = mapper.listToDto(orders(), OrderV1Mapper.SUMMARY);
		assertNotNull(orders, "order is null");
		assertEquals(orders.size(), 3);
	}

	private List<Order> orders() {
		var order = new Order();
		order.setItems(Arrays.asList(new OrderItem()));
		return Arrays.asList(order,order,order);
	}
	
	

}
