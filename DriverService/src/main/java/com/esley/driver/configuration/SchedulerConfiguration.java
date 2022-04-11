package com.esley.driver.configuration;

import com.google.common.collect.ImmutableList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.concurrent.DelegatingSecurityContextScheduledExecutorService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;

import java.util.List;
import java.util.concurrent.Executor;

import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Configuration
public class SchedulerConfiguration implements SchedulingConfigurer {

    private static final String KEY = "spring";
    private static final String PRINCIPAL = "spring";
    private static final List<SimpleGrantedAuthority> AUTHORITIES = ImmutableList.of(new SimpleGrantedAuthority("POLICEMAN"));

    @Override
    public void configureTasks(final ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    @Bean
    public Executor taskExecutor() {
        final AnonymousAuthenticationToken token = new AnonymousAuthenticationToken(KEY, PRINCIPAL, AUTHORITIES);
        final SecurityContext securityContext = getContext();
        securityContext.setAuthentication(token);
        return new DelegatingSecurityContextScheduledExecutorService(newSingleThreadScheduledExecutor(), securityContext);
    }
}
