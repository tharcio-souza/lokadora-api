package br.com.tharcio.lokadoraapi.daos.request

import br.com.tharcio.lokadoraapi.validation.EmailAvaible
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class PostUserRequest(

    @field:NotEmpty(message = "A name must be informed")
    var name: String,
    @field:Email(message = "A valid e-mail must be informed")
    @EmailAvaible
    var email: String,
    @field:NotEmpty(message = "A password must be informed")
    var password: String

)