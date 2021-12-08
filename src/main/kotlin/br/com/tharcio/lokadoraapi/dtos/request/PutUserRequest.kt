package br.com.tharcio.lokadoraapi.dtos.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class PutUserRequest(

    @field:NotEmpty(message = "A name must be informed")
    var name: String,

    @field:Email(message = "A valid e-mail must be informed")
    var email: String

)