package br.com.tharcio.lokadoraapi.repositories

import br.com.tharcio.lokadoraapi.entities.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserDAO: JpaRepository<User, Int> {
    fun findByNameContainingIgnoreCase(pageable: Pageable, name: String) : Page<User>
    fun findByEmail(email: String): Optional<User>
    fun existsByEmail(value: String): Boolean

}