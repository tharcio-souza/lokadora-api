package br.com.tharcio.lokadoraapi.validation

import br.com.tharcio.lokadoraapi.services.UserService
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext


class EmailAvaibleValidator(val userService: UserService): ConstraintValidator<EmailAvaible, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value.isNullOrEmpty()){
            return false
        }

        return userService.emailIsAvaible(value)
    }

}




