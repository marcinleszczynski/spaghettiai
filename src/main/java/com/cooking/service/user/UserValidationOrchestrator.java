package com.cooking.service.user;

import com.cooking.controller.auth.dto.UserRegistrationDto;
import com.cooking.dao.repository.user.UserRepository;
import com.cooking.service.common.exception.RegistrationException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

import static com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

@Service
@RequiredArgsConstructor
public class UserValidationOrchestrator {

    private final UserRepository userRepository;

    public void orchestrate(UserRegistrationDto dto) {
        validateEmail(dto.getEmail());
        validatePassword(dto.getPassword());
        validatePhoneNumber(dto.getPhoneNumber());
    }

    private void validateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new RegistrationException("Email already exists");
        }

        if (!EmailValidator.getInstance().isValid(email)) {
            throw new RegistrationException("Provided email is not valid");
        }
    }

    private void validatePassword(String password) {
        var passwordLength = password.length();
        if (passwordLength < 8 || passwordLength > 20) {
            throw new RegistrationException("Password must be between 8 and 20 characters");
        }

        var specialCharacterRegex = ".*[!@#$^&*()\\-_].*";

        if (!password.matches(specialCharacterRegex)) {
            throw new RegistrationException("Password must contain at least one special character ( !, @, #, $, ^, &, *, (, ), -, or _)");
        }
    }

    private void validatePhoneNumber(String phoneNumber) {
        var phoneNumberUtil = PhoneNumberUtil.getInstance();
        var number = new PhoneNumber();
        number.setCountryCode(48).setNationalNumber(Long.parseLong(phoneNumber));

        if (phoneNumberUtil.isPossibleNumber(number)) {
            throw new RegistrationException("Provided phone number is not valid");
        }
    }
}
