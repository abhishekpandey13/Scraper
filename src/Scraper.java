import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
public class Scraper {
	public WebDriver driver;
	int numberOfResults;
	String priceString;
	String modelName;
	Scraper(){
		driver = new FirefoxDriver();
		numberOfResults = 0;
		priceString = null;
		modelName = null;
	}
	public void findNumberOfResults(String searchString) throws InterruptedException{
		boolean completed = false;		             
		WebElement query = null;
		do{
		   //latencies are subject to internet connection. If NoSuchElementException occurs, refetch the page and try.
		   try{
		       driver.get("http://www.walmart.com");
		       Thread.sleep(5000);
		       query = driver.findElement(By.id("searchText"));
		       query.sendKeys(searchString);        		 
		       //Thread.sleep(10000);
		       query = driver.findElement(By.className("bigGoButton"));
		       query.click();
		       Thread.sleep(12000);             	
		       query = driver.findElement(By.className("numResults"));
		       completed = true;
		   }
		   catch(NoSuchElementException e){
			   //its because the page hasn't loaded yet.
			   System.out.println("Exception. Replaying");
			   Thread.sleep(5000);
		   }        	
		}
		while(!completed);
		//print the number of results        
		numberOfResults = Integer.parseInt(query.getText().replaceAll("\\D+", ""));
		System.out.println("Number of Search results for query "+ searchString + "  : " +  numberOfResults); 
	}
	public void displayModels(String searchString, int pageNumber) throws InterruptedException{
		findNumberOfResults(searchString);
		WebElement query = null;		
        String url = driver.getCurrentUrl();
        Thread.sleep(5000);
        //check if the page number provided isnt out of bounds. The page number should be max the last result page.
        int extraPage =0;
        if(numberOfResults%16 !=0){
        	extraPage =1;
        }
        if(pageNumber > numberOfResults/16 + extraPage){
        	System.out.println("Error! Out of bound page number. " + pageNumber+ " Crossed maximum number of results.");
        	return;
        }
        //Modify the URL to point to the desired page number.
        url = url.replace("ic=16_0", "ic=16_"+ Integer.toString((pageNumber-1)*16));
        System.out.println("Accessing Page Number "+ pageNumber);
        //Access this new modified URL
        driver.get(url);
        Thread.sleep(10000);
        //Collect less than or equal to 16 WebElements per page in a list. Each of these represent a sale item.
        List<WebElement> items = driver.findElements(By.className("item"));
        //System.out.println("size of list(number of items) " + items.size());     
        boolean skip = false;
        for(WebElement element : items){
        	//Print the model Name
        	try{
        		modelName = element.findElement(By.className("prodInfo")).findElement(By.className("prodInfoBox")).findElement(By.className("prodLink")).getAttribute("title");
        		System.out.println("modelName = " + modelName);
        	}
        	catch(NoSuchElementException e){
        		System.out.println("Exception. Replaying");
        		Thread.sleep(10000);
        	}
        	//Print the Price of the model. 
        	//Some prices are not available(in store only items), leading to NoSuchElementException. 
        	//Hence, Check if the webelement is empty, if yes, then skip it.{
        	try{
        		query = element.findElement(By.className("ItemShelfAvail")).findElement(By.className("camelPrice"));
        	}
        	catch(NoSuchElementException e){
        		System.out.println("Pricing info not available for this model. In Store only. ");
        		skip = true;
        	}
        	if(!skip){
        		priceString = query.getText(); 
        		System.out.println("Price = "+ priceString);
        	}
        	skip = false;
        	System.out.println();
        } 
	}
	public static void main(String[] args) throws InterruptedException {		
		if(args.length == 0 || args.length >2){
			System.out.println("Usage: java -jar Scraper.jar <keyword>  OR java -jar Scraper.jar <keyword> <page number> ");
			return;
		}
		String searchString;
		int pageNumber; 
		Scraper scraper = new Scraper();
		// Case 1: java -jar Scraper.jar <keyword>
		if(args.length == 1){
			searchString = args[0];
			scraper.findNumberOfResults(searchString);			
		}
		// Case 2: java -jar Scraper.jar <keyword> <page number>
		if(args.length == 2){
			searchString = args[0];
			pageNumber = Integer.parseInt(args[1]);
			if(pageNumber <= 0){
	        	System.out.println("Error! Invalid page number. Enter page number >=1");
	        	return;
	        }				
			scraper.displayModels(searchString,pageNumber);			
		}
		scraper.driver.close();
		System.exit(0);
	}
}
