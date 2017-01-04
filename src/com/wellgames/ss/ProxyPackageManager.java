package com.wellgames.ss;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.*;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;

import java.util.List;

public class ProxyPackageManager extends PackageManager {
    private PackageManager proxied;

    public ProxyPackageManager(PackageManager proxied) {
        this.proxied = proxied;
    }

    /*
    @Override
    public String getPermissionControllerPackageName() {
        return proxied.getPermissionControllerPackageName();
    }
    */

    // hide annotation
    public String getPermissionControllerPackageName() {
        try {
            return (String) proxied.getClass().getDeclaredMethod("getPermissionControllerPackageName").invoke(proxied);
        } catch (Exception e) {
            return null;
        }
    }

    // signature hook
    @Override
    public PackageInfo getPackageInfo(String s, int i) throws NameNotFoundException {
        PackageInfo real = proxied.getPackageInfo(s, i);
        if ((i & PackageManager.GET_SIGNATURES) != 0)
            real.signatures = new Signature[] { new Signature(new byte[] { 48, -126, 1, -61, 48, -126, 1, 44, -96, 3, 2, 1, 2, 2, 4, 72, 35, -53, -3, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 5, 5, 0, 48, 37, 49, 15, 48, 13, 6, 3, 85, 4, 3, 12, 6, 108, 101, 101, 119, 111, 111, 49, 18, 48, 16, 6, 3, 85, 4, 10, 12, 9, 119, 101, 108, 108, 103, 97, 109, 101, 115, 48, 32, 23, 13, 49, 52, 49, 49, 49, 48, 48, 53, 53, 48, 51, 55, 90, 24, 15, 50, 48, 54, 52, 49, 48, 50, 56, 48, 53, 53, 48, 51, 55, 90, 48, 37, 49, 15, 48, 13, 6, 3, 85, 4, 3, 12, 6, 108, 101, 101, 119, 111, 111, 49, 18, 48, 16, 6, 3, 85, 4, 10, 12, 9, 119, 101, 108, 108, 103, 97, 109, 101, 115, 48, -127, -97, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 3, -127, -115, 0, 48, -127, -119, 2, -127, -127, 0, -88, 51, -83, -122, -69, 96, 105, -43, 93, -29, 9, 73, 106, 25, 24, 16, 106, 92, -68, -123, 99, -93, 49, 100, -100, -29, 29, 40, 55, -15, 114, -15, -90, -68, 68, -113, -16, 13, -12, 6, -58, -14, 25, 96, -39, 80, 27, -118, 31, -124, -32, -119, -104, -107, 20, -4, -11, -106, -15, 84, 95, 33, 73, -9, -79, -32, 121, -13, 94, -60, -40, 27, -13, 58, -24, -63, -125, 14, 94, 54, 28, -22, 24, -58, 6, -83, -13, 36, 59, -14, -15, -109, 37, 97, -36, 96, -67, -59, -1, -79, -78, 32, -93, -77, -80, 112, 89, 100, 38, -64, -30, -112, -42, 100, -60, -98, 90, 109, 89, 126, -53, 111, 113, -14, 28, 7, -81, 69, 2, 3, 1, 0, 1, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 5, 5, 0, 3, -127, -127, 0, 117, 39, -97, 81, 107, 63, -56, -46, 121, 111, -65, -86, -103, 1, 82, -79, 120, 113, 16, 60, 85, -118, 54, 85, -6, -94, -30, 97, 80, -126, -24, -6, 124, -66, -33, -95, 22, -127, -107, 24, 111, 59, -69, -2, 79, 11, -91, -19, -17, 46, 124, -90, -128, 57, 8, -45, 87, -27, -93, -101, 22, 122, -14, -56, 22, -96, 71, -78, 98, 85, -100, 115, -64, 32, 107, -21, -96, 122, 7, 17, -4, 125, -117, 94, 82, 122, 80, -23, 30, 48, 6, -104, -113, 70, -83, 121, 13, -105, 79, 13, 32, -76, -24, -117, 39, 61, -17, -94, -58, -5, 116, 96, -90, 77, 18, -43, -95, 15, 2, 69, 14, 93, 29, 72, -123, -40, 32, 40 }) };
        return real;
    }

    @Override
    public String[] currentToCanonicalPackageNames(String[] strings) {
        return proxied.currentToCanonicalPackageNames(strings);
    }

    @Override
    public String[] canonicalToCurrentPackageNames(String[] strings) {
        return proxied.canonicalToCurrentPackageNames(strings);
    }

    @Override
    public Intent getLaunchIntentForPackage(String s) {
        return proxied.getLaunchIntentForPackage(s);
    }

    @Override
    public Intent getLeanbackLaunchIntentForPackage(String s) {
        return proxied.getLeanbackLaunchIntentForPackage(s);
    }

    @Override
    public int[] getPackageGids(String s) throws NameNotFoundException {
        return proxied.getPackageGids(s);
    }

    @Override
    public int[] getPackageGids(String s, int i) throws NameNotFoundException {
        return proxied.getPackageGids(s, i);
    }

    @Override
    public int getPackageUid(String s, int i) throws NameNotFoundException {
        return proxied.getPackageUid(s, i);
    }

    @Override
    public PermissionInfo getPermissionInfo(String s, int i) throws NameNotFoundException {
        return proxied.getPermissionInfo(s, i);
    }

    @Override
    public List<PermissionInfo> queryPermissionsByGroup(String s, int i) throws NameNotFoundException {
        return proxied.queryPermissionsByGroup(s, i);
    }

    @Override
    public PermissionGroupInfo getPermissionGroupInfo(String s, int i) throws NameNotFoundException {
        return proxied.getPermissionGroupInfo(s, i);
    }

    @Override
    public List<PermissionGroupInfo> getAllPermissionGroups(int i) {
        return proxied.getAllPermissionGroups(i);
    }

    @Override
    public ApplicationInfo getApplicationInfo(String s, int i) throws NameNotFoundException {
        return proxied.getApplicationInfo(s, i);
    }

    @Override
    public ActivityInfo getActivityInfo(ComponentName componentName, int i) throws NameNotFoundException {
        return proxied.getActivityInfo(componentName, i);
    }

    @Override
    public ActivityInfo getReceiverInfo(ComponentName componentName, int i) throws NameNotFoundException {
        return proxied.getReceiverInfo(componentName, i);
    }

    @Override
    public ServiceInfo getServiceInfo(ComponentName componentName, int i) throws NameNotFoundException {
        return proxied.getServiceInfo(componentName, i);
    }

    @Override
    public ProviderInfo getProviderInfo(ComponentName componentName, int i) throws NameNotFoundException {
        return proxied.getProviderInfo(componentName, i);
    }

    @Override
    public List<PackageInfo> getInstalledPackages(int i) {
        return proxied.getInstalledPackages(i);
    }

    @Override
    public List<PackageInfo> getPackagesHoldingPermissions(String[] strings, int i) {
        return proxied.getPackagesHoldingPermissions(strings, i);
    }

    @Override
    public int checkPermission(String s, String s1) {
        return proxied.checkPermission(s, s1);
    }

    @Override
    public boolean isPermissionRevokedByPolicy(String s, String s1) {
        return proxied.isPermissionRevokedByPolicy(s, s1);
    }

    @Override
    public boolean addPermission(PermissionInfo permissionInfo) {
        return proxied.addPermission(permissionInfo);
    }

    @Override
    public boolean addPermissionAsync(PermissionInfo permissionInfo) {
        return proxied.addPermissionAsync(permissionInfo);
    }

    @Override
    public void removePermission(String s) {
        proxied.removePermission(s);
    }

    @Override
    public int checkSignatures(String s, String s1) {
        return proxied.checkSignatures(s, s1);
    }

    @Override
    public int checkSignatures(int i, int i1) {
        return proxied.checkSignatures(i, i1);
    }

    @Override
    public String[] getPackagesForUid(int i) {
        return proxied.getPackagesForUid(i);
    }

    @Override
    public String getNameForUid(int i) {
        return proxied.getNameForUid(i);
    }

    @Override
    public List<ApplicationInfo> getInstalledApplications(int i) {
        return proxied.getInstalledApplications(i);
    }

    @Override
    public String[] getSystemSharedLibraryNames() {
        return proxied.getSystemSharedLibraryNames();
    }

    @Override
    public FeatureInfo[] getSystemAvailableFeatures() {
        return proxied.getSystemAvailableFeatures();
    }

    @Override
    public boolean hasSystemFeature(String s) {
        return proxied.hasSystemFeature(s);
    }

    @Override
    public boolean hasSystemFeature(String s, int i) {
        return proxied.hasSystemFeature(s, i);
    }

    @Override
    public ResolveInfo resolveActivity(Intent intent, int i) {
        return proxied.resolveActivity(intent, i);
    }

    @Override
    public List<ResolveInfo> queryIntentActivities(Intent intent, int i) {
        return proxied.queryIntentActivities(intent, i);
    }

    @Override
    public List<ResolveInfo> queryIntentActivityOptions(ComponentName componentName, Intent[] intents, Intent intent, int i) {
        return proxied.queryIntentActivityOptions(componentName, intents, intent, i);
    }

    @Override
    public List<ResolveInfo> queryBroadcastReceivers(Intent intent, int i) {
        return proxied.queryBroadcastReceivers(intent, i);
    }

    @Override
    public ResolveInfo resolveService(Intent intent, int i) {
        return proxied.resolveService(intent, i);
    }

    @Override
    public List<ResolveInfo> queryIntentServices(Intent intent, int i) {
        return proxied.queryIntentServices(intent, i);
    }

    @Override
    public List<ResolveInfo> queryIntentContentProviders(Intent intent, int i) {
        return proxied.queryIntentContentProviders(intent, i);
    }

    @Override
    public ProviderInfo resolveContentProvider(String s, int i) {
        return proxied.resolveContentProvider(s, i);
    }

    @Override
    public List<ProviderInfo> queryContentProviders(String s, int i, int i1) {
        return proxied.queryContentProviders(s, i, i1);
    }

    @Override
    public InstrumentationInfo getInstrumentationInfo(ComponentName componentName, int i) throws NameNotFoundException {
        return proxied.getInstrumentationInfo(componentName, i);
    }

    @Override
    public List<InstrumentationInfo> queryInstrumentation(String s, int i) {
        return proxied.queryInstrumentation(s, i);
    }

    @Override
    public Drawable getDrawable(String s, int i, ApplicationInfo applicationInfo) {
        return proxied.getDrawable(s, i, applicationInfo);
    }

    @Override
    public Drawable getActivityIcon(ComponentName componentName) throws NameNotFoundException {
        return proxied.getActivityIcon(componentName);
    }

    @Override
    public Drawable getActivityIcon(Intent intent) throws NameNotFoundException {
        return proxied.getActivityIcon(intent);
    }

    @Override
    public Drawable getActivityBanner(ComponentName componentName) throws NameNotFoundException {
        return proxied.getActivityBanner(componentName);
    }

    @Override
    public Drawable getActivityBanner(Intent intent) throws NameNotFoundException {
        return proxied.getActivityBanner(intent);
    }

    @Override
    public Drawable getDefaultActivityIcon() {
        return proxied.getDefaultActivityIcon();
    }

    @Override
    public Drawable getApplicationIcon(ApplicationInfo applicationInfo) {
        return proxied.getApplicationIcon(applicationInfo);
    }

    @Override
    public Drawable getApplicationIcon(String s) throws NameNotFoundException {
        return proxied.getApplicationIcon(s);
    }

    @Override
    public Drawable getApplicationBanner(ApplicationInfo applicationInfo) {
        return proxied.getApplicationBanner(applicationInfo);
    }

    @Override
    public Drawable getApplicationBanner(String s) throws NameNotFoundException {
        return proxied.getApplicationBanner(s);
    }

    @Override
    public Drawable getActivityLogo(ComponentName componentName) throws NameNotFoundException {
        return proxied.getActivityLogo(componentName);
    }

    @Override
    public Drawable getActivityLogo(Intent intent) throws NameNotFoundException {
        return proxied.getActivityLogo(intent);
    }

    @Override
    public Drawable getApplicationLogo(ApplicationInfo applicationInfo) {
        return proxied.getApplicationLogo(applicationInfo);
    }

    @Override
    public Drawable getApplicationLogo(String s) throws NameNotFoundException {
        return proxied.getApplicationLogo(s);
    }

    @Override
    public Drawable getUserBadgedIcon(Drawable drawable, UserHandle userHandle) {
        return proxied.getUserBadgedIcon(drawable, userHandle);
    }

    @Override
    public Drawable getUserBadgedDrawableForDensity(Drawable drawable, UserHandle userHandle, Rect rect, int i) {
        return proxied.getUserBadgedDrawableForDensity(drawable, userHandle, rect, i);
    }

    @Override
    public CharSequence getUserBadgedLabel(CharSequence charSequence, UserHandle userHandle) {
        return proxied.getUserBadgedLabel(charSequence, userHandle);
    }

    @Override
    public CharSequence getText(String s, int i, ApplicationInfo applicationInfo) {
        return proxied.getText(s, i, applicationInfo);
    }

    @Override
    public XmlResourceParser getXml(String s, int i, ApplicationInfo applicationInfo) {
        return proxied.getXml(s, i, applicationInfo);
    }

    @Override
    public CharSequence getApplicationLabel(ApplicationInfo applicationInfo) {
        return proxied.getApplicationLabel(applicationInfo);
    }

    @Override
    public Resources getResourcesForActivity(ComponentName componentName) throws NameNotFoundException {
        return proxied.getResourcesForActivity(componentName);
    }

    @Override
    public Resources getResourcesForApplication(ApplicationInfo applicationInfo) throws NameNotFoundException {
        return proxied.getResourcesForApplication(applicationInfo);
    }

    @Override
    public Resources getResourcesForApplication(String s) throws NameNotFoundException {
        return proxied.getResourcesForApplication(s);
    }

    @Override
    public PackageInfo getPackageArchiveInfo(String archiveFilePath, int flags) {
        return proxied.getPackageArchiveInfo(archiveFilePath, flags);
    }

    @Override
    public void verifyPendingInstall(int i, int i1) {
        proxied.verifyPendingInstall(i, i1);
    }

    @Override
    public void extendVerificationTimeout(int i, int i1, long l) {
        proxied.extendVerificationTimeout(i, i1, l);
    }

    @Override
    public void setInstallerPackageName(String s, String s1) {
        proxied.setInstallerPackageName(s, s1);
    }

    @Override
    public String getInstallerPackageName(String s) {
        return proxied.getInstallerPackageName(s);
    }

    @Override
    @Deprecated
    public void addPackageToPreferred(String s) {
        proxied.addPackageToPreferred(s);
    }

    @Override
    @Deprecated
    public void removePackageFromPreferred(String s) {
        proxied.removePackageFromPreferred(s);
    }

    @Override
    public List<PackageInfo> getPreferredPackages(int i) {
        return proxied.getPreferredPackages(i);
    }

    @Override
    @Deprecated
    public void addPreferredActivity(IntentFilter intentFilter, int i, ComponentName[] componentNames, ComponentName componentName) {
        proxied.addPreferredActivity(intentFilter, i, componentNames, componentName);
    }

    @Override
    public void clearPackagePreferredActivities(String s) {
        proxied.clearPackagePreferredActivities(s);
    }

    @Override
    public int getPreferredActivities(List<IntentFilter> list, List<ComponentName> list1, String s) {
        return proxied.getPreferredActivities(list, list1, s);
    }

    @Override
    public void setComponentEnabledSetting(ComponentName componentName, int i, int i1) {
        proxied.setComponentEnabledSetting(componentName, i, i1);
    }

    @Override
    public int getComponentEnabledSetting(ComponentName componentName) {
        return proxied.getComponentEnabledSetting(componentName);
    }

    @Override
    public void setApplicationEnabledSetting(String s, int i, int i1) {
        proxied.setApplicationEnabledSetting(s, i, i1);
    }

    @Override
    public int getApplicationEnabledSetting(String s) {
        return proxied.getApplicationEnabledSetting(s);
    }

    @Override
    public boolean isSafeMode() {
        return proxied.isSafeMode();
    }

    @Override
    public PackageInstaller getPackageInstaller() {
        return proxied.getPackageInstaller();
    }
}
