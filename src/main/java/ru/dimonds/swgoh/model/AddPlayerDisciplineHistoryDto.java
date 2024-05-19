package ru.dimonds.swgoh.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddPlayerDisciplineHistoryDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Long ruleId;
}
