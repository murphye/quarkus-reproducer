package com.acme.tenant

import io.quarkus.hibernate.reactive.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class TenantRepository: PanacheRepository<Tenant> {

}