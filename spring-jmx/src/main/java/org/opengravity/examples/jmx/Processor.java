package org.opengravity.examples.jmx;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName = "org.opengravity:name=Processor")
public class Processor {

  private final Logger log = LoggerFactory.getLogger(Processor.class);

  private final ExecutorService executor;
  private final String id;
  private String name;

  public Processor() {
    id = UUID.randomUUID().toString();
    executor = Executors.newSingleThreadExecutor();
  }

  @ManagedAttribute
  public String getName() {
    return name;
  }

  @ManagedAttribute
  public void setName(String name) {
    this.name = name;
  }

  @ManagedAttribute
  public String getId() {
    return id;
  }

  @ManagedOperation
  public void interupt() {
    executor.shutdownNow();
  }

  @PostConstruct
  private void init() {
    executor.submit(new Runnable() {
      public void run() {
        log.info("Thread [{}] started", Thread.currentThread().getName());
        while (!Thread.interrupted()) {
          try {
            log.info("Thread [{}] sleeping", Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(5);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
        }
        log.info("Thread [{}] terminating", Thread.currentThread().getName());
      }
    });
  }
}
