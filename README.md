# sb-test-jbpm-basic

- Spring Boot 2 (3 does not inject DeploymentService)
- H2 latest 1.x version (CorrelationPropertyInfo table: H2 version 2.x and later, "value" is a reserved keyword)
- use *mvel* dependency with java 21 (java.lang.Compiler has been removed from JDK 21)