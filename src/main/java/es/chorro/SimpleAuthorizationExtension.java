package es.chorro;

import com.fasterxml.jackson.databind.Module;
import com.google.common.collect.ImmutableList;
import com.google.inject.Binder;
import com.google.inject.multibindings.Multibinder;
import es.chorro.filters.AuthFilterHolder;
import io.druid.initialization.DruidModule;
import io.druid.server.initialization.jetty.ServletFilterHolder;
import org.apache.log4j.Logger;

import java.util.List;

// References
// http://druid.io/docs/latest/operations/including-extensions.html
// https://groups.google.com/d/msg/druid-user/23nVku3G4Rw/DZXYHy2vAgAJ

// Proposal
// https://github.com/druid-io/druid/issues/2355

// Pull request
// https://github.com/druid-io/druid/pull/2424

public class SimpleAuthorizationExtension implements DruidModule {
    private static final Logger log = Logger.getLogger(SimpleAuthorizationExtension.class);

    public List<? extends Module> getJacksonModules() {
        return ImmutableList.of();
    }

    public void configure(Binder binder) {
        Multibinder.newSetBinder(binder, ServletFilterHolder.class)
                .addBinding()
                .toInstance(new AuthFilterHolder("/*"));
    }
}
