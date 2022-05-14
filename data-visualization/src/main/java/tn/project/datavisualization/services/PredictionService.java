package tn.project.datavisualization.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.project.datavisualization.entity.Prediction;
import tn.project.datavisualization.repository.PredictionRepository;

import java.util.List;

@Service
public class PredictionService {
    private PredictionRepository predictionRepository;

    @Autowired
    public PredictionService(PredictionRepository repository){
        this.predictionRepository = repository;
    }
    public List<Prediction> listMadePrediction(){
        return this.predictionRepository.findAll();
    }
}
