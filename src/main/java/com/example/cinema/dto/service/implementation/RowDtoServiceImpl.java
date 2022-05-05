package com.example.cinema.dto.service.implementation;

import com.example.cinema.api.RowService;
import com.example.cinema.dto.model.RowDto;
import com.example.cinema.dto.service.interfaces.RowDtoService;
import com.example.cinema.model.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RowDtoServiceImpl implements RowDtoService {


    @Autowired
    private RowService rowService;

    @Autowired
    private ConversionService conversionService;


    @Override
    public void save(RowDto row) {
        rowService.save(conversionService.convert(row, Row.class));
    }

    @Override
    public RowDto getById(Long id) {
        Row row = rowService.getById(id);
        return conversionService.convert(row, RowDto.class);

    }

    @Override
    public Long delete(Long id) {
        return  rowService.delete(id);
    }

    @Override
    public RowDto update(RowDto row, Long id) {
        return conversionService.convert(
                rowService.update(id,conversionService.convert(row, Row.class)), RowDto.class);

    }

    @Override
    public List<RowDto> getByHall(Long id) {
        return rowService.getByHall(id).stream().map(m->conversionService.convert(m,RowDto.class)).collect(Collectors.toList());
    }
}
