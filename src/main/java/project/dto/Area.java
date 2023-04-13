package project.dto;

import org.springframework.stereotype.Component;

@Component
public class Area {
    String district;

    public Area(String district) {
        this.district = district;
    }
}

