package br.com.tharcio.lokadoraapi.validation

import br.com.tharcio.lokadoraapi.services.impl.UserServiceImpl
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext


class EmailAvaibleValidator(val userService: UserServiceImpl): ConstraintValidator<EmailAvaible, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value.isNullOrEmpty()){
            return false
        }

        return userService.emailIsAvaible(value)
    }

}




