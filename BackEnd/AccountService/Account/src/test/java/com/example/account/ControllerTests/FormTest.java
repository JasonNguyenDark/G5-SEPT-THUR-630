package com.example.account.ControllerTests;

import com.example.account.Controller.FormController;
import com.example.account.Model.Admin;
import com.example.account.Model.Doctor;
import com.example.account.Model.Login;
import com.example.account.Model.User;
import com.example.account.Repository.AdminRepository;
import com.example.account.Repository.DoctorRepository;
import com.example.account.Repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FormTest {

    private static User user;

    @Autowired
    private FormController formController;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private DoctorRepository doctorRepository;

    @MockBean
    private AdminRepository adminRepository;

    @BeforeAll
    public static void setUp() {
        user = new User();
        user.setName("John");
        user.setSurname("Smith");
        user.setEmail("John@yahoo.com");
        user.setGender("Male");
        user.setPassword("abcd1234");
    }

    @Test
    public void successfullyCreatedAccount() {
        formController.SignUp(user);

        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        User result = userRepository.findByEmail(user.getEmail());

        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getSurname(), result.getSurname());
        assertEquals(user.getGender(), result.getGender());
        assertEquals(user.getPassword(), result.getPassword());
    }

    //check if the email the user entered is invalid(meaning email is already used)

    //should return false since theres the email already registered
    @Test
    public void InvalidEmailUser() {
        formController.SignUp(user);

        Login login = new Login();

        login.setEmail(user.getEmail());

        Mockito.when(userRepository.findByEmail(login.getEmail())).thenReturn(user);

        Boolean result = formController.checkEmail(login);

        assertFalse(result);
    }

    //should return false since theres the email already registered in doctor table
    @Test
    public void InvalidEmailDoctor() {

        Doctor doctor = new Doctor();
        doctor.setName("Emily");
        doctor.setSurname("Baker");
        doctor.setEmail("emily@gmail.com");
        doctor.setGender("Female");
        doctor.setPassword("abcd1234");

        doctorRepository.save(doctor);

        Login login = new Login();

        login.setEmail(doctor.getEmail());

        Mockito.when(doctorRepository.findByEmail(login.getEmail())).thenReturn(doctor);

        Boolean result = formController.checkEmail(login);

        assertFalse(result);
    }

    @Test
    public void InvalidEmailAdmin() {

        Admin admin = new Admin();
        admin.setName("Jessica");
        admin.setSurname("Nguyen");
        admin.setEmail("jess123@outlook.com");
        admin.setGender("Female");
        admin.setPassword("abcd1234");

        adminRepository.save(admin);

        Login login = new Login();

        login.setEmail(admin.getEmail());

        Mockito.when(adminRepository.findByEmail(login.getEmail())).thenReturn(admin);

        Boolean result = formController.checkEmail(login);

        assertFalse(result);
    }

    @Test
    public void checkEmailExist() {

        Login login = new Login();

        login.setEmail("newEmail@gmail.com");

        Mockito.when(userRepository.findByEmail(login.getEmail())).thenReturn(null);

        Boolean result = formController.checkEmail(login);

        assertTrue(result);
    }

    @Test
    public void getPatientUsername() {
        formController.SignUp(user);

        Login login = new Login();

        login.setEmail(user.getEmail());

        Mockito.when(userRepository.findByEmail(login.getEmail())).thenReturn(user);

        String result = formController.getUsername(login);

        String expected = user.getName() + ' ' + user.getSurname();

        assertEquals(expected, result);
    }

    @Test
    public void getDoctorUsername() {
        formController.SignUp(user);

        Doctor doctor = new Doctor();
        doctor.setName("Emily");
        doctor.setSurname("Baker");
        doctor.setEmail("emily@gmail.com");
        doctor.setGender("Female");
        doctor.setPassword("abcd1234");

        Login login = new Login();

        login.setEmail(doctor.getEmail());

        Mockito.when(doctorRepository.findByEmail(login.getEmail())).thenReturn(doctor);

        String result = formController.getUsername(login);

        String expected = doctor.getName() + ' ' + doctor.getSurname();

        assertEquals(expected, result);
    }
}
