package microservices.book.multiplication.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;
import microservices.book.multiplication.domain.User;
import microservices.book.multiplication.repository.MultiplicationResultAttemptRepository;
import microservices.book.multiplication.repository.UserRepository;

@Service
public class MultiplicationServiceImpl implements MultiplicationService {

	private RandomGeneratorService randomGeneratorService;

	private MultiplicationResultAttemptRepository attemptRepository;

	private UserRepository userRepository;

	@Autowired
	public MultiplicationServiceImpl(RandomGeneratorService randomGeneratorService,
			MultiplicationResultAttemptRepository attemptRepository, UserRepository userRepsoRepository) {
		this.randomGeneratorService = randomGeneratorService;
		this.attemptRepository = attemptRepository;
		this.userRepository = userRepsoRepository;
	}

	@Override
	public Multiplication createRandomMultiplication() {
		int factorA = randomGeneratorService.generateRandomFactor();
		int factorB = randomGeneratorService.generateRandomFactor();

		return new Multiplication(factorA, factorB);
	}

	@Transactional
	@Override
	public boolean checkAttempt(MultiplicationResultAttempt resultAttempt) {

		Optional<User> user = userRepository.findByAlias(resultAttempt.getUser().getAlias());

		boolean isCorrect = resultAttempt.getResultAttempt() == resultAttempt.getMultiplication().getFactorA()
				* resultAttempt.getMultiplication().getFactorB();

		Assert.isTrue(!resultAttempt.isCorrect());

		MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(
				user.orElse(resultAttempt.getUser()), resultAttempt.getMultiplication(),
				resultAttempt.getResultAttempt(), isCorrect);

		attemptRepository.save(checkedAttempt);
		return isCorrect;
	}

	@Override
	public List<MultiplicationResultAttempt> getStatsForUser(String userAlias) {
		return attemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias);
	}
	
	

}
