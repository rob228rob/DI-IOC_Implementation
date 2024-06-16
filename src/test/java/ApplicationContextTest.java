import org.example.ApplicationContext;
import org.example.SupportPackage.SupportManager;
import org.example.SupportPackage.SupportServlet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApplicationContextTest {

    @Test
    void testApplicationContext() {
        ApplicationContext context = new ApplicationContext();
        SupportManager manager = context.getInstance(SupportManager.class);
        SupportServlet servlet = context.getInstance(SupportServlet.class);

        assertNotNull(manager);
        assertNotNull(servlet);
    }
}
