package com.example.tt;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** TtPlugin */
public class TtPlugin implements FlutterPlugin, ActivityAware {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity

  private static final String CHANNEL = "plugins.flutter.io/tt";
  private MethodCallHandler handler;
  ToastBhs toastBhs;
  private MethodChannel methodChannel;

  private Activity activity;
  @SuppressWarnings("deprecation")
  public static void registerWith(io.flutter.plugin.common.PluginRegistry.Registrar registrar) {
    TtPlugin plugin = new TtPlugin();
    plugin.setUpChannel(registrar.context(), registrar.activity(), registrar.messenger());
  }
  private void setUpChannel(Context context, Activity activity, BinaryMessenger messenger) {
    methodChannel = new MethodChannel(messenger, CHANNEL);
    toastBhs = new ToastBhs(context, activity);
    handler = new MethodCallHandler(toastBhs);
    methodChannel.setMethodCallHandler(handler);
  }
  @Override
  public void onAttachedToEngine(FlutterPluginBinding binding) {
    setUpChannel(binding.getApplicationContext(), null, binding.getBinaryMessenger());
  }


  @Override
  public void onDetachedFromEngine(FlutterPluginBinding binding) {
    methodChannel.setMethodCallHandler(null);
    methodChannel = null;
    toastBhs = null;
  }

  @Override
  public void onAttachedToActivity(ActivityPluginBinding binding) {
    toastBhs.setActivity(binding.getActivity());
  }

  @Override
  public void onDetachedFromActivity() {
    toastBhs.setActivity(null);
  }

  @Override
  public void onReattachedToActivityForConfigChanges(ActivityPluginBinding binding) {
    onAttachedToActivity(binding);
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {
    onDetachedFromActivity();
  }

}
