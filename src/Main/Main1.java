//package Main;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//public class Main1 {
//
//    public static void login(String email, String password) {
//        // Ścieżka do pliku chromedriver.exe - należy ją zmienić na właściwą
//        System.setProperty("webdriver.chrome.driver", "C:/ścieżka/do/chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
//
//        // Otwarcie strony Facebook
//        driver.get("https://www.facebook.com/");
//
//        // Wpisanie adresu email i hasła
//        WebElement emailField = driver.findElement(By.id("email"));
//        emailField.sendKeys(email);
//        WebElement passwordField = driver.findElement(By.id("pass"));
//        passwordField.sendKeys(password);
//
//        // Kliknięcie przycisku logowania
//        WebElement loginButton = driver.findElement(By.name("login"));
//        loginButton.click();
//
//        // Zamknięcie przeglądarki
//        driver.quit();
//    }
//
//    public static void main(String[] args) {
//        String email = "em";
//        String password = "ha";
//        login(email, password);
//    }
//}
