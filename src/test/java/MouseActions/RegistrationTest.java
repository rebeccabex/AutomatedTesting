package MouseActions;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RegistrationTest {

    private DemoQA demoQA;

    @BeforeClass
    public void setup() {

        demoQA = new DemoQA("chrome");

        demoQA.begin("registration/");

    }

    @AfterClass
    public void teardown() {
        demoQA.end(3000);
    }

    @Test
    public void newUser() {

        demoQA.typeTextById("name_3_firstname","John");
        demoQA.typeTextById("name_3_lastname","Smith");

        demoQA.selectOptionByCSS("#pie_register > li:nth-child(2) > div > div > input:nth-child(2)");

        demoQA.selectOptionByCSS("#pie_register > li:nth-child(3) > div > div.radio_wrap > input:nth-child(2)");
        demoQA.selectOptionByCSS("#pie_register > li:nth-child(3) > div > div.radio_wrap > input:nth-child(4)");
        demoQA.selectOptionByCSS("#pie_register > li:nth-child(3) > div > div.radio_wrap > input:nth-child(6)");

        demoQA.selectFromDropDownById("dropdown_7", "United Kingdom");
        demoQA.selectFromDropDownById("mm_date_8", "1");
        demoQA.selectFromDropDownById("dd_date_8", "10");
        demoQA.selectFromDropDownById("yy_date_8", "1992");

        demoQA.typeTextById("phone_9","01234567890");
        demoQA.typeTextById("username","jsmith");
        demoQA.typeTextById("email_1","jsmith@email.com");

        demoQA.typeTextById("description","I am a test user.");
        demoQA.typeTextById("password_2","password123");
        demoQA.typeTextById("confirm_password_password_2","password123");

        demoQA.clickByName("pie_submit");
    }

}
