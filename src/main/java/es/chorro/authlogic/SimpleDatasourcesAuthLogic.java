package es.chorro.authlogic;

import io.druid.server.security.*;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class SimpleDatasourcesAuthLogic implements AuthorizationInfo {
    private static final Logger log = Logger.getLogger(SimpleDatasourcesAuthLogic.class);

    // Empty by default
    private Set<String> datasources = new HashSet<>();

    public SimpleDatasourcesAuthLogic(Set<String> datasources) {
        log.debug("Filtered datasources : " + datasources);

        if(datasources != null)
            this.datasources = datasources;
    }

    public Access isAuthorized(Resource resource, Action action) {
        // Check authorization
        if(resource.getType().equals(ResourceType.DATASOURCE) && datasources.contains(resource.getName())) {
            return new Access(true, "Access granted!");
        } else {
           return new Access(false, "Access denied!");
        }
    }
}
