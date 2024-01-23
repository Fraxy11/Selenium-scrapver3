package srp.taks;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.JavascriptExecutor;;
// import com.opencsv.CSVWriter;

public class App {
public static void main(String[] args) throws IOException{
// Mengantur Path / lokasi file yang ingin di eksekusi

    System.setProperty("webdriver.chrome.driver" , "D:\\Belajar Java\\Java\\Mvn.java\\browser\\chromedriver-win64\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
  
    driver.get("https://www.lazada.co.id/beli-handphone/?spm=a2o4j.home-id.6242483150.80.42c553e0wyPcZ6");
    driver.manage().window().maximize();
    String setScaleScript = "document.body.style.zoom='0.7';";
    ((JavascriptExecutor) driver).executeScript(setScaleScript);


    DevTools devTools = ((ChromeDriver)driver).getDevTools();
    devTools.createSession();
    
    //Melakukan pengulangan

    int counter = 0;
    while (counter < 2) {
    counter++;
    List<WebElement> namaElements = driver.findElements(By.cssSelector("#root > div > div.ant-row.FrEdP > div:nth-child(1) > div > div.ant-col.ant-col-20.ant-col-push-4.Jv5R8 > div._17mcb > div:nth-child(1) > div > div > div.buTCk > div.RfADt"));
    List<WebElement> terjualElements = driver.findElements(By.cssSelector("#root > div > div.ant-row.FrEdP > div:nth-child(1) > div > div.ant-col.ant-col-20.ant-col-push-4.Jv5R8 > div._17mcb > div:nth-child(1) > div > div > div.buTCk > div._6uN7R > span._1cEkb"));
    List<WebElement> kotaElements = driver.findElements(By.cssSelector("#root > div > div.ant-row.FrEdP > div:nth-child(1) > div > div.ant-col.ant-col-20.ant-col-push-4.Jv5R8 > div._17mcb > div:nth-child(1) > div > div > div.buTCk > div._6uN7R > span.oa6ri"));
    //Deklarasi Output csv
    String csvFilePath = "str/src/main/java/srp/taks/output.csv";
    try {
     // Membuat FileWriter dan CSVPrinter
    FileWriter fileWriter = new FileWriter(csvFilePath);
    CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);
    //iterasi melalui elemen dan menambahkan data ke array list
    for (int i = 0; i < namaElements.size(); i++) {
        String nama = namaElements.get(i).getText();
        String terjual = terjualElements.get(i).getText();
        String kota = kotaElements.get(i).getText();
        csvPrinter.printRecord(nama,terjual,kota);
            }
        csvPrinter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    try{
        Thread.sleep(10000);
        }catch(InterruptedException e){
        e.printStackTrace();
    }        
    WebElement nextPageButton = driver.findElement(By.className("ant-pagination-item-link"));
    if (nextPageButton.isEnabled()) {
        nextPageButton.click();
    }
        }
    driver.quit();
    }
}

