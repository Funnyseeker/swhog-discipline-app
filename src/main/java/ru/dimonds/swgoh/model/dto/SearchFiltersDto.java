package ru.dimonds.swgoh.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchFiltersDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date                    start;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date                    end;
    private List<DisciplineRuleDto> rules;

    public boolean isEmpty() {
        return start == null && end == null && (rules == null || rules.isEmpty());
    }
}
