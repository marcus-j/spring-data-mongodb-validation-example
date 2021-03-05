package de.marcusjanke.examples.mongodbvalidation;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;

@Data
@Accessors(chain = true)
@Document
public class MyDocument {

    @Id
    private String id;
    @Min(5)
    private int number;
}
