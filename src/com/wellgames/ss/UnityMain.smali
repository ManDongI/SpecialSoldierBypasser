.class public Lcom/wellgames/ss/UnityMain;
.super Lcom/unity3d/player/UnityPlayerNativeActivity;
.source "UnityMain.java"


# direct methods
.method public constructor <init>()V
    .registers 1

    .prologue
    .line 7
    invoke-direct {p0}, Lcom/unity3d/player/UnityPlayerNativeActivity;-><init>()V

    return-void
.end method


# virtual methods
.method public getPackageManager()Landroid/content/pm/PackageManager;
    .registers 3

    .prologue
    .line 10
    new-instance v0, Lcom/wellgames/ss/ProxyPackageManager;

    invoke-super {p0}, Lcom/unity3d/player/UnityPlayerNativeActivity;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v1

    invoke-direct {v0, v1}, Lcom/wellgames/ss/ProxyPackageManager;-><init>(Landroid/content/pm/PackageManager;)V

    return-object v0
.end method
