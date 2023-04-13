package project.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Common {
    public List<Area> areas = new ArrayList<>();
    public List<Category> categories = new ArrayList<>();

    @Autowired
    public Common() {
        areas.add(new Area("강남역"));
        areas.add(new Area("가로수길"));
        categories.add(new Category("한식"));
        categories.add(new Category("양식"));
    }
}
