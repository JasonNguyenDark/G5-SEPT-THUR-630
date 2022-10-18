package com.example.account.ControllerTests;

import com.example.account.Controller.ProfileController;
import com.example.account.Model.Admin;
import com.example.account.Model.Doctor;
import com.example.account.Model.Profile;
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
class ProfileTest {

    private Profile profile;

    private static User user;

    private static Doctor doctor;

    private static Admin admin;

    @Autowired
    private ProfileController profileController;

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

        doctor = new Doctor();
        doctor.setName("Emily");
        doctor.setSurname("Baker");
        doctor.setEmail("emily@gmail.com");
        doctor.setGender("Female");
        doctor.setPassword("abcd1234");

        admin = new Admin();
        admin.setName("Jessica");
        admin.setSurname("Nguyen");
        admin.setEmail("jess123@outlook.com");
        admin.setGender("Female");
        admin.setPassword("abcd1234");
    }

    @Test
    public void getUserAccount() {
        profile = new Profile();
        profile.setEmail("John@yahoo.com");
        profile.setRole("user");

        Mockito.when(userRepository.findByEmail(profile.getEmail())).thenReturn(user);

        Object result = profileController.getProfile(profile);

        assertEquals(result.getClass(), user.getClass());
    }

    @Test
    public void getDoctorAccount() {
        profile = new Profile();
        profile.setEmail("emily@gmail.com");
        profile.setRole("doctor");

        Mockito.when(doctorRepository.findByEmail(profile.getEmail())).thenReturn(doctor);

        Object result = profileController.getProfile(profile);

        assertEquals(result.getClass(), doctor.getClass());
    }

    @Test
    public void getAdminAccount() {
        profile = new Profile();
        profile.setEmail("jess123@outlook.com");
        profile.setRole("admin");

        Mockito.when(adminRepository.findByEmail(profile.getEmail())).thenReturn(admin);

        Object result = profileController.getProfile(profile);

        assertEquals(result.getClass(), admin.getClass());
    }

    @Test
    public void updateUserEmail() {
        profile = new Profile();
        profile.setEmail("John@yahoo.com");
        profile.setNewEmail("John@gmail.com");
        profile.setRole("user");

        profileController.updateEmail(profile);

        // set the current email as the new email
        user.setEmail(profile.getNewEmail());

        Mockito.when(userRepository.findByEmail(profile.getEmail())).thenReturn(user);

        Object result = profileController.getProfile(profile);

        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        User updated = userRepository.findByEmail(user.getEmail());

        assertEquals(result.getClass(), user.getClass());
        assertEquals(updated.getEmail(), profile.getNewEmail());
    }

    @Test
    public void updateDoctorEmail() {
        profile = new Profile();
        profile.setEmail("emily@gmail.com");
        profile.setNewEmail("emily123@gmail.com");
        profile.setRole("doctor");

        profileController.updateEmail(profile);

        // set the current email as the new email
        doctor.setEmail(profile.getNewEmail());

        Mockito.when(doctorRepository.findByEmail(profile.getEmail())).thenReturn(doctor);

        Object result = profileController.getProfile(profile); //unable to access data within table

        Mockito.when(doctorRepository.findByEmail(doctor.getEmail())).thenReturn(doctor);

        Doctor updated = doctorRepository.findByEmail(doctor.getEmail());

        assertEquals(result.getClass(), doctor.getClass());
        assertEquals(updated.getEmail(), profile.getNewEmail());
    }

    @Test
    public void updateAdminEmail() {
        profile = new Profile();
        profile.setEmail("jess123@outlook.com");
        profile.setNewEmail("jess@outlook.com");
        profile.setRole("admin");

        profileController.updateEmail(profile);

        // set the current email as the new email
        admin.setEmail(profile.getNewEmail());

        Mockito.when(adminRepository.findByEmail(profile.getEmail())).thenReturn(admin);

        Object result = profileController.getProfile(profile); //unable to access data within table

        Mockito.when(adminRepository.findByEmail(admin.getEmail())).thenReturn(admin);

        Admin updated = adminRepository.findByEmail(admin.getEmail());

        assertEquals(result.getClass(), admin.getClass());
        assertEquals(updated.getEmail(), profile.getNewEmail());
    }

    @Test
    public void updateUserFirstName() {
        profile = new Profile();
        profile.setEmail("John@yahoo.com");
        profile.setNewName("Jimmy");
        profile.setRole("user");

        profileController.updateName(profile);

        user.setName(profile.getNewName());

        Mockito.when(userRepository.findByEmail(profile.getEmail())).thenReturn(user);

        Object result = profileController.getProfile(profile);

        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        User updated = userRepository.findByEmail(user.getEmail());

        assertEquals(result.getClass(), user.getClass());
        assertEquals(updated.getName(), profile.getNewName());
    }

    @Test
    public void updateDoctorFirstName() {
        profile = new Profile();
        profile.setEmail("emily@gmail.com");
        profile.setNewName("Kim");
        profile.setRole("doctor");

        profileController.updateName(profile);

        doctor.setName(profile.getNewName());

        Mockito.when(doctorRepository.findByEmail(doctor.getEmail())).thenReturn(doctor);

        Object result = profileController.getProfile(profile);

        Mockito.when(doctorRepository.findByEmail(profile.getEmail())).thenReturn(doctor);

        Doctor updated = doctorRepository.findByEmail(doctor.getEmail());

        assertEquals(result.getClass(), doctor.getClass());
        assertEquals(updated.getName(), profile.getNewName());
    }

    @Test
    public void updateAdminFirstName() {
        profile = new Profile();
        profile.setEmail("jess123@outlook.com");
        profile.setNewName("Jesse");
        profile.setRole("admin");

        profileController.updateName(profile);

        admin.setName(profile.getNewName());

        Mockito.when(adminRepository.findByEmail(profile.getEmail())).thenReturn(admin);

        Object result = profileController.getProfile(profile);

        Mockito.when(adminRepository.findByEmail(admin.getEmail())).thenReturn(admin);

        Admin updated = adminRepository.findByEmail(admin.getEmail());

        assertEquals(result.getClass(), admin.getClass());
        assertEquals(updated.getName(), profile.getNewName());
    }

    @Test
    public void updateUserSurname() {
        profile = new Profile();
        profile.setEmail("John@yahoo.com");
        profile.setNewSurname("Brown");
        profile.setRole("user");

        profileController.updateSurname(profile);

        user.setSurname(profile.getNewSurname());

        Mockito.when(userRepository.findByEmail(profile.getEmail())).thenReturn(user);

        Object result = profileController.getProfile(profile);

        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        User updated = userRepository.findByEmail(user.getEmail());

        assertEquals(result.getClass(), user.getClass());
        assertEquals(updated.getSurname(), profile.getNewSurname());
    }

    @Test
    public void updateDoctorSurname() {
        profile = new Profile();
        profile.setEmail("emily@gmail.com");
        profile.setNewSurname("Smith");
        profile.setRole("doctor");

        profileController.updateSurname(profile);

        doctor.setSurname(profile.getNewSurname());

        Mockito.when(doctorRepository.findByEmail(profile.getEmail())).thenReturn(doctor);

        Object result = profileController.getProfile(profile);

        Mockito.when(doctorRepository.findByEmail(doctor.getEmail())).thenReturn(doctor);

        Doctor updated = doctorRepository.findByEmail(doctor.getEmail());

        assertEquals(result.getClass(), doctor.getClass());
        assertEquals(updated.getSurname(), profile.getNewSurname());
    }

    @Test
    public void updateAdminSurname() {
        profile = new Profile();
        profile.setEmail("jess123@outlook.com");
        profile.setNewSurname("Tran");
        profile.setRole("admin");

        profileController.updateSurname(profile);

        admin.setSurname(profile.getNewSurname());

        Mockito.when(adminRepository.findByEmail(profile.getEmail())).thenReturn(admin);

        Object result = profileController.getProfile(profile);

        Mockito.when(adminRepository.findByEmail(admin.getEmail())).thenReturn(admin);

        Admin updated = adminRepository.findByEmail(admin.getEmail());

        assertEquals(result.getClass(), admin.getClass());
        assertEquals(updated.getSurname(), profile.getNewSurname());
    }

    @Test
    public void updateUserPassword() {
        profile = new Profile();
        profile.setEmail("John@yahoo.com");
        profile.setNewPassword("john123!");
        profile.setRole("user");

        profileController.updatePassword(profile);

        user.setPassword(profile.getNewPassword());

        Mockito.when(userRepository.findByEmail(profile.getEmail())).thenReturn(user);

        Object result = profileController.getProfile(profile);

        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        User updated = userRepository.findByEmail(user.getEmail());

        assertEquals(result.getClass(), user.getClass());
        assertEquals(updated.getPassword(), profile.getNewPassword());
    }

    @Test
    public void updateDoctorPassword() {
        profile = new Profile();
        profile.setEmail("emily@gmail.com");
        profile.setNewPassword("emily123!");
        profile.setRole("doctor");

        profileController.updatePassword(profile);

        doctor.setPassword(profile.getNewPassword());

        Mockito.when(doctorRepository.findByEmail(profile.getEmail())).thenReturn(doctor);

        Object result = profileController.getProfile(profile);

        Mockito.when(doctorRepository.findByEmail(doctor.getEmail())).thenReturn(doctor);

        Doctor updated = doctorRepository.findByEmail(doctor.getEmail());

        assertEquals(result.getClass(), doctor.getClass());
        assertEquals(updated.getPassword(), profile.getNewPassword());
    }

    @Test
    public void updateAdminPassword() {
        profile = new Profile();
        profile.setEmail("jess123@outlook.com");
        profile.setNewPassword("jess123!");
        profile.setRole("admin");

        profileController.updatePassword(profile);

        admin.setPassword(profile.getNewPassword());

        Mockito.when(adminRepository.findByEmail(profile.getEmail())).thenReturn(admin);

        Object result = profileController.getProfile(profile);

        Mockito.when(adminRepository.findByEmail(admin.getEmail())).thenReturn(admin);

        Admin updated = adminRepository.findByEmail(admin.getEmail());

        assertEquals(result.getClass(), admin.getClass());
        assertEquals(updated.getPassword(), profile.getNewPassword());
    }

    @Test
    public void updateUserGender() {
        profile = new Profile();
        profile.setEmail("John@yahoo.com");
        profile.setNewGender("Female");
        profile.setRole("user");

        profileController.updateGender(profile);

        user.setGender(profile.getNewGender());

        Mockito.when(userRepository.findByEmail(profile.getEmail())).thenReturn(user);

        Object result = profileController.getProfile(profile);

        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        User updated = userRepository.findByEmail(user.getEmail());

        assertEquals(result.getClass(), user.getClass());
        assertEquals(updated.getGender(), profile.getNewGender());
    }

    @Test
    public void updateDoctorGender() {
        profile = new Profile();
        profile.setEmail("emily@gmail.com");
        profile.setNewGender("Male");
        profile.setRole("doctor");

        profileController.updateGender(profile);

        doctor.setGender(profile.getNewGender());

        Mockito.when(doctorRepository.findByEmail(profile.getEmail())).thenReturn(doctor);

        Object result = profileController.getProfile(profile);

        Mockito.when(doctorRepository.findByEmail(doctor.getEmail())).thenReturn(doctor);

        Doctor updated = doctorRepository.findByEmail(doctor.getEmail());

        assertEquals(result.getClass(), doctor.getClass());
        assertEquals(updated.getGender(), profile.getNewGender());
    }

    @Test
    public void updateAdminGender() {
        profile = new Profile();
        profile.setEmail("jess123@outlook.com");
        profile.setNewPassword("Other");
        profile.setRole("admin");

        profileController.updateGender(profile);

        admin.setGender(profile.getNewGender());

        Mockito.when(adminRepository.findByEmail(profile.getEmail())).thenReturn(admin);

        Object result = profileController.getProfile(profile);

        Mockito.when(adminRepository.findByEmail(admin.getEmail())).thenReturn(admin);

        Admin updated = adminRepository.findByEmail(admin.getEmail());

        assertEquals(result.getClass(), admin.getClass());
        assertEquals(updated.getGender(), profile.getNewGender());
    }
}
