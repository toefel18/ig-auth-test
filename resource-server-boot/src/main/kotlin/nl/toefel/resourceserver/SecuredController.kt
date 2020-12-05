package nl.toefel.resourceserver

import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

val customerRepository = mutableMapOf(
    "1" to Customer("1", "Sjakie", 15),
    "2" to Customer("2", "Klaas", 33),
    "3" to Customer("3", "Pam", 45),
    "4" to Customer("4", "Daphne", 56)
)

@RestController
class SecuredController {
    @PostMapping("/customers")
    fun createCustomer(@RequestBody newCustomer: Customer): Customer {
        if (customerRepository.containsKey(newCustomer.id)) {
            throw ResponseStatusException(BAD_REQUEST, "user with id ${newCustomer.id} already exists")
        }
        customerRepository.put(newCustomer.id, newCustomer)
        return newCustomer
    }

    @GetMapping("/customers/{id}")
    fun getCustomer(@PathVariable("id") id: String) : Customer {
        return customerRepository[id] ?: throw ResponseStatusException(NOT_FOUND, "user with id $id not found")
    }

    @GetMapping("/customers")
    fun listCustomers(): List<Customer> {
        return customerRepository.values.toList()
    }

    @DeleteMapping("/customers/{id}")
    fun deleteCustomer(@PathVariable("id") id: String): Customer {
        return customerRepository[id]?.run {
            customerRepository.remove(id)
        } ?: throw ResponseStatusException(NOT_FOUND, "no customer with id $id")
    }
}