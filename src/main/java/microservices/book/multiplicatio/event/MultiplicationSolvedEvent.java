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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (correct ? 1231 : 1237);
		result = prime * result
				+ ((multiplicationResultAttemptId == null) ? 0 : multiplicationResultAttemptId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MultiplicationSolvedEvent other = (MultiplicationSolvedEvent) obj;
		if (correct != other.correct)
			return false;
		if (multiplicationResultAttemptId == null) {
			if (other.multiplicationResultAttemptId != null)
				return false;
		} else if (!multiplicationResultAttemptId.equals(other.multiplicationResultAttemptId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	
}
