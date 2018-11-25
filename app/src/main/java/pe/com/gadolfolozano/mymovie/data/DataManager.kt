package pe.com.gadolfolozano.mymovie.data

import android.arch.lifecycle.LiveData
import pe.com.gadolfolozano.mymovie.model.BaseModel

interface DataManager {
    val dummyValue: LiveData<BaseModel>
}
