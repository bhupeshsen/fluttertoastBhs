import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:tt/tt.dart';

void main() {
  const MethodChannel channel = MethodChannel('tt');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await Tt.platformVersion, '42');
  });
}
