/*
 * Copyright 2010-2023 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlin.js

@SinceKotlin("2.2")
@ExperimentalWasmJsInterop
public actual typealias JsBigInt = Long

@SinceKotlin("2.2")
@ExperimentalWasmJsInterop
public actual fun JsBigInt.toLong(): Long =
    unsafeCast<Long>()

@SinceKotlin("2.2")
@ExperimentalWasmJsInterop
public actual fun Long.toJsBigInt(): JsBigInt =
    unsafeCast<JsBigInt>()
