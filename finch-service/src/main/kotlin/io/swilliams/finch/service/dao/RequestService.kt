package io.swilliams.finch.service.dao

import io.swilliams.finch.service.model.Request
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RequestService(val repository: RequestRepository) {

    @Transactional
    fun save(request: Request) {
        repository.save(request)
    }
}
