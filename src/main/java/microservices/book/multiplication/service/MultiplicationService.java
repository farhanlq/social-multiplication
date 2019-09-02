package microservices.book.multiplication.service;

import java.util.List;

import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;

public interface MultiplicationService {

	Multiplication createRandomMultiplication();

	boolean checkAttempt(MultiplicationResultAttempt resultAttempt);

	public List<MultiplicationResultAttempt> getStatsForUser(String userAlias);

	public MultiplicationResultAttempt getResultById(final Long resultId);

}
