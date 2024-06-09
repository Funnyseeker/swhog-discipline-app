package ru.dimonds.swgoh.formatters;

import org.springframework.format.Formatter;
import ru.dimonds.swgoh.model.dto.DisciplineRuleDto;

import java.text.ParseException;
import java.util.Locale;

public class RuleFormatter implements Formatter<DisciplineRuleDto> {

    @Override
    public String print(DisciplineRuleDto hobby, Locale locale) {
        return hobby.getId().toString();
    }

    @Override
    public DisciplineRuleDto parse(String id, Locale locale) throws ParseException {
        return DisciplineRuleDto.builder()
                                .id(Long.parseLong(id))
                                .build();
    }
}