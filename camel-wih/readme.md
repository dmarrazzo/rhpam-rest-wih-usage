# Camel Work Item Handler 

## How to create from scratch

Create the project:

```sh
mvn archetype:generate \
  -DgroupId=com.redhat.example \
  -DartifactId=camel-wih \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DinteractiveMode=false
```

Add the dependencies:

- `jbpm-workitem-core`
- spring boot
- camel fuse

## Implementation

Key elements:

- [Camel Initialization login](src/main/java/com/redhat/example/CamelInit.java)
- [Camel Route Builder](src/main/java/com/redhat/example/OrderRouteBuilder.java)
- [WorkItemHandler which triggers the route](src/main/java/com/redhat/example/CamelWIH.java).
