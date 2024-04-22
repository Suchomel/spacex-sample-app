package cz.gemsi.spacex.core.model

sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()

    data class Error<out T>(val error: ErrorResult, val data: T? = null) : Result<T>()

    companion object {

        fun <T> success(data: T): Result<T> =
            Success(
                data = data
            )

        fun <T> error(error: ErrorResult, data: T? = null): Result<T> =
            Error(
                error,
                data
            )

        fun <T> error(
            errorCode: ErrorCode,
            data: T? = null,
            message: String? = null,
            throwable: Throwable? = null
        ): Result<T> =
            Error(
                ErrorResult(
                    code = errorCode,
                    message = message,
                    throwable = throwable
                ), data
            )
    }

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[data=$data]"
            is Error -> "Error[exception=${error.throwable}"
        }
    }

    fun isFinished() = this is Success || this is Error

    fun isSuccess() = this is Success

    fun isError() = this is Error

    /**
     * Returns the encapsulated value if this instance represents [Success] or null is returned
     */
    fun getOrNull() = if (this is Success) data else null

    fun <E> map(function: (T) -> E): Result<E> {
        return when (this) {
            is Success -> {
                Success(function(this.data))
            }

            is Error -> {
                Error(this.error)
            }
        }
    }
}

interface ErrorCode {

    companion object {
        val UNDEFINED =
            errorCode(-1)
    }

    /**
     * Integer representation of the error
     */
    val value: Int
}

/**
 * The error code builder
 *
 * @param code Integer representation of the error
 *
 * @see ErrorCode
 */
fun errorCode(code: Int) = object :
    ErrorCode {
    override val value = code
}

open class ErrorResult(
    open val code: ErrorCode = ErrorCode.UNDEFINED,
    open val message: String? = null,
    open val throwable: Throwable? = null
) {

    override fun toString(): String {
        return "Error[code:${code.value}] [message:$message] [exception:${throwable?.message}]"
    }
}

/**
 * Wrap a suspending [call] in try/catch. In case an exception is thrown, a [Result.Error] is
 * created based on the [errorCore] and the [errorMessage].
 */
suspend fun <T : Any> callSafe(
    call: suspend () -> Result<T>,
    errorCore: ErrorCode = ErrorCode.UNDEFINED,
    errorMessage: String
): Result<T> {
    return try {
        call()
    } catch (e: Throwable) {
        Result.Error(
            ErrorResult(
                errorCore,
                errorMessage,
                e
            )
        )
    }
}

/**
 * @since 0.4.0
 * @see callSafe
 */
suspend fun <T : Any> callSafe(
    call: suspend () -> Result<T>,
    errorCode: ErrorCode = ErrorCode.UNDEFINED,
    lazyMessage: () -> Any
) = callSafe(
    call,
    errorCode,
    lazyMessage().toString()
)
