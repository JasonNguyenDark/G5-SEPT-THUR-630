package com.example.backend.controller;

import com.example.backend.model.Doctor;
import com.example.backend.model.Login;
import com.example.backend.model.User;
import com.example.backend.repository.DoctorRepository;
import com.example.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
public class FormControllerTest {

    @MockBean
    private DoctorRepository doctorRepo;

    @MockBean
    private UserRepository usrRepo;

    @Autowired
    private FormController controller;

    @Autowired
    MockMvc mockMvc;

    @BeforeAll
    public static void initAll() {

    }

    @Test
    @DisplayName("Test sending JSON to editingProfile")
    void editProfile() throws Exception {

        // set the fake database user
        User user = new User();
        int id = 1;
        user.setId(id);
        user.setName("Bob");
        user.setSurname("Giovanna");
        user.setGender("M");

        String userEmail = "john@gmail.com";
        user.setEmail(userEmail);
        user.setBio("Hi my name is Bob");
        user.setAge(25);

        // set the params for changing the database user.
        User editedUser = new User();
        editedUser.setId(id);
        editedUser.setName("Giorno");
        editedUser.setSurname("Gucci");
        editedUser.setGender("F");
        editedUser.setEmail(userEmail);
        editedUser.setBio("I, Giorno, have a Gucci dream");

        //   mock
        given(this.usrRepo.findByEmail(userEmail)).willReturn(user);

        //
        User newUser = this.controller.editProfile(editedUser);

        // anything else to return?
        assertEquals(newUser.getName(), editedUser.getName());
        // this should be enough for assert equals
    }

    @Test
    void successfulDoctorLogin() {

        // source of mocking https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.testing.spring-boot-applications.mocking-beans
        // This section of code gives the functionality
        // given(this.remoteService.getValue()).willReturn("spring");
        // String reverse = this.reverser.getReverseValue(); // Calls injected RemoteService
        // assertThat(reverse).isEqualTo("gnirps");

        //  Arrange: Ensure the doctor has already been added to the database.
        //  done so in the init()
        //        Act: login with the correct email and password.

        String email = "vjtgisurndgjkdmnlkv@gmail.com";
        String password = "5731659vbghsrkd932";
        Doctor personA = new Doctor();
        personA.setEmail(email);
        personA.setPassword(password);
        // this mocks the functionality of doctorRepo.
        given(this.doctorRepo.findByEmailAndPassword(email,password)).willReturn(personA);

        Login login = new Login();
        login.setEmail(email);
        login.setPassword(password);

        // Assert: login method returns successful.
        // use the form controller method

        String expectedReturn = "doctor";

        // doctorRepo is injected into controller and mocks its functionality.
        assertEquals(expectedReturn, this.controller.Login(login),
                "Login as doctor" );
    }

    @Test
    void successSignUp() {
        //        Arrange: Ensure patient details are not already on the database.
        // The mock database is already empty.
        String email = "mnvbxcmnxcbvmxcvb@gmail.com";
        String password = "pwqru8932rgv34uiwhr832";
        User personA = new User();
        personA.setEmail(email);
        personA.setPassword(password);


        // Act: use sign up method. Add any email and password.

        // Assert: sign up method returns successful, or able to find
        // the user on the repository.
        Boolean signUpUnique = true;
        assertEquals(signUpUnique, controller.SignUp(personA));
    }

    /* Flutter tests
    @Test
    void missingSignUpBlankPassword() {
        //        Arrange: email and blank password
        String email = "fsdnfsdofvnsfse@gmail.com";
        String missingPassword = "";
        User personA = new User();
        personA.setEmail(email);
        personA.setPassword(missingPassword);
        //        Act: signup with missing username and or password.
        //        Assert: sign up method returns unsuccessful.
        //        Method prints the appropriate message for both fields.
        fail("Functionality for sign up not implemented.");

    }
    @Test
    void missingSignUpNullPassword() {
        //  Arrange: email and null password
        String email = "fsdnfsdofvnsfse@gmail.com";
        User personA = new User();
        personA.setEmail(email);
        //        Act: signup with missing username and or password.
        //        Assert: sign up method returns unsuccessful.
        //        Method prints the appropriate message for both fields.
        fail("Functionality for sign up not implemented.");


    }
    @Test
    void missingSignUpBlankEmail() {
        //  Arrange: blank email and password
        String blankEmail = "";
        String password = "32535235235";
        User personA = new User();
        personA.setEmail(blankEmail);
        personA.setPassword(password);
        //        Act: signup with missing username and or password.
        //        Assert: sign up method returns unsuccessful.
        //        Method prints the appropriate message for both fields.
        fail("Functionality for sign up not implemented.");


    }
    @Test
    void missingSignUpNullEmail() {
        //  Arrange: null email and password
        String password = "32535235235";
        User personA = new User();
        personA.setPassword(password);
        //        Act: signup with missing username and or password.
        //        Assert: sign up method returns unsuccessful.
        //        Method prints the appropriate message for both fields.
        fail("Functionality for sign up not implemented.");


    }
    */
    @Test
    void registeredSignUp() {
        //        Arrange: Ensure the patient has already been added to the database.
        String email = "fasjfnsfsgsfnvo@gmail.com";
        String password = "852385723095";

        User personA = new User();
        personA.setEmail(email);
        personA.setPassword(password);
        given(usrRepo.findByEmail(email)).willReturn(personA);

        //        Act: signup with a registered email
        String loginEmail = email;

        //        Assert: sign up method returns unsuccessful.
        Boolean userIsRegistered = false;
        assertEquals(userIsRegistered, controller.SignUp(personA));
    }
    @Test
    void successfulUserLogin() {
        //        Arrange: Ensure the patient has already been added to the database.
        String email = "mnvbxcmnxcbvmxcvb@gmail.com";
        String password = "pwqru8932rgv34uiwhr832";
        User personA = new User();
        personA.setEmail(email);
        personA.setPassword(password);
        // this mocks the functionality of usrRepo method.
        given(this.usrRepo.findByEmailAndPassword(email,password)).willReturn(personA);

        //        Act: login with the correct email and password.

        Login login = new Login();
        login.setEmail(email);
        login.setPassword(password);

        // Assert: login method returns successful.
        // use the form controller method

        String expectedReturn = "user";

        // usrRepo is injected into controller and mocks its functionality.
        assertEquals(expectedReturn, this.controller.Login(login),
                "Login as user" );

    }

    /* Tests for flutter.
    //@Test
    //void signInNullEmail() {
    //    //        Arrange: password
    //    //        Act: login with email null.
    //    String password = "84354305834";
    //    Login login = new Login();
    //    login.setPassword(password);
    //
    //    //        Assert: login method returns null.
    //    assertNull(controller.Login(login));
    //    fail("Functionality for sign in not implemented.");
    //
    //}
    //@Test
    //void signInBlankEmail() {
    //    //        Arrange: password, blank mail
    //    //        Act: login with email blank.
    //    String blankEmail = "";
    //    String password = "84354305834";
    //    Login login = new Login();
    //    login.setEmail(blankEmail);
    //    login.setPassword(password);
    //
    //    //        Assert: login method returns null.
    //    assertNull(controller.Login(login));
    //    fail("Functionality for sign in not implemented.");
    //
    //}
    //@Test
    //void signInNullPassword() {
    //    //        Arrange: email
    //    //        Act: login with password null.
    //    String email = "645276r84982674867@gmail.com";
    //    Login login = new Login();
    //    login.setEmail(email);
    //
    //    //        Assert: login method returns unsuccessful.
    //    assertNull(controller.Login(login));
    //
    //    fail("Functionality for sign in not implemented.");
    //
    //}
    //@Test
    //void signInBlankPassword() {
    //    //        Arrange: email, blank password
    //    //        Act: login with password blank.
    //    String email = "645276r84982674867@gmail.com";
    //    String password = "";
    //    Login login = new Login();
    //    login.setEmail(email);
    //    login.setPassword(password);
    //
    //    //        Assert: login method returns unsuccessful.
    //    assertNull(controller.Login(login));
    //
    //    fail("Functionality for sign in not implemented.");
    //
    //}
    //
    //@Test
    //void unregisteredLogin() {
    //    //        Arrange: Ensure the email is not registered. The mock repository is already empty.
    //    //        Act: enter an email that is not on the database and any password.
    //    String email = "gvuodrjgovidj@gmail.com";
    //    String password = "fsefsefsifseifesjfsejfisejfi";
    //    Login login = new Login();
    //    login.setEmail(email);
    //    login.setPassword(password);
    //
    //    //        Assert: login method returns unsuccessful.
    //    assertNull(controller.Login(login));
    //    fail("Functionality for login is not implemented.");
    //
    //}
    //
    //@Test
    //void passwordWrongLogin() {
    //    //        Arrange: Ensure the email is registered.
    //
    //    //        Act: enter a registered email and any password but the correct password (stored on the database)
    //    String email = "gvuodrjgovidj@gmail.com";
    //    String password = "fsefsefsifseifesjfsejfisejfi";
    //
    //    User personA = new User();
    //    personA.setEmail(email);
    //    personA.setPassword(password);
    //    given(this.usrRepo.findByEmailAndPassword(email, password)).willReturn(personA);
    //
    //    String fakePassword = "fnsfkisodvfmsdfkisdoefv";
    //    Login login = new Login();
    //    login.setEmail(email);
    //    login.setPassword(fakePassword);
    //
    //    //        Assert: login method returns unsuccessful.
    //    //        Error message returned should be that the password is incorrect.
    //    assertNull(controller.Login(login));
    //    fail("Functionality for login is not implemented.");
    //
    //}
    */

    @Test
    @DisplayName("Check if email exists")
    void checkEmail() {
        Login login = new Login();
        String email = "asd@mail.com";
        String password = "x";
        login.setEmail(email);
        login.setPassword(password);

        User fakeUser = new User();

//        Assert: check if email exists. If it does, return false.
//        email checked in either user or doctor repository.
        given(usrRepo.findByEmail(email)).willReturn(fakeUser);
        assertFalse(controller.checkEmail(login));
    }
    @Test
    void getAllUsers() throws Exception {
        ArrayList<User> users = new ArrayList<>();
        final int ITERABLE = 5;
        for (int i = 0; i < ITERABLE; ++i) {
            User user = new User();
            user.setId(i);
            users.add(user);
        }

        Mockito.when(usrRepo.findAll()).thenReturn(users);

        MvcResult result = mockMvc.perform(get("/form/all"))
                .andExpect(status().isOk())
                .andReturn()
        ;
        String resultString = result.getResponse().getContentAsString();
        assertTrue( resultString.contains("\"id\":0") );
        assertTrue( resultString.contains("\"id\":1") );
        assertTrue( resultString.contains("\"id\":2") );
        assertTrue( resultString.contains("\"id\":3") );
        assertTrue( resultString.contains("\"id\":4") );

    }
    @Test
    void getAllDoctors() throws Exception {
        ArrayList<Doctor> doctors = new ArrayList<>();
        final int ITERABLE = 5;
        for (int i = 0; i < ITERABLE; ++i) {
            Doctor doctor = new Doctor();
            doctor.setId(i);
            doctors.add(doctor);
        }

        Mockito.when(doctorRepo.findAll()).thenReturn(doctors);

        MvcResult result = mockMvc.perform(get("/form/allDoc"))
                .andExpect(status().isOk())
                .andReturn()
                ;
        String resultString = result.getResponse().getContentAsString();
        assertTrue( resultString.contains("\"id\":0") );
        assertTrue( resultString.contains("\"id\":1") );
        assertTrue( resultString.contains("\"id\":2") );
        assertTrue( resultString.contains("\"id\":3") );
        assertTrue( resultString.contains("\"id\":4") );
    }
}
