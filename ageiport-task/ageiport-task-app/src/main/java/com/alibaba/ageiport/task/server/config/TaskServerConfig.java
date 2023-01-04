package com.alibaba.ageiport.task.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskServerConfig {
    /**
     * 环境变量
     */
    @Value(value = "${ageiport.env:PRODUCTION}")
    protected String env;
    /**
     * 认证
     */
    @Value(value = "${ageiport.task.server.oauth2.enable:false}")
    protected boolean enableOauth2;

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public boolean isEnableOauth2() {
        return enableOauth2;
    }

    public void setEnableOauth2(boolean enableOauth2) {
        this.enableOauth2 = enableOauth2;
    }
}
