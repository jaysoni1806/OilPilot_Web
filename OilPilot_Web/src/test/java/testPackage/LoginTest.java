package testPackage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.commonUtil.ApplicationException;
import com.commonUtil.ExtentReportManager;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import testBase.TestBase;

public class LoginTest extends TestBase {

	@DataProvider(name = "LoginTestData")
	public Object[][] getTestData(Method method) throws JsonParseException, JsonMappingException, IOException {

		List<Map<String, String>> maps = new ArrayList<>();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(new File(System.getProperty("user.dir") + "/DataFile/Users.json"));

		node = node.path(method.getName());
		maps = mapper.readValue(node.toString(), new TypeReference<List<Map<String, String>>>() {
		});

		return maps.stream().map(object -> new Map[] { object }).toArray(Map[][]::new);
	}

	@Test(dataProvider = "LoginTestData", description = "Test Valid Login")
	public void validLoginTest(Map<String, String> value) throws ApplicationException {
		ExtentReportManager.test.log(Status.PASS, "Enter Email id.");
		login.enterEmail(value.get("username"));

		ExtentReportManager.test.log(Status.PASS, "Enter Password.");
		login.enterPAssword(value.get("password"));

		ExtentReportManager.test.log(Status.PASS, "Cliked on Submit button.");
		login.clickSubmitButton();

		login.validateLoginSuccesfull();
		ExtentReportManager.test.log(Status.PASS, "Logged successfully and navigated on Dashboard");
	}
}
