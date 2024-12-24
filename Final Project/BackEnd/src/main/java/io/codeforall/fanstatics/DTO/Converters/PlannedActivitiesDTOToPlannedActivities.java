package io.codeforall.fanstatics.DTO.Converters;

import io.codeforall.fanstatics.DTO.PlannedActivitiesDTO;
import io.codeforall.fanstatics.Models.PlannedActivities;
import io.codeforall.fanstatics.Services.Interfaces.PlannedActivitiesServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PlannedActivitiesDTOToPlannedActivities implements Converter<PlannedActivitiesDTO, PlannedActivities> {

    private PlannedActivitiesServiceInterface plannedActivitiesService;


    private ActivityDTOToActivity activityDTOToActivity;

    @Autowired
    public void setPlannedActivitiesService(PlannedActivitiesServiceInterface plannedActivitiesService) {
        this.plannedActivitiesService = plannedActivitiesService;
    }

    @Autowired
    public void setActivityDTOToActivity(ActivityDTOToActivity activityDTOToActivity) {
        this.activityDTOToActivity = activityDTOToActivity;
    }

    @Override
    public PlannedActivities convert(PlannedActivitiesDTO plannedActivitiesDTO) {

        PlannedActivities plannedActivities = (plannedActivitiesDTO.getId() != null ? plannedActivitiesService.get(plannedActivitiesDTO.getId()) : new PlannedActivities());

        plannedActivities.setDate(plannedActivitiesDTO.getDate());
        plannedActivities.setLocation(plannedActivitiesDTO.getLocation());
        plannedActivities.setTime(plannedActivitiesDTO.getTime());
        plannedActivities.setNumberOfPeople(plannedActivitiesDTO.getNumberOfPeople());
        plannedActivities.setActivity(activityDTOToActivity.convert(plannedActivitiesDTO.getActivity()));
        return plannedActivities;
    }
}
