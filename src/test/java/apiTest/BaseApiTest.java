package apiTest;

import api.client.ApiClient;
import org.testng.annotations.BeforeClass;

public abstract class BaseApiTest {
    protected ApiClient apiClient;

    @BeforeClass
    public void setUp() {
        apiClient = new ApiClient();
    }
}