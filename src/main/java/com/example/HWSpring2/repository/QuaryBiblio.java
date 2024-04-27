package com.example.HWSpring2.repository;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Класс хранения запросов
 */
@ConfigurationProperties(prefix = "quary")
@Data
public class QuaryBiblio {
    private String findAll;
    private String save;
    private String deleteById;
    private String updateById;
    private String getOne;
}
