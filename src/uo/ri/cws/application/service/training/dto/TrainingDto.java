package uo.ri.cws.application.service.training.dto;

import java.util.List;

import uo.ri.cws.application.service.training.TrainingForMechanicRow;
import uo.ri.cws.application.service.training.TrainingHoursRow;

public class TrainingDto {
    public TrainingForMechanicRow trainingAll;
    public List<TrainingHoursRow> trainingType;
}
