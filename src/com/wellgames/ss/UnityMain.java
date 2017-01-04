package com.wellgames.ss;

import android.content.pm.PackageManager;

import com.unity3d.player.UnityPlayerNativeActivity;

public class UnityMain extends UnityPlayerNativeActivity {
    @Override
    public PackageManager getPackageManager() {
        return new ProxyPackageManager(super.getPackageManager());
    }
}