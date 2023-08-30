/*
 * Copyright (c) Kudo Chien.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE-react-native-v8 file in the root directory of this source tree.
 */
package com.quickjs.expo;

import android.content.Context;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;

import java.util.Collections;
import java.util.List;

import expo.modules.core.interfaces.Package;
import expo.modules.core.interfaces.ReactNativeHostHandler;
import com.quickjs.QuickJSExecutorFactory;

public class QuickJSExpoAdapterPackage implements Package {
    @Override
    public List<? extends ReactNativeHostHandler> createReactNativeHostHandlers(Context context) {
        return Collections.singletonList(new ReactNativeHostHandler() {

            @Nullable
            @Override
            public String getBundleAssetName(boolean useDeveloperSupport) {
                final String quickJSBundleAssetName = QuickJSExecutorFactory.getBundleAssetName(context, useDeveloperSupport);
                if (quickJSBundleAssetName != null) {
                    return quickJSBundleAssetName;
                }
                return null;
            }

            @Override
            public JavaScriptExecutorFactory getJavaScriptExecutorFactory() {
                return new QuickJSExecutorFactory(
                        context,
                        context.getPackageName(),
                        AndroidInfoHelpers.getFriendlyDeviceName(),
                        BuildConfig.DEBUG);
            }
        });
    }
}