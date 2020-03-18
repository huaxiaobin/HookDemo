package com.hyb.hookdemo;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * @author Mr.HuaYunBin
 */
public class HookTest implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        String hookPackageName = "com.hyb.hookdemo";
        if (hookPackageName.equals(lpparam.packageName)) {
            XposedBridge.log("已成功Hook到HookDemo应用");
            Class clazz = lpparam.classLoader.loadClass("com.hyb.hookdemo.MainActivity");
            XposedHelpers.findAndHookMethod(clazz, "toastMsg", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult("你被劫持啦");
                }
            });
        }
    }
}
