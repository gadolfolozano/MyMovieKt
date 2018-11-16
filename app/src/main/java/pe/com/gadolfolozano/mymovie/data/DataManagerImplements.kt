package pe.com.gadolfolozano.mymovie.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import pe.com.gadolfolozano.mymovie.model.BaseModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManagerImplements @Inject constructor() : DataManager {
    override val dummyValue: LiveData<BaseModel>
        get() {
            val data = MutableLiveData<BaseModel>()
            data.value = sucess
            return data
        }

    private val sucess: BaseModel
        get() {
            val baseModel = BaseModel()
            baseModel.isLoading = false
            baseModel.status = BaseModel.STATUS_SUCCESS

            return baseModel
        }
}
