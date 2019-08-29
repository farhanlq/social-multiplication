package microservices.book.multiplication.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;
import microservices.book.multiplication.domain.User;
import microservices.book.multiplication.repository.MultiplicationResultAttemptRepository;
import microservices.book.multiplication.repository.UserRepository;

public class MultiplicationServiceImplTest {

	private MultiplicationServiceImpl multiplicaitonServiceImpl;

	@Mock
	private RandomGeneratorService randomGeneratorService;

	@Mock
	private MultiplicationResultAttemptRepository attemptRepository;

	@Mock
	private UserRepository userRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		multiplicaitonServiceImpl = new MultiplicationServiceImpl(randomGeneratorService, attemptRepository,
				userRepository);
	}

	@Test
	public void createRandomMultiplicationTest() {
		when(randomGeneratorService.generateRandomFactor()).thenReturn(50, 30);
		Multiplication multiplication = multiplicaitonServiceImpl.createRandomMultiplication();
		assertThat(multiplication.getFactorA()).isEqualTo(50);
		assertThat(multiplication.getFactorB()).isEqualTo(30);
	}

	@Test
	public void checkCorrectAttemptTest() {
		Multiplication multiplication = new Multiplication(50, 60);
		User user = new User("farhan_laeeq");
		MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3000, false);
		MultiplicationResultAttempt verifiedAttempt = new MultiplicationResultAttempt(user, multiplication, 3000, true);
		when(userRepository.findByAlias("farhan_laeeq")).thenReturn(Optional.empty());
		boolean attemptResult = multiplicaitonServiceImpl.checkAttempt(attempt);
		assertThat(attemptResult).isTrue();

		verify(attemptRepository).save(ArgumentMatchers.refEq(verifiedAttempt));

	}

	@Test
	public void checkWrongAttemptTest() {
		Multiplication multiplication = new Multiplication(50, 60);
		User user = new User("farhan_laeeq");
		MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3010, false);
		when(userRepository.findByAlias("farhan_laeeq")).thenReturn(Optional.empty());
		boolean attemptResult = multiplicaitonServiceImpl.checkAttempt(attempt);
		assertThat(attemptResult).isFalse();

		verify(attemptRepository).save(ArgumentMatchers.refEq(attempt));
	}

	@Test
	public void retrieveStatsTest() {
		Multiplication multiplication = new Multiplication(50, 60);
		User user = new User("john_doe");
		MultiplicationResultAttempt attempt1 = new MultiplicationResultAttempt(user, multiplication, 3010, false);
		MultiplicationResultAttempt attempt2 = new MultiplicationResultAttempt(user, multiplication, 3051, false);
		List<MultiplicationResultAttempt> latestAttempts = new ArrayList<>();
		latestAttempts.add(attempt1);
		latestAttempts.add(attempt2);
		when(userRepository.findByAlias("farhan_laeeq")).thenReturn(Optional.empty());
		when(attemptRepository.findTop5ByUserAliasOrderByIdDesc("farhan_laeeq")).thenReturn(latestAttempts);
		List<MultiplicationResultAttempt> latestAttemptsResult = multiplicaitonServiceImpl
				.getStatsForUser("farhan_laeeq");

		assertThat(latestAttemptsResult).isEqualTo(latestAttempts);

	}
}
