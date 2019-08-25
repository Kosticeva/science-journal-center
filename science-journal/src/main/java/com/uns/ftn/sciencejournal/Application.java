package com.uns.ftn.sciencejournal;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.Groups;
import org.camunda.bpm.engine.authorization.Resource;
import org.camunda.bpm.engine.authorization.Resources;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import static org.camunda.bpm.engine.authorization.Authorization.ANY;
import static org.camunda.bpm.engine.authorization.Authorization.AUTH_TYPE_GRANT;
import static org.camunda.bpm.engine.authorization.Permissions.ALL;

@SpringBootApplication
@EnableProcessApplication
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);

        ProcessEngine engine = BpmPlatform.getDefaultProcessEngine();
        createDefaultUser(engine);
    }

    public static void createDefaultUser(ProcessEngine engine) {

        if (engine.getIdentityService().createUserQuery().userId("demo").count() == 0) {
            User user = engine.getIdentityService().newUser("demo");
            user.setFirstName("Demo");
            user.setLastName("Demo");
            user.setPassword("demo");
            user.setEmail("demo@camunda.org");
            engine.getIdentityService().saveUser(user);

            Group group = engine.getIdentityService().newGroup(Groups.CAMUNDA_ADMIN);
            group.setName("Administrators");
            group.setType(Groups.GROUP_TYPE_SYSTEM);
            engine.getIdentityService().saveGroup(group);

            for (Resource resource : Resources.values()) {
                Authorization auth = engine.getAuthorizationService().createNewAuthorization(AUTH_TYPE_GRANT);
                auth.setGroupId(Groups.CAMUNDA_ADMIN);
                auth.addPermission(ALL);
                auth.setResourceId(ANY);
                auth.setResource(resource);
                engine.getAuthorizationService().saveAuthorization(auth);
            }

            engine.getIdentityService().createMembership("demo", Groups.CAMUNDA_ADMIN);
        }
    }
}
