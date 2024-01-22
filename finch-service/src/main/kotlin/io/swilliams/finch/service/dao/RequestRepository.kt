package io.swilliams.finch.service.dao

import io.swilliams.finch.service.model.Request
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface RequestRepository: CrudRepository<Request, UUID>