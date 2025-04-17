package mk.finki.ukim.lab_emt.model.dto;

import mk.finki.ukim.lab_emt.model.domain.Statistics;
import mk.finki.ukim.lab_emt.model.enumerations.Category;

public record StatisticsDto(
        Category category, 
        Integer count) {
    
    public static StatisticsDto from(Statistics statistics) {
        return new StatisticsDto(
                statistics.getCategory(),
                statistics.getCount()
        );
    }
}
