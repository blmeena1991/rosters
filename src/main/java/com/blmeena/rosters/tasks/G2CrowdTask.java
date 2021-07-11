package com.blmeena.rosters.tasks;
import com.blmeena.rosters.services.G2CrowdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class G2CrowdTask {

    @Autowired
    G2CrowdService g2CrowdService;

    //@Scheduled(cron = "0 0/1 * * * ?")
    public void updateEmployList() {
        g2CrowdService.updateEmployList();
    }

}
