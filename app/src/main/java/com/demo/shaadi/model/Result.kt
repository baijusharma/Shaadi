package com.demo.shaadi.model

data class Result(
    var cell: String = "",
    var dob: Dob? = Dob(),
    var email: String = "",
    var gender: String = "",
    var location: Location? = Location(),
    var name: Name? = Name(),
    var phone: String = "",
    var picture: Picture? = Picture()
) {
    data class Dob(
        var age: String = "",
        var date: String = ""
    )

    data class Location(
        var city: String = "",
        var country: String = "",
        var postcode: String = "",
        var state: String = "",
    )
    data class Name(
        var first: String = "",
        var last: String = "",
        var title: String = ""
    )

    data class Picture(
        var large: String = ""
    )

}