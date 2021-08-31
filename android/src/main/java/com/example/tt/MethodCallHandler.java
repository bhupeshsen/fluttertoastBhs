// Copyright 2013 The Flutter Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package com.example.tt;

import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

/** Handles the method calls for the plugin. */
class MethodCallHandler implements MethodChannel.MethodCallHandler {

  private ToastBhs toastBhs;

  MethodCallHandler(ToastBhs toastBhs) {
    this.toastBhs = toastBhs;
  }

  @Override
  public void onMethodCall(MethodCall call, MethodChannel.Result result) {
    switch (call.method) {


      case "showToast":
        expectMapArguments(call);
        // Android does not support showing the share sheet at a particular point on screen.

        String msg = call.argument("msg").toString();
        // Android does not support showing the share sheet at a particular point on screen.
        toastBhs.showToast(msg);
        result.success(null);
        break;
      case "getPlatformVersion":
        result.success("Android " + android.os.Build.VERSION.RELEASE);
        break;
      default:
        result.notImplemented();
        break;
    }
  }

  private void expectMapArguments(MethodCall call) throws IllegalArgumentException {
    if (!(call.arguments instanceof Map)) {
      throw new IllegalArgumentException("Map argument expected");
    }
  }
}
