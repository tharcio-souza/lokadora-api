package br.com.tharcio.lokadoraapi.controllers

import br.com.tharcio.lokadoraapi.daos.request.PostUserRequest
import br.com.tharcio.lokadoraapi.daos.request.PutUserRequest
import br.com.tharcio.lokadoraapi.daos.response.UserResponse
import br.com.tharcio.lokadoraapi.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("user")
class UserController(
    private val userService: UserService
) {

    @GetMapping
    fun getAll(): List<UserResponse> {
        return userService.getAll()
    }

    @GetMapping("/{id}")
    fun getByid(@PathVariable id: Int ): UserResponse {
        return userService.getById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody user: PostUserRequest) {
        userService.create(user)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateUser(@PathVariable id: Int, @RequestBody user: PutUserRequest){
        userService.update(id, user)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id: Int) {
        userService.delete(id)

    }
}