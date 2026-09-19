package apiTests;

import api.client.ApiClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;

public abstract class BaseApiTest {
    protected ApiClient apiClient;
    protected Logger logger;

    @BeforeClass
    public void setUp() {
        logger = LogManager.getLogger(this.getClass());
        apiClient = new ApiClient();

    }
}