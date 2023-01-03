package com.gorest.gorestinfo;

import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UserCRUDTest extends TestBase {
    static String name = "pr1";
    static String email = "Prime" + TestUtils.getRandomValue() + "@gmail.com";
    static String gender = "Male";
    static String status = "active";
    static int userId;
    @Steps
    UserSteps userSteps;

    @Title("This will create new user")
    @Test
    public void test001() {
        ValidatableResponse response = userSteps.createUser(name, email, gender, status);
        response.log().all().statusCode(201);
    }

    @Title("Verify that user is added ")
    @Test
    public void test002() {
        HashMap<String, Object> userMap = userSteps.getUserByEmail(email);
        Assert.assertThat(userMap, hasValue(email));
        userId = (int) userMap.get("id");
        System.out.println("User Id is : " + userId);
    }

    @Title("Updating user information and verify the updated information")
    @Test
    public void test003() {
        name = name + "_add";
        userSteps.updateUserById(userId, name, email, gender, status).log().all().statusCode(200);

        HashMap<String, Object> userMap1 = userSteps.getUserByEmail(email);
        Assert.assertThat(userMap1, hasValue(name));
    }

    @Title("Deleting user with id and verify that user is deleted")
    @Test
    public void test004() {
        userSteps.deleteUserById(userId).statusCode(204);
        userSteps.getUserById(userId).statusCode(404);
    }
}
