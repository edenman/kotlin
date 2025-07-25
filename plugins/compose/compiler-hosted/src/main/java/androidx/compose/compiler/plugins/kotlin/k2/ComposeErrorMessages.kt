/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.compose.compiler.plugins.kotlin.k2

import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryToRendererMap
import org.jetbrains.kotlin.diagnostics.KtDiagnosticRenderers
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirDiagnosticRenderers

object ComposeErrorMessages : BaseDiagnosticRendererFactory() {
    override val MAP by KtDiagnosticFactoryToRendererMap("Compose") { map ->
        map.put(
            ComposeErrors.COMPOSABLE_INVOCATION,
            "@Composable invocations can only happen from the context of a @Composable function"
        )

        map.put(
            ComposeErrors.COMPOSABLE_EXPECTED,
            "Functions which invoke @Composable functions must be marked with the @Composable " +
                    "annotation"
        )

        map.put(
            ComposeErrors.NONREADONLY_CALL_IN_READONLY_COMPOSABLE,
            "Composables marked with @ReadOnlyComposable can only call other @ReadOnlyComposable " +
                    "composables"
        )

        map.put(
            ComposeErrors.CAPTURED_COMPOSABLE_INVOCATION,
            "Composable calls are not allowed inside the {0} parameter of {1}",
            FirDiagnosticRenderers.VARIABLE_NAME,
            FirDiagnosticRenderers.DECLARATION_NAME
        )

        map.put(
            ComposeErrors.ILLEGAL_TRY_CATCH_AROUND_COMPOSABLE,
            "Try catch is not supported around composable function invocations."
        )

        map.put(
            ComposeErrors.MISSING_DISALLOW_COMPOSABLE_CALLS_ANNOTATION,
            "Parameter {0} cannot be inlined inside of lambda argument {1} of {2} " +
                    "without also being annotated with @DisallowComposableCalls",
            FirDiagnosticRenderers.VARIABLE_NAME,
            FirDiagnosticRenderers.VARIABLE_NAME,
            FirDiagnosticRenderers.DECLARATION_NAME,
        )

        map.put(
            ComposeErrors.DEPRECATED_OPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE,
            "Detected a @Composable function that overrides an open function compiled with older compiler that is known to " +
                    "crash at runtime. Consider recompiling the dependency with a newer compiler version (>= 2.1.20) to get correct " +
                    "behavior. See https://issuetracker.google.com/165812010 for more details."
        )

        map.put(
            ComposeErrors.COMPOSABLE_SUSPEND_FUN,
            "Composable function cannot be annotated as suspend"
        )

        map.put(
            ComposeErrors.COMPOSABLE_FUN_MAIN,
            "Composable main functions are not currently supported"
        )

        map.put(
            ComposeErrors.COMPOSABLE_PROPERTY_REFERENCE,
            "@Composable property references are not currently supported."
        )

        map.put(
            ComposeErrors.COMPOSABLE_PROPERTY_BACKING_FIELD,
            "Composable properties are not able to have backing fields"
        )

        map.put(
            ComposeErrors.COMPOSABLE_VAR,
            "Composable properties are not able to have backing fields"
        )

        map.put(
            ComposeErrors.COMPOSE_INVALID_DELEGATE,
            "Composable setValue operator is not currently supported."
        )

        map.put(
            ComposeErrors.MISMATCHED_COMPOSABLE_IN_EXPECT_ACTUAL,
            "Mismatched @Composable annotation between expect and actual declaration"
        )

        map.put(
            ComposeErrors.COMPOSABLE_INAPPLICABLE_TYPE,
            "@Composable annotation is not applicable to {0}",
            FirDiagnosticRenderers.RENDER_TYPE
        )

        map.put(
            ComposeErrors.COMPOSE_APPLIER_CALL_MISMATCH,
            "Calling a {1} composable function where a {0} composable was expected",
            KtDiagnosticRenderers.TO_STRING,
            KtDiagnosticRenderers.TO_STRING
        )

        map.put(
            ComposeErrors.COMPOSE_APPLIER_PARAMETER_MISMATCH,
            "A {1} composable parameter was provided where a {0} composable was expected",
            KtDiagnosticRenderers.TO_STRING,
            KtDiagnosticRenderers.TO_STRING
        )

        map.put(
            ComposeErrors.COMPOSE_APPLIER_DECLARATION_MISMATCH,
            "The composition target of an override must match the ancestor target"
        )

        map.put(
            ComposeErrors.ABSTRACT_COMPOSABLE_DEFAULT_PARAMETER_VALUE,
            "Default parameters in abstract @Composable functions are not supported before language version 2.1 (configured version is {0})",
            KtDiagnosticRenderers.TO_STRING
        )

        map.put(
            ComposeErrors.OPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE,
            "Default parameters in open @Composable functions are not supported before language version 2.2 (configured version is {0})",
            KtDiagnosticRenderers.TO_STRING
        )
    }
}