package com.example.data.mapper.base

/**
 * Created by EUNICE BAKARE T. on 11/03/2022
 * Email: eunice@reach.africa
 */
interface EntityModelMapper<E, D> {

    fun mapFromEntity(entity: E): D

    fun mapToEntity(domain: D): E

    fun mapFromEntityList(entities: List<E>): List<D> {
        return entities.mapTo(mutableListOf(), ::mapFromEntity)
    }

    fun mapFromDomainList(domainModels: List<D>): List<E> {
        return domainModels.mapTo(mutableListOf(), ::mapToEntity)
    }
}