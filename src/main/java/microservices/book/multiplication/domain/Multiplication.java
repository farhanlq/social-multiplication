package microservices.book.multiplication.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Multiplication {

	@Id
	@GeneratedValue
	@Column(name = "MULTIPLICATION_ID")
	private Long id;

	private int factorA;

	private int factorB;

	public Multiplication() {
	}
	
	public Multiplication(int factorA, int factorB) {
		this.factorA = factorA;
		this.factorB = factorB;

	}

	public int getFactorA() {
		return factorA;
	}

	public int getFactorB() {
		return factorB;
	}

	@Override
	public String toString() {
		return "Multiplication [factorA=" + factorA + ", factorB=" + factorB + "]";
	}

}
