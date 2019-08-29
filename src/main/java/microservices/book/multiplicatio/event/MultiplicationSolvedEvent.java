package microservices.book.multiplicatio.event;

import java.io.Serializable;

public class MultiplicationSolvedEvent implements Serializable {

	private Long multiplicationResultAttemptId;
	private Long userId;
	private boolean correct;

	public MultiplicationSolvedEvent(Long multiplicationResultAttemptId, Long userId, boolean correct) {
		this.multiplicationResultAttemptId = multiplicationResultAttemptId;
		this.userId = userId;
		this.correct = correct;
	}

	public Long getMultiplicationResultAttemptId() {
		return multiplicationResultAttemptId;
	}

	public Long getUserId() {
		return userId;
	}

	public boolean isCorrect() {
		return correct;
	}

	@Override
	public String toString() {
		return "MultiplicationSolvedEvent [multiplicationResultAttemptId=" + multiplicationResultAttemptId + ", userId="
				+ userId + ", correct=" + correct + "]";
	}

	
}
