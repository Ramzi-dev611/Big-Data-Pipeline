package tn.project.datavisualization.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tn.project.datavisualization.entity.Prediction;
import tn.project.datavisualization.repository.PredictionRepository;
import com.google.gson.Gson;


@Service
public class PredictionListener {
    private PredictionRepository repository;
    @Autowired
    public PredictionListener(PredictionRepository predictionRepository){
        this.repository = predictionRepository;
    }

    @KafkaListener(topics = "receive-prediction", groupId = "group_id")
    public void receivePrediction(String predictionAsJson){
        Prediction prediction = new Gson().fromJson(predictionAsJson, Prediction.class);
        repository.save(prediction);
    }
}
