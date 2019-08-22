package com.uns.ftn.sciencejournal.conf;

import com.uns.ftn.sciencejournal.Application;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackageClasses = {Application.class})
@EnableAutoConfiguration
@TestConfiguration
public class TestApplication {


}
