package stepdefs;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class DLS_WebInterface_StepDefs {

    WebDriver driver;

    @Before
    protected void init(){
        System.setProperty("webdriver.chrome.driver", "/driver/chromedriver");
        driver = new ChromeDriver();
    }

    @Given("A sample file is uploaded with format Order Number, Location and Date Ordered")
    public void aSampleFileIsUploadedWithFormatOrderNumberLocationAndDateOrdered() {
        String filePath = "/resources/input/sampleDLS.json";
        uploadFile(driver, filePath);
    }

    @Then("Its Should generate the a output file with format Order Number and Drone Departure time with final record NPS Value")
    public void itsShouldGenerateTheAOutputFileWithFormatOrderNumberAndDroneDepartureTimeWithFinalRecordNPSValue() throws IOException {
        File sampleOutputfile = new File("/resource/outputfile/DLSSampleTestOutput.json");
        driver.findElement(By.id("Export")).click();
        File generatedOutputFile = new File("/resource/downloads/DLSOutput.json");
        boolean isTwoEqual = FileUtils.contentEquals(sampleOutputfile, generatedOutputFile);
        Assert.assertTrue(isTwoEqual);
    }

    @Then("It Should display a message {string}")
    public void itShouldDisplayAMessage(String msg) {
        Assert.assertEquals(msg,getStatusMesage());
    }

    @Given("A sample file with invalid Date is uploaded in the DLS Interface")
    public void aSampleFileWithInvalidDateIsUploadedInTheDLSInterface() {
        String filePath = "/resources/input/sampleDLSInvalidDate.json";
        uploadFile(driver, filePath);
    }

    @Then("validate generated output file values with sample output file")
    public void validateGeneratedOutputFileValuesWithSampleOutputFile() throws IOException {
        File sampleOutputfile = new File("/resource/outputfile/DLSSampleTestOutput.json");
        driver.findElement(By.id("Export")).click();
        File generatedOutputFile = new File("/resource/downloads/DLSOutput.json");
        boolean isTwoEqual = FileUtils.contentEquals(sampleOutputfile, generatedOutputFile);
        Assert.assertTrue(isTwoEqual);
    }

    @Given("A Order Number is given to the DLS Interface")
    public void aOrderNumberIsGivenToTheDLSInterface() {
        WebElement orderNumberTextField = driver.findElement(By.id("orderNumfield"));
        orderNumberTextField.sendKeys("WM001");
    }

    @Then("It should display the drone delivery for that order")
    public void itShouldDisplayTheDroneDeliveryForThatOrder() {
        String deliveryTime = driver.findElement(By.id("deliveryTimeField")).getText();
        Assert.assertEquals("09:11:50",getStatusMesage());
    }

    @Given("A sample file is uploaded with invalid Order number is uploaded in the DLS Interface")
    public void aSampleFileIsUploadedWithInvalidOrderNumberIsUploadedInTheDLSInterface() {
        String filePath = "/resources/input/sampleDLSInvalidOrderNum.json";
        uploadFile(driver, filePath);
    }

    @Given("A sample file is uploaded with invalid Location is uploaded in the DLS Interface")
    public void aSampleFileIsUploadedWithInvalidLocationIsUploadedInTheDLSInterface() {
        String filePath = "/resources/input/sampleDLSInvalidLocation.json";
        uploadFile(driver, filePath);
    }

    @Given("A sample file is uploaded with invalid Date is uploaded in the DLS Interface")
    public void aSampleFileIsUploadedWithInvalidDateIsUploadedInTheDLSInterface() {
        String filePath = "/resources/input/sampleDLSInvalidDate.json";
        uploadFile(driver, filePath);
    }

    private void uploadFile(WebDriver driver, String filePath) {
        WebElement filePathTextField = driver.findElement(By.id("dlsfileinput"));
        filePathTextField.sendKeys(filePath);
        driver.findElement(By.id("upload")).click();
        Assert.assertEquals("File Uploaded Sucessfully",getStatusMesage());
    }

    private String getStatusMesage(){
        return driver.findElement(By.id("status")).getText().toString();
    }


}
