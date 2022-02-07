# sb-test-jbpm-basic
JBPM / Hibernate 5.6 issue

Hibernate 5.6 cannot parse jbpm-kie-services-7.64.0.Final.jar!/META-INF/Servicesorm.xml against corresponding xsd

```
Caused by: org.xml.sax.SAXParseException: cvc-complex-type.2.4.a: Invalid content was found starting with element '{"http://xmlns.jcp.org/xml/ns/persistence/orm":named-query}'. One of '{"http://xmlns.jcp.org/xml/ns/persistence/orm":sql-result-set-mapping, "http://xmlns.jcp.org/xml/ns/persistence/orm":mapped-superclass, "http://xmlns.jcp.org/xml/ns/persistence/orm":entity, "http://xmlns.jcp.org/xml/ns/persistence/orm":embeddable, "http://xmlns.jcp.org/xml/ns/persistence/orm":converter}' is expected.
```

In [Servicesorm.xml](https://github.com/kiegroup/jbpm/blob/main/jbpm-services/jbpm-kie-services/src/main/resources/META-INF/Servicesorm.xml)

      <named-query name="getDeploymentUnit"> 
      
appears after 

      <sql-result-set-mapping name="UserTaskInstanceDesc"> 
      
this is conflicted with [xsd](https://github.com/hibernate/hibernate-orm/blob/main/hibernate-core/src/main/resources/org/hibernate/xsd/mapping/mapping-2.1.0.xsd):

```
<xsd:sequence>
   ...
   <xsd:element name="named-query" type="orm:named-query" minOccurs="0" maxOccurs="unbounded"/>
   ...
   <xsd:element name="sql-result-set-mapping" type="orm:sql-result-set-mapping" minOccurs="0" maxOccurs="unbounded"/>
   ...
</xsd:sequence>
```

[JBPM-9946](https://issues.redhat.com/browse/JBPM-9946)
