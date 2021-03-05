package de.marcusjanke.examples.mongodbvalidation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MongoValidationSpringBootTest {

	@Autowired
	private MyDocumentRepository repository;

	@Test
	void shouldFailOnInvalidEntity() {
		var invalidDocument = new MyDocument().setNumber(4);

		assertThatThrownBy(() -> repository.save(invalidDocument)).isInstanceOf(ConstraintViolationException.class);
	}
}
