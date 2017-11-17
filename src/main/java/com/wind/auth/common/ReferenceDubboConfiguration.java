package com.wind.auth.common;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangying 2017/11/15.
 */
@Configuration
public class ReferenceDubboConfiguration {

    @Value("${dubbo.registry.address}")
    private String zkRegisterAddress;
    @Value("${dubbo.user.version}")
    private String dubboVersion;
    @Value("${dubbo.protocol}")
    private String dubboProtocol;
    @Value("${dubbo.protocol.port}")
    private int dubboPort;
    @Value("${dubbo.timeout}")
    private int dubboTimeout;
    @Value("${dubbo.application.name}")
    private String applicationName;

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(applicationName);
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(zkRegisterAddress);
        registryConfig.setTimeout(dubboTimeout);
        registryConfig.setCheck(false);
        return registryConfig;
    }
}
