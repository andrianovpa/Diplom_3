package api;

import base.BaseHttpsClient;
import io.restassured.response.Response;

public class DeleteUserApi extends BaseHttpsClient {
    private final String apiPath = "api/auth/user";

    public Response deleteUser(String token) {
        return doDeleteRequest(apiPath, token);
    }
}
