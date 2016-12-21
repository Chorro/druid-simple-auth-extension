package es.chorro.filters;

import com.google.common.collect.Sets;
import es.chorro.authlogic.SimpleDatasourcesAuthLogic;
import io.druid.server.security.AuthConfig;
import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class AuthFilterInjection implements Filter {
    private static final Logger log = Logger.getLogger(AuthFilterInjection.class);

    private Set<String> datasources = new HashSet<>();

    public AuthFilterInjection() {
        // Avoid hardcoding
        datasources = Sets.newHashSet("datasourceA", "datasourceB", "datasourceC");
        log.info("Filtered datasources : " + datasources);
    }

    public void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // Inject implementation of "AuthorizationInfo" interface as a request attribute
        servletRequest.setAttribute(AuthConfig.DRUID_AUTH_TOKEN, new SimpleDatasourcesAuthLogic(datasources));
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {}
}
