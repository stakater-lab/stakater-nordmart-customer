package com.stakater.nordmart.customer.config;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import info.archinnov.achilles.embedded.CassandraEmbeddedServerBuilder;
import info.archinnov.achilles.embedded.CassandraShutDownHook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;

import java.util.Collections;
import java.util.List;

@TestConfiguration
public class TestCassandraConfig extends AbstractCassandraConfiguration {

    @Value("${spring.data.cassandra.keyspace-name:placeholder}")
    private String keySpace;

    @Value("${nordmart.domain.package}")
    private String basePackage;

    @Value("${spring.data.cassandra.contact-points:placeholder}")
    private String contactPoints;

    private final String NORDMART_CLUSTER = "nordmart_cluster";

    private Cluster cluster = CassandraEmbeddedServerBuilder
            .builder()
            .withClusterName(NORDMART_CLUSTER)
            .withListenAddress(contactPoints)
            .withRpcAddress(contactPoints)
            .withCQLPort(9042)
            .withKeyspaceName(keySpace)
            .cleanDataFilesAtStartup(true)
            .useUnsafeCassandraDeamon()
            .withShutdownHook(new CassandraShutDownHook())
            .buildServer().getNativeCluster();

    @Bean
    @Primary
    public Session createSession() {
        return cluster.connect();
    }

    @Override
    protected String getKeyspaceName() {
        return keySpace;
    }

    @Override
    protected String getContactPoints() {
        return contactPoints;
    }

    @Override
    protected int getPort() {
        return 9042;
    }

    @Override
    protected List getKeyspaceCreations() {
        return Collections.singletonList(CreateKeyspaceSpecification
                .createKeyspace(keySpace).ifNotExists()
                .with(KeyspaceOption.DURABLE_WRITES, true)
                .withSimpleReplication());
    }

    @Bean
    @Primary
    public Cluster getCluster() {
        return cluster;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{basePackage};
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

}
