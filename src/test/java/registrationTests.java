import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class registrationTests {
    public static WebDriver driver;

    public void acceptCookies() {
        driver.get("https://elenta.lt/registracija");
        driver.findElement(By.className("fc-button-label")).click();
    }

    public void fillRegistrationForm(String username, String email, String password, String password2) {
        driver.findElement(By.id("UserName")).sendKeys(username);
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("Password2")).sendKeys(password2);
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input")).click();
    }

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        acceptCookies();
    }

    @Test
    public void registrationPositiveTest() {
        fillRegistrationForm("Jonasnezinomas" + (1000 + Math.round(Math.random() * 9999)), "jonas" + (100 + Math.round(Math.random() * 999)) + "@gmail.com", "Slaptazodis1", "Slaptazodis1");
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://elenta.lt/registracija");
    }

    @Test
    public void usernameOneLetter() {
        fillRegistrationForm("" + generateRndStr(1), "jonas" + (100 + Math.round(Math.random() * 999)) + "@gmail.com", "Slaptazodis1", "Slaptazodis1");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "Jūs sėkmingai prisiregistravote!");
    }

    @Test
    public void withoutUsername() {
        fillRegistrationForm("", "jonas" + (100 + Math.round(Math.random() * 999)) + "@gmail.com", "Slaptazodis1", "Slaptazodis1");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[1]/td[2]/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "Įveskite vartotojo vardą.");
    }

    @Test
    public void withoutEmail() {
        fillRegistrationForm("Jonasnezinomas", "", "Slaptazodis1", "Slaptazodis1");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "Įveskite el. pašto adresą.");
    }

    @Test
    public void withoutPassword() {
        fillRegistrationForm("Jonasnezinomas", "jonas@gmail.com", "", "");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[7]/td[2]/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "Įveskite slaptažodį.");
    }

    @Test
    public void emailOneRandomNumberOneLetter() {
        fillRegistrationForm("Jonasnezinomas" + (1000 + Math.round(Math.random() * 9999)), "j" + (1 + Math.round(Math.random() * 9)) + "@gmail.com", "Slaptazodis1", "Slaptazodis1");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "Neteisingas el. pašto adresas.");
    }

    @Test
    public void usernameOneLetterOneRandomNumber() {
        fillRegistrationForm("j" + (1 + Math.round(Math.random() * 9)), "jonas" + (100 + Math.round(Math.random() * 999)) + "@gmail.com", "Slaptazodis1", "Slaptazodis1");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "Vartotojo vardas per trumpas");
    }

    @Test
    public void passwordThreeLetters() {
        fillRegistrationForm("Jonasnezinomas" + (1000 + Math.round(Math.random() * 9999)), "jonas" + (100 + Math.round(Math.random() * 999)) + "@gmail.com", "asd", "asd");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[7]/td[2]/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "Įvestas slaptažodis per trumpas.");
    }

    @Test
    public void passwordDoesntMatch() {
        fillRegistrationForm("Jonasnezinomas" + (1000 + Math.round(Math.random() * 9999)), "jonas" + (100 + Math.round(Math.random() * 999)) + "@gmail.com", "Slaptazodis1", "Slaptazodis2");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[8]/td[2]/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "Nesutampa slaptažodžiai. Pakartokite.");
    }

    @Test
    public void usernameOneNumberThirtyEight() {
        fillRegistrationForm("11111111111111111111111111111111111111", "jonas" + (100 + Math.round(Math.random() * 999)) + "@gmail.com", "Slaptazodis1", "Slaptazodis1");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "Jūs sėkmingai prisiregistravote!");
    }

    @Test
    public void usernameThreeSymbols() {
        fillRegistrationForm("?!.", "jonas" + (100 + Math.round(Math.random() * 999)) + "@gmail.com", "Slaptazodis1", "Slaptazodis1");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "Vartotojo vardas netinkamas");
    }

    @Test
    public void emailSymbol() {
        fillRegistrationForm("Jonasnezinomas" + (1000 + Math.round(Math.random() * 9999)), "@@gmail.com", "Slaptazodis1", "Slaptazodis1");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "El. pašto adresas nėra tinkamas.");
    }

    @Test
    public void emailPlusEmail() {
        fillRegistrationForm("Jonasnezinomas" + (1000 + Math.round(Math.random() * 9999)), "gmail.com@gmail.com", "Slaptazodis1", "Slaptazodis1");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "El. pašto adresas nėra tinkamas.");
    }

    @Test
    public void japaneseLanguage() {
        fillRegistrationForm("ジョナス", "ジョナス@ジョナス", "ジョナスジョナス", "ジョナスジョナス");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "Vartotojo vardas netinkamas");
    }

    @Test
    public void russianLanguage() {
        fillRegistrationForm("Джон", "Джон@mail.ru", "ДжонДжон", "ДжонДжон");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "Vartotojo vardas netinkamas");
    }

    @Test
    public void usernameAstrologicalSymbols() {
        fillRegistrationForm("卍卍卍", "jonas" + (100 + Math.round(Math.random() * 999)) + "@gmail.com", "Slaptazodis1", "Slaptazodis1");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "Vartotojo vardas netinkamas");
    }

    @Test
    public void usernameSwearword() {
        fillRegistrationForm("Bitch", "jonas" + (100 + Math.round(Math.random() * 999)) + "@gmail.com", "Slaptazodis1", "Slaptazodis1");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "Vartotojo vardas netinkamas");
    }

    @Test
    public void emailCompanies() {
        fillRegistrationForm("Jonasnezinomas" + (1000 + Math.round(Math.random() * 9999)), "jonas@gauto.aibe.lt", "Slaptazodis1", "Slaptazodis1");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "Jūs sėkmingai prisiregistravote!");
    }

    @Test
    public void emailWithAstrologicalSymbols() {
        fillRegistrationForm("Jonasnezinomas" + (1000 + Math.round(Math.random() * 9999)), "卍卍卍@one.lt", "Slaptazodis1", "Slaptazodis1");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "El. pašto adresas nėra tinkamas.");
    }

    @Test
    public void emailNamePlusSurname() {
        fillRegistrationForm("Jonasnezinomas" + (1000 + Math.round(Math.random() * 9999)), "jonas+jonas@one.lt", "Slaptazodis1", "Slaptazodis1");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "El. pašto adresas nėra tinkamas.");
    }

    @Test
    public void emailAllCapitalLetters() {
        fillRegistrationForm("Jonasnezinomas" + (1000 + Math.round(Math.random() * 9999)), "JONIKASZVERIAUSKIS@one.lt", "Slaptazodis1", "Slaptazodis1");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "El. pašto adresas nėra tinkamas.");
    }

    @Test
    public void emailRussianLetters() {
        fillRegistrationForm("Jonasnezinomas" + (1000 + Math.round(Math.random() * 9999)), "лапстасS@one.lt", "Slaptazodis1", "Slaptazodis1");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "El. pašto adresas nėra tinkamas.");
    }

    @Test
    public void emailWithoutEta() {
        fillRegistrationForm("Jonasnezinomas" + (1000 + Math.round(Math.random() * 9999)), "jonasgmail.com", "Slaptazodis1", "Slaptazodis1");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "El. pašto adresas nėra tinkamas.");
    }

    @Test
    public void emailWithoutEnd() {
        fillRegistrationForm("Jonasnezinomas" + (1000 + Math.round(Math.random() * 9999)), "jonas@gmail", "Slaptazodis1", "Slaptazodis1");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "El. pašto adresas nėra tinkamas.");
    }

    @Test
    public void emailWithProcent() {
        fillRegistrationForm("Jonasnezinomas" + (1000 + Math.round(Math.random() * 9999)), "jonas%%@gmail.com", "Slaptazodis1", "Slaptazodis1");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "El. pašto adresas nėra tinkamas.");
    }

    @Test
    public void emailWithCopyrightSign() {
        fillRegistrationForm("Jonasnezinomas" + (1000 + Math.round(Math.random() * 9999)), "jonas©@gmail.com", "Slaptazodis1", "Slaptazodis1");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "El. pašto adresas nėra tinkamas.");
    }

    @Test
    public void emailWithFiveNumbers() {
        fillRegistrationForm("Jonasnezinomas" + (1000 + Math.round(Math.random() * 9999)), (10000 + Math.round(Math.random() * 99999)) + "@gmail.com", "Slaptazodis1", "Slaptazodis1");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "El. pašto adresas nėra tinkamas.");
    }

    @Test
    public void emailWithIpadress() {
        fillRegistrationForm("Jonasnezinomas" + (1000 + Math.round(Math.random() * 9999)), "jonas@192.168.2.1", "Slaptazodis1", "Slaptazodis1");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "El. pašto adresas nėra tinkamas.");
    }

    @Test
    public void emailBackWords() {
        fillRegistrationForm("Jonasnezinomas" + (1000 + Math.round(Math.random() * 9999)), "moc.liamg@sanoj", "Slaptazodis1", "Slaptazodis1");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "El. pašto adresas nėra tinkamas.");
    }

    @Test
    public void usernameWithPlus() {
        fillRegistrationForm("Jonasnezinomas+" + (1000 + Math.round(Math.random() * 9999)), "jonas" + (100 + Math.round(Math.random() * 999)) + "@gmail.com", "Slaptazodis1", "Slaptazodis1");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[1]/td[2]/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "Vartotojo vardas netinkamas");
    }

    @Test
    public void emailDublicate() {
        fillRegistrationForm("Jonasnezinomas" + (1000 + Math.round(Math.random() * 9999)), "jonas@gmail.com", "Slaptazodis1", "Slaptazodis1");
        String actual = "";
        try {
            actual = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actual, "Toks el. pašto adresas jau įregistruotas.");
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