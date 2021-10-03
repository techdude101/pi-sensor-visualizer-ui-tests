package seleniumgluecode;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class StepDefinitions {
	public static WebDriver driver;
	private final static String BASE_URL = "https://techdude101.github.io/pi-sensor-visualizer/";

	@Before
	public void setUp() {
		System.setProperty("webdriver.gecko.driver", "C:\\webdrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
	}

	@When("the user is on the home page")
	public void the_user_is_on_the_home_page() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(BASE_URL);
	}
	
	@Given("the user navigates to the site")
	public void user_navigates_to_site() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(BASE_URL);
	}

	@When("the user is on the graph page")
	public void user_navigates_to_graph_page() throws Throwable {
		return;
	}

	@When("the user enters a date range")
	public void user_enters_date() throws Throwable {   
		return;
	}

	@Then("a graph is displayed")
	public void success_message_is_displayed() throws Throwable {
		return;
	}

	@Then("the x-axis title has a label {string}")
	public void the_x_axis_title_has_a_label(String string) {
		String actual_title_x_axis = driver.findElement(By.cssSelector(".xtitle")).getText();
		Assert.assertEquals(string, actual_title_x_axis);
	}

	@Then("the left y-axis title has a label {string}")
	public void the_left_y_axis_title_has_a_label(String string) {
		String actual_title_y_axis = driver.findElement(By.cssSelector(".ytitle")).getText();
		Assert.assertEquals(string, actual_title_y_axis);
	}

	@Then("the right y-axis title has a label {string}")
	public void the_right_y_axis_title_has_a_label(String string) {
		String actual_title_y_axis = driver.findElement(By.cssSelector(".y2title")).getText();
		Assert.assertEquals(string, actual_title_y_axis);
	}

	@Then("a summary is displayed")
	public void a_summary_is_displayed() {
		return;
	}

	@Then("the summary contains the temperature in celsius with a {string} label")
	public void the_summary_contains_temperature(String unit_label) {
		WebElement temperature_element = driver.findElement(By.cssSelector(".card-container__temperature"));
		final String temperature_text = temperature_element.getText();
		Assert.assertEquals(true, temperature_text.endsWith(unit_label));
	}

	@Then("the summary contains the humidity with a percent {string} label")
	public void the_summary_contains_humidity_with_a_percent_label(String unit_label) {
		WebElement humidity_element = driver.findElement(By.cssSelector(".card-container__humidity"));
		Assert.assertEquals(true, humidity_element.getText().endsWith(unit_label));
	}
	
	@Then("the summary contains the date")
	public void the_summary_contains_date() {
		WebElement date_element = driver.findElement(By.cssSelector(".card-container__date"));
		final String date_text = date_element.getText();
		
		SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
		Calendar calendar = Calendar.getInstance();
		final String date_string_1 = date_format.format(calendar.getTime());
		
		calendar.add(Calendar.MINUTE, -1);
		
		final String date_string_2 = date_format.format(calendar.getTime());
		final String message = String.format("Expected: %s or %s Actual: %s", date_string_2, date_string_1, date_text);
		
		// Check that the time is within the last 2 minutes
		final boolean date_within_last_2_minutes = ((date_text.equals(date_string_1)) || (date_text.equals(date_string_2)));
		Assert.assertEquals(message, true, date_within_last_2_minutes);
	}
	
	@Then("the y-axis has a range of {int} to {int}")
	public void the_y_axis_has_a_range_of_to(Integer start, Integer end) {
		int axis_minimum = Integer.MAX_VALUE;
		int axis_maximum = Integer.MIN_VALUE;
		
		List<WebElement> xy2 = driver.findElements(By.cssSelector(".y2tick"));
		

		for (WebElement element : xy2) {
			String text = element.getText();
			int value = Integer.parseInt(text);
			
			if (value > axis_maximum) {
				axis_maximum = value;
			}
			
			if (value < axis_minimum) {
				axis_minimum = value;
			}
		}
		Assert.assertEquals("Humidity axis minimum", start.intValue(), axis_minimum);
		Assert.assertEquals("Humidity axis maximum", end.intValue(), axis_maximum);
	}

	@After
	public void tearDown() {
		driver.close();
	}
}
