package com.blmeena.rosters.services;

import com.blmeena.rosters.models.dto.ProfileDto;
import com.blmeena.rosters.models.dto.UserDto;
import com.blmeena.rosters.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class G2CrowdServiceImpl implements G2CrowdService {
    @Autowired
    public G2CrowdCommunicator g2CrowdCommunicator;

    @Autowired
    public UserService userService;

    @Override
    public void updateEmployList() {
        JSONArray employList = g2CrowdCommunicator.fetchEmployList();
        if(!employList.isEmpty()){
            for(Object employ: employList){
                if ( employ instanceof JSONObject ) {
                    ProfileDto profileDto = new ProfileDto();
                    profileDto.setName(((JSONObject) employ).get("name").toString());
                    profileDto.setDescription(((JSONObject) employ).get("bio").toString());
                    profileDto.setTitle(((JSONObject) employ).get("title").toString());
                    profileDto.setImageUrl(((JSONObject) employ).get("image_url").toString());
                    UserDto userDto = new UserDto();
                    userDto.setEmail(generateEmail(((JSONObject) employ).get("name").toString()));
                    userDto.setUserName(generateUserName(((JSONObject) employ).get("name").toString()));
                    userDto.setPassword(generateUserName(((JSONObject) employ).get("name").toString()));
                    userDto.setProfile(profileDto);
                    try {
                        userService.create(userDto);
                    }catch (Exception e){
                        log.info(e.getMessage());
                    }

                }
            }
        }
    }

    private String generateEmail(String name){
        String domain = "g2.com";
        return name.toLowerCase().replaceAll("[^a-zA-Z0-9]", "").toLowerCase()+ "@" + domain;
    }
    private String generateUserName(String name){
        return name.toLowerCase().replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }
}
