package org.opengravity.examples.jmx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;

@Configuration
@EnableMBeanExport
public class App {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(
        "org.opengravity.examples.jmx"
    );
    context.getBean(Processor.class);
  }
}
