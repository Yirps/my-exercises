package io.codeforall.fanstatics.Controllers;

import io.codeforall.fanstatics.DTO.ActivityDTO;
import io.codeforall.fanstatics.DTO.Converters.ActivityDTOToActivity;
import io.codeforall.fanstatics.DTO.Converters.ActivityToActivityDTO;
import io.codeforall.fanstatics.Models.Activity;
import io.codeforall.fanstatics.Services.ActivityService;
import io.codeforall.fanstatics.Services.Interfaces.ActivityServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/activity")
public class ActivityController {


    private ActivityDTOToActivity activityDTOToActivity;
    private ActivityToActivityDTO activityToActivityDTO;
    private ActivityServiceInterface activityService;

    @Autowired
    public void setActivityService(ActivityServiceInterface activityService){
        this.activityService = activityService;
    }

    @Autowired
    public void setActivityToActivityDTO(ActivityToActivityDTO activityToActivityDTO) {
        this.activityToActivityDTO = activityToActivityDTO;
    }

    @Autowired
    public void setActivityDTOToActivity(ActivityDTOToActivity activityDTOToActivity) {
        this.activityDTOToActivity = activityDTOToActivity;
    }


    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public ResponseEntity<List<ActivityDTO>> listActivity() {

        List<ActivityDTO> activityDTOS = activityService.list().stream()
                .map(activity -> activityToActivityDTO.convert(activity))
                .collect(Collectors.toList());



        return new ResponseEntity<>(activityDTOS, HttpStatus.OK);
    }



    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<ActivityDTO> get(@PathVariable Integer id) {

        Activity activity = activityService.get(id);

        if(activity == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(!activity.getId().equals(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }



        return new ResponseEntity<>(activityToActivityDTO.convert(activity), HttpStatus.OK);
    }
}
