package es.chorro.filters;

import es.chorro.SimpleAuthorizationExtension;
import io.druid.server.initialization.jetty.ServletFilterHolder;
import org.apache.log4j.Logger;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.EnumSet;
import java.util.Map;

public class AuthFilterHolder implements ServletFilterHolder{
    private static final Logger log = Logger.getLogger(SimpleAuthorizationExtension.class);

    String path = "/*";

    public AuthFilterHolder(String path) {
        if(path != null || !path.isEmpty())
            this.path = path;

        log.info("Filtered path : " + path);
    }

    @Override
    public Filter getFilter() {
        return new AuthFilterInjection();
    }

    @Override
    public Class<? extends Filter> getFilterClass() {
        return AuthFilterInjection.class;
    }

    @Override
    public Map<String, String> getInitParameters() {
        return null;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public EnumSet<DispatcherType> getDispatcherType() {
        return null;
    }
}
