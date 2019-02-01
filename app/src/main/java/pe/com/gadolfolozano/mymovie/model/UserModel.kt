package pe.com.gadolfolozano.mymovie.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class UserModel {
    var id: String? = null
    var name: String? = null
    var email: String? = null
}
