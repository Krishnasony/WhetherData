package i.krishnasony.whetherproject.api

interface ApiCallBack<T> {
    abstract fun onSuccess(t: T)
    abstract fun onFailure(message: String)
}