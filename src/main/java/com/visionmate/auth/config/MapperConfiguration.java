package com.visionmate.auth.config;

import com.visionmate.auth.util.mapper.BaseConverter;
import com.visionmate.auth.util.mapper.DefaultMapper;
import com.visionmate.auth.util.mapper.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Set;

@Configuration
public class MapperConfiguration {

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    public Mapper mapper(Set<BaseConverter> converters) {
        return new DefaultMapper(new ArrayList(converters));
    }

}
