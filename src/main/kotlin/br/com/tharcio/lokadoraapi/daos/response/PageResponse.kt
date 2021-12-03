package br.com.tharcio.lokadoraapi.daos.response

class PageResponse<T> (
    var elements: List<T>,
    var currentPage: Int,
    var totalElements: Long,
    var totalPages: Int
)
