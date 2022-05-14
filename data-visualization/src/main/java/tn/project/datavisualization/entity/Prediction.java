package tn.project.datavisualization.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tn.project.datavisualization.dto.PredictionRequestDto;

@Document(collection = "predictions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Prediction extends PredictionRequestDto {
    @Id
    private String id;
    private String prediction;
}
