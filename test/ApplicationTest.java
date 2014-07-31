import com.google.common.collect.ImmutableMap;
import controllers.Application;
import controllers.routes;
import org.junit.Test;
import play.mvc.Result;
import play.test.FakeRequest;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;


/**
 * Simple (JUnit) tests that can call all parts of a play app.
 * If you are interested in mocking a whole application, see the wiki for more details.
 */
public class ApplicationTest {

    private static final String NAME = "Szymon";
    private static final String LAST_NAME = "Kowalski";
    private static final String EMAIL = "mail@wp.pl";
    private static final String DATE_OF_BIRTH = "2014-07-24";
    private static final String FAVORITE_DB = "2";
    private static final String NOTES = "Lorem ipsum na na na.";

    private static final String LONG_NAME = "AAAAAAAAAAAAAAAAAAAAAAA" +
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" +
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    private static final String INVALID_EMAIL = "abc";

    @Test
    public void testIndex() {
        Result result = Application.index();
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("text/html");
        assertThat(charset(result)).isEqualTo("utf-8");
        assertThat(contentAsString(result)).contains("Welcome!");
    }

    @Test
    public void testAddPerson_ValuesRequired() {
        running(fakeApplication(inMemoryDatabase()) , () -> {
            Result result = callAction(routes.ref.Application.addPerson(), new FakeRequest()
                    .withFormUrlEncodedBody(ImmutableMap.of("lastName", LAST_NAME)));
            assertThat(contentAsString(result)).contains("This field is required");
        });
    }

    @Test
    public void testAddPerson_TooLongName() {
        running(fakeApplication(inMemoryDatabase()), () -> {
            Result result = callAction(routes.ref.Application.addPerson(), new FakeRequest()
                    .withFormUrlEncodedBody(ImmutableMap.of(
                    "name", LONG_NAME, "lastName", LAST_NAME, "email", EMAIL, "dateOfBirth",
                            DATE_OF_BIRTH, "favoriteDb", FAVORITE_DB)));
            assertThat(contentAsString(result)).contains("Maximum length is 100");
        });
    }

    @Test
    public void testAddPerson_InvalidEmail() {
        running(fakeApplication(inMemoryDatabase()), () -> {
            Result result = callAction(routes.ref.Application.addPerson(), new FakeRequest()
                    .withFormUrlEncodedBody(ImmutableMap.of(
                    "name", NAME, "lastName", LAST_NAME, "email", INVALID_EMAIL, "dateOfBirth",
                            DATE_OF_BIRTH, "favoriteDb", FAVORITE_DB)));
            assertThat(contentAsString(result)).contains("Must satisfy .+\\@.+\\..+");
        });
    }

    @Test
    public void testAddPerson_OK() {
        running(fakeApplication(inMemoryDatabase()), () -> {
            Result result = callAction(routes.ref.Application.addPerson(), new FakeRequest()
                    .withFormUrlEncodedBody(ImmutableMap.of(
                    "name", NAME, "lastName", LAST_NAME, "email", EMAIL, "dateOfBirth",
                            DATE_OF_BIRTH, "favoriteDb", FAVORITE_DB)));
            assertThat(contentAsString(result)).contains("Person has been added.");
        });
    }
}
