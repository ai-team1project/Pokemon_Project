package org.koreait.member.validators;

import org.koreait.global.vakudatirs.PasswordValidator;
import org.koreait.member.controllers.RequestAgree;
import org.koreait.member.controllers.RequestJoin;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.Period;

@Lazy
@Component
public class JoinValidator implements Validator, PasswordValidator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RequestAgree.class)|| clazz.isAssignableFrom(RequestJoin.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof RequestAgree requestAgree){
            validateAgree(requestAgree,errors);
        }else {
            validateJoin((RequestJoin)target,errors);
        }
    }
    private void validateAgree(RequestAgree form, Errors errors){
        if (!form.isRequiredTerms1()){
            errors.rejectValue("requiredTerms1","AssertTrue");
        }
        if (!form.isRequiredTerms2()){
            errors.rejectValue("requiredTerms2","AssertTrue");
        }
        if (!form.isRequiredTerms3()){
            errors.rejectValue("requiredTerms3","AssertTrue");
        }
    }
    private void validateJoin(RequestJoin form, Errors errors){
    String email=form.getEmail();
    String password= form.getPassword();
    String confirmPassword=form.getConfirmPassword();
    LocalDate birthDt=form.getBirthDt();
    if(!alphaCheck(password, false)||!numberCheck(password)||!specialCharsCheck(password)){
      errors.rejectValue("password","Complexity");
    }
    if (!password.equals(confirmPassword)){
        errors.rejectValue("confirmPassword","Mismatch");

    }
        Period period = Period.between(birthDt, LocalDate.now());
        int year=period.getYears();
        if (year<14){
            errors.rejectValue("birthDt", "UnderAge");
        }
    }

}
