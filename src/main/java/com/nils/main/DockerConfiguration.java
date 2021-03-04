package com.nils.main;

import com.amihaiemil.docker.UnixDocker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class DockerConfiguration {

    @Bean
    public UnixDocker getDocker() {
        return new UnixDocker(new File("/var/run/docker.sock"));
    }

}
