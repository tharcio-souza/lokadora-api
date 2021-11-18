package br.com.tharcio.lokadoraapi.repositories

import br.com.tharcio.lokadoraapi.models.UserModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserModel, Int> {
    fun findByNameContainingIgnoreCase(pageable: Pageable, name: String) : Page<UserModel>
}