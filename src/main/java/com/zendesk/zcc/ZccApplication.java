package com.zendesk.zcc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import static com.zendesk.zcc.Constants.ZCC_PASS;
import static com.zendesk.zcc.Constants.ZCC_SUB_DOMAIN;
import static com.zendesk.zcc.Constants.ZCC_USER;

@SpringBootApplication
public class ZccApplication extends SpringBootServletInitializer implements ApplicationRunner, WebApplicationInitializer {
  @Value("${user}")
  String username;
  @Value("${pass}")
  String password;
  @Value("${sub_domain}")
  String sub_domain;

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
    ctx.register(Config.class);
    ctx.setServletContext(servletContext);

    ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));

    servlet.setLoadOnStartup(1);
    servlet.addMapping("/");
  }

  public static void main(String[] args) {
    SpringApplication.run(ZccApplication.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    System.setProperty(ZCC_USER, username);
    System.setProperty(ZCC_PASS, password);
    System.setProperty(ZCC_SUB_DOMAIN, sub_domain);
  }
}
