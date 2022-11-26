package constant

object Pet {
    const val UPLOAD_IMAGE = "/pet/{id}/uploadImage"
    const val CREATE = "/pet"
    const val UPDATE = "/pet"
    const val FIND_BY_STATUS = "/pet/findByStatus"
    @Deprecated(message = "Deprecated")
    const val FIND_BY_TAGS = "/pet/findByTags"
    const val GET_BY_ID = "/pet/{id}"
    const val UPDATE_WITH_FORM = "/pet/{id}"
    const val DELETE_BY_ID = "/pet/{id}"
}

object Store {
    const val CREATE = "/store/order"
    const val GET_BY_ID = "/store/order/{id}"
    const val DELETE_BY_ID = "/store/order/{id}"
    const val GET_INVENTORY = "/store/inventory"
}


object User {
    const val CREATE_WITH_ARRAY = "/user/createWithArray"
    const val CREATE_WITH_LIST = "/user/createWithList"
    const val GET_BY_ID = "/user/{id}"
    const val UPDATE_BY_ID = "/user/{id}"
    const val DELETE_BY_ID = "/user/{id}"
    const val LOGIN = "/user/login?username={username}&password={password}"
    const val LOGOUT = "/user/logout"
    const val CREATE = "/user"
}
