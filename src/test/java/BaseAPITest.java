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
                        "bone tag",
                        "available"
                },

                {"dog", "Momo",
                        new String[]{"https://www.rawpixel.com/image/12057067/png-white-background-dog"},
                        "circle tag",
                        "available"
                },

                {"dog", "Lassy",
                        new String[]{"https://www.rawpixel.com/image/12087075/png-white-background-dog",
                                "https://www.rawpixel.com/image/12057067/png-white-background-dog"},
                        "qrcode",
                        "sold"
                },

                {"dog", "Lassy",
                        new String[]{"https://www.rawpixel.com/image/12087075/png-white-background-dog",
                                "https://www.rawpixel.com/image/12057067/png-white-background-dog"},
                        "circle tag",
                        "pending"
                },

                {"gold fish", "Lilo",
                        new String[]{"https://www.petmd.com/fish/care/how-to-take-care-of-goldfish"},
                        "",
                        "pending"
                },

                {"gold fish", "Nemo",
                        new String[]{"https://www.petmd.com/fish/care/how-to-take-care-of-goldfish"},
                        "",
                        "available"
                },

                {"gold fish", "Dory",
                        new String[]{"https://www.petmd.com/fish/care/how-to-take-care-of-goldfish"},
                        "",
                        "sold"
                },

                {"cat", "Fluffy",
                        new String[]{"https://en.wiktionary.org/wiki/cat#/media/File:Cat03.jpg"},
                        "heart tag",
                        "pending"
                },

                {"cat", "Mrs Purr",
                        new String[]{"https://en.wiktionary.org/wiki/cat#/media/File:Cat03.jpg"},
                        "paw tag",
                        "sold"
                },

                {"cat", "Kitty",
                        new String[]{"https://en.wiktionary.org/wiki/cat#/media/File:Cat03.jpg"},
                        "paw tag",
                        "sold"
                },

                {"parrot", "Polly",
                        new String[]{""},
                        "",
                        "sold"
                },

                {"parrot", "Lolly",
                        new String[]{""},
                        "",
                        "pending"
                },

                {"parrot", "Lorry",
                        new String[]{""},
                        "",
                        "available"
                }
        };

    }
    @DataProvider(name = "petInfo2")
    public Object[][] createPets2() {
        return new Object[][]{
                {"dog", "Ruffus",
                       "https://www.rawpixel.com/image/12087075/png-white-background-dog",
                        "bone tag",
                        "available"
                },

                {"dog", "Momo",
                        "https://www.rawpixel.com/image/12057067/png-white-background-dog",
                        "circle tag",
                        "available"
                },

                {"dog", "Lassy",
                        "https://www.rawpixel.com/image/12057067/png-white-background-dog",
                        "qrcode",
                        "sold"
                },

                {"dog", "Lassy",
                        "https://www.rawpixel.com/image/12057067/png-white-background-dog",
                        "circle tag",
                        "pending"
                },

                {"gold fish", "Lilo",
                        "https://www.petmd.com/fish/care/how-to-take-care-of-goldfish",
                        "",
                        "pending"
                },

                {"gold fish", "Nemo",
                        "https://www.petmd.com/fish/care/how-to-take-care-of-goldfish",
                        "",
                        "available"
                },

                {"gold fish", "Dory",
                        "https://www.petmd.com/fish/care/how-to-take-care-of-goldfish",
                        "",
                        "sold"
                },

                {"cat", "Fluffy",
                        "https://en.wiktionary.org/wiki/cat#/media/File:Cat03.jpg",
                        "heart tag",
                        "pending"
                },

                {"cat", "Mrs Purr",
                        "https://en.wiktionary.org/wiki/cat#/media/File:Cat03.jpg",
                        "paw tag",
                        "sold"
                },

                {"cat", "Kitty",
                        "https://en.wiktionary.org/wiki/cat#/media/File:Cat03.jpg",
                        "paw tag",
                        "sold"
                },

                {"parrot", "Polly",
                        "",
                        "",
                        "sold"
                },

                {"parrot", "Lolly",
                        "",
                        "",
                        "pending"
                },

                {"parrot", "Lorry",
                        "",
                        "",
                        "available"
                }
        };

    }
}
