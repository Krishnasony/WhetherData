package i.krishnasony.whetherproject.api

interface ApiCallBack<T> {
     fun onSuccess(t: T)
     fun onFailure(message: String)
}