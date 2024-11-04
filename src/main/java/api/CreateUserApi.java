package api;


import base.BaseHttpsClient;
import io.restassured.response.Response;
import model.CreateUser;

public class CreateUserApi extends BaseHttpsClient {
    private final String apiPath = "/api/auth/register";

    public Response createUser(CreateUser createUser) {
        return doPostRequest(apiPath, createUser);
    }
}
