package de.marcusjanke.examples.mongodbvalidation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@Import(MongoValidationConfig.class)
class MongoValidationDataMongoTest {

    @Autowired
    private MyDocumentRepository repository;

    @Test
    void shouldFailOnInvalidEntity() {
        var invalidDocument = new MyDocument().setNumber(4);

        assertThatThrownBy(() -> repository.save(invalidDocument)).isInstanceOf(ConstraintViolationException.class);
    }

    @TestConfiguration
    static class MongoMapKeyDotReplacementConfiguration {
        @Bean
        public LocalValidatorFactoryBean localValidatorFactoryBean() {
            return new LocalValidatorFactoryBean();
        }
    }
}