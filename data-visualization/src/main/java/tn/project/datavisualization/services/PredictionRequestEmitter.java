package tn.project.datavisualization.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tn.project.datavisualization.dto.PredictionRequestDto;
import com.google.gson.Gson;

@Service
public class PredictionRequestEmitter {
    private final String requestTopicName = "request-prediction";
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public PredictionRequestEmitter(KafkaTemplate<String, String> template){
        this.kafkaTemplate = template;
    }

    public void EmitRequest(PredictionRequestDto request){
        this.kafkaTemplate.send(requestTopicName, new Gson().toJson(request));
    }
}
