import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class adTest {
    public static WebDriver driver;
    public static WebDriverWait wait;

    public void acceptCookies() {
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=Komunikacijos_MobilusTelefonai&actionId=Siulo&returnurl=%2Fpatalpinti%2Fivesti-informacija");
        driver.findElement(By.className("fc-button-label")).click();
    }

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        acceptCookies();
    }

    public void fillAdPlacingForm(String title, String text, String price, String location, String phoNo, String email) {
        driver.findElement(By.id("title")).sendKeys(title);
        driver.findElement(By.id("text")).sendKeys(text);
        driver.findElement(By.id("price")).sendKeys(price);
        driver.findElement(By.id("location-search-box")).sendKeys(location);
        driver.findElement(By.id("phone")).sendKeys(phoNo);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("submit-button")).click();
    }

    @Test
    public void adPositiveTest() {
        fillAdPlacingForm("Honor Magic v2", "In very good godition, works perfectly, full set.", "600", "Klaipėda", "+37064345599", "Jonelis@gmail.com");
        driver.findElement(By.id("inputfile")).sendKeys("C:\\Users\\Jonas\\IdeaProjects\\elentaTest\\src\\AdTest\\PositiveTest.jpg");
        driver.findElement(By.id("forward-button")).click();
        driver.findElement(By.id("forward-button")).click();
        driver.findElement(By.cssSelector(".action")).click();
        driver.findElement(By.className("delete")).click();
        driver.switchTo().alert().accept();
    }

    @BeforeMethod
    public void beforeMethod() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e) {
        }
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=Komunikacijos_MobilusTelefonai&actionId=Siulo&returnurl=%2Fpatalpinti%2Fivesti-informacija");
        driver.findElement(By.id("title")).clear();
        driver.findElement(By.id("text")).clear();
        driver.findElement(By.id("price")).clear();
        driver.findElement(By.id("location-search-box")).clear();
        driver.findElement(By.id("phone")).clear();
        driver.findElement(By.id("email")).clear();
    }

    @Test
    public void adWithoutName() {
        fillAdPlacingForm("", "In very good godition, works perfectly, full set.", "600", "Klaipėda", "+37064345599", "Jonelis@gmail.com");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"te\"]")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "Įveskite skelbimo pavadinimą");
    }

    @Test
    public void adWithoutDescription() {
        fillAdPlacingForm("Honor Magic v2", "", "600", "Klaipėda", "+37064345599", "Jonelis@gmail.com");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"txte\"]")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "Įveskite skelbimo aprašymą");
    }

    @Test
    public void adWithoutPrice() {
        fillAdPlacingForm("Honor Magic v2", "In very good godition, works perfectly, full set.", "", "Klaipėda", "+37064345599", "Jonelis@gmail.com");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("/html/body/div[1]/form[1]/div[1]/label/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "įkelkite nuotraukas");
    }

    @Test
    public void adWithoutCity() {
        fillAdPlacingForm("Honor Magic v2", "In very good godition, works perfectly, full set.", "600", "", "+37064345599", "Jonelis@gmail.com");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("/html/body/div[1]/form[1]/div[1]/label/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "įkelkite nuotraukas");
    }

    @Test
    public void adWithoutPhoneNumber() {
        fillAdPlacingForm("Honor Magic v2", "In very good godition, works perfectly, full set.", "600", "Klaipėda", "", "Jonelis@gmail.com");
        String actual = "";
        try {
            actual = driver.findElement(By.id("ce")).getDomAttribute("style");
        } catch (Exception e) {
        }
        Assert.assertNotEquals(actual, "display:none");
    }

    @Test
    public void adWithoutEmailadress() {
        fillAdPlacingForm("Honor Magic v2", "In very good godition, works perfectly, full set.", "600", "Klaipėda", "+37064345599", "");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("/html/body/div[1]/form[1]/div[1]/label/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "įkelkite nuotraukas");
    }

    @Test
    public void adWithNameThreeLetters() {
        fillAdPlacingForm("asd", "In very good godition, works perfectly, full set.", "600", "Klaipėda", "+37064345599", "Jonelis@gmail.com");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("/html/body/div[1]/form[1]/div[1]/label/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "įkelkite nuotraukas");
    }

    @Test
    public void adWithDescriptionTwoLetters() {
        fillAdPlacingForm("Honor Magic v2", "ne", "600", "Klaipėda", "+37064345599", "Jonelis@gmail.com");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("/html/body/div[1]/form[1]/div[1]/label/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "įkelkite nuotraukas");
    }

    @Test
    public void adWithNameThreeNumbers() {
        fillAdPlacingForm("111", "In very good godition, works perfectly, full set.", "600", "Klaipėda", "+37064345599", "Jonelis@gmail.com");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("/html/body/div[1]/form[1]/div[1]/label/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "įkelkite nuotraukas");
    }

    @Test
    public void adRussianLanguage() {
        fillAdPlacingForm("Хонор Магия v2", "В очень хорошем состоянии, работает отлично, полный комплект.", "600", "Klaipėda", "+37064345599", "Jonelis@gmail.com");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("/html/body/div[1]/form[1]/div[1]/label/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "įkelkite nuotraukas");
    }

    @Test
    public void adNameAndDescriptionSymbols() {
        fillAdPlacingForm(".!?@$%^#$%", ".!?@$%^#$%", "600", "Klaipėda", "+37064345599", "Jonelis@gmail.com");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("/html/body/div[1]/form[1]/div[1]/label/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "įkelkite nuotraukas");
    }

    @Test
    public void adPriceWithLetters() {
        fillAdPlacingForm("Honor Magic v2", "In very good godition, works perfectly, full set.", "Ne", "Klaipėda", "+37064345599", "Jonelis@gmail.com");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("/html/body/div[1]/form[1]/div[1]/label/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "įkelkite nuotraukas");
    }

    @Test
    public void adUploadPdf() {
        fillAdPlacingForm("Honor Magic v2", "In very good godition, works perfectly, full set.", "600", "Klaipėda", "+37064345599", "Jonelis@gmail.com");
        driver.findElement(By.id("inputfile")).sendKeys("C:\\Users\\Jonas\\IdeaProjects\\elentaTest\\src\\AdTest\\samplePDF.pdf");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"fileupload-message\"]")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "Netinkamas nuotraukos formatas");
    }

    @Test
    public void adUploadGif() {
        fillAdPlacingForm("Honor Magic v2", "In very good godition, works perfectly, full set.", "600", "Klaipėda", "+37064345599", "Jonelis@gmail.com");
        driver.findElement(By.id("inputfile")).sendKeys("C:\\Users\\Jonas\\IdeaProjects\\elentaTest\\src\\AdTest\\SampleGif.gif");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"photo-1\"]/div/img")));
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void adUploadJpg30mb() {
        fillAdPlacingForm("Honor Magic v2", "In very good godition, works perfectly, full set.", "600", "Klaipėda", "+37064345599", "Jonelis@gmail.com");
        driver.findElement(By.id("inputfile")).sendKeys("C:\\Users\\Jonas\\IdeaProjects\\elentaTest\\src\\AdTest\\SampleJPG_30mb.jpg");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"photo-1\"]/div/img")));
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void adUploadPng() {
        fillAdPlacingForm("Honor Magic v2", "In very good godition, works perfectly, full set.", "600", "Klaipėda", "+37064345599", "Jonelis@gmail.com");
        driver.findElement(By.id("inputfile")).sendKeys("C:\\Users\\Jonas\\IdeaProjects\\elentaTest\\src\\AdTest\\SmaplePNG.png");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"photo-1\"]/div/img")));
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void adUploadTiff() {
        fillAdPlacingForm("Honor Magic v2", "In very good godition, works perfectly, full set.", "600", "Klaipėda", "+37064345599", "Jonelis@gmail.com");
        driver.findElement(By.id("inputfile")).sendKeys("C:\\Users\\Jonas\\IdeaProjects\\elentaTest\\src\\AdTest\\SampleTiff.tiff");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"photo-1\"]/div/img")));
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void adUploadAvi() {
        fillAdPlacingForm("Honor Magic v2", "In very good godition, works perfectly, full set.", "600", "Klaipėda", "+37064345599", "Jonelis@gmail.com");
        driver.findElement(By.id("inputfile")).sendKeys("C:\\Users\\Jonas\\IdeaProjects\\elentaTest\\src\\AdTest\\SampleAvi.avi");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"fileupload-message\"]")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "Netinkamas nuotraukos formatas");
    }

    @Test
    public void adUploadWordDocx() {
        fillAdPlacingForm("Honor Magic v2", "In very good godition, works perfectly, full set.", "600", "Klaipėda", "+37064345599", "Jonelis@gmail.com");
        driver.findElement(By.id("inputfile")).sendKeys("C:\\Users\\Jonas\\IdeaProjects\\elentaTest\\src\\AdTest\\SampleWord.docx");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"fileupload-message\"]")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "Netinkamas nuotraukos formatas");
    }

    @Test
    public void adUploadWebP() {
        fillAdPlacingForm("Honor Magic v2", "In very good godition, works perfectly, full set.", "600", "Klaipėda", "+37064345599", "Jonelis@gmail.com");
        driver.findElement(By.id("inputfile")).sendKeys("C:\\Users\\Jonas\\IdeaProjects\\elentaTest\\src\\AdTest\\SampleWebP.webp");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"fileupload-message\"]")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "Netinkamas nuotraukos formatas");
    }

    //    @Test
    public void adDescription7000Letters() {
        fillAdPlacingForm("Honor Magic v2", "" + generateRndStr(70000), "600", "Klaipėda", "+37064345599", "Jonelis@gmail.com");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("/html/body/div[1]/form[1]/div[1]/label/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "įkelkite nuotraukas");
    }

    @Test
    public void adName20000Letters() {
        fillAdPlacingForm("" + generateRndStr(20000), "In very good godition, works perfectly, full set.", "600", "Klaipėda", "+37064345599", "Jonelis@gmail.com");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("/html/body/div[1]/form[1]/div[1]/label/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "įkelkite nuotraukas");
    }

    @Test
    public void adNameSmileFromSymbol() {
        fillAdPlacingForm("૮₍´˶• . • ⑅ ₎ა", "In very good godition, works perfectly, full set.", "600", "Klaipėda", "+37064345599", "Jonelis@gmail.com");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("/html/body/div[1]/form[1]/div[1]/label/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "įkelkite nuotraukas");
    }

    @Test
    public void adNumberArab() {
        fillAdPlacingForm("Honor Magic v2", "In very good godition, works perfectly, full set.", "600", "Klaipėda", "+971507654321", "Jonelis@gmail.com");
        String actual = "";
        try {
            actual = driver.switchTo().alert().getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "Įvyko klaida.");
    }

    @Test
    public void adEmailTwoEta() {
        fillAdPlacingForm("Honor Magic v2", "In very good godition, works perfectly, full set.", "600", "Klaipėda", "+37064345599", "Jonelis@@gmail.com");
        String actual = "";
        try {
            actual = driver.findElement(By.id("ee")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "Blogas el. pašto adresas");
    }

    @Test
    public void adUploadPhotoAndDelete() {
        fillAdPlacingForm("Honor Magic v2", "In very good godition, works perfectly, full set.", "600", "Klaipėda", "+37064345599", "Jonelis@gmail.com");
        driver.findElement(By.id("inputfile")).sendKeys("C:\\Users\\Jonas\\IdeaProjects\\elentaTest\\src\\AdTest\\PositiveTest.jpg");
        driver.findElement(By.id("remove-photo-1")).click();
        driver.findElement(By.id("forward-button")).click();
        driver.findElement(By.id("forward-button")).click();
        driver.findElement(By.cssSelector(".action")).click();
        driver.findElement(By.className("delete")).click();
        driver.switchTo().alert().accept();
    }

    public static String generateRndStr(int length) {
        String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String text = "";
        for (int i = 0; i < length; i++) {
            text += symbols.charAt((int) (Math.random() * symbols.length()));
        }
        return text;
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}