package br.com.tharcio.lokadoraapi.dtos.response

class PageResponse<T> (
    val elements: List<T>,
    val currentPage: Int,
    val totalElements: Long,
    val totalPages: Int
)
