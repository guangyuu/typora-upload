package com.github.guangyuu.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author Guangyu
 * @Date 2022/11/4
 * @Description 客户端配置类
 **/
@Component
@PropertySource(value = "classpath:/application.properties")
public class ClientProperties {
    /**
     * 用户名
     */
    @Value("${client.username}")
    private String username;
    /**
     * token
     */
    @Value("${client.accessToken}")
    private String accessToken;
    /**
     * http超时时间(秒)
     */
    @Value("${client.http.timeout}")
    private Long timeout;
    /**
     * 服务主机名
     */
    @Value("${server.hosts}")
    private String hosts;
    /**
     * 上传接口路径
     */
    @Value("${server.uploadPath}")
    private String uploadPath;

    public ClientProperties() {
    }

    public ClientProperties(String username, String accessToken, Long timeout, String hosts, String uploadPath) {
        this.username = username;
        this.accessToken = accessToken;
        this.timeout = timeout;
        this.hosts = hosts;
        this.uploadPath = uploadPath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public String getHosts() {
        return hosts;
    }

    public void setHosts(String hosts) {
        this.hosts = hosts;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    @Override
    public String toString() {
        return "ClientProperties{" +
                "username='" + username + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", timeout=" + timeout +
                ", hosts='" + hosts + '\'' +
                ", uploadPath='" + uploadPath + '\'' +
                '}';
    }
}

