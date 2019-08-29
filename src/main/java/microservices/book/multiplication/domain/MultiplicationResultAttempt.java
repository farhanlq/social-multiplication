package microservices.book.multiplication.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public final class MultiplicationResultAttempt {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "USER_ID")
	private final User user;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "MULTIPLICATION_ID")
	private final Multiplication multiplication;
	private final int resultAttempt;

	private final boolean correct;

	public MultiplicationResultAttempt() {
		user = null;
		multiplication = null;
		resultAttempt = -1;
		correct = false;

	}

	public MultiplicationResultAttempt(User user, Multiplication multiplication, int resultAttempt, boolean correct) {
		this.user = user;
		this.multiplication = multiplication;
		this.resultAttempt = resultAttempt;
		this.correct = correct;
	}

	public User getUser() {
		return user;
	}

	public Multiplication getMultiplication() {
		return multiplication;
	}

	public int getResultAttempt() {
		return resultAttempt;
	}

	public boolean isCorrect() {
		return correct;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "MultiplicationResultAttempt [user=" + user + ", multiplication=" + multiplication + ", resultAttempt="
				+ resultAttempt + ", correct=" + correct + "]";
	}

}
