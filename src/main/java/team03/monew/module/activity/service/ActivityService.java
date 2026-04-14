package team03.monew.module.activity.service;

import java.util.UUID;
import team03.monew.module.user.dto.ActivityDto;

public interface ActivityService {

  ActivityDto findUserActivity(UUID userId);

}
