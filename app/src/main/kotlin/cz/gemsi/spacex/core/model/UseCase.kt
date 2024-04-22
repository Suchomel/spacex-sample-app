package cz.gemsi.spacex.core.model

interface UseCase<P, R> {
    operator fun invoke(params: P): R
}

interface UnitUseCase<R> {
    operator fun invoke(): R
}

interface SuspendUseCase<P, R> {
    suspend operator fun invoke(params: P): R
}

interface SuspendUnitUseCase<R> {
    suspend operator fun invoke(): R
}

interface ResultUseCase<P, R> {
    operator fun invoke(params: P): Result<R>
}

interface ResultUnitUseCase<R> {
    operator fun invoke(): Result<R>
}

interface SuspendResultUseCase<P, R> {
    suspend operator fun invoke(params: P): Result<R>
}

interface SuspendResultUnitUseCase<R> {
    suspend operator fun invoke(): Result<R>
}