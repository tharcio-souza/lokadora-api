package br.com.tharcio.lokadoraapi.validation

import javax.validation.Constraint
import kotlin.reflect.KClass

@Constraint(validatedBy = [EmailAvaibleValidator::class])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class EmailAvaible(
    val message: String = "E-mail already in use",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<*>> = []
)
