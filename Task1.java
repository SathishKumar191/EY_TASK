package task;

import static org.testng.Assert.assertEquals;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Task1 {
	public static void main(String[] args) {
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("http://automationpractice.com/index.php");
		driver.findElement(By.className("login")).click();
		driver.findElement(By.id("email")).sendKeys("sathishkumarselvam97@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("Satz7667453486");
		driver.findElement(By.id("SubmitLogin")).click();
		WebElement account = driver.findElement(By.className("account"));
		String text = account.getText();
		assertEquals(text, "Sathish Kumar");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Women")).click();

		// Add product to cart
		WebElement product = driver.findElement(By.xpath("(//*[@class=\"product_img_link\"])[1]"));
		Actions a = new Actions(driver);
		a.moveToElement(product).perform();
		WebElement addtocart = driver.findElement(By.xpath("(//*[text()='Add to cart'])[1]"));
		addtocart.click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//span[@title='Continue shopping']")).click();

		// Search Product
		driver.findElement(By.id("search_query_top")).sendKeys("printed dress");
		driver.findElement(By.xpath("//button[@name='submit_search']")).click();

		// Single Product--- Add to Cart
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement Search_product = driver.findElement(By.xpath("(//a[contains(text(),'Printed Dress')])[3]"));
		a.moveToElement(Search_product).perform();
		driver.findElement(By.xpath("(//*[text()='Add to cart'])[2]")).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//span[@title='Continue shopping']")).click();

		// Add Multiple Products in Cart
		List<WebElement> Multiple_product = driver.findElements(By.xpath("//div[@class='product-image-container']"));
		for (int i = 0; i < 2; i++) {
		WebElement webElement=Multiple_product.get(i);
			a.moveToElement(webElement).perform();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement atc = driver.findElement(By.xpath("//*[text()='Add to cart']"));
			atc.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//span[@title='Continue shopping']")).click();
		}

		// Add Same Product Multiple times
		for (int i = 0; i < 2; i++) {
			WebElement ntimes = driver.findElement(By.xpath("(//a[contains(text(),'Printed Summer Dress')])[4]"));
			a.moveToElement(ntimes).perform();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//*[text()='Add to cart']")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//span[@title='Continue shopping']")).click();
			
			
			//Remove a product from cart
		driver.findElement(By.className("shopping_cart")).click();
		driver.findElement(By.className("cart_quantity_delete")).click();
		}

	}

}