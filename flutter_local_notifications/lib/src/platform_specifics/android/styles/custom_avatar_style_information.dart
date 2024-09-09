import '../../../../flutter_local_notifications.dart';

class CustomAvatarStyleInformation extends DefaultStyleInformation {
  const CustomAvatarStyleInformation(this.imageBitmap) : super(false, false);

  /// The bitmap to be used as the payload for the BigPicture notification.
  final AndroidBitmap<Object> imageBitmap;
}
