package api;

import base.BaseHttpsClient;
import io.restassured.response.Response;
import model.LoginUser;

public class LoginUserApi extends BaseHttpsClient {
    private final String apiPath = "/api/auth/login";

    public Response loginUser(LoginUser loginUser) {
        return doPostRequest(apiPath, loginUser);
    }
}

