package com.blissstock.mappingSite.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("com.blissstock.mapping-site.config")
@Getter
@Setter
public class ApplicationProperties {
    private int courseSearchLimit;
}
