package es.chorro.authlogic;

import com.google.common.collect.Sets;
import io.druid.server.security.Access;
import io.druid.server.security.Action;
import io.druid.server.security.Resource;
import io.druid.server.security.ResourceType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleDatasourcesAuthLogicUnitTest {

    private SimpleDatasourcesAuthLogic simpleDatasourcesAuthLogic;

    @Before
    public void initTest() {
        simpleDatasourcesAuthLogic = new SimpleDatasourcesAuthLogic(
                Sets.newHashSet("datasourceA", "datasourceB", "datasourceC")
        );
    }

    @Test
    public void authorizationLogicShouldWork() {
        Resource resource = new Resource("datasourceA", ResourceType.DATASOURCE);
        Access access = simpleDatasourcesAuthLogic.isAuthorized(resource, Action.WRITE);
        assertTrue(access.isAllowed());

        resource = new Resource("datasourceB", ResourceType.DATASOURCE);
        access = simpleDatasourcesAuthLogic.isAuthorized(resource, Action.WRITE);
        assertTrue(access.isAllowed());

        resource = new Resource("datasourceC", ResourceType.DATASOURCE);
        access = simpleDatasourcesAuthLogic.isAuthorized(resource, Action.WRITE);
        assertTrue(access.isAllowed());

        resource = new Resource("datasourceD", ResourceType.DATASOURCE);
        access = simpleDatasourcesAuthLogic.isAuthorized(resource, Action.WRITE);
        assertFalse(access.isAllowed());

        resource = new Resource("datasourceE", ResourceType.DATASOURCE);
        access = simpleDatasourcesAuthLogic.isAuthorized(resource, Action.WRITE);
        assertFalse(access.isAllowed());
    }

}
