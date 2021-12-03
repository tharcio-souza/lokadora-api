package br.com.tharcio.lokadoraapi.controllers

import br.com.tharcio.lokadoraapi.daos.request.PostUserRequest
import br.com.tharcio.lokadoraapi.daos.request.PutUserRequest
import br.com.tharcio.lokadoraapi.daos.response.PageResponse
import br.com.tharcio.lokadoraapi.daos.response.UserResponse
import br.com.tharcio.lokadoraapi.extensions.toPageResponse
import br.com.tharcio.lokadoraapi.services.UserService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("users")
class UserController(
    private val userService: UserService
) {

    @GetMapping
    fun getAll(@PageableDefault(page = 0, size = 10) pageable:Pageable, @RequestParam name: String?): PageResponse<UserResponse> {
        return userService.getAll(pageable, name).toPageResponse()
    }

    @GetMapping("/{id}")
    fun getByid(@PathVariable id: Int ): UserResponse {
        return userService.getById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody @Valid user: PostUserRequest) {
        userService.create(user)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateUser(@PathVariable id: Int, @RequestBody @Valid user: PutUserRequest){
        userService.update(id, user)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id: Int) {
        userService.delete(id)

    }
}