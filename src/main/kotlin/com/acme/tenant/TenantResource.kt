package com.acme.tenant

import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path

@Path("/tenant")
class TenantResource {

    @Inject
    lateinit var tenantRepository: TenantRepository

    @POST
    @ReactiveTransactional
    fun addTenant(): Uni<Tenant> {
        val tenant = Tenant()
        tenant.name = "Acme"
        tenant.webDomain = "acme.com"
        return tenantRepository.persist(tenant)
    }

    @GET
    fun getAllTenants(): Multi<Tenant> {
        return tenantRepository.streamAll()
    }

}