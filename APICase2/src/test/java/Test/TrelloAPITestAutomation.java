package Test;

import com.relevantcodes.extentreports.LogStatus;

import Builders.PostAPIBuilder;
import Configs.Endpoints;
import Configs.HeaderConfigs;
import Utilities.FileandEnv;
import Verifications.APIVerification;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import com.mashape.unirest.http.exceptions.UnirestException;

public class TrelloAPITestAutomation extends BaseTest {

	@Test
	public void testTrelloAPI() throws UnirestException {
		// Setting up test data
		String boardName = "NewTrelloBoard";
		String cardName = "NewTrelloCard";

		// Initializing test data and environment
		FileandEnv.loadFileAndEnv();

		// Logging test start
		test.log(LogStatus.INFO, "Test is started.");

		// Creating board
		Response createBoardResponse = createBoard(boardName);

		// Getting id from board
		String boardId = createBoardResponse.jsonPath().get("id");
		test.log(LogStatus.INFO, "Board ID: " + boardId);

		// Creating 2 cards
		createCards(boardId, cardName, 2);

		// Updating card
		updateCard(boardId, cardName);

		// Deleting card
		deleteCard(boardId, cardName);

		// Deleting board
		deleteBoard(boardName);

		// Verifying API responses
		APIVerification.verifyResponseCode(createBoardResponse, 200);
		APIVerification.verifyResponseTime(createBoardResponse);

		// Logging test finish
		test.log(LogStatus.INFO, "Test Finished.");
	}

	private Response createBoard(String boardName) {
		HeaderConfigs headerConfigs = new HeaderConfigs();
		PostAPIBuilder builder = new PostAPIBuilder();

		test.log(LogStatus.INFO, "Creating board.");
		return RestAssured.given()
				.contentType("application/json")
				.headers(headerConfigs.headersWithToken(boardName))
				.queryParams(builder.postRequestBody(boardName))
				.when().post(Endpoints.apiEndpoints.CREATE_BOARD)
				.then().extract().response();
	}

	private void createCards(String boardId, String cardName, int numberOfCards) {
		HeaderConfigs headerConfigs = new HeaderConfigs();
		PostAPIBuilder builder = new PostAPIBuilder();

		test.log(LogStatus.INFO, "Creating " + numberOfCards + " cards.");
		for (int i = 0; i < numberOfCards; i++) {
			RestAssured.given()
					.headers(headerConfigs.headerForCard(boardId))
					.queryParams(builder.postRequestBody(cardName))
					.when().post(Endpoints.apiEndpoints.CREATE_CARD)
					.then().extract().response();
		}
	}

	private void updateCard(String boardId, String cardName) {
		HeaderConfigs headerConfigs = new HeaderConfigs();
		PostAPIBuilder builder = new PostAPIBuilder();

		test.log(LogStatus.INFO, "Updating card.");
		RestAssured.given()
				.headers(headerConfigs.headerForCard(boardId))
				.queryParams(builder.postRequestBody(cardName))
				.when().put(Endpoints.apiEndpoints.PUT_CARD)
				.then().extract().response();
	}

	private void deleteCard(String boardId, String cardName) {
		HeaderConfigs headerConfigs = new HeaderConfigs();
		PostAPIBuilder builder = new PostAPIBuilder();

		test.log(LogStatus.INFO, "Deleting card.");
		RestAssured.given()
				.headers(headerConfigs.headerForCard(boardId))
				.queryParams(builder.postRequestBody(cardName))
				.when().delete(Endpoints.apiEndpoints.DELETE_CARD)
				.then().extract().response();
	}

	private void deleteBoard(String boardName) {
		HeaderConfigs headerConfigs = new HeaderConfigs();
		PostAPIBuilder builder = new PostAPIBuilder();

		test.log(LogStatus.INFO, "Deleting board.");
		RestAssured.given()
				.headers(headerConfigs.headersWithToken(boardName))
				.queryParams(builder.postRequestBody(boardName))
				.when().delete(Endpoints.apiEndpoints.DELETE_BOARD)
				.then().extract().response();
	}
}
