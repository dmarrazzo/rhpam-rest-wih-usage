SpringBoot deployment
===============================================================================

Create springboot service project:

```sh
mvn archetype:generate -B \
  -DarchetypeGroupId=org.kie \
  -DarchetypeArtifactId=kie-service-spring-boot-archetype \
  -DarchetypeVersion=7.59.0.Final-redhat-00009 \
  -DgroupId=com.redhat.demo \
  -DartifactId=rest-wih-service \
  -Dversion=1.0-SNAPSHOT \
  -Dpackage=com.redhat.demo.service \
  -DappType=bpm
```

Define the kjar deployment in `application.properties`.

Add the pom plugin for KJAR module static deployment (See [pom.xml](rest-wih-service/pom.xml))

Further information on the RHPAM SpringBoot deployment:
[Creating Red Hat Process Automation Manager business applications with Spring Boot](https://access.redhat.com/documentation/en-us/red_hat_process_automation_manager/7.12/html-single/integrating_red_hat_process_automation_manager_with_other_products_and_components/index#assembly-springboot-business-apps)
