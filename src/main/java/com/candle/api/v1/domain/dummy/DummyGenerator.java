package com.candle.api.v1.domain.dummy;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DummyGenerator {

    private final DummyService dummyService;

    public DummyGenerator(DummyService dummyService) {
        this.dummyService = dummyService;
    }

//    @PostConstruct
//    private void generateDummyData() {
//        dummyService.generateUser();
//        dummyService.generateDiary();
//    }
}
