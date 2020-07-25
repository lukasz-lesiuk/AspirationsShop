package com.codecool.access;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordCheckerTest {
    PasswordChecker checker;

    @BeforeEach
    void prepareTests(){
        checker = new PasswordChecker();
    }

    @Test
    void isPasswordGood_GoodPassword_true(){
        assertTrue(checker.isPasswordGood("gHr#654caF"));
    }

    @Test
    void isPasswordGood_TooShort_false() {
        assertFalse(checker.isPasswordGood("1@qW"));
    }

    @Test
    void isPasswordGood_EightCharacters_True() {
        assertTrue(checker.isPasswordGood("1@qWb5F$"));
    }

    @Test
    void isPasswordGood_NotComplex_false() {
        assertFalse(checker.isPasswordGood("1234qwer"));
    }

    @Test
    void isPasswordGood_GoodPasswordNoSpecials_true(){
        assertTrue(checker.isPasswordGood("vdfFRE485"));
    }

    @Test
    void isPasswordGood_GoodPasswordNoLowercase_true(){
        assertTrue(checker.isPasswordGood("&$#FRE485"));
    }

    @Test
    void isPasswordGood_GoodPasswordNoUpperCase_true(){
        assertTrue(checker.isPasswordGood("vdf^#&485"));
    }

    @Test
    void isPasswordGood_GoodPasswordNoDigits_true(){
        assertTrue(checker.isPasswordGood("vdf^#&GID"));
    }
}