package tn.project.datavisualization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tn.project.datavisualization.dto.PredictionRequestDto;
import tn.project.datavisualization.dto.RequestPredictionResponseDto;
import tn.project.datavisualization.entity.Prediction;
import tn.project.datavisualization.services.PredictionRequestEmitter;
import tn.project.datavisualization.services.PredictionService;

import java.util.List;

@RestController("/prediction")
public class PredictionController {
    private PredictionService predictionService;
    private PredictionRequestEmitter predictionRequestEmitter;

    @Autowired
    public PredictionController(PredictionService service, PredictionRequestEmitter emitter){
        this.predictionRequestEmitter = emitter;
        this.predictionService = service;
    }

    @GetMapping()
    public List<Prediction> getMadePrediction(){
        return this.predictionService.listMadePrediction();
    }

    @PostMapping()
    public RequestPredictionResponseDto submitPredictionRequest(@RequestBody PredictionRequestDto dto){
        this.predictionRequestEmitter.EmitRequest(dto);
        return new RequestPredictionResponseDto("prediction request Sent successfully");
    }


}
