package microservices.book.multiplication.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Entity
public final class User {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Long id;

	private final String alias;

	public User() {
		alias = null;
	}

	public User(String alias) {
		this.alias = alias;
	}

	public String getAlias() {
		return alias;
	}

	@Override
	public String toString() {
		return "User [alias=" + alias + "]";
	}

}
