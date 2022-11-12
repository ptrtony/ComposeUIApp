package com.example.composecoursety.permission

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@ExperimentalPermissionsApi
fun PermissionState.isPermanentDenied(): Boolean {
    return !hasPermission && !shouldShowRationale
}