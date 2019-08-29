package microservices.book.multiplicatio.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventDispatcher {

	private RabbitTemplate rabbitTemplate;

	private String multiplicationExchange;

	private String multiplicationSolvedRoutingKey;

	@Autowired
	public EventDispatcher(RabbitTemplate rabbitTemplate,
			@Value("${multiplication.exchange}") String multiplicationExchange,
			@Value("${multiplication.solved.key}") String multiplicationSolvedRoutingKey) {
		this.rabbitTemplate = rabbitTemplate;
		this.multiplicationExchange = multiplicationExchange;
		this.multiplicationSolvedRoutingKey = multiplicationSolvedRoutingKey;
	}

	public void send(MultiplicationSolvedEvent multiplicationSolvedEvent) {
		rabbitTemplate.convertAndSend(multiplicationExchange, multiplicationSolvedRoutingKey,
				multiplicationSolvedEvent);
	}

}
