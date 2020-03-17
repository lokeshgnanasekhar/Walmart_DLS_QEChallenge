package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static io.restassured.RestAssured.*;


public class DLS_Services_Stepdefs {

    private Response response;
    private RequestSpecification request;

    @Given("A sample list of records in the format, Order Number, Location and Date Ordered")
    public void aSampleListOfRecordsInTheFormatOrderNumberLocationAndDateOrdered() throws Exception {
        String requestBody = "{" +
                "               { \"order\":\"WM001\"," +
                "                 \"location\":\"N11W5\"," +
                "                  \"ordertime\": \"05:11:50\"" +
                "                }," +
                "                {\"order\":\"WM002\"," +
                "                 \"location\":\"S3E2\"," +
                "                  \"ordertime\":\"6:11:55\"" +
                "                 }, " +
                "                 {\"order\":\"WM003\"," +
                "                      \"location\":\"N7E50\"," +
                "                       \"ordertime\":\"15:31:50\"" +
                "                 }}";
        response = getResponse("http://qa.api.com/wm/dlscore",requestBody);
    }

    @Then("It should output records in the format, Order Number and Drone Departure time")
    public void itShouldOutputRecordsInTheFormatOrderNumberAndDroneDepartureTime() throws Exception{
         String responseBody = "{{\"order\":\"WM001\", \"deliveryTime\":\"09:11:50\"},{\"order\":\"WM002\",\"deliveryTime\":\"15:11:55\"},{\"order\":\"WM003\",\"deliveryTime\":\"07:31:50\"}} ";
         validateJsonResponse(responseBody);
    }


    @Given("A sample location <Customer Location>")
    public void aSampleLocationCustomerLocation() throws Exception{
        String responseBody = "N11E5";
        response = getResponse("http://qa.api.com/wm/ddtc/location",responseBody);
    }


    @Then("It should validate the <Customer Type> based on Delivery Time")
    public void itShouldValidateTheCustomerTypeBasedOnDeliveryTime(String customerType) throws Exception {
        validateResponse(customerType);
    }

    @Given("A Promoter Percentage and Detractor percentage")
    public void aPromoterPercentageAndDetractorPercentage() throws Exception{
        String customerPercent = "{{\"customer\":\"Promoter\",\"percent\":80},{\"customer\":\"Detractor\",\"percent\":20}}" ;
        response = getResponse("http://qa.api.com/wm/nps/value", customerPercent);
    }

    @Then("It should calculate the NPS Value")
    public void itShouldCalculateTheNPSValue() throws Exception {
        validateResponse("60");
    }

    @Given("A list of Delivery Time and Customer Type")
    public void aListOfDeliveryTimeAndCustomerType() throws Exception{
        String customerInfo = "{{\"deliveryHr\":10,\"customer\" : \"Promoter\"} ,{\"deliveryHr\": 2,\"customer\" :\" Detractor\"} ,{\"deliveryHr\": 9,\"customer\" : \"Promoter\"},{\"deliveryHr\": 1,\"customer\":\"Detractor\"}}" ;
        response = getResponse("http://qa.api.com/wm/nps/calculate", customerInfo);
    }

    @Then("It should generate a NPS Value based on the list")
    public void itShouldGenerateANPSValueBasedOnTheList() throws Exception {
        String npsvalue = "72";
        validateResponse(npsvalue);
    }

    @Then("It should output records in the format of, Order Number and Drone Departure time and NPS Value")
    public void itShouldOutputRecordsInTheFormatOfOrderNumberAndDroneDepartureTimeAndNPSValue() throws Exception {
        String responseBody = "{{\"order\":\"WM001\",\"deliveryTime\":\" 09:11:50\"},{\"order\":\"WM002\",\"deliveryTime\":\" 15:11:55\"},{\"order\":\"WM003\",\"deliveryTime\":\"07:31:50\"},\"NPS\": 70";
        validateJsonResponse(responseBody);
    }

    @Given("A sample list of records in the invalid format")
    public void aSampleListOfRecordsInTheInvalidFormat() throws Exception {
        String requestBody = "{{\"order\":\"001\",\"ordertime\": \"05:11:50\"},{\"location\":\"S3E2\",\"ordertime\":\"6:11:55\"},{\"order\":\"WM003\",\"location\":\"N7E50\"}} ";
        response = getResponse("http://qa.api.com/wm/dlscore",requestBody);
    }

    @Then("DLS Generator Should display the {string}")
    public void dlsGeneratorShouldDisplayThe(String msg) throws Exception {
        validateResponse(msg);
    }

    @Then("It should generate a valid output with records in the format of, Order Number and Drone Departure time and NPS Value")
    public void itShouldGenerateAValidOutputWithRecordsInTheFormatOfOrderNumberAndDroneDepartureTimeAndNPSValue() throws Exception {
        String responseBody = "{{\"order\":\"WM001\",\"deliveryTime\":\" 09:11:50\"},{\"order\":\"WM002\",\"deliveryTime\":\" 15:11:55\"},{\"order\":\"WM003\",\"deliveryTime\":\"07:31:50\"},\"NPS\": 70";
        validateJsonResponse(responseBody);
    }

    @Given("A Specific period of Time and Date")
    public void aSpecificPeriodOfTimeAndDate() throws Exception {
        String requestBody = "{{\"date\":\"03-Dec-2019\",\"time\":\"05:11:50\"},{\"date\":\"10-Mar-2020\",\"time\":\"6:11:55\"}}";
        response = getResponse("http://qa.api.com/wm/dlsgen/filter",requestBody);

    }

    @Then("It Should display all list of records in the format Order Number, Location and Date Ordered")
    public void itShouldDisplayAllListOfRecordsInTheFormatOrderNumberLocationAndDateOrdered() throws Exception {
        String responseBody = "{{\"order\":\"WM001\",\"deliverytime\":\"09:11:50\"},{\"order\":\"WM002\",\"deliverytime\":\"15:11:55\"},{\"order\":\"WM003\",\"deliverytime\":\"07:31:50\"}}";
        validateJsonResponse(responseBody);
    }

    @Given("A Order number to the DLS Access")
    public void aOrderNumberToTheDLSAccess() throws Exception {
        validateResponse("07:31:50");
    }

    @Then("Compare output records generated with a sample drone schedule data containing records with ordernumber and Departure time")
    public void compareOutputRecordsGeneratedWithASampleDroneScheduleDataContainingRecordsWithOrdernumberAndDepartureTime() throws Exception{
        String responseBody = "{{\"order\":\"WM001\",\"deliverytime\":\"09:11:50\"},{\"order\":\"WM002\",\"deliverytime\":\"15:11:55\"},{\"order\":\"WM003\",\"deliverytime\":\"07:31:50\"}}";
        validateJsonResponse(responseBody);
    }

    @Then("Should display the Delivery time for that order")
    public void shouldDisplayTheDeliveryTimeForThatOrder() throws Exception {
        String deliveryTime = "09:11:50";
        validateResponse(deliveryTime);
    }

    @Given("A sample list of records in the format, Order Number, Location and Date Ordered with Same Order Dates")
    public void aSampleListOfRecordsInTheFormatOrderNumberLocationAndDateOrderedWithSameOrderDates() throws Exception{
        String requestBody = "WM001 N11W5 05:11:50 \n WM002 S3E2 05:11:50 \n WM003 N7E50 05:11:50 ";
        response = getResponse("http://qa.api.com/wm/dlscore",requestBody);
    }


    @Given("A sample location <Customer Location>")
    public void aSampleLocationCustomerLocation(String location) throws Exception {
        response = getResponse("http://qa.api.com/wm/ddtc", location);

    }

    @Then("It should calculate the <Delivery Time> for the order")
    public void itShouldCalculateTheDeliveryTimeForTheOrder(String time) throws Exception {
        validateResponse(time);
    }

    @Given("A <Delivery Time> in hrs to the NPS Engine")
    public void aDeliveryTimeInHrsToTheNPSEngine(String time) throws Exception {
        response = getResponse("http://qa.api.com/wm/nps/customertype", time);
    }

    private Response getResponse(String url,String body) throws Exception{
        return given().body(body)
                .post(url);
    }

    private void validateResponse(String validatorData) throws Exception {
        response.then()
                .statusCode(200);
        String strResponse = response.andReturn().body().asString();
        Assert.assertEquals(validatorData, strResponse);
    }

    private void validateJsonResponse(String validatorData) throws Exception {
        response.then()
                .statusCode(200);
        String strResponse = response.andReturn().body().asString();
        JSONAssert.assertEquals(
                validatorData, strResponse, JSONCompareMode.LENIENT);
    }


}
