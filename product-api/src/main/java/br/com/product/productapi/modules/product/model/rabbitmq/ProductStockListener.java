package br.com.product.productapi.modules.product.model.rabbitmq;

import br.com.product.productapi.modules.product.model.dto.ProductStockDTO;
import br.com.product.productapi.modules.product.model.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductStockListener {

    @Autowired
    private ProductService productService;

    @RabbitListener(queues = "${app-config.rabbit.queue.product-stock}")
    public void recieveProductStockMessage(ProductStockDTO product) throws JsonProcessingException {
         log.info("Recieving message with data: {} and Transaction ID ", new ObjectMapper().writeValueAsString(product),
                 product.getTransactionId());
         productService.updateProductStock(product);
    }
}
