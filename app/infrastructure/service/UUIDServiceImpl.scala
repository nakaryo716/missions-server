package infrastructure.service

import java.util.UUID

import domain.service.UUIDService

class UUIDServiceImpl extends UUIDService {
    def generate(): String = {
        UUID.randomUUID().toString()
    }
}
