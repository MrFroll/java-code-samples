package org.opengravity.examples.jmx;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;

@Configuration
@EnableMBeanExport
public class App {

  public static void main(String[] args) {
    new AnnotationConfigApplicationContext(
        "org.opengravity.examples.jmx"
    );
  }
}
