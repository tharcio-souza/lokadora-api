package br.com.tharcio.lokadoraapi.entities


import br.com.tharcio.lokadoraapi.enums.Roles
import javax.persistence.*


@Entity(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(name = "name")
    var name: String,

    @Column(name = "email")
    var email: String,

    @Column(name = "password")
    var password: String,

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Roles::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")])
    var roles: Set<Roles>
)


