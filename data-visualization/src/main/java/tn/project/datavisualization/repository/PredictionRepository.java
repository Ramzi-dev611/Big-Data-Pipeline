package tn.project.datavisualization.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.project.datavisualization.entity.Prediction;

public interface PredictionRepository extends MongoRepository<Prediction, String> {
}
