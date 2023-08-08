package br.com.product.productapi.modules.sales.rabbitmq;

import br.com.product.productapi.modules.sales.dto.SalesConfirmationDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SalesConfirmationSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Value("${app-config.rabbit.exchange.product}")
    private String productTopicExchange;

    @Value("${app-config.rabbit.routingKey.sales-confirmation}")
    private String salesConfirmationKey;


    public void sendSalesConfirmationMessage(SalesConfirmationDTO salesConfirmationDTO) {

         try {
             log.info("Sending message: {}", new ObjectMapper().writeValueAsString(salesConfirmationDTO));
             rabbitTemplate.convertAndSend(productTopicExchange, salesConfirmationKey, salesConfirmationDTO);
             log.info("Message was sent sucessfully!");

         } catch (Exception e) {
             log.info("Error while trying to send sales confirmation message: ",e);

         }
    }
}
