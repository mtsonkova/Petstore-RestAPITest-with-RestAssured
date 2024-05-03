import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class BaseAPITest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @DataProvider(name = "petInfo")
    public Object[][] createPets() {
        return new Object[][]{
                {"dog", "Ruffus",
                        new String[]{"https://www.rawpixel.com/image/12087075/png-white-background-dog",
                                "https://www.rawpixel.com/image/12057067/png-white-background-dog"},
                        new String[]{"bone tag", "badge", "qrcode", "circle tag"},
                        "available"
                },

                {"dog", "Momo",
                       new String[] {"https://www.rawpixel.com/image/12057067/png-white-background-dog"},
                        new String[]{"bone tag"},
                        "available"
                },

                {"dog", "Lassy",
                        new String[]{"https://www.rawpixel.com/image/12087075/png-white-background-dog",
                                "https://www.rawpixel.com/image/12057067/png-white-background-dog"},
                        new String[]{"qrcode", "circle tag"},
                        "sold"
                },

                {"dog", "Lassy",
                        new String[]{"https://www.rawpixel.com/image/12087075/png-white-background-dog",
                                "https://www.rawpixel.com/image/12057067/png-white-background-dog"},
                        new String[]{"qrcode", "circle tag"},
                        "pending"
                },

                {"gold fish", "Lilo",
                        new String[]{"https://www.petmd.com/fish/care/how-to-take-care-of-goldfish"},
                        new String[]{""},
                        "pending"
                },

                {"gold fish", "Nemo",
                        new String[]{"https://www.petmd.com/fish/care/how-to-take-care-of-goldfish"},
                        new String[]{""},
                        "available"
                },

                {"gold fish", "Dory",
                        new String[]{"https://www.petmd.com/fish/care/how-to-take-care-of-goldfish"},
                        new String[]{""},
                        "sold"
                },

                {"cat", "Fluffy",
                        new String[]{"https://en.wiktionary.org/wiki/cat#/media/File:Cat03.jpg"},
                        new String[]{"qrcode", "circle tag"},
                        "pending"
                },

                {"cat", "Mrs Purr",
                        new String[]{"https://en.wiktionary.org/wiki/cat#/media/File:Cat03.jpg"},
                        new String[]{"circle tag"},
                        "sold"
                },

                {"cat", "Kitty",
                        new String[]{"https://en.wiktionary.org/wiki/cat#/media/File:Cat03.jpg"},
                        new String[]{"paw tag"},
                        "sold"
                },

                {"parrot", "Polly",
                        new String[]{""},
                        new String[]{""},
                        "sold"
                },

                {"parrot", "Lolly",
                        new String[]{""},
                        new String[]{""},
                        "pending"
                },

                {"parrot", "Lorry",
                        new String[]{""},
                        new String[]{""},
                        "available"
                }
        };
    }
}
