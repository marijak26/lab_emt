package mk.finki.ukim.lab_emt.dto;

import mk.finki.ukim.lab_emt.model.enumerations.Category;

public record StatisticsDto(
        Category category, 
        Integer count){
}
