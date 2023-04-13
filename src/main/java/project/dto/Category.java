package project.dto;

import org.springframework.stereotype.Component;

@Component
public class Category {
    String style;

    public Category(String style) {
        this.style = style;
    }
}
