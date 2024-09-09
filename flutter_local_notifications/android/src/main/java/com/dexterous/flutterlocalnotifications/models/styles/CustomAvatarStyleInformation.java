package com.dexterous.flutterlocalnotifications.models.styles;

import com.dexterous.flutterlocalnotifications.models.BitmapSource;

public class CustomAvatarStyleInformation extends DefaultStyleInformation {

    public Object customAvatar;
    public BitmapSource customAvatarBitmapSource;

    public CustomAvatarStyleInformation(
            Object customAvatar,
            BitmapSource customAvatarBitmapSource,
            Boolean htmlFormatTitle,
            Boolean htmlFormatBody) {
        super(htmlFormatTitle, htmlFormatBody);
        this.customAvatar = customAvatar;
        this.customAvatarBitmapSource = customAvatarBitmapSource;
    }
}
